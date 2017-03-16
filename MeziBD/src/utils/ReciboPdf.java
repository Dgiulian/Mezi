/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import bd.Cliente;
import bd.Contrato;
import bd.Cuenta;
import bd.Pago;
import bd.Parametro;
import bd.Propiedad;
import bd.Recibo;
import bd.Recibo_detalle;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Image;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfContentByte;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import transaccion.TCliente;
import transaccion.TContrato;
import transaccion.TCuenta;
import transaccion.TPago;
import transaccion.TParametro;
import transaccion.TPropiedad;
import transaccion.TRecibo;
import transaccion.TRecibo_detalle;

/**
 *
 * @author Diego
 */
public class ReciboPdf extends BasePdf{   
    protected Integer   lineStart;
    protected Integer   lineHeight = 10;
    protected Recibo    recibo;    
    protected Pago      pago;
    protected Cuenta    cuenta;
    protected Contrato  contrato;
    protected Cliente   cliente;
    protected Cliente   propietario;
    protected Propiedad propiedad;
    protected List<Recibo_detalle> lstRecibo_detalle;
    
    public ReciboPdf(Recibo recibo){
        this.recibo      = recibo;
        this.pago        = new TPago().getById(recibo.getId_pago());        
        this.cuenta      = new TCuenta().getById(recibo.getId_cuenta());
        this.contrato    = new TContrato().getById(recibo.getId_contrato());
        this.cliente     = new TCliente().getById(recibo.getId_cliente());
        if (contrato!=null) {
            this.propietario = new TCliente().getById(contrato.getId_propietario());
            this.propiedad   = new TPropiedad().getById(contrato.getId_propiedad());
        } 
        
        HashMap<String,String> mapFiltro = new HashMap();
        mapFiltro.put("id_recibo", recibo.getId().toString());
        this.lstRecibo_detalle = new TRecibo_detalle().getListFiltro(mapFiltro);
        
        if (pago    == null) pago = new Pago();
        if (contrato == null) contrato = new Contrato();
        if (cuenta == null) cuenta = new Cuenta();
        if ( cliente == null) cliente = new Cliente();
    }
    
    @Override
    protected void addContent(Document document) {
       document.setMargins(10, 10, 10, 10);
//        Rectangle pageSize = document.getPageSize();        
       document.newPage();
       PdfContentByte cb = docWriter.getDirectContent();
       String bg_fileName = "";        
       Parametro image_url = new TParametro().getByCodigo(OptionsCfg.RECIBO_IMAGE);
       if (image_url!=null) bg_fileName = image_url.getValor();
       //System.out.println(image_url);
       setBackground(cb,bg_fileName);       
       printDatos(cb);
       printHeader(cb);
       printTitle(cb);
       printTotalPago(cb,700);
       printDetalle(cb);
       lineStart -= 30;
       printFormaPago(cb,lineStart,45);
       printFirma(cb,50,380);
    }
    
    public void printDetalle(PdfContentByte cb){
        lineStart = 640;
       lineHeight = 15;
//       addTextAligned(cb, 50,  lineStart + 25, this.baseFontSize,"Concepto",Element.ALIGN_LEFT);       
       addTextAligned(cb, 45,  lineStart + 13, this.baseFontSize,"Fecha",Element.ALIGN_LEFT);
       addTextAligned(cb, 105, lineStart + 13, this.baseFontSize,"Concepto",Element.ALIGN_LEFT);
       addTextAligned(cb, 410, lineStart + 13, this.baseFontSize,"Debe",Element.ALIGN_LEFT);
       addTextAligned(cb, 460, lineStart + 13, this.baseFontSize,"Haber",Element.ALIGN_LEFT);
       addTextAligned(cb, 525, lineStart + 13, this.baseFontSize,"Saldo",Element.ALIGN_LEFT);
       cb.moveTo(40, lineStart   + 13 - 2);
       cb.lineTo(560, lineStart  + 13 - 2);
       cb.stroke();
       
       Integer i = 0;
       for(Recibo_detalle rd :lstRecibo_detalle){
           addText(cb, 45, lineStart, TFecha.formatearFechaBdVista(rd.getFecha()));
           addText(cb, 105,lineStart, rd.getConcepto());           
           addTextAlignedRight(cb, 430,lineStart, this.baseFontSize,String.format("%7.2f",rd.getDebe()));
           addTextAlignedRight(cb, 490,lineStart, this.baseFontSize,String.format("%7.2f",rd.getHaber()));
           addTextAlignedRight(cb, 555,lineStart, this.baseFontSize,String.format("%7.2f",rd.getSaldo()));
           lineStart -= lineHeight;
           i++;
       }
    }
    
    
    public void printDatos(PdfContentByte cb){
        addText(cb, 470,810, "Nº Recibo: ");
        addText(cb, 525,810, String.format("%d",recibo.getNumero()));
        
        addText(cb, 470,800, "Fecha: ");
        addText(cb, 525,800, TFecha.formatearFechaBdVista(recibo.getFecha()));
    }
    public void printHeader(PdfContentByte cb){ }
    public void printTotalPago(PdfContentByte cb,Integer lineStart){ }
    
    public void printFormaPago(PdfContentByte cb,Integer lineStart,Integer colStart){
       if(pago==null) return;
       
       createHeadings(cb, colStart,lineStart, "Formas de pago:");
        if(pago.getEfectivo()> 0) {
            lineStart -= 20;
            addText(cb, colStart,lineStart, "Efectivo:");
            addTextAligned(cb, colStart + 120, lineStart, this.baseFontSize,String.format("$ %.2f",pago.getEfectivo()),Element.ALIGN_RIGHT);
        }   
        if(pago.getCheque_mnt()> 0) {
            lineStart -= 20;
            addText(cb, colStart,lineStart, "Cheque:");
            addTextAligned(cb, colStart + 120, lineStart,this.baseFontSize, String.format("$ %.2f",pago.getCheque_mnt()),Element.ALIGN_RIGHT);
            addText(cb, colStart + 130,lineStart, String.format("%s (Nº:%s)",pago.getCheque_ban(),pago.getCheque_num()));
            
        }
        
        if(pago.getTransf_mnt()>0) {
            lineStart -= 20;
            addText(cb, colStart,lineStart, "Transferencia:");
            addTextAligned(cb, colStart + 120,lineStart,this.baseFontSize,  String.format("$ %.2f", pago.getTransf_mnt()),Element.ALIGN_RIGHT);
            addText(cb, colStart + 130,lineStart, String.format("(Nº:%s)",pago.getTransf_num()));
        }
        Float total = pago.getEfectivo() + pago.getCheque_mnt() + pago.getTransf_mnt();
        lineStart -= 20;
        addText(cb, colStart,lineStart, "TOTAL:");
        addTextAligned(cb, colStart + 120,lineStart, this.baseFontSize,String.format("$ %.2f",total),Element.ALIGN_RIGHT);
        
    }
    public void setBackground(PdfContentByte cb, String bg_file){
        Image image = getImage(bg_file);
        if (image!=null){
//            image.scaleAbsolute(PageSize.A4.rotate());
            image.setAbsolutePosition(0, 0);            
            image.setDpi(120, 120);
            image.scaleAbsolute(cb.getPdfDocument().getPageSize());
            image.setAbsolutePosition(0, 0);
            try {
                cb.addImage(image);
            } catch (DocumentException ex) {
                Logger.getLogger(ReciboPdf.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    public void printTitle(PdfContentByte cb){}
    /*
     * Imprime la linea sobre la que se debe firmar
     */
    public void printFirma(PdfContentByte cb,Integer lineStart,Integer colStart){
       cb.moveTo(colStart, lineStart);
       cb.lineTo(colStart + 130, lineStart);
       cb.stroke();
       lineStart = 40;
       
    }
    public static void main(String[] args){
        String fileName = "c:\\recibo.pdf";
        
        Recibo recibo;
        recibo = new TRecibo().getById(2);
        ReciboPdf recibopdf = new ReciboPdf(recibo);
        fileName = "c:\\ReciboDocumento.pdf";
        recibopdf.createPdf(fileName);
        recibopdf.abrir(fileName);
    }
}

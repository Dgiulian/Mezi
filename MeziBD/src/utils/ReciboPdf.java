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
    private Integer lineStart;
    private Integer lineHeight = 10;
    Recibo recibo;    
    Pago pago;
    Cuenta cuenta;
    Contrato contrato;
    Cliente cliente;
    Propiedad propiedad;
    List<Recibo_detalle> lstRecibo_detalle;
    
    public ReciboPdf(Recibo recibo){
        this.recibo = recibo;
        pago        = new TPago().getById(recibo.getId_pago());        
        cuenta      = new TCuenta().getById(recibo.getId_cuenta());
        contrato    = new TContrato().getById(recibo.getId_contrato());
        cliente     = new TCliente().getById(recibo.getId_cliente());
        propiedad   = new TPropiedad().getById(contrato.getId_propiedad());
        
        HashMap<String,String> mapFiltro = new HashMap();
        mapFiltro.put("id_recibo", recibo.getId().toString());
        lstRecibo_detalle = new TRecibo_detalle().getListFiltro(mapFiltro);
    }
    
    @Override
    protected void addContent(Document document) {
        document.setMargins(10, 10, 10, 10);
        Rectangle pageSize = document.getPageSize();        
        document.newPage();
            
        PdfContentByte cb = docWriter.getDirectContent();
        String IMAGE = "";        
        Parametro image_url = new TParametro().getByCodigo(OptionsCfg.RECIBO_IMAGE);
        if (image_url!=null) IMAGE = image_url.getValor();
        System.out.println(image_url);
        Image image = getImage(IMAGE);
        if (image!=null){
//            image.scaleAbsolute(PageSize.A4.rotate());
            image.setAbsolutePosition(0, 0);            
            image.setDpi(120, 120);
            image.scaleAbsolute(document.getPageSize());
            image.setAbsolutePosition(0, 0);
            try {
                cb.addImage(image);
            } catch (DocumentException ex) {
                Logger.getLogger(ReciboPdf.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
       printHeader(cb);
      /***********************************/
       printFP(cb);

       lineStart = 600;
       lineHeight = 18;

       Integer i = 0;
       for(Recibo_detalle rd :lstRecibo_detalle){
            addText(cb, 45, lineStart - lineHeight * i, TFecha.formatearFechaBdVista(rd.getFecha()));
            addText(cb, 105,lineStart - lineHeight * i, rd.getConcepto());
            addTextAligned(cb, 470,lineStart - lineHeight * i, 7,String.format("%.2f",rd.getDebe()),Element.ALIGN_RIGHT);
            addTextAligned(cb, 515,lineStart - lineHeight * i, 7,String.format("%.2f",rd.getHaber()),Element.ALIGN_RIGHT);
            addTextAligned(cb, 555,lineStart - lineHeight * i, 7,String.format("%.2f",rd.getSaldo()),Element.ALIGN_RIGHT);
            i++;
       }
        
    }
    public void printHeader(PdfContentByte cb){       
//        createHeadings(cb, 450,790, "Nº Recibo:");        
//        createHeadings(cb, 450,780, "Fecha:");
        
        addText(cb, 515,800, String.format("%d",recibo.getNumero()));
        addText(cb, 515,810, TFecha.formatearFechaBdVista(recibo.getFecha()));
        lineStart = 740;
        addText(cb, 100,lineStart - lineHeight * 0, cliente.getApellido() + ", " + cliente.getNombre());
        addText(cb, 300,lineStart - lineHeight * 0, "Dirección: " + propiedad.getDireccion());
    }
    public void printFP(PdfContentByte cb){
        lineStart = 700;        
        Float total = 0f;
        total = pago.getEfectivo() + pago.getCheque_mnt() + pago.getTransf_mnt();
        
        addText(cb, 145,lineStart, String.format("%.2f",pago.getEfectivo()));
        
        if(pago.getCheque_mnt()> 0) {
            addText(cb, 145,lineStart - 20 , String.format("%.2f",pago.getCheque_mnt()));
            addText(cb, 300,lineStart - 20 , String.format("%s (Nº:%s)",pago.getCheque_ban(),pago.getCheque_num()));
            
        }
        
        if(pago.getTransf_mnt()>0) {
            addText(cb, 170,lineStart - 39,  String.format("%.2f", pago.getTransf_mnt()));
            addText(cb, 300,lineStart - 39 , String.format("%s (Nº:%s)",pago.getCheque_ban(),pago.getCheque_num()));
        }
        addText(cb, 130,lineStart - 55, String.format("%.2f",total));
    }
    
    public static void main(String[] args){
        String fileName = "c:\\recibo.pdf";
        
        Recibo recibo;
//        
//        Pago pago = new TPago().getById(19);        
//        Cuenta cuenta = new TCuenta().getById(pago.getId_cuenta());
//        Contrato contrato = new TContrato().getById(cuenta.getId_contrato());
//        recibo = new Recibo();
//        recibo.setId_pago(pago.getId());
//        recibo.setId_cuenta(cuenta.getId());
//        recibo.setId_contrato(contrato.getId());
//        recibo.setFecha(TFecha.ahora(TFecha.formatoBD));
//        recibo.setNumero(666);
//        recibo.setFecha_creacion(TFecha.ahora(TFecha.formatoBD_Hora));
//        recibo.setId_cliente(cuenta.getId_cliente());
////        recibo.setId_tipo_cliente(cuenta.)

        
        recibo = new TRecibo().getById(7);
        
        ReciboPdf recibopdf = new ReciboPdf(recibo);
        fileName = "c:\\ReciboDocumento.pdf";
        recibopdf.createPdf(fileName);
        recibopdf.abrir(fileName);
    }
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import bd.Caja;
import bd.Cuenta_interna;
import bd.Cuenta_interna_detalle;
import bd.Parametro;
import bd.Recibo;
import bd.Recibo_detalle;
import bd.Usuario;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.pdf.PdfContentByte;
import transaccion.TCaja;
import transaccion.TCuenta_interna;
import transaccion.TCuenta_interna_detalle;
import transaccion.TParametro;
import transaccion.TRecibo;
import transaccion.TRecibo_detalle;
import transaccion.TUsuario;

/**
 *
 * @author Diego
 */
public class ReciboInternoPdf extends ReciboPdf {
    
    public ReciboInternoPdf(Recibo recibo){
        super(recibo);
    }
    
     //@Override
    protected void addContenta(Document document) {
       document.setMargins(10, 10, 10, 10);
//        Rectangle pageSize = document.getPageSize();        
       document.newPage();
       PdfContentByte cb = docWriter.getDirectContent();
       String bg_fileName = "";        
       Parametro image_url = new TParametro().getByCodigo(OptionsCfg.RECIBO_IMAGE);
       if (image_url!=null) bg_fileName = image_url.getValor();
       
       setBackground(cb,bg_fileName);       
       printDatos(cb);
       printHeader(cb);
       printTitle(cb);
      
       
       printTotalPago(cb,700);
       lineStart = 640;
       lineHeight = 18;
       
       Integer i = 0;
       for(Recibo_detalle rd :lstRecibo_detalle){
           addText(cb, 45, lineStart, TFecha.formatearFechaBdVista(rd.getFecha()));
           addText(cb, 105,lineStart, rd.getConcepto());
           addTextAlignedRight(cb, 460,lineStart, this.baseFontSize,String.format("%.2f",rd.getDebe()));
           addTextAlignedRight(cb, 505,lineStart, this.baseFontSize,String.format("%.2f",rd.getHaber()));
           addTextAlignedRight(cb, 555,lineStart, this.baseFontSize,String.format("%.2f",rd.getSaldo()));
           lineStart -= lineHeight;
           i++;
       }
       lineStart -= 20;       
       printFirma(cb,50,380);
    }
    
    @Override
    public void printDetalle(PdfContentByte cb){
       lineStart = 685;       
       
       Recibo_detalle detalle = lstRecibo_detalle.get(0);
       if (detalle!=null ){
            addTextAligned(cb, 45, lineStart, this.baseFontSize,String.format("En concepto de: %s",detalle.getConcepto()),Element.ALIGN_LEFT);
            String nombre_cuenta = getUsuarioCuenta(recibo.getId_cuenta());
            if(!"".equals(nombre_cuenta)){
                addTextAligned(cb, 45, lineStart - 15, this.baseFontSize,String.format("A cuenta: %s",nombre_cuenta),Element.ALIGN_LEFT);
            }
       }
    }
    @Override
    public void printTitle(PdfContentByte cb){
        this.addTextAligned(cb, 320,740, (int) Math.round(this.baseFontSize *1.25),"Movimiento de caja",Element.ALIGN_CENTER);
    }
    public void printDatos(PdfContentByte cb){
        super.printDatos(cb);
        String usuario = this.getUsuarioCaja(this.recibo.getId_caja());
        if(!"".equals(usuario))
        addText(cb, 470,790, String.format("Caja: ",""));        
        addText(cb, 525,790, usuario);
    }
    @Override 
     public void printFirma(PdfContentByte cb,Integer lineStart,Integer colStart){
        super.printFirma(cb, lineStart, colStart);
        addTextAligned(cb, colStart + 65, lineStart - 10, this.baseFontSize,"Recibí conforme",Element.ALIGN_CENTER);
        addTextAligned(cb, colStart + 65, lineStart - 20, this.baseFontSize,"Firma y aclaración",Element.ALIGN_CENTER);
        
    }
    
    @Override
    public void printHeader(PdfContentByte cb){
        lineStart = 715;
        addText(cb, 45, lineStart - lineHeight  * 0, String.format("Recibimos de: %s","INMOBILIARIA MEZI"));
    }
     @Override
     public void printTotalPago(PdfContentByte cb,Integer lineStart){
        Float total = 0f;
        Recibo_detalle detalle = lstRecibo_detalle.get(0);        
        if(detalle!=null) {
            addText(cb, 45,lineStart, String.format("La cantidad de: $%.2f",detalle.getHaber()));
        }
    }

     private String getUsuarioCuenta(Integer id_cuenta_interna){
         Cuenta_interna cuenta_interna = new TCuenta_interna().getById(id_cuenta_interna);         
         if(cuenta_interna!=null){                
                if (cuenta_interna==null) return "";                
                return cuenta_interna.getNombre();
         } else return "";
     }
     private String getUsuarioCaja(Integer id_caja){
         Caja caja = new TCaja().getById(id_caja);
         if (caja==null) return "" + id_caja;
         Usuario usuario = new TUsuario().getById(caja.getId_usuario());
         if(usuario==null) return "";
         return usuario.getUsu_mail();
     }
    public static void main(String[] args){
        String fileName;
        Recibo recibo;
        recibo = new TRecibo().getById(4);
        ReciboInternoPdf recibopdf = new ReciboInternoPdf(recibo);
        fileName = "c:\\ReciboInterno.pdf";
        recibopdf.createPdf(fileName);
        recibopdf.abrir(fileName);
    }
}

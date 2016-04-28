/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import bd.Cliente;
import bd.Contrato;
import bd.Cuenta;
import bd.Cuenta_detalle;
import bd.Pago;
import bd.Parametro;
import bd.Propiedad;
import bd.Recibo;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Image;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfContentByte;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.joda.time.Days;
import org.joda.time.LocalDate;
import transaccion.TCliente;
import transaccion.TContrato;
import transaccion.TCuenta;
import transaccion.TCuenta_detalle;
import transaccion.TParametro;
import transaccion.TPropiedad;
import transaccion.TRecibo;


/**
 *
 * @author Diego
 */
public class CuentaPdf extends BasePdf{   
    private Integer lineStart;
    private Integer lineHeight = 10;
    Pago pago;
    Cuenta cuenta;
    
    Contrato contrato;
    Cliente cliente;
    Propiedad propiedad;    
    String fecha_desde;
    String fecha_hasta;
    List<Cuenta_detalle> lstCuenta_detalle;
    
    public CuentaPdf(Cuenta cuenta){
        this.cuenta      = cuenta;
        HashMap<String,String> mapFiltro =  new HashMap<String,String>();
        mapFiltro.put("id_cuenta",cuenta.getId().toString());
        this.lstCuenta_detalle     = new TCuenta_detalle().getListFiltro(mapFiltro);
        asignarCampos();
    }
    
    public CuentaPdf(Cuenta cuenta,List<Cuenta_detalle> detalle){
        this.cuenta      = cuenta;
        this.lstCuenta_detalle = detalle;
        asignarCampos();
    }
    public CuentaPdf(Cuenta cuenta,String fecha_desde,String fecha_hasta){
        this.cuenta      = cuenta;
        this.fecha_desde = fecha_desde;
        this.fecha_hasta = fecha_hasta;
        asignarCampos();
        HashMap<String,String> mapFiltro =  new HashMap<String,String>();
        mapFiltro.put("id_cuenta",cuenta.getId().toString());
        this.lstCuenta_detalle     = new TCuenta_detalle().getListFiltro(mapFiltro);
        
        ArrayList<Cuenta_detalle> listaDetalle = new ArrayList();
        if (lstCuenta_detalle != null) {
            Float punitorio_porc = contrato.getPunitorio_monto() / 100;
            if(fecha_desde==null || fecha_desde.equals("")) fecha_desde = cuenta.getFecha_liquidacion();
            if(fecha_desde==null || fecha_desde.equals("")) fecha_desde = contrato.getFecha_inicio();

            LocalDate fecha_liquidacion = new LocalDate(fecha_desde);

            LocalDate fecha_hoy;
            if(fecha_hasta==null || fecha_hasta.equals("")) fecha_hoy = new LocalDate();
            else fecha_hoy = new LocalDate(fecha_hasta);
            
            for(Cuenta_detalle cd:lstCuenta_detalle) {
                LocalDate fecha = new LocalDate(cd.getFecha());
                if (fecha_desde!=null && (fecha.isBefore(fecha_liquidacion) ) ) continue;

                if  (fecha.isAfter(fecha_hoy)) continue;
                listaDetalle.add(cd);
                if (cd.getId_concepto()==OptionsCfg.CONCEPTO_ALQUILER || cd.getId_concepto()==OptionsCfg.CONCEPTO_DOCUMENTO){
                    int days = Days.daysBetween(fecha, fecha_hoy).getDays() - 1;
                    if (days >=contrato.getPunitorio_desde()){
                        Cuenta_detalle punitorio = new Cuenta_detalle();
                        punitorio.setFecha(cd.getFecha());
                        punitorio.setConcepto(String.format("Punitorio mes %d (%d dias)",cd.getId_referencia(),days));
                        punitorio.setId_concepto(OptionsCfg.CONCEPTO_PUNITORIO);
                        float monto_punitorio = days * punitorio_porc * cd.getDebe() ;
                        punitorio.setDebe(monto_punitorio);
                        listaDetalle.add(punitorio);                            
                    }
                }
            }                

        }
        this.lstCuenta_detalle = listaDetalle;
        asignarCampos();
    }
    private void asignarCampos(){
        this.contrato    = new TContrato().getById(cuenta.getId_contrato());
        this.cliente     = new TCliente().getById(cuenta.getId_cliente());
        this.propiedad         = new TPropiedad().getById(contrato.getId_propiedad());
    }
    @Override
    protected void addContent(Document document) {
        document.setMargins(10, 10, 10, 10);
        Rectangle pageSize = document.getPageSize();        
        document.newPage();
            
        PdfContentByte cb = docWriter.getDirectContent();
        String IMAGE = "";        
        Parametro image_url = new TParametro().getByCodigo(OptionsCfg.CUENTA_IMAGE);
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
                Logger.getLogger(CuentaPdf.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
       printHeader(cb);
      /***********************************/
       printFP(cb);

       lineStart = 654;
       lineHeight = 18;
       Integer i = 0;
       Float saldo = 0f;
       for(Cuenta_detalle cd :lstCuenta_detalle){
            addText(cb, 45, lineStart - lineHeight * i, TFecha.formatearFechaBdVista(cd.getFecha()));
            addText(cb, 105,lineStart - lineHeight * i, cd.getConcepto());
            saldo += cd.getDebe() - cd.getHaber();
            addTextAligned(cb, 470,lineStart - lineHeight * i, 7,String.format("%.2f",cd.getDebe()),Element.ALIGN_RIGHT);
            addTextAligned(cb, 515,lineStart - lineHeight * i, 7,String.format("%.2f",cd.getHaber()),Element.ALIGN_RIGHT);
            addTextAligned(cb, 555,lineStart - lineHeight * i, 7,String.format("%.2f",saldo),Element.ALIGN_RIGHT);
            i++;
       }
        
    }
    public void printHeader(PdfContentByte cb){       
//        createHeadings(cb, 450,790, "Nº Recibo:");        
//        createHeadings(cb, 450,780, "Fecha:");
        

        addText(cb, 515,800, TFecha.formatearFechaBdVista(TFecha.ahora()));
        lineStart = 740;
        addText(cb, 100,lineStart - lineHeight * 0, cliente.getApellido() + ", " + cliente.getNombre());
        addText(cb, 300,lineStart - lineHeight * 0, "Dirección: " + propiedad.getDireccion());
    }
    public void printFP(PdfContentByte cb){
        lineStart = 715;        
        Float total = 0f;
        if(fecha_desde!=null)
            addText(cb,  70,lineStart, TFecha.formatearFechaBdVista(fecha_desde));
        if(fecha_hasta!=null)
            addText(cb, 360,lineStart, TFecha.formatearFechaBdVista(fecha_hasta));

    }
    
    public static void main(String[] args){
        String fileName = "c:\\recibo.pdf";
        
        Cuenta cuenta;
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

        
        cuenta = new TCuenta().getById(87);
        
        CuentaPdf recibopdf = new CuentaPdf(cuenta,"2016-04-01","2016-04-30");
        fileName = "c:\\CuentaDetalle.pdf";
        recibopdf.createPdf(fileName);
        recibopdf.abrir(fileName);
    }
}

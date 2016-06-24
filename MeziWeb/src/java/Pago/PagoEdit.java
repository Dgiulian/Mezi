/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Pago;

import bd.Contrato;
import bd.Cuenta;
import bd.Cuenta_detalle;
import bd.Pago;
import bd.Recibo;
import bd.Recibo_detalle;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.joda.time.Days;
import org.joda.time.LocalDate;
import transaccion.TContrato;
import transaccion.TCuenta;
import transaccion.TCuenta_detalle;
import transaccion.TPago;
import transaccion.TRecibo;
import transaccion.TRecibo_detalle;
import utils.BaseException;
import utils.JsonRespuesta;
import utils.OptionsCfg;
import utils.Parser;
import utils.TFecha;

/**
 *
 * @author Diego
 */
public class PagoEdit extends HttpServlet {

    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet PagoEdit</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet PagoEdit at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        } finally {            
            out.close();
        }
    }

   
    /**
     * Handles the HTTP
     * <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP
     * <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        /*
         * Cargar el pago
         * Cargar los punitorios
         * Cargar el detalle de pago en la cuenta
         * Cargar el saldo si hubiera
         * Crear el recibo del inquilino
         * Cargar el detalle del recibo. Copiando el detalle de la cuenta con los punitorios, pagos y saldos.
        */
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String  fecha     = TFecha.convertirFecha(request.getParameter("fecha"), TFecha.formatoVista, TFecha.formatoBD);        
        
        Integer tipo      = Parser.parseInt(request.getParameter("tipo"));
        Integer id_cuenta = Parser.parseInt(request.getParameter("id_cuenta"));
        Float liqEfeMnt   = Parser.parseFloat(request.getParameter("liqEfeMnt"));
        Float liqChkMnt   = Parser.parseFloat(request.getParameter("liqChkMnt"));
        String liqChkBan  = request.getParameter("liqChkBan");
        String liqChkNum  = request.getParameter("liqChkNum");
        String liqChkVto  = TFecha.formatearFechaVistaBd(request.getParameter("liqChkVto"));
        Float liqTraMnt   = Parser.parseFloat(request.getParameter("liqTraMnt"));
        String liqTraNum  = request.getParameter("liqTraNum");
        
        
        JsonRespuesta jr    = new JsonRespuesta();
        TCuenta tc          = new TCuenta();
        TCuenta_detalle tcd = new TCuenta_detalle();
        TPago tp            = new TPago();
        Pago p;
        Cuenta cuenta;
        Cuenta_detalle cd ;
        Integer id_pago;
        TRecibo tr =  new TRecibo();
        TRecibo_detalle trd = new TRecibo_detalle();
        TContrato tcr = new TContrato();
        ArrayList<Cuenta_detalle> listaDetalle   = new ArrayList();
        ArrayList<Cuenta_detalle> listaPunitorio = new ArrayList();
        List<Cuenta_detalle> lista ;
        HashMap<String,String> filtroCuenta    = new HashMap<String,String>();
        Float saldo = 0f;
        try{            
            
            cuenta = tc.getById(id_cuenta);
            
            if(cuenta==null) throw new BaseException("ERROR","Debe indicar la cuenta a liquidar");
            filtroCuenta.put("id_cuenta", cuenta.getId().toString());
            p = new Pago();
            p.setId_cuenta(cuenta.getId());
            p.setFecha(fecha);
            p.setFecha_creacion(TFecha.ahora(TFecha.formatoBD_Hora));
            p.setEfectivo(liqEfeMnt);
            
            p.setCheque_mnt(liqChkMnt);
            p.setCheque_ban(liqChkBan);
            p.setCheque_num(liqChkNum);
            if(!"".equals(liqChkVto))
                p.setCheque_vto(liqChkVto);
            
            
            p.setTransf_mnt(liqTraMnt);
            p.setTransf_num(liqTraNum);
            id_pago = tp.alta(p);
            if(id_pago==0) throw new BaseException("ERROR","Ocurri&oacute; un error al guardar el pago. Intentelo nuevamente.");
            
            Recibo recibo = new Recibo();       
            recibo.setId_pago(id_pago);
            recibo.setId_cuenta(cuenta.getId());
            recibo.setId_contrato(cuenta.getId_contrato());
            recibo.setFecha(p.getFecha());
            recibo.setNumero(0);
            recibo.setId_tipo_recibo(cuenta.getId_tipo_cliente());
            recibo.setFecha_creacion(TFecha.ahora(TFecha.formatoBD_Hora));
            recibo.setId_cliente(cuenta.getId_cliente());            
            Integer id_recibo = tr.alta(recibo );   
            
            
            
            lista = tcd.setOrderBy("fecha").getListFiltro(filtroCuenta);
            
             if (lista == null) throw new BaseException("ERROR","Ocurri&oacute; un error al editar la cuenta");
            Contrato contrato = tcr.getById(cuenta.getId_contrato());
            Float punitorio_porc = contrato.getPunitorio_monto() / 100;
            String ult_liquidacion;
            if(cuenta.getFecha_liquidacion()==null || cuenta.getFecha_liquidacion().equals(""))
                ult_liquidacion = contrato.getFecha_inicio();
            else  ult_liquidacion  = cuenta.getFecha_liquidacion();
            /* Si es la primer liquidación, tomamos como ultima liquidacion, el inicio del contrato */
            
            LocalDate fecha_liquidacion = new LocalDate(ult_liquidacion);
            LocalDate fecha_conslta;
            if(fecha==null || fecha.equals("")) fecha_conslta = new LocalDate();
            else fecha_conslta = new LocalDate(fecha);
            
            for(Cuenta_detalle cuenta_detalle:lista) { // Calculo los punitorios
                LocalDate fecha_det = new LocalDate(cuenta_detalle.getFecha());
                if (ult_liquidacion!=null && fecha_det.isBefore(fecha_liquidacion) ) continue;
                if  (fecha_det.isAfter(fecha_conslta)) continue;
                if (cuenta.getId_tipo_cliente()==OptionsCfg.CLIENTE_TIPO_PROPIETARIO) {
                    saldo += cuenta_detalle.getHaber() - cuenta_detalle.getDebe() ;
                } else{
                    saldo += cuenta_detalle.getDebe() - cuenta_detalle.getHaber();
                }
                
                if (cuenta.getId_tipo_cliente()==OptionsCfg.CLIENTE_TIPO_PROPIETARIO) continue;
                
                if (cuenta_detalle.getId_concepto()==OptionsCfg.CONCEPTO_ALQUILER|| cuenta_detalle.getId_concepto()==OptionsCfg.CONCEPTO_DOCUMENTO){
                    int days = Days.daysBetween(fecha_det, fecha_conslta).getDays() - 1;
                    if (days >=contrato.getPunitorio_desde()){
                        float monto_punitorio = days * punitorio_porc * cuenta_detalle.getDebe() ;
                        saldo += monto_punitorio;
                        Cuenta_detalle punitorio = new Cuenta_detalle();
                        punitorio.setFecha(cuenta_detalle.getFecha());
                        punitorio.setConcepto(String.format("Punitorio mes %d (%d dias)",cuenta_detalle.getId_referencia(),days));
                        punitorio.setId_concepto(OptionsCfg.CONCEPTO_PUNITORIO);
                        punitorio.setDebe(monto_punitorio);
                        listaPunitorio.add(punitorio);
                    }
                }
            }
            
            Float total = liqEfeMnt + liqChkMnt + liqTraMnt;
            
            cd = new Cuenta_detalle();
            cd.setId_cuenta(cuenta.getId());
            cd.setFecha(fecha);
            cd.setFecha_creacion(TFecha.ahora(TFecha.formatoBD));
            cd.setConcepto(String.format("Pago Recibo Nº %d Fecha: %s",recibo.getNumero(),request.getParameter("fecha")));
            cd.setId_concepto(OptionsCfg.CONCEPTO_PAGO);
            
            cd.setId_referencia(id_pago);
            if (cuenta.getId_tipo_cliente()==OptionsCfg.CLIENTE_TIPO_PROPIETARIO) {
                cd.setDebe(total);
            }
            else {
                cd.setHaber(total);
            }
            
            saldo = saldo - total;
            listaPunitorio.add(cd);
            if(saldo!=0){ // Si existe un saldo. Lo cargamos en la cuenta.
                Cuenta_detalle det_saldo = new Cuenta_detalle();
                det_saldo.setId_cuenta(id_cuenta);                
                det_saldo.setFecha(cd.getFecha());
                det_saldo.setFecha_creacion(cd.getFecha_creacion());
                det_saldo.setId_referencia(id_pago);
                det_saldo.setId_concepto(OptionsCfg.CONCEPTO_SALDO);
                det_saldo.setConcepto("Saldo");
                if(saldo>0) det_saldo.setDebe(Math.abs(saldo));
                else det_saldo.setHaber(Math.abs(saldo));
                listaPunitorio.add(det_saldo);
            }
            cuenta.setFecha_liquidacion(fecha);
            tc.actualizar(cuenta);
            for(Cuenta_detalle det_pago:listaPunitorio){
                det_pago.setId_cuenta(id_cuenta);
                det_pago.setFecha_creacion(TFecha.ahora());
                int id = tcd.alta(det_pago);
            }

            lista = tcd.setOrderBy("fecha,id_concepto").getListFiltro(filtroCuenta);
            Float saldo_recibo = 0f;
            
            for(Cuenta_detalle cuenta_detalle:lista) { // Calculo los punitorios
                LocalDate fecha_det = new LocalDate(cuenta_detalle.getFecha());
                if (ult_liquidacion!=null && fecha_det.isBefore(fecha_liquidacion) ) continue;
                if  (fecha_det.isAfter(fecha_conslta)) continue;
                  Recibo_detalle rd = new Recibo_detalle();
                    rd.setId_recibo(id_recibo);
                    rd.setConcepto(cuenta_detalle.getConcepto());
                    rd.setFecha(cuenta_detalle.getFecha());
                    rd.setDebe(cuenta_detalle.getDebe());
                    rd.setHaber(cuenta_detalle.getHaber());
                    
                     if (cuenta.getId_tipo_cliente()==OptionsCfg.CLIENTE_TIPO_PROPIETARIO) {
                        saldo_recibo += cuenta_detalle.getHaber() - cuenta_detalle.getDebe() ;
                    } else{
                        saldo_recibo += cuenta_detalle.getDebe() - cuenta_detalle.getHaber();
                    }
                    if(cuenta_detalle.getId_concepto()==OptionsCfg.CONCEPTO_PAGO) saldo_recibo = 0f;                    
                    rd.setSaldo(saldo_recibo);
                    
                    rd.setId_concepto(cuenta_detalle.getId_concepto());
                    trd.alta(rd);
            }
            if(id_pago!=0){
                jr.setResult("OK");
                recibo.setId(id_recibo);
                jr.setRecord(recibo);
            } else {
                throw new BaseException("ERROR","Ocurr&oacute; un error al aplicar el pago");
            }
        } catch (BaseException ex) {
            jr.setResult(ex.getResult());
            jr.setMessage(ex.getMessage());
            
        } finally {
            String jsonResult = new Gson().toJson(jr);
            out.print(jsonResult);
            out.close();
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}

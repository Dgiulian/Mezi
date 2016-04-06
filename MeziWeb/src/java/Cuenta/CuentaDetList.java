/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Cuenta;

import bd.Contrato;
import bd.Cuenta;
import bd.Cuenta_detalle;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.joda.time.Days;
import org.joda.time.LocalDate;
import transaccion.TContrato;
import transaccion.TCuenta;
import transaccion.TCuenta_detalle;
import transaccion.TInquilino;
import transaccion.TPropietario;
import utils.BaseException;
import utils.JsonRespuesta;
import utils.OptionsCfg;
import utils.Parser;

/**
 *
 * @author Diego
 */
public class CuentaDetList extends HttpServlet {

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
      
      response.setContentType("application/json;charset=UTF-8");
      PrintWriter out = response.getWriter();
      Integer id_cliente   = Parser.parseInt(request.getParameter("id_cliente"));
      Integer id_contrato  = Parser.parseInt(request.getParameter("id_contrato"));
      Integer id_propiedad = Parser.parseInt(request.getParameter("id_propiedad"));
      Integer id_tipo      = Parser.parseInt(request.getParameter("id_tipo"));
      Integer page         = Parser.parseInt(request.getParameter("pagNro"));
      
      JsonRespuesta jr = new JsonRespuesta();
        try {            
            TCuenta tc = new TCuenta();
            TContrato tcr = new TContrato();
            TCuenta_detalle tcd = new TCuenta_detalle();
            HashMap<String,String> filtroCuenta = new HashMap<String,String>();
            if(id_tipo==0) throw new BaseException("ERROR","Indique el tipo de cuenta que desea listar");
            
            if(id_cliente == 0 && id_contrato ==0) throw new BaseException("ERROR","Debe seleccionar alg&uacute;n criterio para la busqueda de cuenta corriente");
            Cuenta cuenta = null;            
            cuenta = tc.getBydClienteContrato(id_cliente,id_contrato,id_tipo);
            if(cuenta==null) throw new BaseException("ERROR","No se encontr&oacute; la cuenta corriente");
            filtroCuenta.put("id_cuenta", cuenta.getId().toString());
            List<Cuenta_detalle> lista = tcd.setOrderBy("fecha").getListFiltro(filtroCuenta);
            
            if (lista != null) {
                Contrato contrato = tcr.getById(cuenta.getId_contrato());
                Float punitorio_porc = contrato.getPunitorio_monto();
                
                String ult_liquidacion      = cuenta.getFecha_liquidacion();
                LocalDate fecha_liquidacion = new LocalDate(ult_liquidacion);
                LocalDate fecha_hoy         = new LocalDate();
                ArrayList listaDetalle      = new ArrayList();
                for(Cuenta_detalle cd:lista) {
                    LocalDate fecha = new LocalDate(cd.getFecha());
                    if (ult_liquidacion!=null && fecha.isBefore(fecha_liquidacion) ) continue;
                    
                    if  (fecha.isAfter(fecha_hoy)) continue;
                    listaDetalle.add(cd);
                    if (cd.getId_concepto()==OptionsCfg.CONCEPTO_ALQUILER){
                        int days = Days.daysBetween(fecha, fecha_hoy).getDays();
                        if (days >=10){
                            Cuenta_detalle punitorio = new Cuenta_detalle();
                            punitorio.setFecha(cd.getFecha());
                            punitorio.setConcepto(String.format("Punitorio mes %d",cd.getId_referencia()));
                            punitorio.setId_concepto(OptionsCfg.CONCEPTO_PUNITORIO);
                            float monto_punitorio = days * punitorio_porc / 100;
                            punitorio.setDebe(monto_punitorio);
                            listaDetalle.add(punitorio);                            
                        }
                    }
                }
                jr.setResult("OK");
                jr.setTotalRecordCount(listaDetalle.size());
                jr.setRecords(listaDetalle);
                jr.setRecord(cuenta);
            } else {
                jr.setResult("ERROR");
                jr.setTotalRecordCount(0);
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
        processRequest(request, response);
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

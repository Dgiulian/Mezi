/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package CuentaDetalle;

import bd.Contrato;
import bd.Cuenta;
import bd.Cuenta_detalle;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.joda.time.LocalDate;
import transaccion.TContrato;
import transaccion.TCuenta;
import transaccion.TCuenta_detalle;
import utils.BaseException;
import utils.JsonRespuesta;
import utils.OptionsCfg;
import utils.Parser;
import utils.TFecha;

/**
 *
 * @author Diego
 */
public class CuentaDetEdit extends HttpServlet {

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
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String  fecha     = TFecha.convertirFecha(request.getParameter("fecha"), TFecha.formatoVista, TFecha.formatoBD);
        String  concepto  = request.getParameter("concepto");
        Float   monto     = Parser.parseFloat(request.getParameter("monto"));
        Integer tipo      = Parser.parseInt(request.getParameter("tipo"));
        Integer id_cuenta = Parser.parseInt(request.getParameter("id_cuenta"));
        JsonRespuesta jr  = new JsonRespuesta();
        TCuenta tc = new TCuenta();
        TCuenta_detalle tcd = new TCuenta_detalle();
        try{
            
            
            Cuenta cuenta = tc.getById(id_cuenta);
            
            
            if(cuenta==null) throw new BaseException("ERROR","Debe indicar la cuenta a ajustar");            
            /*
             * La fecha de ajuste no puede ser anterior a la ultima liquidación.
             * Si no existiera ninguna liquidación, la fecha no debe ser anterior a la fecha de inicio del contrato.
             *
             */
            String fecha_inicio = cuenta.getFecha_liquidacion();
            if(fecha_inicio==null || fecha_inicio.equals("")){
                Contrato contrato = new TContrato().getById(cuenta.getId_contrato());
                if(contrato!=null) fecha_inicio = contrato.getFecha_inicio();
            }
            
            
            if(new LocalDate(fecha).isBefore(new LocalDate(fecha_inicio))) throw new BaseException("ERROR","La fecha del ajuste debe ser posterior a la fecha actual");
            
            Cuenta_detalle cd = new Cuenta_detalle();
            cd.setId_cuenta(cuenta.getId());
            cd.setFecha(fecha);
            cd.setFecha_creacion(TFecha.ahora(TFecha.formatoBD));
            cd.setConcepto(concepto);
            cd.setId_concepto(OptionsCfg.CONCEPTO_AJUSTE);
            if(tipo==0) throw new BaseException("ERROR","Seleccione el tipo de ajuste");
            if(tipo==1) cd.setDebe(monto);
            else cd.setHaber(monto);
            int id = tcd.alta(cd);
            if(id!=0){
                jr.setResult("OK");
                jr.setRecord(cd);
            } else {
                throw new BaseException("ERROR","Ocurr&oacute; un error al cargar el ajuste");
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

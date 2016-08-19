/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Caja;

import bd.Caja;
import bd.Caja_detalle;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import transaccion.TAuditoria;
import transaccion.TCaja;
import transaccion.TCaja_detalle;
import utils.BaseException;
import utils.JsonRespuesta;
import utils.OptionsCfg;
import utils.Parser;
import utils.TFecha;

/**
 *
 * @author Diego
 */
public class CajaCierre extends HttpServlet {

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
            out.println("<title>Servlet CajaCerrar</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CajaCerrar at " + request.getContextPath() + "</h1>");
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
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter out = response.getWriter();
        JsonRespuesta jr = new JsonRespuesta();
        
        Integer id_caja = Parser.parseInt(request.getParameter("id_caja"));        
        Float saldo_efectivo       = Parser.parseFloat(request.getParameter("saldo_efectivo"));
        Float saldo_cheques        = Parser.parseFloat(request.getParameter("saldo_cheques"));
        Float saldo_transferencia  = Parser.parseFloat(request.getParameter("saldo_transferencia"));
        Float efectivo_cierre      = Parser.parseFloat(request.getParameter("efectivo_cierre"));
        Float cheque_cierre        = Parser.parseFloat(request.getParameter("cheque_cierre"));
        Float transferencia_cierre = Parser.parseFloat(request.getParameter("transferencia_cierre"));
        
        String motivo_diferencia = request.getParameter("motivo_diferencia");
        try {
            boolean nuevo = false;
            boolean todoOk;
            HttpSession sesion = request.getSession(false);
            Integer id_usuario = (Integer) sesion.getAttribute("id_usuario");
            Integer id_tipo_usuario_actual = (Integer) sesion.getAttribute("id_tipo_usuario");
            if (id_usuario ==null || id_usuario==0) throw new BaseException("ERROR","Su sesi&oacute;n expir&oacute;. Debe estar logueado para abrir una caja");                
           
            Caja caja;
            TCaja tc = new TCaja();
            caja = tc.getById(id_caja);
            if(caja==null)throw new BaseException("ERROR","No se encontr&oacute; la caja");
            if(!caja.getId_usuario().equals(id_usuario)) throw new BaseException("ERROR","No se puede cerrar la caja de otro usuario " + id_usuario.toString());
            
            caja.setId_estado(OptionsCfg.CAJA_CERRADA);
            caja.setEfectivo_cierre(efectivo_cierre);     
            caja.setCheque_cierre(cheque_cierre);
            caja.setMotivo_diferencia(motivo_diferencia);
            
            todoOk = tc.actualizar(caja);          
            if(todoOk){
                TAuditoria.guardar(id_usuario,id_tipo_usuario_actual,OptionsCfg.MODULO_CAJA,nuevo?OptionsCfg.ACCION_ALTA:OptionsCfg.ACCION_MODIFICAR,id_caja,tc.auditar(caja));
                TCaja_detalle tcaja_detalle = new TCaja_detalle();
                if(!saldo_efectivo.equals(efectivo_cierre)){
                    Caja_detalle detalle  = new Caja_detalle();
                    detalle.setImporte(efectivo_cierre - saldo_efectivo);
                    detalle.setId_caja(caja.getId());
                    detalle.setId_forma(OptionsCfg.FORMA_EFECTIVO);
                    detalle.setConcepto(motivo_diferencia);
                    tcaja_detalle.alta(detalle);
                    TAuditoria.guardar(id_usuario,id_tipo_usuario_actual,OptionsCfg.MODULO_CAJA_DETALLE,nuevo?OptionsCfg.ACCION_ALTA:OptionsCfg.ACCION_MODIFICAR,id_caja,tc.auditar(detalle));
                }
                if(!saldo_cheques.equals(cheque_cierre)){
                    Caja_detalle detalle  = new Caja_detalle();
                    detalle.setImporte(cheque_cierre - saldo_cheques);
                    detalle.setId_caja(caja.getId());
                    detalle.setId_forma(OptionsCfg.FORMA_CHEQUE);
                    detalle.setConcepto(motivo_diferencia);
                    tcaja_detalle.alta(detalle);
                    TAuditoria.guardar(id_usuario,id_tipo_usuario_actual,OptionsCfg.MODULO_CAJA_DETALLE,nuevo?OptionsCfg.ACCION_ALTA:OptionsCfg.ACCION_MODIFICAR,id_caja,tc.auditar(detalle));
                }
                if(!saldo_transferencia.equals(transferencia_cierre)){
                    Caja_detalle detalle  = new Caja_detalle();
                    detalle.setImporte(transferencia_cierre - saldo_transferencia);
                    detalle.setId_caja(caja.getId());
                    detalle.setId_forma(OptionsCfg.FORMA_TRANSFERENCIA);
                    detalle.setConcepto(motivo_diferencia);
                    tcaja_detalle.alta(detalle);
                    TAuditoria.guardar(id_usuario,id_tipo_usuario_actual,OptionsCfg.MODULO_CAJA_DETALLE,nuevo?OptionsCfg.ACCION_ALTA:OptionsCfg.ACCION_MODIFICAR,id_caja,tc.auditar(detalle));
                }
                jr.setResult("OK");
                jr.setRecord(caja);
            } else throw new BaseException("ERROR","Ocurri&oacute; un error al abrir la caja");
          
        } catch(BaseException ex){
            jr.setResult(ex.getResult());
            jr.setMessage(ex.getMessage());
        } finally {            
            out.print(new Gson().toJson(jr));
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

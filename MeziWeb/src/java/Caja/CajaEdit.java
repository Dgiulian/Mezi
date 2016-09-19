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
import javax.servlet.RequestDispatcher;
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
public class CajaEdit extends HttpServlet {

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
            out.println("<title>Servlet CajaEdit</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CajaEdit at " + request.getContextPath() + "</h1>");
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
        RequestDispatcher requestDispatcher = null;
        try{
            Integer id = Parser.parseInt(request.getParameter("id"));
        
            Caja caja = new TCaja().getById(id);
            if(caja ==null){
                throw new BaseException("ERROR","No se encontr&oacute; la caja");
            }
            request.setAttribute("caja", caja);
            requestDispatcher = request.getRequestDispatcher("caja_edit.jsp");
        } catch(BaseException ex){
            request.setAttribute("titulo",ex.getResult());
            request.setAttribute("mensaje", ex.getMessage());
            requestDispatcher = request.getRequestDispatcher("message.jsp");
            
        } finally{
            if(requestDispatcher!=null)
                requestDispatcher.forward(request, response);
        }
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
        
        Integer id = Parser.parseInt(request.getParameter("id"));
        String fecha = request.getParameter("fecha");
        Integer id_estado = Parser.parseInt(request.getParameter("id_estado"));
        Float efectivo_anterior = Parser.parseFloat(request.getParameter("efectivo_anterior"));
        Float cheque_anterior = Parser.parseFloat(request.getParameter("cheque_anterior"));
        Float transferencia_anterior = Parser.parseFloat(request.getParameter("transferencia_anterior"));
        
        try {
            boolean nuevo = false;
            boolean todoOk;
            HttpSession sesion = request.getSession(false);
            Integer id_usuario = (Integer) sesion.getAttribute("id_usuario");
            Integer id_tipo_usuario_actual = (Integer) sesion.getAttribute("id_tipo_usuario");
            if (id_usuario ==null) throw new BaseException("ERROR","Su sesi&oacute;n expir&oacute;. Debe estar logueado para abrir una caja");                
           
            Caja caja;
            TCaja tc = new TCaja();
            caja = tc.getById(id);
            // Buscar si existe una caja en la misma fecha
            
            if(caja==null){
                nuevo = true;
                caja = new Caja();
                caja.setId_estado(OptionsCfg.CAJA_ABIERTA);
                caja.setId_usuario(id_usuario);
            }
            caja.setFecha(TFecha.formatearFechaVistaBd(fecha));
            caja.setEfectivo_anterior(efectivo_anterior);
            caja.setCheque_anterior(cheque_anterior);
            caja.setTransferencia_anterior(transferencia_anterior);
            if(tc.buscarCaja(id_usuario,caja.getFecha().getFecha()) != null) throw new BaseException("ERROR","Existe una caja para esa fecha. No se puede abrir otra caja");
            if(tc.buscarCajaAbierta(id_usuario)!= null) throw new BaseException("ERROR","Existe una caja abierta. No se puede abrir otra caja");
            
            if(nuevo){
                id= tc.alta(caja);
                todoOk = id!=0;            
                caja.setId(id);
            } else {
                todoOk = tc.actualizar(caja);
            } 
            sesion.setAttribute("id_caja", caja.getId());
            if(todoOk){                
                TAuditoria.guardar(id_usuario,id_tipo_usuario_actual,OptionsCfg.MODULO_CAJA,nuevo?OptionsCfg.ACCION_ALTA:OptionsCfg.ACCION_MODIFICAR,id,tc.auditar(caja));
                if(efectivo_anterior!=0f){
                    Caja_detalle detalle = new Caja_detalle();
                    detalle.setId_caja(caja.getId());
                    detalle.setImporte(efectivo_anterior);
                    detalle.setId_forma(OptionsCfg.FORMA_EFECTIVO);
                    detalle.setId_tipo(OptionsCfg.TIPO_INGRESO);
                    detalle.setConcepto("Apertura caja del " + fecha);
                    Integer id_detalle = new TCaja_detalle().alta(detalle);
                    detalle.setId(id_detalle);
                }
//               if(cheque_anterior!=0f){
//                    Caja_detalle detalle = new Caja_detalle();
//                    detalle.setId_caja(caja.getId());
//                    detalle.setImporte(cheque_anterior);
//                    detalle.setId_forma(OptionsCfg.FORMA_CHEQUE);
//                    detalle.setConcepto("Apertura caja del " + fecha);
//                    Integer id_detalle = new TCaja_detalle().alta(detalle);
//                    detalle.setId(id_detalle);                    
//                }
//                if(transferencia_anterior!=0f){
//                    Caja_detalle detalle = new Caja_detalle();
//                    detalle.setId_caja(caja.getId());
//                    detalle.setImporte(transferencia_anterior);
//                    detalle.setId_forma(OptionsCfg.FORMA_TRANSFERENCIA);
//                    detalle.setConcepto("Apertura caja del " + fecha);
//                    Integer id_detalle = new TCaja_detalle().alta(detalle);
//                    detalle.setId(id_detalle);                    
//                }
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

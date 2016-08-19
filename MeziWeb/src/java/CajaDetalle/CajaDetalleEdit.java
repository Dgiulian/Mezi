/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package CajaDetalle;

import bd.Caja;
import bd.Caja_detalle;
import bd.Cuenta_interna;
import bd.Cuenta_interna_detalle;
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
import transaccion.TCuenta_interna;
import transaccion.TCuenta_interna_detalle;
import utils.BaseException;
import utils.JsonRespuesta;
import utils.OptionsCfg;
import utils.Parser;

/**
 *
 * @author Diego
 */
public class CajaDetalleEdit extends HttpServlet {

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
            out.println("<title>Servlet CajaDetalleEdit</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CajaDetalleEdit at " + request.getContextPath() + "</h1>");
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
        try{
            HttpSession sesion = request.getSession(false);
            Integer id_usuario = (Integer) sesion.getAttribute("id_usuario");
            Integer id_tipo_usuario_actual = (Integer) sesion.getAttribute("id_tipo_usuario");
            Integer id_caja = Parser.parseInt(request.getParameter("id_caja"));
            Integer id_tipo = Parser.parseInt(request.getParameter("id_tipo"));
            Integer id_forma = Parser.parseInt(request.getParameter("id_forma"));
            Float importe = Parser.parseFloat(request.getParameter("importe"));
            String concepto = request.getParameter("concepto");
            Integer id_cuenta = Parser.parseInt(request.getParameter("id_cuenta"));
            
            TCaja tc = new TCaja();
            TCaja_detalle tcd = new TCaja_detalle();
            Caja_detalle detalle = new Caja_detalle();
            Caja caja = tc.getById(id_caja);
            Cuenta_interna cuenta_interna = new TCuenta_interna().getById(id_cuenta);
            Cuenta_interna_detalle cuenta_interna_detalle = new Cuenta_interna_detalle();
            if(cuenta_interna==null); // Que se hace si no existe la cuenta?
            
            boolean nuevo = true;
            if(caja==null) throw new BaseException("ERROR","No se encontr&oacute; la caja");
            if(!caja.getId_usuario().equals(id_usuario)) throw new BaseException("ERROR","No se pueden realizar movimientos en la caja de otro usuario" + caja.getId_usuario().toString() + "/" + id_usuario.toString());
            if(!caja.getId_estado().equals(OptionsCfg.CAJA_ABIERTA)) throw new BaseException("ERROR","Caja cerrada, no se pueden realizar movimientos");
                    
            detalle.setId_caja(caja.getId());
            detalle.setId_tipo(id_tipo);
            detalle.setId_forma(id_forma);
            detalle.setImporte(importe);
            detalle.setConcepto(concepto);
            int id_detalle = tcd.alta(detalle);
            if(id_detalle==0) throw new BaseException("ERROR","Ocurri&oacute; un error al cargar el movimiento de caja");
            detalle.setId(id_detalle);                        
            TAuditoria.guardar(id_usuario,id_tipo_usuario_actual,OptionsCfg.MODULO_CAJA_DETALLE,nuevo?OptionsCfg.ACCION_ALTA:OptionsCfg.ACCION_MODIFICAR,id_caja,tc.auditar(detalle));
            if(cuenta_interna!=null){
                cuenta_interna_detalle.setId_cuenta(cuenta_interna.getId());
                cuenta_interna_detalle.setFecha(caja.getFecha());
                cuenta_interna_detalle.setConcepto(detalle.getConcepto());
                if (id_tipo==OptionsCfg.TIPO_INGRESO) cuenta_interna_detalle.setHaber(detalle.getImporte());
                else if (id_tipo==OptionsCfg.TIPO_EGRESO)cuenta_interna_detalle.setDebe(detalle.getImporte());
                new TCuenta_interna_detalle().alta(cuenta_interna_detalle);
                TAuditoria.guardar(id_usuario,id_tipo_usuario_actual,OptionsCfg.MODULO_CUENTA_INTERNA_DETALLE,OptionsCfg.ACCION_ALTA,id_caja,tc.auditar(cuenta_interna_detalle));
            }
            
            jr.setResult("OK");
            jr.setRecord(detalle);
        } catch(BaseException ex){
            jr.setResult(ex.getResult());
            jr.setMessage(ex.getMessage());
        } finally{
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

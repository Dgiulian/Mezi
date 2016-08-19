/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package CuentaInterna;

import bd.Cuenta_interna;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import transaccion.TAuditoria;
import transaccion.TCuenta_interna;
import transaccion.TCuenta_interna_detalle;
import utils.BaseException;
import utils.JsonRespuesta;
import utils.OptionsCfg;
import utils.Parser;
import utils.TFecha;

/**
 *
 * @author Diego
 */
public class CuentaInternaDel extends HttpServlet {

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
            out.println("<title>Servlet CuentaInternaDel</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CuentaInternaDel at " + request.getContextPath() + "</h1>");
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
        try {           
           Integer id = Parser.parseInt(request.getParameter("id"));
           Cuenta_interna cuenta = new TCuenta_interna().getById(id);            
           if (cuenta==null) throw new BaseException("ERROR","No existe el registro");
            TCuenta_interna tv  = new TCuenta_interna();
            TCuenta_interna_detalle tid = new TCuenta_interna_detalle();
            float saldo = tid.getSaldo(cuenta.getId(),TFecha.ahora(TFecha.formatoBD));
            if(saldo!=0) throw new BaseException("ERROR","El saldo de la cuenta no es 0. No se puede eliminar");
           //
           boolean baja = tv.baja(cuenta);
           if ( !baja)throw new BaseException("ERROR","Ocurrio un error al eliminar el registro");
               jr.setResult("OK");
                Integer id_usuario = 0;
                Integer id_tipo_usuario = 0;
                HttpSession session = request.getSession();
                id_usuario = (Integer) session.getAttribute("id_usuario");
                id_tipo_usuario = (Integer) session.getAttribute("id_tipo_usuario");
                TAuditoria.guardar(id_usuario,id_tipo_usuario,OptionsCfg.MODULO_CUENTA_INTERNA,OptionsCfg.ACCION_BAJA,cuenta.getId(),tv.auditar(cuenta));
        }  catch (BaseException ex) {
            jr.setResult(ex.getResult());
            jr.setMessage(ex.getMessage());            
        }
        finally {
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

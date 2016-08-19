/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Cuenta;

import bd.Caja;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import transaccion.TCaja;
import utils.Parser;
import utils.TFecha;

/**
 *
 * @author Diego
 */
public class CuentaServlet extends HttpServlet {

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
        Integer id_tipo_cliente = Parser.parseInt(request.getParameter("id_tipo_cliente"));
        request.setAttribute("id_tipo_cliente", id_tipo_cliente);
        HttpSession sesion = request.getSession(false);
        Integer id_usuario = (Integer) sesion.getAttribute("id_usuario");
        Integer id_tipo_usuario_actual = (Integer) sesion.getAttribute("id_tipo_usuario");
        Integer id_caja = (Integer) sesion.getAttribute("id_caja");
        TCaja tc  = new TCaja();
        Caja caja = tc.getById(id_caja);
        if(caja==null){
            tc.buscarCaja(id_usuario, TFecha.ahora(TFecha.formatoBD));
        }
        if(caja!=null){
            request.setAttribute("caja", caja);
        }
        request.getRequestDispatcher("cuenta.jsp").forward(request, response);
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

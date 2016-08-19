/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package CuentaInternaDetalle;

import bd.Cuenta_interna;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import transaccion.TCuenta_interna;
import utils.BaseException;
import utils.Parser;

/**
 *
 * @author Diego
 */
public class CuentaInternaDetallePath extends HttpServlet {

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
            out.println("<title>Servlet CuentaInternaDetallePath</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CuentaInternaDetallePath at " + request.getContextPath() + "</h1>");
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
        try {
            Integer id_cuenta = Parser.parseInt(request.getParameter("id_cuenta"));
            Cuenta_interna cuenta = new TCuenta_interna().getById(id_cuenta);
            if(cuenta==null) throw new BaseException("ERROR","No se encontr&oacute; la cuenta");
            request.setAttribute("cuenta", cuenta);
            request.getRequestDispatcher("cuenta_interna_detalle.jsp").forward(request, response);
            
        } catch(BaseException ex){
            request.setAttribute("titulo",ex.getResult());
            request.setAttribute("menssage",ex.getMessage());
            request.getRequestDispatcher("message.jsp").forward(request,response);
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

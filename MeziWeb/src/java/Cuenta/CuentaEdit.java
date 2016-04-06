/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Cuenta;

import bd.Cliente;
import bd.Contrato;
import bd.Cuenta;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import transaccion.TCliente;
import transaccion.TContrato;
import transaccion.TCuenta;
import utils.BaseException;
import utils.Parser;

/**
 *
 * @author Diego
 */
public class CuentaEdit extends HttpServlet {

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
            out.println("<title>Servlet CuentaEdit</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CuentaEdit at " + request.getContextPath() + "</h1>");
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
        Integer id = Parser.parseInt(request.getParameter("id"));
        Cuenta cuenta = new TCuenta().getById(id);
        try{
            if(cuenta!=null){
                request.setAttribute("cuenta", cuenta);
                Cliente cliente = new TCliente().getById(cuenta.getId_cliente());
                if(cliente!=null){
                    request.setAttribute("cliente",cliente);
                }
                Contrato contrato = new TContrato().getById(cuenta.getId_contrato());
                if(contrato!=null){
                    request.setAttribute("contrato",contrato);
                }
                request.getRequestDispatcher("cuenta_edit.jsp").forward(request, response);
            } else throw new BaseException("Error","No se encontr&oacute; la cuenta corriente");
        } catch(BaseException ex){
            
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

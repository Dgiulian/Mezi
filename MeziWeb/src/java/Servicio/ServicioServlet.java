/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Servicio;

import bd.Propiedad;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import transaccion.TPropiedad;
import utils.BaseException;
import utils.Parser;

/**
 *
 * @author Diego
 */
public class ServicioServlet extends HttpServlet {

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
        
        Integer id_propiedad = Parser.parseInt(request.getParameter("id_propiedad"));
        try{
            Propiedad p = new TPropiedad().getById(id_propiedad);
        
            if (p==null){
                throw new BaseException("ERROR","No se encontr&oacute; la propiedad");
            }
            request.setAttribute("propiedad", p);
            request.getRequestDispatcher("servicio.jsp").forward(request, response);
        } catch(BaseException ex){
            request.setAttribute("titulo",ex.getResult());
            request.setAttribute("mensaje", ex.getMessage());
            request.getRequestDispatcher("message.jsp").forward(request,response);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
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

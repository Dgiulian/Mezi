/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Contrato;

import bd.Contrato;

import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import transaccion.TAuditoria;
import transaccion.TContrato;
import utils.BaseException;
import utils.JsonRespuesta;
import utils.OptionsCfg;
import utils.Parser;

/**
 *
 * @author Diego
 */
public class ContratoDel extends HttpServlet {

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
            out.println("<title>Servlet ContratoDel</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ContratoDel at " + request.getContextPath() + "</h1>");
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
        PrintWriter         out = response.getWriter();
        JsonRespuesta       jr  = new JsonRespuesta();
        Integer id = Parser.parseInt(request.getParameter("id"));
        TContrato tc = new TContrato();
        try {
           Contrato contrato = tc.getById(id);
           if (contrato==null) throw new BaseException("ERROR","No existe el contrato");
           
           boolean todoOk = new TContrato().eliminar(contrato);
           if(!todoOk)throw new BaseException("ERROR","Ocurri&oacute; un error al eliminar el contrato");
           
           jr.setResult("OK");
           jr.setMessage("Contrato eliminado correctamente");
           
           HttpSession session = request.getSession();
           Integer id_usuario = (Integer) session.getAttribute("id_usuario");
           Integer id_tipo_usuario = (Integer) session.getAttribute("id_tipo_usuario");
           TAuditoria.guardar(id_usuario,id_tipo_usuario,OptionsCfg.MODULO_CONTRATO,OptionsCfg.ACCION_BAJA,contrato.getId(),tc.auditar(contrato));

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

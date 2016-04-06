/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Cliente;

import bd.Cliente;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import transaccion.TCliente;
import utils.JsonRespuesta;
import utils.Parser;

/**
 *
 * @author Diego
 */
public class ClienteSearch extends HttpServlet {

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
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter out = response.getWriter();
        Integer id = Parser.parseInt(request.getParameter("id"));
        String documento = request.getParameter("documento");
        try {
            JsonRespuesta jr = new JsonRespuesta();           

            TCliente tp = new TCliente();
            Cliente cliente = null;
            if(id!=0)
                cliente = tp.getById(id);
            else cliente = tp.getByDocumento(documento);
                
                
            if (cliente != null) {
                jr.setResult("OK");
                jr.setRecord(new ClienteDet(cliente));
            } else {
                jr.setResult("ERROR");
                jr.setTotalRecordCount(0);
            }            
            String jsonResult = new Gson().toJson(jr);

            out.print(jsonResult);
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
    
    private class ClienteDet extends Cliente{
       String tipo_persona = "";
       
       ClienteDet(Cliente cliente){
        super(cliente);
        switch (cliente.getId_tipo_persona()){
           case 1: tipo_persona = "Fisica"; break;
           case 2: tipo_persona = "Jur&iacute;dica"; break;
           default: tipo_persona = "";           
       }
      }
    }
}

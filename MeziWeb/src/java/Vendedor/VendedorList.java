/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Vendedor;

import Vendedor.VendedorList;
import bd.Vendedor;
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
import transaccion.TVendedor;
import utils.JsonRespuesta;
import utils.Parser;

/**
 *
 * @author Diego
 */
public class VendedorList extends HttpServlet {

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
        String pagNro = request.getParameter("pagNro");
        String codigo = request.getParameter("codigo");
        Integer page = 0;
        
        page = Parser.parseInt(pagNro);
        try {
            JsonRespuesta jr = new JsonRespuesta();           

//            List<Vendedor> lista ;

            TVendedor tp = new TVendedor();
            HashMap<String,String> mapFiltro = new HashMap<String,String> ();
//            lista =  tp.getListFiltro(mapFiltro);
//           lista = tp.getList();
            List lista = new ArrayList<VendedorDet>();
            for(Vendedor c:tp.getList()){
                lista.add(new VendedorDet(c));
            }
            if (lista != null) {
                jr.setResult("OK");
                jr.setTotalRecordCount(lista.size());
                jr.setRecords(lista);
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
    
    private class VendedorDet extends Vendedor{
        VendedorDet(Vendedor v){
            super(v);
        }
    }
}

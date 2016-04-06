/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Localidad;

import bd.Localidad;
import bd.Provincia;
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
import transaccion.TLocalidad;
import transaccion.TProvincia;
import utils.JsonRespuesta;
import utils.Parser;

/**
 *
 * @author Diego
 */
public class LocalidadList extends HttpServlet {
    HashMap<Integer,Provincia> mapProvincias;
    
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
        mapProvincias = new TProvincia().getMap();
        
        Integer page = (pagNro!=null)?Integer.parseInt(pagNro):0;
         
        try {
            JsonRespuesta jr = new JsonRespuesta();           
            Integer idProv = Parser.parseInt(request.getParameter("idProv"));
            HashMap mapFiltro = new HashMap<String,String>();
            if(idProv!=0) mapFiltro.put("id_provincia", idProv.toString());
            List<Localidad> lista = new TLocalidad().getListFiltro(mapFiltro);
            ArrayList listaDet = new ArrayList();
            if (lista != null) {
                 for (Localidad localidad:lista){
                    listaDet.add(new LocalidadDet(localidad));
                }
                jr.setTotalRecordCount(listaDet.size());
            } else {
                jr.setTotalRecordCount(0);
            }            
            jr.setResult("OK");
            jr.setRecords(listaDet);
            
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
    
    private class LocalidadDet extends Localidad{
        String provincia;
        LocalidadDet(Localidad localidad){
            super(localidad);
            Provincia p = mapProvincias.get(localidad.getId_provincia());
            provincia = (p!=null)?p.getDescripcion():"";
        }
    }
            
}

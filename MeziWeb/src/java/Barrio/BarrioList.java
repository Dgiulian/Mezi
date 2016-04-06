/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Barrio;

import bd.Barrio;
import bd.Localidad;
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
import transaccion.TBarrio;
import transaccion.TLocalidad;
import utils.JsonRespuesta;
import utils.Parser;

/**
 *
 * @author Diego
 */
public class BarrioList extends HttpServlet {
    HashMap<Integer, Localidad> mapLocalidades;
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
        Integer id_localidad = Parser.parseInt(request.getParameter("id_localidad"));
        Integer page = 0;
        
        mapLocalidades = new TLocalidad().getMap();
        
        page = Parser.parseInt(pagNro);
        try {
            JsonRespuesta jr = new JsonRespuesta();           

            List<Barrio> lista ;

            TBarrio tp = new TBarrio();
            HashMap<String,String> mapFiltro = new HashMap<String,String> ();
            if(id_localidad != 0) mapFiltro.put("id_localidad",id_localidad.toString());
            lista =  tp.getListFiltro(mapFiltro);
           //lista = tp.getList();
            ArrayList<BarrioDet> listaDet = new ArrayList();
            for(Barrio c:lista){
                listaDet.add(new BarrioDet(c));
            } 
            if (lista != null) {
                jr.setTotalRecordCount(lista.size());
                jr.setResult("OK");
                jr.setRecords(listaDet);
            } else {                
                jr.setTotalRecordCount(0);
                jr.setResult("ERROR");
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
    
    private class BarrioDet extends Barrio {
        private String localidad = "";
       BarrioDet (Barrio barrio){
           super(barrio);
           Localidad l = mapLocalidades.get(barrio.getId_localidad());
           localidad = l!=null? l.getDescripcion():barrio.getId_localidad().toString();
       }
        
    }
}

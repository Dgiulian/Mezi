/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Servicio;

import bd.Servicio;
import bd.Propiedad;
import bd.Tipo_servicio;
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
import transaccion.TServicio;
import transaccion.TPropiedad;
import transaccion.TTipo_servicio;
import utils.BaseException;
import utils.JsonRespuesta;
import utils.Parser;

/**
 *
 * @author Diego
 */
public class ServicioList extends HttpServlet {
    HashMap<Integer, Tipo_servicio> lstTipo;
    HashMap<Integer, String> lstEnvio;
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
        JsonRespuesta jr = new JsonRespuesta();
        String pagNro = request.getParameter("pagNro");
        Integer page = 0;
        Integer id_propiedad = Parser.parseInt(request.getParameter("id_propiedad"));
        try{
            Propiedad p = new TPropiedad().getById(id_propiedad);
        
            if (p==null){
                throw new BaseException("ERROR","No se encontr&oacute; la propiedad");
            }
            lstEnvio = new HashMap();
            lstEnvio.put(1,"Propietario");
            lstEnvio.put(2,"Oficina");
            lstEnvio.put(3,"Inquilino");
            page = (pagNro!=null)?Parser.parseInt(pagNro):0;             
        
            
//            List<Servicio> lista ;

            TServicio tp = new TServicio();
            lstTipo = new TTipo_servicio().getMap();
            HashMap<String,String> mapFiltro = new HashMap<String,String> ();
            mapFiltro.put("id_propiedad",id_propiedad.toString());
            List<Servicio> listaBase =  tp.getListFiltro(mapFiltro);
//           lista = tp.getList();
            List lista = new ArrayList<ServicioList.ServicioDet>();
            for(Servicio c:listaBase){
                lista.add(new ServicioList.ServicioDet(c));
            }
            if (listaBase != null) {
                jr.setTotalRecordCount(lista.size());
            } else {
                jr.setTotalRecordCount(0);
            }            
            jr.setResult("OK");
            jr.setRecords(lista);            
        } catch(BaseException ex){
            jr.setResult(ex.getResult());
            jr.setMessage(ex.getMessage());
        } finally {   
            String jsonResult = new Gson().toJson(jr);
            out.print(jsonResult);
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
    
     private class ServicioDet extends Servicio{
       String tipo_servicio = "";
       String envio = "";
       
      public ServicioDet(Servicio servicio){
        
        super(servicio);
        Tipo_servicio t = lstTipo.get(servicio.getId_tipo_servicio());
        tipo_servicio = (t!=null)?t.getNombre():servicio.getId_tipo_servicio().toString();
        
        String e = lstEnvio.get(servicio.getId_envio());
        if(e!=null) envio = e;
      }
    }
     
}

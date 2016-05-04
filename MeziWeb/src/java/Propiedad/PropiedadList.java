/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Propiedad;

import bd.Barrio;
import com.google.gson.Gson;
import bd.Propiedad;
import bd.Propietario;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import transaccion.TBarrio;
import transaccion.TPropiedad;
import transaccion.TPropietario;
import utils.JsonRespuesta;
import utils.OptionsCfg;
import utils.OptionsCfg.Option;
import utils.Parser;

/**
 *
 * @author Diego
 */
public class PropiedadList extends HttpServlet {
    private Map<Integer,Barrio> mapBarrios;
    private Map<Integer,Propietario> mapPropietarios;
    private Map<Integer,Option> mapEstados;
    private String[] arrTipo_propiedad  = {"","Casa","Departamento","Terreno","Local comercial"};
    
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
        Integer pagNro = Parser.parseInt(request.getParameter("pagNro"));
        Integer id = Parser.parseInt(request.getParameter("id"));
        Integer id_tipo_inmueble = Parser.parseInt(request.getParameter("id_tipo_inmueble"));
        Integer id_estado = Parser.parseInt(request.getParameter("id_estado"));
        Integer id_operacion = Parser.parseInt(request.getParameter("id_operacion"));
        Integer id_propietario = Parser.parseInt(request.getParameter("id_propietario"));
        Integer page = 0;
        
        try {
            JsonRespuesta jr = new JsonRespuesta();           
            mapBarrios = new TBarrio().getMap();
            mapPropietarios = new TPropietario().getMap();
            mapEstados = OptionsCfg.getMap( OptionsCfg.getEstadosPropiedad());
            List<Propiedad> lista ;
            
            TPropiedad tp = new TPropiedad();
            HashMap<String,String> mapFiltro = new HashMap<String,String> ();
            if(id!=0){
                mapFiltro.put("id",id.toString());
            }
            if(id_tipo_inmueble!=0){
                mapFiltro.put("id_tipo_inmueble",id_tipo_inmueble.toString());
            }
            if(id_estado!=0){
                mapFiltro.put("id_estado",id_estado.toString());
            }
            if(id_operacion!=0){
                mapFiltro.put("id_operacion",id_operacion.toString());
            }
            if(id_propietario!=0){
                mapFiltro.put("id_propietario",id_propietario.toString());
            }
            lista =  tp.getListFiltro(mapFiltro);
//            lista = tp.getList();
            ArrayList<PropiedadDet> listaDet = new ArrayList();
            for(Propiedad p:lista){
                listaDet.add(new PropiedadDet(p));
            }
            if (lista != null) {
                jr.setTotalRecordCount(listaDet.size());
                jr.setRecords(listaDet);
            } else {
                jr.setTotalRecordCount(0);
            }            
            jr.setResult("OK");
            
            
            String jsonResult = new Gson().toJson(jr);

            out.print(jsonResult);
        } finally {            
            out.close();
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
    
    private class PropiedadDet extends Propiedad {
        String barrio      = "";
        String tipo_inmueble        = "";
        String propietario = "";
        String estado      = "";
        String operacion      = "";
        PropiedadDet(Propiedad p){
            super(p);
            Barrio b = mapBarrios.get(p.getId_barrio());
            Propietario prop = mapPropietarios.get(p.getId_propietario());
            Option o = mapEstados.get(p.getId_estado());
            barrio = (b!=null)? b.getNombre():p.getId_barrio().toString();
            propietario = (prop!=null)? prop.getNombre() + ", " + prop.getApellido():p.getId_propietario().toString();
            estado = (o!=null)? o.getDescripcion():p.getId_estado().toString();
           
            switch(p.getId_operacion()){
                case 1: operacion = "Alquiler";break;
                case 2: operacion = "Venta";break;
                default: operacion = p.getId_operacion().toString();
            }
            try{
                tipo_inmueble = arrTipo_propiedad[p.getId_tipo_inmueble()];
            } catch(Exception ex){
                tipo_inmueble = p.getId_tipo_inmueble().toString();
            }
        }
    }
}

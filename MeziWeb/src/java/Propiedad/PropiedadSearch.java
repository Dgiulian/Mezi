/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Propiedad;

import bd.Barrio;
import bd.Localidad;
import bd.Propiedad;
import bd.Propietario;
import com.google.gson.Gson;
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
import transaccion.TLocalidad;
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
public class PropiedadSearch extends HttpServlet {
    private HashMap<Integer, Barrio> mapBarrios;
    private Map<Integer,Option> mapEstados;
    private HashMap<Integer, Localidad> mapLocalidad;
    private String[] arrTipo_propiedad  = {"","Casa","Departamento","Terreno"};
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

        try {
            JsonRespuesta jr = new JsonRespuesta();           
            mapBarrios = new TBarrio().getMap();
            mapEstados = OptionsCfg.getMap( OptionsCfg.getEstadosPropiedad());            
            mapLocalidad = new TLocalidad().getMap();
            
            TPropiedad tp = new TPropiedad();
            Propiedad propiedad = null;
            if(id!=0){
                propiedad = tp.getById(id);
            }
            
            if (propiedad != null) {
                jr.setTotalRecordCount(1);
                jr.setRecord(new PropiedadDet(propiedad));
            } else {
                jr.setMessage("ERROR");
                jr.setTotalRecordCount(0);
            }            
            jr.setResult("OK");
            
            
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
    
    private class PropiedadDet extends Propiedad {
        String  barrio      = "";
        String  tipo_inmueble        = "";
        String  estado      = "";
        String  operacion   = "";
        String  localidad   = "";
        String  direccion   = "";
        //Integer carpeta     = 0;
        Propietario  propietario;
        
        PropiedadDet(Propiedad p){
            super(p);
            Barrio b = mapBarrios.get(p.getId_barrio());
            Localidad l = mapLocalidad.get(p.getId_localidad());
            Option o = mapEstados.get(p.getId_estado());
            barrio = (b!=null)? b.getNombre():p.getId_barrio().toString();
            localidad = (l!=null)? l.getDescripcion():p.getId_localidad().toString();    
            estado = (o!=null)? o.getDescripcion():p.getId_estado().toString();
            direccion = p.getDireccion();
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
            Propietario prop = new TPropietario().getById(p.getId_propietario());
            if(prop!=null) {
                propietario = prop;                
            }
        }
    }
}

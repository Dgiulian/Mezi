/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Reporte;

import bd.Barrio;
import bd.Contrato;
import bd.Inquilino;
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
import transaccion.TContrato;
import transaccion.TInquilino;
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
public class ReportePropietariosList extends HttpServlet {
    private Map<Integer,Barrio> mapBarrios;
    private Map<Integer,Propietario> mapPropietarios;
    private Map<Integer,Option> mapEstados;
    private String[] arrTipo_propiedad  = {"","Casa","Departamento","Terreno","Local comercial","Chacra","Galp&oacute;n"};
    
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
        Integer numResults = Parser.parseInt(request.getParameter("numResults"));
        Integer id = Parser.parseInt(request.getParameter("id"));
        Integer id_tipo_inmueble = Parser.parseInt(request.getParameter("id_tipo_inmueble"));
        Integer id_estado = Parser.parseInt(request.getParameter("id_estado"));
        Integer id_estado_contrato = Parser.parseInt(request.getParameter("id_estado_contrato"));
        Integer id_operacion = Parser.parseInt(request.getParameter("id_operacion"));
        Integer id_propietario = Parser.parseInt(request.getParameter("id_propietario"));
        String calle  = request.getParameter("calle");
        String numero = request.getParameter("numero");
      
        Integer carpeta = Parser.parseInt(request.getParameter("carpeta"));
        String nombre = request.getParameter("nombre");
        String apellido = request.getParameter("apellido");
        String dni = request.getParameter("dni");
        Integer page = 0;
        JsonRespuesta jr = new JsonRespuesta();           
        
        List<Propiedad> lista ;
        TPropiedad tp = new TPropiedad();
        HashMap<String,String> filtroPropiedad = new HashMap<String,String> ();
        HashMap<String,String> filtroPropietario = new HashMap<String,String> ();
        try {
               
            if(nombre!=null && !nombre.equals("")){
                filtroPropietario.put("nombre",nombre);
            }
            if(apellido!=null && !apellido.equals("")){
                filtroPropietario.put("apellido",apellido);
            }
            if(dni!=null && !dni.equals("")){
                filtroPropietario.put("dni",dni);
            }
            if (carpeta!=0){
                filtroPropietario.put("carpeta",carpeta.toString());
            }            
            List<Propietario> lstPropietarios = new TPropietario().getListFiltro(filtroPropietario);            
            //System.out.println(lstPropietarios);
            mapBarrios = new TBarrio().getMap();
            mapEstados = OptionsCfg.getMap( OptionsCfg.getEstadosPropiedad());
            mapPropietarios = new TPropietario().getMap(lstPropietarios);
            
            tp.setOrderBy(" id_propietario ");
            if(numResults>0) tp.setNumResults(numResults);
            
            if(id!=0){
                filtroPropiedad.put("id",id.toString());
            }
            if(id_tipo_inmueble!=0){
                filtroPropiedad.put("id_tipo_inmueble",id_tipo_inmueble.toString());
            }
            if(id_estado!=0){
                filtroPropiedad.put("id_estado",id_estado.toString());
            }
            if(id_operacion!=0){
                filtroPropiedad.put("id_operacion",id_operacion.toString());
            }
            if(id_propietario!=0){
                filtroPropiedad.put("id_propietario",id_propietario.toString());
            }
           
            if(calle!=null && !"".equals(calle)) filtroPropiedad.put("calle",calle);
            if(numero!=null && !"".equals(numero)) filtroPropiedad.put("numero",numero);
            
            ArrayList<PropiedadDet> listaDet = new ArrayList();
            for(Propietario p:lstPropietarios){
                filtroPropiedad.put("id_propietario", p.getId().toString());
                lista =  tp.getListFiltro(filtroPropiedad,pagNro);
            
//            lista = tp.getList();            
                for(Propiedad propiedad:lista){
                    PropiedadDet det = new PropiedadDet(propiedad);
                    if(id_estado_contrato != 0) {
                        if (det.contrato != null && det.contrato.id_estado.equals(id_estado_contrato)) {
                            listaDet.add(det);
                        }
                    } else {
                        listaDet.add(det);
                    }
                }
            }
            
            if (listaDet.size() > 0) {
                jr.setTotalRecordCount(listaDet.size());
                jr.setRecordCount(listaDet.size());
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
        String barrio        = "";
        String tipo_inmueble = "";
        Integer carpeta      = 0;
        String propietario   = "";
        String estado        = "";
        String operacion     = "";
        Inquilino inquilino;
        Contrato  contrato;
        String estado_contrato = "";
        PropiedadDet(Propiedad p){
            super(p);
            Barrio b = mapBarrios.get(p.getId_barrio());
            Propietario prop = mapPropietarios.get(p.getId_propietario());
            Option o = mapEstados.get(p.getId_estado());
            barrio = (b!=null)? b.getNombre():p.getId_barrio().toString();
            if(prop!=null) {
                propietario = prop.getNombre() + ", " + prop.getApellido();
                carpeta     = prop.getCarpeta();
            } else {
                propietario = p.getId_propietario().toString();
            }
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
            if(p.getId_estado().equals(OptionsCfg.PROPIEDAD_ALQUILADA)){
                contrato = new TContrato().getById_propiedad(p.getId());
                if(contrato != null){
                    HashMap<Integer, Option> mapEstadosContrato = OptionsCfg.getMap(OptionsCfg.getEstadosContrato());
                    estado_contrato = mapEstadosContrato.get(contrato.getId_estado()).getDescripcion();
                    inquilino = new TInquilino().getById(contrato.getId_inquilino());
                }
            }
        }
    }
}

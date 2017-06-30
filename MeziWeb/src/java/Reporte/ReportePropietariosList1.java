/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Reporte;

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
public class ReportePropietariosList1 extends HttpServlet {
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
        Integer id_operacion = Parser.parseInt(request.getParameter("id_operacion"));
        Integer id_propietario = Parser.parseInt(request.getParameter("id_propietario"));
        String calle  = request.getParameter("calle");
        String numero = request.getParameter("numero");        
        String codigo = request.getParameter("codigo");
        Integer carpeta = Parser.parseInt(request.getParameter("carpeta"));
        String nombre = request.getParameter("nombre");
        String apellido = request.getParameter("apellido");
        String dni = request.getParameter("dni");
        
        Integer page = 0;
        JsonRespuesta jr = new JsonRespuesta();
        TPropietario tpropietario = new TPropietario();
        TPropiedad   tpropiedad   = new TPropiedad();
        try {
                        
            mapEstados = OptionsCfg.getMap( OptionsCfg.getEstadosPropiedad());
            List<Propietario> lista ;
            
            if(numResults>0) tpropietario.setNumResults(numResults);
            
            HashMap<String,String> mapFiltro = new HashMap<String,String> ();
            if(id!=0){
                mapFiltro.put("id",id.toString());
            }           
            
            lista =  tpropietario.getListFiltro(mapFiltro,pagNro);

            ArrayList<PropietarioDet> listaDet = new ArrayList();

            for(Propietario p:lista){
                listaDet.add(new PropietarioDet(p));
                tpropiedad.getById_propietario(p.getId());
            }
            if (lista != null) {
                jr.setTotalRecordCount(tpropietario.getListFiltroCount(mapFiltro));
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
    
    private class PropietarioDet extends Propietario {
        String barrio         = "";
        String tipo_inmueble  = "";
        String propietario    = "";
        String estado         = "";
        String operacion      = "";
        PropietarioDet(Propietario p){
            super(p);           
        }
    }
}

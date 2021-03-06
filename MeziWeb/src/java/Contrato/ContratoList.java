/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Contrato;

import com.google.gson.Gson;
import bd.Contrato;
import bd.Inquilino;
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
public class ContratoList extends HttpServlet {
    private Map<Integer,Propietario> mapPropietarios;
    private Map<Integer,Inquilino>   mapInquilinos;
    private Map<Integer,Propiedad>   mapPropiedades;
    private Map<Integer,Option>      mapEstados;
    
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
        Integer id_cliente = Parser.parseInt(request.getParameter("id_cliente"));
        Integer id_propiedad = Parser.parseInt(request.getParameter("id_propiedad"));
        Integer id_tipo = Parser.parseInt(request.getParameter("id_tipo"));
        Integer page    = Parser.parseInt(pagNro);
        
        try {
            JsonRespuesta jr = new JsonRespuesta();           
           
            List<Contrato> lista ;
            mapPropietarios = new TPropietario().getMap();
            mapInquilinos   = new TInquilino().getMap();
            mapPropiedades  = new TPropiedad().getMap();
            mapEstados      = OptionsCfg.getMap(OptionsCfg.getEstadosContrato());
            
            TContrato tp = new TContrato();
            HashMap<String,String> mapFiltro = new HashMap<String,String> ();                        
            if(id_cliente!=0) {
                
                if(id_tipo.equals(OptionsCfg.CLIENTE_TIPO_PROPIETARIO)) mapFiltro.put("id_propietario", id_cliente.toString());
                else if (id_tipo.equals(OptionsCfg.CLIENTE_TIPO_INQUILINO)) mapFiltro.put("id_inquilino", id_cliente.toString());
            }
            
            if(id_propiedad!=0) mapFiltro.put("id_propiedad",id_propiedad.toString());
            
            lista =  tp.getListFiltro(mapFiltro);
            //lista = tp.getList();
            
            
            if (lista != null) {
                List<Contrato> listaDet = new ArrayList<Contrato>();
                for (Contrato c:lista){
                    listaDet.add(new ContratoDet(c));
                }
                jr.setTotalRecordCount(listaDet.size());
                jr.setResult("OK");
                jr.setRecords(listaDet);
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
    private class ContratoDet extends Contrato{
        String direccion   = "";
        String inquilino   = "";
        String propietario = "";
        String detalles    = "";
        String precio      = "";
        String tipo_contrato   = "";
        String estado_contrato = "";
        
        
        ContratoDet(Contrato contrato) {
            super(contrato);
            Propiedad propiedad = mapPropiedades.get(contrato.getId_propiedad());
            Inquilino inq = mapInquilinos.get(contrato.getId_inquilino());
            Propietario pro = mapPropietarios.get(contrato.getId_propietario());
            
            if(propiedad!=null) direccion = propiedad.getDireccion();
            if(inq!=null) inquilino   = inq.getApellido() + ", " + inq.getNombre();
            if(pro!=null) propietario = pro.getApellido() + ", " + pro.getNombre();
            Option optEstado = mapEstados.get(contrato.getId_estado());
            
            estado_contrato = (optEstado!=null)?optEstado.getDescripcion():"";
            tipo_contrato = "Alquiler";
            //Propietario po = mapPropietarios.get(contrato.getId_propiedad());
        }
    }
}

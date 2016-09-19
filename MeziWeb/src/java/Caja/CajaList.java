/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Caja;

import bd.Caja;
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
import javax.servlet.http.HttpSession;
import transaccion.TCaja;
import utils.BaseException;
import utils.JsonRespuesta;
import utils.OptionsCfg;

/**
 *
 * @author Diego
 */
public class CajaList extends HttpServlet {
    private HashMap<Integer, OptionsCfg.Option> mapEstadosCaja;
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
        Gson gson = new Gson();
        mapEstadosCaja = OptionsCfg.getMap(OptionsCfg.getEstadosCaja());
        
        try {
           HttpSession sesion = request.getSession(false);
           Integer id_usuario = (Integer) sesion.getAttribute("id_usuario");
           Integer id_tipo_usuario_actual = (Integer) sesion.getAttribute("id_tipo_usuario");
           if (id_usuario ==null) throw new BaseException("ERROR","Su sesi&oacute;n expir&oacute;. Debe estar logueado para abrir una caja");                
         
           TCaja tc = new TCaja();
           HashMap<String,String> mapFiltro = new HashMap<String,String>();
           mapFiltro.put("id_usuario", id_usuario.toString());
           tc.setOrderBy(" fecha desc");
           List<Caja> listFiltro = tc.getListFiltro(mapFiltro);           
           if(listFiltro==null) throw new BaseException("ERROR","Ocurri&oacute; un error al listar las cajas");
           ArrayList listaDet = new ArrayList<CajaDet>();
           
            for(Caja caja:listFiltro)   {
                listaDet.add(new CajaDet(caja));
            }           
           jr.setResult("OK");
           jr.setRecords(listaDet);
        } catch(BaseException ex){
            jr.setResult(ex.getResult());
            jr.setMessage(ex.getMessage());
        } finally {
            out.printf(gson.toJson(jr));
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
    
    private class CajaDet extends Caja{
        public String estado = "";
        public CajaDet(Caja caja){
            super(caja);
            OptionsCfg.Option opt = mapEstadosCaja.get(caja.getId_estado());
            estado = opt==null?caja.getId_estado().toString():opt.getDescripcion();
        }
    }
    
}

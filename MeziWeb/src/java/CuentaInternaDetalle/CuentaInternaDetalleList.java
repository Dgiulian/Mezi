/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package CuentaInternaDetalle;

import bd.Caja;
import bd.Cuenta_interna;
import bd.Cuenta_interna_detalle;
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
import transaccion.TCuenta_interna;
import transaccion.TCuenta_interna_detalle;
import utils.BaseException;
import utils.JsonRespuesta;
import utils.OptionsCfg;
import utils.Parser;

/**
 *
 * @author Diego
 */
public class CuentaInternaDetalleList extends HttpServlet {

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
//        mapEstadosCaja = OptionsCfg.getMap(OptionsCfg.getEstadosCaja());
        
        try {
           HttpSession sesion = request.getSession(false);
           Integer id_usuario = (Integer) sesion.getAttribute("id_usuario");
           Integer id_tipo_usuario_actual = (Integer) sesion.getAttribute("id_tipo_usuario");
           if (id_usuario ==null) throw new BaseException("ERROR","Su sesi&oacute;n expir&oacute;. Debe estar logueado para abrir una caja");                
         
           Integer id_cuenta = Parser.parseInt(request.getParameter("id_cuenta"));
           
           Cuenta_interna cuenta = new TCuenta_interna().getById(id_cuenta);
           if(cuenta == null) throw new BaseException("ERROR","No se encontr&oacute; la cuenta");
           
           TCuenta_interna_detalle tc = new TCuenta_interna_detalle();
           HashMap<String,String> mapFiltro = new HashMap<String,String>();
           mapFiltro.put("id_cuenta", id_cuenta.toString());
           
           List<Cuenta_interna_detalle> listFiltro = tc.getListFiltro(mapFiltro);           
           if(listFiltro==null) throw new BaseException("ERROR","Ocurri&oacute; un error al listar el detalle de cuentas internas");
           ArrayList listaDet = new ArrayList<Cuenta_interna_detalleDet>();
           Float saldo = 0f;
           for(Cuenta_interna_detalle detalle:listFiltro) {
                saldo += detalle.getHaber() - detalle.getDebe();
                detalle.setSaldo(saldo);
                listaDet.add(new Cuenta_interna_detalleDet(detalle));
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
private class Cuenta_interna_detalleDet extends Cuenta_interna_detalle{
        public String estado = "";
        public Cuenta_interna_detalleDet(Cuenta_interna_detalle detalle){
            super(detalle);
            
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
}

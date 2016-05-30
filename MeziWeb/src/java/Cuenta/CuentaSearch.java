/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Cuenta;

import bd.Contrato;
import bd.Cuenta;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import transaccion.TContrato;
import transaccion.TCuenta;
import utils.BaseException;
import utils.JsonRespuesta;
import utils.Parser;

/**
 *
 * @author Diego
 */
public class CuentaSearch extends HttpServlet {

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
      Integer id           = Parser.parseInt(request.getParameter("id"));
      Integer id_cliente   = Parser.parseInt(request.getParameter("id_cliente"));
      Integer id_contrato  = Parser.parseInt(request.getParameter("id_contrato"));
      Integer id_propiedad = Parser.parseInt(request.getParameter("id_propiedad"));
      Integer id_tipo_cliente = Parser.parseInt(request.getParameter("id_tipo_cliente"));
      Integer id_tipo = Parser.parseInt(request.getParameter("id_tipo"));
      Integer page = Parser.parseInt(request.getParameter("pagNro"));
      
      JsonRespuesta jr = new JsonRespuesta();
        try {            
            TCuenta tc = new TCuenta();
            HashMap<String,String> mapFiltro = new HashMap<String,String> ();
            
            HashMap<String,String> filtroCuenta = new HashMap<String,String>();
            Cuenta cuenta = null;
            cuenta = tc.getById(id);
            if(cuenta==null) {
                if(id_cliente == 0 && id_contrato ==0) throw new BaseException("ERROR","Debe seleccionar alg&uacute;n criterio para la busqueda de cuenta corriente");
                cuenta = tc.getBydClienteContrato(id_cliente,id_contrato,id_tipo);
            }
            if(cuenta==null) throw new BaseException("ERROR","No se encontr&oacute; la cuenta corriente");
            
            Contrato contrato = new TContrato().getById(cuenta.getId_contrato());
                if(cuenta.getFecha_liquidacion()==null || cuenta.getFecha_liquidacion().equals("")) cuenta.setFecha_liquidacion(contrato.getFecha_inicio());
                
            jr.setResult("OK");
            jr.setTotalRecordCount(1);
            jr.setRecord(cuenta);
            
        } catch (BaseException ex) {
            jr.setResult(ex.getResult());
            jr.setMessage(ex.getMessage());
            jr.setTotalRecordCount(0);
        } finally {
            String jsonResult = new Gson().toJson(jr);
            out.print(jsonResult);
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
}

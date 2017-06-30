/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Contrato;

import bd.Contrato;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import transaccion.TContrato;
import utils.BaseException;
import utils.JsonRespuesta;
import utils.OptionsCfg;
import utils.Parser;

/**
 *
 * @author Diego
 */
public class ContratoSearch extends HttpServlet {

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
      Integer id_cliente   = Parser.parseInt(request.getParameter("id_cliente"));
      Integer id_contrato  = Parser.parseInt(request.getParameter("id_contrato"));
      Integer id_propiedad = Parser.parseInt(request.getParameter("id_propiedad"));
      Integer id_tipo = Parser.parseInt(request.getParameter("id_tipo"));
      
      Integer page = Parser.parseInt(request.getParameter("pagNro"));
      
      JsonRespuesta jr = new JsonRespuesta();
        try {
            
            TContrato tc = new TContrato();
            Contrato contrato;
            HashMap<String,String> mapFiltro = new HashMap<String,String> ();
            TContrato tcd = new TContrato();
            HashMap<String,String> filtroContrato = new HashMap<String,String>();
            if(id_cliente == 0 && id_contrato ==0 && id_propiedad == 0) throw new BaseException("ERROR","Debe seleccionar alg&uacute;n criterio para la busqueda del contrato");
            
            if(id_cliente!=0) {
                
                if(id_tipo.equals(OptionsCfg.CLIENTE_TIPO_PROPIETARIO)) filtroContrato.put("id_propietario", id_cliente.toString());
                else if (id_tipo.equals(OptionsCfg.CLIENTE_TIPO_INQUILINO)) filtroContrato.put("id_inquilino", id_cliente.toString());
            }
            
            if(id_contrato!=0) filtroContrato.put("id_contrato",id_contrato.toString());
            
            if(id_propiedad!=0) filtroContrato.put("id_propiedad",id_propiedad.toString());
            
            
            List<Contrato> lstContratos = tc.getListFiltro(filtroContrato);
            if(lstContratos==null || lstContratos.isEmpty()) throw new BaseException("ERROR","No se encontr&oacute; la cuenta corriente");
            
            contrato = lstContratos.get(0);
            
            if (contrato != null) {
                jr.setResult("OK");
                jr.setTotalRecordCount(1);
                jr.setRecord(contrato);
            } else {
                jr.setResult("ERROR");
                jr.setTotalRecordCount(0);
            }                        
        } catch (BaseException ex) {
            jr.setResult(ex.getResult());
            jr.setMessage(ex.getMessage());
            
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

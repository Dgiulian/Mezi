/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Recibo;

import bd.Cliente;
import bd.Recibo;
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
import transaccion.TCliente;
import transaccion.TRecibo;
import utils.JsonRespuesta;
import utils.OptionsCfg;
import utils.Parser;

/**
 *
 * @author Diego
 */
public class ReciboList extends HttpServlet {

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
        Integer id_localidad = Parser.parseInt(request.getParameter("id_localidad"));
        Integer page = Parser.parseInt(pagNro);
        
       
        try {
            JsonRespuesta jr = new JsonRespuesta();           

            List<Recibo> lista ;

            TRecibo tp = new TRecibo();
            HashMap<String,String> mapFiltro = new HashMap<String,String> ();
            tp.setOrderBy(" fecha desc ");
            lista =  tp.getListFiltro(mapFiltro);
           //lista = tp.getList();
            ArrayList<ReciboDet> listaDet = new ArrayList();
            for(Recibo c:lista){
                listaDet.add(new ReciboDet(c));
            } 
            if (lista != null) {
                jr.setTotalRecordCount(lista.size());
                jr.setResult("OK");
                jr.setRecords(listaDet);
            } else {                
                jr.setTotalRecordCount(0);
                jr.setResult("ERROR");
            }            

            
            
            String jsonResult = new Gson().toJson(jr);

            out.print(jsonResult);
        } finally {            
            out.close();
        }
    }
    
    private class ReciboDet extends Recibo{
        String tipo_recibo = "";
        String tipo_cliente = "";
        String cliente = "";
        String estado = "";
        
        public ReciboDet(Recibo recibo){
            super(recibo);
            
            
            switch (recibo.getId_tipo_cliente()){
               case OptionsCfg.CLIENTE_TIPO_INQUILINO:
                   tipo_cliente = "Inquilino";
                   break;
                case OptionsCfg.CLIENTE_TIPO_PROPIETARIO:
                    tipo_cliente = "Propietario";
                    break;
                case OptionsCfg.CLIENTE_TIPO_INTERNA: 
                    tipo_cliente = "Interna";
                    break;
            }
            switch (recibo.getId_tipo_recibo()){
                case OptionsCfg.RECIBO_PAGO:
                    tipo_recibo = "Pago"; 
                    break;
                case OptionsCfg.RECIBO_ANULA:
                    tipo_recibo = "Anula";
                    break;
            }
            switch (recibo.getId_estado()){
                case OptionsCfg.RECIBO_PAGO:
                    estado = ""; 
                    break;
                case OptionsCfg.RECIBO_ANULA:
                    estado = "Anulado";
                    break;
            }
            Cliente  c = new TCliente().getById(recibo.getId_cliente());
            if(c!=null) cliente = c.getApellidoyNombre();
            
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
}

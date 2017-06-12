/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Recibo;

import bd.Cliente;
import bd.Contrato;
import bd.Cuenta;
import bd.Recibo;
import bd.Recibo_detalle;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import transaccion.TCliente;
import transaccion.TContrato;
import transaccion.TCuenta;
import transaccion.TRecibo;
import transaccion.TRecibo_detalle;
import utils.BaseException;
import utils.JsonRespuesta;
import utils.OptionsCfg;
import utils.Parser;

/**
 *
 * @author Diego
 */
public class ReciboSearch extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
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
      Integer id       = Parser.parseInt(request.getParameter("id"));
      Integer numero   = Parser.parseInt(request.getParameter("numero"));
      Integer page = Parser.parseInt(request.getParameter("pagNro"));
      JsonRespuesta jr = new JsonRespuesta();
      try {            
            TRecibo tc = new TRecibo();
            TRecibo_detalle trd = new TRecibo_detalle();
            HashMap<String,String> mapFiltro = new HashMap<String,String> ();            

            
            
            Recibo recibo = null;
            recibo = tc.getByNumero(numero);            
            if(recibo==null) throw new BaseException("ERROR","No se encontr&oacute; el recibo ");
            
            if(numero>0) mapFiltro.put("id_recibo",recibo.getId().toString());
            List<Recibo_detalle> lstDetalle = trd.getListFiltro(mapFiltro);
            if(recibo.getId_tipo_recibo().equals(OptionsCfg.RECIBO_ANULA)) throw new BaseException("ERROR","El recibo indicado corresponde a un Comprobante de Anulaci&oacute;n no se puede anular");
            if(recibo.getId_estado().equals(OptionsCfg.RECIBO_ANULA)) throw new BaseException("ERROR","El recibo indicado ya fu&eacute; anulado. No se puede volver a anular.");
            
            jr.setResult("OK");
            jr.setTotalRecordCount(1);
            jr.setRecord(new ReciboDet(recibo));
            jr.setRecords(lstDetalle);
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

    /**
     * Handles the HTTP <code>GET</code> method.
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
     * Handles the HTTP <code>POST</code> method.
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
    private class ReciboDet extends Recibo {
        String tipo_recibo  = "";
        String cuenta       = "";
        String cliente      = "";
        Integer contrato    = 0;
        String tipo_cliente = "";
        Integer carpeta     = 0;
        public ReciboDet(Recibo recibo){
            super(recibo);
            Cuenta    c = new TCuenta().getById(recibo.getId_cuenta());
            Cliente  cl = new TCliente().getById(recibo.getId_cliente());
            Contrato ct = new TContrato().getById(recibo.getId_contrato());
            if(c!=null) cuenta = c.getDescripcion();
            if(cl!=null) {
                cliente = cl.getApellidoyNombre();
                carpeta = cl.getCarpeta();
                switch(recibo.getId_tipo_recibo()){
                    case OptionsCfg.CLIENTE_TIPO_INQUILINO: tipo_recibo = "Inquilino";break;
                    case OptionsCfg.CLIENTE_TIPO_PROPIETARIO: tipo_recibo = "Propietario";break;
                    case OptionsCfg.CLIENTE_TIPO_INTERNA: tipo_recibo = "Cuenta Interna";break;
                    default: tipo_recibo = "";
                }
            }
            if(ct!=null) contrato = ct.getNumero();
            
        }
    }
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Cuenta;

import bd.Contrato;
import bd.Contrato_documento;
import bd.Contrato_gasto;
import bd.Contrato_valor;
import bd.Cuenta;
import bd.Cuenta_detalle;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import transaccion.TContrato;
import transaccion.TContrato_documento;
import transaccion.TContrato_gasto;
import transaccion.TContrato_valor;
import transaccion.TCuenta;
import transaccion.TCuenta_detalle;
import utils.BaseException;
import utils.JsonRespuesta;
import utils.OptionsCfg;
import utils.Parser;
import utils.TFecha;

/**
 *
 * @author Diego
 */
public class CuentaReplicar extends HttpServlet {

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
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        JsonRespuesta jr = new JsonRespuesta();
        try {
            Integer id_cuenta = Parser.parseInt(request.getParameter("id"));
            TCuenta tc = new TCuenta();
            Cuenta cuenta = tc.getById(id_cuenta);
            if (cuenta==null) throw new BaseException("ERROR","No se encontr&oacute; la cuenta");
            
            List<Cuenta_detalle> lstDetalle = null;
            TCuenta_detalle tcd = new TCuenta_detalle();
            Contrato contrato = new TContrato().getById(cuenta.getId_contrato());
            if (contrato==null) throw new BaseException("ERROR","No se encontr&oacute; el contrato");
            ArrayList<Contrato_valor> lstValor = new ArrayList<Contrato_valor>();
            for(Contrato_valor cv: new TContrato_valor().getById_contrato(contrato.getId())){
                lstValor.add(cv);
            }
            ArrayList<Contrato_gasto> lstGasto = new ArrayList<Contrato_gasto>();
            for(Contrato_gasto cv: new TContrato_gasto().getById_contrato(contrato.getId())){
                if(cv.getId_aplica()==cuenta.getId_tipo_cliente()) lstGasto.add(cv);
            }
            
            ArrayList<Contrato_documento> lstDocumento = new ArrayList<Contrato_documento>();
            for(Contrato_documento cv: new TContrato_documento().getById_contrato(contrato.getId())){
                lstDocumento.add(cv);
            }
            
            if(cuenta.getId_tipo_cliente()==OptionsCfg.CLIENTE_TIPO_INQUILINO) {
                if(cuenta.getId_tipo()==OptionsCfg.CUENTA_OFICIAL) lstDetalle=tcd.detalleInquilino(contrato, lstValor, lstGasto);
                else lstDetalle = tcd.detalleInquilino(lstDocumento);                        
            } else {
                if(cuenta.getId_tipo()==OptionsCfg.CUENTA_OFICIAL) 
                lstDetalle  = tcd.detallePropietario(contrato, lstValor, lstGasto);
                else lstDetalle = tcd.detallePropietario(lstDocumento);
                
            }
            if (lstDetalle==null) throw new BaseException("ERROR","No se pudo replicar la cuenta");
            
            for(Cuenta_detalle cd:tcd.getById_cuenta(cuenta.getId())) {
                tcd.baja(cd);
            }
           for(Cuenta_detalle cd:lstDetalle){
                cd.setId_cuenta(id_cuenta);
                cd.setFecha_creacion(TFecha.ahora(TFecha.formatoBD + " " + TFecha.formatoHora));                    
                tcd.alta(cd);
           }
           jr.setResult("OK");
           jr.setMessage("Se replico la cuenta correctamente");
            
        } catch (BaseException ex) {
            jr.setResult(ex.getResult());
            jr.setMessage(ex.getMessage());            
        } finally {    
            out.println(new Gson().toJson(jr));
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

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Recibo;

import bd.Caja;
import bd.Caja_detalle;
import bd.Contrato;
import bd.Cuenta;
import bd.Cuenta_detalle;
import bd.Pago;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bd.Recibo;
import bd.Recibo_detalle;
import javax.servlet.http.HttpSession;
import transaccion.TCaja;
import transaccion.TCaja_detalle;
import transaccion.TContrato;
import transaccion.TCuenta;
import transaccion.TCuenta_detalle;
import transaccion.TPago;
import transaccion.TRecibo;

import transaccion.TRecibo_detalle;
import utils.BaseException;
import utils.JsonRespuesta;
import utils.OptionsCfg;
import utils.Parser;
import utils.TFecha;

/**
 *
 * @author Diego
 */
public class ReciboAnular extends HttpServlet {


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
        request.getRequestDispatcher("recibo_anular.jsp").forward(request, response);
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
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter out = response.getWriter();
        JsonRespuesta jr = new JsonRespuesta();
        HttpSession sesion = request.getSession(false);
        Integer id_recibo = Parser.parseInt(request.getParameter("id_recibo"));
        Integer id_usuario = (Integer) sesion.getAttribute("id_usuario");
        Integer id_caja    = (Integer) sesion.getAttribute("id_caja");
        TRecibo tr = new TRecibo();
        TRecibo_detalle trd = new TRecibo_detalle();
        TCuenta tc = new TCuenta();
        TCuenta_detalle tcd = new TCuenta_detalle();
        TCaja tcaja = new TCaja();
        TCaja_detalle tcaja_detalle = new TCaja_detalle();
        TPago tp = new TPago();
        TContrato tcon = new TContrato();
        try{
            Recibo posterior;
            Recibo anterior;
            Recibo anula = new Recibo();    
            Recibo recibo = tr.getById(id_recibo);
            if(recibo== null) throw new BaseException("ERROR","No se encontr&oacute; el recibo");
            
            //Buscamos la caja abierta del dia
            Caja caja = tcaja.getById(id_caja);
            if(caja==null || caja.getId_estado()!=OptionsCfg.CAJA_ABIERTA) throw new BaseException("ERROR","No existe una caja abierta.");
                            
            Cuenta cuenta = tc.getById(recibo.getId_cuenta());
            if(cuenta==null) throw new BaseException("ERROR","No se encontr&oacute; la cuenta");
            
            Contrato contrato = tcon.getById(recibo.getId_contrato());
            if (contrato==null) throw new BaseException("ERROR","No se encontr&oacute; el contrato");
            
            // Controla que no exista un recibo posterior al que se está por anular
            posterior = tr.getPosterior(recibo);            
            if(posterior!=null) throw new BaseException("ERROR","Existe un recibo con fecha posterior para esa cuenta. No se puede anular el recibo.");
            Pago pago = tp.getById(recibo.getId_pago());
            if(pago==null) throw new BaseException("ERROR","No se encontr&oacute; el pago");
            
            // cuando se anula un recibo, la fecha de ultima liquidación es la del recibo anterior            
            String fecha_anterior;
            anterior = tr.getAnterior(recibo);            
            
            fecha_anterior = (anterior!=null)?anterior.getFecha():contrato.getFecha_inicio();
            
            cuenta.setFecha_liquidacion(fecha_anterior);
            
            //Creamos el concepto de anulacion en la cuenta
            Float total = pago.getTotal();
            Cuenta_detalle cd = new Cuenta_detalle();
            cd.setId_concepto(OptionsCfg.CONCEPTO_ANULA_PAGO);
            cd.setConcepto(String.format("Anulación de recibo nro %d",recibo.getNumero()));
            cd.setFecha(recibo.getFecha());
            cd.setId_cuenta(recibo.getId_cuenta());
            cd.setFecha_creacion(TFecha.ahora());
            cd.setId_referencia(recibo.getId());
            
            if (cuenta.getId_tipo_cliente().equals(OptionsCfg.CLIENTE_TIPO_PROPIETARIO)) {
                cd.setHaber(total);
            } else {
                cd.setDebe(total);
            }
            
            //Actualizamos la cuenta 
            tc.actualizar(cuenta);
            tcd.alta(cd);
            
            anula.setFecha(TFecha.ahora());            
            anula.setFecha_creacion(TFecha.ahora());
            anula.setId_caja(caja.getId());
            anula.setId_cliente(recibo.getId_cliente());
            anula.setId_contrato(recibo.getId_contrato());
            anula.setId_cuenta(recibo.getId_cuenta());
            anula.setId_tipo_cliente(recibo.getId_tipo_cliente());
            anula.setId_tipo_recibo(OptionsCfg.RECIBO_ANULA);
            anula.setNumero(tr.getNumero());
            
            
            Integer id_anula = tr.alta(anula);
            anula.setId(id_anula);
            
            recibo.setId_estado(OptionsCfg.RECIBO_ANULA);
            
            // Se revierte el pago en caja
            if(pago.getEfectivo()>0){
                Caja_detalle caja_detalle = tcaja_detalle.creaEgresoEfectivo(caja, cuenta, anula, pago.getEfectivo());                
                tcaja_detalle.alta(caja_detalle);
            }
            if(pago.getCheque_mnt()>0){
                Caja_detalle caja_detalle = tcaja_detalle.creaEgresoCheque(caja,cuenta,anula,pago.getCheque_mnt());                
                tcaja_detalle.alta(caja_detalle);
            }
            if(pago.getTransf_mnt()>0){
                Caja_detalle caja_detalle = tcaja_detalle.creaEgresoTransferencia(caja,cuenta,anula,pago.getCheque_mnt());                
                tcaja_detalle.alta(caja_detalle);
            }
            
            //HashMap<String,String> mapFilrto = new HashMap<String,String>();

            
            Recibo_detalle detalle_anula = new Recibo_detalle();
            detalle_anula.setId_concepto(OptionsCfg.CONCEPTO_ANULA_PAGO);
            detalle_anula.setConcepto(String.format("Anulación de recibo nro %d",recibo.getNumero()));
            detalle_anula.setFecha(recibo.getFecha());
            detalle_anula.setId_recibo(anula.getId());
            if (cuenta.getId_tipo_cliente()==OptionsCfg.CLIENTE_TIPO_PROPIETARIO) { 
                detalle_anula.setHaber(total);
            } else {
                detalle_anula.setDebe(total);
            }
            trd.alta(detalle_anula);
            jr.setResult("OK");
            jr.setRecord(anula);
            
            jr.setMessage("El recibo se anulo correctamente");
        } catch(BaseException ex){
            jr.setResult(ex.getResult());
            jr.setMessage(ex.getMessage());
        } finally{
            out.println(new Gson().toJson(jr));
            out.close();
        }
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

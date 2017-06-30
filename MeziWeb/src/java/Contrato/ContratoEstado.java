/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Contrato;

import bd.Cliente;
import bd.Contrato;
import bd.Contrato_documento;
import bd.Contrato_gasto;
import bd.Contrato_valor;
import bd.Cuenta;
import bd.Cuenta_detalle;
import bd.Propiedad;
import bd.Propietario;
import bd.Vendedor;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.joda.time.LocalDate;

import transaccion.TCliente;
import transaccion.TContrato;
import transaccion.TContrato_documento;
import transaccion.TContrato_gasto;
import transaccion.TContrato_valor;
import transaccion.TCuenta;
import transaccion.TCuenta_detalle;
import transaccion.TInquilino;
import transaccion.TParametro;
import transaccion.TPropiedad;
import transaccion.TPropietario;
import transaccion.TVendedor;
import utils.BaseException;
import utils.OptionsCfg;
import utils.Parser;
import utils.PathCfg;
import utils.TFecha;

/**
 *
 * @author Diego
 */
public class ContratoEstado extends HttpServlet {


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
        Integer id_contrato = Parser.parseInt(request.getParameter("id"));
        
        try{
        Contrato contrato = new TContrato().getById(id_contrato);

        if(contrato==null) throw  new BaseException("ERROR","No se encontr&oacute; el contrato");
        List<Contrato_valor>     contrato_valor     = new TContrato_valor().getById_contrato(id_contrato);
        List<Contrato_gasto>     contrato_gasto     = new TContrato_gasto().getById_contrato(id_contrato);
        List<Contrato_documento> contrato_documento = new TContrato_documento().getById_contrato(id_contrato);
        Propiedad propiedad = new TPropiedad().getById(contrato.getId_propiedad());
        Cliente inquilino   = new TCliente().getById(contrato.getId_inquilino());
        Propietario propietario = new TPropietario().getById(contrato.getId_propietario());
        Vendedor vendedor = new TVendedor().getById(contrato.getId_vendedor());
        TCuenta_detalle tcd = new TCuenta_detalle();
        TCuenta tc = new TCuenta();
        
        request.setAttribute("contrato",contrato);
        request.setAttribute("contrato_valor",contrato_valor);
        request.setAttribute("contrato_documento",contrato_documento);
        request.setAttribute("contrato_gasto",contrato_gasto);
        
        if(propiedad!=null) 
            request.setAttribute("propiedad", propiedad);
        
        if(inquilino!=null)
            request.setAttribute("inquilino",inquilino);
        if(propietario!=null)
            request.setAttribute("propietario",propietario);
        
        if (vendedor!=null)
            request.setAttribute("vendedor",vendedor);
        List<Cuenta> cuentas_contrato = tc.getById_contrato(contrato.getId());
        
        for(Cuenta cuenta: cuentas_contrato){
            String fecha_liquidacion = cuenta.getFecha_liquidacion();
            if( fecha_liquidacion==null || fecha_liquidacion.equals("")) fecha_liquidacion = contrato.getFecha_inicio();
            
            LocalDate fecha_desde = new LocalDate(fecha_liquidacion);
            LocalDate fecha_hasta = new LocalDate();
            List<Cuenta_detalle> lstConceptos = tcd.calcularPunitorios(cuenta, contrato,fecha_desde, fecha_hasta );
            if(cuenta.getId_tipo_cliente().equals(OptionsCfg.CLIENTE_TIPO_INQUILINO)){
                if(cuenta.getId_tipo().equals(OptionsCfg.CUENTA_OFICIAL))                 
                    request.setAttribute("cuenta_inquilino_oficial",lstConceptos);
                else request.setAttribute("cuenta_inquilino_no_oficial",lstConceptos);
            }
        }
        request.getRequestDispatcher("contrato_estado.jsp").forward(request, response);
        } catch(BaseException ex) {
            request.setAttribute("titulo",ex.getResult());
            request.setAttribute("mensaje",ex.getMessage());
            request.getRequestDispatcher("message.jsp").forward(request, response);
        }
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
        Integer  id_contrato    = Parser.parseInt(request.getParameter("id_contrato"));
        Float  saldo_oficial    = Parser.parseFloat(request.getParameter("saldo_oficial"));
        Float  saldo_no_oficial = Parser.parseFloat(request.getParameter("saldo_no_oficial"));
        //Integer    id_estado = Parser.parseInt(request.getParameter("id_estado"));
        TContrato  tcontrato = new TContrato();
       try{
           Contrato contrato = tcontrato.getById(id_contrato);
           if(contrato==null) throw new BaseException("ERROR","No se encontr&oacute; el contrato");
           switch( contrato.getId_estado()){
               case (OptionsCfg.CONTRATO_ESTADO_INICIAL):{
                tcontrato.activar(contrato);
                break;
                }
                case (OptionsCfg.CONTRATO_ESTADO_ACTIVO):{                    
                    tcontrato.cerrar(contrato); // Pasamos la propiedad a disponible
                    
                    //Si el saldo de las cuentas es 0. Finalizamos el contrato
                    if(saldo_oficial ==0 && saldo_no_oficial==0) tcontrato.finalizar(contrato);
                    break; 
                }
                case (OptionsCfg.CONTRATO_ESTADO_ENTREGA):{
                    if(saldo_oficial !=0)    throw new BaseException("ERROR",String.format("El saldo de la cuenta oficial es %.2f. No se puede finalizar el contrato",saldo_oficial));
                    if(saldo_no_oficial !=0) throw new BaseException("ERROR",String.format("El saldo de la cuenta no oficial es %.2f. No se puede finalizar el contrato",saldo_no_oficial));
                    tcontrato.finalizar(contrato);
                    break;
                }
                default:{
                   throw new BaseException("ERROR","El contrato est&aacute; en estado finalizado");           
                }
            }
                      
           response.sendRedirect(PathCfg.CONTRATO);
       } catch(BaseException ex){
            request.setAttribute("titulo", ex.getResult());
             request.setAttribute("mensaje", ex.getMessage());
             request.getRequestDispatcher("message.jsp").forward(request,response);
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

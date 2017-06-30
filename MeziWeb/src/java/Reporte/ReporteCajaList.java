/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Reporte;

import bd.Caja;
import bd.Caja_detalle;
import bd.Cliente;
import bd.Cuenta;
import bd.Cuenta_interna;
import bd.Usuario;
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
import org.joda.time.LocalDate;
import transaccion.TCaja;
import transaccion.TCaja_detalle;
import transaccion.TCliente;
import transaccion.TCuenta;
import transaccion.TCuenta_interna;
import transaccion.TUsuario;
import utils.BaseException;
import utils.JsonRespuesta;
import utils.OptionsCfg;
import utils.Parser;
import utils.TFecha;

/**
 *
 * @author Diego
 */
public class ReporteCajaList extends HttpServlet {    
    private HashMap<Integer,OptionsCfg.Option> mapFormaPago;
    private HashMap<Integer,OptionsCfg.Option> mapMovimiento;
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

        try {
          Integer id_caja     = Parser.parseInt(request.getParameter("id_caja"));
          Integer id_estado   = Parser.parseInt(request.getParameter("id_estado"));
          Integer id_forma    = Parser.parseInt(request.getParameter("id_forma"));
          Integer id_usuario  = Parser.parseInt(request.getParameter("id_usuario"));
          String  fecha_desde = TFecha.formatearFechaVistaBd(request.getParameter("fecha_desde"));
          String  fecha_hasta = TFecha.formatearFechaVistaBd(request.getParameter("fecha_hasta"));
          HttpSession session = request.getSession();            
          Integer id_usuario_actual = (Integer) session.getAttribute("id_usuario");
          Integer id_tipo_usuario_actual = (Integer) session.getAttribute("id_tipo_usuario");
          
          //Si es un usuario distinto de Administrador, solo se muestran las cajas del usuario actual          
          if(!id_tipo_usuario_actual.equals(OptionsCfg.USUARIO_ADMINISTRADOR)) id_usuario = id_usuario_actual;
          
          mapFormaPago  = OptionsCfg.getMap(OptionsCfg.getFormaPago());
          mapMovimiento = OptionsCfg.getMap(OptionsCfg.getTipoMovimiento());
          mapEstadosCaja = OptionsCfg.getMap(OptionsCfg.getEstadosCaja());
        
          TCaja tc =  new TCaja();          
          TCaja_detalle tcd = new TCaja_detalle();
          HashMap<String,String> mapFiltro = new HashMap<String,String>();
          HashMap<String,String> mapFiltroCaja = new HashMap<String,String>();
          
          tc.setOrderBy(" fecha desc ");
          if(id_estado!=0) mapFiltroCaja.put("id_estado", id_estado.toString());
          if(id_usuario!=0) mapFiltroCaja.put("id_usuario", id_usuario.toString());
          
          List<Caja> lstCajas = tc.getListFiltro(mapFiltroCaja);
          
          ArrayList<CajaDet> listaDet = new ArrayList<CajaDet>();
          LocalDate desde = null;
          LocalDate hasta = null;
          
          if(fecha_desde!=null && !fecha_desde.equals(""))desde = new LocalDate(fecha_desde);
          if(fecha_hasta!=null && !fecha_hasta.equals(""))hasta = new LocalDate(fecha_hasta);
          
          for(Caja caja:lstCajas) {
            CajaDet cd = new CajaDet(caja);
            LocalDate fecha = new LocalDate(caja.getFecha().getFecha());
            if(desde!=null && fecha.isBefore(desde)) continue;
            if(hasta!=null && fecha.isAfter(hasta)) continue;
            
            mapFiltro.put("id_caja", caja.getId().toString());
            if(id_forma!=0) mapFiltro.put("id_forma", id_forma.toString());
            List<Caja_detalle> listFiltro = tcd.getListFiltro(mapFiltro);
            
            if(listFiltro==null) throw new BaseException("ERROR","Ocurri&oacute; un error al listar los movimientos de caja");
            ArrayList<CajaDetalleDet> listaCajaDet = new ArrayList<CajaDetalleDet>();
            Float saldo = 0f;
            Float saldo_efectivo = 0f;
            Float saldo_cheque   = 0f;
            Float saldo_transf   = 0f;
            for(Caja_detalle detalle:listFiltro){
                if(detalle.getConcepto().startsWith("Apertura ")) continue;
                if(detalle.getConcepto().startsWith("Diferencia ")) continue;
                float importe = detalle.getId_tipo() == OptionsCfg.TIPO_INGRESO?detalle.getImporte():-1*detalle.getImporte();
                saldo += importe;
                switch(detalle.getId_forma()){
                    case OptionsCfg.FORMA_EFECTIVO: saldo_efectivo += importe;
                    case OptionsCfg.FORMA_CHEQUE: saldo_cheque += importe;
                    case OptionsCfg.FORMA_TRANSFERENCIA: saldo_transf += importe;
                }
                detalle.setSaldo(saldo);
                listaCajaDet.add(new CajaDetalleDet(detalle));                
            }
            cd.setEfectivo_cierre(saldo_efectivo);
            cd.setCheque_cierre(saldo_cheque);
            cd.setTransferencia_cierre(saldo_transf);
            //if(listaCajaDet.size()>0)   {
                cd.detalle = listaCajaDet;
                listaDet.add(cd);
            //}
          }
          jr.setResult("OK");
          jr.setRecordCount(listaDet.size());
          jr.setRecords(listaDet);
        } catch(BaseException ex){
            jr.setResult(ex.getResult());
            jr.setMessage(ex.getMessage());
        } finally{
            out.print(new Gson().toJson(jr));
            out.close();
        }
    }
    private class CajaDet extends Caja{
        public String estado = "";
        public List detalle;
        public String usuario="";
        public CajaDet(Caja caja){
            super(caja);
            OptionsCfg.Option opt = mapEstadosCaja.get(caja.getId_estado());
            estado = opt==null?caja.getId_estado().toString():opt.getDescripcion();
            Usuario usr = new TUsuario().getById(caja.getId_usuario());
            if(usr!=null) usuario = usr.getUsu_mail();
        }
    }
    private class CajaDetalleDet extends Caja_detalle{
        String forma_pago = "";
        String tipo       = "";
        String nombre_cuenta     = "";
        public CajaDetalleDet(Caja_detalle detalle){
            super(detalle);
            OptionsCfg.Option o = mapFormaPago.get(detalle.getId_forma());
            forma_pago=o!=null?o.getDescripcion():detalle.getId_forma().toString();
            
            o = mapMovimiento.get(detalle.getId_tipo());
            tipo = o!=null?o.getDescripcion():"";
            switch(detalle.getId_tipo_cuenta()){
                case OptionsCfg.CLIENTE_TIPO_INQUILINO:   //nombre_cuenta = "Inquilino";   break;
                case OptionsCfg.CLIENTE_TIPO_PROPIETARIO: {
                    Cuenta cuenta = new TCuenta().getById(this.getId_cuenta());
                    if(cuenta==null) nombre_cuenta=String.format("%d %d",this.getId_tipo_cuenta(),this.getId_cuenta());
                    else {
                        Cliente cliente = new TCliente().getById(cuenta.getId_cliente());
                        nombre_cuenta = cliente!=null?cliente.getApellidoyNombre():this.getId_cuenta().toString();
                    }
                } break;
                case OptionsCfg.CLIENTE_TIPO_INTERNA: {
                    Cuenta_interna cuenta_interna = new TCuenta_interna().getById(this.getId_cuenta());
                    nombre_cuenta = cuenta_interna!=null?cuenta_interna.getNombre():this.getId_cuenta().toString();     
                    break;
                }
                default: nombre_cuenta ="";
            }
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

}

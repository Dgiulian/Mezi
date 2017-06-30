/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package CajaDetalle;

import bd.Caja;
import bd.Caja_detalle;
import bd.Cliente;
import bd.Cuenta;
import bd.Cuenta_interna;
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

import transaccion.TCaja;
import transaccion.TCaja_detalle;
import transaccion.TCliente;
import transaccion.TCuenta;
import transaccion.TCuenta_interna;
import utils.BaseException;
import utils.JsonRespuesta;
import utils.OptionsCfg;
import utils.OptionsCfg.Option;
import utils.Parser;

/**
 *
 * @author Diego
 */
public class CajaDetalleList extends HttpServlet {
    HashMap<Integer,Option> mapFormaPago;
    HashMap<Integer,Option> mapMovimiento;
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
          mapFormaPago  = OptionsCfg.getMap(OptionsCfg.getFormaPago());
          mapMovimiento = OptionsCfg.getMap(OptionsCfg.getTipoMovimiento());
          
          Integer id_caja = Parser.parseInt(request.getParameter("id_caja"));
          Integer id_forma = Parser.parseInt(request.getParameter("id_forma"));
          
          TCaja tc =  new TCaja();          
          TCaja_detalle tcd = new TCaja_detalle();
          HashMap<String,String> mapFiltro = new HashMap<String,String>();
          Caja caja = tc.getById(id_caja);
          if(caja==null) throw new BaseException("ERROR","No se encontr&oacute; la caja");
            /*
             * Chequear:
             *  La caja sea del usuario actual o de Mezi               
             */
             
          mapFiltro.put("id_caja", caja.getId().toString());
                     
           if(id_forma!=0) mapFiltro.put("id_forma", id_forma.toString());
           

          List<Caja_detalle> listFiltro = tcd.getListFiltro(mapFiltro);
          if(listFiltro==null) throw new BaseException("ERROR","Ocurri&oacute; un error al listar los movimientos de caja");
          ArrayList<CajaDetalleDet> listaDet = new ArrayList<CajaDetalleDet>();
          Float saldo = 0f;
          for(Caja_detalle detalle:listFiltro){
              
              saldo += detalle.getId_tipo() == OptionsCfg.TIPO_INGRESO?detalle.getImporte():-1*detalle.getImporte();
              detalle.setSaldo(saldo);
              listaDet.add(new CajaDetalleDet(detalle));
          }
          jr.setResult("OK");
          jr.setRecords(listaDet);
        } catch(BaseException ex){
            jr.setResult(ex.getResult());
            jr.setMessage(ex.getMessage());
        } finally{
            out.print(new Gson().toJson(jr));
            out.close();
        }
    }

    private class CajaDetalleDet extends Caja_detalle{
        String forma_pago = "";
        String tipo       = "";
        String nombre_cuenta     = "";
        public CajaDetalleDet(Caja_detalle detalle){
            super(detalle);
            Option o = mapFormaPago.get(detalle.getId_forma());
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

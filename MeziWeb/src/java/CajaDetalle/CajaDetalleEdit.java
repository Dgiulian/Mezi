/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package CajaDetalle;

import bd.Caja;
import bd.Caja_detalle;
import bd.Cuenta_interna;
import bd.Cuenta_interna_detalle;
import bd.Pago;
import bd.Recibo;
import bd.Recibo_detalle;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import transaccion.TAuditoria;
import transaccion.TCaja;
import transaccion.TCaja_detalle;
import transaccion.TCuenta_interna;
import transaccion.TCuenta_interna_detalle;
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
public class CajaDetalleEdit extends HttpServlet {

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
        try {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet CajaDetalleEdit</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CajaDetalleEdit at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        } finally {            
            out.close();
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
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter out = response.getWriter();
        JsonRespuesta jr = new JsonRespuesta();
        try{
            HttpSession sesion = request.getSession(false);
            Integer id_usuario = (Integer) sesion.getAttribute("id_usuario");
            Integer id_tipo_usuario_actual = (Integer) sesion.getAttribute("id_tipo_usuario");
            Integer id_caja = Parser.parseInt(request.getParameter("id_caja"));
            Integer id_tipo = Parser.parseInt(request.getParameter("id_tipo"));
            Integer id_forma = Parser.parseInt(request.getParameter("id_forma"));
            Float importe = Parser.parseFloat(request.getParameter("importe"));
            String concepto = request.getParameter("concepto");
            Integer id_cuenta = Parser.parseInt(request.getParameter("id_cuenta"));
            
            TCaja tc = new TCaja();
            TCaja_detalle tcd = new TCaja_detalle();
            Caja_detalle detalle = new Caja_detalle();
            Caja caja = tc.getById(id_caja);
            Cuenta_interna cuenta_interna = new TCuenta_interna().getById(id_cuenta);
            Cuenta_interna_detalle cuenta_interna_detalle = new Cuenta_interna_detalle();
            if(cuenta_interna==null); // Que se hace si no existe la cuenta?
            
            boolean nuevo = true;
            if(caja==null) throw new BaseException("ERROR","No se encontr&oacute; la caja");
            if(!caja.getId_usuario().equals(id_usuario)) throw new BaseException("ERROR","No se pueden realizar movimientos en la caja de otro usuario" + caja.getId_usuario().toString() + "/" + id_usuario.toString());
            if(!caja.getId_estado().equals(OptionsCfg.CAJA_ABIERTA)) throw new BaseException("ERROR","Caja cerrada, no se pueden realizar movimientos");
                    
            detalle.setId_caja(caja.getId());
            detalle.setId_tipo(id_tipo);
            detalle.setId_forma(id_forma);
            detalle.setImporte(importe);
            detalle.setConcepto(concepto);
            detalle.setId_tipo_cuenta(OptionsCfg.CLIENTE_TIPO_INTERNA);
            
            
            
            if(cuenta_interna!=null){
                detalle.setId_cuenta(cuenta_interna.getId());
                int id_detalle = tcd.alta(detalle);
                if(id_detalle==0) throw new BaseException("ERROR","Ocurri&oacute; un error al cargar el movimiento de caja");
                detalle.setId(id_detalle);                        
                TAuditoria.guardar(id_usuario,id_tipo_usuario_actual,OptionsCfg.MODULO_CAJA_DETALLE,nuevo?OptionsCfg.ACCION_ALTA:OptionsCfg.ACCION_MODIFICAR,id_caja,tc.auditar(detalle));
            
                cuenta_interna_detalle.setId_cuenta(cuenta_interna.getId());
                detalle.setId_cuenta(cuenta_interna.getId());
                cuenta_interna_detalle.setFecha(caja.getFecha());
                cuenta_interna_detalle.setConcepto(detalle.getConcepto());
                if (id_tipo.equals(OptionsCfg.TIPO_INGRESO)) cuenta_interna_detalle.setHaber(detalle.getImporte());
                else if (id_tipo.equals(OptionsCfg.TIPO_EGRESO)) cuenta_interna_detalle.setDebe(detalle.getImporte());
                new TCuenta_interna_detalle().alta(cuenta_interna_detalle);
                TAuditoria.guardar(id_usuario,id_tipo_usuario_actual,OptionsCfg.MODULO_CUENTA_INTERNA_DETALLE,OptionsCfg.ACCION_ALTA,id_caja,tc.auditar(cuenta_interna_detalle));
            
                Pago p = new Pago();
                TPago tp = new TPago();

                p.setId_cuenta(cuenta_interna_detalle.getId());
                p.setFecha(cuenta_interna_detalle.getFecha().getFecha());
                p.setFecha_creacion(TFecha.ahora(TFecha.formatoBD_Hora));
                switch(id_forma){                
                    case OptionsCfg.FORMA_CHEQUE: {
                            p.setCheque_mnt(detalle.getImporte());
                            break;
                    }
                    case OptionsCfg.FORMA_TRANSFERENCIA: {
                        p.setTransf_mnt(detalle.getImporte()); 
                        break;
                    }
                    case OptionsCfg.FORMA_EFECTIVO: {
                        p.setEfectivo(detalle.getImporte());
                        break;
                    }
                    default:
                }
                
            
            Integer id_pago = tp.alta(p);
            if(id_pago==0) throw new BaseException("ERROR","Ocurri&oacute; un error al guardar el pago. Intentelo nuevamente.");
            
            TRecibo tr = new TRecibo();
            TRecibo_detalle trd = new TRecibo_detalle();
            Recibo recibo = new Recibo();
            recibo.setId_pago(id_pago);
            recibo.setId_cuenta(cuenta_interna.getId());
            recibo.setId_contrato(0);
            recibo.setFecha(caja.getFecha().getFecha());
            recibo.setNumero(0);
            recibo.setId_tipo_cliente(OptionsCfg.CLIENTE_TIPO_INTERNA);
            recibo.setId_tipo_recibo(OptionsCfg.CLIENTE_TIPO_INTERNA);
            recibo.setFecha_creacion(TFecha.ahora(TFecha.formatoBD_Hora));
            recibo.setId_cliente(0);       
            recibo.setNumero(tr.getNumero());
            recibo.setId_caja(id_caja);
            
            Integer id_recibo = tr.alta(recibo );
            recibo.setId(id_recibo);
            if(id_recibo!=0){
                
                Recibo_detalle recibo_detalle = new Recibo_detalle();
                recibo_detalle.setId_recibo(id_recibo);
                recibo_detalle.setConcepto(concepto);
                recibo_detalle.setFecha(recibo.getFecha());                
                recibo_detalle.setDebe(cuenta_interna_detalle.getDebe());
                recibo_detalle.setHaber(cuenta_interna_detalle.getHaber());
                recibo_detalle.setId_cuenta_detalle(cuenta_interna_detalle.getId());
                
                
                trd.alta(recibo_detalle);                
                jr.setResult("OK");
                jr.setRecord(recibo);
            }
          }
            
        } catch(BaseException ex){
            jr.setResult(ex.getResult());
            jr.setMessage(ex.getMessage());
        } finally{
            out.print(new Gson().toJson(jr));
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

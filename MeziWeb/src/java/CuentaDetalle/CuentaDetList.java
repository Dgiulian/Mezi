/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package CuentaDetalle;

import bd.Contrato;
import bd.Cuenta;
import bd.Cuenta_detalle;
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
import org.joda.time.Days;
import org.joda.time.LocalDate;
import transaccion.TContrato;
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
public class CuentaDetList extends HttpServlet {

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
      Integer id_cuenta       = Parser.parseInt(request.getParameter("id_cuenta"));
      Integer id_cliente      = Parser.parseInt(request.getParameter("id_cliente"));
      Integer id_contrato     = Parser.parseInt(request.getParameter("id_contrato"));
      Integer id_propiedad    = Parser.parseInt(request.getParameter("id_propiedad"));
      Integer id_tipo         = Parser.parseInt(request.getParameter("id_tipo"));
      Integer id_tipo_cliente = Parser.parseInt(request.getParameter("id_tipo_cliente"));
      String strFecha_desde   = TFecha.formatearFechaVistaBd(request.getParameter("fecha_desde"));
      String fecha_hasta      =  TFecha.formatearFechaVistaBd(request.getParameter("fecha_hasta"));
//      String fecha_consulta = request.getParameter("fecha_consulta");
      Integer page            = Parser.parseInt(request.getParameter("pagNro"));
      Cuenta cuenta;
      JsonRespuesta jr = new JsonRespuesta();
        try {            
            TCuenta tc = new TCuenta();
            TContrato tcr = new TContrato();
            TCuenta_detalle tcd = new TCuenta_detalle();
            HashMap<String,String> filtroCuenta = new HashMap<String,String>();
            if(id_cuenta!=0)
                cuenta = tc.getById(id_cuenta);
            else {
                if(id_tipo==0) throw new BaseException("ERROR","Indique el tipo de cuenta que desea listar");

                if(id_cliente == 0 && id_contrato ==0) throw new BaseException("ERROR","Debe seleccionar alg&uacute;n criterio para la busqueda de cuenta corriente");            
                System.out.println(String.format("Id_cliente:%d\nid_contrato:%d\nid_tipo:%d,id_tipo_cliente:%d",id_cliente,id_contrato,id_tipo,id_tipo_cliente));
                 cuenta = tc.getBydClienteContrato(id_cliente,id_contrato,id_tipo,id_tipo_cliente);
            }
             if(cuenta==null) throw new BaseException("ERROR","No se encontr&oacute; la cuenta corriente");
             System.out.println("Procesando cuenta: " + cuenta.getId());
            filtroCuenta.put("id_cuenta", cuenta.getId().toString());
//            filtroCuenta.put("id_tipo_cliente", id_tipo_cliente.toString());
            List<Cuenta_detalle> lista = tcd.setOrderBy("fecha,id_concepto").getListFiltro(filtroCuenta);
            
            if (lista != null) {
                Contrato contrato = tcr.getById(cuenta.getId_contrato());
                Float punitorio_porc = contrato.getPunitorio_monto() / 100;
                
                if(strFecha_desde==null || strFecha_desde.equals(""))
                   strFecha_desde = cuenta.getFecha_liquidacion();
                
                if(strFecha_desde==null || strFecha_desde.equals("")) strFecha_desde = contrato.getFecha_inicio();
                
                LocalDate fecha_desde = new LocalDate(strFecha_desde);
                LocalDate fecha_liquidacion;
                if (cuenta.getFecha_liquidacion()!=null && !cuenta.getFecha_liquidacion().equals(""))
                        fecha_liquidacion = new LocalDate(cuenta.getFecha_liquidacion());
                else fecha_liquidacion = new LocalDate(contrato.getFecha_inicio());
                
                LocalDate fecha_consulta;
                if(fecha_hasta==null || fecha_hasta.equals("")) fecha_consulta = new LocalDate();
                else fecha_consulta = new LocalDate(fecha_hasta);
                
                //if (fecha_consulta.isBefore(new LocalDate())) fecha_consulta = new LocalDate();
                
                ArrayList<Cuenta_detalle> listaDetalle      = new ArrayList();
                for(Cuenta_detalle cd:lista) {
                    LocalDate fecha = new LocalDate(cd.getFecha());
                    if (strFecha_desde!=null && (fecha.isBefore(fecha_desde) ) ) continue;
                    
                    if  (fecha.isAfter(fecha_consulta)) continue;
                    listaDetalle.add(cd);
                    if(fecha.isBefore(fecha_liquidacion)) continue;
                    if(fecha.isAfter(new LocalDate())) continue; // No se consideran punitorios de fecha posterior a hoy
                    if (cuenta.getId_tipo_cliente()==OptionsCfg.CLIENTE_TIPO_PROPIETARIO) continue; // No se considera punitorios para los propietarios
                    
                    if (cd.getId_concepto()==OptionsCfg.CONCEPTO_ALQUILER || cd.getId_concepto()==OptionsCfg.CONCEPTO_DOCUMENTO){
                        //int days = Days.daysBetween(fecha, fecha_consulta).getDays() - 1;
                        int days = Days.daysBetween(fecha, new LocalDate()).getDays() - 1;
                        
                        if (days >=contrato.getPunitorio_desde()){
                            float monto_punitorio = days * punitorio_porc * cd.getDebe() ;
                            Cuenta_detalle punitorio = new Cuenta_detalle();
                            punitorio.setFecha(cd.getFecha());
                            punitorio.setConcepto(String.format("Punitorio %s (%d dias)",OptionsCfg.MESES[fecha.getMonthOfYear() - 1],days));
                            punitorio.setId_concepto(OptionsCfg.CONCEPTO_PUNITORIO);
                            
                            punitorio.setDebe(monto_punitorio);
                            listaDetalle.add(punitorio);                            
                        }
                    }
                }
                if(cuenta.getFecha_liquidacion()==null || cuenta.getFecha_liquidacion().equals("")) cuenta.setFecha_liquidacion(strFecha_desde);
                jr.setRecord(cuenta);
                //if(listaDetalle.isEmpty()) throw new BaseException("OK","");
                jr.setResult("OK");
                jr.setTotalRecordCount(listaDetalle.size());
                jr.setRecords(listaDetalle);
                
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

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
        Integer id_tipo         = Parser.parseInt(request.getParameter("id_tipo"));
        Integer id_tipo_cliente = Parser.parseInt(request.getParameter("id_tipo_cliente"));
        String strFecha_desde   = TFecha.formatearFechaVistaBd(request.getParameter("fecha_desde"));
        String fecha_hasta      =  TFecha.formatearFechaVistaBd(request.getParameter("fecha_hasta"));
  //      String fecha_consulta = request.getParameter("fecha_consulta");
        Cuenta cuenta;
        TCuenta tc = new TCuenta();
        TContrato tcr = new TContrato();
        TCuenta_detalle tcd = new TCuenta_detalle();
        JsonRespuesta jr = new JsonRespuesta();
        try {
            
            if(id_cuenta!=0)
                cuenta = tc.getById(id_cuenta);
            else {
                if(id_tipo==0) throw new BaseException("ERROR","Indique el tipo de cuenta que desea listar");

                if(id_cliente == 0 && id_contrato ==0) throw new BaseException("ERROR","Debe seleccionar alg&uacute;n criterio para la busqueda de cuenta corriente");            
                 cuenta = tc.getBydClienteContrato(id_cliente,id_contrato,id_tipo,id_tipo_cliente);
            }
             if(cuenta==null) throw new BaseException("ERROR","No se encontr&oacute; la cuenta corriente");

            tcd.setOrderBy("fecha,id_concepto");
            
            Contrato contrato = tcr.getById(cuenta.getId_contrato());
            Float punitorio_porc = contrato.getPunitorio_monto() / 100;

            if(strFecha_desde==null || strFecha_desde.equals(""))
               strFecha_desde = cuenta.getFecha_liquidacion();

            if(strFecha_desde==null || strFecha_desde.equals("")) strFecha_desde = contrato.getFecha_inicio();

            LocalDate fecha_desde = new LocalDate(strFecha_desde);
            if (cuenta.getFecha_liquidacion()==null || cuenta.getFecha_liquidacion().equals(""))                    
                cuenta.setFecha_liquidacion(contrato.getFecha_inicio());
            
            LocalDate fecha_liquidacion = new LocalDate(cuenta.getFecha_liquidacion());
            LocalDate fecha_consulta;
            if(fecha_hasta==null || fecha_hasta.equals("")) fecha_consulta = new LocalDate();
            else fecha_consulta = new LocalDate(fecha_hasta);

            LocalDate today = new LocalDate();

            Integer punitorio_desde = contrato.getPunitorio_desde();
            ArrayList<Cuenta_detalle> listaDetalle = new ArrayList();
            
            //List<Cuenta_detalle> lista = tcd.getById_cuenta(cuenta.getId());
            List<Cuenta_detalle> lista = tcd.getByRangoFecha(cuenta,fecha_desde,fecha_consulta);
            if (lista == null) throw new BaseException("ERROR","No se encontr&oaacute; la cuenta");
            for(Cuenta_detalle cd:lista) {                    
                LocalDate fecha = new LocalDate(cd.getFecha());
                if (strFecha_desde!=null && (fecha.isBefore(fecha_desde) ) ) continue;

                if  (fecha.isAfter(fecha_consulta)) continue;
                listaDetalle.add(cd);
                if(fecha.isBefore(fecha_liquidacion)) continue;
                if(fecha.isAfter(today)) continue; // No se consideran punitorios de fecha posterior a hoy
                if (cuenta.getId_tipo_cliente().equals(OptionsCfg.CLIENTE_TIPO_PROPIETARIO)) continue; // No se considera punitorios para los propietarios

                int days = Days.daysBetween(fecha, today).getDays() - 1;
                if (days <punitorio_desde) continue;
                Cuenta_detalle punitorio = tcd.calcularPunitorio(cd,1,punitorio_porc);
                if(punitorio!=null) listaDetalle.add(punitorio);
            }
            if(cuenta.getFecha_liquidacion()==null || cuenta.getFecha_liquidacion().equals("")) cuenta.setFecha_liquidacion(strFecha_desde);
            jr.setRecord(cuenta);
            //if(listaDetalle.isEmpty()) throw new BaseException("OK","");
            jr.setResult("OK");
            jr.setTotalRecordCount(listaDetalle.size());
            jr.setRecords(listaDetalle);                
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

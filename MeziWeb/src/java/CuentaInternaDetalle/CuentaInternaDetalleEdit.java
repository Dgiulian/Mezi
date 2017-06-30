/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package CuentaInternaDetalle;

import bd.Cuenta_interna;
import bd.Cuenta_interna_detalle;
import bd.Recibo;
import bd.Recibo_detalle;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.eclipse.jdt.internal.compiler.parser.ParserBasicInformation;
import transaccion.TCuenta_interna;
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
public class CuentaInternaDetalleEdit extends HttpServlet {

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
            out.println("<title>Servlet CuentaInternaDetalleEdit</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CuentaInternaDetalleEdit at " + request.getContextPath() + "</h1>");
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
        
        Integer id_cuenta = Parser.parseInt(request.getParameter("id_cuenta"));
        String fecha = TFecha.formatearFechaVistaBd(request.getParameter("fecha"));
        String concepto = request.getParameter("concepto");
        Integer id_tipo = Parser.parseInt(request.getParameter("id_tipo"));
        Float monto = Parser.parseFloat(request.getParameter("monto"));
        JsonRespuesta jr = new JsonRespuesta();
        TCuenta_interna ti = new TCuenta_interna();
        
        try{
            Cuenta_interna cuenta = ti.getById(id_cuenta);
            if(cuenta==null) throw new BaseException("ERROR","No se encontr&oacute; la cuenta interna");
            Cuenta_interna_detalle cuenta_interna = new Cuenta_interna_detalle();
            cuenta_interna.setId_cuenta(id_cuenta);
            cuenta_interna.setFecha(fecha);
            cuenta_interna.setConcepto(concepto);
            switch (id_tipo) {
                case OptionsCfg.TIPO_INGRESO:
                    cuenta_interna.setHaber(monto);
                    break;
                case OptionsCfg.TIPO_EGRESO:
                    cuenta_interna.setDebe(monto);
                    break;
                default:
                    throw new BaseException("ERROR","Seleccione si el movimiento es de Ingreso o Egreso");
            }
            int id = ti.alta(cuenta_interna);
            if (id==0)throw new BaseException("ERROR","Ocurri&oacute; un error al guardar el movimiento");
            cuenta_interna.setId(id);
            TRecibo tr = new TRecibo();
            TRecibo_detalle trd = new TRecibo_detalle();
            
//            Recibo recibo = new Recibo();            
//            recibo.setId_pago(0);
//            recibo.setId_cuenta(cuenta.getId());
//            recibo.setId_contrato(0);
//            recibo.setFecha(fecha);
//            recibo.setNumero(0);
//            recibo.setId_tipo_cliente(OptionsCfg.CLIENTE_TIPO_INTERNA);
//            recibo.setFecha_creacion(TFecha.ahora(TFecha.formatoBD_Hora));
//            recibo.setId_cliente(0);       
//            recibo.setNumero(tr.getNumero());
//            Integer id_recibo = tr.alta(recibo );   
//            if(id_recibo!=0){
//                Recibo_detalle detalle = new Recibo_detalle();
//                detalle.setId_recibo(id_recibo);
//                detalle.setConcepto(concepto);
//                detalle.setFecha(fecha);
//                detalle.setDebe(cuenta_interna.getDebe());
//                detalle.setHaber(cuenta_interna.getHaber());
//                trd.alta(detalle);                
//            }
//            
            
            jr.setResult("OK");
            jr.setRecord(cuenta_interna);
//            jr.setRecord(recibo);
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

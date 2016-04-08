/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Pago;

import bd.Cuenta;
import bd.Cuenta_detalle;
import bd.Pago;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import transaccion.TCuenta;
import transaccion.TCuenta_detalle;
import transaccion.TPago;
import utils.BaseException;
import utils.JsonRespuesta;
import utils.OptionsCfg;
import utils.Parser;
import utils.TFecha;

/**
 *
 * @author Diego
 */
public class PagoEdit extends HttpServlet {

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
            out.println("<title>Servlet PagoEdit</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet PagoEdit at " + request.getContextPath() + "</h1>");
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
        String  fecha     = TFecha.convertirFecha(request.getParameter("fecha"), TFecha.formatoVista, TFecha.formatoBD);        
        
        Integer tipo      = Parser.parseInt(request.getParameter("tipo"));
        Integer id_cuenta = Parser.parseInt(request.getParameter("id_cuenta"));
        Float liqEfeMnt   = Parser.parseFloat(request.getParameter("liqEfeMnt"));
        Float liqChkMnt   = Parser.parseFloat(request.getParameter("liqChkMnt"));
        String liqChkBan  = request.getParameter("liqChkBan");
        String liqChkNum  = request.getParameter("liqChkNum");
        Float liqTraMnt   = Parser.parseFloat(request.getParameter("liqTraMnt"));
        String liqTraNum  = request.getParameter("liqTraNum");
        
        
        JsonRespuesta jr    = new JsonRespuesta();
        TCuenta tc          = new TCuenta();
        TCuenta_detalle tcd = new TCuenta_detalle();
        TPago tp            = new TPago();
        Pago p;
        Cuenta cuenta;
        Cuenta_detalle cd ;
        Integer id_pago;
        try{            
            
            cuenta = tc.getById(id_cuenta);
            
            if(cuenta==null) throw new BaseException("ERROR","Debe indicar la cuenta a ajustar");
            
            p = new Pago();
            p.setId_cuenta(cuenta.getId());
            p.setFecha(fecha);
            p.setFecha_creacion(TFecha.ahora(TFecha.formatoBD_Hora));
            p.setEfectivo(liqEfeMnt);
            
            p.setCheque_mnt(liqChkMnt);
            p.setCheque_ban(liqChkBan);
            p.setCheque_num(liqChkNum);
            
            p.setTransf_mnt(liqTraMnt);
            p.setTransf_num(liqTraNum);
            id_pago = tp.alta(p);
            if(id_pago==0) throw new BaseException("ERROR","Ocurri&oacute; un error al guardar el pago. Intentelo nuevamente.");
            
            cd = new Cuenta_detalle();
            cd.setId_cuenta(cuenta.getId());
            cd.setFecha(fecha);
            cd.setFecha_creacion(TFecha.ahora(TFecha.formatoBD));
            cd.setConcepto(String.format("Pago %s",request.getParameter("fecha")));
            cd.setId_concepto(OptionsCfg.CONCEPTO_PAGO);
            cd.setId_referencia(id_pago);
            Float total = liqEfeMnt + liqChkMnt + liqTraMnt;
            cd.setHaber(total);
            
            cuenta.setFecha_liquidacion(fecha);
            tc.actualizar(cuenta);
            int id = tcd.alta(cd);
            if(id!=0){
                jr.setResult("OK");
                jr.setRecord(cd);
            } else {
                throw new BaseException("ERROR","Ocurr&oacute; un error al aplicar el pago");
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
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}

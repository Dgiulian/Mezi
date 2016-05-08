/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Contrato;

import bd.Contrato;
import bd.Contrato_documento;
import bd.Contrato_gasto;
import bd.Contrato_valor;
import bd.Cuenta;
import bd.Cuenta_detalle;
import bd.Propiedad;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import transaccion.TAuditoria;
import transaccion.TContrato;
import transaccion.TContrato_documento;
import transaccion.TContrato_gasto;
import transaccion.TContrato_valor;
import transaccion.TCuenta;
import transaccion.TCuenta_detalle;
import transaccion.TInquilino;
import transaccion.TPropiedad;
import transaccion.TPropietario;
import utils.BaseException;
import utils.JsonRespuesta;
import utils.OptionsCfg;
import utils.Parser;

/**
 *
 * @author Diego
 */
public class ContratoDel extends HttpServlet {

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
            out.println("<title>Servlet ContratoDel</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ContratoDel at " + request.getContextPath() + "</h1>");
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
        PrintWriter         out = response.getWriter();
        JsonRespuesta       jr  = new JsonRespuesta();
        TContrato           tc  = new TContrato();
        TPropiedad          tp  = new TPropiedad();
        TContrato_valor     tv  = new TContrato_valor();
        TContrato_documento td  = new TContrato_documento();
        TContrato_gasto     tg  = new TContrato_gasto();
        TCuenta_detalle     tcd = new TCuenta_detalle();
        TCuenta             tcu = new TCuenta();
        HashMap<String,String> mapFiltro = new HashMap<String,String>();
        HashMap<String,String> filtroCuenta = new HashMap<String,String>();
        try {           
           Integer id = Parser.parseInt(request.getParameter("id"));
           Contrato contrato = tc.getById(id);
           if (contrato==null) throw new BaseException("ERROR","No existe el registro");
           mapFiltro.put("id_contrato",contrato.getId().toString());

           
           boolean baja = tc.baja(contrato);
           if ( !baja)throw new BaseException("ERROR","Ocurrio un error al eliminar el registro");
                Propiedad propiedad = tp.getById(contrato.getId_propiedad());
                
                List<Cuenta> listaCuenta          = tcu.getListFiltro(mapFiltro);
                List<Contrato_valor>     lstValor = tv.getListFiltro(mapFiltro);
                List<Contrato_documento> lstDocum = td.getListFiltro(mapFiltro);
                List<Contrato_gasto>    lstGastos = tg.getListFiltro(mapFiltro);
                if(propiedad!=null){
                    propiedad.setId_estado(OptionsCfg.PROPIEDAD_DISPONIBLE);
                    tp.actualizar(propiedad);
                }
                
                for(Cuenta cuenta: listaCuenta) {  
                    filtroCuenta.put("id_cuenta",cuenta.getId().toString());
                    for(Cuenta_detalle cd:tcd.getListFiltro(filtroCuenta)){
                        tcd.baja(cd);
                    }
                    tcu.baja(cuenta);
                    
                }
                for(Contrato_valor valor:lstValor)     tv.baja(valor);
                for(Contrato_documento docum:lstDocum) td.baja(docum);
                for(Contrato_gasto gasto:lstGastos)    tg.baja(gasto);
                new TInquilino().baja(contrato.getId_inquilino());
                new TPropietario().baja(contrato.getId_propietario());
                
                jr.setResult("OK");
                Integer id_usuario = 0;
                Integer id_tipo_usuario = 0;
                HttpSession session = request.getSession();
                id_usuario = (Integer) session.getAttribute("id_usuario");
                id_tipo_usuario = (Integer) session.getAttribute("id_tipo_usuario");
                TAuditoria.guardar(id_usuario,id_tipo_usuario,OptionsCfg.MODULO_CONTRATO,OptionsCfg.ACCION_BAJA,contrato.getId(),tc.auditar(contrato));
           
        }  catch (BaseException ex) {
            jr.setResult(ex.getResult());
            jr.setMessage(ex.getMessage());            
        }
        finally {
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

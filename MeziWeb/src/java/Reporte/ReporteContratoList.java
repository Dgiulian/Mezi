/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Reporte;

import Contrato.*;
import com.google.gson.Gson;
import bd.Contrato;
import bd.Cuenta_detalle;
import bd.Inquilino;
import bd.Propiedad;
import bd.Propietario;
import bd.Vendedor;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.joda.time.LocalDate;
import transaccion.TContrato;
import transaccion.TCuenta_detalle;
import transaccion.TInquilino;
import transaccion.TPropiedad;
import transaccion.TPropietario;
import transaccion.TVendedor;
import utils.JsonRespuesta;
import utils.OptionsCfg;
import utils.OptionsCfg.Option;
import utils.Parser;
import utils.TFecha;

/**
 *
 * @author Diego
 */
public class ReporteContratoList extends HttpServlet {
    private Map<Integer,Propietario> mapPropietarios;
    private Map<Integer,Inquilino>   mapInquilinos;
    private Map<Integer,Propiedad>   mapPropiedades;
    private Map<Integer,Option>      mapEstados;
    private Map<Integer,Vendedor>    mapVendedores;
    
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
        String pagNro = request.getParameter("pagNro");
        String codigo = request.getParameter("codigo");
        Integer id_cliente = Parser.parseInt(request.getParameter("id_cliente"));
        Integer id_propiedad = Parser.parseInt(request.getParameter("id_propiedad"));
        Integer id_tipo = Parser.parseInt(request.getParameter("id_tipo"));
        Integer id_estado = Parser.parseInt(request.getParameter("id_estado"));
        String strAlta_desde = request.getParameter("alta_desde");
        String strAlta_hasta = request.getParameter("alta_hasta");
        String strVencimiento_desde = request.getParameter("vencimiento_desde");
        String strVencimiento_hasta = request.getParameter("vencimiento_hasta");
        LocalDate alta_desde = null;
        LocalDate alta_hasta = null;
        LocalDate vencimiento_desde = null;
        LocalDate vencimiento_hasta = null;
        Integer page    = Parser.parseInt(pagNro);        
        try {
            JsonRespuesta jr = new JsonRespuesta();           
           
            List<Contrato> lista ;
            mapPropietarios = new TPropietario().getMap();
            mapInquilinos   = new TInquilino().getMap();
            mapPropiedades  = new TPropiedad().getMap();
            mapEstados      = OptionsCfg.getMap(OptionsCfg.getEstadosContrato());
            mapVendedores   = new TVendedor().getMap();
            
            TContrato tp = new TContrato();
            HashMap<String,String> mapFiltro = new HashMap<String,String> ();                        
            if(id_cliente!=0) {
                
                if(id_tipo.equals(OptionsCfg.CLIENTE_TIPO_PROPIETARIO)) mapFiltro.put("id_propietario", id_cliente.toString());
                else if (id_tipo.equals(OptionsCfg.CLIENTE_TIPO_INQUILINO)) mapFiltro.put("id_inquilino", id_cliente.toString());
            }
            
            if(id_propiedad!=0) mapFiltro.put("id_propiedad",id_propiedad.toString());
            if(id_estado!=0) mapFiltro.put("id_estado",id_estado.toString());
            
            lista =  tp.getListFiltro(mapFiltro);
            //lista = tp.getList();
            
            if(strAlta_desde!=null && !("").equals(strAlta_desde)) alta_desde = new LocalDate(TFecha.formatearFechaVistaBd(strAlta_desde));
            if(strAlta_hasta!=null && !("").equals(strAlta_hasta)) alta_hasta = new LocalDate(TFecha.formatearFechaVistaBd(strAlta_hasta));
            if(strVencimiento_desde!=null && !("").equals(strVencimiento_desde)) vencimiento_desde = new LocalDate(TFecha.formatearFechaVistaBd(strVencimiento_desde));
            if(strVencimiento_hasta!=null && !("").equals(strVencimiento_hasta)) vencimiento_hasta = new LocalDate(TFecha.formatearFechaVistaBd(strVencimiento_hasta));
            
            if (lista != null) {                
                List<Contrato> listaDet = new ArrayList<Contrato>();
                for (Contrato c:lista){
                    LocalDate fecha_creacion = new LocalDate(TFecha.formatearFecha(c.getFecha_creacion(),TFecha.formatoBD_Hora,TFecha.formatoBD));
                    LocalDate fecha_fin = new LocalDate(c.getFecha_fin());
                    
                    if(alta_desde!=null && fecha_creacion.isBefore(alta_desde)) continue;
                    if(alta_hasta!=null && fecha_creacion.isAfter(alta_hasta)) continue;
                    if(vencimiento_desde!=null && fecha_fin.isBefore(vencimiento_desde)) continue;
                    if(vencimiento_hasta!=null && fecha_fin.isAfter(vencimiento_hasta)) continue;
                    
                    listaDet.add(new ContratoDet(c));
                }
                jr.setTotalRecordCount(listaDet.size());
                jr.setResult("OK");
                jr.setRecords(listaDet);
            } else {
                jr.setResult("ERROR");
                jr.setTotalRecordCount(0);                
            }            
            
            
            String jsonResult = new Gson().toJson(jr);

            out.print(jsonResult);
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
    
    private class ContratoDet extends Contrato{
        String direccion   = "";
        String inquilino   = "";
        String propietario = "";
        String detalles    = "";
        String precio      = "";
        String tipo_contrato   = "";
        String estado_contrato = "";
        String vendedor        = "";
        Float  saldo           = 0f;        
        
        ContratoDet(Contrato contrato) {
            super(contrato);
            Propiedad propiedad = mapPropiedades.get(contrato.getId_propiedad());
            Inquilino inq = mapInquilinos.get(contrato.getId_inquilino());
            Propietario pro = mapPropietarios.get(contrato.getId_propietario());
            Vendedor ven = mapVendedores.get(contrato.getId_vendedor());
            
            if(propiedad!=null) direccion = propiedad.getDireccion();
            if(inq!=null) inquilino   = inq.getCarpeta() + " - " + inq.getApellido() + ", " + inq.getNombre();
            if(pro!=null) propietario = pro.getCarpeta() + " - " + pro.getApellido() + ", " + pro.getNombre();
            Option optEstado = mapEstados.get(contrato.getId_estado());
            
            estado_contrato = (optEstado!=null)?optEstado.getDescripcion():"";
            tipo_contrato = "Alquiler";
            if(ven!=null) vendedor = ven.getApellido() + ", " + ven.getNombre();
            //Propietario po = mapPropietarios.get(contrato.getId_propiedad());
        }        
    }
}

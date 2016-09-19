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
import bd.Parametro;
import bd.Propiedad;
import bd.Vendedor;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import test.ContratoTest;
import test.Contrato_documentoTest;
import test.Contrato_gastoTest;
import test.Contrato_valorTest;
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
public class ContratoEdit extends HttpServlet {


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
        Integer id_propiedad = Parser.parseInt(request.getParameter("id_propiedad"));
        Integer id_cliente   = Parser.parseInt(request.getParameter("id_cliente"));
        Integer id_vendedor  = Parser.parseInt(request.getParameter("id_vendedor"));
        String entorno       = request.getParameter("entorno");
        
        Contrato contrato = new Contrato();
        contrato.setComision_mensual_propietario(10f);
        contrato.setPunitorio_monto(0.017f);
        contrato.setPunitorio_desde(10);
        
        contrato.setComision_mensual_propietario(10f);
        List<Contrato_valor>     contrato_valor     = new ArrayList<Contrato_valor>();
        List<Contrato_gasto>     contrato_gasto     = new ArrayList<Contrato_gasto>();
        List<Contrato_documento> contrato_documento = new ArrayList<Contrato_documento>();

        Parametro parametro = new TParametro().getByCodigo("entorno");
        if ((entorno!=null && entorno.equalsIgnoreCase("test")) || 
           (parametro!=null && parametro.getValor().equalsIgnoreCase("test"))) {
            contrato = new ContratoTest();
            contrato_valor = Contrato_valorTest.getList();
            contrato_documento = Contrato_documentoTest.getList();
            contrato_gasto = Contrato_gastoTest.getList();
            
            id_propiedad = id_propiedad!=0?id_propiedad:contrato.getId_propiedad();
            id_cliente   = id_cliente!=0?id_cliente:contrato.getId_inquilino();
            id_vendedor  = id_vendedor!=0?id_vendedor:contrato.getId_vendedor();
        }
        request.setAttribute("contrato",contrato);
        request.setAttribute("contrato_valor",contrato_valor);
        request.setAttribute("contrato_documento",contrato_documento);
        request.setAttribute("contrato_gasto",contrato_gasto);
         
        Propiedad propiedad = new TPropiedad().getById(id_propiedad);
        if(propiedad!=null) 
            request.setAttribute("propiedad", propiedad);
        
        Cliente inquilino = new TCliente().getById(id_cliente);
        if(inquilino!=null)
            request.setAttribute("inquilino",inquilino);
        
        
        Vendedor vendedor = new TVendedor().getById(id_vendedor);
        if (vendedor!=null)
            request.setAttribute("vendedor",vendedor);
        
        if (parametro!=null && parametro.getValor().equalsIgnoreCase("test")) {
            request.getRequestDispatcher("contrato_edit.jsp").forward(request, response);
//            request.getRequestDispatcher("contrato_edit_test.jsp").forward(request, response);
        } else 
            request.getRequestDispatcher("contrato_edit.jsp").forward(request, response);
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
        Integer  id_inquilino                  = Parser.parseInt(request.getParameter("id_inquilino"));
        Integer  id_propiedad                  = Parser.parseInt(request.getParameter("id_propiedad"));
        Integer  numero                        = Parser.parseInt(request.getParameter("numero"));
        String   fecha_inicio                  = TFecha.formatearFechaVistaBd(request.getParameter("fecha_inicio"));
        Integer  meses                         = Parser.parseInt(request.getParameter("meses"));
        String   fecha_fin                     = TFecha.formatearFechaVistaBd(request.getParameter("fecha_fin"));
        Float    punitorio_monto               = Parser.parseFloat(request.getParameter("punitorio_monto"));
        Integer  punitorio_desde               = Parser.parseInt(request.getParameter("punitorio_desde"));      
        Integer  id_vendedor                   = Parser.parseInt(request.getParameter("id_vendedor"));
        Float    comision_vendedor             = Parser.parseFloat(request.getParameter("comision_vendedor"));
        Float    gastos_escribania_inquilino   = Parser.parseFloat(request.getParameter("gastos_escribania_inquilino"));
        Float    gastos_sellado_inquilino      = Parser.parseFloat(request.getParameter("gastos_sellado_inquilino"));
        Float    comision_monto_inquilino      = Parser.parseFloat(request.getParameter("comision_monto_inquilino"));
        String   comision_desde_inquilino      = TFecha.formatearFechaVistaBd(request.getParameter("comision_desde_inquilino"));
        Integer  comision_cuotas_inquilino     = Parser.parseInt(request.getParameter("comision_cuotas_inquilino"));
        Float    comision_monto_propietario    = Parser.parseFloat(request.getParameter("comision_monto_propietario"));
        String   comision_desde_propietario    = TFecha.formatearFechaVistaBd(request.getParameter("comision_desde_propietario"));
        Integer  comision_cuotas_propietario   = Parser.parseInt(request.getParameter("comision_cuotas_propietario"));
        Float    comision_mensual_propietario  = Parser.parseFloat(request.getParameter("comision_mensual_propietario"));
        Float    gastos_escribania_propietario = Parser.parseFloat(request.getParameter("gastos_escribania_propietario"));
        Float    gastos_sellado_propietario    = Parser.parseFloat(request.getParameter("gastos_sellado_propietario"));
        Float    deposito_monto                = Parser.parseFloat(request.getParameter("deposito_monto"));
        String   deposito_desde                = TFecha.formatearFechaVistaBd(request.getParameter("deposito_desde"));
        Integer  deposito_cuotas               = Parser.parseInt(request.getParameter("deposito_cuotas"));
        
        String  garante_1_dni         = request.getParameter("garante_1_dni");
        String  garante_1_nombre      = request.getParameter("garante_1_nombre");
        String  garante_1_telefono    = request.getParameter("garante_1_telefono");
        Integer garante_1_id_garantia = Parser.parseInt(request.getParameter("garante_1_id_garantia"));
        String  garante_2_dni         = request.getParameter("garante_2_dni");
        String  garante_2_nombre      = request.getParameter("garante_2_nombre");
        String  garante_2_telefono    = request.getParameter("garante_2_telefono");
        Integer garante_2_id_garantia = Parser.parseInt(request.getParameter("garante_2_id_garantia"));
        String  garante_3_dni         = request.getParameter("garante_3_dni");
        String  garante_3_nombre      = request.getParameter("garante_3_nombre");
        String  garante_3_telefono    = request.getParameter("garante_3_telefono");
        Integer garante_3_id_garantia = Parser.parseInt(request.getParameter("garante_3_id_garantia"));
        
        
        Integer  asegura_renta                 = request.getParameter("asegura_renta")!=null&&!request.getParameter("asegura_renta").equals("")?1:0;
        String   observaciones                 = request.getParameter("observaciones");
        String[] arr_valor_desde               = request.getParameterValues("valor_desde");
        String[] arr_valor_hasta               = request.getParameterValues("valor_hasta");
        String[] arr_valor_monto               = request.getParameterValues("valor_monto");
        
        String[] arr_docum_desde               = request.getParameterValues("documento_desde");
        String[] arr_docum_hasta               = request.getParameterValues("documento_hasta");
        String[] arr_docum_monto               = request.getParameterValues("documento_monto");
        
        String[] arr_gasto_concepto            = request.getParameterValues("gasto_concepto");
        String[] arr_gasto_aplica              = request.getParameterValues("gasto_aplica");
        String[] arr_gasto_importe             = request.getParameterValues("gasto_importe");
        String[] arr_gasto_cuota               = request.getParameterValues("gasto_cuota");
        if (arr_valor_desde == null)    arr_valor_desde    = new String[0];
        if (arr_valor_hasta == null)    arr_valor_hasta    = new String[0];
        if (arr_valor_monto == null)    arr_valor_monto    = new String[0];
        if (arr_docum_desde == null)    arr_docum_desde    = new String[0];
        if (arr_docum_hasta == null)    arr_docum_hasta    = new String[0];
        if (arr_docum_monto == null)    arr_docum_monto    = new String[0];
        if (arr_gasto_concepto == null) arr_gasto_concepto = new String[0];
        if (arr_gasto_aplica == null)   arr_gasto_aplica   = new String[0];
        if (arr_gasto_importe == null)  arr_gasto_importe  = new String[0];
        if (arr_gasto_cuota == null)    arr_gasto_cuota    = new String[0];
        
        ArrayList<Contrato_valor>     lstValor      =  new ArrayList<Contrato_valor>();
        ArrayList<Contrato_documento> lstDocum      =  new ArrayList<Contrato_documento>();
        ArrayList<Contrato_gasto>     lstGastoInq   =  new ArrayList<Contrato_gasto>();
        ArrayList<Contrato_gasto>     lstGastoProp  =  new ArrayList<Contrato_gasto>();
        
        TContrato_valor     tv = new TContrato_valor();
        TContrato_documento td = new TContrato_documento();
        TContrato_gasto     tg = new TContrato_gasto();
        TPropiedad          tp = new TPropiedad();
        TInquilino          ti = new TInquilino();
        TPropietario     tprop = new TPropietario();
        
       try{
           Integer id_contrato = 0;
           Propiedad propiedad = tp.getById(id_propiedad);
           if(propiedad==null) throw new BaseException("ERROR","Seleccione la propiedad");
          
           Cliente cliente = new TCliente().getById(id_inquilino);
           if(cliente==null) throw new BaseException("ERROR","Seleccione el inquilino");
           Vendedor vendedor = new TVendedor().getById(id_vendedor);
           if(vendedor ==null) throw new BaseException("ERROR","Seleccione el vendedor");
           
           if(numero==0) throw new BaseException("ERROR","Ingrese el n&uacute;mero de contrato");
           if (fecha_inicio==null) throw new BaseException("ERROR","Ingrese la fecha de inicio del contrato");
           if (fecha_fin ==null) throw new BaseException("ERROR","Ingrese la fecha de fin del contrato");
           
           if (deposito_desde==null || deposito_desde.equals("")) deposito_desde= fecha_inicio;
           if (comision_desde_inquilino==null || comision_desde_inquilino.equals("")) comision_desde_inquilino= fecha_inicio;
           if (comision_desde_propietario==null || comision_desde_propietario.equals("")) comision_desde_propietario= fecha_inicio;
           
           
           //Controlar:
           // Que no exista otro contrato con el mismo número
           // Si las fechas no estan seteadas, no setear el campo?
           // Controlar los campos obligatorios
           
           Contrato contrato = new Contrato();
           contrato.setId_inquilino(id_inquilino);
           contrato.setId_propiedad(id_propiedad);
           contrato.setId_propietario(propiedad.getId_propietario());
           contrato.setNumero(numero);
           contrato.setFecha_inicio(fecha_inicio);
           contrato.setMeses(meses);
           contrato.setFecha_fin(fecha_fin);
           contrato.setPunitorio_monto(punitorio_monto);
           contrato.setPunitorio_desde(punitorio_desde);
           contrato.setId_vendedor(id_vendedor);
           contrato.setComision_vendedor(comision_vendedor);
           contrato.setGastos_escribania_inquilino(gastos_escribania_inquilino);
           contrato.setGastos_sellado_inquilino(gastos_sellado_inquilino);
           contrato.setComision_monto_inquilino(comision_monto_inquilino);
           
           contrato.setComision_desde_inquilino(comision_desde_inquilino);
           contrato.setComision_cuotas_inquilino(comision_cuotas_inquilino);
           contrato.setComision_monto_propietario(comision_monto_propietario);
           
           contrato.setComision_desde_propietario(comision_desde_propietario);
           contrato.setComision_cuotas_propietario(comision_cuotas_propietario);
           contrato.setComision_mensual_propietario(comision_mensual_propietario);
           contrato.setGastos_escribania_propietario(gastos_escribania_propietario);
           contrato.setGastos_sellado_propietario(gastos_sellado_propietario);
           contrato.setDeposito_monto(deposito_monto);
           contrato.setDeposito_desde(deposito_desde);
           contrato.setDeposito_cuotas(deposito_cuotas);
           contrato.setAsegura_renta(asegura_renta);
           
           contrato.setGarante_1_dni(garante_1_dni);
           contrato.setGarante_1_nombre(garante_1_nombre);
           contrato.setGarante_1_telefono(garante_1_telefono);
           contrato.setGarante_1_id_garantia(garante_1_id_garantia);
           
           contrato.setGarante_2_dni(garante_2_dni);
           contrato.setGarante_2_nombre(garante_2_nombre);
           contrato.setGarante_2_telefono(garante_2_telefono);
           contrato.setGarante_2_id_garantia(garante_2_id_garantia);
           
           contrato.setGarante_3_dni(garante_3_dni);
           contrato.setGarante_3_nombre(garante_3_nombre);
           contrato.setGarante_3_telefono(garante_3_telefono);
           contrato.setGarante_3_id_garantia(garante_3_id_garantia);
           contrato.setObservaciones(observaciones);
           contrato.setId_estado(OptionsCfg.CONTRATO_ESTADO_ACTIVO);
           id_contrato = new TContrato().alta(contrato);
           if(id_contrato!=0){ // Si se guardo bien el contrato
               if(arr_valor_desde!=null){
                for (int i =0;i<arr_valor_desde.length;i++){
                    String valor_desde = TFecha.formatearFechaVistaBd(arr_valor_desde[i]);
                    String valor_hasta = TFecha.formatearFechaVistaBd(arr_valor_hasta[i]);
                    Float valor_monto  = Parser.parseFloat(arr_valor_monto[i]);
                    if(valor_desde==null || valor_hasta==null) continue;
                    if(valor_monto==0) continue;

                    Contrato_valor valor = new Contrato_valor();
                    valor.setId_contrato(id_contrato);
                    valor.setDesde(valor_desde);
                    valor.setHasta(valor_hasta);
                    valor.setMonto(valor_monto);
                    lstValor.add(valor);
 //                   new TContrato_valor().alta(valor);
                }
               }
               if(arr_docum_desde!=null && arr_docum_hasta!=null) {
                for (int i =0;i<arr_docum_desde.length;i++){
                    String docum_desde = TFecha.formatearFechaVistaBd(arr_docum_desde[i]);
                    String docum_hasta = TFecha.formatearFechaVistaBd(arr_docum_hasta[i]);
                    Float docum_monto  = Parser.parseFloat(arr_docum_monto[i]);
                    if(docum_desde==null || docum_hasta==null) continue;
                    if(docum_monto==0) continue;
                    Contrato_documento docum = new Contrato_documento();
                    docum.setId_contrato(id_contrato);
                    docum.setDesde(docum_desde);
                    docum.setHasta(docum_hasta);
                    docum.setMonto(docum_monto);             
                    lstDocum.add(docum);
 //                   new TContrato_documento().alta(docum);
                }
               }
               if(arr_gasto_concepto!=null) {
                for (int i =0;i<arr_gasto_concepto.length;i++){
                     String gasto_concepto = arr_gasto_concepto[i];
                     Integer gasto_aplica  = Parser.parseInt(arr_gasto_aplica[i]);
                     Float gasto_importe   = Parser.parseFloat(arr_gasto_importe[i]);                   
                     Integer gasto_cuota   = Parser.parseInt(arr_gasto_cuota[i]);                   
                     Contrato_gasto gasto  =  new Contrato_gasto();
                     gasto.setConcepto(gasto_concepto);
                     gasto.setId_contrato(id_contrato);
                     gasto.setId_aplica(gasto_aplica);
                     gasto.setImporte(gasto_importe);
                     gasto.setCuotas(gasto_cuota!=0?gasto_cuota:1);
                     if (gasto_aplica==1) 
                         lstGastoInq.add(gasto);
                     else if(gasto_aplica==2) lstGastoProp.add(gasto);
 //                    new TContrato_gasto().alta(gasto);
                }
               }
               
               
               ti.alta(id_inquilino);
               tprop.alta(propiedad.getId_propietario());
               propiedad.setId_estado(OptionsCfg.PROPIEDAD_ALQUILADA);
               tp.actualizar(propiedad);
               
               for(Contrato_valor valor:lstValor)     tv.alta(valor);
               for(Contrato_documento docum:lstDocum) td.alta(docum);
               for(Contrato_gasto gasto:lstGastoInq)  tg.alta(gasto);
               for(Contrato_gasto gasto:lstGastoProp) tg.alta(gasto);
               
               Cuenta cc_inquilino_o = new Cuenta();
               
               cc_inquilino_o.setId_cliente(contrato.getId_inquilino());               
               cc_inquilino_o.setId_contrato(id_contrato);
               cc_inquilino_o.setId_usuario(0);
               cc_inquilino_o.setFecha_creacion(TFecha.ahora(TFecha.formatoBD+ " " + TFecha.formatoHora));
               cc_inquilino_o.setId_tipo(OptionsCfg.CUENTA_OFICIAL);
               cc_inquilino_o.setId_tipo_cliente(OptionsCfg.CLIENTE_TIPO_INQUILINO);
               
               Cuenta cc_inquilino_no = new Cuenta(cc_inquilino_o);
               cc_inquilino_no.setId_tipo(OptionsCfg.CUENTA_NO_OFICIAL);
               cc_inquilino_no.setId_tipo_cliente(OptionsCfg.CLIENTE_TIPO_INQUILINO);
               
               Cuenta cc_propietario_o = new Cuenta();
               cc_propietario_o.setId_cliente(contrato.getId_propietario());
               cc_propietario_o.setId_contrato(id_contrato);
               cc_propietario_o.setFecha_creacion(TFecha.ahora(TFecha.formatoBD + " " + TFecha.formatoHora));
               cc_propietario_o.setId_usuario(0);
               cc_propietario_o.setId_tipo(OptionsCfg.CUENTA_OFICIAL);
               cc_propietario_o.setId_tipo_cliente(OptionsCfg.CLIENTE_TIPO_PROPIETARIO);
               Cuenta cc_propietario_no = new Cuenta(cc_propietario_o);
               cc_propietario_no.setId_tipo(OptionsCfg.CUENTA_NO_OFICIAL);
               cc_propietario_no.setId_tipo_cliente(OptionsCfg.CLIENTE_TIPO_PROPIETARIO);
               
               TCuenta tc = new TCuenta();
               TCuenta_detalle tcd = new TCuenta_detalle();
               int id_cc_inquilino_o    = tc.alta(cc_inquilino_o);
               int id_cc_inquilino_no   = tc.alta(cc_inquilino_no);
               int id_cc_propietario_o  = tc.alta(cc_propietario_o);
               int id_cc_propietario_no = tc.alta(cc_propietario_no);
               
               List<Cuenta_detalle> detalleInquilino_o    = tcd.detalleInquilino(contrato, lstValor, lstGastoInq);
               List<Cuenta_detalle> detalleInquilino_no   = tcd.detalleInquilino(lstDocum);
               
               List<Cuenta_detalle> detallePropietario_o  = tcd.detallePropietario(contrato, lstValor, lstGastoProp);
               List<Cuenta_detalle> detallePropietario_no = tcd.detallePropietario(lstDocum);
               
               if(id_cc_inquilino_o!=0) {
                for(Cuenta_detalle cd:detalleInquilino_o){
                    cd.setId_cuenta(id_cc_inquilino_o);
                    cd.setFecha_creacion(TFecha.ahora(TFecha.formatoBD + " " + TFecha.formatoHora));                    
                    tcd.alta(cd);
                }
               }
               if(id_cc_inquilino_no!=0) {
                for(Cuenta_detalle cd:detalleInquilino_no){
                    cd.setId_cuenta(id_cc_inquilino_no);
                    cd.setFecha_creacion(TFecha.ahora(TFecha.formatoBD + " " + TFecha.formatoHora));                    
                    tcd.alta(cd);
                }
               }
               
               if(id_cc_propietario_o!=0){
                for(Cuenta_detalle cd:detallePropietario_o){
                    cd.setId_cuenta(id_cc_propietario_o);
                    cd.setFecha_creacion(TFecha.ahora(TFecha.formatoBD + " " + TFecha.formatoHora));                  
                    tcd.alta(cd);
                }
               }
               if(id_cc_propietario_no!=0){
                for(Cuenta_detalle cd:detallePropietario_no){
                    cd.setId_cuenta(id_cc_propietario_no);
                    cd.setFecha_creacion(TFecha.ahora(TFecha.formatoBD + " " + TFecha.formatoHora));                  
                    tcd.alta(cd);
                }
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

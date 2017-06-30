
<%@page import="bd.Cuenta_detalle"%>
<%@page import="bd.Propietario"%>
<%@page import="utils.OptionsCfg.Option"%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="bd.Contrato_gasto"%>
<%@page import="bd.Contrato_documento"%>
<%@page import="bd.Contrato_valor"%>
<%@page import="utils.TFecha"%>
<%@page import="bd.Contrato"%>
<%@page import="bd.Cliente"%>
<%@page import="transaccion.TLocalidad"%>
<%@page import="transaccion.TBarrio"%>
<%@page import="bd.Localidad"%>
<%@page import="bd.Barrio"%>
<%@page import="transaccion.TVendedor"%>
<%@page import="bd.Vendedor"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="bd.Propiedad"%>
<%@page import="java.util.HashMap"%>
<%@page import="transaccion.TPropiedad"%>
<%
    Contrato contrato = (Contrato) request.getAttribute("contrato");
    Propiedad propiedad = (Propiedad) request.getAttribute("propiedad");
    Propietario propietario = (Propietario) request.getAttribute("propietario");
    ArrayList<Cuenta_detalle> cuenta_detalle_oficial = (ArrayList<Cuenta_detalle>) request.getAttribute("cuenta_inquilino_oficial");
    ArrayList<Cuenta_detalle> cuenta_detalle_no_oficial = (ArrayList<Cuenta_detalle>) request.getAttribute("cuenta_inquilino_no_oficial");
    boolean con_propiedad = true;
    boolean con_cliente = true;
    boolean con_vendedor = true;
    
    TPropiedad tp = new TPropiedad();
   
    List<Vendedor> lstVendedores = new TVendedor().getListActivo();
    

    if(propiedad==null){
        propiedad = new Propiedad();
        con_propiedad = false;
    }
    
    if (propietario==null){
        propietario = new Propietario();        
    }
    Cliente cliente = (Cliente) request.getAttribute("inquilino");
    if (cliente==null){
        cliente = new Cliente();
        con_cliente = false;
    }
   
    Barrio barrio = new TBarrio().getById(propiedad.getId_barrio());
    if(barrio==null){
        barrio = new Barrio();
    }

    Localidad localidad = new TLocalidad().getById(propiedad.getId_localidad());
    if(localidad==null){
        localidad = new Localidad();
    }
    
    Vendedor vendedor = (Vendedor) request.getAttribute("vendedor");
    if (vendedor==null){
        vendedor = new Vendedor();
        con_vendedor = false;
    }
    List<Contrato_valor> lstValor = (List<Contrato_valor>) request.getAttribute("lstValor");
    List<Contrato_documento> lstDocum= (List<Contrato_documento>) request.getAttribute("lstDocum");
    List<Contrato_gasto> lstContrato_gasto = (List<Contrato_gasto>) request.getAttribute("lstGasto");
    
    if(lstValor==null) lstValor = new ArrayList<Contrato_valor>();
    if(lstDocum==null) lstDocum = new ArrayList<Contrato_documento>();
    if(lstContrato_gasto==null) lstContrato_gasto = new ArrayList<Contrato_gasto>();
    if(lstVendedores==null) lstVendedores = new ArrayList<Vendedor>();
    if( cuenta_detalle_oficial ==null) cuenta_detalle_oficial = new ArrayList<Cuenta_detalle>();
    if( cuenta_detalle_no_oficial ==null) cuenta_detalle_oficial = new ArrayList<Cuenta_detalle>();
    
    DecimalFormat df = new DecimalFormat("#.#####");
    String txtBoton;
    switch(contrato.getId_estado()){
        case OptionsCfg.CONTRATO_ESTADO_INICIAL: txtBoton = "Activar"; break;
        case OptionsCfg.CONTRATO_ESTADO_ACTIVO:  txtBoton = "Finalizar"; break;
        case OptionsCfg.CONTRATO_ESTADO_ENTREGA: txtBoton = "Finalizar"; break;
        default: txtBoton = "Guardar";
    }
    String contrato_estado = "";
    HashMap<Integer,OptionsCfg.Option> mapEstados = OptionsCfg.getMap(OptionsCfg.getEstadosContrato());
    contrato_estado = mapEstados.get(contrato.getId_estado()).getDescripcion();
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <%@include file="tpl_head.jsp" %>
    <style>
        hr {
            /*border: 1px;*/
            border-top: 2px solid #eee;
            width: 100%;
        }
    </style>
</head>

<body>
    <!-- start: Header -->
    <%@include file="tpl_header.jsp"%>
		<div class="container">
		<div class="row">

			<!-- start: Main Menu -->
			<%@include file="tpl_sidebar.jsp"%>
			<!-- end: Main Menu -->

			<!-- start: Content -->
			<div id="content" class="col-lg-10 col-sm-11 ">
                            <form action="<%=PathCfg.CONTRATO_ESTADO%>" method="POST">
                                <input type="hidden" id="id_contrato" name="id_contrato" value="<%=contrato.getId()%>" >
                                <input type="hidden" id="id_estado" name="id_estado" value="<%=contrato.getId_estado()%>" >
                                <% if(contrato.getId_estado().equals(OptionsCfg.CONTRATO_ESTADO_INICIAL)) { %> <% } %>                            
			<div class="row">
				<div class="col-lg-12">
					<div class="box" id="rootwizard">
                                            <div class="box-header">
                                                <h2><i class="fa fa-edit"></i>Cambiar estado contrato de alquiler</h2>
                                                <ul id="tabs" class="nav nav-tabs" data-tabs="tabs">
                                                    <li class="active" ><a href="#tabContrato" data-toggle="tab">Estado</a></li>
                                                    <li class="" ><a href="#tab1" data-toggle="tab">Inquilino</a></li>
                                                    <li><a href="#tabProp" data-toggle="tab">Propiedad</a></li>
                                                    <li><a href="#tabBasicos" data-toggle="tab">B&aacute;sicos</a></li>
                                                    <% if(contrato.getId_estado().equals(OptionsCfg.CONTRATO_ESTADO_INICIAL)) { %>
                                                        <li><a href="#tabAdic" data-toggle="tab">Adicionales</a></li>
                                                        <li><a href="#tabGarantes" data-toggle="tab">Garantes</a></li>
                                                        <li><a href="#tabOtros" data-toggle="tab">Otros datos</a></li>
                                                    <% } else if(contrato.getId_estado().equals(OptionsCfg.CONTRATO_ESTADO_ACTIVO)){%>
                                                        <li><a href="#tabCuenta" data-toggle="tab">Cuenta</a></li>
                                                    <% } %>
                                                </ul>
                                            </div>
                                        <form role="form" method="POST" acttion="<%=PathCfg.CONTRATO_EDIT%>">
                                        <div  class="tab-content box-content">
                                            <div class="tab-pane row" id="tab1">
                                               <div class="col-lg-8">
                                                   <fieldset disabled>
                                                       <legend>Datos del inquilino</legend>
                                                       <div class="col-lg-12">
                                                        <div class="form-group row">
                                                                <div class="col-lg-2 nopadding">
                                                                    <div class="controls">
                                                                        <label class="control-label" for="id_inquilino">N&uacute;mero carpeta</label>
                                                                          <div class="input-group ">
                                                                            <input type="text" id="id_inquilino" name="id_inquilino" class="form-control" value="<%=cliente.getId()%>" >
                                                                          </div>
                                                                    </div>
                                                                </div>
                                                                <div class="col-lg-5 ">
                                                                    <div class="controls">
                                                                        <label class="control-label" for="nombre">Nombre</label>
                                                                          <div class="input-group  col-lg-12">
                                                                            <input type="text" id="nombre" name="nombre" class="form-control" value="<%=cliente.getNombre()%>" readonly>
                                                                          </div>
                                                                    </div>
                                                                </div>
                                                                <div class="col-lg-5">
                                                                    <div class="controls">
                                                                        <label class="control-label" for="apellido">Apellido</label>
                                                                          <div class="input-group  col-lg-12">
                                                                            <input type="text" id="apellido" name="apellido" class="form-control" value="<%=cliente.getApellido()%>" readonly>
                                                                          </div>
                                                                    </div>
                                                                </div>
                                                        
                                                           
                                                        </div><!-- row -->
                                                   </div>
                                                    <div class="col-lg-12">
                                                        
                                                        <div class="form-group row">
                                                            <div class="col-lg-4 nopadding">
                                                                <label class="control-label" for="dni">DNI</label>
                                                                <div class="controls">
                                                                    <div class="input-group">
                                                                      <input type="text" id="dni" name="dni" class="form-control" value="<%=cliente.getDni()%>"readonly>
                                                                    </div>
                                                                </div>
                                                            </div>

                                                            <div class="col-lg-4 ">
                                                                <label class="control-label" for="cuil">CUIL / CUIT</label>
                                                                <div class="controls">
                                                                    <div class="input-group">
                                                                      <input type="text" id="cuil" name="cuil" class="form-control" value="<%=cliente.getCuil()%>" readonly>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                            <%if (!con_cliente) {%>
                                                            <div class="col-lg-2 ">
                                                                <div class="controls">
                                                                    <label class="control-label" for="id_inquilino">&nbsp;</label>
                                                                      <div class="input-group ">
                                                                        <span class="btn btn-primary" data-toggle="modal" data-target="#mdlCliente">Seleccionar</span>
                                                                      </div>
                                                                </div>
                                                            </div>
                                                            <%}%>        
                                                        </div>
                                                    </div>
                                                </fieldset>
                                            </div>


                                        </div> <!-- tab1 -->

                                            <div class="tab-pane row " id="tabProp">
                                                <fieldset disabled>
                                                    <legend>Datos de la propiedad</legend>
                                                    <div class="col-lg-7">
                                                     <div class="col-lg-12" >
                                                         <input type="hidden" name="id_propiedad" id="id_propiedad" value="<%=propiedad.getId()%>">
                                                        <div class="form-group row">
                                                                <div class="col-lg-9 nopadding">
                                                                    <div class="controls">
                                                                        <label class="control-label" for="calle">Calle</label>
                                                                          <div class="input-group col-lg-12 ">
                                                                            <input type="text" id="calle" name="calle" class="form-control" value="<%=propiedad.getCalle()%>" readonly>
                                                                          </div>
                                                                    </div>
                                                                </div>
                                                                <div class="col-lg-1 nopadding">
                                                                    <div class="controls">
                                                                        <label class="control-label" for="numero">N&uacute;mero</label>
                                                                          <div class="input-group ">
                                                                            <input type="text" id="numero" name="numero" class="form-control" value="<%=propiedad.getNumero()%>"  readonly>
                                                                          </div>
                                                                    </div>
                                                                </div>
                                                                <div class="col-lg-1 nopadding">
                                                                    <div class="controls">
                                                                        <label class="control-label" for="piso">Piso</label>
                                                                          <div class="input-group ">
                                                                            <input type="text" id="piso" name="piso" class="form-control" value="<%=propiedad.getPiso()%>" readonly>
                                                                          </div>
                                                                    </div>
                                                                </div>
                                                                <div class="col-lg-1 nopadding">
                                                                    <div class="controls">
                                                                        <label class="control-label" for="dpto">Dpto</label>
                                                                          <div class="input-group ">
                                                                            <input type="text" id="dpto" name="dpto" class="form-control" value="<%=propiedad.getDpto()%>"  readonly>
                                                                          </div>
                                                                    </div>
                                                                </div>
                                                            <% if(!con_propiedad) {%>
                                                                <div class="col-lg-2 ">
                                                                    <div class="controls">
                                                                        <label class="control-label" for="id_propiedad">&nbsp;</label>
                                                                          <div class="input-group ">
                                                                            <span class="btn btn-primary" data-toggle="modal" data-target="#mdlPropiedad">Seleccionar</span>
                                                                          </div>
                                                                    </div>
                                                                </div>
                                                            <% } %>
                                                        </div><!-- row -->
                                                     </div> <!-- col-lg-12 -->
                                                     <div class="col-lg-12 nopadding">
                                                        <div class="form-group row">
                                                            <div class=" col-lg-6 ">
                                                                   <label class="control-label" for="localidad">Localidad</label>
                                                                   <div class="controls">
                                                                         <div class="input-group col-lg-12">
                                                                           <input type="text" name="localidad" id="localidad" class="form-control" value="<%=localidad.getDescripcion()%>"readonly>
                                                                         </div>
                                                                   </div>
                                                            </div>
                                                            <div class="col-lg-6 ">
                                                                   <label class="control-label" for="barrio">Barrio</label>
                                                                   <div class="controls">
                                                                         <div class="input-group col-lg-12">
                                                                           <input type="text" name="barrio" id="barrio" class="form-control" value="<%=barrio.getNombre()%>" readonly>
                                                                         </div>
                                                                   </div>
                                                            </div>

                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="col-lg-5 nopadding">
                                                    <div class="form-group row">
                                                        <div class=" col-lg-3 ">
                                                               <label class="control-label" for="localidad">Carpeta</label>
                                                               <div class="controls">
                                                                     <div class="input-group col-lg-12">
                                                                       <input type="text" name="carpeta_propietario" id="carpeta_propietario" class="form-control" value="<%=propietario.getCarpeta()%>"readonly>
                                                                     </div>
                                                               </div>
                                                        </div>
                                                        <div class="col-lg-9 ">
                                                               <label class="control-label" for="barrio">Propietario</label>
                                                               <div class="controls">
                                                                     <div class="input-group col-lg-12">
                                                                       <input type="text" name="nombre_propietario" id="nombre_propietario" class="form-control" value="<%=propietario.getApellidoyNombre()%>" readonly>
                                                                     </div>
                                                               </div>
                                                        </div>

                                                    </div>
                                                </div>
                                            </fieldset>
                                            </div> <!-- tab1 -->
                                            <div class="tab-pane row " id="tabBasicos">
                                                <fieldset disabled>
                                                <div class="col-lg-12 ">
                                                    <div class="form-group col-lg-3 nopadding">
                                                        <label class="control-label" for="numero">N&uacute;mero</label>
                                                        <div class="controls">
                                                              <div class="input-group">
                                                                <input type="text" name="numero" id="numero" class="form-control" value="<%=contrato.getNumero()%>" >
                                                              </div>
                                                        </div>
                                                    </div>
                                                    <div class="form-group col-lg-3">
                                                        <label class="control-label" for="fecha_inicio">Fecha de Inicio</label>
                                                        <div class="controls">
                                                              <div class="input-group ">
                                                                  <input type="text" id="fecha_inicio" name="fecha_inicio" class="form-control " value="<%=TFecha.formatearFechaBdVista(contrato.getFecha_inicio())%>">
                                                              </div>
                                                        </div>
                                                    </div>
                                                    <div class="form-group col-lg-3">
                                                        <label class="control-label" for="meses">Meses</label>
                                                        <div class="controls">
                                                              <div class="input-group ">
                                                                <input type="text" id="meses" name="meses" class="form-control  numeric" value="<%=contrato.getMeses()%>">
                                                              </div>
                                                        </div>
                                                    </div>
                                                    <div class="form-group col-lg-3">
                                                        <label class="control-label" for="fecha_fin">Fecha de Fin</label>
                                                        <div class="controls">
                                                              <div class="input-group ">
                                                                    <input type="text" id="fecha_fin" name="fecha_fin" class="form-control" value="<%=TFecha.formatearFechaBdVista(contrato.getFecha_fin())%>">
                                                              </div>
                                                        </div>
                                                    </div>
                                                <div class="col-lg-12 nopadding ">
                                                    <div class="col-lg-6 ">
                                                        <fieldset>
                                                            <legend>Valores del alquiler</legend>
                                                        <table class="table table-bordered table-condensed" id="tblValor">
                                                            <colgroup>
                                                                <col style="width:25%">
                                                                <col style="width:25%">
                                                                <col style="width:25%">
                                                                <col style="width:10%">
                                                            </colgroup>
                                                            <thead>
                                                                <tr>
                                                                    <th>Desde</th>
                                                                    <th>Hasta</th>
                                                                    <th>Importe</th>
                                                                    <th>Acci&oacute;n</th>
                                                                </tr>
                                                            </thead>
                                                            <tbody>
                                                                <% for(Contrato_valor valor: lstValor) {%> 
                                                                <tr>
                                                                    <td><%=TFecha.formatearFechaBdVista(valor.getDesde())%><input type="hidden" name="valor_desde" value="<%=TFecha.formatearFechaBdVista(valor.getDesde())%>"></td>
                                                                    <td><%=TFecha.formatearFechaBdVista(valor.getHasta())%><input type="hidden" name="valor_hasta" value="<%=TFecha.formatearFechaBdVista(valor.getHasta())%>"></td>
                                                                    <td><%=df.format(valor.getMonto())%><input type="hidden" name="valor_monto" value="<%=valor.getMonto()%>"></td>
                                                                    <td><span class='btn btn-xs btn-danger btn-circle btn-del'><span class='fa fa-trash-o'></span></td>
                                                                </tr>
                                                                <%}%>
                                                                
                                                            </tbody>
                                                            <tfoot>
                                                                <% if(lstValor.size() == 0) {%>
                                                                <tr><td colspan="5">No se agreg&oacute; ning&uacute;n valor al contrato</td></tr>
                                                                <% }%>
                                                            </tfoot>  
                                                            <tfoot></tfoot>
                                                        </table>
                                                    </fieldset>
                                                    </div>
                                                    <div class="col-lg-6 ">
                                                        <fieldset>
                                                            <legend>Documentos</legend>
                                                        <table class="table table-bordered table-condensed" id="tblDocumento">
                                                             <colgroup>
                                                                <col style="width:25%">
                                                                <col style="width:25%">
                                                                <col style="width:25%">
                                                                <col style="width:10%">
                                                            </colgroup>
                                                            <thead>
                                                                <tr>
                                                                    <th>Desde</th>
                                                                    <th>Hasta</th>
                                                                    <th>Importe</th>
                                                                    <th>Acci&oacute;n</th>
                                                                </tr>
                                                            </thead>
                                                            <tbody>
                                                                <% for(Contrato_documento documento: lstDocum) {%> 
                                                                <tr>
                                                                    <td><%=TFecha.formatearFechaBdVista(documento.getDesde())%><input type="hidden" name="documento_desde" value="<%=TFecha.formatearFechaBdVista(documento.getDesde())%>"></td>
                                                                    <td><%=TFecha.formatearFechaBdVista(documento.getHasta())%><input type="hidden" name="documento_hasta" value="<%=TFecha.formatearFechaBdVista(documento.getHasta())%>"></td>
                                                                    <td><%=df.format(documento.getMonto())%><input type="hidden" name="documento_monto" value="<%=documento.getMonto()%>"></td>
                                                                    <td><span class='btn btn-xs btn-danger btn-circle btn-del'><span class='fa fa-trash-o'></span></td>
                                                                </tr>
                                                                <%}%>                                                              
                                                            </tbody>
                                                            <tfoot>
                                                            <% if(lstDocum.size() == 0) {%>
                                                                <tr><td colspan="5">No se agreg&oacute; ning&uacute;n documento al contrato</td></tr>
                                                            <% }%>                                                                
                                                            </tfoot>                                                            
                                                        </table>
                                                            </fieldset>
                                                    </div>
                                                </div>
                                                </div>

                                                <div class="col-lg-6">
                                                    <div class="col-lg-12 nopadding">
                                                        <div class="form-group row">
                                                             <div class="col-lg-6 col-sm-6 ">
                                                                    <label class="control-label" for="punitorio_monto">Punitorio</label>
                                                                    <div class="controls">
                                                                        <div class="input-group">
                                                                          <input type="text" id="punitorio_monto" name="punitorio_monto" class="form-control  numeric" value="<%=df.format(contrato.getPunitorio_monto())%>">
                                                                        </div>
                                                                    </div>
                                                                </div>

                                                                <div class="col-lg-6 col-sm-6 ">
                                                                    <label class="control-label" for="punitorio_desde">Desde el d&iacute;a</label>
                                                                    <div class="controls">
                                                                        <div class="input-group">
                                                                          <input type="text" id="punitorio_desde" name="punitorio_desde" class="form-control" value="<%=contrato.getPunitorio_desde()%>">
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="col-lg-6">
                                                    <div class="col-lg-12 nopadding">

                                                        <div class="form-group row">
                                                             <div class="col-lg-6">
                                                                    <label class="control-label" for="id_vendedor">Vendedor</label>
                                                                    <div class="controls">
                                                                        <div class="input-group  col-lg-12">
                                                                            <select name="id_vendedor" id="id_vendedor" class="form-control" >                                                                                
                                                                                <option value="<%=vendedor.getId()%>" ><%=vendedor.getNombre() + ", " + vendedor.getApellido() %></option>                                                                                
                                                                            </select>

                                                                        </div>
                                                                    </div>
                                                                </div>

                                                                <div class="col-lg-6 ">
                                                                    <label class="control-label" for="comision_vendedor">Comision al vendedor ($)</label>
                                                                    <div class="controls">
                                                                        <div class="input-group">
                                                                          <input type="text" id="comision_vendedor"  name="comision_vendedor"  class="form-control numeric" value="<%=df.format(contrato.getComision_vendedor())%>">
                                                                        </div>
                                                                    </div>
                                                                </div>

                                                        </div>
                                                    </div>
                                                </div>
                                                </fieldset>
                                            </div><!-- tabBasicos -->
                                                <div class="tab-pane  row" id="tabAdic">
                                                    <div class="col-lg-6">
                                                    <fieldset disabled>
                                                        <legend >Gastos inquilino</legend>
                                                       <div class="col-lg-12 ">
                                                            <div class="form-group row">
                                                                <div class="col-lg-4 col-sm-4 ">
                                                                    <label class="control-label" for="gastos_escribania_inquilino">Gastos escriban&iacute;a</label>
                                                                    <div class="controls">
                                                                        <div class="input-group">
                                                                          <input type="text" id="gastos_escribania_inquilino" name="gastos_escribania_inquilino" class="form-control numeric" value="<%=df.format(contrato.getGastos_escribania_inquilino())%>">
                                                                        </div>
                                                                    </div>
                                                                </div>

                                                                <div class="col-lg-4 col-sm-4 ">
                                                                    <label class="control-label" for="gastos_sellado_inquilino">Gastos sellado</label>
                                                                    <div class="controls">
                                                                        <div class="input-group">
                                                                          <input type="text" id="gastos_sellado_inquilino" name="gastos_sellado_inquilino" class="form-control  numeric" value="<%=df.format(contrato.getGastos_sellado_inquilino())%>">
                                                                        </div>
                                                                    </div>
                                                                </div>

                                                            </div>
                                                        </div>
                                                        <div class="col-lg-12 ">
                                                            <div class="form-group row">
                                                                <div class="col-lg-4 col-sm-4 ">
                                                                    <label class="control-label" for="comision_monto_inquilino">Comisi&oacute;n</label>
                                                                    <div class="controls">
                                                                        <div class="input-group">
                                                                          <input type="text" id="comision_monto_inquilino" name="comision_monto_inquilino" class="form-control  numeric" value="<%=df.format(contrato.getComision_monto_inquilino())%>">
                                                                        </div>
                                                                    </div>
                                                                </div>

                                                                <div class="col-lg-4 col-sm-4 ">
                                                                    <label class="control-label" for="comision_desde_inquilino">Fecha Inicio</label>
                                                                    <div class="controls">
                                                                        <div class="input-group">
                                                                            <input type="text" id="comision_desde_inquilino" name="comision_desde_inquilino" class="form-control " value="<%=TFecha.formatearFechaBdVista(contrato.getComision_desde_inquilino())%>">
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                                <div class="col-lg-3 ">
                                                                    <label class="control-label" for="comision_cuotas_inquilino">Cuotas</label>
                                                                    <div class="controls">
                                                                        <div class="input-group">
                                                                          <input type="text" id="comision_cuotas_inquilino" name="comision_cuotas_inquilino" class="form-control numeric" value="<%=contrato.getComision_cuotas_inquilino()%>">
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </div>
                                                        <div class="col-lg-12 "> 
                                                            <div class="form-group row">
                                                                <div class="col-lg-4 col-sm-4 ">
                                                                    <label class="control-label" for="deposito_monto">Deposito</label>
                                                                    <div class="controls">
                                                                        <div class="input-group">
                                                                          <input type="text" id="deposito_monto" name="deposito_monto" class="form-control  numeric" value="<%=df.format(contrato.getDeposito_monto())%>">
                                                                        </div>
                                                                    </div>
                                                                </div>

                                                                <div class="col-lg-4 col-sm-4 ">
                                                                    <label class="control-label" for="deposito_desde">Fecha Inicio</label>
                                                                    <div class="controls">
                                                                        <div class="input-group">
                                                                            <input type="text" id="deposito_desde" name="deposito_desde" class="form-control " value="<%=TFecha.formatearFechaBdVista(contrato.getDeposito_desde())%>">
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                                <div class="col-lg-3 ">
                                                                    <label class="control-label" for="deposito_cuotas">Cuotas</label>
                                                                    <div class="controls">
                                                                        <div class="input-group">
                                                                          <input type="text" id="deposito_cuotas" name="deposito_cuotas" class="form-control  numeric" value="<%=contrato.getDeposito_cuotas()%>">
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </div>
                                                        <div class="col-lg-12 ">
                                                            <div class="form-group row">
                                                                <div class="col-lg-3 ">
                                                                    <label class="control-label" for="llave_monto">Fondo entrega llave</label>
                                                                    <div class="controls">
                                                                        <div class="input-group">
                                                                          <input type="text" id="llave_monto" name="llave_monto" class="form-control  numeric"  value="<%=contrato.getLlave_monto()%>">
                                                                        </div>
                                                                    </div>
                                                                </div>

                                                                <div class="col-lg-4">
                                                                    <label class="control-label" for="llave_desde">Fecha Inicio</label>
                                                                    <div class="controls">
                                                                        <div class="input-group date date-picker " >
                                                                          <input type="text" id="comision_desde_inquilino" id="llave_desde" name="llave_desde" class="form-control date-input "  value="<%=TFecha.formatearFechaBdVista(contrato.getLlave_desde())%>">
                                                                          <span class="add-on input-group-addon"><span class="fa fa-calendar"></span></span>  
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                                <div class="col-lg-2 ">
                                                                    <label class="control-label" for="llave_cuotas">Cuotas</label>
                                                                    <div class="controls">
                                                                        <div class="input-group">
                                                                          <input type="text" id="llave_cuotas" name="llave_cuotas" class="form-control numeric"  value="<%=contrato.getLlave_cuotas()%>">
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </div>                                                                        
                                                    </fieldset> <!--Inquilino -->
                                                </div> <!--col-lg-g -->
                                                <div class="col-lg-6">
                                                    <fieldset disabled>
                                                        <legend >Gastos propietario</legend>
                                                       <div class="col-lg-12 ">
                                                            <div class="form-group row">
                                                                <div class="col-lg-4 col-sm-4 ">
                                                                    <label class="control-label" for="dni">Gastos escriban&iacute;a</label>
                                                                    <div class="controls">
                                                                        <div class="input-group">
                                                                          <input type="text" id="gastos_escribania_propietario" name="gastos_escribania_propietario" class="form-control  numeric" value="<%=df.format(contrato.getGastos_escribania_propietario())%>">
                                                                        </div>
                                                                    </div>
                                                                </div>

                                                                <div class="col-lg-4 col-sm-4 ">
                                                                    <label class="control-label" for="gastos_sellado_propietario">Gastos sellado</label>
                                                                    <div class="controls">
                                                                        <div class="input-group">
                                                                          <input type="text" id="gastos_sellado_propietario" name="gastos_sellado_propietario" class="form-control  numeric" value="<%=df.format(contrato.getGastos_sellado_propietario())%>">
                                                                        </div>
                                                                    </div>
                                                                </div>

                                                            </div>
                                                        </div>
                                                        <div class="col-lg-12 ">
                                                            <div class="form-group row">
                                                                <div class="col-lg-4 col-sm-4 ">
                                                                    <label class="control-label" for="comision_monto_propietario">Comisi&oacute;n</label>
                                                                    <div class="controls">
                                                                        <div class="input-group">
                                                                          <input type="text" id="comision_monto_propietario" name="comision_monto_propietario" class="form-control numeric" value="<%=df.format(contrato.getComision_monto_propietario())%>">
                                                                        </div>
                                                                    </div>
                                                                </div>

                                                                <div class="col-lg-4 col-sm-4 ">
                                                                    <label class="control-label" for="comision_desde_propietario">Fecha Inicio</label>
                                                                    <div class="controls">
                                                                        <div class="input-group">
                                                                            <input type="text" id="comision_desde_propietario" name="comision_desde_propietario" class="form-control " value="<%=TFecha.formatearFechaBdVista(contrato.comision_desde_propietario)%>">
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                                <div class="col-lg-3">
                                                                    <label class="control-label" for="comision_cuotas_propietario">Cuotas</label>
                                                                    <div class="controls">
                                                                        <div class="input-group">
                                                                            <input type="text" id="comision_cuotas_propietario" name="comision_cuotas_propietario" class="form-control numeric" value="<%=contrato.getComision_cuotas_propietario()%>">
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </div>
                                                        <div class="col-lg-12">
                                                                <div class="col-lg-4 row">
                                                                    <label class="control-label" for="comision_mensual_propietario">Comisi&oacute;n mensual</label>
                                                                <div class="controls">
                                                                    <div class="input-group">
                                                                      <input type="text" id="comision_mensual_propietario" name="comision_mensual_propietario" class="form-control  numeric"  value="<%=contrato.getComision_mensual_propietario()%>" >
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </fielset>
                                                </div><!--Gastos propietario-->
                                                <div class="col-lg-12 ">
                                                        <table class="table table-bordered table-condensed" id="tblGasto">
                                                             <colgroup>
                                                                <col style="width:65%">
                                                                <col style="width:10%">
                                                                <col style="width:15%">
                                                                <col style="width:10%">
                                                            </colgroup>
                                                            <thead>
                                                                <tr>
                                                                    <th>Concepto</th>
                                                                    <th>Aplica</th>
                                                                    <th>Cuotas</th>
                                                                    <th>Importe</th>
                                                                    <th>Acci&oacute;n</th>
                                                                </tr>
                                                            </thead>
                                                            
                                                            <tbody>
                                                                <% for(Contrato_gasto gasto: lstContrato_gasto) {
                                                                    String aplica = gasto.getId_aplica()==1?"Inquilino":"Propietario";
                                                                %> 
                                                                <tr>
                                                                    <td><%=gasto.getConcepto()%><input type="hidden" name="gasto_concepto" value="<%=gasto.getConcepto()%>"></td>
                                                                    <td><%=aplica%><input type="hidden" name="gasto_aplica" value="<%=gasto.getId_aplica()%>"></td>
                                                                    <td><%=gasto.getCuotas()%><input type="hidden" name="gasto_cuotas" value="<%=gasto.getCuotas()%>"></td>
                                                                    <td><%=df.format(gasto.getImporte())%><input type="hidden" name="valor_monto" value="<%=gasto.getImporte()%>"></td>
                                                                    <td><span class='btn btn-xs btn-danger btn-circle btn-del'><span class='fa fa-trash-o'></span></td>
                                                                </tr>
                                                                <%}%>                                                               
                                                            </tbody>
                                                            <tfoot>
                                                                <% if(lstContrato_gasto.size() == 0) {%>
                                                                <tr><td colspan="5">No se agreg&oacute; ning&uacute;n gasto adicional al contrato</td></tr>
                                                                <% } else { %>
                                                                <!-- <tr><td colspan="5"><span class="btn btn-primary" id="btnGasto">Agregar Gasto</span></td></tr>-->
                                                                <%}%>
                                                            </tfoot>
                                                        </table>
                                                    </div>
                                            </div> <!-- tabAdic-->
                                            <div class="tab-pane row" id="tabGarantes">
                                               <div class="col-lg-12">
                                                   <fieldset disabled>
                                                       <legend>Garante 1</legend>
                                                    <div class="col-lg-12">
                                                        <div class="form-group row">
                                                                <div class="col-lg-2 nopadding">
                                                                    <div class="controls">
                                                                        <label class="control-label" for="garante_1_dni">DNI</label>
                                                                          <div class="input-group ">
                                                                            <input type="text" id="garante_1_dni" name="garante_1_dni" class="form-control" value="<%=contrato.getGarante_1_dni()%>" >
                                                                          </div>
                                                                    </div>
                                                                </div>
                                                                <div class="col-lg-4 ">
                                                                    <div class="controls">
                                                                        <label class="control-label" for="garante_1_nombre">Nombre y apellido</label>
                                                                          <div class="input-group col-lg-12 ">
                                                                            <input type="text" id="garante_1_nombre" name="garante_1_nombre" class="form-control" value="<%=contrato.getGarante_1_nombre()%>" >
                                                                          </div>
                                                                    </div>
                                                                </div>
                                                                <div class="col-lg-2">
                                                                    <div class="controls">
                                                                        <label class="control-label" for="garante_1_telefono">Telefono</label>
                                                                          <div class="input-group ">
                                                                            <input type="text" id="garante_1_telefono" name="garante_1_telefono" class="form-control" value="<%=contrato.getGarante_1_telefono()%>" >
                                                                          </div>
                                                                    </div>
                                                                </div>
                                                                <div class="col-lg-4">
                                                                    <div class="controls">
                                                                        <label class="control-label" for="garante_1_id_garantia">Tipo</label>
                                                                          <div class="input-group col-lg-4">
                                                                            <select id="garante_1_id_garantia" name="garante_1_id_garantia" class="form-control" value="<%=contrato.getGarante_1_telefono()%>" >
                                                                                <option id="0"></option>
                                                                                 <% for(Option o: OptionsCfg.getGarantias()){
                                                                                String garante_1_selected = o.getId().equals(contrato.getGarante_1_id_garantia())?"selected":"";
                                                                                %>
                                                                                    <option value="<%=o.getId()%>" <%=garante_1_selected%>><%=o.getDescripcion()%></option>
                                                                                <%}%>
                                                                            </select>
                                                                          </div>
                                                                    </div>
                                                                </div>
                                                        </div><!-- row -->
                                                   </div>                                                    
                                                </fieldset>
                                            </div>
                                            
                                            <div class="col-lg-12">
                                                   <fieldset disabled>
                                                       <legend>Garante 2</legend>
                                                    <div class="col-lg-12">
                                                        <div class="form-group row">
                                                                <div class="col-lg-2 nopadding">
                                                                    <div class="controls">
                                                                        <label class="control-label" for="garante_2_dni">DNI</label>
                                                                          <div class="input-group ">
                                                                            <input type="text" id="garante_2_dni" name="garante_2_dni" class="form-control" value="<%=contrato.getGarante_2_dni()%>" >
                                                                          </div>
                                                                    </div>
                                                                </div>
                                                                <div class="col-lg-4 ">
                                                                    <div class="controls">
                                                                        <label class="control-label" for="garante_2_nombre">Nombre y apellido</label>
                                                                          <div class="input-group col-lg-12 ">
                                                                            <input type="text" id="garante_2_nombre" name="garante_2_nombre" class="form-control" value="<%=contrato.getGarante_2_nombre()%>" >
                                                                          </div>
                                                                    </div>
                                                                </div>
                                                                <div class="col-lg-2">
                                                                    <div class="controls">
                                                                        <label class="control-label" for="garante_2_telefono">Telefono</label>
                                                                          <div class="input-group ">
                                                                            <input type="text" id="garante_2_telefono" name="garante_2_telefono" class="form-control" value="<%=contrato.getGarante_2_telefono()%>" >
                                                                          </div>
                                                                    </div>
                                                                </div>
                                                                <div class="col-lg-4">
                                                                    <div class="controls">
                                                                        <label class="control-label" for="garante_2_id_garantia">Tipo</label>
                                                                          <div class="input-group col-lg-4">
                                                                            <select id="garante_2_id_garantia" name="garante_2_id_garantia" class="form-control" value="<%=contrato.getGarante_2_telefono()%>" >
                                                                                <option id="0" ></option>
                                                                                <% for(Option o: OptionsCfg.getGarantias()){
                                                                                String garante_2_selected = o.getId().equals(contrato.getGarante_2_id_garantia())?"selected":"";
                                                                                %>
                                                                                    <option value="<%=o.getId()%>" <%=garante_2_selected%>><%=o.getDescripcion()%></option>
                                                                                <%}%>
                                                                            </select>
                                                                          </div>
                                                                    </div>
                                                                </div>
                                                        </div><!-- row -->
                                                   </div>                                                    
                                                </fieldset>
                                            </div>
                                            
                                            <div class="col-lg-12">
                                                   <fieldset disabled>
                                                       <legend>Garante 3</legend>
                                                    <div class="col-lg-12">
                                                        <div class="form-group row">
                                                                <div class="col-lg-2 nopadding">
                                                                    <div class="controls">
                                                                        <label class="control-label" for="garante_3_dni">DNI</label>
                                                                          <div class="input-group ">
                                                                            <input type="text" id="garante_3_dni" name="garante_3_dni" class="form-control" value="<%=contrato.getGarante_2_dni()%>" >
                                                                          </div>
                                                                    </div>
                                                                </div>
                                                                <div class="col-lg-4 ">
                                                                    <div class="controls">
                                                                        <label class="control-label" for="garante_3_nombre">Nombre y apellido</label>
                                                                          <div class="input-group col-lg-12 ">
                                                                            <input type="text" id="garante_3_nombre" name="garante_3_nombre" class="form-control" value="<%=contrato.getGarante_3_nombre()%>" >
                                                                          </div>
                                                                    </div>
                                                                </div>
                                                                <div class="col-lg-2">
                                                                    <div class="controls">
                                                                        <label class="control-label" for="garante_3_telefono">Telefono</label>
                                                                          <div class="input-group ">
                                                                            <input type="text" id="garante_3_telefono" name="garante_3_telefono" class="form-control" value="<%=contrato.getGarante_3_telefono()%>" >
                                                                          </div>
                                                                    </div>
                                                                </div>
                                                                <div class="col-lg-4">
                                                                    <div class="controls">
                                                                        <label class="control-label" for="garante_3_id_garantia">Tipo</label>
                                                                          <div class="input-group col-lg-4">
                                                                            <select id="garante_3_id_garantia" name="garante_3_id_garantia" class="form-control" value="" >
                                                                                 <option id="0" ></option>
                                                                                    <% for(Option o: OptionsCfg.getGarantias()){ 
                                                                                    String garante_3_selected = o.getId().equals(contrato.getGarante_3_id_garantia())?"selected":"";
                                                                                    %>
                                                                                    <option value="<%=o.getId()%>" <%=garante_3_selected%>><%=o.getDescripcion()%></option>
                                                                                <%}%>
                                                                            </select>
                                                                          </div>
                                                                    </div>
                                                                </div>
                                                        </div><!-- row -->
                                                   </div>                                                    
                                                </fieldset>
                                            </div>
                                        </div> <!-- tabGarantes -->
                                             <div class="tab-pane row " id="tabOtros">
                                                 <fieldset disabled>
                                                 <div class="col-lg-12 nopadding">
                                                      <div class="form-group ">
                                                        <label for="asegura_renta" class="col-lg-2 control-label"><span class="">Asegura Renta</span></label>
                                                        <div class="col-lg-3">
                                                            <% String chkAsegura=contrato.getAsegura_renta()==1?"checked":"";%>
                                                            <input type="checkbox" id="asegura_renta" name="asegura_renta" <%=chkAsegura%> />
                                                        </div>
                                                    </div>
                                                 </div>
                                                 <div class="col-lg-12">
                                                     <label class="control-label" for="observaciones">Observaciones</label>
                                                        <div class="input-control">
                                                              <textarea class="form-control" name="observaciones" id="observaciones"><%=contrato.getObservaciones()%></textarea>
                                                        </div>
                                                </div>
                                                 </fieldset>
                                             </div><!-- tabOtros -->
                                            <div class="tab-pane  row" id="tabCuenta">
                                                <div class="row">
                                                <div class="col-lg-6">
                                                    <h2><i class="fa fa-edit"></i>Cuenta Oficial  </h2>
                                                    <input type="hidden" name="id_cuenta_oficial" id="id_cuenta_oficial" >

                                                    <table class="table table-bordered table-condensed table-striped" id="tblCuentaOficial">
                                                            <colgroup>
                                                                <col style="width:10%">
                                                                <col style="">
                                                                <col style="width:10%;text-align: center">
                                                                <col style="width:10%">
                                                                <col style="width:10%">
                                                            </colgroup>
                                                            <thead>
                                                                <tr>
                                                                    <th>Fecha</th>
                                                                    <th>Concepto</th>
                                                                    <th style="text-align: center">Debe</th>
                                                                    <th style="text-align: center">Haber</th>
                                                                    <th style="text-align: center">Saldo</th>
                                                                </tr>
                                                            </thead>    
                                                            <tbody>
                                                                <% Float saldo_oficial = 0f;
                                                                    for(Cuenta_detalle cd: cuenta_detalle_oficial) { 
                                                                        saldo_oficial += cd.getHaber() - cd.getDebe();
                                                                %>
                                                                <tr>
                                                                    <td><%=TFecha.formatearFechaBdVista(cd.getFecha())%></td>
                                                                    <td><%=cd.getConcepto()%></td>
                                                                    <td><%=cd.getDebe()%></td>
                                                                    <td><%=cd.getHaber()%></td>
                                                                    <td><%= saldo_oficial %></td>
                                                                    <input type="hidden" name="saldo_oficial" value="<%=saldo_oficial%>">
                                                                </tr>
                                                                <% } %>
                                                            </tbody>
        <!--                                                <tfoot>
                                                            <th>
                                                                <td colspan=""></td>     
                                                                <td colspan="2"><b style="text-align: right">Saldo Total</b></td>
                                                                <td colspan="" id="saldoOficial"></td>                                                        
                                                            </th>
                                                      </tfoot>-->
                                                    </table>
                                                </div>
                                                            
                                            <div class="col-lg-6">
                                                <h2><i class="fa fa-edit"></i>Cuenta no oficial </h2>
                                                    <input type="hidden" name="id_cuenta_no_oficial" id="id_cuenta_no_oficial" >

                                                <table class="table table-bordered table-condensed table-striped" id="tblCuentaNoOficial">
                                                    <colgroup>
                                                        <col style="width:10%">
                                                        <col style="">
                                                        <col style="width:10%;text-align: center">
                                                        <col style="width:10%">
                                                        <col style="width:10%">
                                                    </colgroup>
                                                    <thead>
                                                        <tr>
                                                            <th>Fecha</th>
                                                            <th>Concepto</th>
                                                            <th style="text-align: center">Debe</th>
                                                            <th style="text-align: center">Haber</th>
                                                            <th style="text-align: center">Saldo</th>
                                                        </tr>
                                                    </thead>    
                                                     <tbody>
                                                                <% Float saldo_no_oficial = 0f;
                                                                    for(Cuenta_detalle cd: cuenta_detalle_no_oficial) { 
                                                                        saldo_no_oficial += cd.getHaber() - cd.getDebe();
                                                                %>
                                                                <tr>
                                                                    <td><%=TFecha.formatearFechaBdVista(cd.getFecha())%></td>
                                                                    <td><%=cd.getConcepto()%></td>
                                                                    <td><%=cd.getDebe()%></td>
                                                                    <td><%=cd.getHaber()%></td>
                                                                    <td><%= saldo_no_oficial %></td>
                                                                </tr>
                                                                <% } %>
                                                            </tbody>
                                            <!-- <tfoot>
                                                    <th><td colspan="4"></td></th>
                                              </tfoot> -->
                                            </table>
                                        </div>
                                                </div>
                                            </div><!-- tabCuenta -->
                                             
                                        <div class="tab-pane active row" id="tabContrato">                                            
                                            <div class=" form-group row">
                                                <fieldset disabled>
                                                <div class="col-lg-8">                                                    
                                                    <div class="controls col-lg-6">
                                                        <label class="control-label" for="id_inquilino">Estado</label>
                                                          <div class="input-group  col-lg-12">
                                                              <input type="text" class="form-control"  name="contrato_estado" value="<%=contrato_estado %>">                                                                
                                                          </div>
                                                    </div>
                                                    <div class="controls  col-lg-3">
                                                        <label class="control-label" for="id_inquilino">Saldo Oficial</label>
                                                          <div class="input-group ">
                                                              <input type="text" class="form-control"  name="saldo_oficial" value="<%=saldo_oficial%>">                                                                
                                                          </div>
                                                    </div>
                                                    <div class="controls col-lg-3">
                                                        <label class="control-label" for="id_inquilino">Saldo No Oficial</label>
                                                          <div class="input-group ">
                                                              <input type="text" class="form-control"  name="saldo_no_oficial" value="<%=saldo_no_oficial%>">                                                                
                                                          </div>
                                                    </div>   
                                                </div>
                                               </fieldset>           
                                            </div>
                                            
                                    </div> <!-- tabContrato -->
                                        <div class="row">
                                            <div class="col-lg-12">
                                            <ul class="pager wizard">
                                                  <li class="previous first" style="display:none;"><a href="#">First</a></li>
                                                  <li class="previous"><a href="#">Anterior</a></li>
                                                  <li class="next last" style="display:none;"><a href="#">Last</a></li>
                                                  <li class="next"><a href="#">Siguiente</a></li>
                                            </ul>
                                        </div>
                                      </div>
                                     <div class="row">
                                        <div class="col-lg-12">
                                           <div class="form-actions">
                                              <button type="submit" class="btn btn-primary" id="btnSubmit"><%=txtBoton%></button>
                                              <a  href="<%= PathCfg.CONTRATO%>"class="btn btn-default">Volver</a>
                                          </div>
                                       </div>
                                    </div>
                                </div> <!-- tab-content-->
                                      
					</div>
				</div><!--/col-->

			</div><!--/row-->
                        </form>
<!--                              



			</div>
			<!-- end: Content -->

                    </div><!--/row-->

	</div><!--/container-->




	<div class="clearfix"></div>

        <%@include file="tpl_footer.jsp" %>

	<!-- start: JavaScript-->
	<!--[if !IE]>-->
                    <script src="assets/js/jquery-2.1.0.min.js"></script>
	<!--<![endif]-->

	<!--[if IE]>

		<script src="assets/js/jquery-1.11.0.min.js"></script>

	<![endif]-->

	<!--[if !IE]>-->

		<script type="text/javascript">
			window.jQuery || document.write("<script src='assets/js/jquery-2.1.0.min.js'>"+"<"+"/script>");
		</script>

	<!--<![endif]-->

	<!--[if IE]>

		<script type="text/javascript">
	 	window.jQuery || document.write("<script src='assets/js/jquery-1.11.0.min.js'>"+"<"+"/script>");
		</script>

	<![endif]-->
	<script src="assets/js/jquery-migrate-1.2.1.min.js"></script>
	<script src="assets/js/bootstrap.min.js"></script>




	<!-- page scripts -->
	<script src="assets/js/jquery-ui-1.10.3.custom.min.js"></script>
	<!--<script src="assets/js/jquery.sparkline.min.js"></script>-->
	<!--<script src="assets/js/jquery.chosen.min.js"></script>-->
	<!--<script src="assets/js/jquery.cleditor.min.js"></script>-->
	<script src="assets/js/jquery.autosize.min.js"></script>
	<!--<script src="assets/js/jquery.placeholder.min.js"></script>-->
	<script src="assets/js/jquery.maskedinput.min.js"></script>
	<!--<script src="assets/js/jquery.inputlimiter.1.3.1.min.js"></script>-->
	<script src="assets/js/bootstrap-datepicker.min.js"></script>
        <script src="assets/js/locales/bootstrap-datepicker.es.min.js"></script>

	<script src="assets/js/bootstrap-timepicker.min.js"></script>
	<script src="assets/js/moment.min.js"></script>
	<script src="assets/js/daterangepicker.min.js"></script>
	<!--<script src="assets/js/jquery.hotkeys.min.js"></script>-->
	<!--<script src="assets/js/bootstrap-wysiwyg.min.js"></script>-->
	<!--<script src="assets/js/bootstrap-colorpicker.min.js"></script>-->

	<!-- theme scripts -->
	<script src="assets/js/custom.min.js"></script>
	<script src="assets/js/core.min.js"></script>

	<!-- inline scripts related to this page -->
	<!--<script src="assets/js/pages/form-elements.js"></script>-->

        <script src="assets/js/notify.min.js"></script>


        <script src="assets/js/jquery.bootstrap.wizard.min.js"></script>

        <script src="assets/js/bootbox.min.js"></script>
        <script src="assets/js/common-functions.js"></script>

	<!-- end: JavaScript-->
    <%--<%@include file="mdl_cliente.jsp" %>--%>
    <%--<%@include file="mdl_propiedad.jsp" %>--%>
<script>
    $(document).ready(function() {
  	$('#rootwizard').bootstrapWizard({
            onNext: mover,
            onPrevious:mover
        });
        $('form').submit(function(e){
         e.preventDefault();
         bootbox.confirm("¿Est&aacute; seguro que desea cambiar el estado del contrato?",function(result){
         if(result) {
            $('form').unbind('submit')
            $('form').submit();
            
         }
     });
        });
    });
    function mover(tab, navigation, index){
    if(index===3){
        $('#btnRow').css('display','block');
        
    } else $('#btnRow').css('display','none');
}
</script>
 <%@include file="mdl_cliente.jsp" %>
 <%@include file="mdl_propiedad.jsp" %>
</body>
</html>
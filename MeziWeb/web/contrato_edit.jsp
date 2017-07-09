
<%@page import="bd.Propietario"%>
<%@page import="utils.OptionsCfg.Option"%>
<%@page import="bd.Contrato_gasto"%>
<%@page import="bd.Contrato_documento"%>
<%@page import="bd.Contrato_valor"%>
<%@page import="bd.Contrato"%>
<%@page import="utils.TFecha"%>
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
    Contrato                 contrato              = (Contrato)  request.getAttribute("contrato");
    List<Contrato_valor>     lstContrato_valor     = (List<Contrato_valor>)     request.getAttribute("contrato_valor");
    List<Contrato_documento> lstContrato_documento = (List<Contrato_documento>) request.getAttribute("contrato_documento");
    List<Contrato_gasto>     lstContrato_gasto     = (List<Contrato_gasto>)     request.getAttribute("contrato_gasto");

    Propiedad propiedad = (Propiedad) request.getAttribute("propiedad");
    Cliente   cliente   = (Cliente)   request.getAttribute("inquilino");
    Vendedor  vendedor  = (Vendedor)  request.getAttribute("vendedor");
    Propietario propietario = (Propietario) request.getAttribute("propietario");
    
    TPropiedad tp = new TPropiedad();
    
    List<Vendedor> lstVendedores =  new TVendedor().getListActivo();
    if(lstVendedores==null) lstVendedores = new ArrayList<Vendedor>();

    String[] lstOpcAgente={"","Si","No"};
    
    boolean con_propiedad = true;
    boolean con_cliente   = true;
    boolean con_vendedor  = true;
    if(propiedad==null){
        propiedad = new Propiedad();
        con_propiedad = false;
    }
    
    if (propietario==null){
        propietario = new Propietario();        
    }
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

    if (vendedor==null){
        vendedor = new Vendedor();
        con_vendedor = false;
    }
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
                            
			<div class="row">
				<div class="col-lg-12">
					<div class="box" id="rootwizard">
                                            <div class="box-header">
                                                <h2><i class="fa fa-edit"></i>Nuevo contrato de alquiler</h2>
                                                <ul id="tabs" class="nav nav-tabs" data-tabs="tabs">
                                                    <li class="active" ><a href="#tab1" data-toggle="tab">Inquilino</a></li>
                                                    <li><a href="#tabProp" data-toggle="tab">Propiedad</a></li>
                                                    <li><a href="#tabBasicos" data-toggle="tab">B&aacute;sicos</a></li>
                                                    <li><a href="#tabAdic" data-toggle="tab">Adicionales</a></li>
                                                    <li><a href="#tabGarantes" data-toggle="tab">Garantes</a></li>
                                                    <li><a href="#tabOtros" data-toggle="tab">Otros datos</a></li>
                                                </ul>
                                            </div>
                                        <form role="form" method="POST">
                                            <input type="hidden" id="id_contrato" name="id_contrato" class="form-control" value="<%=contrato.getId()%>" >
                                        <div  class="tab-content box-content">
                                            <div class="tab-pane row active" id="tab1">
                                               <div class="col-lg-8">
                                                   <fieldset>
                                                       <legend>Datos del inquilino</legend>
                                                   <div class="col-lg-12">
                                                        <div class="form-group row">
                                                                <div class="col-lg-2 nopadding">
                                                                    <div class="controls">
                                                                        <label class="control-label" for="id_inquilino">N&uacute;mero carpeta</label>
                                                                          <div class="input-group ">
                                                                            <input type="hidden" id="id_inquilino" name="id_inquilino" class="form-control" value="<%=cliente.getId()%>" >
                                                                            <input type="text" id="carpeta" name="carpeta" class="form-control" value="<%=cliente.getCarpeta()%>" readonly>
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
                                                                        <span class="btn btn-sm btn-primary" data-toggle="modal" data-target="#mdlCliente">Seleccionar</span>
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
                                                <fieldset>
                                                    <legend>Datos de la propiedad</legend>
                                                <div class="col-lg-7">
                                                     <div class="col-lg-12" >
                                                         <input type="hidden" name="id_propiedad" id="id_propiedad" value="<%=propiedad.getId()%>">

                                                        <div class="form-group row">
                                                                <div class="col-lg-8 nopadding">
                                                                    <div class="controls">
                                                                        <label class="control-label" for="calle">Calle</label>
                                                                          <div class="input-group col-lg-12 ">
                                                                            <input type="text" id="calle" name="calle" class="form-control" value="<%=propiedad.getCalle()%>" readonly>
                                                                          </div>
                                                                    </div>
                                                                </div>
                                                                <div class="col-lg-2 nopadding">
                                                                    <div class="controls">
                                                                        <label class="control-label" for="prop_numero">N&uacute;mero</label>
                                                                          <div class="input-group ">
                                                                            <input type="text" id="prop_numero" name="prop_numero" class="form-control" value="<%=propiedad.getNumero()%>"  readonly>
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
                                                                            <span class="btn btn-sm btn-primary" data-toggle="modal" data-target="#mdlPropiedad">Seleccionar</span>
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
                                                <div class="col-lg-12 ">
                                                    <div class="form-group col-lg-3 nopadding">
                                                        <label class="control-label" for="numero">N&uacute;mero</label>
                                                        <div class="controls">
                                                              <div class="input-group">
                                                                <input type="text" name="numero" id="numero" class="form-control" value="<%=contrato.getNumero()%>">
                                                              </div>
                                                        </div>
                                                    </div>
                                                    <div class="form-group col-lg-3">
                                                        <label class="control-label" for="fecha_inicio">Fecha de Inicio</label>
                                                        <div class="controls">
                                                              <div class="input-group date date-picker">
                                                                  <input type="text" id="fecha_inicio" name="fecha_inicio" class="form-control date-input " value="<%=TFecha.formatearFechaBdVista(contrato.getFecha_inicio())%>">
                                                                    <span class="input-group-addon "><span class="fa fa-calendar"></span></span>  
                                                              </div>
                                                        </div>
                                                    </div>
                                                    <div class="form-group col-lg-3">
                                                        <label class="control-label" for="meses">Meses</label>
                                                        <div class="controls">
                                                              <div class="input-group ">
                                                                <input type="text" id="meses" name="meses" class="form-control  numeric"  value="<%=contrato.getMeses()%>">
                                                              </div>
                                                        </div>
                                                    </div>
                                                    <div class="form-group col-lg-3">
                                                        <label class="control-label" for="fecha_fin">Fecha de Fin</label>
                                                        <div class="controls">
                                                                <div class="input-group date date-picker">
                                                                    <input type="text" id="fecha_fin" name="fecha_fin" class="form-control  date-input  " value="<%=TFecha.formatearFechaBdVista(contrato.getFecha_fin())%>">
                                                                    <span class="input-group-addon"><span class="fa fa-calendar"></span></span>  
                                                                    </span>  
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
                                                                <% for(Contrato_valor cv: lstContrato_valor){
                                                                    String desde = TFecha.formatearFechaBdVista(cv.getDesde());
                                                                    String hasta = TFecha.formatearFechaBdVista(cv.getHasta());
                                                                    Float monto = cv.getMonto();
                                                                %>                                                                
                                                                 <tr>
                                                                     <td><%=desde%><input type="hidden" name="valor_desde" value="<%=desde%>"></td>
                                                                    <td><%=hasta%><input type="hidden" name="valor_hasta" value="<%=hasta%>"></td>
                                                                    <td><%=monto%><input type="hidden" name="valor_monto" value="<%=monto%>"></td>
                                                                    <td><span class='btn btn-xs btn-danger btn-circle btn-del'><span class='fa fa-trash-o'></span></td>
                                                                </tr>
                                                                <% }%>
                                                            </tbody>
                                                            <tfoot><tr><td colspan="5"><span class="btn btn-primary" id="btnValor">Agregar Valor</span></td></tr></tfoot>
                                                        </table>
                                                    </fieldset>
                                                    </div>
                                                    <div class="col-lg-6 ">
                                                        <fieldset>
                                                            <legend>Valores Adicionales</legend>
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
                                                                <% for(Contrato_documento cd: lstContrato_documento){
                                                                    String desde = TFecha.formatearFechaBdVista(cd.getDesde());
                                                                    String hasta = TFecha.formatearFechaBdVista(cd.getHasta());
                                                                    Float monto = cd.getMonto();
                                                                    %>       
                                                                <tr>
                                                                    <td><%=desde%><input type="hidden" name="documento_desde" value="<%=desde%>"></td>
                                                                    <td><%=hasta%><input type="hidden" name="documento_hasta" value="<%=hasta%>"></td>
                                                                    <td><%=monto%><input type="hidden" name="documento_monto" value="<%=monto%>"></td>
                                                                    <td><span class='btn btn-xs btn-danger btn-circle btn-del'><span class='fa fa-trash-o'></span></td>
                                                                </tr>
                                                                <% } %>
                                                            </tbody>
                                                            <tfoot><tr><td colspan="5"><span class="btn btn-sm btn-primary" id="btnDocumento">Agregar Valor</span></td></tr></tfoot>
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
                                                                          <input type="text" id="punitorio_monto" name="punitorio_monto" class="form-control  numeric" value="<%=contrato.getPunitorio_monto()%>">
                                                                        </div>
                                                                    </div>
                                                                </div>

                                                                <div class="col-lg-6 col-sm-6 ">
                                                                    <label class="control-label" for="punitorio_desde">Desde el d&iacute;a</label>
                                                                    <div class="controls">
                                                                        <div class="input-group">
                                                                          <input type="text" id="punitorio_desde" name="punitorio_desde" class="form-control numeric" value="<%=contrato.getPunitorio_desde()%>">
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
                                                                                <option value="0">Seleccione el vendedor</option>
                                                                               <% for(Vendedor v:lstVendedores){
                                                                                    String vendSel = (v.getId()==vendedor.getId())?"selected":"";
                                                                                %>
                                                                                <option value="<%=v.getId()%>" <%=vendSel%>><%=v.getNombre() + ", " + v.getApellido() %></option>
                                                                                <% } %>
                                                                            </select>

                                                                        </div>
                                                                    </div>
                                                                </div>

                                                                <div class="col-lg-6 ">
                                                                    <label class="control-label" for="comision_vendedor">Comision al vendedor ($)</label>
                                                                    <div class="controls">
                                                                        <div class="input-group">
                                                                          <input type="text" id="comision_vendedor"  name="comision_vendedor"  class="form-control numeric"  value="<%=contrato.getComision_vendedor()%>">
                                                                        </div>
                                                                    </div>
                                                                </div>

                                                        </div>
                                                    </div>
                                                </div>
                                            </div><!-- tabBasicos -->
                                            <div class="tab-pane  row" id="tabAdic">
                                                    <div class="col-lg-6">
                                                    <fielset disabled>
                                                        <legend >Gastos inquilino</legend>
                                                       <div class="col-lg-12 ">
                                                            <div class="form-group row">
                                                                <div class="col-lg-3 ">
                                                                    <label class="control-label" for="gastos_escribania_inquilino">Gastos escriban&iacute;a</label>
                                                                    <div class="controls">
                                                                        <div class="input-group">
                                                                          <input type="text" id="gastos_escribania_inquilino" name="gastos_escribania_inquilino" class="form-control numeric"  value="<%=contrato.getGastos_escribania_inquilino()%>">
                                                                        </div>
                                                                    </div>
                                                                </div>

                                                                <div class="col-lg-3 ">
                                                                    <label class="control-label" for="gastos_sellado_inquilino">Gastos sellado</label>
                                                                    <div class="controls">
                                                                        <div class="input-group">
                                                                          <input type="text" id="gastos_sellado_inquilino" name="gastos_sellado_inquilino" class="form-control  numeric"  value="<%=contrato.getGastos_sellado_inquilino()%>">
                                                                        </div>
                                                                    </div>
                                                                </div>

                                                            </div>
                                                        </div>
                                                        <div class="col-lg-12 ">
                                                            <div class="form-group row">
                                                                <div class="col-lg-3 ">
                                                                    <label class="control-label" for="comision_monto_inquilino">Comisi&oacute;n</label>
                                                                    <div class="controls">
                                                                        <div class="input-group">
                                                                          <input type="text" id="comision_monto_inquilino" name="comision_monto_inquilino" class="form-control  numeric"  value="<%=contrato.getComision_monto_inquilino()%>">
                                                                        </div>
                                                                    </div>
                                                                </div>

                                                                <div class="col-lg-4">
                                                                    <label class="control-label" for="comision_desde_inquilino">Fecha Inicio</label>
                                                                    <div class="controls">
                                                                        <div class="input-group date date-picker " >
                                                                          <input type="text" id="comision_desde_inquilino" name="comision_desde_inquilino" class="form-control date-input "  value="<%=TFecha.formatearFechaBdVista(contrato.getComision_desde_inquilino())%>">
                                                                          <span class="add-on input-group-addon"><span class="fa fa-calendar"></span></span>  
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                                <div class="col-lg-2 ">
                                                                    <label class="control-label" for="comision_cuotas_inquilino">Cuotas</label>
                                                                    <div class="controls">
                                                                        <div class="input-group">
                                                                          <input type="text" id="comision_cuotas_inquilino" name="comision_cuotas_inquilino" class="form-control numeric"  value="<%=contrato.getComision_cuotas_inquilino()%>">
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </div>
                                                        <div class="col-lg-12 ">
                                                            <div class="form-group row">
                                                                <div class="col-lg-3">
                                                                    <label class="control-label" for="deposito_monto">Deposito</label>
                                                                    <div class="controls">
                                                                        <div class="input-group">
                                                                          <input type="text" id="deposito_monto" name="deposito_monto" class="form-control  numeric"  value="<%=contrato.getDeposito_monto()%>">
                                                                        </div>
                                                                    </div>
                                                                </div>

                                                                <div class="col-lg-4">
                                                                    <label class="control-label" for="deposito_desde">Fecha Inicio</label>
                                                                    <div class="controls">
                                                                        <div class="input-group date date-picker">
                                                                          <input type="text" id="deposito_desde" name="deposito_desde" class="form-control date-input "  value="<%=TFecha.formatearFechaBdVista(contrato.getDeposito_desde())%>">
                                                                          <span class="input-group-addon"><span class="fa fa-calendar"></span></span>  
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                                <div class="col-lg-2 ">
                                                                    <label class="control-label" for="deposito_cuotas">Cuotas</label>
                                                                    <div class="controls">
                                                                        <div class="input-group">
                                                                          <input type="text" id="deposito_cuotas" name="deposito_cuotas" class="form-control  numeric"  value="<%=contrato.getDeposito_cuotas()%>">
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
                                                    <fielset>
                                                        <legend >Gastos propietario</legend>
                                                       <div class="col-lg-12 ">
                                                            <div class="form-group row">
                                                                <div class="col-lg-4">
                                                                    <label class="control-label" for="dni">Gastos escriban&iacute;a</label>
                                                                    <div class="controls">
                                                                        <div class="input-group">
                                                                          <input type="text" id="gastos_escribania_propietario" name="gastos_escribania_propietario" class="form-control  numeric"  value="<%=contrato.getGastos_escribania_propietario()%>">
                                                                        </div>
                                                                    </div>
                                                                </div>

                                                                <div class="col-lg-4 ">
                                                                    <label class="control-label" for="gastos_sellado_propietario">Gastos sellado</label>
                                                                    <div class="controls">
                                                                        <div class="input-group">
                                                                          <input type="text" id="gastos_sellado_propietario" name="gastos_sellado_propietario" class="form-control  numeric"  value="<%=contrato.getGastos_sellado_propietario()%>">
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                                 <div class="col-lg-4 ">
                                                                    <label class="control-label" for="agente_retencion">Agente retenci&oacute;n</label>
                                                                    <div class="controls">
                                                                        <div class="input-group">
                                                                          <select id="agente_retencion" name="agente_retencion" class="form-control" >
                                                                              <% for(int i=0;i<lstOpcAgente.length;i++) {
                                                                                String selected = contrato.getAgente_retencion().equals(i)?"selected":"";
                                                                              %>
                                                                              <option value="<%=i%>" <%=selected%>><%=lstOpcAgente[i]%></option>
                                                                              <%}%>
                                                                          </select>
                                                                        </div>
                                                                    </div>
                                                                </div>        
                                                            </div>
                                                        </div>
                                                        <div class="col-lg-12 ">
                                                            <div class="form-group row">
                                                                <div class="col-lg-3 ">
                                                                    <label class="control-label" for="comision_monto_propietario">Comisi&oacute;n</label>
                                                                    <div class="controls">
                                                                        <div class="input-group">
                                                                          <input type="text" id="comision_monto_propietario" name="comision_monto_propietario" class="form-control numeric" value="<%=contrato.getComision_monto_propietario()%>">
                                                                        </div>
                                                                    </div>
                                                                </div>

                                                                <div class="col-lg-4 ">
                                                                    <label class="control-label" for="comision_desde_propietario">Fecha Inicio</label>
                                                                    <div class="controls">
                                                                        <div class="input-group date date-picker">
                                                                          <input type="text" id="comision_desde_propietario" name="comision_desde_propietario" class="form-control date-input "  value="<%=TFecha.formatearFechaBdVista(contrato.getComision_desde_propietario())%>">
                                                                          <span class="input-group-addon"><span class="fa fa-calendar"></span></span>  
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                                <div class="col-lg-2">
                                                                    <label class="control-label" for="comision_cuotas_propietario">Cuotas</label>
                                                                    <div class="controls">
                                                                        <div class="input-group">
                                                                          <input type="text" id="comision_cuotas_propietario" name="comision_cuotas_propietario" class="form-control numeric"  value="<%=contrato.getComision_cuotas_propietario()%>">
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
                                                                      <input type="text" id="comision_mensual_propietario" name="comision_mensual_propietario" class="form-control  numeric"  value="<%=contrato.getComision_mensual_propietario()%>">
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
                                                                <% for(Contrato_gasto cg: lstContrato_gasto) {
                                                                    String aplica = (cg.getId_aplica().equals(OptionsCfg.CLIENTE_TIPO_INQUILINO))?"Inquilino":"Propietario";
                                                                %>
                                                                <tr>
                                                                    <td><%=cg.getConcepto()%><input type="hidden" name="gasto_concepto" value="<%=cg.getConcepto()%>"></td>
                                                                    <td><%=aplica%><input type="hidden" name="gasto_aplica" value="<%=cg.getId_aplica()%>"></td>
                                                                    <td><%=cg.getImporte()%><input type="hidden" name="gasto_importe" value="<%=cg.getImporte()%>"></td>
                                                                    <td><%=cg.getCuotas()%><input type="hidden" name="gasto_cuota" value="<%=cg.getCuotas()%>"></td>
                                                                    <td class=""><span class="btn btn-xs btn-danger btn-circle btn-del"><span class="fa fa-trash-o"></span></span></td>
                                                                </tr>
                                                                <%}%>
                                                            </tbody>
                                                            <tfoot>
                                                                <tr>
                                                                    <td colspan="5">
                                                                <% if(lstContrato_gasto.size() == 0) {%>
                                                                     <!--  No se agreg&oacute; ning&uacute;n gasto adicional al contrato -->
                                                                    <span class="btn btn-sm btn-primary" id="btnGasto">Agregar Gasto </span>
                                                                     <% } else { %>

                                                                     <% } %>
                                                                </td></tr>
                                                                
                                                            </tfoot>
                                                        </table>
                                                    </div>
                                            </div> <!-- tabAdic-->
                                            
                                            <div class="tab-pane row" id="tabGarantes">
                                               <div class="col-lg-12">
                                                   <fieldset>
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
                                                   <fieldset>
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
                                                   <fieldset>
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
                                        </div> <!-- tabGarante -->

                                             <div class="tab-pane row " id="tabOtros">
                                                 <div class="col-lg-12 nopadding">
                                                      <div class="form-group ">
                                                        <label for="asegura_renta" class="col-lg-2 control-label"><span class="">Asegura Renta</span></label>
                                                        <div class="col-lg-3">
                                                            <% String asegura_renta = contrato.getAsegura_renta()==1?"checked":"";%>
                                                            <input type="checkbox" id="asegura_renta" name="asegura_renta" <%=asegura_renta%> />
                                                        </div>
                                                    </div>
                                                 </div>
                                                 <div class="col-lg-12">
                                                     <label class="control-label" for="observaciones">Observaciones</label>
                                                        <div class="input-control">
                                                              <textarea class="form-control" name="observaciones" id="observaciones"><%=contrato.getObservaciones()%></textarea>
                                                        </div>
                                                </div>
                                             </div>

                                             <div class="row">
                                                    <div class="col-lg-12">
                                                    <ul class="pager wizard">
                                                          <li class="previous first" style="display:none;"><a href="#">First</a></li>
                                                          <li class="previous"><a href="#" id="btnAnterior">Anterior</a></li>
                                                          <li class="next last" style="display:none;"><a href="#">Last</a></li>
                                                          <li class="next"><a href="#" id="btnSiguiente">Siguiente</a></li>
                                                  </ul>
                                                    </div>
                                              </div>
                                             <div class="row">
                                                <div class="col-lg-12">
                                                   <div class="form-actions">
                                                      <button type="submit" class="btn btn-primary" id="btnSubmit">Guardar</button>
                                                      <a  href="<%= PathCfg.CONTRATO%>"class="btn btn-default">Cancelar</a>
                                                  </div>
                                               </div>
                                            </div>
                                        </div> <!-- tab-content-->
					</div>
				</div><!--/col-->

			</div><!--/row-->




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
	

	<!-- theme scripts -->
	<script src="assets/js/custom.min.js"></script>
	<script src="assets/js/core.min.js"></script>

        <script src="assets/js/notify.min.js"></script>
        <script src="assets/js/jquery.bootstrap.wizard.min.js"></script>

        <script src="assets/js/bootbox.min.js"></script>
        <script src="assets/js/handlebars.runtime-v4.0.5.js"></script>
        
        <script src="assets/templates/agregarGasto.js"></script>
        <script src="assets/templates/agregarValor.js"></script>
        <script src="assets/templates/agregarMonto.js"></script>
        <script src="assets/templates/agregarLineaGasto.js"></script>
        <script src="assets/js/common-functions.js"></script>

<script>
    $(document).ready(function() {
  	$('#rootwizard').bootstrapWizard({
            onNext: mover,
            onPrevious:mover
        });
//        $('.btn-sel').click(function(){
//            var index = $(this).data('index');
//            buscarPropiedad({id:index});
//        });
        $('#btnValor').click(function(){
            $('#target').val('Valor')
            $('#mdlValor').modal('show');
        });
        $('#btnDocumento').click(function(){
            $('#target').val('Documento');
            $('#mdlDocumento').modal('show');            
        });
        $('#btnGasto').click(function(){agregarGasto("")});
        $('.btn-del').click(borrar);
        $('#meses').focusout(function (){
            var fecha_inicio = $('#fecha_inicio').val();
            var meses = $('#meses').val();
            $('#fecha_fin').val(calcularHasta(fecha_inicio,meses));
            $('#fecha_fin').datepicker('setDate',$('#fecha_fin').val());
        });

    $('#btnSubmit').click(submitForm);
});

function completarCliente(data){
    $('#id_inquilino').val(data.id);
    $('#carpeta').val(data.carpeta);
    $('#nombre').val(data.nombre);
    $('#apellido').val(data.apellido);
    $('#dni').val(data.dni);
    $('#cuil').val(data.cuil);
}

function completarPropiedad(data){
    $('#id_propiedad').val(data.id);
    $('#calle').val(data.calle);
    $('#prop_numero').val(data.numero);
    $('#piso').val(data.piso);
    $('#dpto').val(data.dpto);
    $('#barrio').val(data.barrio);
    $('#localidad').val(data.localidad);
    if(data.propietario) {
        $('#carpeta_propietario').val(data.propietario.carpeta);
        var apeNom = data.propietario.apellido + ", " + data.propietario.nombre;
        $('#nombre_propietario').val(apeNom);
    }
}

function agregarMonto(data){    
    var table = data.target==="Valor"?"#tblValor":"#tblDocumento";
    data.esValor = function(){return data.target==="Valor"};
    var template = Handlebars.templates["agregarMonto"];
    $(table).find('tbody').append(template(data));
    $('.btn-del').click(borrar);
}
function recuperarDatosGasto(){
    var data = {};
    data.concepto     = $('#gasto_concepto').val();
    data.aplica       = parseInt($('#gasto_aplica').val())===1?"Inquilino":"Propietario";
    data.importe      = $('#gasto_importe').val();
    data.cuotas       = $('#gasto_cuota').val();
    data.gasto_aplica = $('#gasto_aplica').val();
    data.gasto_cuota  = $('#gasto_cuota').val();
    return data;
}
                    
function agregarGasto(target){
    var title = "Agregar gasto";
    var table = "#tblGasto";
    var template = Handlebars.templates['agregarGasto'];
    bootbox.dialog({
        title: title,
        message: template({}),
        buttons: {
            success: {
                label: "Guardar",
                className: "btn-success",
                callback: function () {
                    var data = recuperarDatosGasto();
                    console.log(data);
                    var template = Handlebars.templates["agregarLineaGasto"];
                    $(table).find('tbody').append(template(data));
                    $('.btn-del').click(borrar);
                }
            },
            cancel: {
                label: "Cancelar",
                callback: function () {}
            }
        }
    });
}
function borrar(e){
    var $tr = $(this).parent().parent();
    bootbox.confirm("Esta seguro que desea eliminar el registro?",function(result){
         if(result) {
            $tr.remove();
         }
     });
}

function mover(tab, navigation, index){
//    console.log(index);
//    if(index===3){
//        $('#btnRow').css('display','block');
//        
//    } else $('#btnRow').css('display','none');
}
function recuperarValores(){
    var data = {};
    data.id_inquilino = $('#id_inquilino').val();
    data.id_propiedad = $('#id_propiedad').val();
    data.id_vendedor  = $('#id_vendedor').val();
    data.fecha_inicio = $('#fecha_inicio').val();
    data.fecha_fin    = $('#fecha_fin').val();
    data.meses        = $('#meses').val();
    data.numero       = $('#numero').val();
    return data;
}
function validar(data){
    var todoOk = true;
    todoOk &= validarInquilino();
    todoOk &= validarPropiedad();   
    todoOk &= validarFechasContrato();
    todoOk &= validarGarantes();
    todoOk &= validarAgenteRetencion();
    return todoOk;
}
function validarAgenteRetencion(){
    var $agente_retencion =$('#agente_retencion');
    return validarCampo($agente_retencion,"Debe seleccionar seleccionar si es agente retención",validarNoCero);
}
function validarGarantes(){
    var $garante_1_dni         =$('#garante_1_dni');
    var $garante_1_nombre      =$('#garante_1_nombre');
    var $garante_1_telefono    =$('#garante_1_telefono');
    var $garante_1_id_garantia =$('#garante_1_id_garantia');
    var $garante_2_dni         =$('#garante_2_dni');
    var $garante_2_nombre      =$('#garante_2_nombre');
    var $garante_2_telefono    =$('#garante_2_telefono');
    var $garante_2_id_garantia =$('#garante_2_id_garantia');
    var $garante_3_dni         =$('#garante_3_dni');
    var $garante_3_nombre      =$('#garante_3_nombre');
    var $garante_3_telefono    =$('#garante_3_telefono');
    var $garante_3_id_garantia =$('#garante_3_id_garantia');
    return validarCampo($garante_1_dni,"Debe seleccionar al menos un garante",validarNoVacio) &&
           validarCampo($garante_1_nombre,"Debe seleccionar al menos un garante",validarNoVacio) &&
           validarCampo($garante_1_telefono,"Debe seleccionar al menos un garante",validarNoVacio) &&
           validarCampo($garante_1_id_garantia,"Debe seleccionar al menos un garante",validarNoCero);
}
function validarInquilino(){
    var $id_inquilino = $('#id_inquilino');
    return validarCampo($id_inquilino,"Debe seleccionar el inquilino",validarNoCero)
}
function validarPropiedad(){
     var $id_propiedad = $('#id_propiedad');
    return validarCampo($id_propiedad,"Debe seleccionar la propiedad",validarNoCero);
}
function validarVendedor(){
    var $id_vendedor  = $('#id_vendedor');
    validarCampo($id_vendedor,"Debe seleccionar el vendedor",validarNoCero);
}
function validarFechasContrato(){
    var $fecha_inicio = $('#fecha_inicio');
    var $fecha_fin = $('#fecha_fin');
    var $meses     = $('#meses');
    var todoOk = true;
    todoOk &= validarCampo($fecha_inicio,"Ingrese la fecha de inicio del contrato",validarCampoFecha);
    todoOk &= validarCampo($fecha_fin,"Ingrese la fecha de fin del contrato",validarCampoFecha);
    todoOk &= validarNoNulo($meses,"Ingrese la cantidad de meses del contrato",validarNoCero);
    return todoOk;
}

function validarValoresContrato(){
    var $tabla = $('#tblValor');
    var filas = $tabla.find('tbody tr');
    for(var i =0;i<= filas.length;i++ ){
        var f = $(filas[i]);
        var fecha_inicio = $(f[0]);
        var fecha_fin = $(f[1]);
        var importe = $(f[2]);
    }

}
function validarDocumentos(){}

function validarNoNulo($campo,mensaje){
    if($campo ===undefined || $campo.val()==="" || parseInt($campo.val())===0) {
        $.notify(mensaje,"error");
        return false;
    }
    return true;
}
</script>
 
 <%@include file="mdl_propiedad.jsp" %>
 <%@include file="mdl_cliente.jsp" %>
 <%--<%@include file="mdl_propietario.jsp" %>--%>
 <%@include file="mdl_valor.jsp" %>
 <%@include file="mdl_documento.jsp" %>
 <%--<%@include file="mdl_gasto.jsp" %>--%>
</body>
</html>
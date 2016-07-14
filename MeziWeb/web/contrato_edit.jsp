
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

    TPropiedad tp = new TPropiedad();
    HashMap<String,String> mapFiltro = new HashMap<String,String>();

    HashMap<String,String> filtroVendedores = new HashMap<String,String>();

    mapFiltro.put("id_estado","1");
    List<Propiedad> lstPropiedades = tp.getListFiltro(mapFiltro);
    if (lstPropiedades==null) lstPropiedades = new ArrayList<Propiedad>();

    filtroVendedores.put("activo","1");
    List<Vendedor> lstVendedores = new TVendedor().getListFiltro(filtroVendedores);
    if(lstVendedores==null) lstVendedores = new ArrayList<Vendedor>();


    boolean con_propiedad = true;
    boolean con_cliente = true;
    boolean con_vendedor = true;
    if(propiedad==null){
        propiedad = new Propiedad();
        con_propiedad = false;
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
                                                    <li><a href="#tabOtros" data-toggle="tab">Otros datos</a></li>
                                                </ul>
                                            </div>
                                        <form role="form" method="POST">
                                        <div  class="tab-content box-content">
                                            <div class="tab-pane row active" id="tab1">
                                               <div class="col-lg-8">
                                                   <fieldset>
                                                       <legend>Datos del cliente</legend>
                                                   <div class="col-lg-12">
                                                        <div class="form-group row">
                                                                <div class="col-lg-2 nopadding">
                                                                    <div class="controls">
                                                                        <label class="control-label" for="id_inquilino">N&uacute;mero carpeta</label>
                                                                          <div class="input-group ">
                                                                            <input type="text" id="id_inquilino" name="id_inquilino" class="form-control" value="<%=cliente.getId()%>" readonly>
                                                                          </div>
                                                                    </div>
                                                                </div>
                                                                <div class="col-lg-4 ">
                                                                    <div class="controls">
                                                                        <label class="control-label" for="nombre">Nombre</label>
                                                                          <div class="input-group ">
                                                                            <input type="text" id="nombre" name="nombre" class="form-control" value="<%=cliente.getNombre()%>" readonly>
                                                                          </div>
                                                                    </div>
                                                                </div>
                                                                <div class="col-lg-4">
                                                                    <div class="controls">
                                                                        <label class="control-label" for="apellido">Apellido</label>
                                                                          <div class="input-group ">
                                                                            <input type="text" id="apellido" name="apellido" class="form-control" value="<%=cliente.getApellido()%>" readonly>
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
                                                        </div>
                                                    </div>
                                                </fieldset>
                                            </div>


                                        </div> <!-- tab1 -->

                                            <div class="tab-pane row " id="tabProp">
                                                <div class="col-lg-8">
                                                    <fieldset>
                                                    <legend>Datos de la propiedad</legend>
                                                     <div class="col-lg-12" >
                                                         <input type="hidden" name="id_propiedad" id="id_propiedad" value="<%=propiedad.getId()%>">

                                                        <div class="form-group row">
                                                                <div class="col-lg-6 nopadding">
                                                                    <div class="controls">
                                                                        <label class="control-label" for="calle">Calle</label>
                                                                          <div class="input-group col-lg-12 ">
                                                                            <input type="text" id="calle" name="calle" class="form-control" value="<%=propiedad.getCalle()%>" readonly>
                                                                          </div>
                                                                    </div>
                                                                </div>
                                                                <div class="col-lg-2 nopadding">
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
                                                    </fieldset>
                                                </div>
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
                                                    <fielset>
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
                                                                    <th>Importe</th>
                                                                    <th>Cuotas</th>
                                                                    <th>Acci&oacute;n</th>
                                                                </tr>
                                                            </thead>                                                            
                                                            <tbody>
                                                                <% for(Contrato_gasto cg :lstContrato_gasto) {
                                                                    String aplica = (cg.getId_aplica()==OptionsCfg.CLIENTE_TIPO_INQUILINO)?"Inquilino":"Propietario";

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
                                                            <tfoot><tr><td colspan="5"><span class="btn btn-sm btn-primary" id="btnGasto">Agregar Valor</span></td></tr></tfoot>
                                                        </table>
                                                    </div>
                                            </div> <!-- tabAdic-->
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
                                                          <li class="previous"><a href="#">Anterior</a></li>
                                                          <li class="next last" style="display:none;"><a href="#">Last</a></li>
                                                          <li class="next"><a href="#">Siguiente</a></li>
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
	<!--<script src="assets/js/daterangepicker.min.js"></script>-->
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
        $('#btnBuscarCliente').click(function(){
          var id = $('#id_inquilino_search').val();
          if(id!=='') buscarCliente({id: id});
          else {
              var docum = $('#dni_search').val();
              buscarCliente({documento: docum});
          }

        });
        $('.btn-sel').click(function(){
            var index = $(this).data('index');
            buscarPropiedad({id:index});
        });
        $('#btnValor').click(function(){
            $('#target').val('Valor')
            $('#mdlValor').modal('show');
            //agregarValor("Valor")
        });
        $('#btnDocumento').click(function(){
            $('#target').val('Documento')
            $('#mdlValor').modal('show');
            //agregarValor("Documento")
        });
        $('#btnGasto').click(function(){agregarGasto("")});
        $('.btn-del').click(borrar);
        $('#meses').focusout(function(){
            var fecha_inicio = $('#fecha_inicio').val();
            var meses = $('#meses').val();
            $('#fecha_fin').val(calcularHasta(fecha_inicio,meses));
            $('#fecha_fin').datepicker('setDate',$('#fecha_fin').val());
        });

    $('#btnSubmit').click(submitForm);
});


function buscarCliente(data){
    $.ajax({url: '<%=PathCfg.CLIENTE_SEARCH%>',
            data: data,
            method: 'POST',
            dataType: 'json',
            beforeSend:function(){
                completarCliente({id_inquilino: '',
                                  nombre: '',
                                  apellido: '',
                                  dni: '',
                                  cuil: '',
                                  id_tipo_persona: ''});
            },
            success:function(result){
                console.log(result);
                if(result.Result==='OK'){
                    completarCliente(result.Record);
                } else {
                    //bootbox.alert(result.Message);
                }
            }
    });
}
function completarCliente(data){
    $('#id_inquilino').val(data.id);
    $('#nombre').val(data.nombre);
    $('#apellido').val(data.apellido);
    $('#dni').val(data.dni);
    $('#cuil').val(data.cuil);
}
function buscarPropiedad(data){
    $.ajax({
        url:'<%=PathCfg.PROPIEDAD_SEARCH%>',
        data:data,
        method:'POST',
        dataType:'json',
        beforeSend:function(){
            completarPropiedad({id_propiedad: '',
                                calle: '',
                                numero: '',
                                piso: '',
                                dpto: '',
                                barrio: '',
                                localidad: ''});
            },
        success: function(result) {
            if(result.Result==='OK'){
                completarPropiedad(result.Record);
            } else {
                bootbox.alert(result.Message);
            }
        }
    });
}
function completarPropiedad(data){
    $('#id_propiedad').val(data.id);
    $('#calle').val(data.calle);
    $('#numero').val(data.numero);
    $('#piso').val(data.piso);
    $('#dpto').val(data.dpto);
    $('#barrio').val(data.barrio);
    $('#localidad').val(data.localidad);
}

function agregarValor(target){
    var title = target==="Valor"?"Agregar valor de contrato":"Agregar Documento";
    var table = target==="Valor"?"#tblValor":"#tblDocumento";

    bootbox.dialog({
                title: title,
                message: '<div class="row">  ' +
                    '<div class="col-md-12"> ' +
                    '<form class="form-vertical"> ' +
                     '<div class="form-group"> ' +
                        '<label class="col-md-4 control-label" for="valor_fecha_inicio">Desde</label> ' +
                        '<div class="col-md-8 input-group date date-picker"> ' +
                        '<input id="valor_fecha_inicio" type="text" class="form-control input-md date-input" value=""> ' +
                        '<span class="input-group-addon"><span class="fa fa-calendar"></span></span>' + 
                        '</div>' +
                     '</div>' +
                     '<div class="form-group"> ' +
                        '<label class="col-md-4 control-label" for="valor_meses">meses</label> ' +
                        '<div class="col-md-8 input-group"> ' +
                        '<input id="valor_meses" type="text" class="form-control input-md " value=""> ' +
                        '</div>' +
                     '</div>' +
                     '<div class="form-group"> ' +
                        '<label class="col-md-4 control-label" for="valor_fecha_fin">Hasta</label> ' +
                        '<div class="col-md-8 input-group date date-picker"> ' +
                        '<input id="valor_fecha_fin" type="text" class="form-control input-md date-input" value=""> ' +
                        '<span class="input-group-addon"><span class="fa fa-calendar"></span></span>' + 
                        '</div>' +
                     '</div>' +

                    '<div class="form-group"> ' +
                        '<label class="col-md-4 control-label" for="valor_importe">Importe</label>' +
                        '<div class="col-md-8 input-group"> ' +
                        '<input id="valor_importe" type="text" class="form-control input-md" value=""> ' +
                        '</div>' +
                        '</div> ' +
                    '</div>'+

                      '</div> ' +
                    '</form>' +
                    '</div>'+
                    '</div>',
                buttons: {
                    success: {
                        label: "Guardar",
                        className: "btn-success",
                        callback: function () {
                            var fecha_desde = $('#valor_fecha_inicio').val();
                            var fecha_hasta = $('#valor_fecha_fin').val();
                            var monto = $('#valor_importe').val();
                            if (!validarAnterior(fecha_desde,fecha_hasta)){
                                bootbox.alert("La fecha desde debe ser anterior a la fecha hasta");
                                return;
                            }
                            if(target==="Valor"){
                                var html_desde = fecha_desde + ' <input type="hidden" name="valor_desde" value="'+fecha_desde+'">';
                                var html_hasta = fecha_hasta + ' <input type="hidden" name="valor_hasta" value="'+fecha_hasta+'">';
                                var html_monto = monto + ' <input type="hidden" name="valor_monto" value="'+monto+'">';
                            } else {
                                var html_desde = fecha_desde +  ' <input type="hidden" name="documento_desde" value="'+fecha_desde+'">';
                                var html_hasta = fecha_hasta + ' <input type="hidden" name="documento_hasta" value="'+fecha_hasta+'">';
                                var html_monto = monto + ' <input type="hidden" name="documento_monto" value="'+monto+'">';
                            }
                            var html = '';
                            html += wrapTag('td',html_desde,'');
                            html += wrapTag('td',html_hasta,'');
                            html += wrapTag('td',html_monto,'');
                            var span = "<span class='btn btn-xs btn-danger btn-circle btn-del'><span class='fa fa-trash-o'></span>";
                            html += wrapTag('td',span,'');
                            html = wrapTag('tr',html,'');
                            $(table).find('tbody').append(html);
                            $('.btn-del').click(borrar);
                        }
                    },
                    cancel: {
                        label: "Cancelar",
                        callback: function () {}
                    }
                }
            }).init(function(){
        if($().mask) {
//            $('.date-picker').mask('99/99/9999');
            $('.hora').mask('99:99:99');
        }
        if($().datepicker) {
            $('.date-picker').datepicker({
                language: 'es',
                locale:'es-AR',
                format:'dd/mm/yyyy',
                dateFormat:'dd/mm/yyyy',
                autoclose: true
            });
//             $('.date-picker').on('changeDate', function(ev){
//                $(this).datepicker('hide');
//            });
        }
        $('#valor_meses').focusout(function(){
            var fecha_inicio = $('#valor_fecha_inicio').val();
            var meses = $('#valor_meses').val();
            $('#valor_fecha_fin').val(calcularHasta(fecha_inicio,meses));
            $('#valor_fecha_fin').datepicker('setDate',$('#valor_fecha_fin').val());
        });
    });
}

function agregarMonto(data){
    
    var table = data.target==="Valor"?"#tblValor":"#tblDocumento";
    var fecha_desde = data.fecha_desde;;
    var fecha_hasta = data.fecha_hasta;
    var monto = data.monto;
    
    if(data.target==="Valor"){
        var html_desde = fecha_desde + ' <input type="hidden" name="valor_desde" value="'+fecha_desde+'">';
        var html_hasta = fecha_hasta + ' <input type="hidden" name="valor_hasta" value="'+fecha_hasta+'">';
        var html_monto = monto + ' <input type="hidden" name="valor_monto" value="'+monto+'">';
    } else {
        var html_desde = fecha_desde +  ' <input type="hidden" name="documento_desde" value="'+fecha_desde+'">';
        var html_hasta = fecha_hasta + ' <input type="hidden" name="documento_hasta" value="'+fecha_hasta+'">';
        var html_monto = monto + ' <input type="hidden" name="documento_monto" value="'+monto+'">';
    }
    var html = '';
    html += wrapTag('td',html_desde,'');
    html += wrapTag('td',html_hasta,'');
    html += wrapTag('td',html_monto,'');
    var span = "<span class='btn btn-xs btn-danger btn-circle btn-del'><span class='fa fa-trash-o'></span>";
    html += wrapTag('td',span,'');
    html = wrapTag('tr',html,'');
    $(table).find('tbody').append(html);
    $('.btn-del').click(borrar);
                        
       
}
function agregarGasto(target){
    var title = "Agregar gasto";
    var table = "#tblGasto";
    bootbox.dialog({
                title: title,
                message: '<div class="row">  ' +
                    '<div class="col-md-12"> ' +
                    '<form class="form-vertical"> ' +
                     '<div class="form-group"> ' +
                        '<label class="col-md-4 control-label" for="gasto_concepto">Concepto</label> ' +
                        '<div class="col-md-8"> ' +
                        '<input id="gasto_concepto" type="text" class="form-control input-md" value=""> ' +
                     '</div>' +
                     '<div class="form-group"> ' +
                        '<label class="col-md-4 control-label" for="gasto_aplica">Aplica</label> ' +
                        '<div class="col-md-8"> ' +
                        '<select id="gasto_concepto" type="text" class="form-control input-md"> ' +
                        '<option value="1">Inquilino</option>' +
                        '<option value="2">Propietario</option>' +
                        '</select>' +
                     '</div>' +
                      '<div class="form-group"> ' +
                        '<label class="col-md-4 control-label" for="gasto_cuota">Cuotas</label>' +
                        '<div class="col-md-8"> ' +
                        '<input id="gasto_cuota"  type="text" class="form-control input-md" value=""> ' +
                        '</div> ' +
                    '</div>'+
                    '<div class="form-group"> ' +
                        '<label class="col-md-4 control-label" for="gasto_importe">Importe</label>' +
                        '<div class="col-md-8"> ' +
                        '<input id="gasto_importe" type="text" class="form-control input-md" value=""> ' +
                        '</div> ' +
                    '</div>'+

                      '</div> ' +
                    '</form>' +
                    '</div>'+
                    '</div>',
                buttons: {
                    success: {
                        label: "Guardar",
                        className: "btn-success",
                        callback: function () {
                            var concepto = $('#gasto_concepto').val();
                            var aplica   = $('#gasto_aplica').val()===1?"Inquilino":"Propietario";
                            var importe  = $('#gasto_importe').val();
                            var cuotas   = $('#gasto_cuota').val();

                            var html_concepto = concepto + '<input type="hidden" name="gasto_concepto" value="'+concepto+'">';
                            var html_aplica   = aplica   + '<input type="hidden" name="gasto_aplica"  value="'+$('#gasto_aplica').val()+'">';
                            var html_cuotas   = cuotas   + '<input type="hidden" name="gasto_cuota"   value="'+$('#gasto_cuota').val()+'">';
                            var html_importe  = importe  + '<input type="hidden" name="gasto_importe" value="'+importe+'">';

                            var html = '';
                            html += wrapTag('td',html_concepto,'');
                            html += wrapTag('td',html_aplica,'');
                            html += wrapTag('td',html_importe,'');
                            html += wrapTag('td',html_cuotas,'');
                            var span = "<span class='btn btn-xs btn-danger btn-circle btn-del'><span class='fa fa-trash-o'></span>";
                            html += wrapTag('td',span,'');
                            html = wrapTag('tr',html,'');
                            $(table).find('tbody').append(html);
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
    if(index===3){
        $('#btnRow').css('display','block');
        console.log(tab,navigation,index);
    } else $('#btnRow').css('display','none');
}
function validar(data){
    var todoOk = true;
    var $id_inquilino = $('#id_inquilino');
    var $id_propiedad = $('#id_propiedad');
    var $id_vendedor = $('#id_vendedor');
    var $fecha_inicio = $('#fecha_inicio');
    var $fecha_fin = $('#fecha_fin');
    var $meses = $('#meses');
    var $numero = $('#numero');

    todoOk &= validarCampo($id_inquilino,"Debe seleccionar el inquilino",validarCero);
    todoOk &= validarCampo($id_propiedad,"Debe seleccionar la propiedad",validarCero);
    todoOk &= validarCampo($fecha_inicio,"Ingrese la fecha de inicio del contrato",validarCampoFecha);
    todoOk &= validarCampo($fecha_fin,"Ingrese la fecha de fin del contrato",validarCampoFecha);
    todoOk &= validarNoNulo($meses,"Ingrese la cantidad de meses del contrato",validarCero);
    todoOk &= validarCampo($id_vendedor,"Debe seleccionar el vendedor",validarCero);

    return todoOk;
}

function validarValores(){
    var $tabla = $('#tblValor');
    var filas = $tabla.find('tbody tr');
    for(var i =0;i<= filas.length;i++ ){
        var f = $(filas[i]);
        var fecha_inicio = $(f[0]);
        var fecha_fin = $(f[1]);
        var importe = $(f[2]);
    }

}



function validarDocumentos(){

}
function validarNoNulo($campo,mensaje){
    if($campo ===undefined || $campo.val()==="" || parseInt($campo.val())===0) {
        $.notify(mensaje,"error");
        return false;
}
return true;
}
//function loadDataPropiedad(data){
//            var $tabla = $('#tblPropiedades');
//            //$tabla.DataTable().destroy();
//            data.id_estado = 1;
//            data.id_operadion = 1;
//            $.ajax({
//               url: '<%= PathCfg.PROPIEDAD_LIST %>',
//               data: data,
//               method:"POST",
//               dataType: "json",
//               beforeSend:function(){
//                    var cant_cols = $tabla.find('thead th').length;
//                    $tabla.find('tbody').html("<tr><td colspan='" + cant_cols + "'><center><img src='assets/img/ajax-loader.gif'/></center></td></tr>");
//               },
//               success: function(result) {
//                   if(result.Result === "OK") {
//
//                       $tabla.find('tbody').html(createTablePropiedad(result.Records));
//                        $('.btn-del').click(borrar);
//                   }
//               }
//           });
//    }
//function createTablePropiedad(data){
//        var html = "";
//        for(var i = 0;i< data.length;i++){
//           html +="<tr class=''>";
//           d = data[i];
//           html +=wrapTag('td',d.tipo_inmueble,'');
//           html += wrapTag('td',d.calle + ' ' + d.numero,'');
////           html += wrapTag('td',d.barrio,'');
////           html += wrapTag('td',d.precio,'');
//           html += wrapTag('td',d.propietario,'');
//
//           var htmlSel = "<span class='btn btn-xs btn-primary btn-circle btn-sel'  data-index='"+ d.id + "' ><span class='fa fa-plus'></span>";
//           html +='<td style="width:75px"  >' + htmlSel + '</td>';
////         html +=wrapTag('td',htmlEdit + htmlDel,'');
//           html +="</tr>";
//       }
//       return html;
//    }
</script>
 <%@include file="mdl_cliente.jsp" %>
 <%@include file="mdl_propiedad.jsp" %>
 <%@include file="mdl_valor.jsp" %>
 <%--<%@include file="mdl_gasto.jsp" %>--%>
</body>
</html>
<%@page import="transaccion.TCliente"%>
<%@page import="bd.Cliente"%>
<%@page import="java.util.HashMap"%>
<%@page import="bd.Localidad"%>
<%@page import="bd.Localidad"%>
<%@page import="java.util.ArrayList"%>
<%@page import="transaccion.TBarrio"%>
<%@page import="bd.Barrio"%>
<%@page import="java.util.List"%>
<%@page import="bd.Propiedad"%>
<%@page import="transaccion.TLocalidad"%>
<%
Propiedad p = (Propiedad) request.getAttribute("propiedad");
boolean nuevo = false;

if(p==null) {
    p = new Propiedad();
    nuevo = true;
    p.setLatitud(-39.0297688f);
    p.setLongitud(-67.5819531f);
}
if(p.getLatitud()==0f || p.getLongitud()==0f){
    p.setLatitud(-39.0297688f);
    p.setLongitud(-67.5819531f);
}
String action = PathCfg.PROPIEDAD_EDIT;
if(!nuevo) action += "?id=" + p.getId();
HashMap<String,String> mapFiltro = new HashMap();
if (p.getId_localidad()!=0) mapFiltro.put("id_localidad",p.getId_localidad().toString());
else mapFiltro.put("id_localidad","1");

List<Barrio> lstBarrios = new TBarrio().getListFiltro(mapFiltro);
if(lstBarrios == null) lstBarrios = new ArrayList();

String chkCocina       = p.getCocina()!=0?"checked":"";
String chkLiving       = p.getLiving()!=0?"checked":"";
String chkPatio        = p.getPatio()!=0?"checked":"";
String chkGarage       = p.getGarage()!=0?"checked":"";

String chkComedor      = p.getComedor()!=0?"checked":"";
String chkEntrada_auto = p.getEntrada_auto()!=0?"checked":"";
String chkPileta       = p.getPileta()!=0?"checked":"";

List<Localidad> lstLoc = new TLocalidad().getList();

Cliente cliente = new TCliente().getById(p.getId_propietario());
if(cliente==null) cliente = new Cliente();
String apenom = cliente.getId()==0?"":cliente.getApellido() + ", " + cliente.getNombre();
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <%@include file="tpl_head.jsp" %>
    
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
					<div class="box">
                                            <div class="box-header">
                                                <h2><i class="fa fa-edit"></i><% if(nuevo){%>Nuevo<% } else {%> Editar <% }%> propiedad</h2>
                                                
                                                <ul id="tabs" class="nav nav-tabs" data-tabs="tabs">
                                                    <li class="active"><a href="#tab1" data-toggle="tab">Datos B&aacute;sicos</a></li>
                                                    <li class=""><a href="#tabUbi" data-toggle="tab">Ubicaci&oacute;n</a></li>
                                                    <li class=""><a href="#tab2" data-toggle="tab">Comodidades</a></li>
                                                    <li class=""><a href="#tabServ" data-toggle="tab">Servicios</a></li>
                                                    <li class=""><a href="#tabImg" data-toggle="tab">Imagenes</a></li>
                                                    <!--<li class=""><a href="#tabPropietario" data-toggle="tab">Propietarios</a></li>-->
                                                    <li><a href="#tab3" data-toggle="tab">Observaciones</a></li>
                                                </ul>
                                            </div>
                                        <form role="form" action="<%=action%>" method="POST">
                                        <% if(!nuevo) {%>
                                             <input type="hidden" name="id" id="id" class="form-control" value="<%= p.getId() %>" >                                                 
                                        <% }%>
                                        <div  class="tab-content box-content">   
                                            <div class="tab-pane row active" id="tab1">
                                                <div class="col-lg-4">
                                                    <% if(!nuevo) {%>
                                                        <div class="form-group">
                                                            <label class="control-label" for="codigo">C&oacute;digo propiedad</label>
                                                            <div class="controls">
                                                                  <div class="input-group col-lg-12">
                                                                    <input type="text" name="codigo" id="codigo" class="form-control" value="<%=p.getId()%>" readonly>
                                                                  </div>
                                                            </div>
                                                        </div>
                                                    <% } %>
                                                     <div class="col-lg-12">
                                                        <div class="form-group row  ">
                                                            <label class="control-label" for="id_tipo_inmueble">Tipo inmueble</label>
                                                            <div class="controls">
                                                                  <div class="input-group col-lg-12">
                                                                    <select type="text" id="id_tipo_inmueble" name="id_tipo_inmueble" class="form-control">
                                                                        <option value="1"> Casa</option>
                                                                        <option value="2" selected> Departamento</option>
                                                                        <option value="3"> Terreno</option>                                                                            
                                                                        <option value="4"> Local comercial</option>                                                                            
                                                                    </select>
                                                                  </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                     <div class="col-lg-12  nopadding ">
                                                         <div class="form-group">
                                                            <label class="control-label" for="nomenclatura">Nomenclatura Catastral</label>
                                                            <div class="controls">
                                                                  <div class="input-group col-lg-12">
                                                                    <input type="text" name="nomenclatura" id="nomenclatura" class="form-control" value="<%=p.getNomenclatura()%>">
                                                                  </div>
                                                            </div>
                                                        </div>
                                                     </div>
                                                    <div class="col-lg-12  nopadding ">
                                                        <div class="form-group " >
                                                            <label class="control-label">Operaci&oacute;n</label>
                                                                
                                                                <div class="controls form-inline ">
                                                                  <label class="radio  control-label">
                                                                      Alquiler  
                                                                      <input type="radio" name="id_operacion" value="1" checked="">
                                                                  </label>
                                                                  <!--<div style="clear:both"></div>-->
                                                                  <label class="radio  control-label">
                                                                      Venta
                                                                        <input type="radio" name="id_operacion" value="2" >
                                                                  </label>
                                                                </div>
                                                        </div>
                                                    </div>
                                                     <div class="form-group">
                                                            <label class="control-label" for="precio">Precio referencia</label>
                                                            <div class="controls">
                                                                  <div class="input-group col-lg-5">
                                                                      <input type="text" name="precio" id="precio" class="form-control numeric" value="<%=p.getPrecio()%>" >
                                                                  </div>
                                                            </div>
                                                     </div>
                                                </div> <!-- col-lg-4-->
                                                
                                                 <div class="col-lg-8">
                                                     <div class="col-lg-12 col-md-12">
                                                        <div class="form-group row">
                                                            <div class="col-lg-9 nopadding">
                                                                <div class="controls">
                                                                    <label class="control-label" for="calle">Calle</label>
                                                                      <div class="input-group col-lg-12 ">
                                                                        <input type="text" id="calle" name="calle" class="form-control" value="<%=p.getCalle()%>">
                                                                      </div>
                                                                </div>
                                                            </div>
                                                            <div class="col-lg-2 nopadding">
                                                                <div class="controls">
                                                                    <label class="control-label" for="numero">N&uacute;mero</label>
                                                                      <div class="input-group ">
                                                                        <input type="text" id="numero" name="numero" class="form-control" value="<%= p.getNumero()%>">
                                                                      </div>
                                                                </div>
                                                            </div>
                                                        </div><!-- row -->
                                                     </div> <!-- col-lg-12 -->
                                                     <div class="col-lg-12 col-md-12">
                                                        <div class="form-group row">
                                                                <div class="col-lg-2 nopadding">
                                                                    <div class="controls">
                                                                        <label class="control-label" for="piso">Piso</label>
                                                                          <div class="input-group col-lg-12 ">
                                                                            <input type="text" id="piso" name="piso" class="form-control" value="<%=p.getPiso()%>">
                                                                          </div>
                                                                    </div>
                                                                </div>
                                                                <div class="col-lg-2 ">
                                                                    <div class="controls">
                                                                        <label class="control-label" for="dpto">Departamento</label>
                                                                          <div class="input-group ">
                                                                            <input type="text" id="dpto" name="dpto" class="form-control" value="<%=p.getDpto()%>">
                                                                          </div>
                                                                    </div>
                                                                </div>
                                                        </div><!-- row -->
                                                     </div> <!-- col-lg-12 -->
                                                    
                                                     <div class="form-group">
                                                            <label class="control-label" for="id_localidad">Localidad</label>
                                                            <div class="controls">
                                                                  <div class="input-group col-lg-12">
                                                                    <select type="text" name="id_localidad" id="id_localidad" class="form-control" >
                                                                        <option value="0"></option>
                                                                        <% for(Localidad l:lstLoc) {
                                                                            String selLocalidad =  (int) l.getId()== p.getId_localidad()?"selected":"";
                                                                        %>
                                                                        <option value="<%=l.getId()%>" <%=selLocalidad%>><%=l.getDescripcion()%></option>
                                                                        <% } %>
                                                                    </select>
                                                                  </div>
                                                            </div>
                                                     </div>
                                                      <div class="form-group">
                                                            <label class="control-label" for="id_barrio">Barrio</label>
                                                            <div class="controls">
                                                                  <div class="input-group col-lg-12">
                                                                    <select type="text" name="id_barrio" id="id_barrio" class="form-control" >
                                                                        <option value="0"></option>
                                                                        <% for(Barrio b:lstBarrios ) {
                                                                            String selBarrio = b.getId()==p.getId_barrio()?"Selected":"";
                                                                        %>                                                                        
                                                                        <option value="<%=b.getId()%>" <%=selBarrio%>> <%= b.getNombre()%></option>
                                                                        <% } %>
                                                                        
                                                                    </select>
                                                                  </div>
                                                            </div>
                                                     </div>
                                                        <input type="hidden" id="longitud" name="longitud" class="form-control" value="<%=p.getLongitud()%>" >
                                                        <input type="hidden" id="latitud" name="latitud" class="form-control" value="<%=p.getLatitud()%>" >
<!--                                                     <div class="col-lg-12 col-md-12">
                                                        <div class="form-group row">
                                                                <div class="col-lg-6 ">
                                                                    <div class="controls">
                                                                        <label class="control-label" for="longitud">Latitud</label>
                                                                          <div class="input-group col-lg-12 ">
                                                                            <input type="text" id="longitud" name="longitud" class="form-control" value="<%=p.getLongitud()%>" readonly>
                                                                          </div>
                                                                    </div>
                                                                </div>
                                                                <div class="col-lg-6 ">
                                                                    <div class="controls">
                                                                        <label class="control-label" for="latitud">Longitud</label>
                                                                          <div class="input-group ">
                                                                            <input type="text" id="latitud" name="latitud" class="form-control" value="<%=p.getLatitud()%>" readonly>
                                                                          </div>
                                                                    </div>
                                                                </div>
                                                        </div> row 
                                                     </div>  col-lg-12 -->
                                                <div class="col-lg-12 nopadding">
                                                    <div class="form-group">
                                                            <label class="control-label" for="cliente">Propietario</label>
                                                            <div class="controls">
                                                                  <div class="input-group col-lg-12">
                                                                      <input type="text" name="propietario" id="propietario" class="form-control" value="<%=apenom%>" readonly >
                                                                      <span class="btn btn-primary" data-toggle="modal" data-target="#mdlCliente">Buscar</span>
                                                                      <input type="hidden" name="id_propietario" id="id_propietario" class="form-control" value="<%=cliente.getId()%>" >
                                                                  </div>
                                                            </div>
                                                     </div>
                                                </div> 
                                                </div>
                                                                                                                     
                                            </div> <!-- tab1 -->
                                            <div class="tab-pane row" id="tabUbi">                                                
                                                <div class="col-lg-12" style="height: 470px;">
                                                   <div id="gmap" class="Flexible-container"></div> 
                                                </div>
                                            </div><!-- tab1 -->
                                            <div class="tab-pane" id="tab2"> <!-- Comodidades -->
                                                   <div class="col-lg-4">
                                                    <div class="form-group">
                                                        <label for="dormitorios" class="col-lg-8 control-label"><span class="">Dormitorios</span></label>
                                                        <div class="col-lg-4">
                                                            <input type="text" class="form-control numeric" id="dormitorios" name="dormitorios" value="<%=p.getDormitorios()%>"/>
                                                        </div>
                                                    </div>
                                                    
                                                       
                                                    <div class="form-group">
                                                        <label for="banos" class="col-lg-8 control-label"><span class="">Ba&ntilde;os</span></label>
                                                        <div class="col-lg-4">
                                                            <input type="text" class="form-control numeric" id="banos" name="banos"  value="<%=p.getBanos()%>"/>
                                                        </div>
                                                    </div>
                                                    <div class="form-group">
                                                        <label for="cocina" class="col-lg-8 control-label"><span class="">Cocina</span></label>
                                                        <div class="col-lg-4">
                                                            <input type="checkbox" id="cocina" name="cocina"  <%=chkCocina%>/>
                                                        </div>
                                                    </div>
                                                       <div class="form-group">
                                                        <label for="comedor" class="col-lg-8 control-label"><span class="" >Cocina comedor</span></label>
                                                        <div class="col-lg-4">
                                                            <input type="checkbox" class="" id="comedor" name="comedor" <%=chkComedor%>/>
                                                        </div>
                                                    </div>
                                                    <div class="form-group">
                                                        <label for="living" class="col-lg-8 control-label"><span class="" >Living</span></label>
                                                        <div class="col-lg-4">
                                                            <input type="checkbox" class="" id="living" name="living" <%=chkLiving%>/>
                                                        </div>
                                                    </div>   
                                                    <div class="form-group">
                                                        <label for="patio" class="col-lg-8 control-label"><span class=""  >Patio</span></label>
                                                        <div class="col-lg-4">
                                                            <input type="checkbox" class="" id="patio" name="patio " <%=chkPatio%>/>
                                                        </div>
                                                    </div>   
                                                    <div class="form-group">
                                                        <label for="garage" class="col-lg-8 control-label"><span class="">Garage</span></label>
                                                        <div class="col-lg-4">
                                                            <input type="checkbox" class="" id="garage" name="garage" <%=chkGarage%>/>
                                                        </div>
                                                    </div>
                                                        
                                                    <div class="form-group">
                                                        <label for="entrada_auto" class="col-lg-8 control-label"><span class="">Entrada auto</span></label>
                                                        <div class="col-lg-4">
                                                            <input type="checkbox" class="" id="entrada_auto" name="entrada_auto"  <%=chkEntrada_auto%> />
                                                        </div>
                                                    </div>  
                                                    <div class="form-group">
                                                        <label class="col-lg-8 control-label" for="pileta" ><span class="">Pileta</span></label>
                                                        <div class="col-lg-4">
                                                            <input type="checkbox" class="" id="pileta" name="pileta"  <%=chkPileta%> />
                                                        </div>
                                                    </div>
                                                    <div class="form-group">
                                                        <label for="sup_terreno" class="col-lg-8 control-label"><span class="">Sup. Terreno</span></label>
                                                        <div class="col-lg-4">
                                                            <input type="text" class="form-control numeric" id="sup_terreno" name="sup_terreno" value="<%=p.getSup_terreno()%>"/>
                                                        </div>
                                                    </div>
                                                    <div class="form-group">
                                                        <label for="sup_cubierta" class="col-lg-8 control-label"><span class="">Sup. Cubierta</span></label>
                                                        <div class="col-lg-4">
                                                            <input type="text" class="form-control numeric" id="sup_cubierta" name="sup_cubierta" value="<%=p.getSup_cubierta()%>"/>
                                                            
                                                        </div>
                                                    </div> 
<!--                                                       <div class="col-md-1 col-sm-2 col-xs-3">
                                                            <span class="switch switch-primary">
                                                              <input type="checkbox" class="switch-input" checked>
                                                              <span class="switch-label" data-on="Si" data-off="No"></span>
                                                              <span class="switch-handle"></span>
                                                            </span>
                                                        </div>-->
                                                   </div>
                                               </div><!-- tab2 -->
<!--                                                <div class="tab-pane row" id="tabPropietario">
                                                    <div class="row">
                                                        <div class="col-lg-12" >
                                                        <% if (nuevo) {%>
                                                            <h3 style="text-align: center"> Antes de ingresar los propietarios debe guardar la propiedad</h3>
                                                        <%} else { %>
                                                        <H3>Listado de propietarios <span id="btnNuevoProp" class="btn btn-primary" ><span class="fa fa-file" ></span> Nuevo</span></H3>
                                                        <table class="table table-bordered table-condensed table-striped" id="tblPropietario">
                                                        <colgroup>
                                                            <col span="1" style="width: 15%; text-align: right;">  Carpeta 
                                                            <col span="1" style="">  Nombre 
                                                            <col span="1" style="width:15%;">  DNI                                                         
                                                            <col span="1" style="width: 10%;text-align: center">
                                                            <col span="1" style="width: 24%;text-align: center">
                                                         </colgroup>
                                                       <thead>
                                                            <tr>
                                                                <th>Carpeta</th>
                                                                <th>Nombre</th>
                                                                <th>Dni</th>
                                                                <th>Titular</th>
                                                                <th>Acci&oacute;n</th>
                                                            </tr>
                                                        </thead>
                                                        <tbody>

                                                        </tbody>
                                                    </table>
                                                    <% } %>
                                                </div>
                                                    </div>
                                                </div> tabPropietario -->
                                               
                                               <div class="tab-pane " id="tabServ">
                                                   <% if (nuevo) {%>
                                                   <h3 style="text-align: center"> Antes de ingresar los servicios debe guardar la propiedad</h3>
                                                   <%} else { %>
                                                    <H3>Listado de servicios <span id="btnNuevo" class="btn btn-primary" ><span class="fa fa-file" ></span> Nuevo</span></H3>
                                                    <table class="table table-bordered table-condensed table-striped" id="tblServicio">
                                                    <colgroup>
                                                        <col span="1" style="width: 15%; text-align: right;"> <!-- Tipo -->
                                                        <col span="1" style="width: 13%;"> <!-- Lugar -->
                                                        <col span="1" style=""> <!-- Identificacion -->
                                                        <col span="1" style="width: 10%;text-align: center"> <!-- Activo-->                                                    
                                                        <col span="1" style="width: 10%;text-align: center">
                                                        <!--<col span="1" style="width: 24%;text-align: center">-->
                                                     </colgroup>
                                                   <thead>
                                                        <tr>
                                                            <th>Tipo</th>
                                                            <th>Recibe</th>
                                                            <th>Identificaci&oacute;n</th>
                                                            <th>Activo</th>
                                                            <th></th>
                                                        </tr>
                                                    </thead>
                                                    <tbody>

                                                    </tbody>
                                                </table>
                                                    <% } %>
                                               </div><!-- tab3 -->                                               
                                               <div class="tab-pane " id="tabImg">
                                                   <div class="col-lg-12">
                                                        <h2 style="text-align:center">En construcci&oacute;n</h2>
                                                   </div>
<!--                                                   <input type="hidden" name="latitud" value="">
                                                   <input type="hidden" name="longitud" value="">-->
                                                   
                                               </div><!-- tabImg -->
                                               <div class="tab-pane " id="tab3">
                                                   <div class="form-group " >
                                                        <label for="observaciones">Observaciones</label>
                                                        <textarea class="form-control" name="observaciones" id="observaciones"><%=p.getObservaciones()%></textarea>
                                                    </div> 
                                               </div><!-- tab3 -->
                                               
                                            <div class="row">
                                                <div class="col-lg-12">
                                                   <div class="form-actions">
                                                      <button type="submit" class="btn btn-primary">Guardar</button>
                                                      <a  href="<%= PathCfg.PROPIEDAD%>"class="btn btn-default">Cancelar</a>
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
<!--	<script src="assets/js/jquery.chosen.min.js"></script>-->
	<!--<script src="assets/js/jquery.cleditor.min.js"></script>-->
	<script src="assets/js/jquery.autosize.min.js"></script>
	<!--<script src="assets/js/jquery.placeholder.min.js"></script>-->
	<script src="assets/js/jquery.maskedinput.min.js"></script>
	<!--<script src="assets/js/jquery.inputlimiter.1.3.1.min.js"></script>-->
	<!--<script src="assets/js/bootstrap-datepicker.min.js"></script>-->
	<!--<script src="assets/js/bootstrap-timepicker.min.js"></script>-->
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
	
        <script src="assets/js/bootbox.min.js"></script>
        <script src="assets/js/common-functions.js"></script>

        <script src="http://maps.google.com/maps/api/js?sensor=false&libraries=geometry&v=3.7&key=AIzaSyD-jKlyAoMh8GxoIyaDbuvhI1WVw8XSpGA"></script>
        <script src="assets/js/mapplace/maplace-0.1.3.js"></script>


	<!-- end: JavaScript-->
 <script>
        var m ;
        $(document).ready(function(){
            var ApyKey = "AIzaSyD-jKlyAoMh8GxoIyaDbuvhI1WVw8XSpGA";

            var lat = $('#latitud').val();
            var lon = $('#longitud').val();

            if(window.Maplace){ 
                m = new Maplace({
                    locations: [{
                        lat: lat,
                        lon: lon,
                        zoom: 10
                    }],
                    set_center: [<%=p.getLatitud()%>,<%=p.getLongitud()%>],
                    map_options: {
                        mapTypeId: google.maps.MapTypeId.SATELLITE,
                        zoom: 14
                    },
                    controls_on_map: false,
                    listeners: {
                        click: function(map, event) {

                            placeMarker(event.latLng);
                        //map.setOptions({scrollwheel: true});
                    }
    }
                }).Load();
            } else $('#gmap').html("<h3 style='text-align:center'>En contrucci&oacute;n. Google Maps</h3>");
//            
            $('#id_localidad').change(selLocalidadChange);
            
//            var $value= $('#id_localidad').val();   
//            if ($value===0) {$('#id_barrio').html("");return;}
//            loadDataBarrio({id_localidad:$value});
            //loadData({id_propiedad:$('#id').val()});
            
        });
        function placeMarker(location) {
            console.log(location);
            var lat = location.G.toFixed(6);
            var lon = location.K.toFixed(6);

            $('#latitud').val(lat);
            $('#longitud').val(lon);
            
            m.RemoveLocations(m.ln-1);
            m.AddLocation({
                lat:lat,
                lon:lon,
    //            zoom:10
            },0,true);

        }
        function validar(){
//            var $fecha = $('#fecha');
//
//            if($fecha===undefined || $fecha.val()==="" || !validarFecha($fecha.val())){
//                bootbox.alert("Debe ingresar la fecha del certificado");
//                $fecha.parent().addClass("has-error");
//                return false;
//            } else $fecha.parent().removeClass("has-error");
            return true;
        }
    function selLocalidadChange(){
        var $value= $(this).val();   
        if ($value===0) {$('#id_barrio').html("");return;}
        loadDataBarrio({id_localidad:$value});
    }
    function loadDataBarrio(data){    
        $.ajax({
               url: '<%= PathCfg.BARRIO_LIST %>',
               data: data,
               method:"POST",
               dataType: "json",
               success: function(result) {
                   if(result.Result === "OK") {
                        $('#id_barrio').html(createSelectBarrio(result.Records));                       
                   }
               }
        });
    }
    function createSelectBarrio(data){
        var html = "<option value='0'>Seleccione el barrio</option>";
        for (var i=0;i<data.length;i++){
            d = data[i];                
            html += "<option value='" + d.id + "'>" + d.nombre+ "</option>";
        }
        return html;
    }
    function completarCliente(data){
        $('#id_propietario').val(data.id);
        $('#propietario').val(data.apellido + ", " + data.nombre);        
        
    }
    
//    function agregarServicio(data){
//        var checked = (data.activo)?"checked":"";
//        var title = data.id===0?"Nuevo servicio":"Editar servicio";
//        bootbox.dialog({
//                title: title,
//                message: '<div class="row">  ' +
//                    '<div class="col-md-12"> ' +                    
//                    '<form class="form-vertical"> ' +
//                    '<input id="id" name="id" type="hidden" class="" value=' + data.id + ' >' +
//                    '<div class="form-group"> ' +
//                        '<label class="col-md-4 control-label" for="nombre">Tipo:</label> ' +
//                        '<div class="col-md-8"> ' +
//                        '<select id="nombre" name="nombre" class="form-control input-md" > ' +
//                        '<option value=""></option>' +
//                        '</select>' + 
//                        '</div>' + 
//                    '</div>' + 
//                    '<div class="form-group"> ' +
//                        '<label class="col-md-4 control-label" for="nombre">Identificaci&oacute;n:</label> ' +
//                        '<div class="col-md-8"> ' +
//                        '<input id="nombre" name="nombre" type="text" class="form-control input-md" value="'+ data.nombre +'"> ' +
//                        '</div>' + 
//                    '</div>' +    
//                    '<div class="form-group"> ' +
//                        '<label class="col-md-4 control-label" for="nombre">Env&iacute;o:</label> ' +
//                        '<div class="col-md-8"> ' +
//                        '<select id="nombre" name="nombre" class="form-control input-md" > ' +
//                        '<option value="0"></option>' +
//                        '<option value="1">Propietario</option>' +
//                        '<option value="2">Oficina</option>' +
//                        '<option value="3">Inquilino</option>' +
//                        '</select>' + 
//                        '</div>' + 
//                    '</div>' +    
//                    '<div class="form-group"> ' +
//                        '<label class="col-md-4 control-label" for="activo">Activo</label> ' +
//                        '<div class="col-md-8"> ' +
//                        '<input id="activo" name="activo" type="checkbox" class="checkbox input-md" ' + checked + ' value="'+ data.activo +'"> ' +
//                        '</div>' +
//                      '</div> ' +  
//                    '</form>' + 
//                    '</div>'+
//                    '</div>',
//                buttons: {
//                    success: {
//                        label: "Guardar",
//                        className: "btn-success",
//                        callback: function () {
//                            var id     = $('#id').val();
//                            var nombre = $('#nombre').val();
//                            var activo = $('#activo').prop('checked')?'1':'';
//                            
//                            $.ajax({
//                                url:'<%= PathCfg.SERVICIO_EDIT%>',
//                                data: {id:id,nombre:nombre,activo:activo},
//                                method:'POST',
//                                dataType:'json',
//                                success:function(){
//                                    loadData({});
//                                }
//                                });
//                            //bootbox.alert("Nombre " + nombre + ". Email: <b>" + email + "</b>");
//                        }
//                    },
//                    cancel: {
//                        label: "Cancelar",
//                        callback: function () {                            
//                        }
//                    }
//                }
//            });
//    }    
    </script>	
    <script language="">
        $(document).ready(function(){
            var id_propiedad = $('#id_propiedad').val();
           loadData({id_propiedad:id_propiedad});
           $('#btnNuevo').click(function(){
                agregarServicio({id:0,nombre:'',activo:1});
            });
        });
        function loadData(data){
            var $tabla = $('#tblServicio');
            //$tabla.DataTable().destroy();
            $.ajax({
               url: '<%= PathCfg.SERVICIO_LIST %>',
               data: data,
               method:"POST",
               dataType: "json",
               beforeSend:function(){
                    var cant_cols = $tabla.find('thead th').length;
                    $tabla.find('tbody').html("<tr><td colspan='" + cant_cols + "'><center><img src='assets/img/ajax-loader.gif'/></center></td></tr>");
               },
               success: function(result) {
                   if(result.Result === "OK") {
                       $tabla.find('tbody').html(createTableServicio(result.Records));
                       $('.btn-del').click(borrarServicio);
                       $('.btn-edit').click(editarServicio);
                        
                   }
               }
           });
    }
    function borrarServicio(){
        var id = $(this).data('index');
        var $tr = $(this).parent().parent();
        deleteData('<%= PathCfg.SERVICIO_DEL %>',{id:id},function(result) {
                if(result.Result === "OK") {
                    $tr.remove();
                } else if (result.Message) bootbox.alert(result.Message);
        });
    }
    
   function createTableServicio(data){
        var html = "";
        for(var i = 0;i< data.length;i++){
           html +="<tr class=''>";
           d = data[i];
           html += wrapTag('td',d.tipo_servicio,'');
           html += wrapTag('td',d.envio,'');
           html += wrapTag('td',d.identificacion,'');
           var activo = d.activo==1?"Si":"No";
           html += wrapTag('td',activo,'');
           
           var htmlEdit = "<span class='btn btn-xs btn-circle btn-warning btn-edit' data-index='"+ d.id + "'  data-id_tipo_servicio='" + d.id_tipo_servicio + "' data-identificacion='" + d.identificacion + "' data-id_envio='" + d.id_envio + "' data-activo='"+ d.activo + "' ><span class='glyphicon glyphicon-edit fw'></span></span> ";
           var htmlDel  = "<span class='btn btn-xs btn-danger btn-circle btn-del' data-index='"+ d.id + "'><span class='fa fa-trash-o'></span></span>";
           html +='<td style="width:75px"  >' + htmlEdit + htmlDel + '</td>';
           html +="</tr>";
       }      
       return html;
    }
   function borrar(){
        var id = $(this).data('index');
        var $tr = $(this).parent().parent();
        deleteData('<%= PathCfg.SERVICIO_DEL %>',{id:id},function(result) {     
                if(result.Result === "OK") {
                    $tr.remove();
                } else if (result.Message) bootbox.alert(result.Message);
        });
    }
    </script>
    
    <jsp:include page="mdl_servicio.jsp" >
        <jsp:param name="id_propiedad" value="<%=p.getId()%>"/>
    </jsp:include>
    <%@include file="mdl_cliente.jsp" %>
</body>
</html>
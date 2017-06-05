<%@page import="transaccion.TCliente"%>
<%@page import="transaccion.TLocalidad"%>
<%@page import="java.util.List"%>
<%@page import="bd.Localidad"%>
<%@page import="bd.Cliente"%>
<%
    Cliente cliente = (Cliente) request.getAttribute("cliente");
    Boolean nuevo = false;
    if(cliente==null){
        cliente = new Cliente();
        cliente.setCarpeta(new TCliente().siguienteNumero());
        nuevo = true;
    }    
    String action = PathCfg.CLIENTE_EDIT;
    if(!nuevo) action += "?id=" + cliente.getId();
    String[] arrTipoPersona = {"","F&iacute;sica","Jur&iacute;dica"};
    List<Localidad> lstLoc = new TLocalidad().getList();

%>
<!DOCTYPE html>
<html lang="en">
<head>
    <%@include file="tpl_head.jsp" %>
    <link href="assets/css/star-rating.min.css" media="all" rel="stylesheet" type="text/css" />
    <!--<link href="assets/css/jquery.raty.css" media="all" rel="stylesheet" type="text/css" />-->
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
                                                <h2><i class="fa fa-edit"></i><% if(nuevo){%>Nuevo<% } else {%> Editar <% }%> cliente</h2>
                                                
                                                <ul id="tabs" class="nav nav-tabs" data-tabs="tabs">
                                                    <li class="active"><a href="#tab1" data-toggle="tab">Datos B&aacute;sicos</a></li>
                                                    <li><a href="#tab2" data-toggle="tab">Calificaci&oacute;n / Historia</a></li>
<!--                                                    <li><a href="#tab3" data-toggle="tab">Tab3</a></li>
                                                    <li><a href="#tab4" data-toggle="tab">Tab4</a></li>-->
                                                </ul>
                                            </div>
                                        <form role="form" action="<%=action%>" method="POST" novalidate>
                                            <% if(!nuevo) {%>
                                                 <input type="hidden" name="id" id="id" class="form-control" value="<%= cliente.getId() %>" >                                                 
                                            <% }%>
                                        <div  class="tab-content box-content">   
                                            <div class="tab-pane active row" id="tab1">    
                                                <div class="col-lg-8">

                                                    <div class="col-lg-12">
                                                        <div class="form-group row">
                                                                <div class="col-lg-2 nopadding">
                                                                    <div class="controls">
                                                                        <label class="control-label" for="carpeta">C&oacute;digo</label>
                                                                        <div class="input-group  col-lg-12 ">
                                                                            <input type="text" name="id" id="id" class="form-control" value="<%= cliente.getId() %>" readonly>
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                                <div class="col-lg-2 ">
                                                                    <div class="controls">
                                                                        <label class="control-label" for="carpeta">Nº Carpeta</label>
                                                                        <div class="input-group  col-lg-12 ">
                                                                            <input type="text" name="carpeta" id="carpeta" class="form-control" value="<%= cliente.getCarpeta() %>" >
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                                <div class="col-lg-4 ">
                                                                    <div class="controls">
                                                                        <label class="control-label" for="nombre">Nombre</label>
                                                                          <div class="input-group  col-lg-12 ">
                                                                            <input type="text" id="nombre" name="nombre" class="form-control"  value="<%=cliente.getNombre()%>">
                                                                          </div>
                                                                    </div>
                                                                </div>
                                                                <div class="col-lg-4 nopadding">
                                                                    <div class="controls">
                                                                        <label class="control-label" for="apellido">Apellido</label>
                                                                          <div class="input-group  col-lg-12 ">
                                                                            <input type="text" id="apellido" name="apellido" class="form-control"  value="<%=cliente.getApellido()%>">
                                                                          </div>
                                                                    </div>
                                                                </div>
                                                        </div><!-- row -->
                                                        </div>
                                                        <div class="row">
                                                            <div class="col-lg-12">
                                                                <div class=" col-lg-6 nopadding">
                                                                  <label class="control-label" for="id_localidad">Localidad</label>
                                                                  <div class="controls">
                                                                        <div class="input-group col-lg-12">
                                                                        <select type="text" name="id_localidad" id="id_localidad" class="form-control " >
                                                                            <option value="0"></option>
                                                                            <% for(Localidad l:lstLoc) {
                                                                                String selLocalidad = (cliente.getId_localidad().equals(l.getId()))?"selected":"";
                                                                            %>
                                                                            <option value="<%=l.getId()%>" <%=selLocalidad%>><%=l.getDescripcion()%></option>
                                                                        <% } %>

                                                                    </select>
                                                                        </div>
                                                                  </div>
                                                                </div>
                                                                    <div class=" col-sm-6">
                                                                  <label class="control-label" for="direccion">Direcci&oacute;n</label>
                                                                  <div class="controls">
                                                                        <div class="input-group  col-lg-12 ">
                                                                          <input type="text" id="direccion" name="direccion" class="form-control"  value="<%= cliente.getDireccion()%>" >
                                                                        </div>
                                                                  </div>
                                                                </div>
                                                            </div>

                                                        </div>
                                                                        
                                                        <div class="col-lg-12">
                                                            <div class="form-group row">
                                                                <div class="col-lg-6 nopadding">
                                                                    <label class="control-label" for="dni">DNI</label>
                                                                    <div class="controls">
                                                                        <div class="input-group col-lg-12 ">
                                                                          <input type="text" id="dni" name="dni"class="form-control" value="<%=cliente.getDni()%>" >
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                        
                                                                <div class="col-lg-6 ">
                                                                    <label class="control-label" for="cuil">CUIT / CUIL</label>
                                                                    <div class="controls">
                                                                        <div class="input-group col-lg-12 ">
                                                                          <input type="text" id="cuil" name="cuit" class="form-control" value="<%= cliente.getCuil()%>">
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </div>
<!--                                                         <div class="col-lg-12">
                                                            <div class="form-group row  ">
                                                                <label class="control-label" for="id_tipo_persona">Tipo Persona</label>
                                                                <div class="controls">
                                                                      <div class="input-group col-sm-6">
                                                                        <select type="text" id="id_tipo_persona" name="id_tipo_persona" class="form-control">
                                                                            <% for(int i = 1;i< arrTipoPersona.length;i++ ) {
                                                                                String selected = (i==cliente.getId_tipo_persona())? "selected":"";
                                                                            %>
                                                                            <option value="<%=i%>" <%=selected%>><%=arrTipoPersona[i]%></option>
                                                                            <%}%>
                                                                        </select>
                                                                      </div>
                                                                </div>
                                                            </div>
                                                        </div>-->

                                                    </div>
                                                <div class="col-lg-4">
                                                     <div class="form-group">
                                                            <label class="control-label" for="lugar_trabajo">Lugar de trabajo</label>
                                                            <div class="controls">
                                                                  <div class="input-group col-sm-8">
                                                                    <input type="text" id="lugar_trabajo" name="lugar_trabajo" class="form-control"  value="<%= cliente.getLugar_trabajo()%>">
                                                                  </div>
                                                            </div>
                                                     </div>
                                                    <div class="form-group">
                                                            <label class="control-label" for="telefono">Tel&eacute;fono</label>
                                                            <div class="controls">
                                                                  <div class="input-group col-sm-8">
                                                                    <input type="text" id="telefono" name="telefono" class="form-control"  value="<%= cliente.getTelefono()%>" >
                                                                  </div>
                                                            </div>
                                                     </div>
                                                </div>
                                                    </div><!-- tab1 -->
                                                    <div class="tab-pane" id="tab2">
                                                        <div class="row">
                                                            <div class="col-lg-12">
                                                                <div class="form-group">
                                                                    <label class="control-label" for="calificacion">Calificaci&oacute;n</label>
                                                                <div class="controls">
                                                                      <div class="input-group ">
                                                                          <input  type="number" class="" name="calificacion" id="calificacion" min=1 max=5 step=1 data-size="xxs" data-rtl="false" value="<%=cliente.getCalificacion()%>">
                                                                      </div>
                                                                </div>
                                                            </div>
                                                            </div>
                                                        </div>
                                                        <div class="row">
                                                            <div class="col-lg-12">
                                                                <div class="form-group">
                                                                    <label class="control-label" for="observaciones">Observaciones</label>
                                                                    <textarea   class="form-control" name="observaciones" id="observaciones"><%=cliente.getObservaciones()%></textarea>
                                                            </div>
                                                            </div>
                                                        </div>
                                                    </div>
<!--                                                    <div class="tab-pane active" id="tab3"></div> tab3 
                                                    <div class="tab-pane active" id="tab4"></div> tab4 -->
                                            <div class="row">
                                                <div class="col-lg-12">
                                                   <div class="form-actions">
                                                      <button type="submit" id="btnSubmit" class="btn btn-primary">Guardar</button>
                                                      <a  href="<%= PathCfg.CLIENTE%>"class="btn btn-default">Cancelar</a>
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
	<script src="assets/js/jquery.sparkline.min.js"></script>
	<script src="assets/js/jquery.chosen.min.js"></script>
	<script src="assets/js/jquery.cleditor.min.js"></script>
	<script src="assets/js/jquery.autosize.min.js"></script>
	<script src="assets/js/jquery.placeholder.min.js"></script>
	<script src="assets/js/jquery.maskedinput.min.js"></script>
	<script src="assets/js/jquery.inputlimiter.1.3.1.min.js"></script>
	<script src="assets/js/bootstrap-datepicker.min.js"></script>
	<script src="assets/js/bootstrap-timepicker.min.js"></script>
	<script src="assets/js/moment.min.js"></script>
	<script src="assets/js/daterangepicker.min.js"></script>
	<script src="assets/js/jquery.hotkeys.min.js"></script>
	<script src="assets/js/bootstrap-wysiwyg.min.js"></script>
	<script src="assets/js/bootstrap-colorpicker.min.js"></script>
	<script src="assets/js/star-rating.js" type="text/javascript"></script>	
        <script src="assets/js/star-rating_locale_es.js" type="text/javascript"></script>
        <!--<script src="assets/js/jquery.raty.js" type="text/javascript"></script>-->-->
	<!-- theme scripts -->
	<script src="assets/js/custom.min.js"></script>
	<script src="assets/js/core.min.js"></script>
	
	<!-- inline scripts related to this page -->
	<script src="assets/js/pages/form-elements.js"></script>
        
	<!-- end: JavaScript-->
        <script src="assets/js/bootbox.min.js"></script>	
        <script src="assets/js/notify.min.js"></script>
        
        <script src="assets/js/common-functions.js"></script>
        
        <script>
            $(document).ready(function(){
                $('#calificacion').rating({min:0, max:5, 
                    step:1, size:'xxs',
                    language:'es',
                    
                });
               //$('form').submit(submitForm);
               $('#btnSubmit').click(submitForm);
               $('#cuil').mask('99-99999999-9');
            });
            
     function validar(){
        var $nombre = $('#nombre');
        var $apellido = $('#apellido');
        var $dni = $('#dni');
        var $cuil = $('#cuil');
        var todoOk = true;
        todoOk &= validarCampo($nombre,"Ingrese el nombre del cliente",function(){return true;});
        todoOk &= validarCampo($apellido,"Ingrese el apellido del cliente",function(){return true;});
        todoOk &= validarCampo($dni,"Ingrese el dni del cliente",function(){return true;});
//        todoOk &= validarCampo($cuil,"Ingrese el cuil del cliente",function(e){ return !validaCuit(e.val());});
        console.log(todoOk);
        return todoOk;
    }
        </script>
</body>
</html>
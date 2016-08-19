<%@page import="java.util.List"%>
<%@page import="bd.Tipo_usuario"%>
<%@page import="transaccion.TTipo_usuario"%>
<%@page import="bd.Usuario"%>
<%
    Usuario usuario = (Usuario) request.getAttribute("usuario");
    boolean nuevo = false;
    if (usuario==null) {
        usuario = new Usuario();
        nuevo = true;
    }
    String action = PathCfg.USUARIO_EDIT;
    action += (!nuevo)?"?id="+usuario.getId():"";
    List<Tipo_usuario> listTipos = new TTipo_usuario().getList();
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
                                                <h2><i class="fa fa-edit"></i><% if(nuevo) {%>Nuevo<%}else{%>Editar<%}%> Usuario </h2>
                                        <ul id="tabs" class="nav nav-tabs" data-tabs="tabs">
                                            <li class="active"><a href="#tab1" data-toggle="tab">Datos b&aacute;sicos</a></li>
                                        </ul>
                                    </div>

                                          <form role="form" method="POST" action="<%=action%>" >
                                          
                                            <% if(!nuevo) { %>
                                            <input type="hidden" name="id" id ="id" value="<%= usuario.getId() %>">
                                            <%}%>
                                        <div  class="tab-content box-content">
                                            <div class="tab-pane active" id="tab1">
                                                <div class="row" >
                                                <div class="col-lg-6" >
                                                <div class="form-group">
                                                    <label for="nombre">Email</label>
                                                    <% String disabled = !nuevo?"disabled":"";%>
                                                    <input class="form-control" name="usu_mail" id="usu_mail"  <%= disabled%> value="<%= usuario.getUsu_mail() %>">
                                                </div>
                                                <%  if(nuevo) {%>
                                                    <div class="form-group">
                                                        <label for="nombre">Password</label>
                                                        <input class="form-control" name="usu_password" id="usu_password" type="password" value="">
                                                    </div>
                                                    <div class="form-group">
                                                        <label for="nombre">Repita el password</label>
                                                        <input class="form-control" name="usu_password2" id="usu_password2" type="password" value="">
                                                    </div>
                                                <% } %>

                                                <div class="form-group">
                                                    <label for="">Activo</label>

                                                    <% String checked = (nuevo ||usuario.getUsu_activo()!=0)?"checked":"";%>
                                                    <input type="checkbox" name="usu_activo" id="usu_activo" <%=checked%> >
                                               </div>
                                               <div>
                                                   <label for="id_tipo_usuario">Tipo usuario</label>
                                                   <select id="id_tipo_usuario" name="id_tipo_usuario" class="form-control">
                                                       <% for(Tipo_usuario tipo:listTipos){
                                                           String tipoSelected = tipo.getId().equals(usuario.getId_tipo_usuario())?"selected":"";
                                                       %>
                                                       <option value="<%=tipo.getId()%>" <%=tipoSelected%>><%=tipo.getDescripcion()%></option>
                                                       <% }%>
                                                   </select>
                                               </div>
                                          </div>
                                          </div> <!--row -->
                                        </div>
<!--                                               <div class="tab-pane" id="tab2">
                                                  <div class="form-group " >
                                                        <label for="comodidades">Comodidades</label>
                                                        <textarea class="form-control" name="comodidades" id="comodidades"></textarea>
                                                    </div>
                                               </div> tab2 


                                               <div class="tab-pane " id="tab3">
                                                   <div class="form-group " >
                                                        <label for="observaciones">Observaciones</label>
                                                        <textarea class="form-control" name="observaciones" id="observaciones"></textarea>
                                                    </div>
                                               </div> tab3 -->
                                               <!--<div class="tab-pane " id="tab4"></div> tab4 -->
                                               <br>
                                            <div class="row">
                                                <div class="col-lg-12">
                                                   <div class="form-actions">
                                                      <button type="submit" class="btn btn-primary">Guardar</button>
                                                      <a  href="<%= PathCfg.USUARIO%>"class="btn btn-default">Cancelar</a>
                                                  </div>
                                               </div>
                                            </div>
                                        </div> <!-- tab-content-->
                                        </form>
					</div>
				</div><!--/col-->

			</div><!--/row-->




			</div>
			<!-- end: Content -->

				</div><!--/row-->

	</div><!--/container-->


	<div class="modal fade" id="myModal">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
					<h4 class="modal-title">Modal title</h4>
				</div>
				<div class="modal-body">
					<p>Here settings can be configured...</p>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
					<button type="button" class="btn btn-primary">Save changes</button>
				</div>
			</div><!-- /.modal-content -->
		</div><!-- /.modal-dialog -->
	</div><!-- /.modal -->

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


	<!-- theme scripts -->
	<script src="assets/js/custom.min.js"></script>
	<script src="assets/js/core.min.js"></script>

	<!-- inline scripts related to this page -->
	<!--<script src="assets/js/pages/form-elements.js"></script>-->

	<!-- end: JavaScript-->

</body>
</html>
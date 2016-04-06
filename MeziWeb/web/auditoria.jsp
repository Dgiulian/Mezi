<%@page import="java.util.ArrayList"%>
<%@page import="transaccion.TModulo"%>
<%@page import="bd.Modulo"%>
<%@page import="java.util.List"%>
<%@page import="bd.Usuario"%>
<%@page import="bd.Usuario"%>
<%
    Usuario usuario = (Usuario) request.getAttribute("usuario");
    if (usuario==null)
        usuario = new Usuario();
    List<Modulo> lstModulos = new TModulo().getList();
    if(lstModulos==null) lstModulos = new ArrayList<Modulo>();
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
                                            <H3>Auditoria de Sistema</H3>
                                    </div>
                                    <div  class="box-content">
                                        <input type="hidden" name="id_usuario" id="id_usuario" value="<%=usuario.getId()%>">
<!--                                        <div class="col-lg-2">
                                            <div class="form-group row  ">
                                                <label class="control-label" for="id_tipo_usuario">Tipo usuario</label>
                                                <div class="controls">
                                                      <div class="input-group col-lg-12">
                                                        <select type="text" id="id_tipo_usuario" name="id_tipo_usuario" class="form-control">
                                                            <option value="0" selected> Todos</option>
                                                            <option value="1"> Crear</option>
                                                            <option value="2" > Borrar</option>
                                                            <option value="3"> Eliminar</option>                                                                            
                                                        </select>
                                                      </div>
                                                </div>
                                            </div>
                                        </div>-->
                                        <div class="col-lg-2">
                                            <div class="form-group   ">
                                                <label class="control-label" for="id_accion">Acci&oacute;n</label>
                                                <div class="controls">
                                                      <div class="input-group  col-lg-12">
                                                        <select type="text" id="id_accion" name="id_accion" class="form-control">
                                                            <option value="0" selected> Todos</option>
                                                            <option value="1"> Crear</option>
                                                            <option value="2" > Borrar</option>
                                                            <option value="3"> Modificar</option>                                                                            
                                                        </select>
                                                      </div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="col-lg-2">
                                            <div class="form-group  ">
                                                <label class="control-label" for="id_modulo">M&oacute;dulo</label>
                                                <div class="controls">
                                                      <div class="input-group  col-lg-12">
                                                        <select type="text" id="id_modulo" name="id_modulo" class="form-control">
                                                            
                                                            <option value="0" selected> Todos</option>
                                                            <% for(Modulo m: lstModulos) {%>                                                            
                                                            <option value="<%=m.getId()%>"> <%=m.getNombre()%></option>
                                                            <% }%>
                                                            
                                                        </select>
                                                      </div>
                                                </div>
                                            </div>
                                        </div>
                                        <table class="table table-striped table-bordered table-hover" id="tblAuditoria">
                                            <colgroup>
                                                <col span="1" style="width:20%;"></col>
                                                <col span="1" style="width:20%"></col>
                                                <col span="1" style="width:20%"></col>
                                                <col span="1" style="width:20%; "></col>
                                                <col span="1" style="width:15%; "></col>
                                            </colgroup>
                                        <thead>
                                        <tr>
                                           <!--<th>Id</th>-->
                                            <th>Email</th>
                                            <th>Tipo</th>
                                            <th>Modulo</th>
                                            <th>Acci&oacute;n</th>
                                            <th>Fecha</th>
                                        </tr>
                                    </thead>
                                    <tbody>

                                    </tbody>
                                </table>
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
	<!--<script src="assets/js/jquery.autosize.min.js"></script>-->
	<!--<script src="assets/js/jquery.placeholder.min.js"></script>-->
	<!--<script src="assets/js/jquery.maskedinput.min.js"></script>-->
	<!--<script src="assets/js/jquery.inputlimiter.1.3.1.min.js"></script>-->
	<script src="assets/js/bootstrap-datepicker.min.js"></script>
	<!--<script src="assets/js/bootstrap-timepicker.min.js"></script>-->
	<script src="assets/js/moment.min.js"></script>
	<!--<script src="assets/js/daterangepicker.min.js"></script>-->
	<!--<script src="assets/js/jquery.hotkeys.min.js"></script>-->
	<!--<script src="assets/js/bootstrap-wysiwyg.min.js"></script>-->
	<!--<script src="assets/js/bootstrap-colorpicker.min.js"></script>-->-->

	<!-- theme scripts -->
	<script src="assets/js/custom.min.js"></script>
	<script src="assets/js/core.min.js"></script>

	<!-- inline scripts related to this page -->
	<!--<script src="assets/js/pages/form-elements.js"></script>-->

        <script src="assets/js/bootbox.min.js"></script>
        <script src="assets/js/common-functions.js"></script>

	<!-- end: JavaScript-->

  <script>
    $(document).ready(function() {
        var $id_usuario= $('#id_usuario');
        if ($id_usuario!==undefined && $id_usuario.val()!=="")
        loadData({id_usuario : $id_usuario.val()});
        $('#id_usuario').change(filtrar);
        $('#id_accion').change(filtrar);
        $('#id_modulo').change(filtrar);        
    });
    function filtrar(){
        var id_accion = $('#id_accion').val();
        var id_usuario = $('#id_usuario').val();
        var id_modulo = $('#id_modulo').val();
        loadData({id_accion: id_accion,
                  id_usuario: id_usuario,
                  id_modulo: id_modulo
        });
    }
    function loadData(data){
         var $tabla = $('#tblAuditoria');
        $.ajax({
               url: '<%= PathCfg.AUDITORIA_LIST %>',
               data: data,
               method:"POST",
               dataType: "json",
                beforeSend:function(){
                    var cant_cols = $tabla.find('thead th').length;
                    $tabla.find('tbody').html("<tr><td colspan='" + cant_cols + "'><center><img src='images/ajax-loader.gif'/></center></td></tr>");
               },
               success: function(result) {
                   $tabla.find('tbody').html("");
                   if(result.Result === "OK") {
                       $tabla.find('tbody').html(createTable(result.Records));
                   }
               }
           });
    }

    function createTable(data){
        var html = "";
        for(var i = 0;i< data.length;i++){
           html +="<tr class=''>";
           d = data[i];
//            html += wrapTag('td',d.id,'');
            html += wrapTag('td',d.email,'');
            html += wrapTag('td',d.tipo,'');
            html += wrapTag('td',d.modulo,'');
            html += wrapTag('td',d.accion,'');
            html += wrapTag('td',convertirFechayHora(d.fecha),'');

           html +="</tr>";
       }
       return html;
    }

        </script>
    </script>


</body>
</html>
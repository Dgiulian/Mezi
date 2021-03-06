<%@page import="java.util.ArrayList"%>
<%@page import="transaccion.TModulo"%>
<%@page import="bd.Modulo"%>
<%@page import="java.util.List"%>
<%@page import="bd.Usuario"%>
<%@page import="bd.Usuario"%>
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
                                            <H3>Listado de Parametros <span id="btnNuevo" class="btn btn-primary"><span class="fa fa-file-o fa-fw"> </span>Nuevo</span></H3>
                                    </div 
                                    <div  class="box-content">
                                        
                                        <table class="table table-striped table-bordered table-condensed" id="tblParametro">
                                            <thead>
                                                <tr>
                                                    <th>Id</th>
                                                    <th>N&uacute;mero</th>                                            
                                                    <th>C&oacute;digo</th>
                                                    <th>Nombre</th>
                                                    <th>Valor</th>
                                                    <th></th>
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
        <script src="assets/js/handlebars.runtime-v4.0.5.js"></script>
        <script src="assets/templates/parametro.list.js"></script>
        <script src="assets/templates/parametro.edit.js"></script>
	<!-- end: JavaScript-->

   <script>
    $(document).ready(function() {
        loadData({});
        $('#btnNuevo').click(function(){
            agregarParametro({id:0,codigo:'',nombre:'',valor:'',activo:1});
        });
   
    });
    function loadData(data){
        var $tabla = $('#tblParametro');
        $.ajax({
               url: '<%= PathCfg.PARAMETRO_LIST %>',
               data: data,
               method:"POST",
               dataType: "json",
               beforeSend:function(){
                    var cant_cols = $tabla.find('thead th').length;
                    $tabla.find('tbody').html("<tr><td colspan='" + cant_cols + "'><center><img src='images/ajax-loader.gif'/></center></td></tr>");
               },
               success: function(result) {
                   if(result.Result === "OK") {
                       $tabla.find('tbody').html(createTable(result.Records));
                        $('.btn-del').click(borrarParametro);
                        $('.btn-edit').click(editarParametro);
                   }
               }
           });
    }
    function borrarParametro(){
        var id = $(this).data('index');
        var $tr = $(this).parent().parent();
        deleteData('<%= PathCfg.PARAMETRO_DEL %>',{id:id},function(result) {     
                if(result.Result === "OK") {
                    $tr.remove();
                } else if (result.Message) bootbox.alert(result.Message);
        });
    }
     function createTable(data){        
        return Handlebars.templates['parametro.list']({records:data});       
    }
//    function createTable(data){
//        var html = "";
//        for(var i = 0;i< data.length;i++){
//           html +="<tr class=''>";
//           d = data[i];
//            html += wrapTag('td',d.id,'');
//            html += wrapTag('td',d.numero,'');
//            html += wrapTag('td',d.codigo,'');
//            html += wrapTag('td',d.nombre,'');
//            html += wrapTag('td',d.valor,'');            
//           var htmlEdit = "<span href='<%= PathCfg.PARAMETRO_EDIT%>?id="+ d.id +"' data-codigo='" + d.codigo + "' data-nombre='"+ d.nombre +" ' data-valor='"+ d.valor +"' class='btn btn-xs btn-circle  btn-warning  btn-edit'><span class='fa fa-edit fw'></span></span> ";
//           var htmlDel = "<span href='' data-index='"+ d.id + "' class='btn btn-xs btn-danger btn-circle btn-del'><span class='fa fa-trash-o fw'></span></span>";
//           html +=wrapTag('td',htmlEdit + htmlDel,'');
//           html +="</tr>";
//       }
//       return html;
//    }
     function editarParametro(){
        var numero = $(this).data('numero');
        var codigo = $(this).data('codigo');
        var nombre = $(this).data('nombre');
        var valor  = $(this).data('valor');
        var index  = $(this).data('index');
        var activo = $(this).data('activo');
        agregarParametro({numero:numero,codigo:codigo,nombre:nombre,id:index,valor:valor,activo:activo});
    }
    function agregarParametro(data){
        data.checked = (data.activo)?"checked":"";
        var template = Handlebars.templates['parametro.edit'];
        bootbox.dialog({
                title: "Configuraci&oacute;n de par&aacute;metro",
                message: template(data), 
                buttons: {
                    success: {
                        label: "Guardar",
                        className: "btn-success",
                        callback: function () {
                            var data = recuperarCampos();
                            $.ajax({
                                url:'<%=PathCfg.PARAMETRO_EDIT%>',
                                data: data,
                                method:'POST',
                                dataType:'json',
                                success:function(){
                                    loadData();
                                }
                                });
                            //bootbox.alert("Nombre " + nombre + ". Email: <b>" + email + "</b>");
                        }
                    },
                    cancel: {
                        label: "Cancelar",
                        callback: function () {
                        }
                    }
                }
            });
    }
    function recuperarCampos(){
        var data = {};
        data.id     = $('#id').val();
        data.numero = $('#numero').val();
        data.codigo = $('#codigo').val();
        data.nombre = $('#nombre').val();
        data.valor  = $('#valor').val();
        data.activo = $('#activo').prop('checked')?'1':'';
        data.activo = 1;
        return data;
    }
</script>
    </script>


</body>
</html>
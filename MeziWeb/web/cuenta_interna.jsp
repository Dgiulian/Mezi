<%@page import="java.util.List"%>
<%
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
                                                <H3>Listado de cuentas internas <span class="btn btn-primary" id="btnNuevo"><span class="fa fa-file" ></span> Nueva</span></H3>
                                            </div>
                                        <div  class="box-content">
                                            <table class="table table-bordered table-condensed table-striped" id="tblCuentaInterna" >
                                                <colgroup>
                                                    <col span="1" style="width:5%;"></col>
                                                    <col span="1" style=""></col>                                                    
                                                    <col span="1" style="width:10%; "></col>
                                                    <col span="1" style="width:10%; "></col>
                                                    <col span="1" style="width:10%; "></col>
                                                </colgroup>
                                                <thead>
                                                    <tr>
                                                        <th>C&oacute;digo</th>
                                                        <th>Nombre</th>
                                                        <th>Tipo</th>
                                                        <th>Activo</th>
                                                        <th></th>
                                                    </tr>
                                                </thead>
                                                <tbody class=""></tbody>
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
<!--	<script src="assets/js/jquery.sparkline.min.js"></script>
	<script src="assets/js/jquery.chosen.min.js"></script>
	<script src="assets/js/jquery.autosize.min.js"></script>
	<script src="assets/js/jquery.placeholder.min.js"></script>
	<script src="assets/js/jquery.maskedinput.min.js"></script>
	<script src="assets/js/jquery.inputlimiter.1.3.1.min.js"></script>
	<script src="assets/js/bootstrap-datepicker.min.js"></script>
	<script src="assets/js/bootstrap-timepicker.min.js"></script>
	<script src="assets/js/moment.min.js"></script>
	<script src="assets/js/daterangepicker.min.js"></script>
	<script src="assets/js/jquery.hotkeys.min.js"></script>
-->

	<!-- theme scripts -->
	<script src="assets/js/custom.min.js"></script>
	<script src="assets/js/core.min.js"></script>

	<!-- inline scripts related to this page -->
	<!--<script src="assets/js/pages/form-elements.js"></script>-->

        <script src="assets/js/bootbox.min.js"></script>
        <script src="assets/js/common-functions.js"></script>

	<!-- end: JavaScript-->
        <script language="">
        $(document).ready(function(){

           loadData({});
           $('#btnNuevo').click(function(){
                agregarCuenta_interna({id:0,nombre:'',activo:1});
            });
        });
        function loadData(data){
            console.log(data);
            var $tabla = $('#tblCuentaInterna');
            //$tabla.DataTable().destroy();
            $.ajax({
               url: '<%= PathCfg.CUENTA_INTERNA_LIST %>',
               data: data,
               method:"GET",
               dataType: "json",
               beforeSend:function(){
                    var cant_cols = $tabla.find('thead th').length;
                    $tabla.find('tbody').html("<tr><td colspan='" + cant_cols + "'><center><img src='assets/img/ajax-loader.gif'/></center></td></tr>");
               },
               success: function(result) {
                   if(result.Result === "OK") {

                       $tabla.find('tbody').html(createTable(result.Records));
                        $('.btn-del').click(borrar);
                        $('.btn-edit').click(editarCuenta_interna);
                   }
               }
           });
    }
   function createTable(data){
        var html = "";
        for(var i = 0;i< data.length;i++){
           html +="<tr class=''>";
           d = data[i];
           html +=wrapTag('td',d.id,'');
           html +=wrapTag('td',d.nombre ,'');           
           html +=wrapTag('td',d.tipo ,'');           
           var activo = d.id_estado?"Si":"No";
           html +=wrapTag('td',activo ,'');

            var htmlEdit = "<span  class='btn btn-xs btn-circle  btn-warning btn-edit' data-index='"+ d.id + "' data-nombre='"+ d.nombre + "' data-id_tipo='"+d.id_tipo+"' data-id_estado='"+ d.id_estado + "'><span class='fa fa-edit fw'></span></span> ";
            var htmlView = "<a  class='btn btn-xs btn-circle  btn-primary' href='<%=PathCfg.CUENTA_INTERNA_DETALLE%>?id_cuenta=" + d.id + "'><span class='fa fa-search fw'></span></a> ";
            var htmlDel  = "<span  class='btn btn-xs btn-danger btn-circle btn-del' data-index='"+ d.id + "' ><span class='fa fa-trash-o'></span></span>";
            html +='<td style="width:75px"  >' + htmlEdit + htmlView + htmlDel + '</td>';
//            html +=wrapTag('td',htmlEdit + htmlDel,'');
           html +="</tr>";
       }
       return html;
    }
   function borrar(){
        var id = $(this).data('index');
        var $tr = $(this).parent().parent();
        deleteData('<%= PathCfg.CUENTA_INTERNA_DEL%>',{id:id},function(result) {
            if(result.Result === "OK") {
                $tr.remove();
            } else if (result.Message) bootbox.alert(result.Message);
        });
    }

</script>

        <%@include file="mdl_cuenta_interna.jsp" %>

</body>
</html>
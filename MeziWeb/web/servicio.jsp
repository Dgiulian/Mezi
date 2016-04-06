<%@page import="bd.Propiedad"%>
<%
    Propiedad p = (Propiedad) request.getAttribute("propiedad");
    
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
                                                <H3>Listado de servicios <span id="btnNuevo" href="<%= PathCfg.SERVICIO_EDIT%>" class="btn btn-primary" ><span class="fa fa-file" ></span> Nuevo</span></H3>
                                            </div>
                                        <div  class="box-content">
                                            <input type="hidden" name="id_propiedad" id="id_propiedad" value="<%=p.getId()%>">
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
                                                        <th>Lugar</th>
                                                        <th>Identificaci&oacute;n</th>
                                                        <th>Activo</th>
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
<!--	<script src="assets/js/jquery.sparkline.min.js"></script>
	<script src="assets/js/jquery.chosen.min.js"></script>
	<script src="assets/js/jquery.cleditor.min.js"></script>
	<script src="assets/js/jquery.autosize.min.js"></script>
	<script src="assets/js/jquery.placeholder.min.js"></script>
	<script src="assets/js/jquery.maskedinput.min.js"></script>
	<script src="assets/js/jquery.inputlimiter.1.3.1.min.js"></script>
	<script src="assets/js/bootstrap-datepicker.min.js"></script>
	<script src="assets/js/bootstrap-timepicker.min.js"></script>-->
	<script src="assets/js/moment.min.js"></script>
<!--	<script src="assets/js/daterangepicker.min.js"></script>
	<script src="assets/js/jquery.hotkeys.min.js"></script>
	<script src="assets/js/bootstrap-wysiwyg.min.js"></script>
	<script src="assets/js/bootstrap-colorpicker.min.js"></script>-->
	
	<!-- theme scripts -->
	<script src="assets/js/custom.min.js"></script>
	<script src="assets/js/core.min.js"></script>
	
	<!-- inline scripts related to this page -->
	<!--<script src="assets/js/pages/form-elements.js"></script>-->
	
	<!-- end: JavaScript-->
	 <script src="assets/js/bootbox.min.js"></script>	
        <script src="assets/js/common-functions.js"></script>
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
                       $tabla.find('tbody').html(createTable(result.Records));
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
    
   function createTable(data){
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
</body>
</html>
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
                                                <H3> Listado de Usuarios <a href="<%=PathCfg.USUARIO_EDIT%>" class="btn btn-primary" ><span class="fa fa-file" ></span> Nuevo</a></H3>
                                            </div>
                                        
                                        <div  class="box-content">
                                             <table class="table table-striped table-bordered table-hover" id="tblUsuario">
                                    <thead>
                                        <tr>
                                            <th>Username</th>                                            
                                            <th>Tipo</th>
                                            <th>Fecha Alta</th>
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
	
	
<!--	<div class="modal fade" id="myModal">
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
			</div> /.modal-content 
		</div> /.modal-dialog 
	</div> /.modal -->
	
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
	
	<!-- theme scripts -->
	<script src="assets/js/custom.min.js"></script>
	<script src="assets/js/core.min.js"></script>
	
	<!-- inline scripts related to this page -->
	<script src="assets/js/pages/form-elements.js"></script>

        <script src="assets/js/bootbox.min.js"></script>	
        <script src="assets/js/common-functions.js"></script>

	<!-- end: JavaScript-->
        <script>
    $(document).ready(function() {
        loadData({});

    });
    function loadData(data){
         var $tabla = $('#tblUsuario');
        $.ajax({
               url: '<%= PathCfg.USUARIO_LIST %>',
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
                       $('.btn-del').click(borrarUsuario);
//                       $tabla.DataTable({
//                            responsive: true,
//                            retrieve: true,
//                            paging: false,
//                            ordering: true,
//                            searching: false,
//                            lengthChange: false,
//                            bInfo: false,
//                            language: {
//                                url:'bower_components/datatables-plugins/i18n/Spanish.json',
//                            }
//                        });
                   }
               }
           });
    }
    function borrarUsuario(){
        var id = $(this).data('index');
        var $tr = $(this).parent().parent();
        deleteData('<%= PathCfg.USUARIO_DEL %>',{id:id},function(result) {
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
            html += wrapTag('td',d.usu_mail,'');
            html += wrapTag('td',d.tipo_usuario,'');
            html += wrapTag('td',convertirFecha(d.usu_fcreacion),'');
            var activo = d.usu_activo?"Si":"No";
            
            html += wrapTag('td',activo,'');
            var htmlEdit = "<a href='<%= PathCfg.USUARIO_EDIT%>?id="+ d.id +"' class='btn btn-xs btn-circle  btn-warning'><span class='fa fa-edit fw'></span></a> ";
//            var htmlAud = "<a href='<%= PathCfg.AUDITORIA%>?id_usuario="+ d.id +"' class='btn btn-xs btn-circle  btn-primary'><span class='fa fa-list-alt fw'></span></a> ";
            var htmlDel = "<span href='' data-index='"+ d.id + "' class='btn btn-xs btn-danger btn-circle btn-del'><span class='fa fa-trash-o fw'></span></span> ";
            
            html +=wrapTag('td',htmlEdit  + htmlDel,'');
           html +="</tr>";
       }
       return html;
    }
   
        </script>
</body>
</html>
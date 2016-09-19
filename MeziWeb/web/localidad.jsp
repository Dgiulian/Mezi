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
                                                <H3>Listado de localidades  <span class="btn btn-primary" id="btnNuevo"><span class="fa fa-file" ></span> Nuevo</span></H3>
                                            </div>
                                        <div  class="box-content">
                                           
                                            <table class="table table-bordered table-condensed table-striped" id="tblLocalidad" name="tblLocalidad">
                                                 <colgroup>
                                                    <col style="width:10%;">
                                                    <col style="">
                                                    <col style="width:25%;">
                                                    <col style="width:10%;">
                                                </colgroup> 
                                                <thead>
                                                    <tr>
                                                        <th>C&oacute;digo</th>
                                                        <th>Nombre</th>                                                        
                                                        <th>Provincia</th>                                                        
                                                        <th></th>                                                        
                                                    </tr>
                                                </thead>
                                                <tbody class="">
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
	<script src="assets/js/moment.min.js"></script>
	
	<!-- theme scripts -->
	<script src="assets/js/custom.min.js"></script>
	<script src="assets/js/core.min.js"></script>
	
	
        <script src="assets/js/bootbox.min.js"></script>	
        <script src="assets/js/common-functions.js"></script>

	<!-- end: JavaScript-->
        <script language="">
        $(document).ready(function(){
           loadData({});
           $('#btnNuevo').click(function(){
                agregarLocalidad({id:0,nombre:'',activo:1});
            });        
        });
        function loadData(data){
            var $tabla = $('#tblLocalidad');
            //$tabla.DataTable().destroy();
            $.ajax({
               url: '<%= PathCfg.LOCALIDAD_LIST %>',
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
                        $('.btn-edit').click(editarLocalidad);
//                        $tabla.DataTable({
//                                responsive: true,
//                                retrieve: true,
//                                paging: false,
//                                ordering: true,
//                                searching: false,
//                                lengthChange:false,
//                                bInfo: false,
//                                language: {
//                                    url:'bower_components/datatables-plugins/i18n/Spanish.json',
//                                }
//                        });
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
           html +=wrapTag('td',d.descripcion,'');           
           html +=wrapTag('td',d.provincia,'');           
//           html += wrapTag('td',d.calle + ' ' + d.numero,'');
//           html += wrapTag('td',d.barrio,'');
           
            var htmlEdit = "<span  class='btn btn-xs btn-circle  btn-warning btn-edit' data-index='"+  d.id +"' class='btn btn-xs btn-circle  btn-warning'  data-id_provincia='"+ d.id_provincia + "'  data-descripcion='"+ d.descripcion + "' data-activo='"+ d.activo + "'><span class='fa fa-edit fw'></span></span> ";
            var htmlBarrio = "<a href='<%= PathCfg.BARRIO%>?id_localidad="+ d.id +"' class='btn btn-xs btn-circle  btn-primary'><span class='fa fa-building-o fw'></span></a> ";
            var htmlDel = "<span href='' data-index='"+ d.id + "' class='btn btn-xs btn-danger btn-circle btn-del'><span class='fa fa-trash-o'></span></span>";            
            html +='<td style="width:75px"  >' + htmlEdit + htmlBarrio + htmlDel + '</td>';
//            html +=wrapTag('td',htmlEdit + htmlDel,'');
           html +="</tr>";
       }      
       return html;
    }
   function borrar(){
        var id = $(this).data('index');
        var $tr = $(this).parent().parent();
        deleteData('<%= PathCfg.LOCALIDAD_DEL %>',{id:id},function(result) {     
                if(result.Result === "OK") {
                    $tr.remove();
                } else if (result.Message) bootbox.alert(result.Message);
        });
    }
    
        </script>
       <%@include file="mdl_localidad.jsp" %>
         
</body>
</html>
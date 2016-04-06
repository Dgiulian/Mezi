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
                                                <H3>Listado de contratos de alquiler <a href="<%=PathCfg.CONTRATO_EDIT%>" class="btn btn-primary" ><span class="fa fa-file" ></span> Nuevo</a></H3>
                                            </div>                                        
                                        <div  class="box-content">
                                        
                                        <div class="col-lg-2">
                                            <div clas="form-group">
                                                <label for="numero">N&uacute;mero</label>
                                                <span class="input-group">                                                                                            
                                                    <input type="text" class="form-control uppercase" name="numero" id="numero" size="20" value="">
                                                    <span class="input-group-addon" id="btnBuscar" ><span class="fa fa-search fa-fw"></span></span>
                                                </span>
                                            </div>
                                        </div>
                                       
                                        <div class="col-lg-2">
                                            <div class="form-group row  ">
                                                <label class="control-label" for="id_tipo_inmueble">Tipo inmueble</label>
                                                <div class="controls">
                                                      <div class="input-group">
                                                        <select type="text" id="id_tipo_inmueble" name="id_tipo_inmueble" class="form-control">
                                                            <option value="0" selected> Todos</option>
                                                            <option value="1"> Casa</option>
                                                            <option value="2" > Departamento</option>                                                            
                                                        </select>
                                                      </div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="col-lg-2">
                                            <div class="form-group row  ">
                                                <label class="control-label" for="id_estado">Estado</label>
                                                <div class="controls">
                                                      <div class="input-group">
                                                        <select type="text" id="id_estado" name="id_estado" class="form-control">
                                                            <option value="0" selected>Todos</option>
                                                            <option value="1">Disponible </option>
                                                            <option value="2">Alquilada</option>
                                                        </select>
                                                      </div>
                                                </div>
                                            </div>
                                        </div>
                                        
                                            <table class="table table-bordered table-condensed table-striped" id="tblContrato">
                                                <thead>
                                                    <tr>
                                                        <th>N&uacute;mero</th>
                                                        <th>Tipo</th>
                                                        <th>Direcci&oacute;n</th>
                                                        <th>Precio</th>                                                        
                                                        <th>Inquilino</th>
                                                        <th>Estado</th>
                                                        <th>Propietario</th>
                                                        <th>Detalles</th>
                                                        <th>Acciones</th>
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
	<script src="assets/js/jquery.sparkline.min.js"></script>
	<!--<script src="assets/js/jquery.chosen.min.js"></script>-->
	<!--<script src="assets/js/jquery.cleditor.min.js"></script>-->
	<!--<script src="assets/js/jquery.autosize.min.js"></script>-->
	<script src="assets/js/jquery.placeholder.min.js"></script>
	<!--<script src="assets/js/jquery.maskedinput.min.js"></script>-->
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
	
	<!-- end: JavaScript-->
        <script src="assets/js/bootbox.min.js"></script>	
        <script src="assets/js/common-functions.js"></script>
  <script language="">
        $(document).ready(function(){
           loadData({});
            $('#id_tipo_inmueble').change(filtrar);
           $('#id_estado').change(filtrar);
           $('#id_operacion').change(filtrar);
           $('#btnBuscar').click(filtrar);
        
        });
        function filtrar(){
           var id_tipo_inmueble = $('#id_tipo_inmueble').val();
           var id_estado = $('#id_estado').val();
           var id_operacion = $('#id_operacion').val();
           var id = $('#id').val();
           loadData({
               id_tipo_inmueble: id_tipo_inmueble,           
               id_estado: id_estado, 
               id_operacion: id_operacion,
               id: id
           });
           
        }
        function loadData(data){
            var $tabla = $('#tblContrato');
            //$tabla.DataTable().destroy();
            $.ajax({
               url: '<%= PathCfg.CONTRATO_LIST %>',
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
                        $('.btn-del').click(borrar);
                        
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
                      
           html += wrapTag('td',d.numero,'');
           html += wrapTag('td',d.tipo_contrato,'');
           html += wrapTag('td',d.direccion,'');
           html += wrapTag('td',"",'');
           html += wrapTag('td',d.inquilino,'');
           html += wrapTag('td',d.estado_contrato,'');
           html += wrapTag('td',d.propietario,'');
           html += wrapTag('td',d.detalles,'');
           
           
            //var htmlEdit = "<a href='<%= PathCfg.CONTRATO_EDIT%>?id="+ d.id +"' class='btn btn-xs btn-circle  btn-warning'><span class='fa fa-edit fw'></span></a> ";
            var htmlView = "<a href='<%= PathCfg.CONTRATO_VIEW%>?id="+ d.id +"' class='btn btn-xs btn-circle  btn-primary'><span class='fa fa-search fw'></span></a> ";
            var htmlDel = "<span href='' data-index='"+ d.id + "' class='btn btn-xs btn-danger btn-circle btn-del'><span class='fa fa-trash-o'></span></span>";
            html +='<td style="width:75px"  >' + htmlView + htmlDel + '</td>';
//            html +=wrapTag('td',htmlEdit + htmlDel,'');
           html +="</tr>";
       }      
       return html;
    }
   function borrar(){
        var id = $(this).data('index');
        var $tr = $(this).parent().parent();
        deleteData('<%= PathCfg.CONTRATO_DEL %>',{id:id},function(result) {     
                if(result.Result === "OK") {
                    $tr.remove();
                } else if (result.Message) bootbox.alert(result.Message);
        });
    }
    
        </script>
</body>
</html>
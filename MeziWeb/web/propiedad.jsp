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
                                                <H3>Listado de propiedades <a href="<%=PathCfg.PROPIEDAD_EDIT%>" class="btn btn-primary" ><span class="fa fa-file" ></span> Nuevo</a></H3>
                                            </div>
                                        <div  class="box-content">
                                            
                                        <div class="col-lg-2">
                                            <div clas="form-group">
                                                <label for="id">C&oacute;digo</label>
                                                <span class="input-group">                                                                                            
                                                    <input type="text" class="form-control uppercase" name="id" id="id" size="20" value="">
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
                                                            <option value="3"> Terreno</option>                                                                            
                                                        </select>
                                                      </div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="col-lg-2">
                                            <div class="form-group row  ">
                                                <label class="control-label" for="id_estado">Estado</label>
                                                <div class="controls">
                                                      <div class="input-group  col-lg-12">
                                                        <select type="text" id="id_estado" name="id_estado" class="form-control">
                                                            <option value="0" selected>Todos</option>
                                                            <option value="1">Disponible </option>
                                                            <option value="2">Alquilada</option>
                                                        </select>
                                                      </div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="col-lg-3">
                                            <div clas="form-group">
                                                <label for="id">Propietario</label>
                                                <span class="input-group">                                                                                            
                                                    <input type="text" class="form-control uppercase" name="propietario" id="propietario" size="20" value="">
                                                    <input type="hidden" class="form-control uppercase" name="id_propietario" id="id_propietario" size="20" value="">
                                                    <span class="input-group-addon" id="btnBuscarCliente" ><span class="fa fa-search fa-fw"></span></span>
                                                </span>
                                            </div>
                                        </div>
                                        <div class="col-lg-2">
                                            <div class="form-group row  ">
                                                <label class="control-label" for="id_operacion">Operaci&oacute;n</label>
                                                <div class="controls">
                                                      <div class="input-group  col-lg-12">
                                                        <select type="text" id="id_operacion" name="id_operacion" class="form-control">
                                                            <option value="0" selected>Todos</option>
                                                            <option value="1">Alquiler</option>
                                                            <option value="2">Venta</option>                                                            
                                                        </select>
                                                      </div>
                                                </div>
                                            </div>
                                        </div>
                                            <table class="table table-bordered table-condensed table-striped" id="tblPropiedad" name="tblPropiedad">
                                                 <colgroup>
                                                        <col span="1" style="width: 7%; text-align: right;"> <!-- Codigo -->
                                                        <col span="1" style="width: 12%;"> <!-- Tipo -->
                                                        <col span="1" style=""> <!-- Direccion -->
                                                        <col span="1" style="width: 10%;text-align: center"> <!-- Precio -->
                                                        <col span="1" style=""> <!-- Propietario -->
                                                        <col span="1" style="width: 10%;text-align: center">
                                                        <col span="1" style="width: 10%;text-align: center">
                                                        <col span="1" style="width: 10%;text-align: center">
                                                        <!--<col span="1" style="width: 24%;text-align: center">-->
                                                </colgroup>
                                                <thead>
                                                    <tr>
                                                        <th>C&oacute;digo</th>
                                                        <th>Tipo</th>
                                                        <th>Direcci&oacute;n</th>
                                                        <th>Precio</th>
                                                        <th>Propietario</th>
                                                        <th>Estado</th>
                                                        <th>Operaci&oacute;n</th>
                                                        <th>Acciones</th>
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
	<script src="assets/js/jquery-ui-1.10.3.custom.min.js"></script>
	<!--<script src="assets/js/jquery.sparkline.min.js"></script>-->
	<!--<script src="assets/js/jquery.chosen.min.js"></script>-->
	<!--<script src="assets/js/jquery.cleditor.min.js"></script>-->
	<!--<script src="assets/js/jquery.autosize.min.js"></script>-->
	<!--<script src="assets/js/jquery.placeholder.min.js"></script>-->
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

        <script src="assets/js/bootbox.min.js"></script>	
        <script src="assets/js/common-functions.js"></script>

	<!-- end: JavaScript-->
        <script language="">
        $(document).ready(function(){
           loadData({});
           
           $('#id_tipo_inmueble').change(filtrar);
           $('#id_estado').change(filtrar);
           $('#id_operacion').change(filtrar);
           $('#btnBuscar').click(filtrar);
           $('#id_propietario').change(filtrar);
           $('#propietario').change(filtrar);
           $('#btnBuscarCliente').click(function(){
               $('#mdlCliente').modal('show');
           });
           
        });
        function filtrar(){
            console.log("Filtrar");
           var id_tipo_inmueble = $('#id_tipo_inmueble').val();
           var id_estado = $('#id_estado').val();
           var id_operacion = $('#id_operacion').val();
           var id_propietario = $('#id_propietario').val();
           var id = $('#id').val();
           loadData({
               id_tipo_inmueble: id_tipo_inmueble,           
               id_estado: id_estado, 
               id_operacion: id_operacion,
               id_propietario:id_propietario,
               id: id
           });
           
        }
        
        function loadData(data){
            var $tabla = $('#tblPropiedad');
            //$tabla.DataTable().destroy();
            $.ajax({
               url: '<%= PathCfg.PROPIEDAD_LIST %>',
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
           html +=wrapTag('td',d.tipo_inmueble,'');           
           html += wrapTag('td',d.calle + ' ' + d.numero,'');
//           html += wrapTag('td',d.barrio,'');
           html += wrapTag('td',d.precio,'');
           html += wrapTag('td',d.propietario,'');
           html += wrapTag('td',d.estado,'');
           html += wrapTag('td',d.operacion,'');
           
            var htmlEdit = "<a href='<%= PathCfg.PROPIEDAD_EDIT%>?id="+ d.id +"' class='btn btn-xs btn-circle  btn-warning'><span class='fa fa-edit fw'></span></a> ";
            var htmlServ = "<a href='<%= PathCfg.SERVICIO%>?id_propiedad="+ d.id +"' class='btn btn-xs btn-circle  btn-primary'><span class='fa fa-lightbulb-o fw'></span></a> ";
            htmlServ = "";        
            var htmlCont  = "";
            if(d.id_estado===1)
                htmlCont = "<a href='<%= PathCfg.CONTRATO_EDIT%>?id_propiedad="+ d.id +"' class='btn btn-xs btn-circle  btn-primary'><span class='fa  fa-file-text-o fw'></span></a> ";
            var htmlDel = "<span href='' data-index='"+ d.id + "' class='btn btn-xs btn-danger btn-circle btn-del'><span class='fa fa-trash-o'></span></span>";
            html +='<td style="width:75px"  >' + htmlEdit + htmlServ + htmlCont + htmlDel + '</td>';
//            html +=wrapTag('td',htmlEdit + htmlDel,'');
           html +="</tr>";
       }      
       return html;
    }
   function borrar(){
        var id = $(this).data('index');
        var $tr = $(this).parent().parent();
        deleteData('<%= PathCfg.PROPIEDAD_DEL %>',{id:id},function(result) {     
                if(result.Result === "OK") {
                    $tr.remove();
                } else if (result.Message) bootbox.alert(result.Message);
        });
    }
    function completarCliente(data){
        $('#id_propietario').val(data.id);
        var nombre = data.nombre;
        if(data.apellido)
            nombre += ", " + data.apellido;
            $('#propietario').val(nombre);
        $('#id_propietario').trigger('change');
    }
    
        </script>
        <%@include file="mdl_cliente.jsp" %>
</body>
</html>
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
                            
				<div class="col-sm-12">
                                    <div class="box">
                                        <div class="box-header">
                                            <H3>Listado de propiedades <a href="<%=PathCfg.PROPIEDAD_EDIT%>" class="btn btn-primary" ><span class="fa fa-file" ></span> Nuevo</a></H3>
                                        </div>
                                    <div  class="box-content">
                                            
                                    <div class="row">
                                        <div class="col-sm-2">
                                            <div clas="form-group">
                                                <label for="id">C&oacute;digo</label>
                                                <span class="input-group">                                                                                            
                                                    <input type="text" class="form-control uppercase" name="id" id="id" size="20" value="">
                                                    <span class="input-group-addon" id="btnBuscar" ><span class="fa fa-search fa-fw"></span></span>
                                                </span>
                                            </div>
                                        </div>
                                       
                                        <div class="col-sm-2">
                                            <div class="form-group">
                                                <label class="control-label" for="id_tipo_inmueble">Tipo inmueble</label>
                                                
                                                    <select type="text" id="id_tipo_inmueble" name="id_tipo_inmueble" class="form-control">
                                                        <option value="0" selected> Todos</option>
                                                        <option value="1"> Casa</option>
                                                        <option value="2" > Departamento</option>
                                                        <option value="3"> Terreno</option>                                                                            
                                                    </select>
                                            </div>
                                        </div>
                                        <div class="col-sm-2">
                                            <div class="form-group">
                                                <label class="control-label" for="id_estado">Estado</label>
                                                <div class="controls">
                                                        <select type="text" id="id_estado" name="id_estado" class="form-control">
                                                            <option value="0" selected>Todos</option>
                                                            <option value="1">Disponible </option>
                                                            <option value="2">Alquilada</option>
                                                        </select>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="col-sm-3">
                                            <div clas="form-group">
                                                <label for="id">Propietario</label>
                                                <span class="input-group">                                                                                            
                                                    <input type="text" class="form-control uppercase" name="propietario" id="propietario" size="20" value="">
                                                    <input type="hidden" class="form-control uppercase" name="id_propietario" id="id_propietario" size="20" value="">
                                                    <span class="input-group-addon" id="btnBuscarCliente" ><span class="fa fa-search fa-fw"></span></span>
                                                </span>
                                            </div>
                                        </div>
                                        <div class="col-sm-2">
                                            <div class="form-group">
                                                <label class="control-label" for="id_operacion">Operaci&oacute;n</label>
                                                <div class="controls">
                                                        <select id="id_operacion" name="id_operacion" class="form-control">
                                                            <option value="0" selected>Todos</option>
                                                            <option value="1">Alquiler</option>
                                                            <option value="2">Venta</option>                                                            
                                                        </select>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="col-lg-4">
                                            <div class="form-group">
                                                <label class="control-label" for="calle">Calle</label>
                                                <div class="input-group">
                                                    <input type="text" class="form-control uppercase" name="calle" id="calle" size="20" value="">
                                                    <span class="input-group-addon" id="btnFiltrarCalle" ><span class="fa fa-search fa-fw"></span></span>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="col-lg-1">
                                                <div clas="form-group">
                                                    <label  class="control-label" for="pagination">P&aacute;gina </label>
                                                    <select class="form-control" id="pagination" name="pagination"></select>
                                                </div>
                                            </div>  
                                        <div class="col-lg-1">
                                            <div clas="form-group">
                                                <label  class="control-label" for="numResults">Mostrar </label>
                                                <select id="numResults" name="numResults" CLASS="form-control" >
                                                 <option value="25">25</option>
                                                 <option value="50">50</option>
                                                 <option value="100">100</option>                                                            
                                             </select>
                                            </div>
                                        </div>
                                      </div><!--row-->
                                      <div class="row" id="paginacion">
                                          <input type="hidden" id="pagNro" name="pagNro" value="1">                                          
                                          <ul class="pagination"></ul>
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
	
	<script src="assets/js/moment.min.js"></script>
	
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
           filtrar();
           
           $('#id_tipo_inmueble').change(filtrar);
           $('#id_estado').change(filtrar);
           $('#id_operacion').change(filtrar);
           $('#btnBuscar').click(filtrar);
           $('#id_propietario').change(filtrar);
           $('#propietario').change(filtrar);
           $('#numResults').change(filtrar);
           $('#btnFiltrarCalle').click(filtrar);
           $('#btnBuscarCliente').click(function(){
                $('#mdlCliente').modal('show');
           });
           $('#pagination').change(gotoPage);
        });
       
        function filtrar(){
           var data = {}
           data.id_tipo_inmueble = $('#id_tipo_inmueble').val();
           data.id_estado = $('#id_estado').val();
           data.id_operacion = $('#id_operacion').val();
           data.id_propietario = $('#id_propietario').val();
           data.calle = $('#calle').val();           
           
           data.id = $('#id').val();
           data.pagNro = parseInt($('#pagination').val());
           data.numResults = parseInt($('#numResults').val());
           loadData(data);
        }
        
        function loadData(data){
            var $tabla = $('#tblPropiedad');
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
                       $('#pagination').html(createPagination(result.TotalRecordCount,data.pagNro,data.numResults));                       
                       $('.btn-del').click(borrar);
                   }
               }
           });
    }
   function gotoPage(){
        var pagNro = parseInt($(this).val());
        filtrar();
    }
    function createPagination(totalRecordCount,pagNro,numResults){
        var html="";
        var numPages = Math.ceil(totalRecordCount / numResults);
        //if(numPages<=1) return html;
        for(var i=1;i<=numPages;i++){
            var active=(i===pagNro)?' selected "':'';
            html += '<option '+ active +' value="' + i + ' "><a href="#">'+ i +'</a></option>';
        }        
        return html;    
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
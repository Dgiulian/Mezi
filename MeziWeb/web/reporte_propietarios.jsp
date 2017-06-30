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
                                            <H3>Reporte de propietarios</H3>
                                        </div>
                                    <div  class="box-content">
                                    
                                        <div class="row">
                                            <div class="col-lg-1">
                                                <div clas="form-group">
                                                    <label for="carpeta_search">Carpeta</label>
                                                    <input type="text" class="form-control" name="carpeta_search" id="carpeta_search" size="20" value="">                                                    
                                                </div>
                                            </div>
                                            <div class="col-lg-3">
                                                <div clas="form-group">
                                                    <label for="nombre_search">Nombre</label>
                                                    <input type="text" class="form-control" name="nombre_search" id="nombre_search" size="20" value="">                                                    
                                                </div>
                                            </div>
                                            <div class="col-lg-3">
                                             <div clas="form-group">
                                                    <label for="apellido_search">Apellido</label>
<!--                                                    <div class="input-group">                                                                                            -->
                                                        <input type="text" class="form-control" name="apellido_search" id="apellido_search" size="20" value="">
                                                    <!--</div>-->
                                                </div>
                                            </div>
                                            <div class="col-lg-2">
                                                <div clas="form-group">
                                                    <label for="dni_search">Dni</label>
                                                        <input type="text" class="form-control" name="dni_search" id="dni_search" size="20" value="">
                                                </div>
                                            </div>
                                        <div class="col-lg-1">
                                            <div clas="form-group">
                                            <label for="pagination">P&aacute;gina </label>
                                            <select class="form-control" id="pagination" name="pagination"></select>
                                            </div>
                                        </div>  
                                        <div class="col-lg-1">
                                            <div clas="form-group">
                                                <label for="numResults">Mostrar </label>
                                                <select id="numResults" name="numResults" CLASS="form-control" >
                                                 <option value="25">25</option>
                                                 <option value="50">50</option>
                                                 <option value="100">100</option>                                                            
                                             </select>
                                            </div>
                                        </div>
                                      
                                  
                                     </div>
                                      
                                        <table class="table table-bordered table-condensed table-striped" id="tblCliente">
                                            <thead>
                                                <tr>
                                                    <th>Carpeta</th>
                                                    <th>Nombre y Apellido</th>
                                                    <th>Domicilio</th>
                                                    <th>Estado</th>
                                                    <th>Carpeta Inq.</th>
                                                    <th>Inquilino</th>
                                                    <th>Estado Contrato</th>                                                        
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
	
	<!-- end: JavaScript-->
	 <script src="assets/js/bootbox.min.js"></script>	
        <script src="assets/js/common-functions.js"></script>
        <script language="">
        $(document).ready(function(){
           
           $('#carpeta_search').change(filtrar_mdl_cliente);
           $('#nombre_search').change(filtrar_mdl_cliente);
           $('#apellido_search').change(filtrar_mdl_cliente);
           $('#dni_search').change(filtrar_mdl_cliente);
           $('#pagination').change(gotoPage);
           $('#numResults').change(filtrar_mdl_cliente);
           filtrar_mdl_cliente();
        });
        
        function loadData(data){
            console.log(data);
        
            var $tabla = $('#tblCliente');
            //$tabla.DataTable().destroy();
            $.ajax({
               url: '<%= PathCfg.REPORTE_PROPIETARIOS_LIST %>',
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
                       $('#pagination').html(createPagination(result.TotalRecordCount,data.pagNro,data.numResults));
                   }
               }
           });
    }
  
   function createTable(data){
        var html = "";
        for(var i = 0;i< data.length;i++){
           html +="<tr class=''>";
           var d = data[i];
           var carpeta_inquilino = "";
           var nombre_inquilino = "";
           var estado_contrato = "";
           if (d.contrato) {
               estado_contrato = d.estado_contrato;
           }
           if(d.inquilino){
               carpeta_inquilino = d.inquilino.carpeta;
               nombre_inquilino = d.inquilino.apellido + "," + d.inquilino.nombre;
           }
           html += wrapTag('td',d.carpeta,'');
           html += wrapTag('td',d.propietario,'');
           html += wrapTag('td',d.calle + ' ' + d.numero,'');
           html += wrapTag('td',d.estado,'');
           html += wrapTag('td',carpeta_inquilino,'');
           html += wrapTag('td',nombre_inquilino,'');
           html += wrapTag('td',estado_contrato,'');                       
           html +="</tr>";
       }      
       return html;
    }

    function filtrar_mdl_cliente(){     
        var data = {};
        data.carpeta = parseInt($('#carpeta_search').val());
        data.nombre = $('#nombre_search').val();
        data.apellido = $('#apellido_search').val();
        data.dni = $('#dni_search').val();
        data.pagNro = parseInt($('#pagination').val());
        data.numResults = parseInt($('#numResults').val());
        
        loadData(data);

     }
    function gotoPage(){
        var pagNro = parseInt($(this).val());        
        filtrar_mdl_cliente();
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
   }    </script>
</body>
</html>
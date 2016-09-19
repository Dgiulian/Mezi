<%@page import="transaccion.TLocalidad"%>
<%@page import="java.util.List"%>
<%@page import="bd.Localidad"%>
<%
    List<Localidad> lstLoc = new TLocalidad().getList();
    Localidad localidad = (Localidad) request.getAttribute("localidad");
    if(localidad==null){
        localidad = new Localidad();
    }
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
                                                <H3>Listado de barrios <span class="btn btn-primary" id="btnNuevo"><span class="fa fa-file" ></span> Nuevo</span></H3>
                                            </div>
                                        <div  class="box-content">
                                            <div class="row">
                                                <div class="col-lg-4">
                                                     <div class="form-group">
                                                            <label class="control-label" for="id_localidad">Localidad:</label>
                                                            <div class="controls">
                                                                  <div class="input-group col-sm-8">
                                                                      
                                                                    <select  name="id_localidad_search" id="id_localidad_search" class="form-control" >
                                                                        <option value="0">Todas</option>                                                                                                                                         
                                                                        <% for(Localidad l:lstLoc) {
                                                                            String selected = l.getId().equals(localidad.getId())?"selected":"";
                                                                        %>
                                                                        <option value="<%=l.getId()%>" <%=selected%>><%=l.getDescripcion()%></option>
                                                                        <% } %>
                                                                    </select>
                                                                    
                                                                  </div>
                                                            </div>
                                                    </div>                                                    
                                                </div>
                                            </div>
                                            <table class="table table-bordered table-condensed table-striped" id="tblBarrio" name="tblBarrio">
                                                <colgroup>
                                                    <col span="1" style="width:5%;"></col>
                                                    <col span="1" style="width:28%"></col>
                                                    <col span="1" style="width:28%"></col>
                                                    <col span="1" style="width:25%;"></col>
                                                    <col span="1" style="width:4%"></col>
                                                    <col span="1" style="width:10%; "></col>
                                                </colgroup>
                                                <thead>
                                                    <tr>
                                                        <th>C&oacute;digo</th>
                                                       
                                                        <th>Nombre</th>                                                        
                                                        <th>Nombre municipal</th>         
                                                         <th>Localidad</th>
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
	<script src="assets/js/bootstrap-colorpicker.min.js"></script>-->
	
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
           $('#id_localidad_search').change(function(){
                var id_localidad = $('#id_localidad_search').val();
                loadData({'id_localidad':id_localidad});
           });
            
           var id_localidad = $('#id_localidad_search').val();
           loadData({'id_localidad':id_localidad});
           $('#btnNuevo').click(function(){
                agregarBarrio({id:0,id_localidad:0,nombre:'',nombre_municipal:'',activo:1});
            });
        });
        function loadData(data){
            console.log(data);        
            var $tabla = $('#tblBarrio');
            //$tabla.DataTable().destroy();
            $.ajax({
               url: '<%= PathCfg.BARRIO_LIST %>',
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
                        $('.btn-edit').click(editarBarrio);
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
           html +=wrapTag('td',d.nombre_municipal ,'');     
            html +=wrapTag('td',d.localidad,'');
           
           var activo = d.activo?"Si":"No";
           html +=wrapTag('td',activo ,'');           

            var htmlEdit = "<span  class='btn btn-xs btn-circle  btn-warning btn-edit' data-index='"+ d.id + "' data-id_localidad='"+d.id_localidad+"' data-nombre='"+ d.nombre + "' data-nombre_municipal='"+ d.nombre_municipal + "' data-activo='"+ d.activo + "'><span class='fa fa-edit fw'></span></span> ";
            var htmlDel  = "<span  class='btn btn-xs btn-danger btn-circle btn-del' data-index='"+ d.id + "' ><span class='fa fa-trash-o'></span></span>";
            html +='<td style="width:75px"  >' + htmlEdit + htmlDel + '</td>';
//            html +=wrapTag('td',htmlEdit + htmlDel,'');
           html +="</tr>";
       }      
       return html;
    }
   function borrar(){
        var id = $(this).data('index');
        var $tr = $(this).parent().parent();
        deleteData('<%= PathCfg.BARRIO_DEL %>',{id:id},function(result) {     
            if(result.Result === "OK") {
                $tr.remove();
            } else if (result.Message) bootbox.alert(result.Message);
        });
    }
    
        </script>
        
        <%@include file="mdl_barrio.jsp" %>
        
</body>
</html>
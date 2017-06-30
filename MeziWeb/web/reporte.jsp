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
                                            <H3>Listado de cajas <button class="btn btn-primary" id="btnAbrir"><span class="fa fa-file" ></span> Abrir</button></H3>
                                        </div>
                                    <div  class="box-content">

                                            <table class="table table-bordered table-condensed table-striped" id="tblCaja">
<!--                                                <colgroup>
                                                    <col style="width:10%"></col>
                                                    <col style="width:10%"></col>
                                                    <col style="width:10%"></col>
                                                    <col style="width:10%"></col>
                                                </colgroup>-->
                                                <thead>
                                                    <tr>
                                                        <th>Fecha</th>
                                                        <th>Estado</th>
                                                        <th>Efectivo anterior</th>
                                                        <th>Efectivo cierre</th>
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
	<script src="assets/js/handlebars.runtime-v4.0.5.js"></script>
        
        <script src="assets/templates/aperturaCaja.js"></script>
        
	<!-- page scripts -->
	<script src="assets/js/jquery-ui-1.10.3.custom.min.js"></script>
	<script src="assets/js/jquery.placeholder.min.js"></script>
	<script src="assets/js/jquery.inputlimiter.1.3.1.min.js"></script>
	<script src="assets/js/bootstrap-datepicker.min.js"></script>
	<script src="assets/js/bootstrap-timepicker.min.js"></script>
	<script src="assets/js/moment.min.js"></script>
	<script src="assets/js/daterangepicker.min.js"></script>
	<script src="assets/js/jquery.hotkeys.min.js"></script>
		
	
	<!-- theme scripts -->
	<script src="assets/js/custom.min.js"></script>
	<script src="assets/js/core.min.js"></script>	
	
	<!-- end: JavaScript-->
	 <script src="assets/js/bootbox.min.js"></script>	
        <script src="assets/js/common-functions.js"></script>
        <script language="">
        $(document).ready(function(){
           
//           $('#nombre_search').change(filtrar_mdl_caja);
//           $('#apellido_search').change(filtrar_mdl_caja);
//           $('#dni_search').change(filtrar_mdl_caja);
           $('#btnAbrir').click(abrirCaja);
           filtrar_mdl_caja();
        });
        
        function loadDataCaja(data){
            var $tabla = $('#tblCaja');
            //$tabla.DataTable().destroy();
            $.ajax({
               url: '<%= PathCfg.CAJA_LIST %>',
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
                   }else bootbox.alert(result.Message);
               }
           });
    }
   function createTable(data){
        var html = "";
        if(data.length==0) html ="<tr><td colspan='5' style='text-align:center'>A&uacute;n no se ha creado ninguna caja</td></tr>";
        for(var i = 0;i< data.length;i++){
           html +="<tr class=''>";
           d = data[i];
                      
           html += wrapTag('td',convertirFecha(d.fecha.fecha),'');
           html += wrapTag('td',d.estado,'');
           html += wrapTag('td',d.efectivo_anterior,'');
           html += wrapTag('td',d.efectivo_cierre,'');
           var htmlEdit ="";
           if(d.id_estado === 1){
                htmlEdit  = "<a href='<%= PathCfg.CAJA_EDIT%>?id="+ d.id +"' class='btn btn-xs btn-circle  btn-warning'><span class='fa fa-edit fw'></span></a> ";
            } else htmlEdit  = "<a href='<%= PathCfg.CAJA_EDIT%>?id="+ d.id +"' class='btn btn-xs btn-circle  btn-primary'><span class='fa fa-search fw'></span></a> ";
//            var htmlDel = "<span href='' data-nombre='" + d.nombre+ "' data-apellido='"+d.apellido+"' data-index='"+ d.id + "' class='btn btn-xs btn-danger btn-circle btn-del'><span class='fa fa-trash-o'></span></span>";
//            html +='<td style="width:75px"  >' + htmlEdit + htmlDel + '</td>';
            html +=wrapTag('td',htmlEdit,'');
           html +="</tr>";
       }      
       return html;
    }
   function borrar(){   
        var id = $(this).data('index');
        var nombre =  $(this).data('nombre');
        var apellido  =  $(this).data('apellido');
        var message = "Est&aacute; seguro que desea eliminar el cliente <b>" +     apellido + ", " + nombre + "</b>?";
        var $tr = $(this).parent().parent();
        deleteData('<%= PathCfg.CAJA_DEL %>',{id:id},function(result) {     
                if(result.Result === "OK") {
                    $tr.remove();
                } else if (result.Message) bootbox.alert(result.Message);
        }, message);
    }
    function filtrar_mdl_caja(){            
        var nombre_search = $('#nombre_search').val();
        var apellido_search = $('#apellido_search').val();
        var dni_search = $('#dni_search').val();

        loadDataCaja({
            nombre: nombre_search,           
            apellido: apellido_search,
            dni: dni_search,
        });

     }
   function abrirCaja(){
    var template = Handlebars.templates['aperturaCaja'];
    bootbox.dialog({
        title: "Apertura de caja",
        message: template({fecha:new moment().format("YYYY-MM-DD")}),
        buttons: {
            success: {
                label: "Guardar",
                className: "btn-success",
                callback: function () {
                    var data = recuperarDatosCaja();
                    $.ajax({
                        url: '<%=PathCfg.CAJA_EDIT%>',
                        method: "POST",
                        dataType: "json",
                        data: data,
                        success: function(result){
                            console.log(result);
                            if(result.Result ==="OK"){
                                window.location = "<%=PathCfg.CAJA_EDIT%>" + "?id=" + result.Record.id;
                            } else {
                                bootbox.alert(result.Message);
                            }
                                
                        },
                    });
                }
            },
            cancel: {
                label: "Cancelar",
                callback: function () {}
            }
        }
    });
 }
function recuperarDatosCaja(){
    var data={};
    data.fecha = $('#fecha_caja').val();
    data.efectivo_anterior = parsearInt($('#efectivo_anterior').val());
    data.cheque_anterior = parsearInt($('#cheque_anterior').val());
    data.transferencia_anterior = parsearInt($('#transferencia_anterior').val());
    return data;
}
    </script>
</body>
</html>
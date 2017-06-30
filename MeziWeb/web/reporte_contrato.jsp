<%@page import="utils.TFecha"%>
<%@page import="transaccion.TUsuario"%>
<%@page import="bd.Usuario"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <%@include file="tpl_head.jsp" %>
<%
  //  Integer id_tipo_usuario = (Integer) session.getAttribute("id_tipo_usuario");
    Integer id_usuario = (Integer) session.getAttribute("id_usuario");
    
%>
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
                                            <H3>Reporte contratos </H3>
                                        </div>
                                    <div  class="box-content">
                                        <table class="table table-bordered table-condensed table-striped" id="tblContrato">
                                            <colgroup>
                                                <col style="width:7%"></col>
                                                <col style="width:7%"></col>
                                                <col style="width:10%"></col>
                                                <col style=""></col>
                                                <col style="width:10%"></col>
                                                <col style="width:10%"></col>
                                                <col style="width:10%"></col>
                                                <col style="width:7%"></col>
                                            </colgroup>
                                            <thead>
                                                <tr>
                                                    <th>Fecha Alta</th>
                                                    <th>Inquilino</th>
                                                    <th>Propietario</th>
                                                    <th>Domicilio</th>
                                                    <th>Estado</th>
                                                    <th>Fin</th>
                                                    <th>Saldo</th>
                                                    <th>Ult. Liq.</th>
                                                    <th>Vendedor</th>
                                                    <th>Agente Ret</th>
                                                </tr>
                                            </thead>
                                            <tbody class=""></tbody>
                                        </table>
                                        <div class="row">
                                            <div class="col-lg-12">
                                               <div class="form-actions">
                                                  <a  href="<%= PathCfg.REPORTE%>"class="btn btn-default">Volver</a>
                                              </div>
                                           </div>
                                        </div>
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
           $('#fecha_desde').change(filtrar_mdl_contrato);
           $('#fecha_hasta').change(filtrar_mdl_contrato);
           $('#id_estado').change(filtrar_mdl_contrato);
           $('#id_usuario').change(filtrar_mdl_contrato); 
           filtrar_mdl_contrato();
        });
        
        function loadDataContrato(data){
            var $tabla = $('#tblContrato');
            $.ajax({
               url: '<%= PathCfg.REPORTE_CONTRATO_LIST %>',
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
                   }else bootbox.alert(result.Message);
               }
           });
    }
    function createTable(data){
        var html = "";
        for(var i = 0;i< data.length;i++){
           html +="<tr class=''>";
           var d = data[i];
           html += wrapTag('td','','');
           html += wrapTag('td',d.inquilino,'');
           html += wrapTag('td',d.propietario,'');
           html += wrapTag('td',d.direccion,'');
           html += wrapTag('td',d.estado_contrato,'');
           html += wrapTag('td',convertirFecha(d.fecha_fin),'');
           html += wrapTag('td',d.saldo,'');           
           html += wrapTag('td',convertirFecha(d.ult_liq),'');           
           html += wrapTag('td',d.vendedor,'');
           html += wrapTag('td',d.agente_retencion?"Si":"No",'');
           
           //html += wrapTag('td',d.numero,'');
           //html += wrapTag('td',d.tipo_contrato,'');
           
           
           html +="</tr>";
       }      
       return html;
    }
    function getDatosFiltro(){
        var data = {};
        data.fecha_desde = $('#fecha_desde').val();
        data.fecha_hasta = $('#fecha_hasta').val();
        data.id_estado   = $('#id_estado').val();
        data.id_usuario  = $('#id_usuario').val();
        return data;
    }
    function filtrar_mdl_contrato(){            
        var data = getDatosFiltro();
        loadDataContrato(data);

     }
   
    </script>
</body>
</html>
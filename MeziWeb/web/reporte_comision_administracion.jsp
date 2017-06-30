<%@page import="utils.TFecha"%>
<%@page import="transaccion.TVendedor"%>
<%@page import="bd.Vendedor"%>
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
                                            <H3>Reporte comisiones de Administraci&oacute;n</H3>
                                        </div>
                                    <div  class="box-content">
                                        <div class="row">
                                            <div class="form-group col-lg-2">
                                                <label class="control-label" for="fecha_desde">Desde</label>
                                                <div class="controls">
                                                    <div class="input-group date date-picker">
                                                        <input type="text" id="fecha_desde" name="fecha_desde" class="form-control date-input " value="<%=TFecha.ahora(TFecha.formatoVista)%>">
                                                        <span class="input-group-addon "><span class="fa fa-calendar"></span></span>  
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="form-group col-lg-2">
                                                <label class="control-label" for="fecha_hasta">Hasta</label>
                                                <div class="controls">
                                                    <div class="input-group date date-picker">
                                                        <input type="text" id="fecha_hasta" name="fecha_hasta" class="form-control  date-input  " value="<%=TFecha.ahora(TFecha.formatoVista)%>">
                                                        <span class="input-group-addon"><span class="fa fa-calendar"></span></span>  
                                                        </span>  
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="form-group col-lg-3">
                                                <label class="control-label" for="id_vendedor">Vendedor</label>
                                                <div class="controls">
                                                    <div class="input-group">                                                        
                                                        <select class="form-control" name="id_vendedor" id="id_vendedor">
                                                            <option value="0">Todos</option>
                                                            <% for(Vendedor v:new TVendedor().getList()) { %>
                                                            <option value="<%=v.getId()%>" ><%= v.getApellido() + ", " + v.getNombre() %></option>
                                                            <% } %>
                                                        </select>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <table class="table table-bordered table-condensed table-striped" id="tblContrato">
                                            <colgroup>
                                                <col style="width:7%"></col>
                                                <col style="width:7%"></col>
                                                <col style="width:15%"></col>
                                                <col style=""></col>
                                                <col style="width:7%"></col>
                                                <col style="width:7%"></col>
                                            </colgroup>
                                            <thead>
                                                <tr>
                                                    <th>N&uacute;mero</th>
                                                    <th>Fecha</th>
                                                    <th>Propietario</th>
                                                    <th>Domicilio</th>
                                                    <th>Comision Inquilino</th>
                                                    <th>Comision Propietario</th>
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
           $('#id_vendedor').change(filtrar_mdl_contrato);
           filtrar_mdl_contrato();
        });
        
        function loadDataContrato(data){
            var $tabla = $('#tblContrato');
            $.ajax({
               url: '<%= PathCfg.REPORTE_COMISION_VENDEDOR_LIST %>',
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
           html += wrapTag('td',d.numero,'');
           html += wrapTag('td',convertirFecha(d.fecha_inicio),'');
           html += wrapTag('td',d.propietario,'');
           html += wrapTag('td',d.direccion,'');
           html += wrapTag('td',d.comision_monto_inquilino,'');
           html += wrapTag('td',d.comision_monto_propietario,'');
           html +="</tr>";
       }      
       return html;
    }
    function getDatosFiltro(){
        var data = {};
        data.fecha_desde = $('#fecha_desde').val();
        data.fecha_hasta = $('#fecha_hasta').val();
        data.id_vendedor = $('#id_vendedor').val();        
        return data;
    }
    function filtrar_mdl_contrato(){            
        var data = getDatosFiltro();
        loadDataContrato(data);
     }
    </script>
</body>
</html>
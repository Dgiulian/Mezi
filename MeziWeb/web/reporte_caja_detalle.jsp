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
                                            <H3>Reporte detallado por caja </H3>
                                        </div>
                                    <div  class="box-content">
                                        <div class="row">
                                            <div class="form-group col-lg-2">
                                                        <label class="control-label" for="fecha_desde">Fecha</label>
                                                        <div class="controls">
                                                              <div class="input-group date date-picker">
                                                                  <input type="text" id="fecha_desde" name="fecha_desde" class="form-control date-input " value="<%=TFecha.ahora(TFecha.formatoVista)%>">
                                                                    <span class="input-group-addon "><span class="fa fa-calendar"></span></span>  
                                                              </div>
                                                        </div>
                                                    </div>
                                                   <!-- <div class="form-group col-lg-2">
                                                        <label class="control-label" for="fecha_hasta">Hasta</label>
                                                        <div class="controls">
                                                                <div class="input-group date date-picker">
                                                                    <input type="text" id="fecha_hasta" name="fecha_hasta" class="form-control  date-input  " value="">
                                                                    <span class="input-group-addon"><span class="fa fa-calendar"></span></span>  
                                                                    </span>  
                                                              </div>
                                                        </div>
                                                    </div> -->
                                                <div class="form-group col-lg-3">
                                                        <label class="control-label" for="id_usuario">Usuario</label>
                                                        <div class="controls">
                                                            <div class="input-group">
                                                                <% String disabled = id_tipo_usuario>OptionsCfg.USUARIO_ADMINISTRADOR?"disabled":""; %>
                                                                <select class="form-control" name="id_usuario" id="id_usuario" <%=disabled%>>
                                                                    <option value="0">Todas</option>
                                                                    <% for(Usuario u:new TUsuario().getList()) {
                                                                        String selected = u.getId().equals(id_usuario)?"selected":"";
                                                                    %>
                                                                    
                                                                    <option value="<%=u.getId()%>" <%=selected%> ><%=u.getUsu_mail()%></option>
                                                                    <% } %>
                                                                </select>
                                                            </div>
                                                        </div>
                                                    </div>
                                            <div class="form-group col-lg-3">
                                                        <label class="control-label" for="id_estado">Estado</label>
                                                        <div class="controls">
                                                                <div class="input-group">
                                                                    <select class="form-control" name="id_estado" id="id_estado">
                                                                        <option value="0">Todas</option>
                                                                        <option value="<%=OptionsCfg.CAJA_ABIERTA%>">Abierta</option>
                                                                        <option value="<%=OptionsCfg.CAJA_CERRADA%>">Cerrada</option>
                                                                    </select>
                                                              </div>
                                                        </div>
                                                    </div>
                                        </div>
                                            <table class="table table-bordered table-condensed table-striped" id="tblCaja">
                                                <colgroup>
                                                    <col style="width:7%"></col>
                                                    <col style="width:7%"></col>
                                                    <col style="width:10%"></col>
                                                    <col style=""></col>
                                                    <col style="width:10%"></col>
                                                    <col style="width:10%"></col>
                                                    <col style="width:10%"></col>
                                                    <col style="width:10%"></col>
                                                </colgroup>
                                                <thead>
                                                    <tr>
                                                        <th>Fecha</th>
                                                        <th>Usuario</th>
                                                        <th>Estado</th>
                                                        <th>Concepto</th>
                                                        <th>Forma</th>
                                                        <th>Tipo</th>
                                                        <th>Importe</th>
                                                        <th>Saldo</th>                                                        
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
           $('#fecha_desde').change(filtrar_mdl_caja);
           $('#fecha_hasta').change(filtrar_mdl_caja);
           $('#id_estado').change(filtrar_mdl_caja);
           $('#id_usuario').change(filtrar_mdl_caja); 
           filtrar_mdl_caja();
        });
        
        function loadDataCaja(data){
            var $tabla = $('#tblCaja');
            $.ajax({
               url: '<%= PathCfg.REPORTE_CAJA_DETALLE_LIST %>',
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
        if(data.length===0) html ="<tr><td colspan='5' style='text-align:center'>No se encontr&oacute; ninguna caja</td></tr>";
        
        for(var i = 0;i< data.length;i++){
           html +="<tr class=''>";
           var d = data[i];
           for(var j=0;j<d.detalle.length;j++){
               var cd = d.detalle[j];               
               html += wrapTag('td',convertirFecha(d.fecha.fecha),'');
               html += wrapTag('td',d.usuario,'');
               html += wrapTag('td',d.estado,'');
               html += wrapTag('td',cd.concepto,'');
               html += wrapTag('td',cd.forma_pago,'');
               html += wrapTag('td',cd.tipo,'');
               html += wrapTag('td',cd.importe,'');
               html += wrapTag('td',cd.saldo,'');
               html +="</tr>";
           }           
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
    function filtrar_mdl_caja(){            
        var data = getDatosFiltro();
        loadDataCaja(data);

     }
   
    </script>
</body>
</html>
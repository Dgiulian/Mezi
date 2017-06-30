<%@page import="transaccion.TUsuario"%>
<%@page import="bd.Usuario"%>
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
                                        <H3>Reportes</H3>
                                    </div>
                                <div  class="box-content">
                                    <div class="row">
                                        <fieldset> <legend>Cajas</legend>
                                            <a href="<%=PathCfg.REPORTE_CAJA%>" class="btn btn-default">General de Caja</a>
                                            <a href="<%=PathCfg.REPORTE_CAJA_DETALLE%>" class="btn btn-default">Detalle por Caja</a>
                                        </fieldset>
                                        <fieldset> <legend>Contratos</legend>
                                            <a href="<%=PathCfg.REPORTE_CONTRATO%>" class="btn btn-default">Contratos</a>
                                            <a href="<%=PathCfg.REPORTE_PROPIETARIOS%>" class="btn btn-default">Propietarios</a>
                                            <a href="<%=PathCfg.REPORTE_COMISION_VENDEDOR%>" class="btn btn-default">Comision vendedor</a>
                                            <a href="<%=PathCfg.REPORTE_COMISION_ADMINISTRACION%>" class="btn btn-default">Comision Administracion</a>
                                        </fieldset>
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
         
        });
        
        
   
    </script>
</body>
</html>
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
                                            <H3>Listado de recibos
                                            <a href="<%=PathCfg.RECIBO_ANULAR%>" class="btn btn-danger">Anular recibo </a>
                                            </H3>
                                        </div>
                                    <div  class="box-content">

                                         <table class="table table-bordered table-condensed table-striped" id="tblRecibo">
                                            <thead>
                                                <tr>
                                                    <th>Fecha</th>
                                                    <th>N&uacute;mero</th>
                                                    <th>Cliente</th>
                                                    <th>Cliente</th>
                                                    <th>Tipo</th>
                                                    <th>Estado</th>
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
           loadDataRecibo({});
        });
        
        function loadDataRecibo(data){
            var $tabla = $('#tblRecibo');
            $.ajax({
               url: '<%= PathCfg.RECIBO_LIST %>',
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
        
        if(data.length===0) html ="<tr><td colspan='5' style='text-align:center'>A&uacute;n no se ha creado ninguna caja</td></tr>";
        for(var i = 0;i< data.length;i++){
           html +="<tr class=''>";
           var d = data[i];
                      
           html += wrapTag('td',convertirFecha(d.fecha),'');
           html += wrapTag('td',d.numero,'');
           html += wrapTag('td',d.cliente,'');
           html += wrapTag('td',d.tipo_cliente,'');           
           html += wrapTag('td',d.tipo_recibo,'');
           html += wrapTag('td',d.estado,'');
           var htmlPrint = "<a href='<%= PathCfg.RECIBO_PRINT%>?id="+ d.id +"' class='btn btn-xs btn-circle btn-primary'><span class='fa fa-print fw'></span></a> ";
            html +=wrapTag('td',htmlPrint,'');
           html +="</tr>";
           
       }      
       return html;
    }
          

    </script>
</body>
</html>
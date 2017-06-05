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
                                            <H3>Anular Recibo</H3>
                                        </div>
                                        <div  class="box-content">
                                            <div class="row">
                                                <div class="col-lg-2">
                                                    <div class="controls">
                                                        <label class="control-label" for="recibo_numero">N&uacute;mero recibo</label>
                                                          <div class="input-group ">
                                                            
                                                               <div class="input-group">
                                                                    <input type="text" id="recibo_numero" name="recibo_numero" class="form-control" value="" >
                                                                    <span class="input-group-addon" id="btnBuscar"><span class="fa fa-search"></span></span>
                                                                </div>
                                                            <input type="hidden" id="id_recibo" name="id_recibo" value="0">
                                                          </div>                                                        
                                                    </div>
                                                    
                                                </div>
                                                <div class="col-lg-1">
                                                    <div class="controls">
                                                        <div class="input-group">
                                                           <label>&nbsp;</label>
                                                           <!-- <button class="btn btn-sm btn-primary" id="btnBuscar">Buscar</button> -->
                                                            <button class="btn btn-sm btn-danger" id="btnAnular" style="display: block;">Anular</button>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="row">
                                                <div class="col-lg-2">
                                                    <div class="controls">
                                                        <label class="control-label" for="fecha">Fecha</label>
                                                          <div class="input-group ">
                                                            <input type="text" id="fecha" name="fecha" class="form-control" value="" readonly>                                                            
                                                          </div>                                                        
                                                    </div>
                                                </div>
                                                <div class="col-lg-2">
                                                    <div class="controls">
                                                        <label class="control-label" for="tipo_recibo">Tipo recibo</label>
                                                          <div class="input-group ">
                                                            <input type="text" id="tipo_recibo" name="tipo_recibo" class="form-control" value="" readonly>                                                            
                                                          </div>                                                        
                                                    </div>
                                                </div>
                                                <div class="col-lg-2">
                                                    <div class="controls">
                                                        <label class="control-label" for="contrato">Contrato</label>
                                                          <div class="input-group ">
                                                            <input type="text" id="contrato" name="contrato" class="form-control" value="" readonly>                                                            
                                                          </div>                                                        
                                                    </div>
                                                </div>
                                                <div class="col-lg-2">
                                                    <div class="controls">
                                                        <label class="control-label" for="carpeta">Carpeta</label>
                                                          <div class="input-group ">
                                                            <input type="text" id="carpeta" name="carpeta" class="form-control" value="" readonly>                                                            
                                                          </div>                                                        
                                                    </div>
                                                </div>
                                                <div class="col-lg-2">
                                                    <div class="controls">
                                                        <label class="control-label" for="cliente">Tipo cliente</label>
                                                          <div class="input-group ">
                                                            <input type="text" id="cliente" name="cliente" class="form-control" value="" readonly>                                                            
                                                          </div>                                                        
                                                    </div>
                                                </div>
                                            </div><!-- row -->
                                            <div class="row">
                                                <div class="col-lg-12">
                                                 <table class="table table-bordered table-condensed table-striped" id="tblRecibo">
                                                    <thead>
                                                        <tr>
                                                            <th>Fecha</th>
                                                            <th>Concepto</th>
                                                            <th>Debe</th>                                                    
                                                            <th>Haber</th>
                                                            <th>Saldo</th>
                                                        </tr>
                                                    </thead>
                                                    <tbody class=""></tbody>
                                                </table>   
                                                    
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
            $('#btnBuscar').click(hndBuscar);
            $('#btnAnular').click(hndAnular);
          // loadDataRecibo({});
        });
        function hndBuscar(){
            var data = getDataRecibo();
            loadDataRecibo(data);
        }
        function hndAnular(){
            var data   = getDataRecibo();
            var $tabla = $('#tblRecibo');
            if (data.id_recibo ===0) {
                bootbox.alert("No se encontr&oacute; el recibo");
                return;
            }
            bootbox.confirm("¿Est&aacute; seguro que desea anular el recibo? <br> <b>Esta operaci&oacute; no se puede deshacer</b>", function(result){
                if(result){
                    anularRecibo(data);                    
                }
            });
        }
        function anularRecibo(data){            
            $.ajax({
               url: '<%= PathCfg.RECIBO_ANULAR %>',
               data: data,
               method:"POST",
               dataType: "json",
               beforeSend:function(){},
               success: function(result) {                    
                   if(result.Result === "OK") {
                    if(result.Record.id) {
                         location.href = "<%=PathCfg.RECIBO_PRINT%>?id="+ result.Record.id;                         
                         //hndBuscar();
                     }
                   } else bootbox.alert(result.Message);
                }
           });    
        }
        function getDataRecibo(){
            var data = {};
            data.numero    = parseInt($('#recibo_numero').val()) || 0;
            data.id_recibo = parseInt($('#id_recibo').val())  || 0;
            return data;
        }
        function loadDataRecibo(data){
            var $tabla = $('#tblRecibo');
            $.ajax({
               url: '<%= PathCfg.RECIBO_SEARCH %>',
               data: data,
               method:"GET",
               dataType: "json",
               beforeSend:function(){
                   mostrarRecibo({});
               },
               success: function(result) {                    
                   if(result.Result === "OK") {
                       mostrarRecibo(result.Record);
                       $tabla.find('tbody').html(createTable(result.Records));
                   }else bootbox.alert(result.Message);
               }
           });
    }
    function mostrarRecibo(data){
        console.log(data);
        var fecha       = data.fecha? convertirFecha(data.fecha):'';
        var tipo_recibo = data.tipo_recibo?data.tipo_recibo:'';
        var contrato    = data.contrato?data.contrato:'';
        var cuenta      = data.cuenta?data.cuenta:'';
        var cliente     = data.cliente?data.cliente:'';
        var cliente     = data.cliente?data.cliente:'';
        var carpeta     = data.carpeta?data.carpeta:'';
        var id_recibo   = data.id?data.id:0;
        
        $('#fecha').val(fecha);
        $('#tipo_recibo').val(tipo_recibo);
        $('#contrato').val(contrato);
        $('#cuenta').val(cuenta);
        $('#cliente').val(cliente);
        $('#cliente').val(cliente);
        $('#carpeta').val(carpeta);
        $('#id_recibo').val(id_recibo);
    }
    
   function createTable(data){
        var html = "";
        if(data.length===0) html ="<tr><td colspan='5' style='text-align:center'>A&uacute;n no se ha creado ninguna caja</td></tr>";
        for(var i = 0;i< data.length;i++){
           html +="<tr class=''>";
           var d = data[i];
           html += wrapTag('td',convertirFecha(d.fecha),'');
           html += wrapTag('td',d.concepto,'');
           html += wrapTag('td',d.debe,'');
           html += wrapTag('td',d.haber,'');
           html += wrapTag('td',d.saldo,'');
           html +="</tr>";
       }      
       return html;
    }
          

    </script>
</body>
</html>
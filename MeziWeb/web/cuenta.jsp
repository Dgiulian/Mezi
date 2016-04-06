<%@page import="org.joda.time.format.DateTimeFormat"%>
<%@page import="org.joda.time.format.DateTimeFormatter"%>
<%@page import="org.joda.time.LocalDate"%>
<%@page import="bd.Localidad"%>
<%@page import="bd.Barrio"%>
<%@page import="bd.Propiedad"%>
<%@page import="bd.Cliente"%>
<%
    boolean con_cliente = false; 
    boolean con_propiedad = true;
    Cliente cliente = new Cliente();
    Propiedad propiedad = new Propiedad();
    Localidad localidad = new Localidad();
    Barrio barrio = new Barrio();
    Integer id_tipo_cliente = (Integer) request.getAttribute("id_tipo_cliente");
    
    DateTimeFormatter dtfOut = DateTimeFormat.forPattern("MM/dd/yyyy");
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
                                            <H3 style="font-height:15px">Administraci&oacute;n de cuenta corriente <%=(id_tipo_cliente==OptionsCfg.CLIENTE_TIPO_PROPIETARIO)?"propietario":"inquilino" %></H3>
                                        </div>
                                    <div  class="box-content">
                                        <div class="col-lg-12   ">
                                        <div class="col-lg-6 nopadding">
                                             <fieldset>
                                                    <legend>Datos del cliente  <%if (!con_cliente) {%><span class="btn btn-primary" data-toggle="modal" data-target="#mdlCliente">Seleccionar</span> <% } %></legend>
                                                    <input type="hidden" name="id_tipo_cliente" id="id_tipo_cliente" value="<%=id_tipo_cliente%>">
                                                        <div class="form-group row">
                                                                <div class="col-lg-2 nopadding">
                                                                    <div class="controls">
                                                                        <label class="control-label" for="id_inquilino">Carpeta</label>
                                                                          <div class="input-group ">
                                                                            <input type="text" id="id_inquilino" name="id_inquilino" class="form-control" value="<%=cliente.getId()%>" readonly>
                                                                          </div>
                                                                    </div>
                                                                </div>
                                                                <div class="col-lg-4 ">
                                                                    <div class="controls">
                                                                        <label class="control-label" for="nombre">Nombre</label>
                                                                          <div class="input-group ">
                                                                            <input type="text" id="nombre" name="nombre" class="form-control" value="<%=con_cliente?cliente.getNombre():""%>" readonly>
                                                                          </div>
                                                                    </div>
                                                                </div>
                                                                <div class="col-lg-4">
                                                                    <div class="controls">
                                                                        <label class="control-label" for="apellido">Apellido</label>
                                                                          <div class="input-group ">
                                                                            <input type="text" id="apellido" name="apellido" class="form-control" value="<%=cliente.getApellido()%>" readonly>
                                                                          </div>
                                                                    </div>
                                                                </div>
<!--                                                                <div class="col-lg-4">
                                                                    <div class="controls">
                                                                        <label class="control-label" for="id_tipo_cliente">Tipo</label>
                                                                          <div class="input-group ">
                                                                            <input type="radio" id="id_tipo_cliente" name="id_tipo_cliente" class="radio radio-inline" value="1" checked> Inquilino
                                                                            <input type="radio" id="id_tipo_cliente" name="id_tipo_cliente" class="radio radio-inline" value="2" > Propietario
                                                                          </div>
                                                                    </div>
                                                                </div>-->
                                                            <%if (!con_cliente) {%>
<!--                                                            <div class="col-lg-2 ">
                                                                <div class="controls">
                                                                    <label class="control-label" for="id_inquilino">&nbsp;</label>
                                                                      <div class="input-group ">
                                                                        <span class="btn btn-primary" data-toggle="modal" data-target="#mdlCliente">Seleccionar</span>
                                                                      </div>
                                                                </div>
                                                            </div>-->
                                                            <%}%>
                                                        
                                                        </div><!-- row -->
                                                        </fieldset>
                                                            <div class="col-lg-4 ">
                                                                <div class="controls">
                                                                    <label class="control-label" for="fecha_consulta">Fecha consulta</label>
                                                                      <div class="input-group ">
                                                                          <input type="text" class="form-control" id="fecha_consulta" name="fecha_consulta" value="<%=dtfOut.print(new LocalDate())%>" disabled>
                                                                      </div>
                                                                </div>
                                                            </div>
                                                            <div class="col-lg-4 ">
                                                                <div class="controls">
                                                                    <label class="control-label" for="fecha_liquidacion">&Uacute;ltima Liquidaci&oacute;n</label>
                                                                      <div class="input-group ">
                                                                          <input type="text" class="form-control" id="fecha_liquidacion" name="fecha_liquidacion" disabled>
                                                                      </div>
                                                                </div>
                                                            </div>
                                                            
                                                </div>
                                                <!--<div class="row">-->
                                                <div class="col-lg-6">
                                                    <fieldset>
                                                    <legend>Datos del inmueble</legend>
                                                     <div class="col-lg-12" >
                                                         <input type="hidden" name="id_propiedad" id="id_propiedad" value="<%=propiedad.getId()%>">
                                                         <table class="table table-condensed table-striped" id="tblContrato">
                                                            
                                                             <thead>
                                                                 <tr>
                                                                     <td>Direcci&oacute;n</td>
                                                                 </tr>
                                                             </thead>
                                                             <tbody></tbody>
                                                         </table>
<!--                                                        <div class="form-group row">
                                                                <div class="col-lg-12 nopadding">
                                                                    <div class="controls">
                                                                        <label class="control-label" for="calle">Direcci&oacute;n</label>
                                                                          <div class="input-group col-lg-12 ">
                                                                            <input type="text" id="direccion" name="calle" class="form-control" value="<%=propiedad.getDireccion()%>"  readonly>
                                                                          </div>
                                                                    </div>
                                                                </div>                                                                
                                                            <% if(!con_propiedad) {%>
                                                                <div class="col-lg-2 ">
                                                                    <div class="controls">
                                                                        <label class="control-label" for="id_propiedad">&nbsp;</label>
                                                                          <div class="input-group ">
                                                                            <span class="btn btn-primary" data-toggle="modal" data-target="#mdlPropiedad">Seleccionar</span>
                                                                          </div>
                                                                    </div>
                                                                </div>
                                                            <% } %>
                                                        </div> row -->
                                                     </div> <!-- col-lg-12 -->
                                                    </fieldset>
                                                </div>
                                    </div>
                                    <!--</div>-->
                                    <input type="hidden" name="id_contrato" id="id_contrato" val="">
                                    
                                    
                                    <div class="row">   
                                        <hr /> 
                                        <div class="col-lg-6">
                                            <h2><i class="fa fa-edit"></i>Cuenta Oficial 
                                                <span class="btn btn-primary" id="btnAjOficial"  data-id_cuenta='' data-toggle="modal" data-target="#mdlConcepto">Ajustar</span>                                            
                                                <span class="btn btn-primary" id="btnLiqOficial" data-id_cuenta='' data-toggle="modal" data-target="#mdlLiquidar" id="btnLiquidar">Liquidar</span>
                                            </h2>
                                            <table class="table table-bordered table-condensed table-striped" id="tblCuentaOficial">
                                                    <colgroup>
                                                        <col style="width:10%">
                                                        <col style="">
                                                        <col style="width:10%;text-align: center">
                                                        <col style="width:10%">
                                                        <col style="width:5%">
                                                    </colgroup>
                                                    <thead>
                                                        <tr>
                                                            <th>Fecha</th>
                                                            <th>Concepto</th>
                                                            <th style="text-align: center">Debe</th>
                                                            <th style="text-align: center">Haber</th>
                                                            <th style="text-align: center">Saldo</th>
                                                        </tr>
                                                    </thead>    
                                                    <tbody>                                                                                                                        
                                                    </tbody>
<!--                                                    <tfoot>
                                                    <th>
                                                        <td colspan=""></td>     
                                                        <td colspan="2"></td>
                                                        <td colspan=""></td>                                                        
                                                    </th>
                                                    </tfoot>-->
                                            </table>
                                        </div>
                                        <div class="col-lg-6">
                                            <h2><i class="fa fa-edit"></i>Cuenta no oficial 
                                                <span class="btn btn-primary" id="btnAjNoOficial"  data-id_cuenta='' data-toggle="modal" data-target="#mdlConcepto">Ajustar</span>
                                                <span class="btn btn-primary" id="btnLiqNoOficial" data-id_cuenta='' data-toggle="modal" data-target="#mdlLiquidar" id="btnLiquidar">Liquidar</span></h2>
                                            <table class="table table-bordered table-condensed table-striped" id="tblCuentaNoOficial">
                                                    <colgroup>
                                                        <col style="width:10%">
                                                        <col style="">
                                                        <col style="width:10%;text-align: center">
                                                        <col style="width:10%">
                                                        <col style="width:5%">
                                                    </colgroup>
                                                    <thead>
                                                        <tr>
                                                            <th>Fecha</th>
                                                            <th>Concepto</th>
                                                            <th style="text-align: center">Debe</th>
                                                            <th style="text-align: center">Haber</th>
                                                            <th style="text-align: center">Saldo</th>
                                                        </tr>
                                                    </thead>    
                                                    <tbody>                                                                                                                        
                                                    </tbody>
<!--                                              <tfoot>
                                                    <th>
                                                        <td colspan=""></td>     
                                                        <td colspan="2"></td>
                                                        <td colspan=""></td>                                                        
                                                    </th>
                                                  </tfoot>-->
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
	
	<!-- page scripts -->
	<script src="assets/js/jquery-ui-1.10.3.custom.min.js"></script>
        <script src="assets/js/bootstrap-datepicker.min.js"></script>
	<script src="assets/js/bootstrap-timepicker.min.js"></script>
	<script src="assets/js/moment.min.js"></script>
	
	<!-- theme scripts -->
	<script src="assets/js/custom.min.js"></script>
	<script src="assets/js/core.min.js"></script>
		
	<!-- end: JavaScript-->
	 <script src="assets/js/bootbox.min.js"></script>	
        <script src="assets/js/common-functions.js"></script>
        <script language="">
        $(document).ready(function(){
           
//           $('#nombre_search').change(filtrar_cuenta);
//           $('#apellido_search').change(filtrar_cuenta);
//           $('#dni_search').change(filtrar_cuenta);
//           filtrar_cuenta();

            
        });
        
        function loadData(data){
            var $tabla;    
            var $btnAjustar;
            var $btnLiquidar;
            if(data.id_tipo===2){
                $tabla      = $('#tblCuentaNoOficial');
                $btnAjustar = $('#btnAjNoOficial');
                $btnLiquidar = $('#btnLiqNoOficial');
            } else {
               $tabla      = $('#tblCuentaOficial');
               $btnAjustar = $('#btnAjOficial');
               $btnLiquidar = $('#btnLiqOficial');
            }
            
            $.ajax({
               url: '<%= PathCfg.CUENTA_DET_LIST %>',
               data: data,
               method:"POST",
               dataType: "json",
               beforeSend:function(){
                    var cant_cols = $tabla.find('thead th').length;
                    $tabla.find('tbody').html("<tr><td colspan='" + cant_cols + "'><center><img src='assets/img/ajax-loader.gif'/></center></td></tr>");
               },
               success: function(result) {
                   if(result.Result === "OK") {
                       $('#fecha_liquidacion').val(convertirFecha(result.Record.fecha_liquidacion));
                       $tabla.find('tbody').html(createTableCuenta(result.Records));                       
                       $btnAjustar.data('id_cuenta',result.Record.id);
                       $btnLiquidar.data('id_cuenta',result.Record.id);
                   } else { 
                       var  html = result.Message;
                       html = "<td colspan='5' style='text-align:center'>" + html + "</td>";
                       html = wrapTag("tr",html);
                       $tabla.find('tbody').html(html);                       
                   }
               }
           });
    }
   function createTableCuenta(data){
        var html = "";
        var saldo = 0;
        for(var i = 0;i< data.length;i++){
           html +="<tr class=''>";
           var d = data[i];
           saldo += d.debe - d.haber;
           html += wrapTag('td',convertirFecha(d.fecha),'');
           html += wrapTag('td',d.concepto,'');
           html += wrapTag('td',d.debe,'numeric');
           html += wrapTag('td',d.haber,'numeric');
           html += wrapTag('td',saldo,'numeric');
            
//            html +='<td style="width:75px"  >' + '</td>';
//            html +=wrapTag('td',htmlEdit + htmlDel,'');
           html +="</tr>";
       }      
       return html;
    }  
    function filtrar_cuenta(){            
        var id_cliente = $('#id_inquilino').val();
        var id_contrato = $('#id_contrato').val();
        var dni_search = $('#id_propiedad').val();
        
        var data ={
            id_cliente: id_cliente,
            id_tipo:1,
            id_tipo_cliente:1,
            id_contrato: id_contrato,
            dni: dni_search  ,
        };
        loadData(data);
        data.id_tipo = 2;
        loadData(data);

     }
   function completarCliente(data){
        $('#id_inquilino').val(data.id);
        $('#nombre').val(data.nombre);
        $('#apellido').val(data.apellido);
        $('#dni').val(data.dni);
        $('#cuil').val(data.cuil);
        var $id_tipo_cliente = $('#id_tipo_cliente').val();
        listar_contrato({id_cliente:data.id,id_tipo:$id_tipo_cliente});
        //buscar_contrato({id_cliente:data.id,id_tipo:$id_tipo_cliente});
    }
    function listar_contrato(data){
        var $tabla = $('#tblContrato');
        $.ajax({
               url: '<%= PathCfg.CONTRATO_LIST %>',
               data: data,
               method:"POST",
               dataType: "json",
               beforeSend:function(){      
                    var cant_cols = $tabla.find('thead th').length;
                    $tabla.find('tbody').html("<tr><td colspan='" + cant_cols + "'><center><img src='assets/img/ajax-loader.gif'/></center></td></tr>");
               },
               success: function(result) {
                   if(result.Result === "OK") {
                       $tabla.find('tbody').html(createTableContrato(result.Records));
                       $('input[name="rdPropiedad"]').change(function(){
                           $('#id_contrato').val($(this).val());
                          //$('#id_propiedad').val($(this).val());
                          filtrar_cuenta();
                       });
//                       var d = result.Record;                       
//                       if(d!==undefined){
//                        $('#id_contrato').val(d.id);
//                        buscarPropiedad({id:d.id_propiedad});
//                        filtrar_cuenta();
//                       } else $('#id_contrato').val(0);
                   }
               }
           });
    }
    function createTableContrato(data){
        var html = "";
        for(var i = 0;i< data.length;i++){
           html +="<tr class=''>";
           d = data[i];
           html += wrapTag('td',d.direccion,'');
           html += wrapTag('td',d.estado_contrato,'');           
           var htmlRadio = "<span class='btn btn-xs btn-circle  btn-primary'><input name='rdPropiedad' type='radio' value='" + d.id + "'></span></a> ";
           html +='<td style="width:75px"  >' + htmlRadio + '</td>';
           html +="</tr>";
       }      
       return html;
    }
    function buscar_contrato(data){
        $.ajax({
               url: '<%= PathCfg.CONTRATO_SEARCH %>',
               data: data,
               method:"POST",
               dataType: "json",
               beforeSend:function(){                    
               },
               success: function(result) {
                   if(result.Result === "OK") {
                       var d = result.Record;                       
                       if(d!==undefined){
                        $('#id_contrato').val(d.id);
                        buscarPropiedad({id:d.id_propiedad});
                        filtrar_cuenta();
                       } else $('#id_contrato').val(0);
                   }
               }
           });
    }
function completarPropiedad(data){
    $('#id_propiedad').val(data.id);
    $('#direccion').val(data.direccion);
//    $('#calle').val(data.calle);
//    $('#numero').val(data.numero);
//    $('#piso').val(data.piso);
//    $('#dpto').val(data.dpto);
//    $('#barrio').val(data.barrio);    
//    $('#localidad').val(data.localidad);
}

    </script>
    <%@include file="mdl_cliente.jsp" %>
    <%@include file="mdl_propiedad.jsp" %>
    <%@include file="mdl_concepto.jsp" %>
    <%@include file="mdl_liquidar.jsp" %>
</body>
</html>
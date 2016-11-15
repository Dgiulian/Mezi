<%@page import="bd.Caja"%>
<%@page import="bd.Cuenta"%>
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
    String fecha_consulta = TFecha.ahora(TFecha.formatoVista);
//    fecha_consulta = "05/01/2016";
    Caja caja = (Caja) request.getAttribute("caja");
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
                                            <H3 style="font-size:15px">Administraci&oacute;n de cuenta corriente <%=(id_tipo_cliente==OptionsCfg.CLIENTE_TIPO_PROPIETARIO)?"propietario":"inquilino" %></H3>
                                        </div>
                                    <div  class="box-content">
                                        <div class="col-lg-12   ">
                                        <div class="col-lg-6 nopadding">
                                             <fieldset>
                                                    <legend>Datos del cliente  <%if (!con_cliente) {%><span class="btn btn-sm btn-primary" data-toggle="modal" data-target="#mdlCliente">Seleccionar</span> <% } %></legend>
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
                                                        </div><!-- row -->
                                                        </fieldset>
                                                                          
                                                        <div class="col-lg-5">
                                                            <div class="controls">
                                                                <label class="control-label" for="fecha_desde_oficial">Fecha desde</label>
                                                                  <div class="input-group date  date-picker">
                                                                      <input type="text" class="form-control date-input" id="fecha_desde_oficial" name="fecha_desde_oficial" >
                                                                      <span class="input-group-addon"><span class="fa fa-calendar"></span></span>
                                                                  </div>
                                                            </div>
                                                        </div>
                                                        <div class="col-lg-5 ">
                                                            <div class="controls">
                                                                <label class="control-label" for="fecha_hasta_oficial">Fecha hasta</label>
                                                                  <div class="input-group date date-picker">
                                                                      <input type="text" class="form-control date-input" id="fecha_hasta_oficial" name="fecha_hasta_oficial" value="<%=fecha_consulta%>">
                                                                      <span class="input-group-addon"><span class="fa fa-calendar"></span></span>  
                                                                  </div>
                                                            </div>
                                                        </div>
                                                        <div class="col-lg-12 ">
                                                            <div class="controls">
                                                                <label class="control-label" for="">&nbsp;</label>
                                                                <div class="controls ">
                                                                <span class="btn btn-sm btn-primary" id="btnActualizarOficial" >Actualizar </span>
                                                                <span class="btn btn-sm btn-primary" id="btnImprimirOficial">Imprimir</span>
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
                                                                     <th>Domicilios</th>
                                                                 </tr>
                                                             </thead>
                                                             <tbody></tbody>
                                                         </table>
                                                     </div> <!-- col-lg-12 -->
                                                    </fieldset>
                                                         <div class="col-lg-12 nopadding">
                                                              
                                                        <div class="col-lg-5 ">
                                                            <div class="controls">
                                                                <label class="control-label" for="fecha_desde_no_oficial">Fecha desde</label>
                                                                  <div class="input-group  date date-picker">
                                                                      <input type="text" class="form-control date-input" id="fecha_desde_no_oficial" name="fecha_desde_no_oficial" >
                                                                      <span class="input-group-addon"><span class="fa fa-calendar"></span></span>  
                                                                  </div>
                                                            </div>
                                                        </div>
                                                        <div class="col-lg-5 ">
                                                            <div class="controls">
                                                                <label class="control-label" for="fecha_hasta_no_oficial">Fecha hasta</label>
                                                                  <div class="input-group date date-picker"">
                                                                      <input type="text" class="form-control date-input" id="fecha_hasta_no_oficial" name="fecha_hasta_no_oficial" value="<%=fecha_consulta%>">
                                                                      <span class="input-group-addon"><span class="fa fa-calendar"></span></span>  
                                                                  </div>
                                                            </div>
                                                        </div>
                                                        <div class="col-lg-12 ">
                                                            <label class="control-label" for="">&nbsp;</label>
                                                            <div class="controls">                                                                
                                                                <span class="btn btn-sm btn-primary" id="btnActualizarNoOficial" sytle="margin-right:2px;">Actualizar </span> 
                                                                <span class="btn btn-sm btn-primary" id="btnImprimirNoOficial">Imprimir</span>
                                                                
                                                            </div>
                                                        </div>
                                                        </div>
                                                </div>
                                    </div>
                                    <!--</div>-->
                                    <input type="hidden" name="id_contrato" id="id_contrato" val="">
                                    
                                    
                                    <div class="row">   
                                        <hr /> 
                                        <div class="col-lg-6">
                                            <h2><i class="fa fa-edit"></i>Cuenta Oficial 
                                                <input type="hidden" name="id_cuenta_oficial" id="id_cuenta_oficial" >
                                                <span class="btn btn-sm btn-primary" id="btnAjOficial"  data-id_cuenta='' data-toggle="modal" data-target="#mdlConcepto">Ajustar</span>                                             
                                                <% if(caja!=null && caja.getId_estado()==OptionsCfg.CAJA_ABIERTA) {%>
                                                    <span class="btn btn-sm btn-primary" id="btnLiqOficial" data-id_cuenta='' data-toggle="modal" data-target="#mdlLiquidar" id="btnLiquidar">Liquidar</span>
                                                <% } else {%>
                                                <h6> No hay ninguna caja abierta. No se puede liquidar</h6>
                                                <% } %>
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
<!--                                                <tfoot>
                                                    <th>
                                                        <td colspan=""></td>     
                                                        <td colspan="2"><b style="text-align: right">Saldo Total</b></td>
                                                        <td colspan="" id="saldoOficial"></td>                                                        
                                                    </th>
                                              </tfoot>-->
                                            </table>
                                        </div>
                                        <div class="col-lg-6">
                                            <h2><i class="fa fa-edit"></i>Cuenta no oficial 
                                                <input type="hidden" name="id_cuenta_no_oficial" id="id_cuenta_no_oficial" >
                                                <span class="btn btn-sm btn-primary" id="btnAjNoOficial"  data-id_cuenta='' data-toggle="modal" data-target="#mdlConcepto">Ajustar</span>
                                                <% if(caja!=null && caja.getId_estado()==OptionsCfg.CAJA_ABIERTA) {%>
                                                <span class="btn btn-sm btn-primary" id="btnLiqNoOficial" data-id_cuenta='' data-toggle="modal" data-target="#mdlLiquidar" id="btnLiquidar">Liquidar</span></h2>                                                
                                                 <% } else {%>
                                                <h6> No hay ninguna caja abierta. No se puede liquidar</h6>
                                                <% } %>
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
                                                        <td colspan="2"><b style="text-align: right">Saldo Total</b></td>
                                                        <td colspan="" id="saldoNoOficial"></td>                                                        
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
	<script src="assets/js/handlebars-v4.0.5.js"></script>
        
	<!-- page scripts -->
	<script src="assets/js/jquery-ui-1.10.3.custom.min.js"></script>
        <script src="assets/js/bootstrap-datepicker.min.js"></script>
        <script src="assets/js/locales/bootstrap-datepicker.es.min.js"></script>
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

            $('#btnActualizarOficial').click(function(){               
                loadData(recuperarDataOficial());
            });
            $('#btnActualizarNoOficial').click(function(){
                loadData(recuperarDataNoOficial());
            });
            
            $('#btnImprimirOficial').click(function(){
                imprimir(recuperarDataOficial());
            });
            $('#btnImprimirNoOficial').click(function(){
                imprimir(recuperarDataNoOficial());        
            });
        });
        function imprimir(data){
            if (data.id_cuenta===0) {
                    bootbox.alert("Debe seleccionar la cuenta");
                    return;
            }
            location.href = "<%=PathCfg.CUENTA_PRINT%>?id="+encodeURIComponent(data.id_cuenta) + "&fecha_desde=" + encodeURIComponent(data.fecha_desde) + "&fecha_hasta=" + encodeURIComponent(data.fecha_hasta); 
        }
        function recuperarDataOficial(){
            var $id_cuenta = parseInt($('#id_cuenta_oficial').val());
            var $fecha_desde = $('#fecha_desde_oficial').val();
            var $fecha_hasta = $('#fecha_hasta_oficial').val();
            
            if (isNaN($id_cuenta)) $id_cuenta = 0;
            
            return {
                    id_cuenta: $id_cuenta,  
                    id_tipo:  1,
                    fecha_desde: $fecha_desde,
                    fecha_hasta: $fecha_hasta,
                }
        }
        function recuperarDataNoOficial(){
            var $id_cuenta   = parseInt($('#id_cuenta_no_oficial').val());
            if (isNaN($id_cuenta)) $id_cuenta = 0;
            var $fecha_desde = $('#fecha_desde_no_oficial').val();
            var $fecha_hasta = $('#fecha_hasta_no_oficial').val();
        
            return {
                    id_cuenta: $id_cuenta,  
                    id_tipo: 2,
                    fecha_desde: $fecha_desde,
                    fecha_hasta: $fecha_hasta,
                    }
        }
        function buscar_cuenta(data){            
            var $tabla;    
            var $btnAjustar;
            var $btnLiquidar;
            var $btnDesde;      
            console.log("T: ",data);
            if(data.id_tipo===2){
                $tabla       = $('#tblCuentaNoOficial');
                $btnAjustar  = $('#btnAjNoOficial');
                $btnLiquidar = $('#btnLiqNoOficial');
                $btnDesde    = $('#fecha_desde_no_oficial');
                data.fecha_desde = $('#fecha_desde_no_oficial').val();
                data.fecha_hasta = $('#fecha_hasta_no_oficial').val();
            } else {
               $tabla       = $('#tblCuentaOficial');
               $btnAjustar  = $('#btnAjOficial');
               $btnLiquidar = $('#btnLiqOficial');
               $btnDesde    = $('#fecha_desde_oficial');
               data.fecha_desde = $('#fecha_desde_oficial').val();
               data.fecha_hasta = $('#fecha_hasta_oficial').val();
            }
            
            $.ajax({
               url: '<%= PathCfg.CUENTA_SEARCH %>',
               data: data,
               method:"POST",
               dataType: "json",
               beforeSend:function(){
                    var cant_cols = $tabla.find('thead th').length;
                    $tabla.find('tbody').html("<tr><td colspan='" + cant_cols + "'><center><img src='assets/img/ajax-loader.gif'/></center></td></tr>");
               },
               success: function(result) {
                   if(result.Result === "OK") {
                       if(result.Record.fecha_liquidacion!==undefined)
                            $btnDesde.val(convertirFecha(result.Record.fecha_liquidacion));
                        if(result.Record.id_tipo===<%=OptionsCfg.CUENTA_OFICIAL%>)
                            $('#id_cuenta_oficial').val(result.Record.id);
                        
                        else $('#id_cuenta_no_oficial').val(result.Record.id);
                        
                       $btnAjustar.data('id_cuenta',result.Record.id);
                       $btnLiquidar.data('id_cuenta',result.Record.id);
                       data.fecha_desde = $btnDesde.val();
                       loadData(data);
                   } else { 
                       var  html = result.Message;
                       html = "<td colspan='5' style='text-align:center'>" + html + "</td>";
                       html = wrapTag("tr",html);
                       $tabla.find('tbody').html(html);                       
                   }
               }
           });
    }
    //
        function loadData(data){
            
            var $tabla;    
            var $btnAjustar;
            var $btnLiquidar;
            console.log(data);
            if(data.id_tipo===2){
                $tabla       = $('#tblCuentaNoOficial');
                $btnAjustar  = $('#btnAjNoOficial');
                $btnLiquidar = $('#btnLiqNoOficial');
            } else {
               $tabla       = $('#tblCuentaOficial');
               $btnAjustar  = $('#btnAjOficial');
               $btnLiquidar = $('#btnLiqOficial');
               $saldoTotal = $('#')
               
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
                       if(result.Record.fecha_liquidacion!==undefined)
                        $('#fecha_desde').val(convertirFecha(result.Record.fecha_liquidacion));
                       $tabla.find('tbody').html(createTableCuenta(result.Records));                       
                       $btnAjustar.data('id_cuenta',result.Record.id);
                       $btnLiquidar.data('id_cuenta',result.Record.id);
                       var mnt_liq = parseFloat($tabla.find('tr:last').find('td:last').text());
                       $btnLiquidar.data('mnt_liq',mnt_liq);
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
          var id_tipo_cliente = $('#id_tipo_cliente').val();
          if(parseInt(id_tipo_cliente)===<%=OptionsCfg.CLIENTE_TIPO_PROPIETARIO%>)
            saldo += d.haber.toFixed(2) - d.debe.toFixed(2);
          else 
           saldo += d.debe.toFixed(2) - d.haber.toFixed(2);
           if(d.id_concepto ===<%=OptionsCfg.CONCEPTO_PAGO%>) saldo = 0;
           html += wrapTag('td',convertirFecha(d.fecha),'');
           html += wrapTag('td',d.concepto,'');
           html += wrapTag('td',d.debe.toFixed(2),'numeric');
           html += wrapTag('td',d.haber.toFixed(2),'numeric');
           html += wrapTag('td',saldo.toFixed(2),'numeric');
           html +="</tr>";
       }      
       return html;
    }  
    function filtrar_cuenta(){            
        var id_cliente    = $('#id_inquilino').val();
        var id_contrato   = $('#id_contrato').val();
        var id_propiedad  = $('#id_propiedad').val();   
        var id_tipo_cliente = $('#id_tipo_cliente').val();
//        var data = {
//            id_cliente: id_cliente,
//            id_tipo: 1,
//            id_tipo_cliente:1,
//            id_contrato: id_contrato,
//            id_propiedad: id_propiedad,
//        
//        };

        buscar_cuenta({
            id_cliente: id_cliente,
            id_tipo: 1,
            id_tipo_cliente:id_tipo_cliente,
            id_contrato: id_contrato,
            id_propiedad: id_propiedad,
        
        });
        buscar_cuenta({
            id_cliente: id_cliente,
            id_tipo: 2,
            id_tipo_cliente:id_tipo_cliente,
            id_contrato: id_contrato,
            id_propiedad: id_propiedad,
        
        });
   }
   function completarCliente(data){
        $('#id_inquilino').val(data.id);
        $('#nombre').val(data.nombre);
        $('#apellido').val(data.apellido);
        $('#dni').val(data.dni);
        $('#cuil').val(data.cuil);
        var $id_tipo_cliente = $('#id_tipo_cliente').val();
        console.log(data.id);
        if(data.id !==0)
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
                          filtrar_cuenta();
                       });
                       if(result.TotalRecordCount===1) {
                           $('input[name="rdPropiedad"]').prop('checked' ,true);
                           $('input[name="rdPropiedad"]').trigger('change');
                       }
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
                        //buscar_cuenta();
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
    <%@include file="mdl_pago_adelantado.jsp" %>
</body>
</html>
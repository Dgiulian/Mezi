<%@page import="java.util.Map"%>
<%@page import="utils.OptionsCfg.Option"%>
<%@page import="utils.TFecha"%>
<%@page import="bd.Caja"%>
<%
    Caja caja = (Caja) request.getAttribute("caja");
    if (caja==null) return;
    Map<Integer,Option> mapEstadosCaja = OptionsCfg.getMap(OptionsCfg.getEstadosCaja());
    
    String estadoCaja = mapEstadosCaja.get(caja.getId_estado()).getDescripcion();
    
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
                                            <H3>Caja diaria 
                                            <% if(caja.getId_estado()==OptionsCfg.CAJA_ABIERTA) { %>
                                                <button class="btn btn-primary" id="btnAgregar">Agregar</button>
                                                <button class="btn btn-warning" id="btnCerrar">Cerrar</button>
                                            <% }%>
                                            </H3>
                                        </div>
                                    <div  class="box-content">
                                        <div class="row">
                                          <fieldset disabled>
                                              <input type="hidden" id="id_caja" name="id_caja" value="<%= caja.getId()%>">
                                              <div class="col-lg-2">
                                                <div clas="form-group">
                                                    <label for="nombre_search">Estado</label>
                                                    <div class="input-group">                                                                                            
                                                        <input type="text" class="form-control" size="20" value="<%=estadoCaja%>">
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="col-lg-2">
                                                <div clas="form-group">
                                                    <label for="nombre_search">Fecha</label>
                                                    <div class="input-group">                                                                                            
                                                        <input type="text" class="form-control" size="20" value="<%=TFecha.formatearFechaBdVista(caja.getFecha().getFecha())%>">
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="col-lg-2">
                                                <div clas="form-group">
                                                    <label for="efectivo_anterior">Efectivo anterior</label>
                                                    <div class="input-group">                                                                                            
                                                        <input type="text" class="form-control" size="20" value="<%=caja.getEfectivo_anterior()%>">
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="col-lg-2">
                                                <div clas="form-group">
                                                    <label for="efectivo_anterior">Cheque anterior</label>
                                                    <div class="input-group">                                                                                            
                                                        <input type="text" class="form-control" size="20" value="<%=caja.getCheque_anterior()%>">
                                                    </div>
                                                </div>
                                            </div>
                                            <%if(caja.getId_estado()!=OptionsCfg.CAJA_ABIERTA) {%>
                                                <div class="col-lg-2">
                                                 <div clas="form-group">
                                                        <label for="efectivo_cierre">Efectivo cierre</label>
                                                        <div class="input-group">                                                                                            
                                                            <input type="text" class="form-control" size="20" value="<%=caja.getEfectivo_cierre()%>">
                                                        </div>
                                                 </div>
                                                </div>
                                                <div class="col-lg-2">
                                                 <div clas="form-group">
                                                        <label for="cheque_cierre">Cheques cierre</label>
                                                        <div class="input-group">                                                                                            
                                                            <input type="text" class="form-control" size="20" value="<%=caja.getCheque_cierre()%>">
                                                        </div>
                                                 </div>
                                                </div>
                                            <% }%>
                                           </fieldset>
                                        </div>
                                           <div class="row">
                                           <div class="form-group">
                                            <label class="control-label" for="id_forma">Forma de pago</label>
                                            <div class="input-group">
                                              <select class="form-control input-md " id="id_forma">
                                                <option value="0">Todos</option>
                                                <% for(Option o:OptionsCfg.getFormaPago()) {%>
                                                    <option value="<%=o.getId()%>"><%=o.getDescripcion()%></option>

                                                <% } %>
                                              </select>
                                          </div>
                                           </div>
                                        <div class="row">
                                            <div class="col-lg-12">
                                            <table class="table table-bordered table-condensed table-striped" id="tblCajaDetalle">
<!--                                              <colgroup>
                                                  <col style=""></col>
                                                  <col style="width:10%"></col>
                                                  <col style="width:10%"></col>
                                                  <col style="width:10%"></col>
                                                  <col style="width:5%"></col>
                                              </colgroup>-->
                                              <thead>
                                                  <tr>
                                                      <th>Concepto</th>
                                                      <th>Cuenta</th>
                                                      <th>Forma</th>
                                                      <th>Tipo</th>
                                                      <th>Importe</th>
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
	<script src="assets/js/handlebars-v4.0.5.js"></script>
        
<!--        <script src="assets/templates/aperturaCaja.js"></script>-->
        
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
        <script id="agregarCajaDetalle" type="text/x-handlebars-template">
           <div class="row">
            <div class="col-md-12">
              <form class="form-vertical">
                   <div class="form-group">
                      <label class="col-md-4 control-label" for="concepto">Concepto</label>
                      <div class="col-md-8 input-group">
                      <input id="concepto" name="concepto" type="text" class="form-control input-md " value="">
                      </div>
                   </div>
                   <div class="form-group">
                      <label class="col-md-4 control-label" for="importe">Importe</label>
                      <div class="col-md-8 input-group">
                      <input id="importe" type="text" class="form-control input-md " value="">
                      </div>
                   </div>
                    <div class="form-group">
                      <label class="col-md-4 control-label" for="id_tipo">Tipo</label>
                      <div class="col-md-8 input-group">
                      <input name="id_tipo" type="radio" class="" value="1">Ingreso
                      <input name="id_tipo" type="radio" class="" value="2">Egreso                      
                      </div>
                   </div>
                   <div class="form-group">
                      <label class="col-md-4 control-label" for="id_forma">Forma</label>
                      <div class="col-md-8 input-group">
                      <input name="id_forma" type="radio" class="" value="1">Efectivo
                      <input name="id_forma" type="radio" class="" value="2">Cheque
                      <input name="id_forma" type="radio" class="" value="3">Transferencia
                      </div>
                   </div>
                    <div class="form-group">
                      <label class="col-md-4 control-label" for="id_cuenta">Cuenta</label>
                      <div class="col-md-8 input-group">
                        <select class="form-control input-md " id="id_cuenta">
                        {{#each cuentas_internas}}
                            <option value={{id}}>{{nombre}}</option>
                        {{/each}}                    
                        </select>
                    </div>
                  </div>
                </form>
            </div>
          </div> 
        </script>
        <script id="CajaDetalleList" type="text/x-handlebars-template">
            {{#each detalles}}
            <tr>
                <td>{{concepto}}</td>                
                <td>{{nombre_cuenta}}</td>                
                <td>{{forma_pago}}</td>                
                <td>{{tipo}}</td>                
                <td>{{importe}}</td>
                <td>{{saldo}}</td>
            </tr>           
            {{else}}
            <tr><td colspan="6" ><center>A&uacute;n no se han realizado movimientos</center></td></tr>
            {{/each}}           
        </script>
        <script id="cerrarCaja" type="text/x-handlebars-template">
           <div class="row">
            <div class="col-md-12">
              <form class="form-vertical">
                <fieldset disabled>
                    <div class="form-group">
                      <label class="col-md-4 control-label" for="saldo_efectivo">Saldo Efectivo</label>
                      <div class="col-md-8 input-group">
                      <input id="saldo_efectivo" type="text" class="form-control input-md " value="{{saldo_efectivo}}">
                      </div>
                    </div>
                   <div class="form-group">
                      <label class="col-md-4 control-label" for="saldo_cheques">Saldo cheques</label>
                      <div class="col-md-8 input-group">
                      <input id="saldo_cheques" type="text" class="form-control input-md " value="{{saldo_cheques}}">
                      </div>
                   </div>
                    <div class="form-group">
                      <label class="col-md-4 control-label" for="saldo_transferencia">Saldo transferencia</label>
                      <div class="col-md-8 input-group">
                      <input id="saldo_transferencia" type="text" class="form-control input-md " value="{{saldo_transferencia}}">
                      </div>
                   </div>
                </fieldset>
                <fieldset enabled>
                   <div class="form-group">
                      <label class="col-md-4 control-label" for="efectivo_cierre">Efectivo cierre</label>
                      <div class="col-md-8 input-group">
                      <input id="efectivo_cierre" type="text" class="form-control input-md " value="">
                      </div>
                   </div>
                   <div class="form-group">
                      <label class="col-md-4 control-label" for="cheque_cierre">Cheques cierre</label>
                      <div class="col-md-8 input-group">
                      <input id="cheque_cierre" type="text" class="form-control input-md " value="">
                      </div>
                   </div>
                    <div class="form-group">
                      <label class="col-md-4 control-label" for="cheque_cierre">Transferencia cierre</label>
                      <div class="col-md-8 input-group">
                      <input id="transferencia_cierre" type="text" class="form-control input-md " value="">
                      </div>
                   </div>
                   <div class="form-group">
                      <label class="col-md-4 control-label" for="motivo_diferencia">Motivo diferencia</label>
                      <div class="col-md-8 input-group">
                      <input id="motivo_diferencia" type="text" class="form-control input-md " value="">
                   </div>
                </fieldset>
                </form>
            </div>
          </div> 
        </script>
        
        <script language="">
        var  cuentas_internas = [] ;
        $(document).ready(function(){
           $('#btnAgregar').click(agregarDetalle);
           $('#btnCerrar').click(cerrarCaja);
           $('#id_forma').change(filtrar_mdl_caja);
           filtrar_mdl_caja();
           loadCuentasInternas();
        });
    function loadCuentasInternas(data){            
        $.ajax({
           url: '<%= PathCfg.CUENTA_INTERNA_LIST%>',
           data: data,
           method:"POST",
           dataType: "json",
           beforeSend:function(){
            cuentas_internas = [];
           },
           success: function(result) {
               if(result.Result === "OK") {
                   cuentas_internas = result.Records;                       
               }
           }
       });
    }
        function loadDataCajaDetalle(data){
            var $tabla = $('#tblCajaDetalle');
            $.ajax({
               url: '<%= PathCfg.CAJA_DETALLE_LIST %>',
               data: data,
               method:"POST",
               dataType: "json",
               beforeSend:function(){
                    var cant_cols = $tabla.find('thead th').length;
                    $tabla.find('tbody').html("<tr><td colspan='" + cant_cols + "'><center><img src='assets/img/ajax-loader.gif'/></center></td></tr>");
               },
               success: function(result) {
                   if(result.Result === "OK") {
                       $tabla.find('tbody').html(createTable(result.Records));                        
                   }
               }
           });
    }
   function createTable(data){
    var template = Handlebars.compile($('#CajaDetalleList').html());
    return template({saldo:0,detalles:data});
    
        var html = "";
        for(var i = 0;i< data.length;i++){
           html +="<tr class=''>";
           d = data[i];
           html += wrapTag('td',d.concepto,'');
           html += wrapTag('td',d.importe,'');
           html += wrapTag('td',d.saldo,'');
           html +="</tr>";
       }      
       return html;
    }  
    function filtrar_mdl_caja(){            
        var data = {};
        data.id_caja = $('#id_caja').val();
        data.id_forma = $('#id_forma').val();
        loadDataCajaDetalle(data);

     }
   function agregarDetalle(){
       var template = Handlebars.compile($('#agregarCajaDetalle').html());
       bootbox.dialog({
        title: "Agregar movimiento de caja",
        
        message: template({cuentas_internas:cuentas_internas}),
        buttons: {
            success: {
                label: "Guardar",
                className: "btn-success",
                callback: function () {
                    var data = getDatosDetalle();
                    $.ajax({
                        url: '<%=PathCfg.CAJA_DETALLE_EDIT%>',
                        method: "POST",
                        dataType: "json",
                        data: data,
                        success:function(result){
                            if(result.Result ==="OK"){
                                if(result.Record.id) {
                                location.href = "<%=PathCfg.RECIBO_PRINT%>?id="+ result.Record.id;
                               }
                                filtrar_mdl_caja({});
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
function getDatosDetalle(){
    var data={};
    data.id_caja   = $('#id_caja').val();
    data.importe   = parsearInt($('#importe').val());
    data.concepto  = $('#concepto').val();
    data.id_forma  = $('input[name="id_forma"]:checked').val();
    data.id_tipo   = $('input[name="id_tipo"]:checked').val();
    data.id_cuenta = $('#id_cuenta').val();
    return data;
}

function cerrarCaja(){
    calcularSaldos().then(mostrarDialogoCierre,function(message){
        bootbox.alert(message);
    });
 }
 function mostrarDialogoCierre(data){
        var template = Handlebars.compile($('#cerrarCaja').html());
        bootbox.dialog({
            title: "Cierre de caja",
            message: template(data),
            buttons: {
                success: {
                    label: "Guardar",
                    className: "btn-success",
                    callback: function () {
                        var data = getDatosCierre();
                        $.ajax({
                            url: '<%=PathCfg.CAJA_CIERRE%>',
                            method: "POST",
                            dataType: "json",
                            data: data,
                            success:function(result){
                                if(result.Result ==="OK"){
                                    window.location='<%=PathCfg.CAJA%>';
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
 function getDatosCierre(){
    var data = {};
    data.id_caja = $('#id_caja').val();
    data.saldo_efectivo       = $('#saldo_efectivo').val();
    data.saldo_cheques        = $('#saldo_cheques').val();
    data.saldo_transferencia  = $('#saldo_transferencia').val();
    data.efectivo_cierre      = $('#efectivo_cierre').val();
    data.cheque_cierre        = $('#cheque_cierre').val();
    data.transferencia_cierre = $('#transferencia_cierre').val();
    data.motivo_diferencia    = $('#motivo_diferencia').val();
    return data;
 }
function calcularSaldos(result){
    return new Promise(function(resolve,reject){
         $.ajax({url:'<%=PathCfg.CAJA_DETALLE_CIERRE%>',
            data:{id_caja : $('#id_caja').val()},
            method:'POST',
            dataType:'json',
            beforeSend:function(){},
            success:function(result){
                console.log(result);
                if(result.Result==="OK") resolve(result.Record);
                else reject(result.Message);
            },
            error: function() {reject("Ocurri&oacute; un error al cerrar la caja. Intentelo nuevamente");}
        }); 
        
    });
}
    </script>
</body>
</html>
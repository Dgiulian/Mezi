<%@page import="utils.TFecha"%>
<%@page import="transaccion.TLocalidad"%>
<%@page import="utils.PathCfg"%>
<%@page import="bd.Localidad"%>
<div class="modal fade " id="mdlPagoAdelantado">
            <div class="modal-dialog modal-lg">
            <div class="modal-content ">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="tituloServ">Liquidaci&oacute;n - Pago Adelantado</h4>
            </div>
            <div class="modal-body">
                <div class="row">
                <div class="col-md-12">
                <form class="form-vertical">
                <input id="pag_id_cuenta" name="pag_id_cuenta" type="hidden" class="" value="" >
                <div class="row">
                <div class="col-md-4">
                    <div class="form-group row">
                        <label class="col-md-4 control-label" for="pagFecha">Fecha</label>
                        <div class="input-group date date-picker">
                            <input id="pagFecha" name="pagFecha" type="text" class="form-control input-md date-input" value="<%=TFecha.ahora(TFecha.formatoVista)%>">
                            <span class="input-group-addon"><span class="fa fa-calendar"></span></span>  
                        </div>
                    </div>
                </div>
                </div>
               <div class="row">
                    <div class="col-md-4">
                         <div class="form-group row">
                             <label class="col-md-6 control-label" for="pag_mnt">Cantidad Meses</label>
                             <div class="input-group ">
                                 <input id="pag_mnt" name="pag_mnt" type="text" class="form-control input-md numeric" value="">
                                 <span id="" name="" type="button" class="btn btn-primary"  >Calcular</span>
                             </div>
                         </div>
                    </div>
               </div>
               <div class="row">
                    <div class="col-md-4">
                         <div class="form-group row">
                             <label class="col-md-6 control-label" for="pag_mnt">Saldo alquileres</label>
                             <div class="input-group ">
                                 <input id="pag_mnt" name="pag_mnt" type="text" class="form-control input-md numeric" value="" disabled>
                             </div>
                         </div>
                    </div>
               </div> 
                <div class="row">
                    <span class="btn btn-primary" id="btnConcepto" >Agregar concepto </span>
                    <table class="table table-bordered table-condensed" id="tblConcepto">
                            <colgroup>
                               <col style="width:65%">
                               <col style="width:10%">
                               <col style="width:10%">
                           </colgroup>
                           <thead>
                               <tr>
                                   <th>Concepto</th>
                                   <th>Importe</th>
                                   <th>Acci&oacute;n</th>
                               </tr>
                           </thead>                                                            
                           <tbody></tbody>
                    </table>
                </div>
                <div class="row">            
                <div class="col-md-12">
                  <table class="table table-bordered">
                     <tr>
                      <div class="form-inline">
                        <td colspan="4"><label class="control-label form-inline" for="pagEfeMnt">Efectivo</label>
                        <input id="pagEfeMnt" name="pagEfeMnt" type="text" class="form-control input-md numeric" value=""></td>
                      </div>
                    </tr>
                    <tr>
                        <td>
                            <label class="control-label" for="pagChkMnt">Cheque</label>
                            <input id="pagChkMnt" name="pagChkMnt" type="text" class="form-control input-md numeric" value="">
                        </td>
                        <td>
                            <label class="control-label" for="pagChkBan">Banco</label>
                            <input id="pagChkBan" name="pagChkBan" type="text" class="form-control input-md numeric" value="">
                        </td>
                        <td>
                            <label class="control-label" for="pagChkNum">N&uacute;mero</label>
                            <input id="pagChkNum" name="pagChkNum" type="text" class="form-control input-md numeric" value="">
                        </td>
                        <td><label class="control-label" for="pagChkVto">Vencimiento</label>
<!--                            <div class="input-group date date-picker">-->
                            <input id="pagChkVto" name="pagChkVto" type="text" class="form-control input-md date-picker" value="" >
                            <!--<span class="input-group-addon"><span class="fa fa-calendar"></span></span>-->  
                        </div>
                            
                            <!--<input id="pagChkVto" name="pagChkVto" type="text" class="form-control input-md date-picker" value="">-->
                        </td>
                    </tr>
                     <tr>
                        <td>
                            <label class="control-label" for="pagTraMnt">Tranferencia</label>
                            <input id="pagTraMnt" name="pagTraMnt" type="text" class="form-control input-md numeric" value="">
                        </td>
                        <td colspan="3">
                            <label class="control-label" for="pagTraNum">N&uacute;mero</label>
                            <input id="pagTraNum" name="pagTraNum" type="text" class="form-control input-md numeric" value="">
                        </td>
                    </tr>                    
                </table>                
                </div>
                
                    <div class="col-md-4">
                        <div class="form-group">
                            <label class="col-md-2 control-label" for="pagTotal">Total</label>
                            <div class="col-md-10">
                            <input id="pagTotal" name="pagTotal" type="text" class="form-control input-md numeric" disabled value="">
                            </div>
                        </div>
                    </div>
                  </div>  
                </form>
                </div><!-- /.modal-body -->
                </div>
                </div>
                <div class="modal-footer">                        
                        <button type="button" class="btn btn-primary" id="btnGuardarPag">Guardar</button>
                        <button type="button" class="btn btn-default" data-dismiss="modal">Cancelar</button>
                </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->

<script id="agregarConcepto" type="text/x-handlebars-template">
           <div class="row">
            <div class="col-md-12">
              <form class="form-vertical">
                <fieldset >
                    <div class="form-group">
                      <label class="col-md-4 control-label" for="saldo_efectivo">Concepto</label>
                      <div class="col-md-8 input-group">
                      <input id="txt_concepto" type="text" class="form-control input-md " value="">
                      </div>
                    </div>
                   <div class="form-group">
                      <label class="col-md-4 control-label" for="saldo_cheques">Importe</label>
                      <div class="col-md-8 input-group">
                      <input id="txt_importe" type="text" class="form-control input-md " value="">
                      </div>
                   </div>                    
                </fieldset>                
                </form>
            </div>
          </div> 
        </script>
<script>
    $(document).ready(function(){
        $('#mdlPagoAdelantado').on('shown.bs.modal',function(e){
//            var invoker = $(e.relatedTarget);
//            var id_cuenta  = invoker.data('id_cuenta');
//            var mnt_pag  = invoker.data('mnt_pag');
//            if(isNaN(parseInt(id_cuenta))) {
//                bootbox.alert("Primero debe seleccionar una cuenta");
//                $('#mdlPagoAdelantado').modal('hide');
//            }
//            mnt_pag = isNaN(mnt_pag)?0:mnt_pag;
//            
//            $('#pag_mnt').val(mnt_pag);
//            $('#pag_id_cuenta').val(id_cuenta);
//
//           // $('#pagFecha').val("");
//            $('#pagEfeMnt').val("");
//            $('#pagChkMnt').val("");
//            $('#pagChkBan').val("");
//            $('#pagChkNum').val("");
//            $('#pagChkVto').val("");
//            $('#pagTraMnt').val("");
//            $('#pagTraNum').val("");
//            $('#pagTotal').val("");
        });
       
        $('#btnGuardarPag').click(guardarPagoAdelantado);
        $('#pagEfeMnt').change(sumarPago);
        $('#pagChkMnt').change(sumarPago);
        $('#pagTraMnt').change(sumarPago);
        $('#btnConcepto').click(btnAgregarConcepto);
    });
    
    function sumarPago(){
        var pagEfeMnt = parsearFloat($('#pagEfeMnt').val());
        var pagChkMnt = parsearFloat($('#pagChkMnt').val());
        var pagTraMnt = parsearFloat($('#pagTraMnt').val());        
        var total = pagEfeMnt + pagChkMnt + pagTraMnt;
        $('#pagTotal').val(total);
    }
  
    function guardarPagoAdelantado() {
        $('#mdlPagoAdelantado').modal('hide');
            var data = getDatosPago();
            $.ajax({
                url:'<%= PathCfg.PAGO_ADELANTADO%>',
                data: data,
                method:'POST',
                dataType:'json',
                success:function(result){
                   if(result.Result==="OK"){
                    $('#mdlPagoAdelantado').modal('hide');
                    console.log(result.Record);
                    if(result.Record.id) {
                       
                    }
                   } else{
                       bootbox.alert(result.Message);
                   }
                }
            });
            //bootbox.alert("Nombre " + nombre + ". Email: <b>" + email + "</b>");
    }
    function getDatosPago(){
        var data = {};
           var id_cuenta = $('#pag_id_cuenta').val();
            var fecha     = $('#pagFecha').val();            
            var pagEfeMnt = parsearFloat($('#pagEfeMnt').val());
            var pagChkMnt = parsearFloat($('#pagChkMnt').val());
            var pagChkBan = $('#pagChkBan').val();
            var pagChkNum = $('#pagChkNum').val();
            var pagChkVto = $('#pagChkVto').val();
            var pagTraMnt = parsearFloat($('#pagTraMnt').val());
            var pagTraNum = $('#pagTraNum').val();
            
        return data;
    }
    function btnAgregarConcepto(){
        var template = Handlebars.compile($('#agregarConcepto').html());
        bootbox.dialog({
            title: "Agregar concepto",        
            message: template({}),
            buttons: {
                success: {
                    label: "Agregar",
                    className: "btn-success",
                    callback: function () {
                        var $tabla = $('#tblConcepto');
                        $tabla.find('tbody').append(htmlConcepto());
                    }
                },
                cancel: {
                    label: "Cancelar",
                    callback: function () {}
                }
            }
        });
    }
    function htmlConcepto(){
        var html = "";
        var concepto = $('#txt_concepto').val();
        var importe = $('#txt_importe').val();
        html += '<tr>';
        html += '<td>' + concepto + '</td>';
        html += '<td>' + importe  + '</td>';
        html += '<td> <span class="btn btn-xs btn-circle btn-danger "><span class="fa fa-trash-o"></span></span></td>';
        html += '</tr>';
        return html;
    }
</script>
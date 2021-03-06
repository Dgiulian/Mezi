<%@page import="utils.TFecha"%>
<%@page import="transaccion.TLocalidad"%>
<%@page import="utils.PathCfg"%>
<%@page import="bd.Localidad"%>
<div class="modal fade" id="mdlLiquidar">
            <div class="modal-dialog">
            <div class="modal-content ">
            <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                    <h4 class="modal-title" id="tituloServ">Liquidaci&oacute;n mensual</h4>
            </div>
            <div class="modal-body">
                <div class="row">
                <div class="col-md-12">
                <form class="form-vertical">
                <input id="liq_id_cuenta" name="liq_id_cuenta" type="hidden" class="" value="" >                
               <div class="col-md-7">
                    <div class="form-group row">
                        <label class="col-md-6 control-label" for="liq_mnt">Saldo a pagar</label>
                        <div class="input-group ">
                            <input id="liq_mnt" name="liq_mnt" type="text" class="form-control input-md numeric" value="" disabled>
                        </div>
                    </div>
               </div>
               <div class="col-md-7">
                    <div class="form-group row">
                        <label class="col-md-4 control-label" for="liqFecha">Fecha</label>
                        <div class="input-group date date-picker">
                            <input id="liqFecha" name="liqFecha" type="text" class="form-control input-md date-input" value="<%=TFecha.ahora(TFecha.formatoVista)%>">
                            <span class="input-group-addon"><span class="fa fa-calendar"></span></span>  
                        </div>
                    </div>
                </div>
                <div class="col-md-12">
                  <table class="table table-bordered">
                     <tr>
                      <div class="form-inline">
                        <td colspan="4"><label class="control-label form-inline" for="liqEfeMnt">Efectivo</label>
                        <input id="liqEfeMnt" name="liqEfeMnt" type="text" class="form-control input-md numeric" value=""></td>
                      </div>
                    </tr>
                    <tr>
                        <td>
                            <label class="control-label" for="liqChkMnt">Cheque</label>
                            <input id="liqChkMnt" name="liqChkMnt" type="text" class="form-control input-md numeric" value="">
                        </td>
                        <td>
                            <label class="control-label" for="liqChkBan">Banco</label>
                            <input id="liqChkBan" name="liqChkBan" type="text" class="form-control input-md numeric" value="">
                        </td>
                        <td>
                            <label class="control-label" for="liqChkNum">N&uacute;mero</label>
                            <input id="liqChkNum" name="liqChkNum" type="text" class="form-control input-md numeric" value="">
                        </td>
                        <td><label class="control-label" for="liqChkVto">Vencimiento</label>
<!--                            <div class="input-group date date-picker">-->
                            <input id="liqChkVto" name="liqChkVto" type="text" class="form-control input-md date-picker" value="" >
                            <!--<span class="input-group-addon"><span class="fa fa-calendar"></span></span>-->  
                        </div>
                            
                            <!--<input id="liqChkVto" name="liqChkVto" type="text" class="form-control input-md date-picker" value="">-->
                        </td>
                    </tr>
                     <tr>
                        <td>
                            <label class="control-label" for="liqTraMnt">Tranferencia</label>
                            <input id="liqTraMnt" name="liqTraMnt" type="text" class="form-control input-md numeric" value="">
                        </td>
                        <td colspan="3">
                            <label class="control-label" for="liqTraNum">N&uacute;mero</label>
                            <input id="liqTraNum" name="liqTraNum" type="text" class="form-control input-md numeric" value="">
                        </td>
                    </tr>                    
                </table>                
                </div>
                <div class="col-md-4">
                    <div class="form-group">
                        <label class="col-md-2 control-label" for="liqTotal">Total</label>
                        <div class="col-md-10">
                        <input id="liqTotal" name="liqTotal" type="text" class="form-control input-md numeric" disabled value="">
                        </div>
                    </div>
                </div>
                    
                </form>
                </div><!-- /.modal-body -->
                </div>
                </div>
                <div class="modal-footer">                        
                        <button type="button" class="btn btn-primary" id="btnGuardarLiq">Guardar</button>
                        <button type="button" class="btn btn-default" data-dismiss="modal">Cancelar</button>
                </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->


<script>
    $(document).ready(function(){
        $('#mdlLiquidar').on('shown.bs.modal',function(e){
            var invoker = $(e.relatedTarget);
            var id_cuenta  = invoker.data('id_cuenta');
            var mnt_liq  = invoker.data('mnt_liq');
            if(isNaN(parseInt(id_cuenta))) {
                bootbox.alert("Primero debe seleccionar una cuenta");
                $('#mdlLiquidar').modal('hide');
            }
            mnt_liq = isNaN(mnt_liq)?0:mnt_liq;
            
            $('#liq_mnt').val(mnt_liq);
            $('#liq_id_cuenta').val(id_cuenta);

           // $('#liqFecha').val("");
            $('#liqEfeMnt').val("");
            $('#liqChkMnt').val("");
            $('#liqChkBan').val("");
            $('#liqChkNum').val("");
            $('#liqChkVto').val("");
            $('#liqTraMnt').val("");
            $('#liqTraNum').val("");
            $('#liqTotal').val("");
        });
        
        $('#btnGuardarLiq').click(function(){
            guardarLiquidacion();
        });
        $('#liqEfeMnt').change(sumarTodo);
        $('#liqChkMnt').change(sumarTodo);
        $('#liqTraMnt').change(sumarTodo);
    });
    
    function sumarTodo(){
        var liqEfeMnt = parsearFloat($('#liqEfeMnt').val());
        var liqChkMnt = parsearFloat($('#liqChkMnt').val());
        var liqTraMnt = parsearFloat($('#liqTraMnt').val());        
        var total = liqEfeMnt + liqChkMnt + liqTraMnt;
        $('#liqTotal').val(total);
    }
    function guardarLiquidacion() {
        $('#mdlConcepto').modal('hide');
    
            var id_cuenta = $('#liq_id_cuenta').val();
            var fecha     = $('#liqFecha').val();            
            var liqEfeMnt = parsearFloat($('#liqEfeMnt').val());
            var liqChkMnt = parsearFloat($('#liqChkMnt').val());
            var liqChkBan = $('#liqChkBan').val();
            var liqChkNum = $('#liqChkNum').val();
            var liqChkVto = $('#liqChkVto').val();
            var liqTraMnt = parsearFloat($('#liqTraMnt').val());
            var liqTraNum = $('#liqTraNum').val();
            
            
            var data = { id_cuenta: id_cuenta,
            fecha:     fecha,
            liqEfeMnt: liqEfeMnt,
            liqChkMnt: liqChkMnt,
            liqChkBan: liqChkBan,
            liqChkNum: liqChkNum,
            liqChkVto: liqChkVto,
            liqTraMnt: liqTraMnt,
            liqTraNum: liqTraNum};
            
        
            $.ajax({
                url:'<%= PathCfg.PAGO_EDIT%>',
                data: data,
                method:'POST',
                dataType:'json',
                success:function(result){
                   if(result.Result==="OK"){
                    $('#mdlLiquidar').modal('hide');
                    if(result.Record.id) {
                        location.href = "<%=PathCfg.RECIBO_PRINT%>?id="+ result.Record.id;
                        filtrar_cuenta();
                    }
                   } else{
                       bootbox.alert(result.Message);
                   }
                }
            });
            //bootbox.alert("Nombre " + nombre + ". Email: <b>" + email + "</b>");
    }
</script>
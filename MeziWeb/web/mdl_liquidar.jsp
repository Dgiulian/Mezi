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
                    <div class="form-group">
                        <label class="col-md-4 control-label" for="liqFecha">Fecha</label>
                        <div class="col-md-8">
                            <input id="liqFecha" name="liqFecha" type="text" class="form-control input-md date-picker" value="<%=TFecha.ahora(TFecha.formatoVista)%>">
                        </div>
                    </div>
                </div>
                <div class="col-md-12">
                  <table class="table table-bordered">
                     <tr>
                      <div class="form-inline">
                        <td colspan="3"><label class="control-label form-inline" for="liqEfeMnt">Efectivo</label>
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
                    </tr>
                     <tr>
                        <td>
                            <label class="control-label" for="liqTraMnt">Tranferencia</label>
                            <input id="liqTraMnt" name="liqTraMnt" type="text" class="form-control input-md numeric" value="">
                        </td>
                        <td colspan="2">
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
            $('#liq_id_cuenta').val(id_cuenta);
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
            var liqChkBan = parsearFloat($('#liqChkBan').val());
            var liqChkNum = parsearFloat($('#liqChkNum').val());
            var liqTraMnt = parsearFloat($('#liqTraMnt').val());
            var liqTraNum = parsearFloat($('#liqTraNum').val());
            
            
            var data = { id_cuenta: id_cuenta,
            fecha:     fecha,
            liqEfeMnt: liqEfeMnt,
            liqChkMnt: liqChkMnt,
            liqChkBan: liqChkBan,
            liqChkNum: liqChkNum,
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
                        filtrar_cuenta();
                   } else{
                       bootbox.alert(result.Message);
                   }
                }
            });
            //bootbox.alert("Nombre " + nombre + ". Email: <b>" + email + "</b>");
    }
</script>
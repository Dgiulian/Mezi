<%@page import="transaccion.TLocalidad"%>
<%@page import="utils.PathCfg"%>
<%@page import="bd.Localidad"%>
<div class="modal fade" id="mdlConcepto">
            <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                        <h4 class="modal-title" id="tituloServ">Agregar concepto</h4>
                </div>
            <div class="modal-body">
                <div class="row">
                <div class="col-md-12">
                <form class="form-vertical">
                <input id="aj_id_cuenta" name="aj_id_cuenta" type="hidden" class="" value="" >                
               
                <div class="form-group">
                    <label class="col-md-4 control-label" for="ajFecha">Fecha</label>
                    <div class="col-md-8 input-group date date-picker">
                    <input id="ajFecha" name="ajFecha" type="text" class="form-control input-md " value="">
                    <span class="input-group-addon"><span class="fa fa-calendar"></span></span>  
                    </div>
                </div>
                 <div class="form-group">
                    <label class="col-md-4 control-label" for="ajConcepto">Concepto</label>
                    <div class="col-md-8 input-group">
                    <input id="ajConcepto" name="ajConcepto" type="text" class="form-control input-md" value="">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-md-4 control-label" for="ajMonto">Monto</label>
                    <div class="col-md-8 input-group">
                    <input id="ajMonto" name="ajMonto" type="text" class="form-control input-md numeric" value="">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-md-4 control-label" for="">Tipo</label>
                    <div class="col-md-8 input-group"> 
                        <input id="ajTipoDebe"  name="ajTipo" type="radio" class="radio-inline input-md" value="1">Debe                    
                        <input id="ajTipoHaber" name="ajTipo" type="radio" class="radio-inline input-md" value="2">Haber
                    </div>
                </div>
                </form>
                </div>
                </div>
                </div>
                <div class="modal-footer">                        
                        <button type="button" class="btn btn-primary" id="btnGuardar">Guardar</button>
                        <button type="button" class="btn btn-default" data-dismiss="modal">Cancelar</button>
                </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->


<script>
    $(document).ready(function(){
        $('#mdlConcepto').on('shown.bs.modal',function(e){
            var invoker = $(e.relatedTarget);
            var id_cuenta  = invoker.data('id_cuenta');
            $('#aj_id_cuenta').val(id_cuenta);            
            $('#ajFecha').val("");
            $('#ajConcepto').val("");
            $('#ajMonto').val("");                        
            var tipo = $('input[name="ajTipo"]:checked').prop('checked',false);
        });
        
        $('#btnGuardar').click(function(){
            guardarConcepto();
        });
    });
    
    function guardarConcepto() {
        $('#mdlConcepto').modal('hide');
         
        var id_cuenta = $('#aj_id_cuenta').val();
        var fecha     = $('#ajFecha').val();
        var concepto  = $('#ajConcepto').val();
        var monto = $('#ajMonto').val();                        
        var tipo = $('input[name="ajTipo"]:checked').val();
        var data = {id_cuenta: parsearInt(id_cuenta),
                   fecha: fecha,
                   concepto: concepto,
                   monto: parsearFloat(monto),
                   tipo: tipo};
       if(!validarAltaConcepto(data)) return;


        $.ajax({
            url:'<%= PathCfg.CUENTA_DET_EDIT%>',
            data: data,
            method:'POST',
            dataType:'json',
            success:function(result){
               if(result.Result==="OK"){
                $('#mdlConcepto').modal('hide');
                filtrar_cuenta();
               } else{
                   bootbox.alert(result.Message);
               }
            }
        });
    }
    
    function validarAltaConcepto(data){
        if(data.id_cuenta===0) {
            bootbox.alert("Seleccione la cuenta que desea ajustar");
            return false;
        }
        if(data.fecha==="" || !validarFecha(data.fecha)){
            bootbox.alert("Ingrese una fecha v&aacute;lida");
            return false;
        }
        if(data.concepto ==='') {
            bootbox.alert("Ingrese el concepto del ajuste");
            return false;
        }
        if(data.monto ===0) {
            bootbox.alert("Ingrese el monto del ajuste");
            return false;
        }
        if (data.tipo===undefined){
            bootbox.alert("Seleccione el tipo de ajuste");
            return false;
        }
        return true;
    }
    
</script>
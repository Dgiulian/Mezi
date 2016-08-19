<%@page import="utils.TFecha"%>
<%@page import="transaccion.TLocalidad"%>
<%@page import="utils.PathCfg"%>
<%@page import="bd.Localidad"%>
<div class="modal fade" id="mdlDocumento">
    <div class="modal-dialog">
        <div class="modal-content ">
        <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="ttlModal">Agregar documento</h4>
        </div>
        <div class="modal-body">
            <input name="target" id="target" type="hidden">
            <div class="row">   
              <div class="col-md-12">  
               <div class="form-group">  
                  <label class="col-md-4 control-label" for="documento_fecha_inicio">Desde</label>  
                  <div class="col-md-8 input-group date date-picker">  
                  <input id="documento_fecha_inicio" type="text" class="form-control input-md date-input" value="">  
                  <span class="input-group-addon"><span class="fa fa-calendar"></span></span>  
                  </div> 
               </div> 
               <div class="form-group">  
                  <label class="col-md-4 control-label" for="documento_meses">Meses</label>  
                  <div class="col-md-8 input-group">  
                  <input id="documento_meses" type="text" class="form-control input-md " value="">  
                  </div> 
               </div> 
               <div class="form-group">  
                  <label class="col-md-4 control-label" for="documento_fecha_fin">Hasta</label>  
                  <div class="col-md-8 input-group date date-picker">  
                  <input id="documento_fecha_fin" data-date-format="dd/mm/yyyy" type="text" class="form-control input-md date-input" value="">  
                  <span class="input-group-addon"><span class="fa fa-calendar"></span></span>  
                  </div> 
               </div> 

              <div class="form-group">  
                  <label class="col-md-4 control-label" for="documento_importe">Importe</label> 
                  <div class="col-md-8 input-group">  
                  <input id="documento_importe" type="text" class="form-control input-md" value="">  
                  </div> 
                  </div>  
              </div>


              </div>
            </div>
            <div class="modal-footer">                        
                    <button type="button" class="btn btn-primary" id="btnGuardarDocumento">Guardar</button>
                    <button type="button" class="btn btn-default" data-dismiss="modal">Cancelar</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->


<script>
    $(document).ready(function(){
        $('#mdlDocumento').on('shown.bs.modal',function(e){
            var invoker = $(e.relatedTarget);
            $('#documento_fecha_inicio').val("");
            $('#documento_fecha_fin').val("");
            $('#documento_importe').val("");
            $('#documento_meses').val(""); 
        });
         $('#documento_meses').focusout(function(){
            var fecha_inicio = $('#documento_fecha_inicio').val();
            var meses = $('#documento_meses').val();
            $('#documento_fecha_fin').val(calcularHasta(fecha_inicio,meses));
            $('#documento_fecha_fin').datepicker('setDate',$('#documento_fecha_fin').val());
        });
        $('#btnGuardarDocumento').click(function(){
            var fecha_desde = $('#documento_fecha_inicio').val();
            var fecha_hasta = $('#documento_fecha_fin').val();
            var monto       = $('#documento_importe').val();
            var target      = "Documento";//$('#target').val();
            if(fecha_desde==="" || fecha_hasta ==="") {
                bootbox.alert("Debe ingresar la fecha desde y hasta");
                return;
            }
            if (!validarAnterior(fecha_desde,fecha_hasta)){
                bootbox.alert("La fecha desde debe ser anterior a la fecha hasta");
                return;
            }
            agregarMonto({
                target: target,
                fecha_desde: fecha_desde,
                fecha_hasta: fecha_hasta,
                monto: monto
            });
            $('#mdlDocumento').modal('hide');
        });        
    });    
</script>
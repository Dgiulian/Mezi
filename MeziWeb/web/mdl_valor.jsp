<%@page import="utils.TFecha"%>
<%@page import="transaccion.TLocalidad"%>
<%@page import="utils.PathCfg"%>
<%@page import="bd.Localidad"%>
<div class="modal fade" id="mdlValor">
    <div class="modal-dialog">
        <div class="modal-content ">
        <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="ttlModal">Agregar valor</h4>
        </div>
        <div class="modal-body">
            <input name="target" id="target" type="hidden">
            <div class="row">   
              <div class="col-md-12">  
               <div class="form-group">  
                  <label class="col-md-4 control-label" for="valor_fecha_inicio">Desde</label>  
                  <div class="col-md-8 input-group date date-picker">  
                  <input id="valor_fecha_inicio" type="text" class="form-control input-md date-input" value="">  
                  <span class="input-group-addon"><span class="fa fa-calendar"></span></span>  
                  </div> 
               </div> 
               <div class="form-group">  
                  <label class="col-md-4 control-label" for="valor_meses">Meses</label>  
                  <div class="col-md-8 input-group">  
                  <input id="valor_meses" type="text" class="form-control input-md " value="">  
                  </div> 
               </div> 
               <div class="form-group">  
                  <label class="col-md-4 control-label" for="valor_fecha_fin">Hasta</label>  
                  <div class="col-md-8 input-group date date-picker">  
                  <input id="valor_fecha_fin" data-date-format="dd/mm/yyyy" type="text" class="form-control input-md date-input" value="">  
                  <span class="input-group-addon"><span class="fa fa-calendar"></span></span>  
                  </div> 
               </div> 

              <div class="form-group">  
                  <label class="col-md-4 control-label" for="valor_importe">Importe</label> 
                  <div class="col-md-8 input-group">  
                  <input id="valor_importe" type="text" class="form-control input-md" value="">  
                  </div> 
                  </div>  
              </div>


              </div>
            </div>
            <div class="modal-footer">                        
                    <button type="button" class="btn btn-primary" id="btnGuardarValor">Guardar</button>
                    <button type="button" class="btn btn-default" data-dismiss="modal">Cancelar</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->


<script>
    $(document).ready(function(){
        $('#mdlValor').on('shown.bs.modal',function(e){
            var invoker = $(e.relatedTarget);            
            //$('#ttlModal').text("Agregar " + $('#target').val());
            $('#valor_fecha_inicio').val("");
            $('#valor_fecha_fin').val("");
            $('#valor_importe').val("");
            $('#valor_meses').val(""); 
        });
         $('#valor_meses').focusout(function(){
            var fecha_inicio = $('#valor_fecha_inicio').val();
            var meses = $('#valor_meses').val();
            $('#valor_fecha_fin').val(calcularHasta(fecha_inicio,meses));
            $('#valor_fecha_fin').datepicker('setDate',$('#valor_fecha_fin').val());
        });
        $('#btnGuardarValor').click(function(){
            var fecha_desde = $('#valor_fecha_inicio').val();
            var fecha_hasta = $('#valor_fecha_fin').val();
            var monto       = $('#valor_importe').val();
            var target      = "Valor"; //$('#target').val();
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
            $('#mdlValor').modal('hide');
        });        
    });    
</script>
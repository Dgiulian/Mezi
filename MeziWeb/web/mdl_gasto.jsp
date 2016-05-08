<%@page import="utils.TFecha"%>
<%@page import="transaccion.TLocalidad"%>
<%@page import="utils.PathCfg"%>
<%@page import="bd.Localidad"%>
<div class="modal fade" id="mdlGasto">
    <div class="modal-dialog">
        <div class="modal-content ">
        <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="ttlModal">Agregar gasto</h4>
        </div>
        <div class="modal-body">
            <div class="row">   
                <div class="col-md-12">  

                     <div class="form-group">  
                        <label class="col-md-4 control-label" for="gasto_concepto">Concepto</label>  
                        <div class="col-md-8">  
                        <input id="gasto_concepto" type="text" class="form-control input-md" value="">  
                     </div> 
                     <div class="form-group">  
                        <label class="col-md-4 control-label" for="gasto_aplica">Aplica</label>  
                        <div class="col-md-8">  
                        <select id="gasto_concepto" type="text" class="form-control input-md">  
                        <option value="1">Inquilino</option> 
                        <option value="2">Propietario</option> 
                        </select> 
                     </div> 
                      <div class="form-group">  
                        <label class="col-md-4 control-label" for="gasto_cuota">Cuotas</label> 
                        <div class="col-md-8">  
                        <input id="gasto_cuota"  type="text" class="form-control input-md" value="">  
                        </div>  
                    </div>
                    <div class="form-group">  
                        <label class="col-md-4 control-label" for="gasto_importe">Importe</label> 
                        <div class="col-md-8">  
                        <input id="gasto_importe" type="text" class="form-control input-md" value="">  
                        </div>  
                    </div>

                      </div>          
                </div>
            </div>
            </div>
            <div class="modal-footer">                        
                    <button type="button" class="btn btn-primary" id="btnGuardarGasto">Guardar</button>
                    <button type="button" class="btn btn-default" data-dismiss="modal">Cancelar</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->


<script>
    $(document).ready(function(){
        $('#mdlValor').on('shown.bs.modal',function(e){
            var invoker = $(e.relatedTarget);           
             
        });
       
        $('#btnGuardarGasto').click(function(){
            
        });        
    });    
</script>
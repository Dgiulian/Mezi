<%@page import="transaccion.TLocalidad"%>
<%@page import="utils.PathCfg"%>
<%@page import="bd.Localidad"%>
<div class="modal fade" id="mdlCuenta_interna">
            <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                        <h4 class="modal-title" id="tituloCuenta_interna">Editar cuenta_interna</h4>
                </div>
            <div class="modal-body">
                <div class="row">
                <div class="col-md-12">
                <form class="form-vertical">
                <input id="id" name="id" type="hidden" class="" value="" >
                <div class="form-group">
                    <label class="col-md-4 control-label" for="nombre">Nombre</label>
                    <div class="col-md-8">
                    <input id="nombre" name="nombre" type="text" class="form-control input-md" value="">
                    </div>
                </div>
                 <div class="form-group">
                    <label class="col-md-4 control-label" for="nombre">Tipo</label>
                    <div class="col-md-8">
                        <select id="id_tipo" name="id_tipo"  class="form-control input-md" >
                            <option value="1" disabled>Vendedor</option>
                            <option value="2">Otros</option>
                        </select>
                    </div>
                </div>
                
                <div class="form-group">
                    <label class="col-md-4 control-label" for="id_estado">Activo</label>
                    <div class="col-md-8"> 
                    <input id="id_estado" name="id_estado" type="checkbox" class="checkbox input-md" value="">
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
        
        $('#btnGuardar').click(function(){
            guardarCuenta_interna();
        });
    });
    
    function guardarCuenta_interna() {
            var id      = $('#id').val();            
            var nombre = $('#nombre').val();            
            var id_tipo = $('#id_tipo').val();            
            var id_estado = $('#id_estado').prop('checked')?'1':'';

            $.ajax({
                url:'<%= PathCfg.CUENTA_INTERNA_EDIT%>',
                    data: {id:id,
                            nombre: nombre,
                            id_tipo:id_tipo,
                            id_estado:id_estado},
                method:'POST',
                dataType:'json',
                success:function(){
                    $('#mdlCuenta_interna').modal('hide');
                    loadData({});
                }
                });
            //bootbox.alert("Nombre " + nombre + ". Email: <b>" + email + "</b>");
    }
    function editarCuenta_interna(){
        var index  = $(this).data('index');     
        var nombre = $(this).data('nombre');
        var id_tipo = $(this).data('id_tipo');
        var id_estado = $(this).data('id_estado');        
        agregarCuenta_interna({id:index,nombre: nombre,id_tipo:id_tipo,id_estado:id_estado});
    }
    
    function agregarCuenta_interna(data){
        console.log(data);
        $('#tituloCuenta_interna').text(data.id==0?"Nueva cuenta_interna":"Editar cuenta_interna");            
        $('#id').val(data.id);
        $('#nombre').val(data.nombre);
        $('#id_tipo').val(data.id_tipo);        
        $('#id_estado').prop('checked',data.id_estado);        
        var checked = (data.id_estado)?"checked":"";        
        $('#mdlCuenta_interna').modal('show');
    }
</script>
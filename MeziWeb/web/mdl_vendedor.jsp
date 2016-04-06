<%@page import="transaccion.TLocalidad"%>
<%@page import="utils.PathCfg"%>
<%@page import="bd.Localidad"%>
<div class="modal fade" id="mdlVendedor">
            <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                        <h4 class="modal-title" id="tituloVendedor">Editar vendedor</h4>
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
                    <label class="col-md-4 control-label" for="apellido">Apellido</label>
                    <div class="col-md-8">
                    <input id="apellido" name="apellido" type="text" class="form-control input-md" value="">
                    </div>
                </div>
                
                <div class="form-group">
                    <label class="col-md-4 control-label" for="activo">Activo</label>
                    <div class="col-md-8"> 
                    <input id="activo" name="activo" type="checkbox" class="checkbox input-md" value="">
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
            guardarVendedor();
        });
    });
    
    function guardarVendedor() {
            var id      = $('#id').val();            
            var nombre = $('#nombre').val();
            var apellido = $('#apellido').val();            
            var activo = $('#activo').prop('checked')?'1':'';

            $.ajax({
                url:'<%= PathCfg.VENDEDOR_EDIT%>',
                    data: {id:id,
                            nombre: nombre,
                            apellido: apellido,
                            activo:activo},
                method:'POST',
                dataType:'json',
                success:function(){
                    $('#mdlVendedor').modal('hide');
                    loadData({});
                }
                });
            //bootbox.alert("Nombre " + nombre + ". Email: <b>" + email + "</b>");
    }
    function editarVendedor(){
        var index  = $(this).data('index');     
        var nombre = $(this).data('nombre');
        var apellido = $(this).data('apellido');
        var activo = $(this).data('activo');        
        agregarVendedor({id:index,nombre: nombre,apellido:apellido,activo:activo});
    }
    
    function agregarVendedor(data){
        console.log(data);
        $('#tituloVendedor').text(data.id==0?"Nuevo vendedor":"Editar vendedor");            
        $('#id').val(data.id);
        $('#nombre').val(data.nombre);
        $('#apellido').val(data.apellido);        
        $('#activo').prop('checked',data.activo);        
        var checked = (data.activo)?"checked":"";        
        $('#mdlVendedor').modal('show');
    }
</script>
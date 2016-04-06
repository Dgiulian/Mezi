<%@page import="transaccion.TLocalidad"%>
<%@page import="utils.PathCfg"%>
<%@page import="bd.Localidad"%>
<div class="modal fade" id="mdlBarrio">
            <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                        <h4 class="modal-title" id="tituloServ">Editar barrio</h4>
                </div>
            <div class="modal-body">
                <div class="row">
                <div class="col-md-12">
                <form class="form-vertical">
                <input id="id" name="id" type="hidden" class="" value="" >                
                <div class="form-group">
                    <label class="col-md-4 control-label" for="id_localidad">Localidad:</label>
                     <div class="col-md-8">
                    <select id="id_localidad" name="id_localidad" class="form-control input-md" >
                    <option value=""></option>
                    <% for(Localidad t: new TLocalidad().getList()) {%>
                        <option value="<%=t.getId()%>"><%=t.getDescripcion()%></option>
                    <% } %>
                    </select>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-md-4 control-label" for="nombre">Nombre</label>
                    <div class="col-md-8">
                    <input id="nombre" name="nombre" type="text" class="form-control input-md" value="">
                    </div>
                </div>
                 <div class="form-group">
                    <label class="col-md-4 control-label" for="nombre_municipal">Nombre municipal</label>
                    <div class="col-md-8">
                    <input id="nombre_municipal" name="nombre_municipal" type="text" class="form-control input-md" value="">
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
            guardarBarrio();
        });
    });
    
    function guardarBarrio() {
            var id      = $('#id').val();
            var id_localidad = $('#id_localidad').val();
            var nombre = $('#nombre').val();
            var nombre_municipal = $('#nombre_municipal').val();            
            var activo = $('#activo').prop('checked')?'1':'';

            $.ajax({
                url:'<%= PathCfg.BARRIO_EDIT%>',
                data: {id:id,
                       id_localidad:id_localidad,
                       nombre: nombre,
                       nombre_municipal: nombre_municipal,
                       activo:activo},
                method:'POST',
                dataType:'json',
                success:function(){
                    $('#mdlBarrio').modal('hide');
//                    loadData({});
                    loadData({'id_localidad':id_localidad});
                }
                });
            //bootbox.alert("Nombre " + nombre + ". Email: <b>" + email + "</b>");
    }
    function editarBarrio(){
        var index  = $(this).data('index');     
        var id_localidad = $(this).data('id_localidad');
        var nombre = $(this).data('nombre');
        var nombre_municipal = $(this).data('nombre_municipal');
        var activo = $(this).data('activo');        
        agregarBarrio({id:index,id_localidad: id_localidad,nombre: nombre,nombre_municipal:nombre_municipal,activo:activo});
    }
    
    function agregarBarrio(data){
        console.log(data);
        $('#tituloServ').text(data.id==0?"Nuevo barrio":"Editar barrio");            
        $('#id').val(data.id);
        $('#id_localidad').val(data.id_localidad);
        $('#nombre').val(data.nombre);
        $('#nombre_municipal').val(data.nombre_municipal);        
        $('#activo').prop('checked',data.activo);        
        var checked = (data.activo)?"checked":"";        
        $('#mdlBarrio').modal('show');
    }
</script>
<%@page import="java.util.List"%>
<%@page import="transaccion.TProvincia"%>
<%@page import="utils.PathCfg"%>
<%@page import="bd.Provincia"%>

<div class="modal fade" id="mdlLocalidad">
            <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                        <h4 class="modal-title" id="tituloProvincia">Editar localidad</h4>
                </div>
            <div class="modal-body">
                <div class="row">
                <div class="col-md-12">
                <form class="form-vertical">
                <input id="id" name="id" type="hidden" class="" value="" >                
                <div class="form-group">
                    <label class="col-md-4 control-label" for="id_provincia">Provincia:</label>
                     <div class="col-md-8">
                    <select id="id_provincia" name="id_provincia" class="form-control input-md" >
                    <option value=""></option>
                    <% for(Provincia p: new TProvincia().getList()) {%>
                        <option value="<%=p.getId()%>"><%=p.getDescripcion()%></option>
                    <% } %>
                    </select>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-md-4 control-label" for="descripcion">Nombre:</label>
                    <div class="col-md-8">
                    <input id="descripcion" name="descripcion" type="text" class="form-control input-md" value="">
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
            guardarLocalidad();
        });
    });
    
    function guardarLocalidad() {
            var id      = $('#id').val();
            var id_provincia = $('#id_provincia').val();
            var descripcion = $('#descripcion').val();
            var activo = $('#activo').prop('checked')?'1':'';

            $.ajax({
                url:'<%= PathCfg.LOCALIDAD_EDIT%>',
                data: {id:id,
                       id_provincia:id_provincia,
                       descripcion: descripcion,
                       activo:activo},
                method:'POST',
                dataType:'json',
                success:function(){
                    $('#mdlLocalidad').modal('hide');
                    loadData({});
                }
                });
            //bootbox.alert("Nombre " + descripcion + ". Email: <b>" + email + "</b>");
    }
    function editarLocalidad(){
        var index  = $(this).data('index');     
        var id_provincia = $(this).data('id_provincia');
        var descripcion = $(this).data('descripcion');
        var activo = $(this).data('activo');        
        console.log(index,id_provincia,descripcion,activo);
        
        agregarLocalidad({id:index,id_provincia: id_provincia,descripcion: descripcion,activo:activo});
    }
    
    function agregarLocalidad(data){
        console.log(data);
        $('#tituloServ').text(data.id==0?"Nuevo provincia":"Editar provincia");            
        $('#id').val(data.id);
        $('#id_provincia').val(data.id_provincia);
        $('#descripcion').val(data.descripcion);
        $('#activo').prop('checked',data.activo);        
        var checked = (data.activo)?"checked":"";        
        $('#mdlLocalidad').modal('show');
    }
</script>
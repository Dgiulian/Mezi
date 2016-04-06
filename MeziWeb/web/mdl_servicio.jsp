5<%@page import="utils.PathCfg"%>
<%@page import="bd.Tipo_servicio"%>
<%@page import="transaccion.TTipo_servicio"%>
<% 
    String id_propiedad = (String) request.getParameter("id_propiedad");
%>
<div class="modal fade" id="mdlServicio">
            <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                        <h4 class="modal-title" id="tituloServ">Editar servicio</h4>
                </div>
            <div class="modal-body">
                <div class="row">
                <div class="col-md-12">
                <form class="form-vertical">
                <input id="id" name="id" type="hidden" class="" value="" >
                <input id="id_propiedad" name="id_propiedad" type="hidden" value="<%=id_propiedad%>" >
                <div class="form-group">
                    <label class="col-md-4 control-label" for="id_tipo_servicio">Tipo:</label>
                     <div class="col-md-8">
                    <select id="id_tipo_servicio" name="id_tipo_servicio" class="form-control input-md" >
                    
                    <% for(Tipo_servicio t: new TTipo_servicio().getList()) {%>
                        <option value="<%=t.getId()%>"><%=t.getNombre()%></option>
                    <% } %>
                    </select>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-md-4 control-label" for="identificacion">Identificaci&oacute;n:</label>
                    <div class="col-md-8">
                    <input id="identificacion" name="identificacion" type="text" class="form-control input-md" value="">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-md-4 control-label" for="id_envio">Env&iacute;o:</label>
                    <div class="col-md-8">
                    <select id="id_envio" name="id_envio" class="form-control input-md" >
                    <option value="0"></option>
                    <option value="1">Propietario</option>
                    <option value="2">Oficina</option>
                    <option value="3">Inquilino</option>
                    </select>
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
            guardarServicio();
        });
    });
    
    function guardarServicio() {
            var id      = $('#id').val();
            var id_tipo_servicio = $('#id_tipo_servicio').val();
            var id_propiedad = $('#id_propiedad').val();
            var identificacion = $('#identificacion').val();
            var id_envio = $('#id_envio').val();
            var activo = $('#activo').prop('checked')?'1':'';

            $.ajax({
                url:'<%= PathCfg.SERVICIO_EDIT%>',
                data: {id:id,
                       id_propiedad:id_propiedad,
                       id_tipo_servicio:id_tipo_servicio,
                       identificacion:identificacion,
                       id_envio:id_envio,
                       activo:activo},
                method:'POST',
                dataType:'json',
                success:function(){
                    $('#mdlServicio').modal('hide');
                    loadData({id_propiedad:id_propiedad});
                }
                });
            //bootbox.alert("Nombre " + nombre + ". Email: <b>" + email + "</b>");
    }
    function editarServicio(){
        var index  = $(this).data('index');     
        var id_tipo_servicio = $(this).data('id_tipo_servicio');
        var identificacion = $(this).data('identificacion');
        var id_envio = $(this).data('id_envio');
        var activo = $(this).data('activo');        
        agregarServicio({id:index,id_tipo_servicio:id_tipo_servicio,id_propiedad:id_propiedad,identificacion:identificacion,id_envio:id_envio,activo:activo});
    }
    function agregarServicio(data){
        $('#tituloServ').text(data.id==0?"Nuevo servicio":"Editar servicio");            
        $('#id').val(data.id);
        $('#id_tipo_servicio').val(data.id_tipo_servicio);
        $('#identificacion').val(data.identificacion);
        $('#id_envio').val(data.id_envio);
        $('#activo').prop('checked',data.activo);
        
        var checked = (data.activo)?"checked":"";
        var title = data.id===0?"Nuevo servicio":"Editar servicio";
        $('#mdlServicio').modal('show');
    }
</script>

<%@page import="utils.PathCfg"%>
<div class="modal fade" id="mdlCliente">
    <div class="modal-dialog">
        <div class="modal-content">
                <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                        <h4 class="modal-title" id="mdlTitle">Buscar cliente</h4>
                </div>
                <div class="modal-body">
                    <div class="col-lg-4">
                        <div clas="form-group row">
                            <label class="control-label" for="nombre_search">Nombre</label>
                            <div class="control-group">                                                                                            
                                <input type="text" class="form-control" name="nombre_search" id="nombre_search" size="20" value="">
                                <!--<span class="input-group-addon" id="btnBuscar" ><span class="fa fa-search fa-fw"></span></span>-->
                            </div>
                        </div>
                       
                    </div>
                    <div class="col-lg-4">
                     <div clas="form-group row">
                            <label for="apellido_search">Apellido</label>
                            <div class="input-group">                                                                                            
                                <input type="text" class="form-control" name="apellido_search" id="apellido_search" size="20" value="">
                                <!--<span class="input-group-addon" id="btnBuscar" ><span class="fa fa-search fa-fw"></span></span>-->
                            </div>
                        </div>
                    </div>
                    <div class="col-lg-4">
                        <div clas="form-group row">
                            <label for="dni_search">Dni</label>
                            <div class="controls">                                                      
                                <div class="input-group">                                                                                            
                                    <input type="text" class="form-control" name="dni_search" id="dni_search" size="20" value="">
                                    <!--<span class="input-group-addon" id="btnBuscar" ><span class="fa fa-search fa-fw"></span></span>-->
                                </div>
                            </div>
                        </div>
                    </div>
                    
                    <table class="table table-striped table-condensed" id="tblCliente">
                        <thead>
                            <tr>
                                <th>Carpeta</th>
                                <th>Apellido y Nombre</th>
                                <th>Documento</th>
                                <th></th>
                            </tr>
                        </thead>
                        <tbody></tbody>
                    </table>
                </div>
                <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal" id="btnCancelar">Cancelar</button>
                        <!--<button type="button" class="btn btn-primary">Guardar</button>-->
                </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->

<script>
    $(document).ready(function(){
       $('#mdlCliente').on('show.bs.modal',function(){
           loadDataCliente({});
           var $id_tipo_cliente=$('#id_tipo_cliente').val();
           var titulo = "Buscar cliente";
           if($id_tipo_cliente==1) titulo = "Buscar propietario" ;
           if($id_tipo_cliente==2) titulo = "Buscar Inquilino";
           
           $('#mdlTitle').text(titulo);
       });
       $('#btnCancelar').click(function(){
            $('#mdlCliente').modal('hide');
            completarCliente({id:0,nombre:'',apellido:''});
       });
       $('#nombre_search').change(filtrar_mdl_cliente);
       $('#apellido_search').change(filtrar_mdl_cliente);
       $('#dni_search').change(filtrar_mdl_cliente);
    });
    function filtrar_mdl_cliente(){            
           var nombre_search = $('#nombre_search').val();
           var apellido_search = $('#apellido_search').val();
           var dni_search = $('#dni_search').val();
           
           loadDataCliente({
               nombre: nombre_search,           
               apellido: apellido_search,
               dni: dni_search  ,
           });
           
        }
    function loadDataCliente(data){
            var $tabla = $('#tblCliente');
            //$tabla.DataTable().destroy();
            var $id_tipo_cliente=$('#id_tipo_cliente').val();
            $.ajax({
               url: '<%= PathCfg.CLIENTE_LIST %>?id_tipo_cliente=' + $id_tipo_cliente,
               data: data,
               method:"POST",
               dataType: "json",
               beforeSend:function(){
                    var cant_cols = $tabla.find('thead th').length;
                    $tabla.find('tbody').html("<tr><td colspan='" + cant_cols + "'><center><img src='assets/img/ajax-loader.gif'/></center></td></tr>");
               },
               success: function(result) {
                   if(result.Result === "OK") {
                       $tabla.find('tbody').html(createTableCliente(result.Records));
                       $('.btnSelCliente').click(function(){
                           var id = $(this).data('index');
                           buscarCliente({id:id});
//                           var nombre = $(this).data('nombre');
//                           var apellido = $(this).data('apellido');
//                           completarCliente({'id':id,'nombre':nombre,'apellido':apellido});
                           $('#mdlCliente').modal('hide');
                       });
                   }
               }
           });
    }
    function buscarCliente(data){
        $.ajax({url: '<%=PathCfg.CLIENTE_SEARCH%>',
                data: data,
                method: 'POST',
                dataType: 'json',
                beforeSend:function(){
                    completarCliente({id_inquilino: '',
                                      nombre: '',
                                      apellido: '',
                                      dni: '',
                                      cuil: '',
                                      id_tipo_persona: ''});
                },
                success:function(result){
                    console.log(result);
                    if(result.Result==='OK'){
                        completarCliente(result.Record);
                    } else {
                        //bootbox.alert(result.Message);
                    }
                }
    });
}
   function createTableCliente(data){
   
    var html = "";
    for(var i = 0;i< data.length;i++){
       html +="<tr class=''>";
       d = data[i];

       html += wrapTag('td',d.id,'');
       html += wrapTag('td',d.nombre + ", " + d.apellido,'');
       html += wrapTag('td',d.dni,'');

        var htmlSel = "<span href='' data-index='"+ d.id + "' data-nombre='"+ d.nombre + "' data-apellido='"+ d.apellido + "'  class='btn btn-xs btn-primary btn-circle btnSelCliente'><span class='fa fa-plus'></span></span>";
        html +='<td style="width:75px"  >' + htmlSel + '</td>';
//            html +=wrapTag('td',htmlEdit + htmlDel,'');
       html +="</tr>";
   }      
   return html;
}

</script>
    

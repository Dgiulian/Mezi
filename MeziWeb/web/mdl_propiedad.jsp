
<%@page import="utils.PathCfg"%>
<div class="modal fade" id="mdlPropiedad">
    <div class="modal-dialog">
        <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                    <h4 class="modal-title">Buscar propiedad</h4>
                </div>
                <div class="modal-body">
                    <div class="row">
<!--                      <div class="col-lg-6">
                            <div clas="form-group">
                                <label for="id">Propietario</label>
                                <span class="input-group">                                                                                            
                                    <input type="text" class="form-control uppercase" name="propietario" id="propietario" size="20" value="">
                                    <input type="hidden" class="form-control uppercase" name="id_propietario" id="id_propietario" size="20" value="">
                                    <span class="input-group-addon" id="btnBuscarCliente" ><span class="fa fa-search fa-fw"></span></span>
                                </span>
                            </div>
                        </div>-->
                        <div class="col-lg-6">
                            <div clas="form-group">
                                <label for="calle_search">Calle</label>
                                <span class="input-group">                                                                                            
                                    <input type="text" class="form-control uppercase" name="calle_search" id="calle_search" size="20" value="">
                                    <span class="input-group-addon" id="btnFiltrarCalle" ><span class="fa fa-search fa-fw"></span></span>
                                </span>
                            </div>
                        </div>
                    </div>
                    <table class="table table-striped table-condensed" id="tblPropiedad">
                        <thead>
                            <tr>                                
                                <th>Tipo</th>
                                <th>Direcci&oacute;n</th>
                                <th>Barrio</th>
                                <th></th>
                            </tr>
                        </thead>
                        <tbody></tbody>
                    </table>
                </div>
                <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">Cancelar</button>
                        <!--<button type="button" class="btn btn-primary">Guardar</button>-->
                </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->

<script>
    $(document).ready(function(){
       $('#btnFiltrarCalle').click(filtrarPropiedad);
       $('#mdlPropietario').on('hide.bs.modal',filtrarPropiedad);
       $('#mdlPropiedad').on('show.bs.modal',function(){
        //   loadDataPropiedad({});
       }) 
    });
    function filtrarPropiedad(){
        var data = {};
        data.id_propietario = $('#id_propietario').val();
        data.calle = $('#calle_search').val();
//        console.log(data);
        loadDataPropiedad(data);
    }
    function loadDataPropiedad(data){
            var $tabla = $('#tblPropiedad');
            $.ajax({
               url: '<%= PathCfg.PROPIEDAD_LIST %>',
               data: data,
               method:"POST",
               dataType: "json",
               beforeSend:function(){
                    var cant_cols = $tabla.find('thead th').length;
                    $tabla.find('tbody').html("<tr><td colspan='" + cant_cols + "'><center><img src='assets/img/ajax-loader.gif'/></center></td></tr>");
               },
               success: function(result) {
               
                   if(result.Result === "OK") {
                       $tabla.find('tbody').html(createTablePropiedad(result.Records));
                       $('.btnSelPropiedad').click(function(){
                           var id = $(this).data('index');
//                           var nombre = $(this).data('nombre');
//                           var apellido = $(this).data('apellido');
//                           completarPropiedad({'id':id,'nombre':nombre,'apellido':apellido});
                            buscarPropiedad({id:id});
                           $('#mdlPropiedad').modal('hide');
                       });
                   }
               }
           });
    }
   function createTablePropiedad(data){
   
    var html = "";
    for(var i = 0;i< data.length;i++){
       html +="<tr class=''>";
       var d = data[i];
       html += wrapTag('td',d.tipo_inmueble,'');
       var direccion = d.calle+ " " + d.numero;
       if (d.piso) direccion += " " + d.piso + d.dpto;
       
       html += wrapTag('td',direccion,'');
       html += wrapTag('td',d.barrio,'');

        var htmlSel = "<span href='' data-index='"+ d.id + "' class='btn btn-xs btn-primary btn-circle btnSelPropiedad'><span class='fa fa-plus'></span></span>";
        html +='<td style="width:75px"  >' + htmlSel + '</td>';
//            html +=wrapTag('td',htmlEdit + htmlDel,'');
       html +="</tr>";
   }      
   return html;
}
 function buscarPropiedad(data){
        $.ajax({url: '<%=PathCfg.PROPIEDAD_SEARCH%>',
                data: data,
                method: 'POST',
                dataType: 'json',
                beforeSend:function(){                    
                    completarPropiedad({id_inquilino: '',
                                      nombre: '',
                                      apellido: '',
                                      dni: '',
                                      cuil: '',
                                      id_tipo_persona: ''});
                },
                success:function(result){
                    console.log(result);
                    if(result.Result==='OK'){
                        completarPropiedad(result.Record);
                    } else {
                        //bootbox.alert(result.Message);
                    }
                }
    });
}
</script>
    

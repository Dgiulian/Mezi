<%@page import="bd.Tipo_usuario"%>
<%@page import="transaccion.TTipo_usuario"%>
<%@page import="bd.Usuario"%>
<%--<%@page import="transaccion.TLocalidad"%>
<%@page import="bd.Localidad"%>
<%@page import="bd.Provincia"%>
<%@page import="transaccion.TProvincia"%>--%>
<%@page import="java.util.List"%>
<%@page import="org.apache.commons.lang3.StringEscapeUtils" %>
<%
    Usuario usuario = (Usuario) request.getAttribute("usuario");
    boolean nuevo = false;
    if (usuario==null) {
        usuario = new Usuario();
        nuevo = true;
    }
    TTipo_usuario t = new TTipo_usuario();
    List<Tipo_usuario> lstTipoUsuario = t.getList();
%>
<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title><%= PathCfg.PAGE_TITLE %></title>
     <link rel="shortcut icon" href="favicon.ico" type="image/x-icon">
    <!-- Bootstrap Core CSS -->
    <link href="bower_components/bootstrap/dist/css/bootstrap.min.css" rel="stylesheet">

    <!-- MetisMenu CSS -->
    <link href="bower_components/metisMenu/dist/metisMenu.min.css" rel="stylesheet">

    <!-- Custom CSS -->
    <link href="dist/css/sb-admin-2.css" rel="stylesheet">

    <!-- Custom Fonts -->
    <link href="bower_components/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

</head>

<body>
    <div id="wrapper">
      <%@include file="tpl_navbar.jsp" %>

        <div id="page-wrapper">
            <div class="row">
                <div class="col-lg-12">
                    <h1 class="page-header"><% if(nuevo) {%>Nuevo<%}else{%>Editar<%}%> Usuario</h1>                    
                    <% if (!nuevo) {%>                     
                    <div class="button-bar" >                        
                    </div>
                    <% } %>
                </div>
                
                <!-- /.col-lg-12 -->
            </div>
            <!-- /.row -->
            <div class="row">
                <%
                String action = PathCfg.USUARIO_EDIT;
                action += (!nuevo)?"?id="+usuario.getId():"";
                %>
                <form role="form" method="POST" action="<%=action%>" >
                
                    <% if(!nuevo) { %>
                    <input type="hidden" name="id" id ="id" value="<%= usuario.getId() %>">
                    <%}%>
                <div class="col-lg-12">
                    <div class="panel panel-default">
<!--                      <div class="panel-heading">
                            Datos b&aacute;sicos del usuario
                        </div>-->
                        <ul id="tabs" class="nav nav-tabs" data-tabs="tabs">
                           <li class="active"><a href="#tab1" data-toggle="tab">Datos b&aacute;sicos</a></li>
<!--                           <li><a href="#tab2" data-toggle="tab">Domicilio</a></li>
                           <li><a href="#tab3" data-toggle="tab">Contacto</a></li>
                           <li><a href="#tab4" data-toggle="tab">Datos financieros</a></li>
                           <li><a href="#tab5" data-toggle="tab">Observaciones</a></li>-->
                       </ul>
                        <div class="panel-body">
                            
                            <div class="tab-content ">
                                <div class="tab-pane active" id="tab1">
                                   <div class="col-lg-6" >
                                        <div class="form-group">
                                            <label for="nombre">Email</label>
                                            <% String disabled = !nuevo?"disabled":"";%>
                                            <input class="form-control" name="usu_mail" id="usu_mail"  <%= disabled%> value="<%= usuario.getUsu_mail() %>">
                                        </div>
                                        <%  if(nuevo) {%>
                                            <div class="form-group">
                                                <label for="nombre">Password</label>
                                                <input class="form-control" name="usu_password" id="usu_password"  value="">
                                            </div>
                                            <div class="form-group">
                                                <label for="nombre">Repita el password</label>
                                                <input class="form-control" name="usu_password2" id="usu_password2"  value="">
                                            </div>
                                        <% } %>
                                        
                                        <div class="form-group">
                                            <label>Activo</label>
                                            
                                            <% String checked = (nuevo ||usuario.getUsu_activo()!=0)?"checked":"";%>
                                            <input type="checkbox" name="usu_activo" id="usu_activo" <%=checked%> >
                                        </div>
                                </div>
                                </div>
                                <!-- /.col-lg-6 (nested) -->
                                <div class="tab-pane" id="tab2" >
                                    <div class="col-lg-6"></div>
                                    <div class="col-lg-6"></div>
                                </div>
                                 <div class="tab-pane" id="tab3">
                                   <div class="col-lg-6"></div>
                                 </div>

                                  <div class="tab-pane" id="tab5">
                                      <div  class="col-lg-12">
                                       <div class="form-group">
                                            <label for="observaciones">Observaciones</label>
                                            <textarea class="form-control" rows="3"  name="observaciones" id="observaciones" ></textarea>
                                        </div>
                                      </div>
                                </div>
                                <!-- /.col-lg-6 (nested) -->
                                
                            </div>
                            <!-- /.row (nested) -->
                            <div class="col-lg-12">
                                <button type="submit" class="btn btn-default">Guardar</button>
                                <a type="reset" class="btn btn-default" href="<%=PathCfg.USUARIO%>">Cancelar</a>
                            </div>
                        </div>
                        <!-- /.panel-body -->
                    </div>
                    <!-- /.panel -->
                </div>
                <!-- /.col-lg-12 -->
                </form>
            </div>
            <!-- /.row -->
        </div>
        <!-- /#page-wrapper -->

    </div>
    <!-- /#wrapper -->

    <!-- jQuery -->
    <script src="bower_components/jquery/dist/jquery.min.js"></script>

    <!-- Bootstrap Core JavaScript -->
    <script src="bower_components/bootstrap/dist/js/bootstrap.min.js"></script>

    <!-- Metis Menu Plugin JavaScript -->
    <script src="bower_components/metisMenu/dist/metisMenu.min.js"></script>
    <script src="js/bootbox.min.js"></script>
    <script src="bower_components/jquery-mask/jquery.mask.min.js"></script>    
    <script src="bower_components/bootstrap-datepicker/js/bootstrap-datepicker.min.js"></script>    
    <script src="bower_components/bootstrap-datepicker/js/locales/bootstrap-datepicker.es.min.js"></script>        

    <!-- Custom Theme JavaScript -->
    <script src="dist/js/sb-admin-2.js"></script>
    <script src="js/moment-with-locales.min.js"></script>
    <script src="js/invesoft.js"></script>
    <script>
        $(document).ready(function(){ 
            $('form').submit(function(e){
                $(e).preventDefault();
                
            });
        });
    </script>
    <%@include file="tpl_footer.jsp"%>
</body>

</html>

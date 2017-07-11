<%@page import="utils.OptionsCfg"%>
<%@page import="utils.PathCfg"%>
<%
    Integer id_tipo_usuario = (Integer) session.getAttribute("id_tipo_usuario");
    if(id_tipo_usuario ==null) id_tipo_usuario = 15;
%>
<!-- start: Main Menu -->
<div id="sidebar-left" class="col-lg-2 col-sm-1 ">

        <div class="sidebar-nav nav-collapse collapse navbar-collapse">
                <ul class="nav main-menu">
                        <li><a href="<%=request.getContextPath()%>"><i class="fa fa-globe"></i><span class="hidden-sm text"> Principal</span></a></li>	
                        <li><a href="<%=PathCfg.CLIENTE%>"><i class="fa fa-users"></i><span class="hidden-sm text"> Clientes</span></a></li>	
<!--                        <li> <a class="dropmenu" href=""           ><i class="fa fa-user"></i><span class="hidden-sm text"> Clientes</span> <span class="chevron closed"></span></a>
                            <ul>
                                <li><a class="submenu" href="<%=PathCfg.CLIENTE%>"><i class="fa fa-users"></i><span class="hidden-sm text"> Todos</span></a></li>
                                <li><a class="submenu" href="<%=PathCfg.PROPIETARIO%>"><i class="fa fa-users"></i><span class="hidden-sm text"> Inquilinos</span></a></li>
                                <li><a class="submenu" href="<%=PathCfg.INQUILINO%>"><i class="fa fa-users"></i><span class="hidden-sm text"> Propietarios</span></a></li>
                            </ul>
                        </li>-->
                        <li><a href="<%=PathCfg.PROPIEDAD%>"><i class="fa fa-home"></i><span class="hidden-sm text"> Propiedades</span></a></li>
                        <li><a href="<%=PathCfg.CONTRATO%>"><i class="fa fa-file-text"></i><span class="hidden-sm text"> Carpetas</span></a></li>
                        <li><a href="<%=PathCfg.CUENTA%>?id_tipo_cliente=<%=OptionsCfg.CLIENTE_TIPO_INQUILINO%>"><i class="fa fa-dollar"></i><span class="hidden-sm text"> Cuenta Inquilino</span></a></li>
                        <li><a href="<%=PathCfg.CUENTA%>?id_tipo_cliente=<%=OptionsCfg.CLIENTE_TIPO_PROPIETARIO%>"><i class="fa fa-dollar"></i><span class="hidden-sm text"> Cuenta Propietario</span></a></li>
                        <% if(id_tipo_usuario<=2) {%>
                            <li><a href="<%=PathCfg.CAJA%>"><i class="fa fa-inbox"></i><span class="hidden-sm text"> Cajas diarias</span></a></li>
                            <li><a href="<%=PathCfg.CUENTA_INTERNA%>"><i class="fa fa-dollar"></i><span class="hidden-sm text"> Cuentas Internas</span></a></li>
                            <li><a href="<%=PathCfg.RECIBO%>"><i class="fa fa-list-alt"></i><span class="hidden-sm text"> Recibos</span></a></li>
                        <% }%>
                        <li><a href="<%=PathCfg.LOCALIDAD%>"><i class="fa fa-flag"></i><span class="hidden-sm text"> Localidades</span></a></li>
                        <li><a href="<%=PathCfg.BARRIO%>"><i class="fa fa-building-o"></i><span class="hidden-sm text"> Barrios</span></a></li>
                        <li><a href="<%=PathCfg.VENDEDOR%>"><i class="fa fa-male"></i><span class="hidden-sm text"> Vendedores</span></a></li>
                        <li><a href="<%=PathCfg.USUARIO%>"><i class="fa fa-users"></i><span class="hidden-sm text"> Usuarios</span></a></li>                     
                        <li><a href="<%=PathCfg.REPORTE%>"><i class="fa fa-bar-chart-o"></i><span class="hidden-sm text"> Reportes</span></a></li>                     
                </ul>
        </div>
            <a href="#" id="main-menu-min" class="full visible-md visible-lg"><i class="fa fa-angle-double-left"></i></a>
    </div>
<!-- end: Main Menu -->
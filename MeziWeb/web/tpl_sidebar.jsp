<%@page import="utils.OptionsCfg"%>
<%@page import="utils.PathCfg"%>
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
                        <li><a href="<%=PathCfg.LOCALIDAD%>"><i class="fa fa-flag"></i><span class="hidden-sm text"> Localidades</span></a></li>
                        <li><a href="<%=PathCfg.BARRIO%>"><i class="fa fa-building-o"></i><span class="hidden-sm text"> Barrios</span></a></li>
                        <li><a href="<%=PathCfg.VENDEDOR%>"><i class="fa fa-male"></i><span class="hidden-sm text"> Vendedores</span></a></li>
                        <li><a href="<%=PathCfg.USUARIO%>"><i class="fa fa-users"></i><span class="hidden-sm text"> Usuarios</span></a></li>
                     <!-- 
                        
                        <li>
                                <a class="dropmenu" href="index.html#"><i class="fa fa-folder-o"></i><span class="hidden-sm text"> Example Pages</span> <span class="chevron closed"></span></a>
                                <ul>
                                        <li><a class="submenu" href="page-inbox.html"><i class="fa fa-envelope-o"></i><span class="hidden-sm text"> Inbox</span></a></li>
                                        <li><a class="submenu" href="page-invoice.html"><i class="fa fa-file-text"></i><span class="hidden-sm text"> Invoice</span></a></li>
                                        <li><a class="submenu" href="page-todo.html"><i class="fa fa-tasks"></i><span class="hidden-sm text"> ToDo & Timeline</span></a></li>
                                        <li><a class="submenu" href="page-profile.html"><i class="fa fa-male"></i><span class="hidden-sm text"> Profile</span></a></li>
                                        <li><a class="submenu" href="page-pricing-tables.html"><i class="fa fa-table"></i><span class="hidden-sm text"> Pricing Tables</span></a></li>
                                        <li><a class="submenu" href="page-404.html"><i class="fa fa-unlink"></i><span class="hidden-sm text"> 404</span></a></li>
                                        <li><a class="submenu" href="page-500.html"><i class="fa fa-unlink"></i><span class="hidden-sm text"> 500</span></a></li>
                                        <li><a class="submenu" href="page-lockscreen.html"><i class="fa fa-lock"></i><span class="hidden-sm text"> LockScreen</span></a></li>
                                        <li><a class="submenu" href="page-lockscreen2.html"><i class="fa fa-lock"></i><span class="hidden-sm text"> LockScreen2</span></a></li>
                                        <li><a class="submenu" href="page-login.html"><i class="fa fa-key"></i><span class="hidden-sm text"> Login Page</span></a></li>
                                        <li><a class="submenu" href="page-register.html"><i class="fa fa-sign-in"></i><span class="hidden-sm text"> Register Page</span></a></li>
                                </ul>	
                        </li>
                        <li>
                                <a class="dropmenu" href="index.html#"><i class="fa fa-edit"></i><span class="hidden-sm text"> Forms</span> <span class="chevron closed"></span></a>
                                <ul>
                                        <li><a class="submenu" href="form-elements.html"><i class="fa fa-edit"></i><span class="hidden-sm text"> Form elements</span></a></li>
                                        <li><a class="submenu" href="form-wizard.html"><i class="fa fa-edit"></i><span class="hidden-sm text"> Wizard</span></a></li>
                                        <li><a class="submenu" href="form-dropzone.html"><i class="fa fa-edit"></i><span class="hidden-sm text"> Dropzone Upload</span></a></li>
                                        <li><a class="submenu" href="form-x-editable.html"><i class="fa fa-edit"></i><span class="hidden-sm text"> X-editable</span></a></li>
                                </ul>
                        </li>
                        <li>
                                <a class="dropmenu" href="index.html#"><i class="fa fa-list-alt"></i><span class="hidden-sm text"> Charts</span> <span class="chevron closed"></span></a>
                                <ul>
                                        <li><a class="submenu" href="charts-flot.html"><i class="fa fa-chevron-right"></i><span class="hidden-sm text"> Flot Charts</span></a></li>
                                        <li><a class="submenu" href="charts-xcharts.html"><i class="fa fa-chevron-right"></i><span class="hidden-sm text"> xCharts</span></a></li>
                                        <li><a class="submenu" href="charts-others.html"><i class="fa fa-chevron-right"></i><span class="hidden-sm text"> Other</span></a></li>
                                </ul>

                        </li>
                        <li><a href="typography.html"><i class="fa fa-font"></i><span class="hidden-sm text"> Typography</span></a></li>
                        <li><a href="gallery.html"><i class="fa fa-picture-o"></i><span class="hidden-sm text"> Gallery</span></a></li>
                        <li><a href="table.html"><i class="fa fa-align-justify"></i><span class="hidden-sm text"> Tables</span></a></li>
                        <li><a href="calendar.html"><i class="fa fa-calendar"></i><span class="hidden-sm text"> Calendar</span></a></li>
                        <li><a href="file-manager.html"><i class="fa fa-folder-open"></i><span class="hidden-sm text"> File Manager</span></a></li>
                        <li>
                                <a class="dropmenu" href="index.html#"><i class="fa fa-star"></i><span class="hidden-sm text"> Icons</span> <span class="chevron closed"></span></a>
                                <ul>
                                        <li><a class="submenu" href="icons-halflings.html"><i class="fa fa-star"></i><span class="hidden-sm text"> Halflings</span></a></li>
                                        <li><a class="submenu" href="icons-glyphicons-pro.html"><i class="fa fa-star"></i><span class="hidden-sm text"> Glyphicons PRO</span></a></li>
                                        <li><a class="submenu" href="icons-filetypes.html"><i class="fa fa-star"></i><span class="hidden-sm text"> Filetypes</span></a></li>
                                        <li><a class="submenu" href="icons-social.html"><i class="fa fa-star"></i><span class="hidden-sm text"> Social</span></a></li>
                                        <li><a class="submenu" href="icons-font-awesome.html"><i class="fa fa-star"></i><span class="hidden-sm text"> Font Awesome</span></a></li>
                                </ul>
                        </li>
                        <li>
                                <a class="dropmenu" href="index.html#"><i class="fa fa-folder-open"></i><span class="hidden-sm text"> 4 Level Menu</span> <span class="chevron closed"></span></a>
                                <ul>
                                        <li><a href="2nd-level.html"><i class="fa fa-folder"></i><span class="hidden-sm text"> 2nd Level</span></a></li>
                                        <li>
                                                <a class="dropmenu" href="index.html#"><i class="fa fa-folder-open"></i><span class="hidden-sm text"> 2nd Level</span> <span class="chevron closed"></span></a>
                                                <ul>
                                                        <li><a href="3rd-level.html"><i class="fa fa-folder"></i><span class="hidden-sm text"> 3rd Level</span></a></li>
                                                        <li>
                                                                <a class="dropmenu" href="index.html#"><i class="fa fa-folder-open"></i><span class="hidden-sm text"> 3rd Level</span> <span class="chevron closed"></span></a>
                                                                <ul>
                                                                        <li>
                                                                                <a class="submenu" href="4th-level.html"><i class="fa fa-folder"></i><span class="hidden-sm text"> 4th Level</span></a>
                                                                        </li>
                                                                </ul>
                                                        </li>
                                                        <li>
                                                                <a class="dropmenu" href="index.html#"><i class="fa fa-folder-open"></i><span class="hidden-sm text"> 3rd Level</span> <span class="chevron closed"></span></a>
                                                                <ul>
                                                                        <li>
                                                                                <a class="submenu" href="4th-level2.html"><i class="fa fa-folder"></i><span class="hidden-sm text"> 4th Level</span></a>
                                                                        </li>
                                                                </ul>
                                                        </li>
                                                </ul>	
                                        </li>
                                </ul>
                        </li>-->
                </ul>
        </div>
            <a href="#" id="main-menu-min" class="full visible-md visible-lg"><i class="fa fa-angle-double-left"></i></a>
    </div>
<!-- end: Main Menu -->
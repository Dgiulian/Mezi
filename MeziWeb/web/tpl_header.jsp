
<%@page import="utils.PathCfg"%>
<%
    String email = (String)    session.getAttribute("email");
    if(email==null) email = "";
%>
<!-- start: Header -->
	<header class="navbar">
		<div class="container">
			<button class="navbar-toggle" type="button" data-toggle="collapse" data-target=".sidebar-nav.nav-collapse">
			      <span class="icon-bar"></span>
			      <span class="icon-bar"></span>
			      <span class="icon-bar"></span>
			</button>
			<a id="main-menu-toggle" class="hidden-xs open"><i class="fa fa-bars"></i></a>		
			<a class="navbar-brand col-md-2 col-sm-1 col-xs-2" href="<%=request.getContextPath()%>"><span>Mezi</span></a>
<!--			<div id="search" class="col-sm-4 col-xs-8 col-lg-3">
				<select>
					<option>everything</option>
					<option>messages</option>
					<option>comments</option>
					<option>users</option>
			  	</select>
				<input type="text" placeholder="search" />
				<i class="fa fa-search"></i>
			</div>-->
			<!-- start: Header Menu -->
			<div class="nav-no-collapse header-nav">
				<ul class="nav navbar-nav pull-right">					
					<li>
						<a class="btn" href="index.html#">
							<i class="fa fa-wrench"></i>
						</a>
					</li>
					<!-- start: User Dropdown -->
					<li class="dropdown">
						<a class="btn account dropdown-toggle" data-toggle="dropdown" href="index.html#">
							<div class="avatar"></div>
							<div class="user">
								<span class="hello">Bienvenido!</span>
								<span class="name"><%= email %></span>
							</div>
						</a>
						<ul class="dropdown-menu">
<!--							<li><a href="index.html#"><i class="fa fa-user"></i> Perfil</a></li>
							<li><a href="index.html#"><i class="fa fa-cog"></i> Configuraci&oacute;n</a></li>							-->
							<li><a href="<%=PathCfg.LOGOUT%>"><i class="fa fa-sign-out"></i> Logout</a></li>
						</ul>
					</li>
					<!-- end: User Dropdown -->
				</ul>
			</div>
			<!-- end: Header Menu -->
			
		</div>	
	</header>
	<!-- end: Header -->
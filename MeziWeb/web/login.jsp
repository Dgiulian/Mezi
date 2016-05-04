<%@page import="java.util.ArrayList"%>
<%@page import="utils.PathCfg"%>
<%
    ArrayList<String> errores = (ArrayList) request.getAttribute("errores");
    String ref =  request.getParameter("ref");
%>
<!DOCTYPE html>
<html lang="en">
<head>

	<!-- start: Meta -->
	<meta charset="utf-8">
	<title><%=PathCfg.PAGE_TITLE%></title>
	<meta name="description" content="Genius Dashboard - Bootstrap Admin Template.">
	<meta name="author" content="Diego Giuliani">
	<meta name="keyword" content="">
	<!-- end: Meta -->

	<!-- start: Mobile Specific -->
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<!-- end: Mobile Specific -->

	<!-- start: CSS -->
	<link href="assets/css/bootstrap.min.css" rel="stylesheet">
	<link href="assets/css/style.min.css" rel="stylesheet">
	<link href="assets/css/print.css" rel="stylesheet" type="text/css" media="print"/>
	<!-- end: CSS -->


	<!-- The HTML5 shim, for IE6-8 support of HTML5 elements -->
	<!--[if lt IE 9]>

	  	<script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
		<script src="assets/js/respond.min.js"></script>

	<![endif]-->

	<!-- start: Favicon and Touch Icons -->
	<link rel="apple-touch-icon-precomposed" sizes="144x144" href="assets/ico/apple-touch-icon-144-precomposed.png">
	<link rel="apple-touch-icon-precomposed" sizes="114x114" href="assets/ico/apple-touch-icon-114-precomposed.png">
	<link rel="apple-touch-icon-precomposed" sizes="72x72" href="assets/ico/apple-touch-icon-72-precomposed.png">
	<link rel="apple-touch-icon-precomposed" sizes="57x57" href="assets/ico/apple-touch-icon-57-precomposed.png">
	<link rel="shortcut icon" href="assets/ico/favicon.png">
	<!-- end: Favicon and Touch Icons -->

</head>

<body>
		<div class="container">
		<div class="row">

                        <div id="content" class="col-sm-12 full">

			<div class="row">
				<div class="login-box">

					<div class="header">
						Inmobiliaria Mezi - Login
					</div>
<!--					<div class="text-with-hr">
						<span>Ingrese sus datos de acceso al sistema</span>
					</div>-->
                            <% if (errores != null) {%>
                            <% for (String error : errores) {%>
                            <div class="alert alert-error">
                                <button data-dismiss="alert" class="close" type="button">×</button>
                                <%= error%>
                            </div>
                            <% }%>

                         <% }%>
					<form class="form-horizontal login" action="<%=PathCfg.LOGIN%>" method="post">
						 <% if(ref!=null) {%>
                                                    <input type="hidden" name="ref" id="ref" value="<%= ref%>">
                                                <% } %>
						<fieldset class="col-sm-12">
							<div class="form-group">
							  	<div class="controls row">
									<div class="input-group col-sm-12">
										<input type="text" class="form-control" id="email" name="email" placeholder="Username or E-mail"/>
										<span class="input-group-addon"><i class="fa fa-user"></i></span>
									</div>
							  	</div>
							</div>

							<div class="form-group">
							  	<div class="controls row">
									<div class="input-group col-sm-12">
										<input type="password" class="form-control" id="password" name="password" placeholder="Password"/>
										<span class="input-group-addon"><i class="fa fa-key"></i></span>
									</div>
							  	</div>
							</div>

							<div class="confirm">
								<input type="checkbox" name="remember"/>
								<label for="remember">Recordarme</label>
							</div>

							<div class="row">
								<button type="submit" class="btn btn-lg btn-primary col-xs-12">Login</button>
							</div>

						</fieldset>

					</form>

					<a class="pull-left" href="page-login.html#">Olvido su contraseña?</a>

					<div class="clearfix"></div>

				</div>
			</div><!--/row-->

		</div>



				</div><!--/row-->

	</div><!--/container-->


	<!-- start: JavaScript-->
	<!--[if !IE]>-->

			<script src="assets/js/jquery-2.1.0.min.js"></script>

	<!--<![endif]-->

	<!--[if IE]>

		<script src="assets/js/jquery-1.11.0.min.js"></script>

	<![endif]-->

	<!--[if !IE]>-->

		<script type="text/javascript">
			window.jQuery || document.write("<script src='assets/js/jquery-2.1.0.min.js'>"+"<"+"/script>");
		</script>

	<!--<![endif]-->

	<!--[if IE]>

		<script type="text/javascript">
	 	window.jQuery || document.write("<script src='assets/js/jquery-1.11.0.min.js'>"+"<"+"/script>");
		</script>

	<![endif]-->
	<script src="assets/js/jquery-migrate-1.2.1.min.js"></script>
	<script src="assets/js/bootstrap.min.js"></script>




	<!-- page scripts -->
	<script src="assets/js/jquery.icheck.min.js"></script>

	<!-- theme scripts -->
	<script src="assets/js/custom.min.js"></script>
	<script src="assets/js/core.min.js"></script>

	<!-- inline scripts related to this page -->	

	<!-- end: JavaScript-->

</body>
</html>

<%@page import="utils.OptionsCfg.Option"%>
<%@page import="bd.Contrato_gasto"%>
<%@page import="bd.Contrato_documento"%>
<%@page import="bd.Contrato_valor"%>
<%@page import="bd.Contrato"%>
<%@page import="utils.TFecha"%>
<%@page import="bd.Cliente"%>
<%@page import="transaccion.TLocalidad"%>
<%@page import="transaccion.TBarrio"%>
<%@page import="bd.Localidad"%>
<%@page import="bd.Barrio"%>
<%@page import="transaccion.TVendedor"%>
<%@page import="bd.Vendedor"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="bd.Propiedad"%>
<%@page import="java.util.HashMap"%>
<%@page import="transaccion.TPropiedad"%>
<%
    Contrato                 contrato              = (Contrato)  request.getAttribute("contrato");
    List<Contrato_valor>     lstContrato_valor     = (List<Contrato_valor>)     request.getAttribute("contrato_valor");
    List<Contrato_documento> lstContrato_documento = (List<Contrato_documento>) request.getAttribute("contrato_documento");
    List<Contrato_gasto>     lstContrato_gasto     = (List<Contrato_gasto>)     request.getAttribute("contrato_gasto");

    Propiedad propiedad = (Propiedad) request.getAttribute("propiedad");
    Cliente   cliente   = (Cliente)   request.getAttribute("inquilino");
    Vendedor  vendedor  = (Vendedor)  request.getAttribute("vendedor");

    TPropiedad tp = new TPropiedad();
    HashMap<String,String> mapFiltro = new HashMap<String,String>();

    HashMap<String,String> filtroVendedores = new HashMap<String,String>();

    mapFiltro.put("id_estado","1");
    List<Propiedad> lstPropiedades = tp.getListFiltro(mapFiltro);
    if (lstPropiedades==null) lstPropiedades = new ArrayList<Propiedad>();

    filtroVendedores.put("activo","1");
    List<Vendedor> lstVendedores = new TVendedor().getListFiltro(filtroVendedores);
    if(lstVendedores==null) lstVendedores = new ArrayList<Vendedor>();


    boolean con_propiedad = true;
    boolean con_cliente   = true;
    boolean con_vendedor  = true;
    if(propiedad==null){
        propiedad = new Propiedad();
        con_propiedad = false;
    }

    if (cliente==null){
        cliente = new Cliente();
        con_cliente = false;
    }
    Barrio barrio = new TBarrio().getById(propiedad.getId_barrio());
    if(barrio==null){
        barrio = new Barrio();
    }

    Localidad localidad = new TLocalidad().getById(propiedad.getId_localidad());
    if(localidad==null){
        localidad = new Localidad();
    }

    if (vendedor==null){
        vendedor = new Vendedor();
        con_vendedor = false;
    }
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <%@include file="tpl_head.jsp" %>
    <style>
        hr {
            /*border: 1px;*/
            border-top: 2px solid #eee;
            width: 100%;
        }

    </style>
</head>

<body>    
		<!-- start: Header -->
	<%@include file="tpl_header.jsp"%>

		<div class="container">
		<div class="row">

			<!-- start: Main Menu -->
			<%@include file="tpl_sidebar.jsp"%>
			<!-- end: Main Menu -->

			<!-- start: Content -->
			<div id="content" class="col-lg-10 col-sm-11 ">
                         <form action="<%=PathCfg.CONTRATO_ESTADO%>" method="POST">
			<div class="row">
				<div class="col-lg-12">
                                   
                                    <input type="hidden" id="id_contrato" name="id_contrato" value="<%=contrato.getId()%>" >
                                    <input type="hidden" id="id_estado" name="id_estado" value="<%=contrato.getId_estado()%>" >
                                    <% if(contrato.getId_estado().equals(OptionsCfg.CONTRATO_ESTADO_INICIAL)) {%>
                                    
                                    <% } %>
				</div><!--/col-->
                                <div class="row">
                                    <div class="col-lg-12">
                                       <div class="form-actions">
                                          <button type="submit" class="btn btn-primary" id="btnSubmit">Guardar</button>
                                          <a  href="<%= PathCfg.CONTRATO%>"class="btn btn-default">Cancelar</a>
                                      </div>
                                   </div>
                                </div>

			</div><!--/row-->
                        </form>



			</div>
			<!-- end: Content -->

                    </div><!--/row-->

	</div><!--/container-->




	<div class="clearfix"></div>

        <%@include file="tpl_footer.jsp" %>

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
	<script src="assets/js/jquery-ui-1.10.3.custom.min.js"></script>
	<!--<script src="assets/js/jquery.sparkline.min.js"></script>-->
	<!--<script src="assets/js/jquery.chosen.min.js"></script>-->
	<!--<script src="assets/js/jquery.cleditor.min.js"></script>-->
	<script src="assets/js/jquery.autosize.min.js"></script>
	<!--<script src="assets/js/jquery.placeholder.min.js"></script>-->
	<script src="assets/js/jquery.maskedinput.min.js"></script>
	<!--<script src="assets/js/jquery.inputlimiter.1.3.1.min.js"></script>-->
	<script src="assets/js/bootstrap-datepicker.min.js"></script>
        <script src="assets/js/locales/bootstrap-datepicker.es.min.js"></script>

	<script src="assets/js/bootstrap-timepicker.min.js"></script>
	<script src="assets/js/moment.min.js"></script>
	

	<!-- theme scripts -->
	<script src="assets/js/custom.min.js"></script>
	<script src="assets/js/core.min.js"></script>

        <script src="assets/js/notify.min.js"></script>
        <script src="assets/js/jquery.bootstrap.wizard.min.js"></script>

        <script src="assets/js/bootbox.min.js"></script>
        <script src="assets/js/handlebars.runtime-v4.0.5.js"></script>
        
        <script src="assets/templates/agregarGasto.js"></script>
        <script src="assets/templates/agregarValor.js"></script>
        <script src="assets/templates/agregarMonto.js"></script>
        <script src="assets/templates/agregarLineaGasto.js"></script>
        <script src="assets/js/common-functions.js"></script>

<script>
$(document).ready(function() {
    $('#btnSubmit').click(submitForm);
});

function validar(data){
    var todoOk = true;
    return todoOk;
}
</script>
 
 <%@include file="mdl_propiedad.jsp" %>
 <%@include file="mdl_cliente.jsp" %>
 <%--<%@include file="mdl_propietario.jsp" %>--%>
 <%@include file="mdl_valor.jsp" %>
 <%@include file="mdl_documento.jsp" %>
 <%--<%@include file="mdl_gasto.jsp" %>--%>
</body>
</html>
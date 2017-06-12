(function() {
  var template = Handlebars.template, templates = Handlebars.templates = Handlebars.templates || {};
templates['contrato.accion'] = template({"1":function(container,depth0,helpers,partials,data) {
    var helper;

  return "              <a href=\"ContratoEdit?id="
    + container.escapeExpression(((helper = (helper = helpers.id || (depth0 != null ? depth0.id : depth0)) != null ? helper : helpers.helperMissing),(typeof helper === "function" ? helper.call(depth0 != null ? depth0 : (container.nullContext || {}),{"name":"id","hash":{},"data":data}) : helper)))
    + "\" class=\"btn btn-warning btn-block link-disabled\">Editar contrato</a>\r\n";
},"3":function(container,depth0,helpers,partials,data) {
    var helper;

  return "              <a href=\"ContratoEdit?id="
    + container.escapeExpression(((helper = (helper = helpers.id || (depth0 != null ? depth0.id : depth0)) != null ? helper : helpers.helperMissing),(typeof helper === "function" ? helper.call(depth0 != null ? depth0 : (container.nullContext || {}),{"name":"id","hash":{},"data":data}) : helper)))
    + "\" class=\"btn btn-warning btn-block\">Editar contrato</a>\r\n";
},"compiler":[7,">= 4.0.0"],"main":function(container,depth0,helpers,partials,data) {
    var stack1, helper, alias1=depth0 != null ? depth0 : (container.nullContext || {}), alias2=helpers.helperMissing, alias3="function", alias4=container.escapeExpression;

  return "<div class=\"row\">\r\n  <div class=\"col-md-12\">\r\n      <form class=\"form-vertical\">\r\n          <input id=\"id\" name=\"id\" type=\"hidden\" class=\"\" value=\""
    + alias4(((helper = (helper = helpers.id || (depth0 != null ? depth0.id : depth0)) != null ? helper : alias2),(typeof helper === alias3 ? helper.call(alias1,{"name":"id","hash":{},"data":data}) : helper)))
    + "\" >\r\n\r\n          <div class=\"form-group\">\r\n              <a href=\"ContratoView?id="
    + alias4(((helper = (helper = helpers.id || (depth0 != null ? depth0.id : depth0)) != null ? helper : alias2),(typeof helper === alias3 ? helper.call(alias1,{"name":"id","hash":{},"data":data}) : helper)))
    + "\" class=\"btn btn-primary btn-block\">Ver contrato</a>\r\n          </div>\r\n          <div class=\"form-group\">\r\n"
    + ((stack1 = helpers["if"].call(alias1,(helpers.isActive || (depth0 && depth0.isActive) || alias2).call(alias1,(depth0 != null ? depth0.id_estado : depth0),{"name":"isActive","hash":{},"data":data}),{"name":"if","hash":{},"fn":container.program(1, data, 0),"inverse":container.program(3, data, 0),"data":data})) != null ? stack1 : "")
    + "          </div>\r\n          <div class=\"form-group\">\r\n              <a href=\"ContratoEstado?id="
    + alias4(((helper = (helper = helpers.id || (depth0 != null ? depth0.id : depth0)) != null ? helper : alias2),(typeof helper === alias3 ? helper.call(alias1,{"name":"id","hash":{},"data":data}) : helper)))
    + "\" class=\"btn btn-warning btn-block\">Cambiar estado</a>\r\n          </div>\r\n          <div class=\"form-group\">\r\n            <span class=\"btn btn-danger btn-block btn-del\" data-index="
    + alias4(((helper = (helper = helpers.id || (depth0 != null ? depth0.id : depth0)) != null ? helper : alias2),(typeof helper === alias3 ? helper.call(alias1,{"name":"id","hash":{},"data":data}) : helper)))
    + ">Eliminar contrato</span>\r\n          </div>\r\n        </form>\r\n  </div>\r\n</div>";
},"useData":true});
})();
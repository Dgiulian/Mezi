(function() {
  var template = Handlebars.template, templates = Handlebars.templates = Handlebars.templates || {};
templates['agregarMonto'] = template({"1":function(container,depth0,helpers,partials,data) {
    var helper, alias1=depth0 != null ? depth0 : {}, alias2=helpers.helperMissing, alias3="function", alias4=container.escapeExpression;

  return "    <tr>\r\n        <td>"
    + alias4(((helper = (helper = helpers.fecha_desde || (depth0 != null ? depth0.fecha_desde : depth0)) != null ? helper : alias2),(typeof helper === alias3 ? helper.call(alias1,{"name":"fecha_desde","hash":{},"data":data}) : helper)))
    + "<input type=\"hidden\" name=\"valor_desde\" value=\""
    + alias4(((helper = (helper = helpers.fecha_desde || (depth0 != null ? depth0.fecha_desde : depth0)) != null ? helper : alias2),(typeof helper === alias3 ? helper.call(alias1,{"name":"fecha_desde","hash":{},"data":data}) : helper)))
    + "\"></td>\r\n        <td>"
    + alias4(((helper = (helper = helpers.fecha_hasta || (depth0 != null ? depth0.fecha_hasta : depth0)) != null ? helper : alias2),(typeof helper === alias3 ? helper.call(alias1,{"name":"fecha_hasta","hash":{},"data":data}) : helper)))
    + "<input type=\"hidden\" name=\"valor_hasta\" value=\""
    + alias4(((helper = (helper = helpers.fecha_hasta || (depth0 != null ? depth0.fecha_hasta : depth0)) != null ? helper : alias2),(typeof helper === alias3 ? helper.call(alias1,{"name":"fecha_hasta","hash":{},"data":data}) : helper)))
    + "\"></td>\r\n        <td>"
    + alias4(((helper = (helper = helpers.monto || (depth0 != null ? depth0.monto : depth0)) != null ? helper : alias2),(typeof helper === alias3 ? helper.call(alias1,{"name":"monto","hash":{},"data":data}) : helper)))
    + "<input type=\"hidden\" name=\"valor_monto\" value=\""
    + alias4(((helper = (helper = helpers.monto || (depth0 != null ? depth0.monto : depth0)) != null ? helper : alias2),(typeof helper === alias3 ? helper.call(alias1,{"name":"monto","hash":{},"data":data}) : helper)))
    + "\"></td>\r\n        <td><span class='btn btn-xs btn-danger btn-circle btn-del'><span class='fa fa-trash-o'></span></td>\r\n    </tr>\r\n";
},"3":function(container,depth0,helpers,partials,data) {
    var helper, alias1=depth0 != null ? depth0 : {}, alias2=helpers.helperMissing, alias3="function", alias4=container.escapeExpression;

  return "  <tr>\r\n    <td>"
    + alias4(((helper = (helper = helpers.fecha_desde || (depth0 != null ? depth0.fecha_desde : depth0)) != null ? helper : alias2),(typeof helper === alias3 ? helper.call(alias1,{"name":"fecha_desde","hash":{},"data":data}) : helper)))
    + "<input type=\"hidden\" name=\"documento_desde\" value=\""
    + alias4(((helper = (helper = helpers.fecha_desde || (depth0 != null ? depth0.fecha_desde : depth0)) != null ? helper : alias2),(typeof helper === alias3 ? helper.call(alias1,{"name":"fecha_desde","hash":{},"data":data}) : helper)))
    + "\"></td>\r\n    <td>"
    + alias4(((helper = (helper = helpers.fecha_hasta || (depth0 != null ? depth0.fecha_hasta : depth0)) != null ? helper : alias2),(typeof helper === alias3 ? helper.call(alias1,{"name":"fecha_hasta","hash":{},"data":data}) : helper)))
    + "<input type=\"hidden\" name=\"documento_hasta\" value=\""
    + alias4(((helper = (helper = helpers.fecha_hasta || (depth0 != null ? depth0.fecha_hasta : depth0)) != null ? helper : alias2),(typeof helper === alias3 ? helper.call(alias1,{"name":"fecha_hasta","hash":{},"data":data}) : helper)))
    + "\"></td>\r\n    <td>"
    + alias4(((helper = (helper = helpers.monto || (depth0 != null ? depth0.monto : depth0)) != null ? helper : alias2),(typeof helper === alias3 ? helper.call(alias1,{"name":"monto","hash":{},"data":data}) : helper)))
    + "<input type=\"hidden\" name=\"documento_monto\" value=\""
    + alias4(((helper = (helper = helpers.monto || (depth0 != null ? depth0.monto : depth0)) != null ? helper : alias2),(typeof helper === alias3 ? helper.call(alias1,{"name":"monto","hash":{},"data":data}) : helper)))
    + "\"></td>\r\n    <td><span class='btn btn-xs btn-danger btn-circle btn-del'><span class='fa fa-trash-o'></span></td>\r\n  </tr>\r\n";
},"compiler":[7,">= 4.0.0"],"main":function(container,depth0,helpers,partials,data) {
    var stack1;

  return ((stack1 = helpers["if"].call(depth0 != null ? depth0 : {},(depth0 != null ? depth0.esValor : depth0),{"name":"if","hash":{},"fn":container.program(1, data, 0),"inverse":container.program(3, data, 0),"data":data})) != null ? stack1 : "");
},"useData":true});
})();
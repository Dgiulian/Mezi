(function() {
  var template = Handlebars.template, templates = Handlebars.templates = Handlebars.templates || {};
templates['agregarLineaGasto'] = template({"compiler":[7,">= 4.0.0"],"main":function(container,depth0,helpers,partials,data) {
    var helper, alias1=depth0 != null ? depth0 : {}, alias2=helpers.helperMissing, alias3="function", alias4=container.escapeExpression;

  return "<tr>\r\n       <td>"
    + alias4(((helper = (helper = helpers.concepto || (depth0 != null ? depth0.concepto : depth0)) != null ? helper : alias2),(typeof helper === alias3 ? helper.call(alias1,{"name":"concepto","hash":{},"data":data}) : helper)))
    + "<input type=\"hidden\" name=\"gasto_concepto\" value=\""
    + alias4(((helper = (helper = helpers.concepto || (depth0 != null ? depth0.concepto : depth0)) != null ? helper : alias2),(typeof helper === alias3 ? helper.call(alias1,{"name":"concepto","hash":{},"data":data}) : helper)))
    + "\"></td>\r\n       <td>"
    + alias4(((helper = (helper = helpers.aplica || (depth0 != null ? depth0.aplica : depth0)) != null ? helper : alias2),(typeof helper === alias3 ? helper.call(alias1,{"name":"aplica","hash":{},"data":data}) : helper)))
    + "<input type=\"hidden\" name=\"gasto_aplica\"  value=\""
    + alias4(((helper = (helper = helpers.gasto_aplica || (depth0 != null ? depth0.gasto_aplica : depth0)) != null ? helper : alias2),(typeof helper === alias3 ? helper.call(alias1,{"name":"gasto_aplica","hash":{},"data":data}) : helper)))
    + "\"></td>\r\n       <td>"
    + alias4(((helper = (helper = helpers.cuotas || (depth0 != null ? depth0.cuotas : depth0)) != null ? helper : alias2),(typeof helper === alias3 ? helper.call(alias1,{"name":"cuotas","hash":{},"data":data}) : helper)))
    + "<input type=\"hidden\" name=\"gasto_cuota\"   value=\""
    + alias4(((helper = (helper = helpers.gasto_cuota || (depth0 != null ? depth0.gasto_cuota : depth0)) != null ? helper : alias2),(typeof helper === alias3 ? helper.call(alias1,{"name":"gasto_cuota","hash":{},"data":data}) : helper)))
    + "\"></td>\r\n       <td>"
    + alias4(((helper = (helper = helpers.importe || (depth0 != null ? depth0.importe : depth0)) != null ? helper : alias2),(typeof helper === alias3 ? helper.call(alias1,{"name":"importe","hash":{},"data":data}) : helper)))
    + "<input type=\"hidden\" name=\"gasto_importe\" value=\""
    + alias4(((helper = (helper = helpers.importe || (depth0 != null ? depth0.importe : depth0)) != null ? helper : alias2),(typeof helper === alias3 ? helper.call(alias1,{"name":"importe","hash":{},"data":data}) : helper)))
    + "\"></td>\r\n       <td><span class='btn btn-xs btn-danger btn-circle btn-del'><span class='fa fa-trash-o'></span></td>\r\n</tr>\r\n";
},"useData":true});
})();
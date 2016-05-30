/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

 function siguienteHora(fecha,hora){
    if ( fecha === "" ) fecha = moment().format('DD/MM/YYYY');
    if ( hora === "" ) hora = moment().subtract(1,'hour').format('HH:mm:ss');  // Tomamos una hora antes

    var m = moment(fecha + " " + hora,'DD/MM/YYYY HH:mm:ss');

    if (m.isValid()){
        m.add(1,'hour');
        return [m.format("DD/MM/YYYY"),m.format("HH:mm:ss")];
    }
    else return ["",""];
 }
 function wrapTag(tag,value,cls,id){
             var str = "<"   + tag;
             if (cls!=undefined) {str += " class='" + cls+"'"};
             if(id!=undefined){ str += " id= '"+ id +"'";}
             str += ">";
             str += value
             str += "</" + tag+">";
             return str;
         }
function convertirFecha(fecha) { 
    return moment(fecha,'YYYY-MM-DD').format("DD/MM/YYYY");
}
function convertirFechayHora(fecha){
    return moment(fecha,'YYYY-MM-DD hh:mm:ss').format("DD/MM/YYYY hh:mm:ss");
}
function validateDate(md_date,md_time){
    var newDate = moment(md_date + " " + md_time,'DD/MM/YYYY HH:mm:ss');
    return newDate.isValid();
}
function validarFecha(fecha,formato){
    if (formato===undefined) formato = "DD/MM/YYYY";
    return moment(fecha,formato,true).isValid();
}
function validarAnterior(fecha_desde,fecha_hasta){
    var desde = moment(fecha_desde,"DD/MM/YYYY");
    var hasta = moment(fecha_hasta,"DD/MM/YYYY");
    return desde.isBefore(hasta);
}
function availableDate(md_date,md_time,rows){
    if (rows === undefined)
        var rows = $('#measured_data tbody tr').not('.data-unsaved');
    var newDate = moment(md_date + " " + md_time,'DD/MM/YYYY HH:mm:ss');
    
    var arrFechas = [];
    for(var i = 0;i<= rows.length;i++){
        var row = $(rows[i]);
        var fecha = row.find('.data-date').text();
        var hora = row.find('.data-time').text();
        var m = moment(fecha + " " + hora, "DD/MM/YYYY HH:mm:ss");              
        if (m.isValid()){                  
            arrFechas.push(m);              
       }
     }

    for ( var i = 0;i<arrFechas.length;i++){
      if (newDate.isSame(arrFechas[i])){          
          return false;
      };
    }
    return true;
}
function movePage(side){
    if (side === "left") {
        if(page>0) page--;
    } else {
        page++;
    }            
    data.pagNro = page;
    if(page===0){$('.data-left').css('visibility','hidden' )}
    if(page>0) {$('.data-left').css('visibility','visible')}
    loadData(data);
}
function exportar(tipo,formato){
    data.type=tipo;
    data.format = formato;
    var location = "Excel?";
    for (d in data){
        location += d + "=" + data[d] + "&";
    }
    window.location = location;
}

 function deleteData(url,data,onSuccess,message){
    //$tr = $(this).parent().parent();
    if (message===undefined) message= "Esta seguro que desea eliminar el registro?";
    bootbox.confirm(message,function(result){
         if(result) {
             $.ajax({
                url: url, //DATAENTRY_DEL
                data: data,
                method:"POST",
                dataType: "json",
                success: onSuccess
             });

         }
     });
}

function searchData(url,data,onSuccess){
    $.ajax({
       url: url,
       data: data,
       method:"POST",
       dataType: "json",
       success: onSuccess
});
}

function rubroChange(url,data,$id_subrubro) {           
    if($id_subrubro===undefined) $id_subrubro =  $('#id_subrubro');
    
    $id_subrubro.html("");
    
    if (data.id_rubro!=0) {
        
        $.ajax({
            url: url,
            data: data ,
            method:"POST",
            dataType: "json",
            success: function(result) {
                var html = "";
                
                if(result.Result==="OK"){
                    $id_subrubro.html(createSelect(result.Records));
                }
            }
        });
        
    }
    loadDataActivo(data);
}
function subrubroChange() {                       
    $id_rubro = $('#id_rubro');
    loadDataActivo({id_subrubro:$(this).val(),
              id_rubro:$id_rubro.val() 
    });
}

function createSelect(data){
    var html = "<option value='0'>Todos</option>";
    for (var i=0;i<data.length;i++){
        d = data[i];                
        html += "<option value='" + d.id + "'>" + d.descripcion+ "</option>";
    }
    return html;
}

function submitForm(e){
    e.preventDefault();                
    if(validar()){
        $('form').submit();
    }
}

function selTodos(filtro,checked){
    var $arr = $(filtro);
    for(var i = 0;i<$arr.length;i++) {
      $input = $($arr[i]).prop('checked',checked);                
    }
}

$(document).ready(function(){
   $('.uppercase').keyup(function(e) {
        if(e.keyCode>=65 && e.keyCode <= 90) 
            this.value = this.value.toUpperCase();
        return true;
    });
    $('form').bind('keypress keydown',function(e){
        var code = e.keyCode || e.which;
        if(code===13){
            e.preventDefault();
            return false;
        }
    }); 
    
//    $('input').bind('keypress keydown',function(e){
//        var code = e.keyCode || e.which;
//        // Tab code = 9;
//        if(code===13){
//            e.preventDefault();
//            return false;
//        }
//   
//    }); 
    var inputs = $(':input:not(:hidden,:button)').keydown(function keydown(e){  /* Simulate tab  */
            var code = e.keyCode || e.which;            
            if(code===13){
           e.preventDefault();
           var nextInput = inputs.get(inputs.index(this) + 1);
           if (nextInput) {
              nextInput.focus();
           }
        }
    });
    
    $('a[data-toggle="tab"]').on('shown.bs.tab', function (e) {
        var target = $(e.target).attr("href") // activated tab
        var inputs = $(':input:not(:hidden,:button)').keydown(function (e){  /* Simulate tab  */
        
        var code = e.keyCode || e.which;
        if(code===13){
        e.preventDefault();
        var nextInput = inputs.get(inputs.index(this) + 1);
        if (nextInput) {
           nextInput.focus();
        }
    }
});
    });
    if($().mask) {
        $('.date-input').mask('99/99/9999');
        $('.hora').mask('99:99:99');
    }
    if($().datepicker) {
        if($.fn.datepicker.defaults)
            $.fn.datepicker.defaults.format = "dd/mm/yyyy";
        $('.date-picker').datepicker({
            language: 'es',
            locale:'es-AR',
            format:'dd/mm/yyyy',
            dateFormat:'dd/mm/yyyy',
            autoclose: true
        });
//                .on('changeDate', function(ev){
//            
//            $(this).parent().find('input').val(ev.target.value);    
//            $(this).datepicker('hide');
//        });
    }
    
   $('#btnHideData').click(function(){
      $('#btnHideData').toggleClass("fa-chevron-down");
      $('#btnHideData').toggleClass("fa-chevron-up");
      $('#data').slideToggle();
   });
    
   $('#lnkVRCertificado').click(lnkVRCertificado);
   $('#lnkVRHistorial').click(lnkVRHistorial);
   $('#lnkVRSites').click(lnkVRSites);
   
});

    
 function validarCampo (campo,mensajeError,check){     
    if(check ===undefined) { check = false;}          
    if(campo ===undefined || campo.val()=== "" || check(campo)){
      if ($.notify){
          $.notify(mensajeError,"error");
      }else if (bootbox)
            bootbox.alert(mensajeError);
      else alert(mensajeError);
      campo.parent().addClass("has-error");
      return false;
  } else campo.parent().removeClass("has-error");
  return true;
}
//function validarCampoFecha(campo,mensajeError){
//     if(campo===undefined || campo.val()==="" || !validarFecha(campo.val())){            
//      bootbox.alert(mensajeError);
//      campo.parent().addClass("has-error");
//      return false;
//  } else campo.parent().removeClass("has-error");
//  return true;
//}


function validarCampoFecha(e){
    return !validarFecha(e.val());
}
function validarNoCero(e){return parseInt(e.val())===0}    

function parsearInt(valor){
    var v = parseInt(valor);
    if(isNaN(v)) v=0;
    return v;
}
function parsearFloat(valor){
    var v = parseFloat(valor);
    if(isNaN(v)) v=0;    
    return v;
}
function validaCuit(sCUIT) {     
    var sMult = '5432765432'; 
    var aMult = sMult.split(''); 
    sCUIT = sCUIT.replace(/-/g,"") ;
    if (sCUIT && sCUIT.length === 11) 
    { 
        var aCUIT = sCUIT.split(''); 
        var iResult = 0; 
        for(i = 0; i <= 9; i++) 
        { 
            iResult += aCUIT[i] * aMult[i]; 
        } 
        iResult = (iResult % 11); 
        iResult = 11 - iResult; 
         
        if (iResult === 11) iResult = 0; 
        if (iResult === 10) iResult = 9; 
         
        if (iResult === parseInt(aCUIT[10])) 
        { 
            return true; 
        } 
    }     
    return false; 
} 
function validaCuita(sCUIT) 
{     
    var sMult = '5432765432'; 
    var aMult = sMult.split(''); 
     
    if (sCUIT && sCUIT.length === 11) 
    { 
        var aCUIT = sCUIT.split(''); 
        var iResult = 0; 
        for(i = 0; i <= 9; i++) 
        { 
            iResult += aCUIT[i] * aMult[i]; 
        } 
        iResult = (iResult % 11); 
        iResult = 11 - iResult; 
         
        if (iResult === 11) iResult = 0; 
        if (iResult === 10) iResult = 9; 
         
        if (iResult === aCUIT[10]) 
        { 
            return true; 
        } 
    }     
    return false; 
} 
function lnkVRCertificado(){
       bootbox.prompt("Ingrese el c&oacute;digo de activo",function(result){
          if (result !== null) {
                $.ajax({
                   url: 'ActivoSearch',
                   data: {codigo:result},
                   method:"POST",
                   dataType: "json",
                   beforeSend:function(){},
                   success: function(result) {
                       if(result.Result === "OK" ) {
                           if(result.TotalRecordCount === 0 ){
                               bootbox.alert("No se encontr&oacute; el activo");
                               return false;
                           } else if(result.TotalRecordCount > 1 ){
                               bootbox.alert("Existe m&aacute;s de un activo con ese c&oacute;digo");
                               return false;
                           }
                           else {
                               window.location = "CertificadoEdit?id_activo=" + result.Record.id;
                           }
                           console.log(result);
                       }
                   }
               });
          } else {}
           
       });
   }
function lnkVRHistorial(){
       bootbox.prompt("Ingrese el nombre del cliente",function(result){
          if (result !== null) {
                $.ajax({
                   url: 'ClienteSearch',
                   data: {nombre:result},
                   method:"POST",
                   dataType: "json",
                   beforeSend:function(){},
                   success: function(result) {
                       if(result.Result === "OK" ) {
                           if(result.TotalRecordCount === 0 ){
                               bootbox.alert("No se encontr&oacute; el cliente");
                               return false;
                           } else if(result.TotalRecordCount > 1 ){
                               bootbox.alert("Existe m&aacute;s de un cliente con ese nombre");
                               return false;
                           }
                           else {
                               $('#mdlClienteHistoria').modal('show');
//                               window.location = "CertificadoEdit?id_activo=" + result.Record.id;
                           }
                           console.log(result);
                       }
                   }
               });
          } else {}
           
       });
   }        

   function lnkVRSites(){
       bootbox.prompt("Ingrese el nombre del cliente",function(result){
          if (result !== null) {
                $.ajax({
                   url: 'ClienteSearch',
                   data: {nombre:result},
                   method:"POST",
                   dataType: "json",
                   beforeSend:function(){},
                   success: function(result) {
                       if(result.Result === "OK" ) {
                           if(result.TotalRecordCount === 0 ){
                               bootbox.alert("No se encontr&oacute; el cliente");
                               return false;
                           } else if(result.TotalRecordCount > 1 ){
                               bootbox.alert("Existe m&aacute;s de un cliente con ese nombre");
                               return false;
                           }
                           else {
                               window.location = "Site?id_cliente=" + result.Record.id;
                           }
                           console.log(result);
                       } else bootbox.alert(result.Message);
                   }
               });
          } else {}
           
       });
   }        
function calcularHasta(fecha,meses){
    if ( fecha === "" ) return "";
    if ( meses === "" ) return "";
    meses = parseInt(meses);

    var m = moment(fecha, 'DD/MM/YYYY');

    if (m.isValid()){
//        if(meses===12) m.add(meses - 1,'months').endOf('month');
//        else m.add(meses,'months').endOf('month');      
        m.startOf('month').add(meses,'months').subtract(1,'days');        
        return m.format("DD/MM/YYYY");
    }
    else return "";
 }
//$('#nav-bar').width('50px'); $('$('.side - bar').width('50px');$('#page - wrapper').css('margin - left','50px')
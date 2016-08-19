// casper.options.verbose = true;
casper.options.logLevel = 'debug';
casper.start();

// casper.test.begin("Focusout del campo meses",1,function(test){
//     var fecha_fin  ="";
//     casper.thenOpen('http://localhost:8080/MeziWeb/ContratoEdit#tabBasicos',function(){
//         //this.evaluate(function(){$.noConflict();});
//     });
//     casper.waitUntilVisible('#rootwizard',function(){
//         fecha_fin = this.evaluate(function(){
//             $('#fecha_inicio').val("01/05/2016");
//             $('#meses').val(12)
//             $('#meses').trigger('focusout');
//             return $('#fecha_fin').val();
//         });
//         test.assert(fecha_fin==='30/04/2017',"Se completa correctamente la fecha hasta");
//     });

//     //test.done();
// });

// casper.test.begin("Navegación en tabs",3,function(test){
//     casper.thenOpen('http://localhost:8080/MeziWeb/ContratoEdit');
//     casper.then(function(){
//         test.assert(this.evaluate(function(){
//             var $arrTabs = $('.tab-pane.row');
//             var $tabActual = $arrTabs.filter('.active');
//             var $indiceActual = $arrTabs.index($tabActual);
//             $('#btnSiguiente').trigger('click');
//             var $activoNuevo = $arrTabs.filter('.active');
//             var $indiceNuevo = $arrTabs.index($activoNuevo);
//             return $indiceActual < $indiceNuevo;
//         }),"Al hacer click en siguiente debe pasar al siguiente tab");
//     });
//     casper.then(function(){
//         test.assert(this.evaluate(function(){
//             $('ul.nav-tabs li a').last().trigger('click')
//             var $arrTabs = $('.tab-pane.row');
//             var $tabActual = $arrTabs.filter('.active');
//             var $indiceActual = $arrTabs.index($tabActual);
//             $('#btnAnterior').click();
//             var $activoNuevo = $arrTabs.filter('.active');
//             var $indiceNuevo = $arrTabs.index($activoNuevo);
//             return $indiceActual > $indiceNuevo;
//         }),"Al hacer click en anterior debe pasar al anterior tab");
//     });
//     //});
// });

// casper.test.begin("Agregar valor de contrato",6,function(test){
//     var cant_valor, cant_valor_nuevo;
//     casper.thenOpen('http://localhost:8080/MeziWeb/ContratoEdit');
//     casper.then(function(){
//         cant_valor = this.evaluate(function(){
//             $('#tabBasicos').click();
//             $('#btnValor').click();
//             return $('#tblValor tbody tr').size();
//         })
//         casper.waitUntilVisible('#mdlValor',function(){
//             test.assert(true,"Se muestra la ventana de alta de valor");
//         });
//         test.assert(this.evaluate(function(){
//             return $('#mdlValor h4.modal-title').text()=="Agregar valor";
//         }),"El titulo de la ventana es 'Agregar Valor'");

//         casper.then(function(){
//             var valor_fecha_fin= this.evaluate(function(){
//                 $('#valor_fecha_inicio').val("01/01/2016");
//                 $('#valor_meses').val(2);
//                 $('#valor_meses').trigger('focusout');
//                 $('#valor_importe').val(50);
//                 return $('#valor_fecha_fin').val();
//             });
//             test.assert(valor_fecha_fin=="29/02/2016","Se completa la fecha fin del valor de contrato");
//         });

//         casper.then(function(){
//             this.evaluate(function(){
//                 $('#btnGuardarValor').click();
//             });
//         });
//         casper.waitWhileVisible('#mdlValor',function(){
//             test.assert(true,"Se oculta la ventana de alta de valor");
//         });

//         casper.then(function(){
//             cant_valor_nuevo = this.evaluate(function(){
//                 return $('#tblValor tbody tr').size();
//             });
//             console.log(cant_valor, cant_valor_nuevo );
//             test.assert(cant_valor_nuevo== ( cant_valor+ 1),"Se agrega una linea a la tabla Valor de contrato");
//             // });
//         });

//         casper.then(function(){
//             var valores = this.evaluate(function(){
//                 var valores = [];
//                 $('#tblValor tbody tr td').each(function(i,e){valores.push($(e).text())});
//                 return valores;
//             });
//             test.assertEquals(valores,["01/01/2016","29/02/2016","50",""],"Los valores agregados son correctos");
//         });
//     });
// });
//*--------
casper.test.begin("Agregar valor incompleto de contrato",6,function(test){
    var cant_valor, cant_valor_nuevo;
    casper.thenOpen('http://localhost:8080/MeziWeb/ContratoEdit');
    casper.then(function(){
        this.evaluate(function(){
            spy = sinnon.spy(Bootbox,'alert');
        });
    });
    casper.then(function(){
        this.evaluate(function(){
            $('#tabBasicos').click();
            $('#btnValor').click();
        });
    });
    casper.waitForSelector('#mdlValor[style*="display: block"]',function(){
        test.assert(true,"Se muestra la ventana de alta de valor");
    });

        casper.then(function(){
            var callCount = this.evaluate(function(){
                $('#valor_fecha_inicio').val("01/01/2016");
                // $('#valor_meses').val(2);
                $('#valor_meses').trigger('focusout');
                // $('#valor_importe').val(50);
                $('#btnGuardarValor').click();
                console.log("Fuck this fucking thing");
                console.log(sinnon);
                return spy.callCount();
            });
            console.log("Call count " + callCount);
        });


        casper.waitForSelector('#mdlValor[style*="display: block"]',function(){
            test.assert(true,"No se oculta la ventana de alta de valor");
        });
    // });
});



// casper.test.begin("Agregar documento de contrato",6,function(test){
//     var cant_documento, cant_documento_nuevo;
//     casper.thenOpen('http://localhost:8080/MeziWeb/ContratoEdit');
//     casper.then(function(){
//         cant_documento = this.evaluate(function(){
//             $('#tabBasicos').click();
//             $('#btnDocumento').click();
//             return $('#tblDocumento tbody tr').size();
//         })
//         casper.waitForSelector('#mdlDocumento[style*="display: block"]',function(){
//             test.assert(true,"Se muestra la ventana de alta de documento");
//         });
//         test.assert(this.evaluate(function(){
//             return $('#mdlDocumento h4.modal-title').text()=="Agregar documento";
//         }),"El titulo de la ventana es 'Agregar documento'");

//         casper.then(function(){
//             var documento_fecha_fin= this.evaluate(function(){
//                 $('#documento_fecha_inicio').val("01/01/2016");
//                 $('#documento_meses').val(2);
//                 $('#documento_meses').trigger('focusout');
//                 $('#documento_importe').val(50);
//                 return $('#documento_fecha_fin').val();
//             });
//             test.assert(documento_fecha_fin=="29/02/2016","Se completa la fecha fin del documento de contrato");
//         });

//         casper.then(function(){
//             this.evaluate(function(){
//                 $('#btnGuardarDocumento').click();
//             });
//         });
//         casper.waitForSelector('#mdlDocumento[style*="display: none"]',function(){
//             test.assert(true,"Se oculta la ventana de alta de documento");
//         });

//         casper.then(function(){
//             cant_documento_nuevo = this.evaluate(function(){
//                 return $('#tblDocumento tbody tr').size();
//             });
//             console.log(cant_documento, cant_documento_nuevo );
//             test.assert(cant_documento_nuevo== ( cant_documento+ 1),"Se agrega una linea a la tabla Valor de contrato");
//             // });
//         });

//         casper.then(function(){
//             var documentos = this.evaluate(function(){
//                 var documentos = [];
//                 $('#tblDocumento tbody tr td').each(function(i,e){documentos.push($(e).text())});
//                 return documentos;
//             });
//             test.assertEquals(documentos,["01/01/2016","29/02/2016","50",""],"Los documentos agregados son correctos");
//         });
//     });
// });


casper.test.begin("Test suite ended",1,function(test){
    casper.then(function(){
        test.assert(true,"Test suite completed");
        test.done();
    });
});
casper.run(function(){
    // this.test.done();
    casper.exit();
});
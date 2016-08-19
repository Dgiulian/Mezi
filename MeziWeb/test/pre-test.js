casper.options.verbose = true;
// casper.options.logLevel = 'debug';
var utils = require('utils');
casper.on('remote.message',function(msg){
    console.debug('Remote msg: ' + JSON.stringify(msg));
});


// testUtils = {};
// testUtils.login = function(){
//     console.debug("Log in method");
//     casper.start("http://localhost:8080/MeziWeb/Login");
//     casper.waitForSelector('form.login',function(){
//         casper.fill('form',{
//             'email':'giuliani.diego@gmail.com',
//             'password':'linux2006'
//         });
//     });
//     casper.then(function(){
//         this.evaluate(function(){
//             $('button').click();
//         });
//     });
//     casper.then(function(){
//         console.debug(casper.getCurrentUrl());
//     });
//     casper.waitForSelector('.sidebar-nav',function(){
//         console.debug("Login successful! Starting the testsuite");
//         // test.assert(true,"Logged into the page");
//     });
// };
// console.debug("Pre test");
// module.exports = casper;
var  clientScripts =  [
    'sinon-1.17.3.js',
    // 'sinon-server-1.7.3.js'
];
// casper.test.setUp(function () {
    for (var i = 1;i<clientScripts.length;i++){
        // Check if script not already loaded?
        // Check if script exists?
        casper.options.clientScripts.push(clientScripts[i]);
    }
//     console.debug("Setup function");
//    //testUtils.login();

// });

casper.test.begin("Login to the App",1,function(test){
    casper.start("http://localhost:8080/MeziWeb/Login");
    casper.waitForSelector('form.login',function(){
        casper.fill('form',{
            'email':'giuliani.diego@gmail.com',
            'password':'linux2006'
        },true);
    });
    // casper.then(function(){
    //     this.evaluate(function(){
    //         $('button').click();
    //     });
    // });
    casper.then(function(){
        console.debug(casper.getCurrentUrl());
    });
    casper.waitForSelector('.sidebar-nav',function(){
        console.debug("Login successful! Starting the testsuite");
        test.assert(true,"Logged into the page");
    });

    casper.run(function(){
        test.done();
    });
});
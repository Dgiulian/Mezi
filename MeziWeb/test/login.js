casper.options.verbose = true;
casper.options.logLevel = 'debug';

var  clientScripts =  [
     'sinon-1.17.3.js',
//     'sinon-server-1.7.3.js'
];
casper.test.setUp(function () {
    for (var i = 1;i<clientScripts.length;i++){
        // Check if script not already loaded?
        // Check if script exists?
        casper.options.clientScripts.push(clientScripts[i]);
    }
});


casper.test.begin("Log into the page",1,function(test){
    casper.start("http://localhost:8080/MeziWeb/Login");
    casper.waitForSelector('form.login');
    casper.then(function(){
        casper.fill('form',{
            'email':'giuliani.diego@gmail.com',
            'password':'linux2006'
        });
        casper.click('button[type="submit"]');
    });
    casper.waitForSelector('.sidebar-nav')
    casper.then(function(){
        console.log("Login successful! Starting the testsuite");
    });
    casper.run(function(){
            test.done(); // Concludes the tests
//            casper.exit();
    });
});
<%@page import="utils.PathCfg"%>
<!-- start: Meta -->
        <meta charset="utf-8">

        <title><%=PathCfg.PAGE_TITLE%></title>
        <meta name="description" content="Mezi Inmobiliaria">
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
        <link rel="shortcut icon" href="assets/ico/favicon.png">
        <!-- end: Favicon and Touch Icons -->

    <style>
        legend{
            margin-bottom:5px;

        }
        .nopadding {
            padding: 0 !important;
            margin: 0 !important;
         }
         .numeric{
             text-align: right;
         }
         .box-header h3 {
            margin-left: 10px;
            margin-top: 5px;
            font-size: 20px;
            line-height: normal;
        }
        .box-header .nav-tabs li.active>a {
            background: #A9A8A8;
        }

        Flexible-container {
            position: relative;
            padding-bottom: 56.25%;
            padding-top: 30px;
            height: 0;
            overflow: hidden;
        }

        .Flexible-container iframe,
        .Flexible-container object,
        .Flexible-container embed {
            position: absolute;
            top: 0;
            left: 0;
            width: 100%;
            height: 100%;
        }
        #gmap{
            position:absolute;
            /*width:450px;*/
            width:100%;
            height:450px;
        }
    </style>
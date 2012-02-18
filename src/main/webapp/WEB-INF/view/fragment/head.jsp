<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<head>
    <meta http-equiv="Content-type" content="text/html; charset=utf-8">
    <title>Jo Ivie Arts</title>
    <link rel="stylesheet" href="<c:url value="/resources/css/basic.css" />" type="text/css"/>
    <link rel="stylesheet" href="<c:url value="/resources/css/galleriffic-5.css" />" type="text/css"/>
    <link rel='stylesheet' href='http://fonts.googleapis.com/css?family=Amatic+SC:400,700' type='text/css'>
    <!--[if gte IE 9]>
    <style type="text/css">
        .gradient {
            filter: none;
        }
    </style>
    <![endif]-->
    <!--[if gte IE 7]>
    <style type="text/css">
        body { padding-left: 50%; }
        #page {
            margin-left: -480px;
        }
    </style>
    <![endif]-->
    <script type="text/javascript" src="<c:url value="/resources/js/jquery.min.js" />"></script>
    <script type="text/javascript" src="<c:url value="/resources/js/jquery.history.js" />"></script>
    <script type="text/javascript" src="<c:url value="/resources/js/jquery.galleriffic.js" />"></script>
    <script type="text/javascript" src="<c:url value="/resources/js/jquery.opacityrollover.js" />"></script>
    <!-- We only want the thunbnails to display when javascript is disabled -->
    <script type="text/javascript">
        document.write('<style>.noscript { display: none; }</style>');
    </script>
</head>
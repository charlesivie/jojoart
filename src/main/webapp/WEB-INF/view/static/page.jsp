<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<jsp:include page="../fragment/head.jsp"/>

<body>

<div id="page">
    <div id="container">
        <div id="header">
            <h1><a href="/">Jo Ivie Arts</a></h1>
            <jsp:include page="/static/list"/>
        </div>

        <div class="left">
            <jsp:include page="/categories"/>
        </div>
        <div class="main">
            ${staticPage.htmlContent}
        </div>
        <div class="clear">&nbsp;</div>

    </div>
</div>
<jsp:include page="../fragment/footer.jsp"/>
</body>
</html>
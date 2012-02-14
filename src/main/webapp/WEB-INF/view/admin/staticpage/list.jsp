<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!--
Created by IntelliJ IDEA.
User: charlieivie
Date: 08/12/2011
Time: 14:20
To change this template use File | Settings | File Templates.
-->
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<html>
<head><title>Static Pages</title></head>
<body>

<div><h2>Pages</h2></div>
<div>
    <ul>
        <c:forEach items="${staticpages}" var="staticpage">
            <li>
                <a href="<c:url value="/admin/staticpage/${staticpage.path}"/>">
                        ${staticpage.htmlContent},
                        ${staticpage.active}</a>
            </li>
        </c:forEach>
    </ul>
</div>
<div>
    <a href="<c:url value="/admin/staticpage/0"/>">insert new static page</a>
</div>

</body>
</html>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head><title>Static Pages</title></head>
<body>

<div>
    <jsp:include page="../nav.jsp"/>
</div>

<div><h2>Pages</h2></div>
<div>
    <ul>
        <c:forEach items="${staticPages}" var="staticPage">
            <li>
                <a href="<c:url value="/admin/staticpage/${staticPage.path}"/>">
                        ${staticPage.name} -- active:${staticPage.active}
                </a>
            </li>
        </c:forEach>
    </ul>
</div>
<div>
    <a href="<c:url value="/admin/staticpage/0"/>">insert new static page</a>
</div>

</body>
</html>

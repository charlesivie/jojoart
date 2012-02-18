<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head><title>Images</title></head>
<body>

<div>
    <jsp:include page="../nav.jsp"/>
</div>

<div><h2>Images</h2></div>
<div>
    <ul>
        <c:forEach items="${images}" var="image">
            <li>
                <a href="<c:url value="/admin/image/${image.id}"/>">
                        ${image.name} -- active: ${image.active}
                </a>
            </li>
        </c:forEach>
    </ul>
</div>
<div>
    <a href="<c:url value="/admin/image/0"/>">insert new image</a>
</div>

</body>
</html>

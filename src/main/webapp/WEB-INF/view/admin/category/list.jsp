<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head><title>Categories</title></head>
<body>

<div>
    <jsp:include page="../nav.jsp"/>
</div>

<div><h2>Categories</h2></div>
<div>
    <ul>
        <c:forEach items="${categories}" var="category">
            <li>
                <a href="<c:url value="/admin/category/${category.id}" />">
                        ${category.name} -- active: ${category.active}
                </a>
            </li>
        </c:forEach>
    </ul>
</div>
<div>
    <a href="<c:url value="/admin/category/0" />">insert new category</a>
</div>

</body>
</html>
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
<head><title>Categories</title></head>
<body>

<div><h2>Categories</h2></div>
<div>
    <ul>
        <c:forEach items="${categories}" var="category">
            <li>
                <a href="<c:url value="/admin/category/${category.id}" />">
                    ${category.name},
                    ${category.description},
                    ${category.active},
                    ${category.defaultCategory}</a>
                ${category.image.name}
            </li>
        </c:forEach>
    </ul>
</div>
<div>
    <a href="<c:url value="/admin/category/0" />">insert new category</a>
</div>

</body>
</html>
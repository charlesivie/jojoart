<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<ul class="leftnav">
    <c:forEach items="${categories}" var="category">
        <li class="portrait">
            <a href="/art/index.html?categoryId=${category.id}">${category.description}</a>
        </li>
    </c:forEach>
</ul>

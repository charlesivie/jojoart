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
<head><title>Images</title></head>
<body>

<div><h2>Images</h2></div>
<div>
    <ul>
        <c:forEach items="${images}" var="image">
            <li>
                <a href="/art/image/${image.id}">
                    ${image.name},
                    ${image.description},
                    ${image.mimeType},
                    ${image.active}</a>
                ${image.category.name}
            </li>
        </c:forEach>
    </ul>
</div>
<div>
    <a href="/art/admin/image/0">insert new image</a>
</div>

</body>
</html>

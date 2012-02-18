<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<ul class="topnav">
    <c:forEach items="${staticPages}" var="staticPage">
        <li><a href="/static/${staticPage.path}">${staticPage.name}</a></li>
    </c:forEach>
</ul>
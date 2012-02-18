<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head><title>Admin Categories</title></head>
<body>

<div>
    <jsp:include page="../nav.jsp"/>
</div>

<div>
    <h2>${category.id>0?'Edit ':'Insert New '} Category</h2>
</div>

<div>
    <form:form commandName="category" method="POST">
        <form:hidden path="id"/>
        <ul>
            <li><form:label path="name"/><form:input path="name" label="name"/></li>
            <li><form:label path="description"/><form:input path="description" label="description"/></li>
            <li><form:label path="active"/><form:checkbox path="active" label="active"/></li>
            <li><form:label path="defaultCategory"/><form:checkbox path="defaultCategory" label="defaultCategory"/></li>
        </ul>
        <input type="submit" value="save"/>
    </form:form>
</div>

</body>
</html>

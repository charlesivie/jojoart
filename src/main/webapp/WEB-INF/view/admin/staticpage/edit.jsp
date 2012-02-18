<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head><title>Static Page</title></head>
<body>

<div>
    <jsp:include page="../nav.jsp"/>
</div>

<div>
    <h2>${staticpage.path != null ?'Edit ':'Insert New '} Page</h2>
</div>


<div>
    <form:form commandName="staticPage" method="POST" enctype="multipart/form-data">
        <form:hidden path="path"/>
        <form:hidden path="id"/>
        <ul>
            <li><form:label path="name">Name</form:label><form:input path="name" label="name"/></li>
            <li><form:label path="active">active?</form:label><form:checkbox path="active" label="active"/></li>
            <li><form:label path="htmlContent">Html Content</form:label><form:textarea path="htmlContent"/></li>
        </ul>
        <input type="submit" value="save"/>
    </form:form>
</div>
</body>
</html>


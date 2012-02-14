<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!--
Created by IntelliJ IDEA.
User: charlieivie
Date: 08/12/2011
Time: 14:20
To change this template use File | Settings | File Templates.
-->
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head><title>Static Page</title></head>
<body>

<div>
    <h2>${staticpage.path != null ?'Edit ':'Insert New '} Page</h2>
</div>

<div>
    <form:form commandName="staticpage" method="POST" enctype="multipart/form-data">
        <form:hidden path="id"/>
        <ul>
            <li><form:label path="path">Path</form:label><form:input path="path" label="path"/></li>
            <li><form:label path="active">active?</form:label><form:checkbox path="active" label="active"/></li>
            <li><form:label path="htmlContent">Html Content</form:label><form:input path="htmlContent"/></li>
        </ul>
        <input type="submit" value="save"/>
    </form:form>
</div>
</body>
</html>


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
<head><title>Simple jspx page</title></head>
<body>

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

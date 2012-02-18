<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head><title>Image</title></head>
<body>

<div>
    <jsp:include page="../nav.jsp"/>
</div>

<div>
    <h2>${image.id>0?'Edit ':'Insert New '} Image</h2>
</div>

<div>
    <form:form commandName="image" method="POST" enctype="multipart/form-data">
        <form:hidden path="id"/>
        <ul>
            <li><form:label path="name">Name</form:label><form:input path="name" label="name"/></li>
            <li><form:label path="description">Description</form:label><form:input path="description"
                                                                                   label="description"/></li>
            <li><form:label path="active">active?</form:label><form:checkbox path="active" label="active"/></li>
            <li>
                <form:label path="category">category</form:label>
                <form:select path="category">
                    <form:options items="${categories}" itemValue="id" itemLabel="name"/>
                </form:select>
            </li>
            <li>
                <form:input type="file" name="file" id="file" path="" />
            </li>
        </ul>
        <input type="submit" value="save"/>
    </form:form>
</div>

<c:if test="${image.id > 0}">
    <div>
        <img src="<c:url value="/image/${image.id}/thumbnail" />" alt="${image.name}"/>
    </div>
</c:if>

</body>
</html>


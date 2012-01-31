<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!--
Created by IntelliJ IDEA.
User: charlieivie
Date: 08/12/2011
Time: 14:20
To change this template use File | Settings | File Templates.
-->
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<ul class="thumbs noscript">

    <c:forEach items="${images}" var="image">
        <li>
            <a class="thumb" name="${image.name}" href="/art/image/${image.id}/normal" title="${image.name}">
                <img src="/art/image/${image.id}/thumbnail" alt="${image.name}"/>
            </a>

            <div class="caption">
                <div class="image-title">${image.name}</div>
                <div class="image-desc">${image.description}</div>
                <div class="download">
                    <a href="/art/image/${image.id}/large">Download Original</a>
                </div>
            </div>
        </li>
    </c:forEach>
</ul>

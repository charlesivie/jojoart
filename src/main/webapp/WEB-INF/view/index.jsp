<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<jsp:include page="fragment/head.jsp"/>
<body>

<div id="page">
    <div id="container">
        <div id="header">
            <h1><a href="/">Jo Ivie Arts</a></h1>
            <jsp:include page="/static/list"/>
        </div>

        <div class="left">
            <jsp:include page="/categories"/>
        </div>
        <div class="main">
            <!-- Start Advanced Gallery Html Containers -->

            <div class="content">
                <div class="slideshow-container">
                    <div id="controls" class="controls"></div>

                    <div id="loading" class="loader"></div>
                    <div id="slideshow" class="slideshow"></div>
                </div>
                <div id="caption" class="caption-container">
                    <div class="photo-index"></div>
                </div>
            </div>

            <div class="navigation-container">
                <div id="thumbs" class="navigation">

                    <a class="pageLink prev" style="visibility: hidden;" href="#" title="Previous Page"></a>

                    <jsp:include page="/images/${categoryId!=null?categoryId:0}"/>

                    <a class="pageLink next" style="visibility: hidden;" href="#" title="Next Page"></a></div>
            </div>
            <!-- End Gallery Html Containers -->
        </div>
        <div class="clear">&nbsp;</div>

    </div>
</div>
<jsp:include page="fragment/footer.jsp"/>
</body>
</html>
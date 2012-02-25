<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<jsp:include page="../fragment/head.jsp"/>

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
            <h2>Contact me...</h2>

            <div>
                <form name="input" action="<c:url value="/contact/sendmail" />" method="get">
                    <table cellpadding="0" cellspacing="0">
                        <tr>
                            <td>Your email address</td>
                            <td><input type="text" size="30" name="email"/></td>
                        </tr>
                        <tr>
                            <td>Your phone number</td>
                            <td><input type="text" size="30" name="phone"/></td>
                        </tr>
                        <tr>
                            <td>Subject</td>
                            <td><input type="text" size="30" name="subject"/></td>
                        </tr>
                        <tr>
                            <td>Message</td>
                            <td><textarea rows="10" cols="40" name="message"></textarea></td>
                        </tr>
                    </table>
                    <input type="submit" value="Send Message >>>"/>
                </form>
            </div>
        </div>
        <div class="clear">&nbsp;</div>

    </div>
</div>
<jsp:include page="../fragment/footer.jsp"/>
</body>
</html>
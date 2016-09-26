<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@ include file="/WEB-INF/pages/modules/work.jsp" %>
    <title><spring:message code="title_main"/></title>
</head>
<body>
<hgroup>
    <%@ include file="/WEB-INF/pages/modules/slogan.jsp" %>
</hgroup>
<nav>
    <%@ include file="/WEB-INF/pages/modules/menu/main_menu.jsp" %>
</nav>
<section id="bg" class="overlay">
    <c:choose>
        <c:when test="${sessionScope.client.id > 0}">
            <%@ include file="/WEB-INF/pages/modules/menu/profile_menu.jsp" %>
        </c:when>
        <c:when test="${sessionScope.client == null}">
            <%@ include file="/WEB-INF/pages/modules/forms/login_form.jsp" %>
        </c:when>
    </c:choose>
</section>
<inbody>
<div>
    <form action="get_password" method="POST">
            <h3><spring:message code="enter_forgot_password"/></h3>
        <h3><c:if test="${message_error_get_password ne null}"><spring:message code="message_error_get_password"/></c:if></h3>
            <input type="text" name="email"  value="name@gmail.com">
            <br/>
        <input type="submit" value="<spring:message code="get_password"/>" />
    </form>
</div>
</inbody>
</body>
</html>

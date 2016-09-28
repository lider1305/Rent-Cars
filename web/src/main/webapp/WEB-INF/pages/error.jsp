<%@ page contentType="text/html;charset=UTF-8" language="java"
         isErrorPage="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
    <%@ include file="/WEB-INF/pages/modules/work.jsp" %>
    <title><spring:message code="title_error"/></title>
</head>
<body>
<%@ include file="/WEB-INF/pages/modules/slogan.jsp" %>
<loc id="bg" class="login">
    <jsp:include page="/WEB-INF/pages/modules/menu/language_menu.jsp"/>
</loc>
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
    <h2>${exception_database}</h2>
    <h2>${error}</h2>
    <h2>${errorDatabase}</h2>
    <h2><c:if test="${service_exception ne null}"><spring:message code="${service_exception}"/></c:if></h2>
    <h2>${exception}</h2>
    <h2><c:if test="${exceptionMessage ne null}"><spring:message code="error_500"/></c:if></h2>
</inbody>
</body>
</html>

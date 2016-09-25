<%@ page contentType="text/html;charset=UTF-8" language="java"
         errorPage="/WEB-INF/pages/error.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
    <%@ include file="/WEB-INF/pages/modules/work.jsp" %>
    <title><spring:message code="title_main"/></title>
</head>
<body>
<loc id="bg" class="login">
    <jsp:include page="/WEB-INF/pages/modules/menu/language_menu.jsp"/>
</loc>
<%@ include file="/WEB-INF/pages/modules/slogan.jsp" %>
<nav>
    <%@ include file="/WEB-INF/pages/modules/menu/main_menu.jsp" %>
</nav>
<section id="bg" class="overlay">
    <c:choose>
        <c:when test="${sessionScope.client.role.name ne null}">
            <%@ include file="/WEB-INF/pages/modules/menu/profile_menu.jsp" %>
        </c:when>
        <c:when test="${sessionScope.client eq null}">
            <%@ include file="/WEB-INF/pages/modules/forms/login_form.jsp" %>
        </c:when>
    </c:choose>
</section>
<inbody>
    <h2><spring:message code="hello"/></h2>
    <h1>${greeting}</h1>
    <h2>${error}</h2>
    <h3><c:if test="${wrong_login ne null}"><spring:message code="message_error_login"/></c:if></h3>
    <h2><c:if test="${reg ne null}"><spring:message code="message_success_registry"/></c:if></h2>
    <h2>${session_close}</h2>
</inbody>
</body>
</html>

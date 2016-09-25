<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@ include file="/WEB-INF/pages/modules/work.jsp" %>
    <title><fmt:message key="title_main"/></title>
</head>
<body>
<loc id="bg" class="login">
    <jsp:include page="/WEB-INF/pages/modules/menu/language_menu.jsp"/>
</loc>
<hgroup>
    <%@ include file="/WEB-INF/pages/modules/slogan.jsp" %>
</hgroup>
<nav>
    <%@ include file="/WEB-INF/pages/modules/menu/client_menu.jsp" %>
</nav>
<section id="bg" class="overlay">
    <div>
        <%@ include file="/WEB-INF/pages/modules/menu/profile_menu.jsp" %>
    </div>
</section>
<inbody>
   <h2><spring:message code="hello_user"/> ${client.name}  ${client.surname}</h2>
    <h3><c:if test="${success ne null}"><spring:message code="message_success_order"/></c:if></h3>
    <h3><c:if test="${service_exception ne null}"><spring:message code="${service_exception}"/></c:if></h3>
    <p></p>
   <div> <table width="100%" border="1" class="table">
        <tr>
            <td><spring:message code="client_name"/></td>
            <td><spring:message code="client_sur_name"/></td>
            <td><spring:message code="client_email"/></td>
            <td><spring:message code="client_phone"/></td>
            <td><spring:message code="client_passport"/></td>
            <td><spring:message code="client_passport_start"/></td>
            <td><spring:message code="client_passport_end"/></td>
        </tr>
       <tr>
            <td>${client.name}</td>
            <td>${client.surname}</td>
            <td>${client.email}</td>
            <td>${client.phone}</td>
            <td>${client.passports.passport}</td>
            <td><fmt:formatDate value="${client.passports.passportIssueDate}" /></td>
            <td><fmt:formatDate value="${client.passports.passportEndDate}" /></td>
        </tr>
    </table>
   </div>
</inbody>
</body>
</html>

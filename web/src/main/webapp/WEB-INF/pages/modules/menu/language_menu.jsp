<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<c:set var="user" value="USER"/>
<c:set var="admin" value="ADMIN"/>
<c:choose>
    <c:when test="${sessionScope.client.role.name eq user}">
<div><spring:message code="choose_language"/> <a href="?locale=ru" id="locale_ru">RU</a><a href="?locale=en" id="locale_en">| EN</a>
</div>
    </c:when>
    <c:when test="${sessionScope.client.role.name eq admin}">
        <div><spring:message code="choose_language"/> <a href="?locale=ru" id="locale_ru">RU</a><a href="?locale=en" id="locale_en">| EN</a>
        </div>
    </c:when>
</c:choose>
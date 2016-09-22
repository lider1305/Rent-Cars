<%@ page contentType="text/html;charset=UTF-8" language="java"
         errorPage="/WEB-INF/pages/error.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>
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
<hgroup>
    <%@ include file="/WEB-INF/pages/modules/slogan.jsp" %>
</hgroup>
<nav>
    <c:choose>
        <c:when test="${sessionScope.client.id > 0}">
            <%@ include file="/WEB-INF/pages/modules/menu/client_menu.jsp" %>
        </c:when>
        <c:when test="${sessionScope.client == null}">
            <%@ include file="/WEB-INF/pages/modules/menu/main_menu.jsp" %>
        </c:when>
    </c:choose>
</nav>
<section id="bg" class="overlay">
    <c:choose>
        <c:when test="${client.id > 0}">
            <%@ include file="/WEB-INF/pages/modules/menu/profile_menu.jsp" %>
        </c:when>
        <c:when test="${client == null}">
            <%@ include file="/WEB-INF/pages/modules/forms/login_form.jsp" %>
        </c:when>
    </c:choose>
</section>
<inbody>
    <h2>Фильтр</h2>
    <div>
        <form method="POST" action="get_cars_by_filter">
            <jsp:include page="/WEB-INF/pages/modules/filters/cars_filter.jsp"/>
            <input type="submit" value="Применить"/>

            <table>
                <h2><spring:message code="list_of_all_cars"/></h2>
                <tr>
                    <td width="15%"><spring:message code="auto_brand"/></td>
                    <td width="15%"><spring:message code="auto_model"/></td>
                    <td width="10%"><spring:message code="auto_body_type"/></td>
                    <td width="20%"><spring:message code="auto_engine_type"/></td>
                    <td width="20%"><spring:message code="auto_transmission"/></td>
                    <td width="10%"><spring:message code="auto_year"/></td>
                    <td width="10%"><spring:message code="auto_amount"/></td>
                </tr>
                <c:forEach var="car" items="${cars}">
                <tr>
                    <td colspan="7"></td>
                </tr>
                <tr>
                    <td width="15%"><c:out value="${car.brand.brandName}"/></td>
                    <td width="15%"><c:out value="${car.model}"/></td>
                    <td width="10%"><c:out value="${car.bodyType.bodyType}"/></td>
                    <td width="20%"><c:out value="${car.engineType.engineType}"/></td>
                    <td width="20%"><c:out value="${car.transmissionType.transmissionType}"/></td>
                    <td width="10%"><c:out value="${car.yearOfManufacture}"/></td>
                    <td width="10%"><c:out value="${car.amount} y.e."/></td>
                </tr>
                </c:forEach>
                <tr>  
                    <td colspan="7"></td>
                </tr>
            </table>
            <jsp:include page="/WEB-INF/pages/modules/pagination/pagination.jsp"/>
        </form>
    </div>
</inbody>
</body>
</html>

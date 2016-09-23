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
    <h3><c:if test="${message_wrong_param ne null}"><spring:message code="message_no_chosen"/></c:if></h3>
    <h3>${exception_null_date}</h3>
    <h3>${exception_wrong_date}</h3>
    <h3>${exception_wrong_date_end}</h3>
    <div>
        <form method="GET" action="get_cars_by_filter">
            <jsp:include page="/WEB-INF/pages/modules/filters/cars_filter.jsp"/>
            <input type="submit" value="Применить"/>
        </form>

        <form method="get" action="check_car">
            <table>
                <h2><spring:message code="list_of_all_cars"/></h2>
                <tr>
                    <td width="15%"><spring:message code="auto_brand"/></td>
                    <td width="15%"><spring:message code="auto_model"/></td>
                    <td width="10%"><spring:message code="auto_body_type"/></td>
                    <td width="15%"><spring:message code="auto_engine_type"/></td>
                    <td width="15%"><spring:message code="auto_transmission"/></td>
                    <td width="10%"><spring:message code="auto_year"/></td>
                    <td width="10%"><spring:message code="auto_amount"/></td>
                    <td width="10%">Выбрать</td>
                </tr>
                <c:forEach var="car" items="${cars}">
                    <tr>
                        <td colspan="8"></td>
                    </tr>
                    <tr>
                        <td width="15%"><c:out value="${car.brand.brandName}"/></td>
                        <td width="15%"><c:out value="${car.model}"/></td>
                        <td width="10%"><c:out value="${car.bodyType.bodyType}"/></td>
                        <td width="15%"><c:out value="${car.engineType.engineType}"/></td>
                        <td width="15%"><c:out value="${car.transmissionType.transmissionType}"/></td>
                        <td width="10%"><c:out value="${car.yearOfManufacture}"/></td>
                        <td width="10%"><c:out value="${car.amount} y.e."/></td>
                        <td width="10%"><input type="radio" name="carId" value="${car.id}"/></td>
                    </tr>
                </c:forEach>
                <tr>
                    <td colspan="8"></td>
                </tr>
            </table>
            <br/>
            <table align="center" border="0">
                <tr>
                    <td width="20%"><spring:message code="date_start_of_rent"/>:</td>
                    <td width="20%"><spring:message code="date_end_of_rent"/>:</td>
                    <td> Сообщение о статусе автомобиля</td>
                </tr>
                <tr>
                    <td width="20%"><input type="text" readonly="readonly" name="startDate" class="tcal" value=""/></td>
                    <td width="20%"><input type="text" readonly="readonly" name="endDate" class="tcal" value=""/></td>
                    <td><c:if test="${car_status ne null}">${car_status} <spring:message code="reserved"/></c:if>
                        <c:if test="${car_status_free ne null}">${car_status_free} <spring:message code="free"/></c:if></td>
                </tr>
            </table>
            <input type="submit" value="Проверить"/>
            <jsp:include page="/WEB-INF/pages/modules/pagination/pagination.jsp"/>
        </form>
    </div>
</inbody>
</body>
</html>

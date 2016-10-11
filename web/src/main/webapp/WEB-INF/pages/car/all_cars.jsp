<%@ page contentType="text/html;charset=UTF-8" language="java"
         errorPage="/WEB-INF/pages/error.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags/form" %>
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
    <h2><spring:message code="filter"/></h2>
    <h3><c:if test="${service_exception ne null}"><spring:message code="${service_exception}"/></c:if></h3>
    <div>
        <form method="GET" action="get_cars_by_filter">
            <jsp:include page="/WEB-INF/pages/modules/filters/cars_filter.jsp"/>
            <input type="submit" value="<spring:message code="apply"/>"/>
        </form>

        <s:form method="GET" action="check_car" modelAttribute="orderDTO">
            <table class="table">
                <h2><spring:message code="list_of_all_cars"/></h2>
                <tr>
                    <td width="15%"><spring:message code="auto_brand"/></td>
                    <td width="15%"><spring:message code="auto_model"/></td>
                    <td width="10%"><spring:message code="auto_body_type"/></td>
                    <td width="15%"><spring:message code="auto_engine_type"/></td>
                    <td width="15%"><spring:message code="auto_transmission"/></td>
                    <td width="10%"><spring:message code="auto_year"/></td>
                    <td width="10%"><spring:message code="auto_amount"/></td>
                    <td width="10%"><spring:message code="choose"/></td>
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
                        <td width="10%"><s:radiobutton path="carId" name="carId" value="${car.id}"/></td>
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
                    <td> <spring:message code="message_status_car"/></td>
                </tr>
                <tr>
                    <input type="hidden" name="brand" value="${filterBrand.id}"/>
                    <input type="hidden" name="body_type" value="${filterBody.id}"/>
                    <input type="hidden" name="engine_type" value="${filterEngine.id}"/>
                    <input type="hidden" name="transmission_type" value="${filterTransmission.id}"/>
                    <input type="hidden" name="yearFrom" value="${filterYearFrom}"/>
                    <input type="hidden" name="yearTo" value="${filterYearTo}"/>
                    <input type="hidden" name="amountFrom" value="${filterAmountFrom}"/>
                    <input type="hidden" name="amountTo" value="${filterAmountTo}"/>
                    <input type="hidden" name="start" value="${counter}"/>
                    <td><s:input type="text" readonly="readonly" path="startDate" name="startDate" class="tcal" value=""/>
                        <s:errors path="startDate" cssClass="error-validation"/> </td>
                    <td><s:input type="text" readonly="readonly" path="endDate" name="endDate" class="tcal" value=""/>
                        <s:errors path="endDate" cssClass="error-validation"/></td>
                    <td><c:if test="${car_status ne null}"><spring:message code="${car_status}"/></c:if></td>
                </tr>
            </table>
            <input type="submit" value="<spring:message code="check"/>"/>
        </s:form>
        <jsp:include page="/WEB-INF/pages/modules/pagination/pagination.jsp"/>
    </div>
</inbody>
</body>
</html>
<%@ page contentType="text/html;charset=UTF-8" language="java"
         errorPage="/WEB-INF/pages/error.jsp"%>
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
    <%@ include file="/WEB-INF/pages/modules/menu/client_menu.jsp" %>
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
    <c:choose>
        <c:when test="${client.id > 0}">
            <h2>Изменить заказ</h2>
            <h3>${message_wrong_param}</h3>
            <h3>${exception_null_date}</h3>
            <h3>${message_error_get}</h3>
            <h3>${message_error_update}</h3>
            <h3>${car_is_rent_on_this_date}</h3>
            <h3>${exception_wrong_date}</h3>
            <h3>${exception_wrong_date_end}</h3>
            <form method="POST" action="get_cars_by_filter">
                <jsp:include page="/WEB-INF/pages/modules/filters/cars_filter.jsp"/>
                <input type="submit" value="Выбрать автомобиль"/>
            </form>

            <form method="POST" action="edit_order">
                <table>
                    <tr>
                        <td width="15%"><spring:message code="auto_brand"/></td>
                        <td width="15%"><spring:message code="auto_model"/></td>
                        <td width="10%"><spring:message code="auto_body_type"/></td>
                        <td width="15%"><spring:message code="auto_engine_type"/></td>
                        <td width="15%"><spring:message code="auto_transmission"/></td>
                        <td width="10%"><spring:message code="auto_year"/></td>
                        <td width="10%"><spring:message code="auto_amount"/></td>
                        <td width="10%">Отметить</td>
                    </tr>
                    <c:forEach var="car" items="${cars}">
                        <tr>
                            <td colspan="7"></td>
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
                <table>
                    <tr>
                        <td width="15%"><c:out value="${orderEdit.car.brand.brandName}"/></td>
                        <td width="15%"><c:out value="${orderEdit.car.model}"/></td>
                        <td width="10%"><c:out value="${orderEdit.car.bodyType.bodyType}"/></td>
                        <td width="15%"><c:out value="${orderEdit.car.engineType.engineType}"/></td>
                        <td width="15%"><c:out value="${orderEdit.car.transmissionType.transmissionType}"/></td>
                        <td width="10%"><c:out value="${orderEdit.car.yearOfManufacture}"/></td>
                        <td width="10%"><c:out value="${orderEdit.car.amount} y.e."/></td>
                        <td width="10%"><input type="hidden" name="orderId" value="${orderEdit.id}"/></td>
                    </tr>
                    <tr>
                        <td colspan="7"></td>
                    </tr>
                </table>
                <jsp:include page="/WEB-INF/pages/modules/pagination/pagination.jsp"/>
                <br/>
                <table>
                    <tr>
                        <td><spring:message code="date_start_of_rent"/>:</td>
                        <td><spring:message code="date_end_of_rent"/>:</td>
                        <td><spring:message code="other_info"/>:</td>
                    </tr>
                    <tr>
                        <td><input type="text" readonly="readonly" name="startDate" class="tcal" value="${orderEdit.startDate}"/></td>
                        <td><input type="text" readonly="readonly" name="endDate" class="tcal" value="${orderEdit.endDate}"/></td>
                        <td><input type="text" name="message_for_order" value="${orderEdit.message}" size="20"/></td>
                    </tr>
                </table>
                <input type="submit" value="Изменить заказ"/>
            </form>
            </div>
        </c:when>
        <c:when test="${client == null}">
            <h2><fmt:message key="message_for_make_order"/></h2>
        </c:when>
    </c:choose>
</inbody>
</body>
</html>

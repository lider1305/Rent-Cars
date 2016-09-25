<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java"
         errorPage="/WEB-INF/pages/error.jsp" %>
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
    <%@ include file="/WEB-INF/pages/modules/menu/admin_menu.jsp" %>
</nav>
<section id="bg" class="overlay">
    <div>
        <%@ include file="/WEB-INF/pages/modules/menu/profile_menu.jsp" %>
    </div>
</section>
<inbody>
    <h2><spring:message code="process_order"/></h2>
    <div>
       <h2><c:if test="${message_error_get_orders ne null}"><spring:message code="error_get_orders"/></c:if></h2>
       <h2><c:if test="${service_exception ne null}"><spring:message code="${service_exception}"/></c:if></h2>
        <form  method="POST" action="process_order" >
            <input type="hidden" name="orderId" value="${order.id}"/>
            <table>
                <tr>
                    <td width="5%"><spring:message code="client_id"/></td>
                    <td width="10%"><spring:message code="client_name"/></td>
                    <td width="10%"><spring:message code="client_sur_name"/></td>
                    <td width="10%"><spring:message code="auto_brand"/></td>
                    <td width="10%"><spring:message code="auto_model"/></td>
                    <td width="10%"><spring:message code="order_start_date"/></td>
                    <td width="10%"><spring:message code="order_end_date"/></td>
                    <td width="15%"><spring:message code="other_info"/></td>
                    <td width="10%"><spring:message code="order_status"/></td>
                    <td width="10%"><spring:message code="process_order"/></td>
                </tr>
            </table>
                <table>
                    <tr><td colspan="9"> </td></tr>
                    <tr>
                        <td width="5%"><c:out value="${order.id}"/></td>
                        <td width="10%"><c:out value="${order.client.name}"/></td>
                        <td width="10%"><c:out value="${order.client.surname}"/></td>
                        <td width="10%"><c:out value="${order.car.brand.brandName}"/></td>
                        <td width="10%"><c:out value="${order.car.model}"/></td>
                        <td width="10%"><fmt:formatDate value="${order.startDate}" /></td>
                        <td width="10%"><fmt:formatDate value="${order.endDate}"/></td>
                        <td width="15%"><c:out value="${order.message}"/></td>
                        <td width="10%"><c:out value="${order.orderStatus.status}"/></td>
                        <td width="10%"><select name="statusId" class="input">
                        <c:forEach var="status" items="${statusOfOrder}">
                            <option value="<c:out value="${status.id}"/>"><c:out value="${status.status}"/></option>
                        </c:forEach>
                    </select></td>
                    </tr>
                </table>
            <input type="submit" value="<spring:message code="processing"/>" />
        </form>
    </div>
</inbody>
</body>
</html>


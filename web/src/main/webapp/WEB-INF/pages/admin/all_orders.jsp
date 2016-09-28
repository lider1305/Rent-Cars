<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java"
         errorPage="/WEB-INF/pages/error.jsp"%>
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
    <h2><spring:message code="all_orders"/></h2>
    <div>
        <h3><c:if test="${service_exception ne null}"><spring:message code="${service_exception}"/></c:if></h3>
        <form  method="GET" action="get_all_orders" >
            <jsp:include page="/WEB-INF/pages/modules/pagination/per_page.jsp"/>
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
            <c:forEach var="order" items="${ordersAdmin}" >
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
                        <c:choose>
                            <c:when test="${order.orderStatus.id >= 1 and order.orderStatus.id <= 3}">
                                <td width="10%"><a href="${pageContext.servletContext.contextPath}/process_order?orderId=${order.id}"><img
                                        src="${pageContext.servletContext.contextPath}/resources/images/status_order.png"></a></td>
                            </c:when>
                            <c:otherwise>
                                <td width="10%"></td>
                            </c:otherwise>
                        </c:choose>
                    </tr>
                </table>
            </c:forEach>
            <input type="submit" value="<spring:message code="button_show"/>" />
            <jsp:include page="/WEB-INF/pages/modules/pagination/pagination_all_order.jsp"/>
        </form>
    </div>
</inbody>
</body>
</html>

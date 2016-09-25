<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java"  errorPage="/WEB-INF/pages/error.jsp"%>
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
    <h2><spring:message code="menu_add_car"/></h2>
    <h2><c:if test="${exception_null_model ne null}"><spring:message code="message_null_model"/></c:if></h2>
    <h2><c:if test="${message_error_save_order ne null}"><spring:message code="error_save_object"/></c:if></h2>
    <h2><c:if test="${service_exception ne null}"><spring:message code="${service_exception}"/></c:if></h2>
    <div>
        <s:form method="POST" action="add_car" modelAttribute="carAll" onsubmit="validateAddCAr()">

            <table>
                <tr>
                    <td width="15%"><spring:message code="auto_brand"/></td>
                    <td width="20%"><spring:message code="auto_model"/></td>
                    <td width="10%"><spring:message code="auto_body_type"/></td>
                    <td width="15%"><spring:message code="auto_engine_type"/></td>
                    <td width="20%"><spring:message code="auto_transmission"/></td>
                    <td width="10%"><spring:message code="auto_year"/></td>
                    <td width="10%"><spring:message code="auto_amount"/></td>
                </tr>
            </table>
            <table>
                    <tr><td colspan="7"> </td></tr>
                    <tr>
                        <td width="15%"><s:select name="brand" path="brand" class="input">
                            <c:forEach var="brands" items="${all_brands}" >
                            <s:option value="${brands.id}"><c:out value="${brands.brandName}"/></s:option>
                            </c:forEach>
                        </s:select></td>
                        <td width="20%"> <s:errors path="model"  cssClass="error-validation"/><br/>
                            <s:input type="text" name="model" id="model" path="model" value="" size="10"/>
                           </td>
                        <td width="10%"><s:select name="body_type" path="bodyType" class="input">
                            <c:forEach var="body" items="${all_body_types}" >
                                <option value="<c:out value="${body.id}"/>"><c:out value="${body.bodyType}"/></option>
                            </c:forEach>
                        </s:select></td>
                        <td width="15%"><s:select name="engine_type" path="engineType" class="input">
                            <c:forEach var="engine" items="${all_engine_types}" >
                                <option value="<c:out value="${engine.id}"/>"><c:out value="${engine.engineType}"/></option>
                            </c:forEach>
                        </s:select></td>
                        <td width="20%"><s:select name="transmission_type" path="transmissionType" class="input">
                            <c:forEach var="transmission" items="${all_transmission_types}" >
                                <option value="<c:out value="${transmission.id}"/>"><c:out value="${transmission.transmissionType}"/></option>
                            </c:forEach>
                        </s:select></td>
                        <td width="10%"><s:input type="number" path="yearOfManufacture" name="yearOfManufacture" class="input" min="2008" max="2016" step="1" value="2016" size="10"/></td>
                        <td width="10%"><s:input type="number" path="amount" name="amount" class="input" min="0" max="100" step="1" value="5" size="10"/></td>
                    </tr>
                </table>
            <input type="submit" value="<spring:message code="button_add_car"/>" />
        </s:form>
        <script>
            function validateAddCAr() {
                var model = document.getElementById("model");

                if(!model.value) {
                    model.style.border = "2px solid red";
                    alert("<spring:message code="message_null_model"/>");
                    return false;
                }
            }
        </script>
    </div>
</inbody>
</body>
</html>

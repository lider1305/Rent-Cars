<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<table width="100%" border="1" >
    <tr>
        <td><spring:message code="sort_by"/></td>
        <td><spring:message code="ascending"/></td>
        <td><spring:message code="decending"/></td>
        <td><spring:message code="by_type"/></td>
        <td><spring:message code="records_number"/></td>
        <td><spring:message code="reset"/></td>
    </tr>
    <tr>
        <td></td>
        <td><input type="radio" name="sort_type"  value="true" checked></td>
        <td> <input type="radio" name="sort_type" value="false"></td>
        <td><select name="sort_name" class="input">
            <option value="brand"><spring:message code="brand"/></option>
            <option value="bodyType"><spring:message code="auto_body_type"/></option>
            <option value="engineType"><spring:message code="auto_engine_type"/></option>
            <option value="transmissionType"><spring:message code="auto_transmission"/></option>
            <option value="amount"><spring:message code="amount"/></option>
            <option value="yearOfManufacture"><spring:message code="year"/></option>
        </select></td>
        <td><jsp:include page="/WEB-INF/pages/modules/pagination/per_page.jsp"/></td>
        <td><a href="${pageContext.servletContext.contextPath}/${command}?perPages=5&start=1&brand=&body_type=&engine_type=&transmission_type=&amountFrom=&amountTo=&yearFrom=&yearTo=&sort_type=true&sort_name=brand"
               class="btn btn-secondary" role="button" aria-pressed="true"><h3><spring:message code="clean"/></h3></a>
        </td>
    </tr>
    <tr>
        <td colspan="6"></td>
    </tr>
</table>
<table>
    <tr>
        <td width="15%"><spring:message code="manufacturer"/></td>
        <td width="15%"><spring:message code="body_type"/></td>
        <td width="15%"><spring:message code="fuel"/></td>
        <td width="15%"><spring:message code="transmission_type"/></td>
        <td width="10%"><spring:message code="year_from"/></td>
        <td width="10%"><spring:message code="year_to"/></td>
        <td width="10%"><spring:message code="amount_from"/></td>
        <td width="10%"><spring:message code="amount_to"/></td>
    </tr>
    <tr>
        <td colspan="8"></td>
    </tr>
    <tr>
        <td width="15%"><select name="brand" class="input">
            <option value="${filterBrand.id}">${filterBrand.brandName} </option>
            <c:forEach var="brands" items="${all_brands}">
                <option value="<c:out value="${brands.id}"/>"><c:out value="${brands.brandName}"/></option>
            </c:forEach>
        </select></td>
        <td width="15%"><select name="body_type" class="input">
            <option value="${filterBody.id}">${filterBody.bodyType}</option>
            <c:forEach var="body" items="${all_body_types}">
                <option value="<c:out value="${body.id}"/>"><c:out value="${body.bodyType}"/></option>
            </c:forEach>
        </select></td>
        <td width="15%"><select name="engine_type" class="input">
            <option value="${filterEngine.id}">${filterEngine.engineType} </option>
            <c:forEach var="engine" items="${all_engine_types}">
                <option value="<c:out value="${engine.id}"/>"><c:out value="${engine.engineType}"/></option>
            </c:forEach>
        </select></td>
        <td width="15%"><select name="transmission_type" class="input">
            <option value="${filterTransmission.id}">${filterTransmission.transmissionType} </option>
            <c:forEach var="transmission" items="${all_transmission_types}">
                <option value="<c:out value="${transmission.id}"/>"><c:out
                        value="${transmission.transmissionType}"/></option>
            </c:forEach>
        </select></td>
        <td width="10%"><input type="number" name="yearFrom" class="input" min="2008" max="2016" step="1"
                               value="${filterYearFrom}" size="10"/></td>
        <td width="10%"><input type="number" name="yearTo" class="input" min="2008" max="2016" step="1"
                               value="${filterYearTo}" size="10"/></td>
        <td width="10%"><input type="number" name="amountFrom" class="input" min="0" max="100" step="1"
                               value="${filterAmountFrom}" size="10"/></td>
        <td width="10%"><input type="number" name="amountTo" class="input" min="0" max="100" step="1"
                               value="${filterAmountTo}" size="10"/></td>
    </tr>
</table>

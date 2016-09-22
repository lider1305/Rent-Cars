<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<table width="100%" border="1">
    <tr>
        <td>Сортировать по</td>
        <td>возрастанию</td>
        <td>убыванию</td>
        <td>типу</td>
        <td>Колличество записей</td>
        <td>Сбросить настройки</td>
    </tr>
    <tr>
        <td></td>
        <td><input type="radio" name="sort_type"  value="true" checked></td>
        <td> <input type="radio" name="sort_type" value="false"></td>
        <td><select name="sort_name">
            <option value="brand">производитель</option>
            <option value="bodyType">кузов</option>
            <option value="engineType">топливо</option>
            <option value="transmissionType">трансмиссии</option>
            <option value="amount">стоимости</option>
            <option value="yearOfManufacture">году выпуска</option>
        </select></td>
        <td><jsp:include page="/WEB-INF/pages/modules/pagination/per_page.jsp"/></td>
        <td><a href="${pageContext.servletContext.contextPath}/${command}?perPages=5&start=1&brand=&body_type=&engine_type=&transmission_type=&amountFrom=&amountTo=&yearFrom=&yearTo=&sort_type=true&sort_name=brand "><h3 class="button">Очистить</h3></a></td>
    </tr>
    <tr>
        <td colspan="6"></td>
    </tr>
</table>
<table>
    <tr>
        <td width="15%">Производитель</td>
        <td width="15%">Тип кузова</td>
        <td width="15%">Вид топлива</td>
        <td width="15%">Тип трансмиссии</td>
        <td width="10%">Год от</td>
        <td width="10%">Год до</td>
        <td width="10%">Стоимость от</td>
        <td width="10%">Стоимость до</td>
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

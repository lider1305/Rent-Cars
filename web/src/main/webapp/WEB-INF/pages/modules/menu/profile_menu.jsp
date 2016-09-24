<ul id="menu1" class="client-menu">
    <c:set var="user" value="USER"/>
    <c:set var="admin" value="ADMIN"/>
    <c:choose>
        <c:when test="${sessionScope.client.role.name eq admin}">
            <li><a href="${pageContext.servletContext.contextPath}/admin/main" id="admin"> <span class="wrap"> <span
                    class="link"><spring:message code="personal_page"/></span></span> </a></li>
        </c:when>
        <c:when test="${sessionScope.client.role.name eq user}">
            <li><a href="${pageContext.servletContext.contextPath}/client/client" id="user"> <span class="wrap"> <span
                    class="link"><spring:message code="personal_page"/></span></span> </a></li>
        </c:when>
    </c:choose>
    <li><a href="${pageContext.servletContext.contextPath}/go_to_edit_client" id="rewrite"> <span class="wrap"> <span
            class="link"><spring:message code="edit_profile"/></span></span> </a></li>
    <li><a href="${pageContext.servletContext.contextPath}/logout" id="logout"><span class="wrap"> <span
            class="link"><spring:message code="logout_client"/></span> </span> </a></li>
</ul>

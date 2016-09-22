<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<nav aria-label="Page navigation">
    <ul class="pagination">
        <c:if test="${totalPage<=4}" >
            <c:set var="end" value="${totalPage}"/>
            <c:set var="start" value="1"/>
        </c:if>
        <c:if test="${totalPage > 4}" >
            <c:set var="end" value="${totalPage}"/>
        </c:if>
        <c:if test="${counter > 1}">
            <li>
                <a href="${pageContext.servletContext.contextPath}/${command}?start=${counter - 1}&perPages=${perCount}&sort_type=${sort_type}&sort_name=${sort_name}" aria-label=" < ">
                    <span aria-hidden="true">&laquo;</span>
                </a>
            </li>
        </c:if>
        <c:forEach begin="1" end="${end}" var="i">
            <c:choose>
                <c:when test="${counter == i}">
                    <li class="active"><a href="#">${i}<span class="sr-only"></span></a></li>
                </c:when>
                <c:otherwise>
                    <li><a href="${pageContext.servletContext.contextPath}/${command}?start=${i}&perPages=${perCount}&sort_type=${sort_type}&sort_name=${sort_name}">${i}</a></li>
                </c:otherwise>
            </c:choose>
        </c:forEach>
        <c:if test="${counter < totalPage}">
            <li>
                <a href="${pageContext.servletContext.contextPath}/${command}?start=${counter + 1}&perPages=${perCount}&sort_type=${sort_type}&sort_name=${sort_name}" aria-label=" > ">
                    <span aria-hidden="true">&raquo;</span>
                </a>
            </li>
        </c:if>
    </ul>
</nav>

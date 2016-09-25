<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@ include file="/WEB-INF/pages/modules/work.jsp" %>
    <title><spring:message code="title_main"/></title>
</head>
<body>
<hgroup>
    <%@ include file="/WEB-INF/pages/modules/slogan.jsp" %>
</hgroup>
<nav>
    <%@ include file="/WEB-INF/pages/modules/menu/main_menu.jsp" %>
</nav>
<section id="bg" class="overlay">
    <c:choose>
        <c:when test="${sessionScope.client.id gt 0}">
            <%@ include file="/WEB-INF/pages/modules/menu/profile_menu.jsp" %>
        </c:when>
        <c:when test="${sessionScope.client eq null}">
            <%@ include file="/WEB-INF/pages/modules/forms/login_form.jsp" %>
        </c:when>
    </c:choose>
</section>
<inbody>
    <div>
        <h2><c:if test="${service_exception ne null}"><spring:message code="${service_exception}"/></c:if></h2>
        <s:form method="POST" action="new_user" modelAttribute="client" onsubmit="validateRegistration()">
            Введите ваши данные:<br/><br/>
            <spring:message code="client_name"/>: *<br/>
            <s:input type="text" name="name" path="name" value="" size="20"/>
            <s:errors path="name" cssClass="error-validation"/><br/>

            <spring:message code="client_sur_name"/>: *<br/>
            <s:input type="text" name="surname" path="surname" id="surname" value="" size="20"/>
            <s:errors path="surname" cssClass="error-validation"/><br/>

            <spring:message code="client_email"/>: *<br/>
            <s:input type="text" name="email" path="email" id="email" value="" size="20"/>
            <s:errors path="email" cssClass="error-validation"/><br/>

            <spring:message code="client_password"/>: *<br/>
            <s:input type="text" name="password" path="password" id="password" value="" size="20"/>
            <s:errors path="password" cssClass="error-validation"/><br/>

            <spring:message code="client_phone"/>: *<br/>
            <s:input type="text" name="phone" path="phone" id="phone" value="" size="20"/>
            <s:errors path="phone" cssClass="error-validation"/><br/>

            <spring:message code="client_passport"/>: *<br/>

            <s:input type="text" name="passport" path="passports.passport" id="passport" value="" size="20"/>
            ${passport_error}<br/><br/>

            <spring:message code="client_passport_start"/>: *<br/>
            <s:input type="text" name="passportIssueDate" readonly="true" path="passports.passportIssueDate" id="passportIssueDate" class="tcal" value=""/>
            ${date_error}<br/>

            <spring:message code="client_passport_end"/>: *<br/>
            <s:input type="text" path="passports.passportEndDate" readonly="true" name="passportEndDate" id="passportEndDate" class="tcal" value=""/>
            ${date_error}<br/>

            <input type="submit"  value="<spring:message code="button_registration"/>"/>
        </s:form>
    </div>
    <script>
        function validateRegistration() {
            var name = document.getElementById("name");
            var surname = document.getElementById("surname");
            var email = document.getElementById("email");
            var password = document.getElementById("password");
            var phone = document.getElementById("phone");
            var passport = document.getElementById("passport");
            var passportIssueDate = document.getElementById("passportIssueDate");
            var passportEndDate = document.getElementById("passportEndDate");

            if(!name.value) {
                name.style.border = "2px solid red";
                alert("Вы не ввели имя");
                return false;
            }

            if(!surname.value) {
                surname.style.border = "2px solid red";
                alert("Вы не ввели фамилию");
                return false;
            }

            if(!email.value) {
                email.style.border = "2px solid red";
                alert("Вы не ввели почту");
                return false;
            }
            if(!password.value) {
                password.style.border = "2px solid red";
                alert("Вы не ввели пароль");
                return false;
            }
            if(!phone.value) {
                phone.style.border = "2px solid red";
                alert("Вы не ввели телефонный номер");
                return false;
            }if(!passport.value) {
                passport.style.border = "2px solid red";
                alert("Вы не ввели серию паспорта");
                return false;
            }if(!passportIssueDate.value) {
                passportIssueDate.style.border = "2px solid red";
                alert("Вы не ввели дату выдачи");
                return false;
            }if(!passportEndDate.value) {
                passportEndDate.style.border = "2px solid red";
                alert("Вы не ввели дату окончания");
                return false;
            }
            return true;
        }
    </script>
</inbody>
</body>
</html>



<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <spring:message code="title_main"/>
    <%@ include file="/WEB-INF/pages/modules/work.jsp" %>
</head>
<loc id="bg" class="login">
    <jsp:include page="/WEB-INF/pages/modules/menu/language_menu.jsp"/>
</loc>
<body>
<hgroup>
    <%@ include file="/WEB-INF/pages/modules/slogan.jsp" %>
</hgroup>
<nav>
    <%@ include file="/WEB-INF/pages/modules/menu/client_menu.jsp" %>
</nav>
<section id="bg" class="overlay">
    <div>
        <%@ include file="/WEB-INF/pages/modules/menu/profile_menu.jsp" %>
    </div>
</section>
<inbody>
    <h3><c:if test="${service_exception ne null}"><spring:message code="${service_exception}"/></c:if></h3>
    <div>
        <s:form method="POST" action="change_data" modelAttribute="clientDTO" onsubmit="validateEdit">
            <spring:message code="context_edit_data"/><br/><br/>

            <spring:message code="client_name"/>:<br/>
            <s:input type="text" path="name" name="name" value="${client.name}" size="20"/>
            <sf:errors path="name" cssClass="error-validation"/><br/>

            <spring:message code="client_sur_name"/>:<br/>
            <s:input type="text" path="surname" name="surname" value="${client.surname}" size="20"/>
            <sf:errors path="surname" cssClass="error-validation"/><br/>

            <spring:message code="client_email"/>:<br/>
            <s:input type="text" path="email" name="email" value="${client.email}" size="20"/>
            <sf:errors path="email" cssClass="error-validation"/><br/>

            <spring:message code="client_password"/>:<br/>
            <s:input type="text" path="password" name="password" value="${client.password}" size="20"/>
            <sf:errors path="password" cssClass="error-validation"/><br/>

            <spring:message code="client_phone"/>:<br/>
            <s:input type="text" path="phone" name="phone" value="${client.phone}" size="20"/>
            <sf:errors path="phone" cssClass="error-validation"/><br/>


            <spring:message code="client_passport"/>:<br/>
            <s:input type="text" path="passport" name="passport" value="${client.passports.passport}"
                     size="20"/>
            ${passport_error}<br/>
            <sf:errors path="passport" cssClass="error-validation"/><br/>

            <spring:message code="client_passport_start"/>:<br/>
            ${exception_null_date}
            <s:input type="text" name="passportIssueDate" readonly="true" class="tcal" path="passportIssueDate"
                     value="${client.passports.passportIssueDate}"/>
            ${date_error}<br/>
            <sf:errors path="passportIssueDate" cssClass="error-validation"/><br/>

            <spring:message code="client_passport_end"/>:<br/>
            ${exception_null_date}
            <s:input type="text" name="passportEndDate" readonly="true" class="tcal" path="passportEndDate"
                     value="${client.passports.passportEndDate}"/>
            ${date_error}<br/>
            <sf:errors path="passportEndDate" cssClass="error-validation"/><br/>
            <input type="submit" value="<spring:message code="change_data"/>"/>
        </s:form>
    </div>
    <script>
        function validateEdit() {
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

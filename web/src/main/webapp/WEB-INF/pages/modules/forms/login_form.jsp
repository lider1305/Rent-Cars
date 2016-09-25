<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div>
    <form method="post" action="login" class="login" onclick="validate()">
        <label for="email"><spring:message code="login_f"/></label>
        <input type="text" name="email" id="email" value="names@mail.ru"/>
        <h3><c:if test="${email_error ne null}"><spring:message code="message_wrong_email"/></c:if></h3><br/>
        <label for="password"><spring:message code="password_f"/></label>
        <input type="password" name="password" id="password" value="123456789"/>
        <h3><c:if test="${password_error ne null}"><spring:message code="message_wrong_password"/></c:if></h3><br/>
        <p class="login-submit">
            <button type="submit" class="login-button" id="send"><spring:message code="enter"/></button>
        </p>

        <a href="${pageContext.servletContext.contextPath}/go_to_registration"> <spring:message
                code="registration"/> </a> <a
            href="${pageContext.servletContext.contextPath}/forgot_password"> <spring:message
            code="forgot_password"/> </a></p>
    </form>
</div>
<script>
    function validate() {
        var email = document.getElementById("email");
        var password = document.getElementById("password");

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
        return true;
    }
</script>
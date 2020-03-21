<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Login</title>
    <meta charset="utf-8">
    <link rel="icon" href="..\imgs\database-icon.png"/>
    <link rel="stylesheet" href="<c:url value="../css/style-login-reg.css"/>"/>
</head>
<body class="back-picture">

    <div class="right-top-corner-reg-login"><a href="/view/registration.jsp">Регистрация</a></div>
    <div class="welcome-shift right-top-corner-reg-login"><a href="/view/welcome.jsp">Главная</a></div>
    <div class="sample-class">
        <div class="modal-w">
            <div class="defaultLogin">
                <h1>Синтетический Генератор</h1>
            </div>
            <c:if test="${message != null}">
                <h5 class="error-mes">${message}</h5>
            </c:if>
            <form action="/login" method="post">

                <div class="login formRow">
                    <input type="text" name="username" class="username" placeholder="Имя пользователя"/>
                </div>

                <div class="password formRow">
                    <input type="password" name="password" class="upassword" placeholder="Пароль"/>
                </div>

                <div class="submit formRow">
                    <input type="submit" class="btnLogin" value="Логин"/>
                </div>

            </form>
        </div>
    </div>
</body>
</html>

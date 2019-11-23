<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Login</title>
    <link rel="stylesheet" href="<c:url value="/css/style-login-reg.css"/>"/>
</head>
<body>

    <div class="sample-class">
        <div class="modal-w">
            <div class="defaultLogin">
                <div class="logo"></div>
                <h1>Welcome to SintGenerator!</h1>
            </div>
            <c:if test="${message != null}">
                <h3 class="error-mes">${message}</h3>
            </c:if>
            <form action="/login" method="post">

                <div class="login formRow">
                    <input type="text" login="username" class="username" placeholder="Username"/>
                </div>

                <div class="password formRow">
                    <input type="password" login="password" class="upassword" placeholder="Password"/>
                </div>

                <div class="submit formRow">
                    <input type="submit" class="btnLogin" value="Login"/>
                </div>

            </form>
        </div>
    </div>

    <a href="view/registration.jsp">Registration</a>
</body>
</html>

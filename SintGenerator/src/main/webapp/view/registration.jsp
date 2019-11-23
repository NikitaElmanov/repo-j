<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Registration</title>
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
                <h4 class="error-mes">${message}</h4>
            </c:if>
            <form action="/registration" method="post">

                <div class="login formRow">
                    <input type="text" login="username" class="username" placeholder="Username"/>
                </div>

                <div class="password formRow">
                    <input type="password" login="password" class="upassword" placeholder="Password"/>
                </div>

                <div class="submit formRow">
                    <input type="submit" class="btnLogin" value="Register"/>
                </div>

            </form>
        </div>
    </div>

</body>
</html>

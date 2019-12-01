<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Registration</title>
    <link rel="stylesheet" href="<c:url value='/css/style-login-reg.css'/>"/>
</head>
<body class="back-picture">

    <div class="right-top-corner-reg-login"><a href="/view/login.jsp">Login</a></div>
    <div class="welcome-shift right-top-corner-reg-login"><a href="/view/welcome.jsp">Welcome</a></div>
    <div class="sample-class">
        <div class="modal-w">
            <div class="defaultLogin">
                <h1>Welcome to SintGenerator!</h1>
            </div>
            <c:if test="${message != null}">
                <h5 class="error-mes">${message}</h5>
            </c:if>
            <form action="/registration" method="post">

                <div class="login formRow">
                    <input type="text" name="username" class="username" placeholder="Username"/>
                </div>

                <div class="password formRow">
                    <input type="password" name="password" class="upassword" placeholder="Password"/>
                </div>

                <div class="submit formRow">
                    <input type="submit" class="btnReg" value="Register"/>
                </div>

            </form>
        </div>
    </div>

</body>
</html>

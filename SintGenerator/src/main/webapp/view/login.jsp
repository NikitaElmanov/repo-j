<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Login</title>
</head>
<body>

    <form method="post" action="login">
        <input type="text" name="username"/><br/>
        <input type="password" name="password"/><br/>
        <input type="submit" value="Login"/>
    </form>


    <a href="view/registration.jsp">Registration</a>
</body>
</html>

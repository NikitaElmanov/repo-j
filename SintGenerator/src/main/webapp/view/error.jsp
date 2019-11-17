<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Error</title>
    <link rel="stylesheet" href="<c:url value="/css/style-error.css"/>" type="text/css"/>
</head>
<body>
    <div id="container">
        <div class="error-page">
            <h1>${message}</h1>
            <div class="go-back"><a href="javascript:history.back();" style="text-decoration: none;">&emsp; &lArr; Go back</a></div>
        </div>
    </div>
</body>
</html>

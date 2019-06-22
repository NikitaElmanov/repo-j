<%@ page language="java" isErrorPage="true" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
    <title>Error 404</title>
    <%--<link rel="stylesheet" href="/css/style.css"/>--%>
</head>
<body>
    <h2 style="color: red;">You input invalid url page!</h2>

    <p><b>Error code:</b> ${pageContext.errorData.statusCode}</p>
    <p><b>Request URI:</b> ${pageContext.request.scheme}://${header.host}${pageContext.errorData.requestURI}</p>
</body>
</html>

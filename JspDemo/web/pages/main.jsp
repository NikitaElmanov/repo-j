<%--
  Created by IntelliJ IDEA.
  User: NickMax
  Date: 24.06.2019
  Time: 16:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<head>
    <title>Main Page</title>
</head>
<body>
    <%request.setCharacterEncoding("UTF-8");%>
    <h1><%=request.getParameter("username")%></h1>
    <h1>${param["password"]}</h1>
</body>
</html>

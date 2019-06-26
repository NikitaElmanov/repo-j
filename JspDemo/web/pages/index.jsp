<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<head>
    <title>Index Page</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css">
</head>
<body>
    <%request.setCharacterEncoding("UTF-8");%>
    <div class="container-blue">
        <div class="con-red">
           <form name="username" action="pages/main.jsp" method="post">
               Name: <input class="align-middle" name="username" type="text" value="" placeholder="lalalala" size="23"/><br>
               <input id="in" value="Enter" type="submit"/>
           </form>
        </div>
        <h7 class="left-bottom-corner">Developer: Nikita Elmanov, 2019.</h7>
    </div>

</body>
</html>

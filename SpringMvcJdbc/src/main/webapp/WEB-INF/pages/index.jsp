<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Department List</title>
</head>
<body>
<div align="center">
    <h1>Department List</h1>
    <table border="1">
        <tr>
            <th>No</th>
            <th>Dept No</th>
            <th>Dept Name</th>
            <th>Location</th>
        </tr>
        <c:forEach var="dept" items="${departments}" varStatus="status">
            <tr>
                <td>${dept.deptId}</td>
                <td>${dept.deptNo}</td>
                <td>${dept.deptName}</td>
                <td>${dept.location}</td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>
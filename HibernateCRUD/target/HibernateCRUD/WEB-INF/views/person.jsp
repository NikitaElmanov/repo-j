
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page session="false" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <title>Person Page</title>
    <link href="/resources/css/style.css" type="text/css" rel="stylesheet"/>
</head>
<body>
<h1>
    Add a Person
</h1>

<c:url var="addAction" value="/add" ></c:url>

<c:if test="${empty person.name}">
    <form:form action="${addAction}" modelAttribute="person">
        <table>
            <tr>
                <td>
                    <form:label path="name">
                        <spring:message text="Name"/>
                    </form:label>
                </td>
                <td>
                    <form:input path="name" />
                </td>
            </tr>
            <tr>
                <td>
                    <form:label path="country">
                        <spring:message text="Country"/>
                    </form:label>
                </td>
                <td>
                    <form:input path="country" />
                </td>
            </tr>
            <tr>
                <td colspan="2">
                        <input type="submit" value="<spring:message text="Add Person"/>" />
                </td>
            </tr>
        </table>
    </form:form>
</c:if>
<br>
<h3>Persons List</h3>
<c:if test="${!empty listPersons}">
    <table class="tg">
        <tr>
            <th width="80">Person ID</th>
            <th width="120">Person Name</th>
            <th width="120">Person Country</th>
            <th width="60">Edit</th>
            <th width="60">Delete</th>
        </tr>
        <c:forEach items="${listPersons}" var="person">
            <tr>
                <td>${person.id}</td>
                <td>${person.name}</td>
                <td>${person.country}</td>
                <td><a href="<c:url value='/edit/${person.id}' />" >Edit</a></td>
                <td><a href="<c:url value='/remove/${person.id}' />" >Delete</a></td>
            </tr>
        </c:forEach>
    </table>
</c:if>
</body>
</html>
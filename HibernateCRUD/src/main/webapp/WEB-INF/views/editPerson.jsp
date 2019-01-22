<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page session="false" %>
<%--
  Created by IntelliJ IDEA.
  User: NickMax
  Date: 22.01.2019
  Time: 10:37
  To change this template use File | Settings | File Templates.
--%>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <title>EDITPerson</title>
    <link href="/resources/css/style.css" type="text/css" rel="stylesheet"/>
</head>
<body>

<c:url var="editAction" value="/update" ></c:url>

<c:if test="${!empty person.name}">
    <form:form action="${editAction}" modelAttribute="person">
        <table>
                <tr>
                    <td>
                        <form:label path="id">
                            <spring:message text="ID"/>
                        </form:label>
                    </td>
                    <td>
                        <form:input path="id" readonly="true" size="8"  disabled="true" />
                            <%--<form:hidden path="id" />--%>
                    </td>
                </tr>
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
                    <input type="submit" value="<spring:message text="Edit Person"/>" />
                </td>
            </tr>
        </table>
    </form:form>
</c:if>
</body>
</html>

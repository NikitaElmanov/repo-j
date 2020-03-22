<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Generated Script</title>
    <link rel="icon" href="..\imgs\database-icon.png"/>
    <link rel="stylesheet" href="<c:url value="../css/generated-script.css"/>"/>
    <!--<script src="http://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.js" type="text/javascript"></script>-->
    <script src="/js/jquery-3.0.0.min.js" type="text/javascript"></script>
</head>
<body>
    <div id="container">
        <div id="script-container">
            <form action="/WriteClipboard" method="post">
                <input type="text" name="cb-text" value="${resScript}" hidden>
                <input type="submit" value="Copy to Clipboard" id="copy-btn">
            </form>

            <pre id="res-script">${resScript}</pre>
        </div>

        <c:set var = "amountColumns" scope = "session" value = "${fieldNames.size()}"/>
        <c:set var = "maxAmountRows" scope = "session" value = "9"/>
        <div id="table-preview">
            <table cellpadding="5">
                <caption>Таблица <b>${tableName}</b> первых 10 записей</caption>
                <tr>
                    <c:choose>
                        <c:when test="${seqNumPKField == -1}">
                            <th>№</th>
                        </c:when>
                        <c:otherwise>
                            <th><pre><em style="color: gold;">PK : ${fieldPKName}</em></pre></th>
                        </c:otherwise>
                    </c:choose>

                    <c:forEach var="field" items="${fieldNames}">
                        <th>${field}</th>
                    </c:forEach>
                </tr>
                <c:forEach var="list" items="${listsOfValues}" varStatus="counter" begin="0" end="${maxAmountRows * amountColumns}" step="${amountColumns}">
                    <tr>
                        <c:choose>
                            <c:when test="${seqNumPKField == -1}">
                                <td>${counter.count}</td>
                            </c:when>
                            <c:otherwise>
                                <c:forEach var="value" items="${list}" varStatus="row">
                                    <td><pre>${list.get(row.index)}</pre></td>
                                </c:forEach>
                            </c:otherwise>
                        </c:choose>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </div>

    <script>
        $('input#copy-btn').click(function(){
            $(this).css("color", "red");
        });
    </script>
</body>
</html>
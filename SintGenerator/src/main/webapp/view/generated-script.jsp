<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
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
    <c:set var = "amountColumns" scope = "session" value = "${1}"/>
    <c:set var = "maxAmountRows" scope = "session" value = "9"/>
    <c:set var = "list" scope = "session" value = "9"/>
    <%
        int c = 0;
        ArrayList<ArrayList<String>> ValuesPK = (ArrayList<ArrayList<String>>) request.getSession().getAttribute("listOfValuesPK");
        ArrayList<ArrayList<String>> ValuesPK2 = (ArrayList<ArrayList<String>>) request.getSession().getAttribute("listOfValuesPK2");
    %>

    <div id="container-script">
        <div id="script-container">
            <form action="/WriteClipboard" method="post">
                <input type="text" name="cb-text" value="${resScript}" hidden>
                <input type="submit" value="Copy to Clipboard" id="copy-btn">
            </form>

            <pre id="res-script">${resScript}</pre>
        </div>
    </div>

    <div id="container-tables">
        <div class="table-preview">
            <table cellpadding="5">
                <caption>Первые 10 записей<c:if test="${tableName2 != null}"> родительской</c:if> таблицы <b>${tableName}</b></caption>
                <tr>
                    <c:choose>
                        <c:when test="${autoIncPKFlag == -1}">
                            <th>№</th>
                        </c:when>
                        <c:when test="${autoIncPKFlag == 2}">
                            <th><em style="color: gold;">PK : ${fieldPKName}</em></th>
                        </c:when>
                        <c:otherwise>
                            <th><em style="color: gold;">PK : ${fieldPKName}</em></th>
                        </c:otherwise>
                    </c:choose>

                    <c:forEach var="field" items="${fieldsNames}">
                        <th>${field}</th>
                    </c:forEach>
                </tr>
                <c:forEach var="list" items="${listsOfValues}" varStatus="counter" begin="0" end="${maxAmountRows * amountColumns}" step="${amountColumns}">
                    <tr>
                        <c:choose>
                            <c:when test="${autoIncPKFlag == -1 || autoIncPKFlag == 2}">
                                <th><em style="color: gold;">${counter.count}</em></th>
                            </c:when>
                            <c:otherwise>
                                <th><em style="color: gold;">
                                    <%
                                        for (int i = c; i < ValuesPK.size(); i++) {
                                            out.print(ValuesPK.get(i).get(0));
                                            break;
                                        }
                                        c++;
                                    %>
                                </em></th>
                            </c:otherwise>
                        </c:choose>
                        <c:forEach var="value" items="${list}">
                            <td><pre>${value}</pre></td>
                        </c:forEach>
                    </tr>
                </c:forEach>
            </table>
        </div>

        <c:if test="${tableName2 != null}">
            <%
                int c2 = 0;
                List<String> fieldsNames2 = (List<String>) request.getSession().getAttribute("fieldsNames2");
                String childTableFieldFK = (String) request.getSession().getAttribute("childTableFieldFK");
            %>
            <div class="table-preview">
                <table cellpadding="5">
                    <caption>10 первых записей дочерней таблицы <b>${tableName2}</b></caption>
                    <tr>
                        <c:choose>
                            <c:when test="${autoIncPKFlag2 == -1}">
                                <th>№</th>
                            </c:when>
                            <c:when test="${autoIncPKFlag2 == 2}">
                                <th><em style="color: gold;">PK : ${fieldPKName2}</em></th>
                            </c:when>
                            <c:otherwise>
                                <th><em style="color: gold;">PK : ${fieldPKName2}</em></th>
                            </c:otherwise>
                        </c:choose>

                        <%
                            for (String field : fieldsNames2) {
                                if (field.equalsIgnoreCase(childTableFieldFK)) {
                                    out.print("<th><em> FK : " + field + "</em></th>");
                                } else {
                                    out.print("<th><em>" + field + "</em></th>");
                                }
                            }
                        %>
                    </tr>
                    <c:forEach var="list" items="${listsOfValues2}" varStatus="counter" begin="0" end="${maxAmountRows * amountColumns}" step="${amountColumns}">
                        <tr>
                            <c:choose>
                                <c:when test="${autoIncPKFlag2 == -1 || autoIncPKFlag2 == 2}">
                                    <th><em style="color: gold;">${counter.count}</em></th>
                                </c:when>
                                <c:otherwise>
                                    <th><em style="color: gold;">
                                        <%
                                            for (int i = c2; i < ValuesPK2.size(); i++) {
                                                out.print(ValuesPK2.get(i).get(0));
                                                break;
                                            }
                                            c2++;
                                        %>
                                    </em></th>
                                </c:otherwise>
                            </c:choose>
                            <c:forEach var="value" items="${list}">
                                <td><pre>${value}</pre></td>
                            </c:forEach>
                        </tr>
                    </c:forEach>
                </table>
            </div>
        </c:if>
    </div>

    <script>
        $('input#copy-btn').click(function(){
            $(this).css("color", "red");
        });
    </script>
</body>
</html>
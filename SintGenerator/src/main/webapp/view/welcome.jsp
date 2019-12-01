<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Welcome</title>
    <link rel="stylesheet" href="<c:url value="/css/welcome.css"/>"/>
    <script src="http://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js" type="text/javascript"></script>
    <script src="<c:url value="/js/welcome.js"/>" type="text/javascript"></script>
</head>
<body class="back-picture">
    <%
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
    %>

    <c:if test="${username != null}">
    <form action="/logout" method="post" class="welcome-shift">
        <input type="submit" value="Logout"/>
    </form>
    </c:if>

    <div class="sample-class">
        <div class="modal-w">
            <div class="title"><h1>Synthetic Generator</h1></div>
            <div class="modal-w-row">

                <div class="table">
                    <div class="table-thead">
                        <ul class="table-tr">
                            <li class="table-th">Filed Name</li>
                            <li class="table-th">Type</li>
                            <li class="table-th">Precision</li>
                            <li class="table-th">Primary Key</li>
                            <li class="table-th"><input id="del" type="button" value="Del"/></li>
                        </ul>
                    </div>
                    <div class="table-tbody">
                        <ul class="table-tr tr-body">
                            <li class="table-td"><input type="text" id="field-name"	/></li>
                            <li class="table-td">
                                <select class="type">//loop set values of types
                                    <option selected>The first option</option>
                                    <option>The second option</option>
                                    <option>The third option</option>
                                </select>
                            </li>
                            <li class="table-td"><input type="text" id="precision"/></li>
                            <li class="table-td">
                                <input type="checkbox" class="pk" name="pk"/>
                            </li>
                            <li class="table-td">
                                <input type="checkbox" class="del" name="del"/>
                            </li>
                        </ul>
                    </div>
                </div>
                <div class="welcome-shift"><input type="submit" value="Add row" id="addRow"/></div>

            </div>
            <div class="modal-w-row">
                <div id="finish-attrs">
                    <div name="insert"><input type="checkbox" name="insert" value="insert"/>insert</div>
                    <div name="addCS"><input type="checkbox" name="addCS" value="addCS"/>Add creating script</div>
                    <div name="addId"><input type="checkbox" name="addId" value="addId"/>Add id autoincrement</div>
                </div>
                <!--<form action="" method="get" id="generate">-->
                <button type="submit" value="Generate" id="generate">Generate</button>
                <!--</form>-->
            </div>
        </div>
    </div>
</body>
</html>

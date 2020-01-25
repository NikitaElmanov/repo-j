<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Welcome</title>
    <meta charset="utf-8">
    <link rel="icon" href="..\imgs\database-icon.png"/>
    <link rel="stylesheet" href="<c:url value="/css/welcome.css"/>"/>
    <!--<script src="http://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.js" type="text/javascript"></script>-->
    <script src="/js/jquery-3.0.0.min.js" type="text/javascript"></script>
    <script src="<c:url value="/js/welcome.js"/>" type="text/javascript"></script>
</head>
<body class="back-picture">
    <%
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
    %>

    <div id="ajaxGetUserServletResponse"></div>

    <c:if test="${username != null}">
    <div id="username-container"><h1>${username}</h1></div>
    <form action="/logout" method="post" class="welcome-shift">
        <input type="submit" value="Выйти"/>
    </form>
    </c:if>

    <div class="sample-class">
        <div class="modal-w">
            <div class="title"><h1>Синтетический Генератор</h1></div>
            <div class="modal-w-row">

                <div class="table">
                    <div class="table-thead">
                        <ul class="table-tr">
                            <li class="table-th">Имя поля</li>
                            <li class="table-th">Тип</li>
                            <li class="table-th">Точность</li>
                            <li class="table-th">Первичный ключ</li>
                            <li class="table-th"><input id="del" type="button" value="Удалить"/></li>
                        </ul>
                    </div>
                    <div class="table-tbody">
                        <ul class="table-tr tr-body" id='first-row-table-body'>
                            <li class="table-td"><input type="text" class="field-name"/></li>
                            <li class="table-td">
                                <select class="type">//loop set values of types
                                    <option selected>VARCHAR</option>
                                    <option>CHAR</option>
                                    <option>INT</option>
                                    <option>INT UNSIGNED</option>
                                    <option>DECIMAL</option>
                                    <option>DATE</option>
                                    <option>BOOLEAN</option>
                                </select>
                            </li>
                            <li class="table-td"><input type="text" class="precision"/></li>
                            <li class="table-td">
                                <input type="radio" class="pk" name="pk"/>
                            </li>
                            <li class="table-td">
                                <input type="checkbox" class="del" name="del"/>
                            </li>
                        </ul>
                    </div>
                </div>
                <div class="welcome-shift"><input type="submit" value="Добавить строку" id="addRow"/></div>

            </div>
            <div class="modal-w-row">
                <div id="finish-attrs">
                    <div id="table-name-container">
                        <span>Имя таблицы: </span><input id="table-name" type="text"/>
                    </div>
                    <div name="insert-script" id="insert-script">
                        <input id="insert" type="checkbox" name="script"/>
                        <label for="insert">Сгенерировать insert-ы</label>
                    </div>
                    <div id="amount-rows-container">
                        <span>Кол-во строк: </span><input id="amount-rows" type="text" disabled=true/>
                    </div>
                    <!--<div name="update-script" id="update-script">
						<input id="update" type="radio" name="script"/>
						<label for="update">Update script</label>
					</div>	-->
                    <div name="add-create-script">
                        <input type="checkbox" name="addCS" value="addCS" id="add-create-script"/>
                        <label for="add-create-script">Добавить create скрипт</label>
                    </div>
                    <!--<div name="addId">
						<input type="checkbox" name="addId" value="addId" id="addId"/>
						<label for="addId">Add id autoincrement</label>
					</div>-->
                </div>
                <!--<form action="" method="get" id="generate">-->
                <button type="submit" value="Generate" id="generate">Сгенерировать</button>
                <!--</form>-->
            </div>
        </div>
    </div>
    <!--<form action="/show-script" method="post" target="_blank" hidden>
        <input type="text" id="data-script" name="data-script">
        <input type="submit" id="btn-show-script">
    </form>-->

    <!--<script>
        $('button#generate').click(function(){
            $.ajax({
                url : '/showScript',
                method : 'get',
                success : function(){
                    window.open('/showScript', '_blank');
                }
            });
        });
    </script>-->
</body>
</html>

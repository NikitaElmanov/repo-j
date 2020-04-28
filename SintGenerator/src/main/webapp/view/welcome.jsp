<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Welcome</title>
    <meta charset="utf-8">
    <link rel="icon" href="..\imgs\database-icon.png"/>
    <link rel="stylesheet" href="<c:url value="/css/welcome.css"/>"/>
    <link rel="stylesheet" href="<c:url value="/css/user-settings.css"/>"/>
    <!--<script src="http://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.js" type="text/javascript"></script>-->
    <script src="/js/jquery-3.0.0.min.js" type="text/javascript"></script>
    <script src="<c:url value="/js/welcome.js"/>" type="text/javascript"></script>
    <script src="<c:url value="/js/user-param-settings.js"/>" type="text/javascript"></script>
</head>
<body class="back-picture">
<%
    response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
%>
<c:if test="${username != null}">
    <div id="username-container"><h1>${username}</h1></div>
    <form action="/logout" method="post" class="welcome-shift">
        <input type="submit" value="Выйти"/>
    </form>
</c:if>

<div id="container">
    <div class="sample-class">
        <div class="modal-w">
            <div class="title">
                <h1>Synthetic Generator</h1>
                <img id="click-settings" src="/imgs/settings.png" alt="settings">
            </div>
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
                <div class="welcome-shift"><input type="submit" value="Add row" id="addRow"/></div>

            </div>
            <div class="modal-w-row">
                <div id="finish-attrs">
                    <div id="table-name-container">
                        <span>Table name: </span><input id="table-name" type="text"/>
                    </div>
                    <div name="insert-script" id="insert-script">
                        <input id="insert" type="checkbox" name="script"/>
                        <label for="insert">Insert script</label>
                    </div>
                    <div id="amount-rows-container">
                        <span>Amount rows: </span><input id="amount-rows" type="text" disabled=true/>
                    </div>
                    <!--<div name="update-script" id="update-script">
						<input id="update" type="radio" name="script"/>
						<label for="update">Update script</label>
					</div>	-->
                    <div name="add-create-script">
                        <input type="checkbox" name="addCS" value="addCS" id="add-create-script"/>
                        <label for="add-create-script">Add creating script</label>
                    </div>
                    <div name="add-connect-table">
                        <input type="checkbox" name="addCT" value="addCT" id="add-connect-table" disabled/>
                        <label for="add-connect-table">Create connected table</label>
                    </div>
                    <!--<div name="addId">
						<input type="checkbox" name="addId" value="addId" id="addId"/>
						<label for="addId">Add id autoincrement</label>
					</div>-->
                </div>
                <!--<form action="" method="get" id="generate">-->
                <button type="submit" value="Generate" id="generate">Generate</button>
                <!--</form>-->
            </div>
        </div>
    </div>

    <!--!!!!!!!!!!222222222222222222222222222222222!!!!!!!!!!!------>

    <div class="sample-class2" style="display: none;">
        <div class="modal-w2">
            <div class="modal-w-row2">

                <div class="table2">
                    <div class="table-thead2">
                        <ul class="table-tr2">
                            <li class="table-th2">Filed Name</li>
                            <li class="table-th2">Type</li>
                            <li class="table-th2">Precision</li>
                            <li class="table-th2">Primary Key</li>
                            <li class="table-th2"><input id="del2" type="button" value="Del"/></li>
                        </ul>
                    </div>
                    <div class="table-tbody2">
                        <ul class="table-tr2 tr-body2" id='first-row-table-body2'>
                            <li class="table-td2"><input type="text" class="field-name2"/></li>
                            <li class="table-td2">
                                <select class="type2">//loop set values of types
                                    <option selected>VARCHAR</option>
                                    <option>CHAR</option>
                                    <option>INT</option>
                                    <option>INT UNSIGNED</option>
                                    <option>DECIMAL</option>
                                    <option>DATE</option>
                                    <option>BOOLEAN</option>
                                </select>
                            </li>
                            <li class="table-td2"><input type="text" class="precision2"/></li>
                            <li class="table-td2">
                                <input type="radio" class="pk2" name="pk2"/>
                            </li>
                            <li class="table-td2">
                                <input type="checkbox" class="del2" name="del2"/>
                            </li>
                        </ul>
                    </div>
                </div>
                <div class="welcome-shift2"><input type="submit" value="Add row" id="addRow2"/></div>

            </div>
            <div class="modal-w-row2">
                <div id="finish-attrs2">
                    <div id="table-name-container2">
                        <span>Table name: </span><input id="table-name2" type="text"/>
                    </div>
                    <div name="child-table-field">
                        <span>Child field: </span><input id="child-table-field" type="text"/>
                    </div>
                    <div name="parent-table-field">
                        <span>Parent field: </span><input id="parent-table-field" type="text"/>
                    </div>
                    <div name="add-create-script2">
                        <input type="checkbox" name="addCS" value="addCS" id="add-create-script2"/>
                        <label for="add-create-script2">Add creating script</label>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>


<%--//-settings for user params--%>
<div class="modal-w-settings-param" style="display: none;">
    <div id="settings-title">
        <h1>Пользовательские настройки</h1>
    </div>

    <div id="close">
        <img src="/imgs/close.png" alt="close button">
    </div>

    <div id="settings-param-container">
        <div id="username-settings" class="settings-param">
            <form action="/changeUserParams" method="post">
                <div><input type="text" name="newName" placeholder="новое имя" style="height: 35px; font-size: 18;"></div><br>
                <div style="position:relative;">
                    <input type="password" name="newPassword" placeholder="новое пароль" style="height: 35px; font-size: 18;">
                    <img src="/imgs/eye.png" style="position: absolute; height: 100%; cursor: pointer; border-radius: 10px;" alt="eye">
                </div><br>
                <div><input type="submit" value="Изменить" style="height: 35px; width: 100%;"></div>
            </form>
        </div>
    </div>
</div>
<%--////settings params--%>

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

$(document).ready(function () {
    var counterOfRows = 0;
    var maxAmountOfColumns = 15;
    var stepOfExpandingMW = 50;
    //--------------------
    var counterOfRows2 = 0;
    var maxAmountOfColumns2 = 15;
    var stepOfExpandingMW2 = 40;
    //----------------------
    var autoIncrementFlag = -1;
    var autoIncrementFlag2 = -1;
    //----------------------
    var noPKTypes = [/*'VARCHAR'*/, 'CHAR', 'BOOLEAN', 'DATE', 'DECIMAL'];
    var precTypes = ['CHAR', 'VARCHAR'];
    var noPrecTypes = ['BOOLEAN'/*,'INT',INT UNSIGNED', 'DATE'*/];
    var typesFields, precisionFields, pkFields, secondTableFlag = 0;

    window.setInterval(function () {
        precisionFields = $('input.precision');
        typesFields = $('select.type');
        pkFields = $('input.pk');
        var activeFlag = 0;

        //handling types {if type = 'BOOLEAN','INT','INT UNSIGNED' then disable}
        for (var i = 0; i < typesFields.length; i++) {
            var activeFlag = 0;

            for (var k = 0; k < noPrecTypes.length; k++) {//!!!date is here temporarily, probably
                //'BOOLEAN','INT','INT UNSIGNED', 'DATE',
                if (noPrecTypes[k] == typesFields[i].value) {
                    activeFlag = 1;
                }
            }

            if (activeFlag == 1) {
                precisionFields[i].removeAttribute('placeholder');
                precisionFields[i].style.background = 'white';
                precisionFields[i].value = '';
                precisionFields[i].setAttribute('disabled', 'true');
            } else {
                precisionFields[i].removeAttribute('disabled');
            }

            if ('DECIMAL' == typesFields[i].value) {
                //precisionFields[i].style.background = 'white';
                precisionFields[i].setAttribute('placeholder', '5.2');
            }

            if ('DECIMAL' != typesFields[i].value
                && precisionFields[i].getAttribute("placeholder") == "") {
                //precisionFields[i].style.background = 'white';
                precisionFields[i].removeAttribute('placeholder');
            }

            if ('INT' == typesFields[i].value || 'INT UNSIGNED' == typesFields[i].value) {
                if (pkFields[i].checked) {
                    precisionFields[i].value = "";
                    precisionFields[i].disabled = true;
                } else {
                    precisionFields[i].disabled = false;
                }
            }
        }

        $('input.pk').click(function () {//make unchecked action for PK radiobutton
            if ($(this).is(':checked')) {
                $(this).prop('checked', false);
            } else {
                $(this).prop('checked', true);
            }
        });

        //autoIncrementFlag UPPER^----------------
        var pk = $('input.pk');

        var supFlag = -1;
        for (var i = 0; i < pk.length; i++) {
            if (pk[i].checked) {

                if ($('select.type')[i].value == 'INT'
                    || $('select.type')[i].value == 'INT UNSIGNED') {
                    autoIncrementFlag = 1;
                } else {
                    autoIncrementFlag = -1;
                }

                supFlag = 1;
            }
        }

        if (supFlag == -1) {
            autoIncrementFlag = -1;
        }


        var addConnectTable = $('input#add-connect-table');
        if (addConnectTable.is(':checked')) {
            autoIncrementFlag = -1;
        }

        $('div.welcome-shift').each(function () {
            if (autoIncrementFlag == 1
                && $('div[name="auto-increment-one"]').length <= 0) {
                $(this).append('' +
                    '<div name="auto-increment-one">' +
                    '<input type="checkbox" name="AIOne" value="AIOne" id="auto-increment-one"/>' +
                    '<label for="auto-increment-one">Make primary key is auto-increment?</label>' +
                    '</div>');
            } else if (autoIncrementFlag == -1 && $('div[name="auto-increment-one"]').length > 0) {
                $('div[name="auto-increment-one"]').remove();
            }
        });

        //-----------------------------------------------

        //---------------------------------------------------222222222222222222222222222222222222222
        precisionFields2 = $('input.precision2');
        typesFields2 = $('select.type2');
        pkFields2 = $('input.pk2');
        var activeFlag2 = 0;

        //handling types {if type = 'BOOLEAN','INT','INT UNSIGNED' then disable}
        for (var i = 0; i < typesFields2.length; i++) {
            var activeFlag2 = 0;

            for (var k = 0; k < noPrecTypes.length; k++) {//!!!date is here temporarily, probably
                //'BOOLEAN','INT','INT UNSIGNED', 'DATE',
                if (noPrecTypes[k] == typesFields2[i].value) {
                    activeFlag2 = 1;
                }
            }

            if (activeFlag2 == 1) {
                precisionFields2[i].removeAttribute('placeholder');
                precisionFields2[i].style.background = 'white';
                precisionFields2[i].value = '';
                precisionFields2[i].setAttribute('disabled', 'true');
            } else {
                precisionFields2[i].removeAttribute('disabled');
            }

            if ('DECIMAL' == typesFields2[i].value) {
                //precisionFields[i].style.background = 'white';
                precisionFields2[i].setAttribute('placeholder', '5.2');
            }

            if ('DECIMAL' != typesFields2[i].value
                && precisionFields2[i].getAttribute("placeholder") == "") {
                //precisionFields[i].style.background = 'white';
                precisionFields2[i].removeAttribute('placeholder');
            }

            if ('INT' == typesFields2[i].value || 'INT UNSIGNED' == typesFields2[i].value) {
                if (pkFields2[i].checked) {
                    precisionFields2[i].value = "";
                    precisionFields2[i].disabled = true;
                } else {
                    precisionFields2[i].disabled = false;
                }
            }
        }

        $('input.pk2').click(function () {//make unchecked action for PK radiobutton
            if ($(this).is(':checked')) {
                $(this).prop('checked', false);
            } else {
                $(this).prop('checked', true);
            }
        });

        //----------------------------
        var pk2 = $('input.pk2');
        var supFlag2 = -1;
        for (var i = 0; i < pk2.length; i++) {
            if (pk2[i].checked) {

                if ($('select.type2')[i].value == 'INT'
                    || $('select.type2')[i].value == 'INT UNSIGNED') {
                    autoIncrementFlag2 = 1;
                } else {
                    autoIncrementFlag2 = -1;
                }

                supFlag2 = 1;
            }
        }

        if (supFlag2 == -1) {
            autoIncrementFlag2 = -1;
        }

        $('div.welcome-shift2').each(function () {
            if (autoIncrementFlag2 == 1
                && $('div[name="auto-increment-two"]').length <= 0) {
                $(this).append('' +
                    '<div name="auto-increment-two">' +
                    '<input type="checkbox" name="AITwo" value="AITwo" id="auto-increment-two"/>' +
                    '<label for="auto-increment-two">Make primary key is auto-increment?</label>' +
                    '</div>');
            } else if (autoIncrementFlag2 == -1
                && $('div[name="auto-increment-two"]').length > 0) {

                $('div[name="auto-increment-two"]').remove();
            }
        });
        //---------------------------
        //222222222222222222222222222222222222

        if ($('input#add-connect-table').is(':checked')) {
            $('div.sample-class2')[0].style.display = 'flex';
            secondTableFlag = 1;
        } else {
            $('div.sample-class2')[0].style.display = 'none';
            secondTableFlag = 0;
        }
    }, 500);

    $('#del').click(function () {
        var delCheckBoxs = $('input.del');
        var amountOfChecked = 0;
        var originalHeight = $('.modal-w').height();
        var newHeight;

        delCheckBoxs.each(function () {
            if ($(this).is(':checked')) {
                amountOfChecked++;
            }
        });

        if ($('input.del').length == amountOfChecked) {
            // alert('one column (filed) has to retain');
            alert('Осталась одна колонка (поле), её нельзя удалять');
        } else {
            originalHeight -= stepOfExpandingMW * amountOfChecked;
            $('.modal-w').css("height", originalHeight);

            delCheckBoxs.each(function () {//if amountOfChecked = 0 then happens nothing
                if ($(this).is(':checked')) {
                    $(this).parents('.table-tr').remove();
                    counterOfRows--;
                }
            });
        }
    });

    $('input#addRow').click(function () {//adding new row (column) for table
        if (counterOfRows <= maxAmountOfColumns) {

            var btnGen = $('#generate');
            var modelWindow = $('.modal-w');

            //changing height of modal window
            if (btnGen.offset().top + btnGen.height() >= modelWindow.offset().top + modelWindow.height() - btnGen.height() - stepOfExpandingMW) {
                var originalHeight = $('.modal-w').height();
                var newHeight = originalHeight + stepOfExpandingMW;
                $('.modal-w').css("height", newHeight);
            }

            $('.table-tbody').append('<ul class="table-tr tr-body">' +
                '<li class="table-td"><input type="text" class="field-name"/></li>' +
                '<li class="table-td">' +
                '<select class="type">' +
                '<option selected>VARCHAR</option>' +
                '<option>CHAR</option>' +
                '<option>INT</option>' +
                '<option>INT UNSIGNED</option>' +
                '<option>DECIMAL</option>' +
                '<option>DATE</option>' +
                '<option>BOOLEAN</option>' +
                '</select>' +
                '</li>' +
                '<li class="table-td"><input type="text" class="precision"/></li>' +
                '<li class="table-td">' +
                '<input type="radio" class="pk" name="pk"/>' +
                '</li>' +
                '<li class="table-td">' +
                '<input type="checkbox" class="del" name="del"/>' +
                '</li>' +
                '</ul>');

            counterOfRows++;

        } else {
            alert('Ограничение на кол-во полей в одной таблице равно 15');
        }
    });

    //22222222222222222222222222222
    $('input#addRow2').click(function () {//adding new row (column) for table
        if (counterOfRows2 <= maxAmountOfColumns2) {

            var addCSCheckBox = $('#add-create-script2');
            var modelWindow = $('.modal-w2');

            //changing height of modal window
            if (addCSCheckBox.offset().top + addCSCheckBox.height() >= modelWindow.offset().top + modelWindow.height() - addCSCheckBox.height() - stepOfExpandingMW2) {
                var originalHeight = $('.modal-w2').height();
                var newHeight = originalHeight + stepOfExpandingMW2;
                $('.modal-w2').css("height", newHeight);
            }

            $('.table-tbody2').append('<ul class="table-tr2 tr-body2" id="first-row-table-body2">' +
                '<li class="table-td2"><input type="text" class="field-name2"/></li>' +
                '<li class="table-td2">' +
                '<select class="type2">' +//loop set values of types
                '<option selected>VARCHAR</option>' +
                '<option>CHAR</option>' +
                '<option>INT</option>' +
                '<option>INT UNSIGNED</option>' +
                '<option>DECIMAL</option>' +
                '<option>DATE</option>' +
                '<option>BOOLEAN</option>' +
                '</select>' +
                '</li>' +
                '<li class="table-td2"><input type="text" class="precision2"/></li>' +
                '<li class="table-td2">' +
                '<input type="radio" class="pk2" name="pk2"/>' +
                '</li>' +
                '<li class="table-td2">' +
                '<input type="checkbox" class="del2" name="del2"/>' +
                '</li>' +
                '</ul>');

            counterOfRows2++;

        } else {
            alert('Ограничение на кол-во полей в одной таблице равно 15');
        }
    });

    $('#del2').click(function () {
        var delCheckBoxs = $('input.del2');
        var amountOfChecked = 0;
        var originalHeight = $('.modal-w2').height();
        var newHeight;

        delCheckBoxs.each(function () {
            if ($(this).is(':checked')) {
                amountOfChecked++;
            }
        });

        if ($('input.del2').length == amountOfChecked) {
            alert('one column (filed) has to retain');
        } else {
            originalHeight -= stepOfExpandingMW2 * amountOfChecked;
            $('.modal-w2').css("height", originalHeight);

            delCheckBoxs.each(function () {//if amountOfChecked = 0 then happens nothing
                if ($(this).is(':checked')) {
                    $(this).parents('.table-tr2').remove();
                    counterOfRows2--;
                }
            });
        }
    });
    //2222222222222222222222222222222222222222222

    /*$('input#add-connect-table').click(function () {
        if ($(this).is(':checked')) {
            $('div.sample-class2')[0].style.display = 'flex';
            secondTableFlag = 1;
        } else {
            $('div.sample-class2')[0].style.display = 'none';
            secondTableFlag = 0;
        }
    });*/

    $('input#insert').click(function () {
        if ($(this).is(':checked')) {
            $('input#amount-rows').attr('disabled', false);
            $('input#add-connect-table').attr('disabled',false);
        } else {
            $('input#amount-rows').attr('disabled', true);
            $('input#add-connect-table').attr('disabled', true);
            $('input#add-connect-table').prop('checked', false);
        }
    });

    var allGoodFlag = 1;
    var generateBtn = $('#generate');//running generate process
    generateBtn.click(function () {//handling of empty filed name error
        var supportFlag = 1;

        $('li.table-td input.field-name').each(function () {
            if ($.trim($(this).val()).length == 0
                || $.trim($(this).val()).match('^\\D+$') == null) {
                $(this).css('border', '2px solid red');
                $(this).val('');
                $(this).attr('placeholder', 'введите имя поля!');

                supportFlag = -1;

                //return;
            } else {
                allGoodFlag = 1;

                $(this).css('border', '1px solid darkblue');
            }

            if (supportFlag == -1) {
                allGoodFlag == 0;
            }
        });

        if (secondTableFlag != 0) {
            var supportFlag2 = 1;
            //2222222222222222222
            $('li.table-td2 input.field-name2').each(function () {
                if ($.trim($(this).val()).length == 0
                    || $.trim($(this).val()).match('^(\\D)+$') == null) {
                    $(this).css('border', '2px solid red');
                    $(this).val('');
                    $(this).attr('placeholder', 'введите имя поля!');

                    supportFlag2 = -1;

                    return;
                } else {
                    allGoodFlag = 1;

                    $(this).css('border', '1px solid darkblue');
                }
            });
            if (supportFlag2 == -1) {
                allGoodFlag = 0;
            }
            //22222222222222222222222
        }
        //-----------------------------

        var alertFlag = 0;
        for (var j = 0; j < typesFields.length; j++) {
            for (var i = 0; i < precTypes.length; i++) {
                if (precTypes[i] == typesFields[j].value) {//'CHAR',VARCHAR
                    if (precisionFields[j].value.match('^\\d{1,3}$')) {
                        precisionFields[j].removeAttribute('placeholder');
                        precisionFields[j].style.background = 'white';
                    } else {
                        precisionFields[j].value = "";
                        precisionFields[j].setAttribute('placeholder', 'введите цифру');
                        precisionFields[j].style.background = 'pink';

                        //alert('Задайте максимальный допустимый размер для поля');

                        allGoodFlag = 0;

                        //return;
                    }
                }
            }

            alertFlag = 0;

            if ('DECIMAL' == typesFields[j].value) {//decimal
                if (precisionFields[j].value.match('^\\d{1}$') ||
                    (precisionFields[j].value.match('^\\d{1}((?=[.|,]\\d{1})|[.|,]\\d{1})$')
                        && (precisionFields[j].value.split(".")[0] > precisionFields[j].value.split(".")[1]
                            || precisionFields[j].value.split(",")[0] > precisionFields[j].value.split(",")[1]))) {

                    precisionFields[j].removeAttribute('placeholder');
                    precisionFields[j].style.background = 'white';
                } else {
                    precisionFields[j].value = "";
                    precisionFields[j].setAttribute('placeholder', 'введите цифру');
                    precisionFields[j].style.background = 'pink';
                    alert('Задайте пареметр для типа DECIMAL по шаблону *,*');

                    allGoodFlag = 0;

                    return;
                }
            }

            if (typesFields[j].value == 'INT' || typesFields[j].value == 'INT UNSIGNED') {
                if (precisionFields[j].value.includes(".")) {
                    precisionFields[j].value = precisionFields[j].value.replace('.', ',');
                }
            }

            if ('INT' == typesFields[j].value && !pkFields[j].checked) {
                if (precisionFields[j].value.match('^(-\\d{1,4}|\\d{1,4})(.|,)(-\\d{1,4}|\\d{1,4})$')
                    && parseInt(precisionFields[j].value.split(",")[0]) < parseInt(precisionFields[j].value.split(",")[1])) {

                    //console.log('INT firmat - ok');
                } else {
                    alert('INT имеет следующий шаблон: (минимальная граница) "." or "," (максимальная граница)');

                    //allGoodFlag = 0;
                    return;
                }
            }

            if ('INT UNSIGNED' == typesFields[j].value && !pkFields[j].checked) {
                if (precisionFields[j].value.match('^\\d{1,4}(.|,)\\d{1,4}$')
                    && parseInt(precisionFields[j].value.split(",")[0]) > 0
                    && parseInt(precisionFields[j].value.split(",")[1]) > 0
                    && parseInt(precisionFields[j].value.split(",")[0]) < parseInt(precisionFields[j].value.split(",")[1])) {

                    //console.log('INT UNSIGNED firmat - ok');
                } else {
                    alert('INT UNSIGNED имеет следующий шаблон: (минимальная граница).(максимальная граница), также числа должны быть положительными');

                    //allGoodFlag = 0;
                    return;
                }
            }
        }

        var fieldNames = $('input.field-name');

        if (allGoodFlag != -1) {
            //handling of field's names (checking on unique)
            for (var i = 0; i < fieldNames.length; i++) {
                for (var j = 0; j < fieldNames.length; j++) {
                    if (fieldNames[j].value == fieldNames[i].value && i != j
                        && fieldNames[j].value != ""
                        && fieldNames[i].value != "") {
                        alert('Имена полей должны быть уникальными. Необходимо исправить - \"' + fieldNames[j].value + '\"');

                        return;
                    }
                }
            }

            if (secondTableFlag != 0) {
                //22222222222222222222222
                var fieldNames2 = $('input.field-name2');

                //handling of field's names (checking on unique)
                for (var i = 0; i < fieldNames2.length; i++) {
                    for (var j = 0; j < fieldNames2.length; j++) {
                        if (fieldNames2[j].value == fieldNames2[i].value && i != j
                            && fieldNames2[j].value != ""
                            && fieldNames2[i].value != "") {
                            alert('Имена полей должны быть уникальными. Необходимо исправить - \"' + fieldNames2[j].value + '\"');

                            return;
                        }
                    }
                }
                //2222222222222222222222
            }

        }

        typesFields = $('select.type');//checking PK's types (ONLY INT AND UNSIGNED INT)
        pkFields = $('input.pk');
        precisionFields = $('input.precision');
        for (var i = 0; i < typesFields.length; i++) {
            for (var k = 0; k < noPKTypes.length; k++) {
                if (typesFields[i].value == noPKTypes[k] && pkFields[i].checked) {
                    alert('Первичным ключём может быть поле с типами INT, INT UNSIGNED or VARCHAR');

                    return;
                }
            }

            if (typesFields[i].value == 'DATE') {
                if (precisionFields[i].value.match('^[0-9]{4}(,|.)[0-9]{4}$')) {

                    if (precisionFields[i].value.includes(".")) {
                        precisionFields[i].value = precisionFields[i].value.replace('.', ',');
                    }

                    var start = precisionFields[i].value.substr(0, precisionFields[i].value.indexOf(","));
                    var end = precisionFields[i].value.substr(precisionFields[i].value.indexOf(",") + 1);

                    if (start >= end) {
                        alert('First number has to be less then second (DATE Field).');
                        return;
                    }

                } else {
                    alert('Точность даты должна выглядеть как \'**** (.) or (,) ****\', где * означает цифру');
                    return;
                }
            }

            if (typesFields[i].value == 'DECIMAL') {
                if (precisionFields[i].value.includes(".")) {
                    precisionFields[i].value = precisionFields[i].value.replace('.', ',');
                }
            }
        }

        if (secondTableFlag != 0) {
            //222222222222222222

            typesFields2 = $('select.type2');//checking PK's types (ONLY INT AND UNSIGNED INT)
            pkFields2 = $('input.pk2');
            precisionFields2 = $('input.precision2');
            for (var i = 0; i < typesFields2.length; i++) {
                for (var k = 0; k < noPKTypes.length; k++) {
                    if (typesFields2[i].value == noPKTypes[k] && pkFields2[i].checked) {
                        alert('Первичным ключём может быть поле с типами INT, INT UNSIGNED or VARCHAR');

                        return;
                    }
                }

                if (typesFields2[i].value == 'DATE') {
                    if (precisionFields2[i].value.match('^[0-9]{4}(,|.)[0-9]{4}$')) {

                        if (precisionFields2[i].value.includes(".")) {
                            precisionFields2[i].value = precisionFields2[i].value.replace('.', ',');
                        }

                        var start = precisionFields2[i].value.substr(0, precisionFields2[i].value.indexOf(","));
                        var end = precisionFields2[i].value.substr(precisionFields2[i].value.indexOf(",") + 1);

                        if (start >= end) {
                            alert('First number has to be less then second (DATE Field).');
                            return;
                        }

                    } else {
                        alert('Точность даты должна выглядеть как \'**** (.) or (,) ****\', где * означает цифру');
                        return;
                    }
                }

                if (typesFields2[i].value == 'DECIMAL') {
                    if (precisionFields2[i].value.includes(".")) {
                        precisionFields2[i].value = precisionFields2[i].value.replace('.', ',');
                    }
                }

                if (typesFields2[i].value == 'INT' || typesFields2[i].value == 'INT UNSIGNED') {
                    if (precisionFields2[i].value.includes(".")) {
                        precisionFields2[i].value = precisionFields2[i].value.replace('.', ',');
                    }
                }
            }
            //22222222222222222
        }

        var tableName = $('input#table-name').val();

        if (tableName == '' || tableName.match('^[0-9]{0,}$') || tableName.match('^\\s+$')) {
            alert('Имя таблицы необходимо исправить');
            return;
        }

        //2222222222222222

        if (secondTableFlag != 0) {
            var tableName1 = $('input#table-name').val();
            var tableName2 = $('input#table-name2').val();

            if (tableName2 == '' || tableName2.match('^[0-9]{0,}$') || tableName2.match('^\\s+$')) {
                alert('Имя таблицы необходимо исправить');
                return;
            }

            if (tableName1 == tableName2) {
                allGoodFlag = 0;
                alert('Таблицы не могут иметь одинаковые имена');
                return;
            }
        }
        //2222222222222

        var amountRows = $('input#amount-rows').val();

        if (!amountRows.match('^[0-9]+$') && $("input#insert").is(':checked')) {
            alert('Кол-во строк необходимо исправить');
            return;
        } else if (amountRows == '') {
            amountRows = 0;
        }

        var insertScript = $("input#insert");
        //var updateScript = $("input#update");
        var addCreateScript = $("input#add-create-script");
        //var addIdScript = $("input#addId");
        var autoOrNot = $("input#add-connect-table");
        var addCreateScript2 = $("input#add-create-script2");

        var resParams = '';

        if (insertScript.is(':checked')) {
            resParams += "insert;";
        }
        /*if (updateScript.is(':checked')){
            resParams += "update;";
        }*/
        if (addCreateScript.is(':checked')) {
            resParams += "create;";
        }
        if (autoOrNot.is(':checked')) {
            resParams += "secondTable;";
        }
        if (addCreateScript2.is(':checked')) {
            resParams += "create2;";
        }
        if (autoIncrementFlag == 1
            && $('input#auto-increment-one').is(':checked')) {
            resParams += "AIOne";
        }
        if (autoIncrementFlag2 == 1
            && $('input#auto-increment-two').is(':checked')) {
            resParams += "AITwo";
        }
        /*if (addIdScript.is(':checked')){
            resParams += "id;";
        }*/

        if (resParams.length < 1) {
            alert('Необходимо выбрать хотя бы одну установку для генерации (create или/и insert)');
            return;
        }

        var fieldNames = [];
        $('input.field-name').each(function () {
            fieldNames.push($(this).val());
        });
        var fieldTypes = [];
        $('select.type').each(function () {
            fieldTypes.push($(this).val());
        });
        var fieldPrecisions = [];
        $('input.precision').each(function () {
            fieldPrecisions.push($(this).val());
        });
        var fieldPK = [];
        $('input.pk').each(function () {
            if ($(this).is(':checked')) {
                fieldPK.push('true');
            } else {
                fieldPK.push('false');
            }
        });

        var parentTableField;
        var childTableField;

        var fieldNames2 = [];
        var fieldTypes2 = [];
        var fieldPrecisions2 = [];
        var fieldPK2 = [];
        if (secondTableFlag != 0) {
            //222222222222222222222222
            $('input.field-name2').each(function () {
                fieldNames2.push($(this).val());
            });
            $('select.type2').each(function () {
                fieldTypes2.push($(this).val());
            });
            $('input.precision2').each(function () {
                fieldPrecisions2.push($(this).val());

                if ($(this).val() == '') {
                    fieldPrecisions2.pop();
                    fieldPrecisions2.push('1,100');
                }
            });
            $('input.pk2').each(function () {
                if ($(this).is(':checked')) {
                    fieldPK2.push('true');
                } else {
                    fieldPK2.push('false');
                }
            });

            parentTableField = $('input#parent-table-field').val();
            childTableField = $('input#child-table-field').val();

            if (parentTableField == ''
                || childTableField == ''
                || parentTableField.match('^\\s+$')
                || childTableField.match('^\\s+$')) {

                alert('Поля Child Filed и Parent Field должны быть заполнены');
                allGoodFlag = 0;
                return;
            }

            var checkFirstTableIndex = -1;
            var checkSecondTableIndex = -1;

            for (var i = 0; i < fieldNames.length; i++) {
                if (fieldNames[i] == parentTableField
                    && fieldPK[i] == 'true') {

                    checkFirstTableIndex = i;
                }
            }

            if (checkFirstTableIndex == -1) {
                alert('Указанное поле отсутствует в описанной таблице (Главная таблица) либо ошибка с первичным ключём');
                allGoodFlag = 0;
                return;
            }

            for (var i = 0; i < fieldNames2.length; i++) {
                if (fieldNames2[i] == childTableField
                    && fieldPK2[i] == 'false') {
                    checkSecondTableIndex = i;
                }
            }

            if (checkSecondTableIndex == -1) {
                alert('Указанное поле имеет ошикбу в описанной таблице (Ссылаемая таблица)');
                allGoodFlag = 0;
                return;
            }

            if (fieldTypes[checkFirstTableIndex] != fieldTypes2[checkSecondTableIndex]) {
                alert('Родительское и Ссылаемое поля имеют разные типы данных');
                allGoodFlag = 0;
                return;
            }

            //!!!!!!!!!!!!!!!!!
            for (var j = 0; j < typesFields2.length; j++) {

                if (allGoodFlag == 1) {
                    for (var i = 0; i < precTypes.length; i++) {
                        if (precTypes[i] == typesFields2[j].value) {//'CHAR'
                            if (precisionFields2[j].value.match('^\\d{1,3}$')) {
                                precisionFields2[j].removeAttribute('placeholder');
                                precisionFields2[j].style.background = 'white';
                            } else {
                                precisionFields2[j].value = "";
                                precisionFields2[j].setAttribute('placeholder', 'введите цифру');
                                precisionFields2[j].style.background = 'pink';
                                alert('Задайте максимальный допустимый размер для поля');

                                //allGoodFlag = 0;

                                return;
                            }
                        }
                    }

                    if (typesFields2[j].value == 'INT' || typesFields2[j].value == 'INT UNSIGNED') {
                        if (precisionFields2[j].value.includes(".")) {
                            precisionFields2[j].value = precisionFields2[j].value.replace('.', ',');
                        }
                    }

                    if ($('input.field-name2')[j].value != $('input#child-table-field').val()) {
                        if ('INT' == typesFields2[j].value && !pkFields2[j].checked) {
                            if (precisionFields2[j].value.match('^(-\\d{1,4}|\\d{1,4})(.|,)(-\\d{1,4}|\\d{1,4})$')
                                && parseInt(precisionFields2[j].value.split(",")[0]) < parseInt(precisionFields2[j].value.split(",")[1])) {

                                //console.log('INT firmat - ok');
                            } else {
                                alert('INT имеет следующий шаблон: (минимальная граница) "." or "," (максимальная граница)');

                                //allGoodFlag = 0;
                                return;
                            }
                        }

                        if ('INT UNSIGNED' == typesFields2[j].value && !pkFields2[j].checked) {
                            if (precisionFields2[j].value.match('^\\d{1,4}(.|,)\\d{1,4}$')
                                && parseInt(precisionFields2[j].value.split(",")[0]) > 0
                                && parseInt(precisionFields2[j].value.split(",")[1]) > 0
                                && parseInt(precisionFields2[j].value.split(",")[0]) < parseInt(precisionFields2[j].value.split(",")[1])) {

                                //console.log('INT UNSIGNED firmat - ok');
                            } else {
                                alert('INT UNSIGNED имеет следующий шаблон: (минимальная граница).(максимальная граница), также числа должны быть положительными');

                                //allGoodFlag = 0;
                                return;
                            }
                        }
                    }
                }

                if ('DECIMAL' == typesFields2[j].value) {//decimal
                    if (precisionFields2[j].value.match('^\\d{1}$') ||
                        (precisionFields2[j].value.match('^\\d{1}((?=[.|,]\\d{1})|[.|,]\\d{1})$')
                            && (precisionFields2[j].value.split(".")[0] > precisionFields2[j].value.split(".")[1]
                                || precisionFields2[j].value.split(",")[0] > precisionFields2[j].value.split(",")[1]))) {

                        precisionFields2[j].removeAttribute('placeholder');
                        precisionFields2[j].style.background = 'white';
                    } else {
                        precisionFields2[j].value = "";
                        precisionFields2[j].setAttribute('placeholder', 'введите цифру');
                        precisionFields2[j].style.background = 'pink';
                        alert('Задайте пареметр для типа DECIMAL по шаблону *,*');

                        return;
                    }
                }
            }
            //!!!!!!!!!!!!!!!!!!!
            //222222222222222222222222
        }

        if (allGoodFlag == 1) {
            $.ajax({
                url: '/generate',
                type: 'post',
                data: {
                    resParams: resParams,
                    amountRows: amountRows,

                    fieldNames: JSON.stringify(fieldNames),
                    fieldTypes: JSON.stringify(fieldTypes),
                    fieldPrecisions: JSON.stringify(fieldPrecisions),
                    fieldPK: JSON.stringify(fieldPK),
                    tableName: tableName,

                    fieldNames2: JSON.stringify(fieldNames2),
                    fieldTypes2: JSON.stringify(fieldTypes2),
                    fieldPrecisions2: JSON.stringify(fieldPrecisions2),
                    fieldPK2: JSON.stringify(fieldPK2),
                    tableName2: tableName2,

                    childTableField: childTableField,
                    parentTableField: parentTableField
                },
                success: function () {
                    window.open('/showScript', '_blank');
                }
            });
        } else {
            return;
        }
    });
});
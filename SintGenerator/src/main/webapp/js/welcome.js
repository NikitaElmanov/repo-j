$(document).ready(function() {
    var counterOfRows = 0;
    var maxAmountOfColumns = 15;
    var stepOfExpandingMW = 30;
    var noPKTypes = [/*'VARCHAR'*/,'CHAR','BOOLEAN','DATE','DECIMAL'];
    var precTypes = ['CHAR',/*'VARCHAR'*/];
    var noPrecTypes = ['BOOLEAN'/*,'INT',INT UNSIGNED', 'DATE'*/];
    var typesFields, precisionFields, pkFields;

    $('input#addRow').click(function(){//adding new row (column) for table
        if (counterOfRows <= maxAmountOfColumns){

            var btnGen = $('#generate');
            var modelWindow = $('.modal-w');

            //changing height of modal window
            if (btnGen.offset().top + btnGen.height() >= modelWindow.offset().top + modelWindow.height() - btnGen.height()){
                var originalHeight = $('.modal-w').height();
                var newHeight = originalHeight + stepOfExpandingMW;
                $('.modal-w').css("height", newHeight);
            }

            $('.table-tbody').append('<ul class="table-tr tr-body">'+
                '<li class="table-td"><input type="text" class="field-name"/></li>'+
                '<li class="table-td">'+
                '<select class="type">'+
                '<option selected>VARCHAR</option>'+
                '<option>CHAR</option>'+
                '<option>INT</option>'+
                '<option>INT UNSIGNED</option>'+
                '<option>DECIMAL</option>'+
                '<option>DATE</option>'+
                '<option>BOOLEAN</option>'+
                '</select>'+
                '</li>'+
                '<li class="table-td"><input type="text" class="precision"/></li>'+
                '<li class="table-td">'+
                '<input type="radio" class="pk" name="pk"/>'+
                '</li>'+
                '<li class="table-td">'+
                '<input type="checkbox" class="del" name="del"/>'+
                '</li>'+
                '</ul>');

            counterOfRows++;

        } else {
            alert('Ограничение на кол-во полей в одной таблице равно 15');
        }
    });

    window.setInterval(function () {
        precisionFields = $('input.precision');
        typesFields = $('select.type');
        pkFields = $('input.pk');
        var activeFlag = 0;

        //handling types {if type = 'BOOLEAN','INT','INT UNSIGNED' then disable}
        for (var i = 0; i < typesFields.length; i++){
            var activeFlag = 0;

            for (var k = 0; k < noPrecTypes.length; k++){//!!!date is here temporarily, probably
                //'BOOLEAN','INT','INT UNSIGNED', 'DATE', 'VARCHAR'
                if (noPrecTypes[k] == typesFields[i].value){
                    activeFlag = 1;
                }
            }

            if (activeFlag == 1){
                precisionFields[i].removeAttribute('placeholder');
                precisionFields[i].style.background = 'white';
                precisionFields[i].value = '';
                precisionFields[i].setAttribute('disabled', 'true');
            } else {
                precisionFields[i].removeAttribute('disabled');
            }

            if ('DECIMAL' == typesFields[i].value){
                //precisionFields[i].style.background = 'white';
                precisionFields[i].setAttribute('placeholder', '5.2');
            }

            if ('DECIMAL' != typesFields[i].value){
                //precisionFields[i].style.background = 'white';
                precisionFields[i].removeAttribute('placeholder');
            }

            if ('INT' == typesFields[i].value || 'INT UNSIGNED' == typesFields[i].value || 'VARCHAR' == typesFields[i].value){
                if (pkFields[i].checked){
                    precisionFields[i].value = "";
                    precisionFields[i].disabled = true;
                } else {
                    precisionFields[i].disabled = false;
                }
            }
        }

        $('input.pk').click(function(){//make unchecked action for PK radiobutton
            if ($(this).is(':checked')){
                $(this).prop('checked', false);
            } else {
                $(this).prop('checked', true);
            }
        });




        //-----------------------------------------------
        //handling difficult condition enough (PK visibility and checking) 89653185774
        /*var disPKFlag = false;
        var index = 0;
        var counter = 0;

        for (var i = 0; i < typesFields.length; i++){
            for (var k = 0; k < noPKTypes.length; k++){
                if (noPKTypes[k] == typesFields[i].value){
                    pkFields[i].style.visibility = 'hidden';
                } else {
                    disPKFlag = true;
                    index = i;
                }
            }
        }

        if (disPKFlag){
            pkFields[index].style.visibility = 'visible';
        }*/
        //---------------------------------------------------

    }, 500);

    $('#del').click(function(){
        var delCheckBoxs = $('input.del');
        var amountOfChecked = 0;
        var originalHeight = $('.modal-w').height();
        var newHeight;

        delCheckBoxs.each(function(){
            if ($(this).is(':checked')){
                amountOfChecked++;
            }
        });

        if ($('input.del').length == amountOfChecked){
            alert('one column (filed) has to retain');
        } else {
            originalHeight -= stepOfExpandingMW*amountOfChecked;
            $('.modal-w').css("height", originalHeight);

            delCheckBoxs.each(function(){//if amountOfChecked = 0 then happens nothing
                if ($(this).is(':checked')){
                    $(this).parents('.table-tr').remove();
                }
            });
        }
    });

    $('input#insert').click(function(){
        if ($(this).is(':checked')){
            $('input#amount-rows').attr('disabled', false);
        } else {
            $('input#amount-rows').attr('disabled', true);
        }
    });

    var allGoodFlag = 1;
    var generateBtn = $('#generate');//running generate process
    generateBtn.click(function(){//handling of empty filed name error
        $('li.table-td input.field-name').each(function(){
            if ($.trim($( this ).val()).length == 0 || $.trim($( this ).val()).match('^[0-9]{0,}$')){
                $( this ).css('border', '2px solid red');
                $( this ).val('');
                $( this ).attr('placeholder', 'введите имя поля!');

                allGoodFlag = 0;

                //return;
            } else {
                allGoodFlag = 1;

                $( this ).css('border', '1px solid darkblue');
            }
        });
        //-----------------------------

        for (var j = 0; j < typesFields.length; j++){
            for (var i = 0; i < precTypes.length; i++){
                if (precTypes[i] == typesFields[j].value){//'CHAR',^!'VARCHAR'!
                    if (precisionFields[j].value.match('^\\d{1,3}$')){
                        precisionFields[j].removeAttribute('placeholder');
                        precisionFields[j].style.background = 'white';
                    } else {
                        precisionFields[j].value = "";
                        precisionFields[j].setAttribute('placeholder','введите цифру');
                        precisionFields[j].style.background = 'pink';

                        //allGoodFlag = 0;

                        return;
                    }
                }
            }

            if ('DECIMAL' == typesFields[j].value){//decimal
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

                    return;
                }
            }

            if (typesFields[j].value == 'INT' || typesFields[j].value == 'INT UNSIGNED'){
                if (precisionFields[j].value.includes(".")){
                    precisionFields[j].value = precisionFields[j].value.replace('.', ',');
                }
            }

            if ('INT' == typesFields[j].value && !pkFields[j].checked){
                if (precisionFields[j].value.match('^(-\\d{1,4}|\\d{1,4})(.|,)(-\\d{1,4}|\\d{1,4})$')
                    && parseInt(precisionFields[j].value.split(",")[0]) < parseInt(precisionFields[j].value.split(",")[1])){

                    //console.log('INT firmat - ok');
                } else {
                    alert('INT имеет следующий шаблон: (минимальная граница) "." or "," (максимальная граница)');

                    //allGoodFlag = 0;
                    return;
                }
            }

            if ('INT UNSIGNED' == typesFields[j].value && !pkFields[j].checked){
                if (precisionFields[j].value.match('^\\d{1,4}(.|,)\\d{1,4}$')
                    && parseInt(precisionFields[j].value.split(",")[0]) > 0
                    && parseInt(precisionFields[j].value.split(",")[1]) > 0
                    && parseInt(precisionFields[j].value.split(",")[0]) < parseInt(precisionFields[j].value.split(",")[1])){

                    //console.log('INT UNSIGNED firmat - ok');
                } else {
                    alert('INT UNSIGNED имеет следующий шаблон: (минимальная граница).(максимальная граница), также числа должны быть положительными');

                    //allGoodFlag = 0;
                    return;
                }
            }
        }


        var fieldNames = $('input.field-name');

        //handling of field's names (checking on unique)
        for (var i = 0; i < fieldNames.length; i++){
            for (var j = 0; j < fieldNames.length; j++){
                if (fieldNames[j].value == fieldNames[i].value && i != j){
                    alert('Имена полей должны быть уникальными. Необходимо исправить - \"' + fieldNames[j].value + '\"');

                    return;
                }
            }
        }

        typesFields = $('select.type');//checking PK's types (ONLY INT AND UNSIGNED INT)
        pkFields = $('input.pk');
        precisionFields = $('input.precision');
        for (var i = 0; i < typesFields.length; i++){
            for (var k = 0; k < noPKTypes.length; k++){
                if (typesFields[i].value == noPKTypes[k] && pkFields[i].checked){
                    alert('Первичным ключём может быть поле с типами INT or INT UNSIGNED');

                    return;
                }
            }

            if (typesFields[i].value == 'DATE'){
                if (precisionFields[i].value.match('^[0-9]{4}(,|.)[0-9]{4}$')){

                    if (precisionFields[i].value.includes(".")){
                        precisionFields[i].value = precisionFields[i].value.replace('.', ',');
                    }

                    var start = precisionFields[i].value.substr(0, precisionFields[i].value.indexOf(","));
                    var end = precisionFields[i].value.substr(precisionFields[i].value.indexOf(",")+1);

                    if (start >= end){
                        alert('First number has to be less then second (DATE Field).');
                        return;
                    }

                } else {
                    alert('Точность даты должна выглядеть как \'**** (.) or (,) ****\', где * означает цифру');
                    return;
                }
            }

            if (typesFields[i].value == 'DECIMAL'){
                if (precisionFields[i].value.includes(".")){
                    precisionFields[i].value = precisionFields[i].value.replace('.', ',');
                }
            }
        }

        var tableName = $('input#table-name').val();

        if (tableName == '' || tableName.match('^[0-9]{0,}$') || tableName.match('^\\s+$')){
            alert('Имя таблицы необходимо исправить');
            return;
        }

        var amountRows = $('input#amount-rows').val();

        if (!amountRows.match('^[0-9]+$') && $("input#insert").is(':checked')){
            alert('Кол-во строк необходимо исправить');
            return;
        } else if (amountRows == ''){
            amountRows = 0;
        }

        var insertScript = $("input#insert");
        //var updateScript = $("input#update");
        var addCreateScript = $("input#add-create-script");
        //var addIdScript = $("input#addId");

        var resParams = '';

        if (insertScript.is(':checked')){
            resParams += "insert;";
        }
        /*if (updateScript.is(':checked')){
            resParams += "update;";
        }*/
        if (addCreateScript.is(':checked')){
            resParams += "create;";
        }
        /*if (addIdScript.is(':checked')){
            resParams += "id;";
        }*/

        if (resParams.length < 1){
            alert('Необходимо выбрать хотя бы одну установку для генерации (create или/и insert)');
            return;
        }

        var fieldNames = [];
        $('input.field-name').each(function(){
            fieldNames.push($(this).val());
        });
        var fieldTypes = [];
        $('select.type').each(function(){
            fieldTypes.push($(this).val());
        });
        var fieldPrecisions = [];
        $('input.precision').each(function(){
            fieldPrecisions.push($(this).val());
        });
        var fieldPK = [];
        $('input.pk').each(function(){
            if ($(this).is(':checked')){
                fieldPK.push('true');
            } else {
                fieldPK.push('false');
            }
        });

        if (allGoodFlag == 1){
            $.ajax({
                url : '/generate',
                type: 'get',
                data : {
                    resParams : resParams,
                    fieldNames : JSON.stringify(fieldNames),
                    fieldTypes : JSON.stringify(fieldTypes),
                    fieldPrecisions : JSON.stringify(fieldPrecisions),
                    fieldPK : JSON.stringify(fieldPK),
                    tableName : tableName,
                    amountRows : amountRows
                },
                success : function(){
                    window.open('/showScript', '_blank');
                }
            });
        } else{
            return;
        }
    });
});
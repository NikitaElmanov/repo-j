$(document).ready(function() {
    var counterOfRows = 0;
    var maxAmountOfColumns = 15;
    var stepOfExpandingMW = 30;
    var noPKTypes = ['VARCHAR','CHAR','BOOLEAN','DATE','DECIMAL'];
    var precTypes = ['CHAR','VARCHAR'];
    var noPrecTypes = ['BOOLEAN','INT','UNSIGNED INT'/*, 'DATE'*/];
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
                '<option>UNSIGNED INT</option>'+
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
            alert('limited amount of column is 15');
        }
    });

    window.setInterval(function () {
        precisionFields = $('input.precision');
        typesFields = $('select.type');
        pkFields = $('input.pk');
        var activeFlag = 0;

        //handling types {if type = 'BOOLEAN','INT','UNSIGNED INT' then disable}
        for (var i = 0; i < typesFields.length; i++){
            var activeFlag = 0;

            for (var k = 0; k < noPrecTypes.length; k++){//!!!date is here temporarily, probably
                //'BOOLEAN','INT','UNSIGNED INT', 'DATE'
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

    var allGoodFlag = 1;
    var generateBtn = $('#generate');//running generate process
    generateBtn.click(function(){//handling of empty filed name error
        $('li.table-td input.field-name').each(function(){
            if ($.trim($( this ).val()).length == 0 || !$.trim($( this ).val()).match('[a-zA-Z][0-9]{0,}')){
                $( this ).css('border', '2px solid red');
                $( this ).val('');
                $( this ).attr('placeholder', 'input field name!');

                allGoodFlag = 0;
            } else {
                $( this ).css('border', '1px solid darkblue');
            }
        });
        //-----------------------------

        for (var j = 0; j < typesFields.length; j++){
            for (var i = 0; i < precTypes.length; i++){
                if (precTypes[i] == typesFields[j].value){//'CHAR','VARCHAR'
                    if (precisionFields[j].value.match('^\\d{1,3}$')){
                        precisionFields[j].removeAttribute('placeholder');
                        precisionFields[j].style.background = 'white';
                    } else {
                        precisionFields[j].value = "";
                        precisionFields[j].setAttribute('placeholder','input figure');
                        precisionFields[j].style.background = 'pink';

                        allGoodFlag = 0;
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
                    precisionFields[j].setAttribute('placeholder', 'input figure');
                    precisionFields[j].style.background = 'pink';

                    return;
                }
            }
        }


        var fieldNames = $('input.field-name');

        //handling of field's names (checking on unique)
        for (var i = 0; i < fieldNames.length; i++){
            for (var j = 0; j < fieldNames.length; j++){
                if (fieldNames[j].value == fieldNames[i].value && i != j){
                    alert('Field\'s names have to be unique. Name is \"' + fieldNames[j].value + '\"');

                    return;
                }
            }
        }

        fieldNames.each(function(){
            if ($(this).val().match("^\\d{0,}$")){
                alert('Error with field name!');
                allGoodFlag = 0;
            } else {
                console.log('ok');
            }
        });

        typesFields = $('select.type');//checking PK's types (ONLY INT AND UNSIGNED INT)
        pkFields = $('input.pk');
        precisionFields = $('input.precision');
        for (var i = 0; i < typesFields.length; i++){
            for (var k = 0; k < noPKTypes.length; k++){
                if (typesFields[i].value == noPKTypes[k] && pkFields[i].checked){
                    alert('PK can has only INT or UNSIGNED INT types');

                    return;
                }
            }

            if (typesFields[i].value == 'DATE'){
                if (precisionFields[i].value.match('^[0-9]{4}(,|.)[0-9]{4}$')){
                    var precisionString;

                    if (precisionFields[i].value.includes(".")){
                        precisionString = precisionFields[i].value;
                        precisionString = precisionString.replace('.', ',');
                    } else {
                        precisionString = precisionFields[i].value;
                    }

                    var start = precisionString.substr(0, precisionString.indexOf(","));
                    var end = precisionString.substr(precisionString.indexOf(",")+1);

                    if (start >= end){
                        alert('First number has to be less then second.');
                        return;
                    }

                } else {
                    alert('Precision for date has to look like \'**** (.) or (,) ****\' where * means figure.');
                    return;
                }
            }
        }

        var tableName = $('input#table-name').val();

        if (tableName == '' || tableName.match('^[0-9]{0,}$') || tableName.match('^\\s+$')){
            alert('Table name should be edited.');
            return;
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
            alert('You should choose at least');
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
                    tableName : tableName
                }
            });
        }
    });
});
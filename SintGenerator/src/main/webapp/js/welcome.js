$(document).ready(function() {
    var counterOfRows = 0;
    var maxAmountOfColumns = 15;
    var stepOfExpandingMW = 30;
    var pkTypes = ['INT','UNSIGNED INT'];
    var precTypes = ['CHAR','VARCHAR'];
    var noPrecTypes = ['BOOLEAN','INT','UNSIGNED INT', 'DATE'];
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
                '<input type="radio" class="pk" name="pk" style="visibility:hidden"/>'+
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

            for (var k = 0; k < noPrecTypes.length; k++){
                //'BOOLEAN','INT','UNSIGNED INT', 'DATE' !!!date is here temporarily, probably
                if (noPrecTypes[k] == typesFields[i].value){
                    activeFlag = 1;
                }
            }

            if (activeFlag == 1){
                precisionFields[i].removeAttribute('placeholder');
                precisionFields[i].style.background = 'white';
                precisionFields[i].setAttribute('disabled', 'true');
            } else {
                precisionFields[i].removeAttribute('disabled');
            }

            if ('DECIMAL' == typesFields[i].value){
                precisionFields[i].style.background = 'white';
                precisionFields[i].setAttribute('placeholder', '5.2');
            }
        }

        //-----------------------------------------------
        //handling difficult condition enough (PK visibility and checking)
        var disPKFlag = false;
        var index = 0;
        var counter = 0;

        for (var i = 0; i < typesFields.length; i++){

            for (var k = 0; k < pkTypes.length; k++){
                if (pkTypes[k] == typesFields[i].value){
                    disPKFlag = true;
                    index = i;
                } else {
                    pkFields[i].style.visibility = 'hidden';
                }
            }
        }

        pkFields.each(function(){
            if ($(this).is(':checked')){
                counter++;
            }
        });

        if (disPKFlag && counter <= 1){
            pkFields[index].style.visibility = 'visible';
        }

        pkFields.each(function(){
            if ($(this).css('visibility') == 'hidden'){
                $(this).prop('checked', false);
            }
        });
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
                if (precisionFields[j].value.match('^\\d{1}$') || precisionFields[j].value.match('^\\d{1}((?=[.|,]\\d{0,1})|[.|,]\\d{0,1})$')){
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

        if (allGoodFlag == 1){
            var insertScript = $("input#insert");
            var updateScript = $("input#update");
            var addCreateScript = $("input#add-create-script");
            var addIdScript = $("input#addId");

            var resParams = '';

            if (insertScript.is(':checked')){
                resParams += "insert;";
            }
            if (updateScript.is(':checked')){
                resParams += "update;";
            }
            if (addCreateScript.is(':checked')){
                resParams += "create;";
            }
            if (addIdScript.is(':checked')){
                resParams += "id;";
            }

            var fieldNames = $('input.field-name');
            var fieldTypes = $('select.type');
            var fieldPrecisions = $('input.precision');

            var fieldPK = $('input.pk');
            var idOfPKField;
            console.log(fieldPK.length);

        }
    });
});
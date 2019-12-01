$(document).ready(function() {
    var counterOfRows = 0;
    var maxAmountOfColumns = 15;
    var stepOfExpandingMW = 35;

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
                '<li class="table-td"><input type="text"/></li>'+
                '<li class="table-td">'+
                '<select class="type">'+
                '<option selected>The first option</option>'+
                '<option>The second option</option>'+
                '<option>The third option</option>'+
                '</select>'+
                '</li>'+
                '<li class="table-td"><input type="text" id="field-name"	/></li>'+
                '<li class="table-td">'+
                '<input type="checkbox" class="pk" name="pk"/>'+
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

    var generateBtn = $('#generate');
    generateBtn.click(function(){//handling of empty filed name error
        $('li.table-td input[type="text"]').each(function(){
            if ($.trim($( this ).val()).length == 0 || !$.trim($( this ).val()).match('[a-zA-Z][0-9]{0,}')){
                $( this ).css('border', '2px solid red');
                $( this ).val('');
                $( this ).attr('placeholder', 'input field name!');
            } else {
                $( this ).css('border', '1px solid darkblue');
            }
        });
    });

    window.setInterval(function () {
        var checkBoxPK = $('input.pk');
        var checkedFlag = true;

        checkBoxPK.each(function(){
            if ($(this).is(':checked')){
                checkedFlag = false;

                checkBoxPK.each(function(){

                    if ($(this).attr('disabled') == undefined) {
                        $(this).attr('disabled', 'disabled');
                    }
                });

                $(this).removeAttr('disabled');
            }

            if (checkedFlag){
                checkBoxPK.each(function(){
                    $(this).removeAttr('disabled');
                });
            }
        });
    }, 500);

    $('#del').click(function(){
        if ($('input.del').length > 1){
            var delCheckBoxs = $('input.del');
            var amountOfChecked = 0;

            delCheckBoxs.each(function(){
                if ($(this).is(':checked')){
                    amountOfChecked++;
                }
            });

            if ($('input.del').length == amountOfChecked){
                alert('one column (filed) has to retain');
            } else {
                delCheckBoxs.each(function(){//if amountOfChecked = 0 then happens nothing
                    if ($(this).is(':checked')){
                        $(this).parents('.table-tr').remove();
                    }
                });
            }
        }
    });
});
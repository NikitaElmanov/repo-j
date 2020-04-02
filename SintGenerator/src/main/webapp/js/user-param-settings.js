$(document).ready(function () {

    $('img#click-settings').click(function() {
        $('#container').attr('disabled', true);
        $('.modal-w-settings-param').css('display', 'block');
        $('#container').css('opacity', '0');
    });

    $('#close').click(function() {
        $('#container').attr('disabled', false);
        $('.modal-w-settings-param').css('display', 'none');
        $('#container').css('opacity', '1');
    });




    $('button#username-settings-btn').click(function() {

        var userNameForm = $('#username-form-container');
        if (userNameForm.prop('hidden') == false) {
            userNameForm.prop('hidden', true);
        } else {
            userNameForm.prop('hidden', false);
        }
    });




    $('img[alt="eye"]').mousedown(function() {
        $('input[name="newPassword"]').prop("type", "text");
    });
    $('img[alt="eye"]').mouseup(function() {
        $('input[name="newPassword"]').prop("type", "password");
    });

});
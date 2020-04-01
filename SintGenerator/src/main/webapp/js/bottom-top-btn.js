$(document).ready(function () {

    //condition to show or hide up and down buttons
    if (/*$(document).height() <= window.screen.height
        &&*/ $(document).height() < 1500) {
        $('img.sup-btns').css('display', 'none');
        console.log('hide');
    } else {
        $('img.sup-btns').css('display', 'inline-block');
        console.log('show');
    }

    //up and down click handlers
    $('img#bottom-btn').click(function() {
        window.scrollTo(0, document.body.scrollHeight);
        $(this).css('visibility', 'hidden');
        $('img#top-btn').css('visibility', 'visible');
    });

    $('img#top-btn').click(function() {
        window.scrollTo(0,0);
        $(this).css('visibility', 'hidden');
        $('img#bottom-btn').css('visibility', 'visible');
    });
});
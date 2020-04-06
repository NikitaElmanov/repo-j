$(document).ready(function () {

    $(window).scroll(function(){
        if ($(this).scrollTop() <= 0) {
            $('img#top-btn').css('visibility', 'hidden');
            $('img#bottom-btn').css('visibility', 'visible');
        }

        var bottomOfWindow = Math.max(window.pageYOffset, document.documentElement.scrollTop, document.body.scrollTop) + window.innerHeight === document.documentElement.offsetHeight

        if (bottomOfWindow) {
            $('img#top-btn').css('visibility', 'visible');
            $('img#bottom-btn').css('visibility', 'hidden');
        }
    });

    //condition to show or hide up and down buttons
    if (/*$(document).height() <= window.screen.height
        &&*/ $(document).height() < 1500) {
        $('img.sup-btns').css('display', 'none');
    } else {
        $('img.sup-btns').css('display', 'inline-block');
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
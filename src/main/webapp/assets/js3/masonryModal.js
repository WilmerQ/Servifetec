//$('.gridma1').masonry({
//    itemSelector: '.grid-item',
//    columnWidth: '.grid-sizer',
//    percentPosition: true
//});

// Modal with transition
$('.grid-item3').click(function (event) {
    console.log("aqui haciendo clic 1");
    // Check if not already open
    if (!$(this).hasClass('item-opened')) {
        // Values
        var elWidth = $(this).outerWidth() / 2;
//        var elPosition = this.getBoundingClientRect();
        var elPosition = this.getBoundingClientRect();

        // Store position
        $(this).attr('data-coord-left', $(this).css('left'));
        $(this).attr('data-coord-top', $(this).css('top'));

        // Transition effect
        $(this).css({
            top: elPosition.top,
            left: elPosition.left
        }).delay(100).css({
            top: '120px',
            left: '25%',
            zIndex: '99999',
            position: 'absolute'
//            width: '50%'
        }).addClass('item-opened');

        $('.grid-alpha3').css('display', 'block');

        // Scroll to the top
        $('html, body').animate({
            scrollTop: $('.gridma3').offset().top
        }, 650);
        $('.gridma3').css('overflow', 'visible');
    } else {
        $('.gridma3').css('overflow', 'hidden');
    }
});


// Close item Modal
$(document).on('click', function (e) {
    if ($('.item-opened').length > 0) {
        if (!$(e.target).closest('.grid-item3').length && !$(e.target).hasClass('item-opened')) {
            $('.grid-alpha3').fadeOut(650);

            $('.item-opened').css({
                top: $('.item-opened').data('coord-top'),
                left: $('.item-opened').data('coord-left'),
                marginLeft: ''
            });

            $('html, body').animate({
                scrollTop: ($('.gridma3').offset().top + parseFloat($('.item-opened').data('coord-top'))) - 30
            }, 650);

            setTimeout(function () {
                $('.grid-item3').css('z-index', '').removeClass('item-opened');
            }, 100);
            $('.gridma3').css('overflow', 'hidden');
        }
    }
});

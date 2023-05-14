(function ($) {
    "use strict";

    // Dropdown on mouse hover
    $(document).ready(function () {
        $('body').hide().fadeIn(2000);
        let currentLocation = window.location.pathname;
        $("a").click(function(event) {
            event.preventDefault();
            let href = this.getAttribute("href");
            if (href !== currentLocation) {
                location.href = href;
            }
        });
        if (currentLocation.includes("about")){
            $("#nav-about").addClass("active")
        } else if (currentLocation === ("/")){
            $("#nav-home").addClass("active")
        } else if (currentLocation.includes("menu")){
            $("#nav-menu").addClass("active")
        } else if (currentLocation.includes("reservation")){
            $("#nav-reservation").addClass("active")
        }


        $(".sizeSelect").change(function() {
            let selectId = $(this).attr("id");
            let productSizeSPrice = $(`#${selectId} option:selected`).attr("name")
            let productId = selectId.split("-")[1];
            let selectDisplayPrice = "price-" + productId;
            let displayPrice = "$" + productSizeSPrice
            $(`#${selectDisplayPrice}`).text(`${displayPrice}`);

            // let selectSizeValue = $(`#${selectId}`).val();
            // let sizeValueArr = selectSizeValue.split("-");
            // let selectDisplayId = "price-" + sizeValueArr[1];
            //
            // let displayPrice;
            // if (sizeValueArr[0] === "1") {
            //     displayPrice = parseInt(productSizeSPrice);
            // } else if (sizeValueArr[0] === "2") {
            //     displayPrice = parseInt(productSizeSPrice) + parseInt(productSizeSPrice) / 100 * 10;
            // } else {
            //     displayPrice = parseInt(productSizeSPrice) + parseInt(productSizeSPrice) / 100 * 20;
            // }
            // let newDisplayPrice = "$" + displayPrice;
            // $(`#${selectDisplayId}`).text(`${newDisplayPrice}`);
        })


        function toggleNavbarMethod() {
            if ($(window).width() > 992) {
                $('.navbar .dropdown').on('mouseover', function () {
                    $('.dropdown-toggle', this).trigger('click');
                }).on('mouseout', function () {
                    $('.dropdown-toggle', this).trigger('click').blur();
                });
            } else {
                $('.navbar .dropdown').off('mouseover').off('mouseout');
            }
        }
        toggleNavbarMethod();
        $(window).resize(toggleNavbarMethod);
    });
    
    
    // Back to top button
    $(window).scroll(function () {
        if ($(this).scrollTop() > 100) {
            $('.back-to-top').fadeIn('slow');
        } else {
            $('.back-to-top').fadeOut('slow');
        }
    });
    $('.back-to-top').click(function () {
        $('html, body').animate({scrollTop: 0}, 1500, 'easeInOutExpo');
        return false;
    });
    

    // Date and time picker
    $('.date').datetimepicker({
        format: 'L'
    });
    $('.time').datetimepicker({
        format: 'LT'
    });


    // Testimonials carousel
    $(".testimonial-carousel").owlCarousel({
        autoplay: true,
        smartSpeed: 1500,
        margin: 30,
        dots: true,
        loop: true,
        center: true,
        responsive: {
            0:{
                items:1
            },
            576:{
                items:1
            },
            768:{
                items:2
            },
            992:{
                items:3
            }
        }
    });
    
})(jQuery);



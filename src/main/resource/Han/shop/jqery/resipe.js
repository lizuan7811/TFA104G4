$(function () {
    $(".buyAll_icon").on("mouseenter", function () {
        // $(".buyAll_icon").addClass("shadow_orange");
        $(".product_list li").addClass("fontColor_red");
    });
    $(".buyAll_icon").on("mouseleave", function () {
        // $(".buyAll_icon").removeClass("shadow_orange");
        $(".product_list li").removeClass("fontColor_red");
    });
});

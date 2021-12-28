$(document).ready(function() {
    // 商品數量的加減按鈕
    //如果點擊"減少"按鈕 input text 數量會減少一
        $(".minus").click(function () {
            var inputin = $(this).parent().find('#inputvalue');
            var count = parseInt(inputin.val()) - 1;
            count = count < 1 ? 1 : count;
            inputin.val(count);
            inputin.change();
            return false;
        });
    //如果點擊"增加"按鈕 input text 數量會加一
        $(".plus").click(function () {
            var inputin = $(this).parent().find('#inputvalue');
            inputin.val(parseInt(inputin.val()) + 1);
            $inputin.change();
            return false;
        });
    
    var counts = 0; //加入購物車數量計數 預設值為0

    //當點擊"加入購物車" 進入以下動作
    $(".buy_icon").on("click", function (){ 
        var inputin = $(this).parent().find('#inputvalue');
        counts = inputin.val();
        $(".cart-counter").animate({   
            opacity: 1
        }, 300, function () {
    // 商品input text中的數量會顯示於 購物車的icon 
            $(this).text(counts);
        });
    }); 
});
$(function() {
    $(".buyAll_icon").on("mouseenter", function() {
        // $(".buyAll_icon").addClass("shadow_orange");
        $(".product_list li").addClass("fontColor_red");
    });
    $(".buyAll_icon").on("mouseleave", function() {
        // $(".buyAll_icon").removeClass("shadow_orange");
        $(".product_list li").removeClass("fontColor_red");
    });


	let idRecipe = (new URL(location.href)).searchParams.get('idRecipe');
	console.log(idRecipe);
    $.ajax({
        url: "Recipeservlet.html",
        data: {'idRecipe': idRecipe},
        type: "post",
        success: function(data) {

            let data2 = JSON.parse(data);

            $(".resipe_name").append(data2.name);
            $(".resipe_img").append('<img src="Recipe_BlobServlet?id=' + data2.idRecipe + '">');
			 						 
            var descripArray = new Array();
            var descripArray = data2.descrip.split("。");
            for (i = 0; i < descripArray.length; i++) {
                $(".step_list_inner ul").append('<li>' + descripArray[i] + '</li>');
            }
            var textArray = new Array();
            var textArray = data2.text.split("、");
            for (i = 0; i < textArray.length; i++) {
                //console.log(i)
                $(".item_list ul").append('<li>' + textArray[i] + '</li>');
            }
        }
    });
});
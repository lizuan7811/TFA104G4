$(function() {
	$('.your-class').slick({
		slidesToShow: 3,
		slidesToScroll: 1,
		autoplay: true,
		autoplaySpeed: 1000,
		dots: true

	});

	$.ajax({
		type: 'post',
		url: 'ShopServlet',
		data: {},
		success: function(data) {
			var x = 1;
			var array = JSON.parse(data);
			for (var i of array) {
				console.log(i.name);
				$("#product_name" + x).append(i.name);
				$("#product_unit" + x).before("$" + i.price);
				$("#product_unit" + x).prepend('/' + i.unit + i.gran + "g ±10%")
				$("#product_img" + x).append('<img data-idIngre="' + i.idIngre + '" class="top_img" src="Ingre_BlobServlet?id=' + i.idIngre + '">');
				x++;
			}
			$(".top_img").on("click", function(e) {
				let id = this.getAttribute("data-idIngre");
				location.href = 'product.html?idIngre=' + id;
			});
		}
	});

    
    var counts = 0;
    $(".top_cart_icon pointer").click(function () {
    //to number and increase to 1 on each click
       counts += +1;
       $(".cart-counter").animate({
    //show span with number
                 opacity: 1
             }, 300, function () {
    //write number of counts into span
                 $(this).text(counts);
             });
         }); 


});
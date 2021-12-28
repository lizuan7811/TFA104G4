$(function() {
		var host = window.location.host;
		var path = window.location.pathname;
		var webContext = path.substring(0, path.indexOf('/', 1));
		var serverEndPoint = "/ShopServlet";
		var endPointURL = "http://" + window.location.host + webContext + serverEndPoint;
	
	
	readyf();


    $.ajax({
        type:'post',
        url:'/TFA104G4/Shop_RandomRecipeServlet',
        data: {},
        success: function(data) {
            var array = JSON.parse(data);
            console.log(array);
			var temU="http://" + window.location.host + webContext;
            var x = 1;
            for (var i of array) {
                $("#rcpName" + x).append(i.name);
                $("#rcpImg" + x).attr('data-idRecipe', i.idRecipe);
                $("#rcpImg" + x).attr("src", temU+'/Recipe_BlobServlet?id=' + i.idRecipe);
                x++;
            }
            $(".resipe_img").on("click", function(e) {
                let id = this.getAttribute("data-idRecipe");
                location.href = 'resipe.html?idRecipe=' + id;
            });
        }
    });

$.ajax({
        type: 'post',
        url:'/TFA104G4/SearchAll_RecipeListServlet',
        data: {},
        success: function(data) {
            var array = JSON.parse(data);
            //console.log(array);
            //console.log(z);

            for (var x = 1; x < 4; x++) {
                for (var i of array) {
                    if ($("#rcpImg" + x).data("idrecipe") == i.idRecipe) {
                        //console.log(i.idRecipe)
                        $("#list" + x).append("<li data-idIngre='" + i.idIngre + "' data-ingreQuan='" + i.ingreQuan + "'>" + i.Name + i.ingreQuan + i.Unit + "</li>");
                        console.log(x);
                    }
                }
            }
        }
    });

	$('.your-class').slick({
		slidesToShow: 3,
		slidesToScroll: 1,
		autoplay: true,
		autoplaySpeed: 1000,
		dots: true

	});
	function readyf(){		
		$.ajax({
		url: endPointURL,
		type: 'post',
		data: {},
		success: function(data) {
			var x = 1;
			var array = JSON.parse(data);
			for (var i of array) {
				console.log(i.name);
				var tempStr="<input type='hidden' name='idIngre' value='"+i.idIngre+"'><input type='hidden' name='name' value='"+i.name+"'><input type='hidden' name='descrip' value='"+i.descript+"'><input type='hidden' class='price' name='price' value='"+i.price+"'><input type='hidden' class='price' name='photo' value='"+i.photo+"'><input type='hidden' class='quantity' name='quantity' size='1' value='1'><input type='hidden' class='action' name='action' value='ADD'>";
				$("#product_name" + x).append(i.name);
				$("#product_unit" + x).before("$" + i.price);
				$("#product_unit" + x).prepend('/' + i.unit + i.gran + "g ±10%")
				$("#product_img" + x).append("<img  class='top_img' src='data:image/jpg;base64,"+i.photo+"'>");
//				$("#product_img" + x).append('<img data-idIngre="' + i.idIngre + '" class="top_img" src="Ingre_BlobServlet?id=' + i.idIngre + '">');
				$("#product_img" + x).append(tempStr);
			x++;
			}
			$(".top_img").on("click", function(e) {
				let id = this.getAttribute("data-idIngre");
				location.href = 'product.html?idIngre=' + id;
			});
		}
	});
	}

function appInps(){
	$(".top_box4").after();
}


//	$.ajax({
//		url: endPointURL,
//		type: 'post',
//		data: {},
//		success: function(data) {
//			var x = 1;
//			var array = JSON.parse(data);
//			for (var i of array) {
//				console.log(i.name);
//				$("#product_name" + x).append(i.name);
//				$("#product_unit" + x).before("$" + i.price);
//				$("#product_unit" + x).prepend('/' + i.unit + i.gran + "g ±10%")
//				$("#product_img" + x).append('<img data-idIngre="' + i.idIngre + '" class="top_img" src="Ingre_BlobServlet?id=' + i.idIngre + '">');
//				x++;
//			}
//			$(".top_img").on("click", function(e) {
//				let id = this.getAttribute("data-idIngre");
//				location.href = 'product.html?idIngre=' + id;
//			});
//		}
//	});
//$.ajax({
//        type: 'post',
//        url: 'ShopServlet',
//        data: {},
//        success: function(data) {
//            var x = 1;
//            var array = JSON.parse(data);
//            for (var i of array) {
//                //console.log(i.name);
//                $("#product_name" + x).append(i.name);
//                $("#product_unit" + x).before("$ " + i.price);
//                $("#product_unit" + x).prepend(' / ' + i.unit);
//                $("#product_img" + x).append('<img data-idIngre="' + i.idIngre + '" class="top_img" src="Ingre_BlobServlet?id=' + i.idIngre + '">');
//                x++;
//            }
//            $(".top_img").on("click", function(e) {
//                let id = this.getAttribute("data-idIngre");
//                location.href = 'product.html?idIngre=' + id;
//            });
//        }
//    });
    
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




 $(".type_img").on("click", function(e) {
        let id = this.getAttribute("data-idIngre");
        location.href = 'product.html?idIngre=' + id;
    });

});
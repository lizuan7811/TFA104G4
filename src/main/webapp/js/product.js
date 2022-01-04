$(function() {

    $(".plus_icon").click(function() {
        // alert("+");
        var n = $(this).siblings(".product_input").val();
        n++;
        $(this).siblings(".product_input").val(n);

    })
    $(".minus_icon").click(function() {
        // alert("-");
        var n = $(this).siblings(".product_input").val();
        if (n == 1) {
            return false;
        }
        n--;
        $(this).siblings(".product_input").val(n);
    })

	let  idIngre = (new URL(location.href)).searchParams.get('idIngre');
	console.log(idIngre);
    $.ajax({
        url: "ingreservlet1.html",
        data: {'idIngre': idIngre},
        type: "post",
        success: function(data) {

            let data2 = JSON.parse(data);
			console.log(data2.name);
            $(".product_name").append(data2.name);
            $(".product_list ul").append(data2.descrip);
            $(".product_price").append(data2.price);
            $(".product_unit").append('/' + data2.unit);
            $(".product_gran").append(data2.gran + "g ±10%");
            $(".product_img").append('<img src="Ingre_BlobServlet?id=' + data2.idIngre + '">');
        }
    });

});
$(function() {

    $(".search").click(function() {

        $.ajax({
            url: "search_productServlet",
            data: { "idIngre": $("#idIngre").val() },
            type: "post",
            success: function(data) {

                let data2 = JSON.parse(data);

                console.log(data2);
                $("#name").val(data2.name);
                $("#idIngreType").val(data2.idIngreType);
                $("#descrip").val(data2.descrip);
                $("#purPrice").val(data2.purPrice);
                $("#price").val(data2.price);
                $("#unit").val(data2.unit);
                $("#gran").val(data2.gran);
                $("#sell").val(data2.sell);
                $("#launch").val(Number(data2.launch));
                $('#view').attr('src', 'Ingre_BlobServlet?id=' + data2.idIngre);
            },
            error: function() {
                alert("查無此商品，請輸入正確商品編號");
            }

        });
    });

    $(".update").click(function() {

        let input_file = document.getElementById("file").files[0];
        var idIngreType = $("#idIngreType").val();
        var idIngre = $("#idIngre").val();
        var name = $("#name").val();
        var descrip = $("#descrip").val();
        var purPrice = $("#purPrice").val();
        var price = $("#price").val();
        var unit = $("#unit").val();
        var gran = $("#gran").val();
        var sell = $("#sell").val();
        var launch = $("#launch").val();

        var formData = new FormData();

        formData.append('file', input_file);
        formData.append('idIngreType', idIngreType);
        formData.append('idIngre', idIngre);
        formData.append('name', name);
        formData.append('descrip', descrip);
        formData.append('purPrice', purPrice);
        formData.append('price', price);
        formData.append('unit', unit);
        formData.append('gran', gran);
        formData.append('sell', sell);
        formData.append('launch', launch);

        $.ajax({
            type: 'post',
            url: 'http://localhost:8081/tbmgf_v1/Update_ProductServlet',
            data: formData,
            dataType: "json",
            processData: false,
            contentType: false,

            success: function(data) {
                alert("更新成功");
            },

            error: function(data) {
                alert("更新失敗");
            }

        });
    });

});
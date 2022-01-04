$(function() {

    $("#search").click(function() {

        $.ajax({
            url: "Search_RecipeServlet",
            data: { "idRecipe": $("#idRecipe").val() },
            type: "post",
            success: function(data) {

                let data2 = JSON.parse(data);

                console.log(data2);
                $("#name").val(data2.name);
                $("#descrip").val(data2.descrip);
                $("#text").val(data2.text);

                $('#view').attr('src', 'Recipe_BlobServlet?id=' + data2.idRecipe);
            },
            error: function() {
                alert("查無此菜單，請輸入正確菜單編號");
            }

        });
    });

    $("#update").click(function() {

        let input_file = document.getElementById("file").files[0];
        var idRecipe = $("#idRecipe").val();
        var descrip = $("#descrip").val();
        var name = $("#name").val();
        var text = $("#text").val();


        var formData = new FormData();

        formData.append('file', input_file);
        formData.append('idRecipe', idRecipe);
        formData.append('descrip', descrip);
        formData.append('name', name);
        formData.append('text', text);


        $.ajax({
            type: 'post',
            url: 'Update_RecipeServlet',
            data: formData,
            dataType: "json",
            processData: false,
            contentType: false,

            success: function() {
                alert("更新成功");
            },

            error: function() {
                alert("更新失敗");
            }

        });
    });

});
$(function(){
    var pathname=window.location.pathname;
    var host=window.location.host;
    var webContext = pathname.substring(0, pathname.indexOf('/', 1));
   

    $("#adminUsername").on("change",function(){
        $(".UNRespDiv").text("");
        var username=$("#adminUsername").val().trim();
        if(username==null|| username==""){
            $(".UNRespDiv").text("請輸入管理者帳號!");
        }
    });
    $("#adminPassword").on("change",function(){
        $(".PSRespDiv").text("");
        var password=$("#adminPassword").val().trim();
        if(password==null || password==""){
            $(".PSRespDiv").text("請輸入管理者密碼!");
        }
    });

    $(".form_input_submitBtn").on("click",function(){
        var username=$("#adminUsername").val().trim();
        if(username==null|| username==""){
            $(".UNRespDiv").text("請輸入管理者帳號!");
        }
        var password=$("#adminPassword").val().trim();
        if(password==null || password==""){
            $(".PSRespDiv").text("請輸入管理者密碼!");
        }

        if(username==null|| username==""||password==null || password==""){
            return;
        }
        $.ajax({
            url:"http://" + window.location.host + webContext +"/LoginServlet",
            data:{"metChoice":"login","username":username,"password":password},
            type:"POST",
            success:function(data){
                var dd=JSON.parse(data);
                console.log(dd);
                alert("Success Login!");
                window.location=dd["address"];

            },
            error:function(data){
                alert("未連接後端伺服器!");
            }
        });
    })



});
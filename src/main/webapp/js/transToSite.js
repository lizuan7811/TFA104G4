$(function(){
    $(".aboutUs").on("click",function(){
        $.ajax({
            data:{"transToSite":"aboutUs"},
            url:servletPath(),
            type:"POST",
            success:function(data){
                console.log(data);
                window.location=data;
            }
        });
    });
    $(".shopCity").on("click",function(){
        $.ajax({
            data:{"transToSite":"shopCity"},
           url:servletPath(),
            type:"POST",
            success:function(data){
               console.log(data);
               window.location=data;
            }
        });
    });
    $(".eatLife").on("click",function(){
        $.ajax({
            data:{"transToSite":"eatLife"},
            url:servletPath(),
            type:"POST",
            success:function(data){
                console.log(data);
                window.location=data;
            }
        });
    });
    $(".custLogin").on("click",function(){
        $.ajax({
            data:{"transToSite":"custLogin"},
            url:servletPath(),
            type:"POST",
            success:function(data){
                console.log(data);
                window.location=data;
            }
        });
    });
    $(".historyOrder").on("click",function(){
        $.ajax({
            data:{"transTo":"historyOrder"},
            url:servletPath(),
            type:"POST",
            success:function(data){
                console.log(data);
                window.location=data;
            }
        });s
    });

    function servletPath(){
        var path=window.location.pathname;
        var host=window.location.host;
        var contextPath=path.substring(0,path.indexOf('/',1));
        var serverEndPoint="/otherWeb/TransToOther.html";
        var realPath="http://"+host+contextPath+serverEndPoint;
        return realPath;
    }
});
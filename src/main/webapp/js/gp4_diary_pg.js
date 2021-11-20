$(function(){
    $(".like_btn").click(function()
    {
        $.ajax({
            url:"usermethod.html",
            data:{"curDiaryID":"10","curCustID":"15","metChoice":"clickLike"},
            type:"post",
            success:function(respLikeNum)
            {
                var tun=document.getElementsByClassName(".thumbs_up_num");
				$(".thumbs_up_num").html(respLikeNum);
				/*console.log($(".thumbs_up_num").val(respLikeNum));
				console.log(respLikeNum);
                console.log("接收到讚數"+respLikeNum);*/
            },
            error:function(respLikeNum)
            {
                console.log("沒有接收到資料!");
            }
        
        });
    });
});
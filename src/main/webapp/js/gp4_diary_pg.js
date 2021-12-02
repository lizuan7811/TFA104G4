$(function(){
    
//	按讚功能
	$(".like_btn").click(function()
    {
        $.ajax({
            url:"usermethod/UserServlet",
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
	//檢舉功能
	$(".report_btn").click(function(){
		$.ajax({
			url:"usermethod/UserServlet",
			data:{"metChoice":"commentReport","custID":11,"diaryID":10,"reportReason":$(".main_article_p").text()},
			type:"post",
			success:function(data)
			{
				alert("使用檢舉功能!");
			},
			error:function()
			{
				console.log($(".main_article_p").val());
			}
		});
	});
	
//	加好友
$(".add_friend").click(function()
{
	$.ajax({
		url:"usermethod/UserServlet",
		data:{"metChoice":"addFriend","custID":13,"myFriendID":1},
		type:"POST",
		success:function(data)
		{
			alert("成功送出好友申請!");
			// this.addClass(".successAddFriend");		
		},
		error:function()
		{
			
		}
	});
	
});
	
	
	
	
//	轉址到關於我們

//	轉址到食健商城

//	轉址到食健生活

//	轉址到會員登入
	
	
});
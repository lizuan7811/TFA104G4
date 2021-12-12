$(function(){
    $(".report_btn").on("click", function(){
        Swal.fire({
            position: 'center',
            title: '檢舉',
            html: `<input type="text" class="swal_input" placeholder="請輸入檢舉理由">`,
            confirmButtonText: '送出',
            focusConfirm: false
        });
		sendReport();
		
		function sendReport(){
			$(".swal2-actions button").on("click",function(){
				var commReport=$(".swal2-content input").val();
				$.ajax({
					url:"usermethod/UserServlet",
					data:{"metChoice":"diaryReport","custID":6,"diaryID":2,"reportReason":$(".swal2-content input").val()},
//					data:{"metChoice":"diaryReport","custID":$(".author_name").attr("data-custId"),"diaryID":$(".author_name").attr("data-diaryId"),"reportReason":$(".swal2-content input").val()},

					type:"POST",
					success:function(data){
							console.log("完成送出檢舉!");
					},
					error:function(){
						
					},
				});
			})
		};
	});
	
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
	//評論檢舉檢舉功能
	// $(".report_btn").click(function(){
	// 	$.ajax({
	// 		url:"usermethod/UserServlet",
	// 		data:{"metChoice":"commentReport","custID":11,"diaryID":10,"reportReason":$(".main_article_p").text()},
	// 		type:"post",
	// 		success:function(data)
	// 		{
	// 			// alert("使用檢舉功能!");
	// 		},
	// 		error:function()
	// 		{
	// 			console.log($(".main_article_p").val());
	// 		}
	// 	});
	// });
	
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
	
	
	
	
});


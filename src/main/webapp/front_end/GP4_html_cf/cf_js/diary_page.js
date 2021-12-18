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
				var diaryReport=$(".swal2-content input").val();
				$.ajax({
					url:servletPath(),
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

	$("#btn").click(function(){
		sendMSG();
	});

	//	按讚功能
	$(".like_btn").click(function()
    {
        $.ajax({
            url:servletPath(),
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
	
	
//	加好友
$(".add_friend").click(function()
{
	$.ajax({
		url:servletPath(),
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
	
	var repCustNickName;
	var repDiaryID=$(".author_name").attr("data-diaryid");
	var repComm;
	var commentID;
	
	i=1;
	function sendMSG(){
	    fnsubmit();
	    //submit後清空暱稱及留言的內容
	    $("#nametxt").val("");
	    $("#text").val("");
	    
	}
	
	function fnsubmit()
	{
	var odiv=document.getElementById("box");
	var oem=odiv.getElementsByTagName("em")[0];
	var otext=document.getElementById("text");
	var nametxt=document.getElementById("nametxt");
	var add_li=document.createElement("li");
	var o_span=document.createElement("span");
	var o_btn=document.createElement("button");
	o_btn.textContent="檢舉";
	var today=new Date();
	
	
	// 日期
	var currentDateTime =
	"< "+ today.getFullYear()+'年'+
	(today.getMonth()+1)+'月'+
	today.getDate()+'日' + "  "+today.getHours()+':'+ today.getMinutes();
	
	//如果沒有輸入內容 將跳出提醒
	if(otext.value=="")
	{
	alert("請填寫留言內容");
	return;
	}
	
	
	oem.style.display="none";
	o_span.style.position="absolute";
	
	o_span.style.top="-20px";
	o_span.style.right="20px";
	o_span.style.background="#faeae3";
	add_li.style.position="relative";
	add_li.index=i;
	
	add_li.style.background="#faeae3";
	add_li.style.marginBottom="10px";
	add_li.style.bottom="10px";
	add_li.style.lineHeight="50px";
	add_li.style.opacity= "0.5";
	add_li.style.width= "97.7%";
	add_li.style.marginLeft = "10px";
	o_btn.style.width="30px";
	o_btn.style.height="50px";
	o_btn.style.float="right";
	repCustNickName=nametxt.value;

	add_li.classList.add("box_li");
	o_btn.classList.add("box_btn");
	
	var str_name=document.createTextNode(i+"樓 "+nametxt.value+": "); // 幾樓和暱稱
	var str=document.createTextNode(otext.value +currentDateTime  +">"); //留言內容及發表時間
	var strspan=document.createTextNode(otext.value);

	repComm=otext.value;
	console.log(add_li);
	add_li.appendChild(o_span);
	
	add_li.appendChild(o_btn);
	o_span.style.display="none";
	o_span.appendChild(strspan);
	add_li.appendChild(str_name);
	add_li.appendChild(str);
	odiv.appendChild(add_li);
	i++;
	$.ajax({
		url:servletPath(),
		data:{"metChoice":"comment","diaryID":repDiaryID,"custNickName":repCustNickName,"reportReason":repComm},
		type:"POST",
		success:function(data){
			alert("留言已送出");
			commentID=JSON.parse(data);

			$(".box_li").attr("data-commentID",commentID.commentID);
			$(".box_btn").attr("data-commentID",commentID.commentID);
			$(".box_btn").attr("data-custNickName",repCustNickName);
			$(".box_btn").attr("data-repDiaryID",repDiaryID);
			$(".box_btn").on("click",sendCommReport(this));
		},
		error:function(){

		}
	});
	}
	// 評論檢舉檢舉功能
	function sendCommReport(){
		
		$(".swal2-actions button").on("click",function(){
			Swal.fire({
				position: 'center',
				title: '檢舉',
				html: `<input type="text" class="swal_input" placeholder="請輸入檢舉理由">`,
				confirmButtonText: '送出',
				focusConfirm: false
			});
			var commReport=$(".swal2-content input").val();
			$.ajax({
				url:servletPath(),
				data:{"metChoice":"commentReport","custNickName":$(this)["data-custNickName"],"diaryID":$(this)["data-repDiaryID"],"reportReason":$(".swal2-content input").val()},
				type:"POST",
				success:function(data){
						console.log("完成送出留言檢舉!");
				},
				error:function(){
					console.log("送出留言檢舉失敗!");
				},
			});
		})
	}

	function servletPath(){
		var host = window.location.host;
		var path = window.location.pathname;
		var webContext = path.substring(0, path.indexOf('/', 1));
		var serverEndPoint = "/usermethod/UserServlet";
		var endPointURL = "http://" + window.location.host + webContext + serverEndPoint;
		return endPointURL;
	}
	
});


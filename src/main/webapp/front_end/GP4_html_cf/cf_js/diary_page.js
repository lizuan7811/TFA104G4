$(function(){
	console.log($(".author_name").attr("data-custid"));
	console.log($(".author_name").attr("data-id"));

	//-----------------臉書分享按鈕-----------------
	var fbButton = document.getElementById('fb-share-button');
	var url = window.location.href;
	// 以下連結屬於測試用test_url
	var test_url = "https://www.facebook.com/sharer/sharer.php?s=100&p[url]=http://www.example.com&p[images][0]=&p[title]=Title%20Goes%20Here&p[summary]=Description%20goes%20here!";

	fbButton.addEventListener('click', function() {
	// http://www.facebook.com/sharer.php?u=+ 要分享的網址 + "&t=" + 標題
	// https://www.facebook.com/sharer/sharer.php?u=[URL]
    window.open('https://www.facebook.com/sharer/sharer.php?u='+test_url, 'facebook-share-dialog',
	'width=800,height=600');
    return false;
	});

	// --------日誌單獨篇查詢--------
    var diaryid = (new URL(location.href)).searchParams.get("diaryid");
    // alert(diaryid);

    function ServletPath(){
        var host = window.location.host;
        var path = window.location.pathname;
        var webCtx = path.substring(0, path.indexOf('/', 1));
        var endPointURL = "http://" + window.location.host + webCtx + "/MainDiaryServlet";
		console.log(endPointURL);
        return endPointURL;
    }

    $.ajax({
        url: ServletPath(),
        data: {"action": "GET_DIARY","diaryid": diaryid},
        type: "post",
        success: function(data) {
            let data2 = JSON.parse(data);
            $("#breadcrumb_type").append(data2.diarytype); //breadcrumb文章分類類別
            $("#breadcrumb_subject").append(data2.subject); //breadcrumb文章標題
            $(".article_title").append(data2.subject); //文章主題
            $(".author_name").append("作者: 加法真是太神奇了 (會員編號:"+ data2.custid + ")"); //作者暱稱+帳號
            var dtstr= Date.parse(data2.createdtime);
            var dt = new Date(dtstr);
            $("#createdtime").append(dt.toLocaleString()); //文章創建時間
            $("#one").append("<img src='data:image/jpg;base64,"+data2.Photo_video_1+"'>") //文章圖片1
            $("#two").append("<img src='data:image/jpg;base64,"+data2.Photo_video_2+"'>") //文章圖片2
            $("#three").append("<img src='data:image/jpg;base64,"+data2.Photo_video_3+"'>") //文章圖片3
            $(".main_article").append("<p style='white-space: pre-line;'>"+data2.text+"</p>") //文章主要內容
            // $("#one").append("<img src='/PicReader?diaryID="+data2.diaryid+"'>")
            // src='data:image/jpg;base64,"+photoStr+"'>
                  
            
        }
    });

var rowCount=0; //留言樓層計數
		//--------------查詢 單篇日誌的所有留言--------------
		$.ajax({
			url:servletPath(),
			data:{"metChoice":"ALL_COMMENT","diaryid": $(".author_name").attr("data-diaryid")},
			type:"POST",
			// dataType:"JSON",
			success:function(data){
				console.log(data);
				var array = JSON.parse(data);
				 console.log(data==undefined?"YES":"NO");
				if(data=="" || data==undefined)
				{
					return;
				}
				
				// console.log(diaryid);
				// console.log(data);
				var num = 1;
				for(var key in array){
					var comment_str=array[key].CommentText;
					// console.log(comment_str);
					var dt=array[key].CreatedTime;
					var nickname=array[key].CustNickName;
					
					var com ="<li class='comm'>"+"<button class='commentbtn'>檢舉</button>"+num+"樓"+" "+nickname+":"+" "+comment_str+"<"+dt+">"+"</li>";
					
					// console.log(com);
					$("#box").append(com);
					num++;
					rowCount=num;
				}
			
			
			$(".commentbtn").addClass("commbtn");
			$(".comm").addClass("commClass");
			
			

			},
			error:function(){

			}
		});


    $(".report_btn").on("click", function(){
        Swal.fire({
            position: 'center',
            title: '檢舉',
            html: `<input type="text" class="swal_input" placeholder="請輸入檢舉理由">`,
            confirmButtonText: '送出',
            focusConfirm: false
        });
		sendDiaryReport();
		function sendDiaryReport(){
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
            data:{"curDiaryID":$(".author_name").attr("data-diaryid"),"curCustID":$(".author_name").attr("data-custid"),"metChoice":"clickLike"},
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
		data:{"metChoice":"addFriend","custID":$(".author_name").attr("data-custid"),"myFriendID":$(".author_name").attr("data-id")},
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
	o_btn.classList.add("swal2-sendComment");
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
			$(".swal2-sendComment").click(sendCommReport(this));
		},
		error:function(){

		}
	});
	}
	// 評論檢舉檢舉功能

	var commReport;
	var custNickNameD;
	var repDiaryIDD;
	var commentID;
	function sendCommReport(){
		$(".swal2-sendComment").on("click",function(){
			custNickNameD=$(this).attr("data-custnickname");
			repDiaryIDD=$(this).attr("data-repdiaryid")
			commentID=$(this).attr("data-commentid");
			console.log(custNickNameD);
			console.log(repDiaryIDD);
			console.log(commentID);

			Swal.fire({
				position: 'center',
				title: '檢舉留言',
				html: `<input type="text" class="swal_input" placeholder="請輸入檢舉理由">`,
				confirmButtonText: '送出',
				focusConfirm: false
			});
			$(".swal2-confirm").click(function(){sdCommdRep();});
		});
	}

	function sdCommdRep(){
		commReport=$(".swal2-content .swal_input").val();
		console.log(commReport);
		$.ajax({
			url:servletPath(),
			data:{"metChoice":"commentReport","custNickName":custNickNameD,"repDiaryID":repDiaryIDD,"commentID":commentID,"reportReason":commReport},
			type:"POST",
			success:function(data){
					console.log("送出留言檢舉成功!");
			},
			error:function(){
				console.log("送出留言檢舉失敗!");
			},
		});
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


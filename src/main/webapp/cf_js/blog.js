$(function() {
	$(".chatlist").on("focus", function() {
		$(this).removeClass(".chatlist");  //移除原本聊天列表的css樣式
		$(".title").css("display", "none");  //隱藏抬頭: 聊天列表
		//加入html語法 並設定class等...
		$(this).html('<div class="chat_online"><div class="chat_name">貪吃的貓<br></div><textarea class="chattxt" type="text" readonly></textarea><input class="input_mesg" type="text"  placeholder="訊息內容..."/><input class="send_btn" type="submit" value="Send"/></div>');
	});

	var inputStr = function(user) {
		var str = "<li class=\"one_friend\" type=\"button\" name= \"friend\" value=\"\" data-frID=\"" + user.idCustomer + "\"/>" + user.nickName + "<li class=\"iconlist\"><span data-frID=\"" + user.idCustomer + "\"class=\"icon-user\"></span><span data-frID=\"" + user.idCustomer + "\" class=\"icon-bubbles3\"></span></li></li>";
		//        var str="<li><img width=\"900\" height=\"450\" src=\"data:image/png;base64,"+decodeURI(user.profic)+"\"/><input class=\"friend\" type=\"button\" name= \"friend\" value=\""+user.nickName+"\" data-frID=\""+user.idCustomer+" onclick=\"clickfunc()\"\"/></li>";
		return str;
	};

	$(".icon-bubbles3").click(function() {
		console.log("$(this).text()" + $(this).text());
		console.log("$(this).attr(\"data-frID\")" + $(this).attr("data-frID"));
		$(".chatlist").focus();
	});

  $(".aside_list").click(function(e) {
    if (e.stopPropagation) {
      e.stopPropagation();
    }
    else {
      e.cancelBubble = true;
    }

    $(document).bind('click', function() {
      $('.one_friend').css('display', 'none');
      $('.icon-user').css('display', 'none');
      $('.icon-bubbles3').css('display', 'none');
    });
  });

	var count = 0;
	var innerCount = 0;
	$(".aside_list .all_friend").click(function() {
		$('.one_friend').css('display', 'inline-block');
		$('.icon-user').css('display', 'inline-block');
		$('.icon-bubbles3').css('display', 'inline-block');
		if (count >= 1) {
			return;
		}
		$.ajax({
				url: "usermethod/UserServlet",
				data: { "metChoice": "friendList", "custID": 1 },
				type: "POST",
				dataType: "JSON",
				success:function(friendList) {
					count++;
					innerCount++;
					// console.log(friendList);
					// console.log(JSON.stringify(friendList));
					var jsObj = JSON.stringify(friendList);
					var jsObj2 = JSON.parse(jsObj);
					if (innerCount < 1) {
						for (var key in jsObj2) {
							for (var key1 in jsObj2[key]) {
								for (var key2 in jsObj2[key][key1]) {
									var ans = jsObj2[key][key1][key2];
									$(".aside_list .all_friend").after(inputStr(ans)).fadeIn(20000);
								}
							}
						}
						// $(".all_friend .one_friend").on("click","input",clickfunc(this)).cancelBubble;
						$(".all_friend .one_friend").on("click", "li", clickfunc(this)).cancelBubble;
					}
				},
				error: function() {
				}
			}, false);
	});

	// 列表中好友的點擊事件
	var clickfunc = function() {
		$(".aside_list .one_friend").click(function() {
			console.log($(this).text());
			console.log($(this).attr("data-frID"));
			id = $(this).attr("data-frID");
			$.ajax({
					url: "#",
					data: { "": "" },
					type: "POST",
					dataType: "JSON",
					success: function() {
						alert("11");
					},
					error: function() {
					}
				});
		});

	};
});
	//
	// var userName;
	// var webskchatfunction=function(){
	//
	//   var ws=new WebSocket("ws://localhost:8081/ChatEndpoint")
	//   ws.onopen=function()
	//   {
	//     $("userName").html("Client:"+userName+"<span style'float:right;color:green'>線上</span>");
	//
	//   }
	//   // 收到後端傳送的資料後呼叫執行
	//   ws.onmessage=function(event)
	//   {
	//     var dataStr=evt.data;
	//     var res=JSON.parse(dataStr);
	//
	//     if(res.isSystem)
	//     {
	//       var names=res.message;
	//       var userlistStr="";
	//       var broadcastListStr="";
	//       for(var name of names)
	//       {
	//         if(name!=userName){
	//           // userlisStr+="<li class=\"\""><a onclick="showCaht(\""+name+"\"")'"+name+"/></li>";
	//           // broadcastListStr+="<li class=\"\" style=\"color:red;font-family:宋體\">你的好友"+name+"上線</li>";
	//
	//         }
	//       }
	//
	//       $("#userList").html(userLisStr);
	//       $("#broadcastList").html(broadcastListStr);
	//
	//
	//     }else
	//     {
	//
	//     }
	//
	//
	//   }
	//   ws.onclose=function()
	//   {
	//
	//   }



	// $.ajax({
	//   url:"",
	//   data:{},
	//   type:"post",
	//   success:function(){
	//     $("#username").html("Client:"+res+"<span style='float:right color=green'>線上</span>")
	//   },
	//   async:false;

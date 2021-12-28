$(function() {
	var selfAcc;
	var friAcc;
	var message;
	var count = 0;
	var innerCount = 0;
	var countFri=0;
	var innerCountFri=0;
	 readyload();
		
	$(".chatlist").on("focus", function() {
		$(this).removeClass(".chatlist"); //移除原本聊天列表的css樣式
		$(".title").css("display", "none"); //隱藏抬頭: 聊天列表
		//加入html語法 並設定class等...
		$(this).html(
			'<div class="chat_online"><div class="chat_name">貪吃的貓<br></div><div class=\"talkBox\"><ul class="chattxt"></ul></div><input class="input_mesg" type="text"  placeholder="訊息內容..."/><button class="send_btn"/>Send</button></div>'
		);
		chatroomFunction();
	});
// 好友列表用的字串
	var inputStr = function(user) {
		var str="<li class=\"one_friend\" type=\"button\" name= \"friend\" value=\"\" data-frID=\"" + user.idCustomer + "\"/>"+"<img class=\"showPic\" src=\"data:image/png;base64,"+user.profic+"\">"+user.nickName + "<li class=\"iconlist one_friend_List\"><span data-frID=\"" + user.idCustomer + "\"class=\"icon-user one_friend_icon\" ></span><span id =\"getAccounts\"data-frID=\"" + user	.idCustomer + "\" data-frAccount=\"" + user.account +"\" class=\"icon-bubbles3 one_friend_icon\"></span></li></li>";
		return str;
	};
//申請好友用的字串 ，最後需要綁定帳號進去，點按鈕後才會有資料出現
	var appFri = function(user) {
		if(user.apply==="applyFriend"){
			var str="<li class=\"appFri\" type=\"button\" name= \"friend\" value=\"\" data-frID=\"" + user.idCustomer + "\"/>"+"<img class=\"showPic\" src=\"data:image/png;base64,"+user.profic+"\">"+user.nickName + "<li class=\"iconlist appFri_iconList\"><span data-frID=\""+user.idCustomer + "\"class=\"icon-user-minus appFri_icon\"\" data-frAccount=\"" + user.account +"\" ></span></li></li>";
		}else if(user.apply==="appliedFriend"){
			var str="<li class=\"appFri\" type=\"button\" name= \"friend\" value=\"\" data-frID=\"" + user.idCustomer + "\"/>"+"<img class=\"showPic\" src=\"data:image/png;base64,"+user.profic+"\">"+user.nickName+"<li class=\"iconlist appFri_iconList\"><span data-frID=\"" + user	.idCustomer + "\"class=\"icon-user-check appFri_icon\"\" data-frAccount=\"" + user.account +"\" ></span></li></li>";
		}
		return str;
	};
	
	function servletPath(){
		var host = window.location.host;
		var path = window.location.pathname;
		var webContext = path.substring(0, path.indexOf('/', 1));
		var serverEndPoint = "/usermethod/UserServlet";
		var endPointURL = "http://" + window.location.host + webContext + serverEndPoint;
		return endPointURL;
	}
	// 列表中好友的點擊事件
	// var toFriName;
	// var toFriID;
	var clickfunc = function() {
		$(".aside_list .one_friend").click(function() {
			console.log($(this).text());
			console.log($(this).attr("data-frID"));
			id = $(this).attr("data-frID");
		});
// 按聊天泡泡，就會注意到聊天列表
		$(".icon-bubbles3").click(function() {
			selfAcc="lizuan";
			friAcc=$(this).attr("data-frAccount");
			$(".chatlist").focus();

		});
		// 按同意好友按鈕會送出同意好友申請請求
		$(".icon-user-check").click(function() {
			selfAcc="lizuan";
			friAcc=$(this).attr("data-frAccount");
			friID=$(this).attr("data-frID");
			alert(friID);
			// console.log(selfAcc+"\t"+friAcc);
			$.ajax({
				url:servletPath(),
				data:{"metChoice":"agreeFriend","custID":5,"myFriendID":friID},
				type:"POST",
				datatype:"JSON",
				success:function(applyFriendResponse){
					alert("已同意"+friAcc+"好友申請!");
				},
				error:function(applyFriendResponse){
					alert("已同意"+friAcc+"好友申請失敗!");
				}
			});
		});
	};
	
	
	
// 當點了好友列表後會顯示好友列表
	$(".aside_list .all_friend").click(function() {
		// $('.one_friend').css('display', 'inline-block');
		// $('.icon-user').css('display', 'inline-block');
		// $('.icon-bubbles3').css('display', 'inline-block');
		
		$('.one_friend').css('display', 'inline-block');
		$('.one_friend_icon').css('display', 'inline-block');
		$(".one_friend_List").css("display","inline-block");
		
		if (count >= 1) {
			return;
		}
		$.ajax({
			url:servletPath(),
			data: {
				"metChoice": "friendList",
				"custID": 5
			},
			type: "POST",
			dataType: "JSON",
			success: function(friendList) {
				count++;
				innerCount++;
				// console.log(friendList);
				// console.log(JSON.stringify(friendList));
				var jsObj = JSON.stringify(friendList);
				var jsObj2 = JSON.parse(jsObj);

				if (innerCount <= 1) {
					for (var key in jsObj2) {
						for (var key1 in jsObj2[key]) {
							for (var key2 in jsObj2[key][key1]) {
								var ans = jsObj2[key][key1][key2];
								$(".aside_list .all_friend").after(inputStr(ans)).fadeIn(20000);
							}
						}
					}
					$(".all_friend .one_friend").on("click", "li", clickfunc(this)).stopPropagation;
				}
			},
			error: function() {}
		}, false);
	});
// 點選好友申請列表時會執行的程式
$(".aside_list .friend_req").click(function() {
		$('.appFri').css('display', 'inline-block');
		$('.appFri_icon').css('display', 'inline-block');
		$(".appFri_iconList ").css("display","inline-block");
		
		if (countFri >= 1) {
			return;
		}
		$.ajax({
			url: servletPath(),
			data: {
				"metChoice": "applyList",
				"custID": 5
			},
			type: "POST",
			dataType: "JSON",
			success: function(agreeFriend) {
				countFri++;
				innerCountFri++;
				// console.log(friendList);
				// console.log(JSON.stringify(friendList));
				var jsObj = JSON.stringify(agreeFriend);
				var jsObj2 = JSON.parse(jsObj);

				if (innerCountFri <= 1) {
					for (var key in jsObj2) {
						for (var key1 in jsObj2[key]) {
							for (var key2 in jsObj2[key][key1]) {
								var ans = jsObj2[key][key1][key2];
								$(".aside_list .friend_req").after(appFri(ans)).fadeIn(20000);
							}
						}
					}
					$(".friend_req .appFri").on("click", "li", clickfunc(this)).stopPropagation;
				}
			},
			error: function() {}
		}, false);
	});



	$(".aside_list").click(function(e) {
		if (e.stopPropagation) {
			e.stopPropagation();
		} else {
			e.cancelBubble = true;
		}
// 當滑鼠目標不在好友列表後，會隱藏好友列表中的好友及icon
		$(document).bind('click', function() {
			$('.one_friend').css('display', 'none');
			$('.one_friend_icon').css('display', 'none');
			
			$('.appFri').css('display', 'none');
			$('.appFri_icon').css('display', 'none');
			
			$(".one_friend_List").css("display","none");
			$(".appFri_iconList").css("display","none");
		});
	});

	var host = window.location.host;
	var path = window.location.pathname;
	var webContext = path.substring(0, path.indexOf('/', 1));
	var serverEndPoint = "/chatroom";
	var endPointURL = "ws://" + window.location.host + webContext + serverEndPoint + "/lizuan";
	// console.log("host\t" + host);
	// console.log("path\t" + path);
	// console.log("serverEndPoint\t" + serverEndPoint);
	console.log("endPointURL\t" + endPointURL);
	var sendMessage;
	var connect;
	var disconnect;
	var showMess="";
	// websocket聊天室連接的函式
	var chatroomFunction = function() {
		var webSocket;
		connected();
		// 按send按鍵送出訊息
		$(".input_mesg").keydown(function(event){
			if(event.keyCode==13)
			{
				if($(".input_mesg").val().trim()===""){
					$(".input_mesg").focus()
					return;
				}
				sendMessage($(".input_mesg").val());
				showMess=$(".input_mesg").val();
				$("input_mesg").val("");
				$("input_mesg").focus();
			}
			
		});
		
		$(".send_btn").click(function() {
			if($(".input_mesg").val().trim()===""){
				$(".input_mesg").focus()
				return;
			}
			sendMessage($(".input_mesg").val());
			$("input_mesg").val("");
			$("input_mesg").focus();
		});

		
		
		function connected() {
			//			webSocket = new WebSocket(endPointURL);
			if ('WebSocket' in window) {
				webSocket = new WebSocket(endPointURL);
			} else if ('MozWebSocket' in window) {
				webSocket = new MozWebSocket(endPointURL);
			} else {
				Console.log('Error: WebSocket is not supported by this browser.');
				return;
			}

			webSocket.onopen = function() {
				var histo = {type: "history",selfAccount:selfAcc,friAccount:friAcc,message:"",createdTime:""};
				webSocket.send(JSON.stringify(histo));
				console.log("WebSocket Connect!")
			};

			webSocket.onmessage = function(event) {
				// console.log(event.data);
				// console.log(typeof event.data);
				var jSon=JSON.parse(event.data);
				// console.log(JSON.parse(jSon["message"]));
				// var secJSon=JSON.parse(jSon["message"]);
				var secJSon;
				if(jSon["message"]!=undefined)
				{
					secJSon=JSON.parse(jSon["message"]);
				}
				
				
				// console.log(secJSon);
				var strBuf="";
				
				for(var secIndex in secJSon)
				{
					
					var realJson=JSON.parse(secJSon[secIndex]);
					// console.log(realJson);
					// selAccount代表自己的帳號'位置'，selfAcc代表自己的帳號的本身
			// if(realJson.message==undefined || typeof(realJson.message)=="undefined")
			// {
				// // ctinue;
			// }
					
					strBuf=strBuf+"<li ";
					if(realJson.selfAccount===selfAcc){
						// 如果目前取出的訊息中，自己帳號位置的值與帳號本身的值相同
						// 那資料就要顯示在右邊
						strBuf=strBuf+"class=\"mySend\"";
					}else if(realJson.selfAccount===friAcc)
					{
						strBuf=strBuf+"class=\"friSend\"";
						// 如果目前取出的訊息中，自己帳號位置的值與朋友的帳號相同，代表訊息是朋友傳過來的
						// 那這樣資料就要顯示在左邊
					}
					strBuf=strBuf+">"+realJson.message+"<p class=\"createdTime\">"+realJson.createdTime+"</p></li>";
				}
				$(".chattxt").append(strBuf);
			};
			webSocket.onclose = function() {
				console.log("WebSocket Close!");
			};
		};

		function sendMessage(mess) {
			if (selfAcc != null && friAcc != null && mess != null) {
				ans = {
					type: "sendMesg",
					selfAccount: selfAcc,
					friAccount: friAcc,
					message: mess,
					createdTime:""
				};
				webSocket.send(JSON.stringify(ans));
				console.log("mess:\t"+mess);
			} else {
				$(".send_btn").focus();
			}
			showMess="<li class=\"mySend\">"+mess+"<p class=\"createdTime\">"+new Date().toLocaleString()+"</p></li>";
			$(".chattxt").append(showMess);
			$(".input_mesg").val("");
			$(".input_mesg").focus();
		}

		function disconnect() {
			webSocket.close();
			sendMessage = true;
			connect = false;
			disconnect = true;
		}
	};
	
	function readyload()
	{
		$.ajax({
			url:servletPath(),
			data:{"metChoice":"readyLoad"},
			type:"POST",
			datatype:"JSON",
			success:function(data)
			{
				console.log(data);
			}
		});
	}
	
	
	
	
});


// 點好友同意功能
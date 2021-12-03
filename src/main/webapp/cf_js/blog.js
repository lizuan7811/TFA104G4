$(function() {
	var selfAcc;
	var friAcc;
	var message;
	var count = 0;
	var innerCount = 0;
	
	$(".chatlist").on("focus", function() {
		$(this).removeClass(".chatlist"); //移除原本聊天列表的css樣式
		$(".title").css("display", "none"); //隱藏抬頭: 聊天列表
		//加入html語法 並設定class等...
		$(this).html(
			'<div class="chat_online"><div class="chat_name">貪吃的貓<br></div><ul class="chattxt"></ul><input class="input_mesg" type="text"  placeholder="訊息內容..."/><button class="send_btn"/>Send</button></div>'
		);
		chatroomFunction();
	});

	var inputStr = function(user) {
		var str = "<li class=\"one_friend\" type=\"button\" name= \"friend\" value=\"\" data-frID=\"" + user
			.idCustomer + "\"/>" + user.nickName + "<li class=\"iconlist\"><span data-frID=\"" + user
			.idCustomer + "\"class=\"icon-user\"></span><span id =\"getAccounts\"data-frID=\"" + user
			.idCustomer + "\" data-frAccount=\"" + user.account +
			"\" class=\"icon-bubbles3\"></span></li></li>";
		//        var str="<li><img width=\"900\" height=\"450\" src=\"data:image/png;base64,"+decodeURI(user.profic)+"\"/><input class=\"friend\" type=\"button\" name= \"friend\" value=\""+user.nickName+"\" data-frID=\""+user.idCustomer+" onclick=\"clickfunc()\"\"/></li>";
		return str;
	};
	// 列表中好友的點擊事件
	// var toFriName;
	// var toFriID;
	var clickfunc = function() {
		$(".aside_list .one_friend").click(function() {
			console.log($(this).text());
			console.log($(this).attr("data-frID"));
			id = $(this).attr("data-frID");
		});

		$(".icon-bubbles3").click(function() {
			// console.log("$(this).text()" + $(this).text());
			// console.log("$(this).attr(\"data-frID\")" + $(this).attr("data-frID"));
			// friendID = {
			// 	"friendID": $(this).attr("data-frID")
			// };
			// friAccount = $(this).attr("data-frAccount");
			selfAcc="lizuan";
			friAcc=$(this).attr("data-frAccount");
			$(".chatlist").focus();

		});
	};

	$(".aside_list .all_friend").click(function() {
		$('.one_friend').css('display', 'inline-block');
		$('.icon-user').css('display', 'inline-block');
		$('.icon-bubbles3').css('display', 'inline-block');
		if (count >= 1) {
			return;
		}
		$.ajax({
			url: "usermethod/UserServlet",
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
								$(".aside_list .all_friend").after(inputStr(ans)).fadeIn(
									20000);
							}
						}
					}
					$(".all_friend .one_friend").on("click", "li", clickfunc(this))
						.stopPropagation;
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

		$(document).bind('click', function() {
			$('.one_friend').css('display', 'none');
			$('.icon-user').css('display', 'none');
			$('.icon-bubbles3').css('display', 'none');
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

	var chatroomFunction = function() {
		var webSocket;
		connected();
		$(".send_btn").click(function() {
			if($(".input_mesg").val().trim()===""){
				$(".input_mesg").focus()
				return;
			}
			
			sendMessage($(".input_mesg").val());
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
				var secJSon=JSON.parse(jSon["message"]);
				// console.log(secJSon);
				var strBuf="";
				
				for(var secIndex in secJSon)
				{
					
					var realJson=JSON.parse(secJSon[secIndex]);
					// console.log(realJson);
					// selAccount代表自己的帳號'位置'，selfAcc代表自己的帳號的本身
					if(realJson.message==undefined || typeof(realJson.message)=="undefined")
					{
						continue;
					}
					
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
					strBuf=strBuf+">"+realJson.message+"</li>";
					strBuf=strBuf+"<p class=\"createdTime\">"+realJson.createdTime+"</p>";
					console.log(realJson.createdTime);
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
});

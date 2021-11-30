$(function()
{

    $(".chatlist").on("focus",function()
	{
        $(this).removeClass(".chatlist");  //移除原本聊天列表的css樣式
        $(".title").css("display", "none");  //隱藏抬頭: 聊天列表
        //加入html語法 並設定class等...
        $(this).html('<div class="chat_online"><div class="chat_name">貪吃的貓<br></div><textarea class="chattxt" type="text" readonly></textarea><input class="input_mesg" type="text"  placeholder="訊息內容..."/><input class="send_btn" type="submit" value="Send"/></div>');
	});

	// <div class="chat_people">
	//     <img class="chat_pic" src="cf_css/cat_toby.jpg" alt="cat"> </div>
	//     <div class="chat_content">
	//       <h5>貪吃的貓 <span class="chat_date">16:46 pm</span></h5>
	//       <br>
	//       <p>今天沒那麼冷了吧?</p>
	//     </div>
	// </div>
	// $(".chatlist").on("blur",function(){
	// 	$(".chat_online").css("display", "none");
	// 	$(".title").css("display", "inline-block");
	// 	$(".chatlist").append(originSource);
	// });


    var inputStr=function(user)
	{
        var str="<li class=\"one_friend\" type=\"button\" name= \"friend\" value=\"\" data-frID=\""+user.idCustomer+"\"/>"+user.nickName+"<li class=\"iconlist\"><span data-frID=\""+user.idCustomer+"\"class=\"icon-user\"></span><span data-frID=\""+user.idCustomer+"\" class=\"icon-bubbles3\"></span></li></li>";
//        var str="<li><img width=\"900\" height=\"450\" src=\"data:image/png;base64,"+decodeURI(user.profic)+"\"/><input class=\"friend\" type=\"button\" name= \"friend\" value=\""+user.nickName+"\" data-frID=\""+user.idCustomer+" onclick=\"clickfunc()\"\"/></li>";
		return str;
    };
// 列表中好友的點擊事件
	var clickfunc=function()
	{
		$(".aside_list .one_friend").click(function(){
			console.log($(this).text());
			console.log($(this).attr("data-frID"));
			id=$(this).attr("data-frID");
			$.ajax(
			{
				url:"#",
				data:{"":""},
				type:"POST",
				dataType:"JSON",
				success:function()
				{
					alert("11");
				},
				error:function()
				{

				}
			});


		});

		$(".icon-bubbles3").click(function(){
			console.log("$(this).text()"+$(this).text());
			console.log("$(this).attr(\"data-frID\")"+$(this).attr("data-frID"));
			$(".chatlist").focus();
		});


	};



    var count=0;
	var innerCount=0;
    $(".aside_list .all_friend").click(function()
	{
		$('.one_friend').css('display','inline-block');
		$('.icon-user').css('display','inline-block');
		$('.icon-bubbles3').css('display','inline-block');
		if(count>=1)
		{
			return ;
		}
        $.ajax(
		{
            url:"usermethod/UserServlet",
            data:{"metChoice":"friendList","custID":1},
            type:"POST",
            dataType:"JSON",
            success:function(friendList)
            {
                count++;
				innerCount++;
                // console.log(friendList);
                // console.log(JSON.stringify(friendList));
                var jsObj=JSON.stringify(friendList);
                var jsObj2=JSON.parse(jsObj);

                if(innerCount<=1)
                {
                    for(var key in jsObj2)
                    {
                        for(var key1 in jsObj2[key])
						{
                            for(var key2 in jsObj2[key][key1])
							{
                                var ans=jsObj2[key][key1][key2];
                                $(".aside_list .all_friend").after(inputStr(ans)).fadeIn(20000);
							}
                        }
                    }
					// $(".all_friend .one_friend").on("click","input",clickfunc(this)).cancelBubble;
					$(".all_friend .one_friend").on("click","li",clickfunc(this)).cancelBubble;
                }
            },
            error:function()
			{
            }
        },false);
    });

	$(".aside_list").click(function(e)
	{
		if (e.stopPropagation)
		{
			e.stopPropagation();}
		else
		{
			e.cancelBubble = true;
		}

		$(document).bind('click',function()
		{
			$('.one_friend').css('display','none');
			$('.icon-user').css('display','none');
			$('.icon-bubbles3').css('display','none');
		});
	});
// 聊天室函式
// 若輸入框被focus，就開啟websocket聊天程式
	$(".return_btn").on("click",function(){
		chatroomFunction();
		alert(1234);
						});
	var chatroomFunction=function (){
		var webSocket;
		alert(111);

		var host = window.location.host;
		var path = window.location.pathname;
		var webContext = path.substring(0, path.indexOf('/', 1));
		var serverEndPoint = "/chatroom";
		var endPointURL = "ws://" + window.location.host + webContext + serverEndPoint;
		     console.log("host\t"+host);
		     console.log("path\t"+path);
		     console.log("serverEndPoint\t"+serverEndPoint);
		     console.log("endPointURL\t"+endPointURL);
		connect();
     function connect(){
       webSocket = new WebSocket(endPointURL);
  	 	webSocket.onopen=function(event){
         console.log("WebSocket Connect!")
    
  	 	};
  	 	webSocket.onmessage=function(event){
         console.log("WebSocket SendMessage!")
    
  	 	};
  	 	webSocket.onclose=function(event){
         console.log("WebSocket Close!")
    
  	 	};
    
     };



    };


});

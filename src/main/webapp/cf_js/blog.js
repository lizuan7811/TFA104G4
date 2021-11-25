$(function(){
    // $(".return_btn").focus(function(){
    //     alert(11);
    //   })
    $(".chatlist").click(function(){
        $(this).removeClass(".chatlist");  //移除原本聊天列表的css樣式
        $(".title").css("display", "none");  //隱藏抬頭: 聊天列表
          
        //加入html語法 並設定class等...
        $(this).html('<div class="chat_online"><div class="chat_name">貪吃的貓<br></div><textarea class="chattxt" type="text"></textarea><input class="input_mesg" type="text"  placeholder="訊息內容..."/><input class="send_btn" type="submit" value="Send"/></div>');                          
    });

    var inputStr=function(user){
        var str="<li><img width=\"900\" height=\"450\" src=\"data:image/png;base64,"+decodeURI(user.profic)+"\"/><input class=\"friend\" type=\"button\" name= \"friend\" value=\""+user.nickName+"\" data-frID=\""+user.idCustomer+"\"/></li>";
        return str;
    };


    var count=0;
    $(".aside_list .all_friend").focus(function(){
        $.ajax({
            url:"usermethod/UserServlet",
            data:{"metChoice":"friendList","custID":1},
            type:"POST",
            dataType:"json",
            success:function(friendList)
            {   
                count++;
                // console.log(friendList);
                // console.log(JSON.stringify(friendList));
                var jsObj=JSON.stringify(friendList);
                var jsObj2=JSON.parse(jsObj);
               
                if(count<=1)
                {
                    for(var key in jsObj2)
                    {
                        // console.log(jsObj2[key]);
                        for(var key1 in jsObj2[key]){
                            // console.log(jsObj2[key][key1]);
                            for(var key2 in jsObj2[key][key1]){
                                var ans=jsObj2[key][key1][key2];
                                console.log(jsObj2[key][key1][key2]);
                                console.log(inputStr(ans));
                                console.log(ans.profic);
                                $(".aside_list .all_friend").after(inputStr(ans)).addClass(".friend");
                            }
                        }
                    }
                }
               
                // var jsonObj=JSON.stringify(friendList);
				// console.log(jsonObj);
				// console.log(JSON.parse(jsonObj));
				// var ansObj=JSON.parse(jsonObj);
				// for(var key in jsonObj)
				// {
				// 	console.log(jsonObj[key]);
				// }
                // console.log("jsonObj"+jsonObj);
				// console.log(jsonObj);
				// var JJsonObj=JSON.parse(jsonObj);
                // for(var key in JJsonObj)
                // {
                //     console.log(JJsonObj[key]["FRIENDLIST"]["nickName"]);
                //     var mapLoop=JJsonObj[key]["FRIENDLIST"];
                //   }
            },
            error:function(){

            }

        });



        // $(".aside_list .all_friend").after("<li>1235</li>");
    });




});


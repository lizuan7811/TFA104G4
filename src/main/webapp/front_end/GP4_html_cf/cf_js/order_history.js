$(function(){
   
    getUserOwnOrder();
    var fOderStr="";
    var inp_btnCheckCnt=0;
    var inp_btnSelCnt=0;


    var finalOrderObj;
    var ingreObj;
    var orderIngreObj;
    
    function getUserOwnOrder(){
        $.ajax({
            url:servletPath()+"/finalorder/OrderServlet",
            data:{"metChoice":"initUserOwnOrder"},
            type:"POST",
            success:function(data){
                // console.log(data);
                var jData=JSON.parse(data);
                // console.log(jData["finalOrderAll"]);
                // console.log(jData["ingreAll"]);
                // console.log(jData["orderIngreList"]);

                finalOrderObj=jData["UserOwnfinalOrdre"];
                ingreObj=jData["ingreAll"];
                orderIngreObj=jData["orderIngreList"];
                // for(var key in ingreObj)
                // {
                //     console.log(orderIngreObj[key]);
                //     console.log(ingreObj[key]);
                // }
				newElemStr(finalOrderObj)
                $(".order_body").append(newElemStr(finalOrderObj));
                $(".mainbox tbody tr td .inp_btnSel").on("click",selFunc(this)).stopPropagation;
                $(".mainbox tbody tr td .inp_btnSel").on("click",checkFunc(this)).stopPropagation;
            },
            error:function(){
                alert("未連接資料庫!");
            }
        })
    }

    // 拼最後初始化的畫面訂單字串物件訊息
    function newElemStr(jsonObj)
    {
        for(var key in jsonObj)
        {
            var inp_btnSel="<td><input class='inp_btnSel btnClaz' data-idFinalOrder='"+jsonObj[key]["idFinalOrder"]+"'data-idCustomer='"+jsonObj[key]["idCustomer"]+"' type=\"button\" value=\"訂單明細\"></td>";
            var inp_btnCheck="<td><input class='inp_btnCheck btnClaz' data-idFinalOrder='"+jsonObj[key]["idFinalOrder"]+"'data-idCustomer='"+jsonObj[key]["idCustomer"]+"' type=\"button\" value=\"取消訂單\"></td>";
            fOderStr=fOderStr+"<tr><td>"+jsonObj[key]["createdTime"].substring(0,10)+"</td><td>"+jsonObj[key]["idFinalOrder"]+"</td><td>信用卡</td><td>"+(jsonObj[key]["shipTime"]==undefined?"尚未出貨":jsonObj[key]["shipTime"].substring(0,10))+"</td><td>"+(jsonObj[key]["arrivalTime"]==undefined?"":jsonObj[key]["arrivalTime"].substring(0,10))+"</td><td>"+jsonObj[key]["orderAmount"]+"</td><td>"+jsonObj[key]["recipient"]+"</td><td>"+jsonObj[key]["recipientAddress"]+"</td><td>"+jsonObj[key]["footnote"]+"</td>"+inp_btnSel+inp_btnCheck+"</tr>";
        }
        return fOderStr;
    }


    function checkFunc(){
        $(".inp_btnCheck").click(function(){
            
            alert("inp_btnCheck");
            $(".swal2-actions button").on("click",function(){
				var diaryReport=$(".swal2-content input").val();
				$.ajax({
					// url:servletPath(),
					data:{"metChoice":"cancelOrder","custID":6,"idFinalOrder":$(this).attr("idFinalOrder"),"reportReason":$(".swal2-content input").val()},
//					data:{"metChoice":"diaryReport","custID":$(".author_name").attr("data-custId"),"diaryID":$(".author_name").attr("data-diaryId"),"reportReason":$(".swal2-content input").val()},

					type:"POST",
					success:function(data){
							console.log("完成送出檢舉!");
					},
					error:function(){
						
					},
				});
			})


            if(inp_btnCheckCnt>=1)
            {
                return;
            }
            if($(this).attr("data-idFinalOrder")==$(".showFinalOrder ul .liID").attr("data-idFinalOrder"))
            {
                respRsID=$(this).attr("data-idFinalOrder");
                inp_btnCheckCnt+=1;
                if(inp_btnCheckCnt<=1){
                window.setTimeout(function(){
                    $(".commBox").removeClass("dpNone");
                },200);
                }
            }
        });
    }

var ingreNum=0;
var timeoutID;
var tmpRespStr;
    function selFunc(){
        
        $(".inp_btnSel").click(function(){
            $(".repBox").html("");
            var idFinalOrder=$(this).attr("data-idFinalOrder");
console.log("idFinalOrder\t"+idFinalOrder);
            var jsonOjj=finalOrderObj[idFinalOrder-1];
          
            if(inp_btnSelCnt>=1)
            {
                    // $(".showFinalOrder").removeClass("tranToBigdv");
                    // $(".showFinalOrder").html("<div class='repBox'></div>");
                    // $(".commBox").addClass("dpNone");
                    // window.clearInterval(timeoutID);
                    // inp_btnCheckCnt=0;
                    // inp_btnSelCnt=0;
            }
            
            if(inp_btnSelCnt<1)
            {
                window.clearInterval(timeoutID);
                timeoutID=window.setInterval(function(){
                    console.log($(window).scrollTop());
                    var sdHei=$(".showFinalOrder").offset().top;
                    $(".showFinalOrder").offset({
                        top:100+$(window).scrollTop()
                    })
                },200);
             
                window.setTimeout(function(){
                    if(inp_btnSelCnt>=1)
                    {return;}
                    $(".showFinalOrder").addClass("tranToBigdv");
                    $(".showFinalOrder").append(repStr(jsonOjj,idFinalOrder));
                    inp_btnSelCnt+=1;
                },700);
            }
            
        })}

        
// 拼系統回覆的字串
    function repStr(jsonOjj,idFinalOrder){
        var ingreNum;
        var tmpArr=new Array();
console.log(jsonOjj);
        var tpTime=jsonOjj.arrivalTime==undefined?"":jsonOjj.arrivalTime;
        var newEleStr="<ul><li class='liID' data-idFinalOrder="+idFinalOrder+">訂單編號:\t"+idFinalOrder+"</li><li>收貨人:\t"+jsonOjj.recipient+"</li><li>收貨地址:\t"+jsonOjj.recipientAddress+"</li><li>消費金額:"+jsonOjj.orderAmount+"</li><li>訂單成立時間:\t"+jsonOjj.createdTime+"</li><li class='arrivalTime'>貨物送達時間:\t"+tpTime+"</li><li>備註:\t"+jsonOjj.footnote+"</li>";
        for(var key in orderIngreObj)
        {
            
            if(idFinalOrder==orderIngreObj[key].idFinalOrder)
            {
                tmpArr.push(orderIngreObj[key].idIngre);
            }
        }
       var flag=false;
        for(var index in tmpArr)
        {
            flag=false;
            tmpRespStr="";
            // tempOIList="<li>"+tmpArr[index].name+"</li>";
            console.log(tmpArr[index]);
            // var tmpIngreObj=ingreObj[tmpArr[index]];
            // console.log(ingreObj);
            for(var idx in ingreObj){
                if(tmpArr[index]==ingreObj[idx][tmpArr[index]])
                {
                    console.log(ingreObj[idx]);
                    var tpIngreObj=ingreObj[idx];
                    tmpRespStr+="<li class='upLine'>食材名稱:"+tpIngreObj["name"]+"</li><li>食材圖片:<img class='picSize' src='data:image/jpg;base64,"+tpIngreObj["photo"]+"'></li><li>單價:"+tpIngreObj["purPrice"]+"<li class='downLine'>重量:"+tpIngreObj["gran"]+"g/"+tpIngreObj["unit"]+"\t數量:\t"+tpIngreObj["sell"]+"</li>";
                    // console.log(tmpRespStr);
                    flag=true;
                }
                if(flag==true)
                {
                    break;
                }
            }
            // console.log("tmpIngreObj\t"+tmpIngreObj[tmpArr[index]]);
            // tmpRespStr+=tmpIngreObj.index
            newEleStr+=tmpRespStr+"</ul>";
            
        }
        return newEleStr;
    }

//     function sdResult(){
// // 送出審查通過訊息
//         $(".pass").on("click",function(){
//             $.ajax({
//               url:servletPath(),
//               data:{"metChoice":"respResult","respValue":"true","diaryReportID":respRsID},
//               type:"POST",
//               success:function(data){
//                 console.log("respRsID"+respRsID);
//                 $(".respValue"+respRsID).html("審查通過");
//                 $(".respValue"+respRsID).removeClass("noPassVal");
//                 $(".respValue"+respRsID).addClass("passVal");

//                 // alert("審查結果送出成功!")
//               },
//               error:function(){
//                 alert("審查結果送出不成功!")
//               }
//             })
//         });
// // 送出審查不通過訊息
//         $(".noPass").on("click",function(data){
//             $.ajax({
//                 url:servletPath(),
//                 data:{"metChoice":"respResult","respValue":"false","diaryReportID":respRsID},
//                 type:"POST",
//                 success:function(){
//                     console.log("respRsID"+respRsID);
//                     $(".respValue"+respRsID).html("審查不通過");
//                     $(".respValue"+respRsID).removeClass("passVal");
//                     $(".respValue"+respRsID).addClass("noPassVal");
//                     // alert("審查結果送出成功!")
//                 },
//                 error:function(){
//                     alert("審查結果送出不成功!")
//                 }
//               })
//         });

//     }
    function servletPath(){
		var host = window.location.host;
		var path = window.location.pathname;
		var webContext = path.substring(0, path.indexOf('/', 1));
		var serverEndPoint = "/finalorder/OrderServlet";
		var endPointURL = "http://" + window.location.host + webContext;
		return endPointURL;
	}
});

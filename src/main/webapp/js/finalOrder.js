$(function(){
   
    getFinalOrder();
    var fOderStr="";
    var inp_btnCheckCnt=0;
    var inp_btnSelCnt=0;


    var finalOrderObj;
    var ingreObj;
    var orderIngreObj;
    
    function getFinalOrder(){
        $.ajax({
            url:servletPath()+"/finalorder/OrderServlet",
            data:{"metChoice":"initOrderDetail"},
            type:"POST",
            success:function(data){
                var jData=JSON.parse(data);
                console.log(jData["finalOrderAll"]);
                console.log(jData["ingreAll"]);
                console.log(jData["orderIngreList"]);

                finalOrderObj=jData["finalOrderAll"];
                ingreObj=jData["ingreAll"];
                orderIngreObj=jData["orderIngreList"];
                // for(var key in ingreObj)
                // {
                //     console.log(orderIngreObj[key]);
                //     console.log(ingreObj[key]);
                // }
                newElemStr(finalOrderObj);
                $(".mainbox").append(newElemStr(finalOrderObj));
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
            // console.log(jsonObj[key]);
            var inp_btnSel="<td><input class='inp_btnSel btnClaz' data-idFinalOrder='"+jsonObj[key]["idFinalOrder"]+"'data-idCustomer='"+jsonObj[key]["idCustomer"]+"' type=\"button\" value=\"訂單明細\"></td>";
            var inp_btnCheck="<td><input class='inp_btnCheck btnClaz' data-idFinalOrder='"+jsonObj[key]["idFinalOrder"]+"'data-idCustomer='"+jsonObj[key]["idCustomer"]+"' type=\"button\" value=\"同意取消\" disabled></td>";
            fOderStr=fOderStr+"<tr><td>"+jsonObj[key]["idFinalOrder"]+"</td><td>"+jsonObj[key]["idCustomer"]+"</td><td>"+jsonObj[key]["recipient"]+"</td><td>"+jsonObj[key]["recipientAddress"]+"</td><td>"+jsonObj[key]["orderAmount"]+"</td><td>"+jsonObj[key]["createdTime"]+"</td><td>"+jsonObj[key]["arrivalTime"]+"</td><td>"+jsonObj[key]["footnote"]+"</td>"+inp_btnSel+inp_btnCheck+"</tr>";
        }
        return fOderStr;
    }


    function checkFunc(){
        $(".inp_btnCheck").click(function(){
            
            if(inp_btnCheckCnt>=1)
            {
                return;
            }
            console.log("this.diaryID"+$(this).attr("data-diaryReportID"));
            console.log("showFinalOrder"+$(".showFinalOrder  ul .liID").attr("data-idFinalOrder"));
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

var count=0;
var timeoutID;
var reportReason;
    function selFunc(){
        
        $(".inp_btnSel").click(function(){
            $(".repBox").html("");
            var idFinalOrder=$(this).attr("data-idFinalOrder");
            var jsonOjj=finalOrderObj[idFinalOrder-1];
          
            if(inp_btnSelCnt>=1)
            {
                    $(".showFinalOrder").removeClass("tranToBigdv");
                    $(".showFinalOrder").html("<div class='repBox'></div>");
                    $(".commBox").addClass("dpNone");
                    window.clearInterval(timeoutID);
                    inp_btnCheckCnt=0;
                    inp_btnSelCnt=0;
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

        

    function repStr(jsonOjj,idFinalOrder){
        var tempIngre;
        var tempOIList;
        var tmpArr=new Array();
        var newEleStr="<ul><li class='liID' data-idFinalOrder="+idFinalOrder+">訂單編號:\t"+idFinalOrder+"</li><li>收貨人:\t"+jsonOjj.recipient+"</li><li>收貨地址:\t"+jsonOjj.recipientAddress+"</li><li>消費金額:"+jsonOjj.orderAmount+"</li><li>訂單成立時間:\t"+jsonOjj.createdTime+"</li><li>貨物送達時間:\t"+(jsonOjj.arrivalTime==undefined?"":jsonOjj.arrivalTime)+"</li><li>備註:\t"+jsonOjj.footnote+"</li>";
        for(var key in orderIngreObj)
        {
            if(idFinalOrder==orderIngreObj[key].idFinalOrder)
            {
                tmpArr.push(orderIngreObj[key].idIngre);
            }
        }
        console.log(tmpArr);
        for(var index in tmpArr)
        {
            tempOIList="<li>"+tmpArr[index].name+"</li>";
            
        }
        return newEleStr;
    }

    function sdResult(){
// 送出審查通過訊息
        $(".pass").on("click",function(){
            $.ajax({
              url:servletPath(),
              data:{"metChoice":"respResult","respValue":"true","diaryReportID":respRsID},
              type:"POST",
              success:function(data){
                console.log("respRsID"+respRsID);
                $(".respValue"+respRsID).html("審查通過");
                $(".respValue"+respRsID).removeClass("noPassVal");
                $(".respValue"+respRsID).addClass("passVal");

                // alert("審查結果送出成功!")
              },
              error:function(){
                alert("審查結果送出不成功!")
              }
            })
        });
// 送出審查不通過訊息
        $(".noPass").on("click",function(data){
            $.ajax({
                url:servletPath(),
                data:{"metChoice":"respResult","respValue":"false","diaryReportID":respRsID},
                type:"POST",
                success:function(){
                    console.log("respRsID"+respRsID);
                    $(".respValue"+respRsID).html("審查不通過");
                    $(".respValue"+respRsID).removeClass("passVal");
                    $(".respValue"+respRsID).addClass("noPassVal");
                    // alert("審查結果送出成功!")
                },
                error:function(){
                    alert("審查結果送出不成功!")
                }
              })
        });

    }
    function servletPath(){
		var host = window.location.host;
		var path = window.location.pathname;
		var webContext = path.substring(0, path.indexOf('/', 1));
		var serverEndPoint = "/finalorder/OrderServlet";
		var endPointURL = "http://" + window.location.host + webContext;
		return endPointURL;
	}
});

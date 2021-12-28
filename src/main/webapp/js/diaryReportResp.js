$(function(){
    var commStr="";
    var respRsID="";
    var diaryID;
    getDiary();
    sdResult();
    var host;
    var path;
    var webContext;
    servletPath();
   
    // 取得被檢舉的評論
    $(document).ready(function(){
        
        $.ajax({
            url:"userlist/UserListServlet",
            data:{"metChoice":"getDiaryComms"},
            type:"POST",
            success:function(data){
                var jsonO=JSON.parse(data);                
                $(".mainbox").append(newElemStr(jsonO));
                $(".mainbox tbody tr td .inp_btnSel").on("click",selFunc(this)).stopPropagation;
                $(".mainbox tbody tr td .inp_btnCheck").on("click",checkFunc(this)).stopPropagation;    
            },
            error:function(){
            }
        })
    });
    var inp_btnCheckCnt=0;
    var inp_btnSelCnt=0;
    function checkFunc(){
        $(".inp_btnCheck").click(function(){
            
            if(inp_btnCheckCnt>=1)
            {
                return;
            }
            console.log("this.diaryID"+$(this).attr("data-diaryReportID"));
            console.log("showDiaryID"+$(".showDiary  ul .liID").attr("data-diaryReportID"));
            if($(this).attr("data-diaryReportID")==$(".showDiary ul .liID").attr("data-diaryReportID"))
            {
                respRsID=$(this).attr("data-diaryReportID");
                diaryID=$(this).attr("data-diaryID");
                console.log("respRsID"+respRsID);
                console.log("diaryID"+diaryID);
                inp_btnCheckCnt+=1;
                if(inp_btnCheckCnt<=1){
                window.setTimeout(function(){
                    $(".commBox").removeClass("dpNone");
                },200);
                }

            }
            
        });
    }

var diaryContant;
function getDiary(){
    $.ajax({
        url:"userlist/UserListServlet",
        data:{"metChoice":"DiaryReported"},
        type:"POST",
        success:function(data){
            diaryContant=JSON.parse(data);
        },
        error:function(){
            alert("未連接資料庫!");
        }
    })
}
var repListObj;

var count=0;
var timeoutID;
var reportReason;
    function selFunc(){
        
        $(".inp_btnSel").click(function(){
            $(".repBox").html("");
            console.log($(this).attr("data-custID"));
            console.log($(this).attr("data-diaryID"));
            reportReason=$(this).attr("data-repReason");
            var diaryRpID=$(this).attr("data-diaryReportID");
            var jsonOjj=diaryContant[String($(this).attr("data-diaryID"))];
            
            if(inp_btnSelCnt>=1)
            {
                    $(".showDiary").removeClass("tranToBigdv");
                    $(".showDiary").html("<div class='repBox'></div>");
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
                    var sdHei=$(".showDiary").offset().top;
                    $(".showDiary").offset({
                        top:100+$(window).scrollTop()
                    })
                },200);
             
                window.setTimeout(function(){
                    if(inp_btnSelCnt>=1)
                    {return;}
                    $(".showDiary").addClass("tranToBigdv");
                    $(".showDiary").append(repStr(jsonOjj,reportReason,diaryRpID));
                    inp_btnSelCnt+=1;
                },700);
            }
            
        })}

        

    function repStr(jsonOjj,rpReason,diaryRpID){
        var result;
        var reason;
        var newEleStr="<ul><li class='liID' data-diaryReportID="+diaryRpID+">檢舉編號:\t"+diaryRpID+"</li><li>日誌標題:\t"+jsonOjj.subject+"</li><li>日誌建立時間:\t"+jsonOjj.fd_createdTime+"</li>日誌分享圖:\t<img class='picSize' src='data:image/jpg;base64,"+jsonOjj.photo+"'alt='日誌分享圖片'><li>敘述:\t"+jsonOjj.text+"</li><li>檢舉者暱稱:\t"+jsonOjj.nickName+"</li><li>檢舉時間:\t"+jsonOjj.ct_createdTime+"</li><li>檢舉原因:\t"+reportReason+"</li>";
        return newEleStr;
    }
    function newElemStr(jsonObj)
    {
        repListObj=jsonObj;
        for(var key in jsonObj)
        {
            var inp_btnSel="<td><input class='inp_btnSel btnClaz' data-diaryReportID='"+jsonObj[key]["diaryReportID"]+"'data-diaryID='"+jsonObj[key]["diaryID"]+"' data-custID='"+jsonObj[key]["custID"]+"' data-repReason='"+jsonObj[key]["reportReason"]+"' type=\"button\" value=\"查看日誌\"></td>";
            var inp_btnCheck="<td><input class='inp_btnCheck btnClaz' data-diaryReportID='"+jsonObj[key]["diaryReportID"]+"'data-diaryID='"+jsonObj[key]["diaryID"]+"' data-custID='"+jsonObj[key]["custID"]+"' type=\"button\" value=\"審核\"></td>";
            fontStyleFac(jsonObj[key]["diaryReportID"]);
            commStr=commStr+"<tr><td>"+jsonObj[key]["diaryReportID"]+"</td><td>"+jsonObj[key]["diaryID"]+"</td><td>"+jsonObj[key]["custID"]+"</td><td>"+jsonObj[key]["createdTime"]+"</td><td>"+jsonObj[key]["reportReason"]+"</td>"+fontStyleFac(jsonObj[key]["diaryReportID"],jsonObj[key]["reportResult"])+"</td>"+inp_btnSel+inp_btnCheck+"</tr>";
        }
        return commStr;
    }

    function fontStyleFac(tmpO,data){
        var tmpStr=(data==true?"<td class='respValue"+tmpO+" passVal'>審核通過</td>":"<td class='respValue"+tmpO+" noPassVal'>審核不通過</td>");
        return tmpStr;
    }

    function sdResult(){
        $(".pass").on("click",function(){
            $.ajax({
              url:servletPath(),
              data:{"metChoice":"respResult","diaryID":diaryID,"respValue":"true","diaryReportID":respRsID},
              type:"POST",
              success:function(data){
                console.log("respRsID"+respRsID);
                $(".respValue"+respRsID).html("審核通過");
                $(".respValue"+respRsID).removeClass("noPassVal");
                $(".respValue"+respRsID).addClass("passVal");

                // alert("審核結果送出成功!")
              },
              error:function(){
                alert("審核結果送出不成功!")
              }
            })
        });

        $(".noPass").on("click",function(data){
            $.ajax({
                url:servletPath(),
                data:{"metChoice":"respResult","diaryID":diaryID,"respValue":"false","diaryReportID":respRsID},
                type:"POST",
                success:function(){
                    console.log("respRsID"+respRsID);
                    $(".respValue"+respRsID).html("審核不通過");
                    $(".respValue"+respRsID).removeClass("passVal");
                    $(".respValue"+respRsID).addClass("noPassVal");
                    // alert("審核結果送出成功!")
                },
                error:function(){
                    alert("審核結果送出不成功!")
                }
              })
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

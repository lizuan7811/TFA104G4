$(function(){
    var commStr="";
    getDiary();
    $(document).ready(function(){
        
        $.ajax({
            url:"userlist/UserListServlet",
            data:{"metChoice":"getDiaryComms"},
            type:"POST",
            success:function(data){
                var jsonO=JSON.parse(data);                
                $(".mainbox").append(newElemStr(jsonO));
                $(".mainbox tbody tr td .inp_btnSel").on("click",selFunc(this)).stopPropagation;
                $(".mainbox tbody tr td .inp_btnDel").on("click",delFunc(this)).stopPropagation;    
            },
            error:function(){
            }
        })
    });
    function delFunc(){
        $(".inp_btnDel").click(function(){
            console.log($(this).attr("data-custID"));
            console.log($(this).attr("data-diaryID"));
              
        })
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
            
            console.log($(this).attr("data-custID"));
            console.log($(this).attr("data-diaryID"));
            reportReason=$(this).attr("data-repReason");
            var jsonOjj=diaryContant[String($(this).attr("data-diaryID"))];
            if(count==1)
            {
                    $(".showDiary").removeClass("tranToBigdv");
                    $(".showDiary").html("");
                    window.clearInterval(timeoutID);
                   count=0;
            }
            if(count<1)
            {
                window.clearInterval(timeoutID);
                timeoutID=window.setInterval(function(){
                    console.log($(window).scrollTop());
                    var sdHei=$(".showDiary").offset().top;
                    $(".showDiary").offset({
                        top:100+$(window).scrollTop()
                    })
                },600);
                window.setTimeout(function(){
                    $(".showDiary").addClass("tranToBigdv");
                    $(".showDiary").append(repStr(jsonOjj,reportReason));
                },1000);
            }
            count=count+1;
        })}
    function repStr(jsonOjj,rpReason){
        var result;
        var reason;
        var newEleStr="<ul><li>日誌標題:"+jsonOjj.subject+"</li><li>日誌建立時間:"+jsonOjj.fd_createdTime+"</li>日誌分享圖片<img class='picSize' src='data:image/jpb;base64,"+jsonOjj.photo+"'alt='日誌分享圖片'><li>敘述:"+jsonOjj.text+"</li><li>檢舉者暱稱"+jsonOjj.nickName+"</li><li>檢舉時間:"+jsonOjj.ct_createdTime+"</li><li>檢舉原因:"+reportReason+"</li>";
        return newEleStr;
    }
    function newElemStr(jsonObj)
    {
        repListObj=jsonObj;
        for(var key in jsonObj)
        {
            var inp_btnSel="<td><input class='inp_btnSel btnClaz' data-diaryID='"+jsonObj[key]["diaryID"]+"' data-custID='"+jsonObj[key]["custID"]+"' data-repReason='"+jsonObj[key]["reportReason"]+"' type=\"button\" value=\"審查\"></td>";
            var inp_btnDel="<td><input class='inp_btnDel btnClaz' data-diaryID='"+jsonObj[key]["diaryID"]+"' data-custID='"+jsonObj[key]["custID"]+"' type=\"button\" value=\"修改\"></td>";
            commStr=commStr+"<tr><td>"+jsonObj[key]["diaryReportID"]+"</td><td>"+jsonObj[key]["diaryID"]+"</td><td>"+jsonObj[key]["custID"]+"</td><td>"+jsonObj[key]["createdTime"]+"</td><td>"+jsonObj[key]["reportReason"]+"</td><td></td>"+inp_btnSel+inp_btnDel+"</tr>";
        }
        return commStr;
    }
});

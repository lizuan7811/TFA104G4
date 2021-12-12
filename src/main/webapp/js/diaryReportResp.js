$(function(){
    var commStr="";
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

    function selFunc(){
        $(".inp_btnSel").click(function(){
            console.log($(this).attr("data-custID"));
            console.log($(this).attr("data-diaryID"));    
        })    }

    function newElemStr(jsonObj)
    {
        for(var key in jsonObj)
        {
            var inp_btnSel="<td><input class='inp_btnSel btnClaz' data-diaryID='"+jsonObj[key]["diaryID"]+"' data-custID='"+jsonObj[key]["custID"]+"' type=\"button\" value=\"審查\"></td>";
            var inp_btnDel="<td><input class='inp_btnDel btnClaz' data-diaryID='"+jsonObj[key]["diaryID"]+"' data-custID='"+jsonObj[key]["custID"]+"' type=\"button\" value=\"修改\"></td>";
            commStr=commStr+"<tr><td>"+jsonObj[key]["diaryReportID"]+"</td><td>"+jsonObj[key]["diaryID"]+"</td><td>"+jsonObj[key]["custID"]+"</td><td>"+jsonObj[key]["createdTime"]+"</td><td>"+jsonObj[key]["reportReason"]+"</td><td></td>"+inp_btnSel+inp_btnDel+"</tr>";
        }
        return commStr;
    }
});

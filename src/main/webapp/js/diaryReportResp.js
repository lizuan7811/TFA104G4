$(function(){
    var commStr="";
    $(document).ready(function(){
        $.ajax({
            url:"userlist/UserListServlet",
            data:{"metChoice":"getDiaryComms"},
            type:"POST",
            success:function(data){
                console.log(data);
                var jsonO=JSON.parse(data);                
                $(".mainbox").append(newElemStr(jsonO));
            },
            error:function(){
            }
        })
    });
    var inp_btnSel="<td><FORM METHOD=\post\" ACTION=\"\" style=\"margin-bottom: 0px;\"><input type=\"submit\" value=\"查詢\"><input type=\"hidden\" name=\"\"  value=\"\"><input type=\"hidden\" name=\"action\"	value=\"getOne_For_Update\"></FORM></td>";
    var inp_btnDel="<td><FORM METHOD=\"post\" ACTION=\"\" style=\"margin-bottom: 0px;\"><input type=\"submit\" value=\"移除\"><input type=\"hidden\" name=\"diaryID\"  value=\"${diaryVO.diaryID}\"><input type=\"hidden\" name=\"action\" value=\"delete\"></FORM></td>";
    function newElemStr(jsonObj)
    {
        for(var key in jsonObj)
        {
            commStr=commStr+"<tr><td>"+jsonObj[key]["diaryReportID"]+"</td><td>"+jsonObj[key]["diaryID"]+"</td><td>"+jsonObj[key]["custID"]+"</td><td>"+jsonObj[key]["createdTime"]+"</td><td>"+jsonObj[key]["reportReason"]+"</td><td></td>"+inp_btnSel+inp_btnDel+"</tr>";
        }
        console.log(commStr);
        return commStr;
    }
});
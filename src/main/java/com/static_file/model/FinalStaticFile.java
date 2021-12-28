package com.static_file.model;

public class FinalStaticFile {
	
	public static final String APPLY="APPLY FRIEND";
	public static final String APPLIED="BE APPLIED";
	public static final String FRIENDLIST="FRIENDLIST";
	public static final String ADMIN_UPDATE="UPDATE Admin SET adminID=?,account=?,password=?,createdTime=?,authority=? WHERE idAdmin=?;";
	public static final String ADMIN_DELETE="DELETE FROM Admin";
	public static final String ADMIN_SELECT="SELECT * FROM Admin";
	public static final String SGADMIN_SELECT="SELECT * FROM Group4_db.Admin WHERE account=?";

	public static final String ADMIN_INSERT="INSERT INTO Admin(idAdmin,`account`,`password`,createdTime,authority)values(?,?,?,?,?);";

	public static final String USER_UPDATE="UPDATE Customer SET commentReportedNum=?,diaryReportedNum=?,suspended=? WHERE idCustomer=?;";

	public static final String SGUSER_DELETE="DELETE FROM Group4_db.Customer WHERE idCustomer= ?;";

	public static final String USER_SELECT="SELECT * FROM Group4_db.Customer;";
	public static final String SGUSER_SELECT="SELECT * FROM Customer WHERE account=?;";
	public static final String USER_INSERT="INSERT INTO Group4_db.Customer(idCustomer,name,profic,nickName,account,password,email,phone,createdTime,suspended,externalAcc,externalIdToken,commentReportedNum,diaryReportedNum)values(?,?,?,?,?,?,?,?,?,?,?,?,?,?);";

	public static final String ADMIN_SESSION="ADMIN_SESSION";

	public static final String DIARYLIKE_SELECT="SELECT * FROM DiaryLike;";

	public static final String DIARYLIKESG_SELECT="SELECT * FROM `DiaryLike` WHERE idCustomer=?;";

	public static final String DIARYLIKESG_INSERT="INSERT INTO `DiaryLike`(diaryLikeID,diaryID,idCustomer,createdTime)values(?,?,?,?);";

	public static final String DIARYLIKESG_DELETE="DELETE FROM `DiaryLike` WHERE diarylikeID=?;";
//	留言檢舉
	public static final String COMMENTREPORT_ALTER="UPDATE CommentReport SET createdTime=?,reportReason=?,reportResult=? WHERE commentID = ? and custNickName = ?;";

	public static final String COMMENTREPORT_INSERT="INSERT INTO CommentReport(commentReportID,commentID,custNickName,createdTime,reportReason,reportResult)VALUES(?,?,?,?,?,?);";
//	搜尋留言檢舉表
	public static final String COMMENTREPORT_SELECT="SELECT * FROM CommentReport WHERE custNickName = ? and commentReportID = ?;";
//	日誌檢舉----------------
	public static final String DIARYREPORT_ALTER="UPDATE DiaryReport SET createdTime=?,reportReason=?,reportResult=? WHERE diaryID = ? and custID = ?;";
	
	public static final String DIARYREPORT_INSERT="INSERT INTO DiaryReport(diaryID,custID,createdTime,reportReason,reportResult)VALUES(?,?,?,?,?);";
	
	public static final String DIARYREPORTSG_SELECT="SELECT * FROM DiaryReport WHERE custID = ? and diaryID = ?;";
	
	public static final String DIARYREPORT_SELECT="SELECT * FROM DiaryReport;";
	
	public static final String DIARYREPORTCHECK_UPDATE="UPDATE DiaryReport SET reportResult = ? WHERE diaryReportID = ? ;";
//	搜尋日誌
	public static final String FOODDIARY_SELECT="select fd.*,ct.* from FoodDiary fd join Customer ct on fd.custID=ct.idCustomer where diaryStatus=1;";
	
	
//	會員登入後，取得所有文章的每個按讚數，前端網頁使用

//	會員按讚後，資料寫入Jedis

	public static final String DIARYLIKE="DiaryLikeVO";
//	執行新增好友申請指令V
	public static final String FRIEND_INSERT="INSERT INTO Group4_db.Friend(friendChatID,custID,myfriendID,friendStatusNum,statusUpdate)VALUES(?,?,?,?,?);";
//	執行刪除好友指令
	public static final String FRIEND_DELETE="UPDATE Group4_db.Friend SET friendStatusNum = 2 ,statusUpdate = ? WHERE custID=? and myFriendID=?;";
//	執行同意後，將狀態修改為成為好友的指令
	public static final String FRIEND_UPDAET="UPDATE Group4_db.Friend SET friendStatusNum = 1 WHERE custID = ? and myfriendID = ?";
//	執行同意後，將狀態修改為成為好友的指令
	public static final String FRIEND_AGREE="UPDATE Group4_db.Friend SET friendStatusNum = 1 ,statusUpdate = ? WHERE custID=? and myFriendID=?;";

//	判斷資料庫中好友是否已存在，並返回ResultSet
//	為空，判斷就為false，若存在，就送出同意，然後更改狀態。
//	按同意就會執行agree

//	判斷資料庫中是否已存在好友申請，或是已經是好友
	public static final String FRIENDCOMP_SELECT="SELECT * FROM Group4_db.Friend where (custID=? and myFriendID=?) or (custID=? and myFriendID=?);";

//	是查詢所有好友使用
//	執行搜尋目前使用者登入後所有好友ID的指令，判斷登入的ID在好友表中的custID或myFriendID中存在的，且狀態為1或0的，分別搜尋到就顯示出來。
//	搜尋已成為好友的Customer
	public static final String FRIENDLISTONE_SELECT="SELECT DISTINCT(cu.idCustomer),cu.`name`,cu.profic,cu.nickName,cu.`account`,cu.email,cu.phone FROM Friend fr join Customer cu on fr.custID=cu.idCustomer or fr.myFriendID=cu.idCustomer WHERE (custID=? or myFriendID=?) and fr.friendStatusNum = 1 and cu.idCustomer != ?;";
//	搜尋好友狀態是被申請的
//	public static final String FRIENDLISZERO_SELECT="SELECT distinct(cu.idCustomer),cu.`name`,cu.profic,cu.nickName,cu.`account`,cu.email,cu.phone FROM Friend fr join Customer cu on fr.custID=cu.idCustomer or fr.myFriendID=cu.idCustomer WHERE (custID=? or myFriendID=?) and friendStatusNum = 0";
//	查詢待申請狀態下，申請及被申請者
//	申請的 找 被申請的 資料
	public static final String FRIENDAPPLI_SELECT="SELECT * FROM Friend fr join Customer cu on fr.myFriendID = cu.idCustomer where fr.custID = ? and friendStatusNum = 0;";
//	被申請的 找 申請 的資料
	public static final String FRIENDAPPLIED_SELECT="SELECT * FROM Friend fr join Customer cu on fr.custID = cu.idCustomer where fr.myFriendID = ? and friendStatusNum = 0;";

//-----以下為訂單使用指令-----
//	搜尋歷史定單，包括已生成的
	public static final String FINALUSERORDER_SELECT="SELECT * FROM FINALORDER WHERE idCustomer = ?;";
//	搜尋消費者最新一筆訂單的訂單id
	public static final String USERLATESTORDER_SELECT="select idFinalOrder from FinalOrder WHERE idCustomer = ? group by idFinalOrder order by createdTime desc limit 1;";
//	搜尋所有消費者歷史訂單
	public static final String FINALORDERALL_SELECT="SELECT * FROM Group4_db.FinalOrder;";
//	搜尋所有消費者自己的歷史訂單
	public static final String FINALORDERSG_SELECT="SELECT * FROM Group4_db.FinalOrder WHERE idCustomer = ?;";
//	若選擇食譜，需要搜尋該食譜，對照食材取出食材品名及單價
//	搜尋所有食譜資料
	public static final String RECIPEALL_SELECT="SELECT * FROM RECIPE;";
//	搜尋單個食譜資料
	public static final String RECIPESG_SELECT="SELECT * FROM RECIPE WHERE idRecipe = ? ;";
//	搜索食材詳細資料
	public static final String INGREALL_SELECT="SELECT * FROM Ingre;";
//	訂單的詳細資料需存在OrderIngreList
	public static final String ORDERINGRELIST_INSERT="INSERT INTO TempOrder(idFinalOrder,idIngre,orderQuan,price)VALUES(?,?,?,?);";
//	取得各個食材單價後，計算該訂單的總金額，是否與前端傳的金額相符，若相符則寫入資料庫。	
//	新增訂單，搜尋材料及價錢，生成訂單(這裡存的只有訂單的列表資料，詳細資料會是在對照表中)，新增前須確定已付款
	public static final String FINALORDERSG_INSERT="INSERT INTO FINALORDER(idCustomer,recipient,recipientAddress,orderAmount,createdTime,shipTime,arrivalTime,footnote)VALUES(?,?,?,?,?,?,?,?);";
//  刪除已生成的訂單或歷史訂單
	public static final String FINALORDERSG_DELETE="DELETE FROM FINALORDER WHERE idFinalOrder = ?;";
//	搜尋所有食譜食材對照表資料
	public static final String RECIPEINGRE_SELECT="SELECT * FROM RecipeIngre;";
//	寫入訂單的詳細清單資料庫
//	public static final String TEMPORDER_INSERT="INSERT INTO TempOrder(idFinalOrder,idIngre,orderQuan,price)VALUES(?,?,?,?);";
//	修改使用者日誌的日誌狀態
	public static final String FOODDIARY_UPDATE="UPDATE FoodDiary SET diarystatus = ? WHERE diaryID = ?;";
	
	public static final String ORDERINGRELIST_SELECT="SELECT * FROM Group4_db.TempOrder order by idFinalOrder asc;";

	public static final String COMMENT_INSERT = "INSERT INTO Comment(diaryID,custNickName,createdTime,commentText,commentStatus)VALUES(?,?,?,?,?);";

	public static final String COMMENTSG_SELECT = "SELECT commentID FROM Comment WHERE diaryID = ? and custNickName = ? order by createdTime desc limit 1;";
}

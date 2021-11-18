package you.contents;

public class FinalStaticFile {
	public final static String ADMIN_UPDATE="UPDATE Admin SET adminID=?,adminAcco=?,AdminPass=?,createdTime=?,adminAuthority=? WHERE adminID=?;";
	public final static String ADMIN_DELETE="DELETE FROM Admin";
	public final static String ADMIN_SELECT="SELECT * FROM Admin";
	public final static String SGADMIN_SELECT="SELECT * FROM Admin WHERE adminAcco=?";

	public final static String ADMIN_INSERT="INSERT INTO Admin(adminID,adminAcco,adminPass,createdTime,adminAuthority)values(?,?,?,?,?);";
	
	public final static String USER_UPDATE="UPDATE Customer SET commentReportedNum=?,diaryReportedNum=?,suspended=? WHERE idCustomer=?;";
	public final static String SGUSER_DELETE="DELETE FROM Customer WHERE idCustomer=?";
	
	public final static String USER_SELECT="SELECT * FROM Customer";
	public final static String SGUSER_SELECT="SELECT * FROM Customer WHERE account=?";
	public final static String USER_INSERT="INSERT INTO Customer(idCustomer,name,profic,nickName,account,password,email,phone,createdTime,suspended,externalAcc,externalIdToken,commentReportedNum,diaryReportedNum)values(?,?,?,?,?,?,?,?,?,?,?,?,?,?);";

	public final static String ADMIN_SESSION="ADMIN_SESSION";
	
	public final static String DIARYLIKE_SELECT="SELECT * FROM DiaryLike;";

	public final static String DIARYLIKESG_SELECT="SELECT * FROM `DiaryLike` WHERE idCustomer=?;";
	
	public final static String DIARYLIKESG_INSERT="INSERT INTO `DiaryLike`(diaryLikeID,diaryID,idCustomer,createdTime)values(?,?,?,?);";

	public final static String DIARYLIKESG_DELETE="DELETE FROM `DiaryLike` WHERE diarylikeID=?;";

}

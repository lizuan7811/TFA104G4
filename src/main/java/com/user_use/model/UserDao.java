package com.user_use.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.json.JSONArray;
import org.json.JSONObject;

import com.pojo.model.AdminVO;

public interface UserDao {
//	增
//	public void insertUser(Connection conn,PreparedStatement ps);
//	刪
	public void deleteUser(Connection conn, PreparedStatement ps, Integer usPos);

//	修
	public void alterUser(Connection conn, PreparedStatement ps, Integer usPos, Object... paras);

//	查
	public ResultSet selectUsers(Connection conn, PreparedStatement ps, String username);

//	帳號登入
	public AdminVO adminLogin(Connection conn, PreparedStatement ps, String username, String password);

//	點讚後，增加數量到資料庫，需存入點讚的custID
	public void insDiaryLike(Connection conn, PreparedStatement ps, Integer idCustomer, Integer diaryID);

//	刪讚，在資料庫中已存在的ID，重複點讚就刪除
	public void delDiaryLike(Connection conn, PreparedStatement ps, Integer diaryLikeID, Integer diaryID);

//	搜尋日誌的點讚資料
	public Integer selDiaryLike(Connection conn, PreparedStatement ps, Integer diaryID);

//	使用者加好友
	public Integer userAddFriend(Connection conn, PreparedStatement ps, Integer custID, Integer friendID);

//	加好友前判斷資料庫是否存在同樣資料，防錯
	public ResultSet selectFriend(Connection conn, PreparedStatement ps, Integer custID, Integer friendID);

	public Integer rejectFriend(Connection conn, PreparedStatement ps, Integer custID, Integer friendID);

	// sql="UPDATE Group4_db.Friend SET friendStatusNum = ? ,statusUpdate = ? where
	// (custID=? and myFriendID=?) or (custID=? and myFriendID=?)"
	public Integer agreeAddFriend(Connection conn, PreparedStatement ps, Integer custID, Integer myFriendID);

//	"SELECT * FROM Group4_db.Friend where (custID=? and myFriendID=?) or (custID=? and myFriendID=?)"
	public JSONArray selectFriend(Connection conn, PreparedStatement ps, Integer custID, String sql);

//	public JSONArray selectApplied(Connection conn,PreparedStatement ps,Integer custID);
	public JSONArray getDiaryComms(Connection conn, PreparedStatement ps);

	public JSONObject getDiaryReported(Connection conn, PreparedStatement ps);

	public Integer updateDiaryResponse(Connection conn, PreparedStatement ps,Integer diaryID, Integer diaryReportID,Boolean checkResult);
	
//	public JSONArray selFinalOrderAll(Connection conn,PreparedStatement ps);

}

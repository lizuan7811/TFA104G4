package com.user_use.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.json.JSONArray;

import com.pojo.model.AdminVO;


public interface UserDao {
//	增
//	public void insertUser(Connection conn,PreparedStatement ps);
//	刪
	public void deleteUser(Connection conn,PreparedStatement ps,Integer usPos);
//	修
	public void alterUser(Connection conn,PreparedStatement ps,Integer usPos,Object...paras);
//	查
	public ResultSet selectUsers(Connection conn,PreparedStatement ps,String username);
//	帳號登入
	public AdminVO adminLogin(Connection conn,PreparedStatement ps, String username, String password);

	public void insDiaryLike(Connection conn,PreparedStatement ps,Integer idCustomer,Integer diaryID);

	public void delDiaryLike(Connection conn,PreparedStatement ps,Integer diaryLikeID,Integer diaryID);

	public Integer selDiaryLike(Connection conn,PreparedStatement ps,Integer diaryID);

	public Integer userAddFriend(Connection conn,PreparedStatement ps, Integer custID, Integer friendID);

	//	sql="UPDATE Group4_db.Friend SET friendStatusNum = ? ,statusUpdate = ? where (custID=? and myFriendID=?) or (custID=? and myFriendID=?)"
	public Integer agreeAddFriend(Connection conn,PreparedStatement ps,Integer custID,Integer myFriendID);

//	"UPDATE Group4_db.Friend SET friendStatusNum = 1 WHERE custID = ? and myfriendID = ?";
//	public void updateFriStatus(Connection conn,PreparedStatement ps,Integer custID,Integer friendID);
	//	sql="SELECT * FROM Group4_db.Friend where (custID=? and myFriendID=?) or (custID=? and myFriendID=?)"
	public JSONArray selectOwnFriend(Connection conn,PreparedStatement ps,Integer custID);

}

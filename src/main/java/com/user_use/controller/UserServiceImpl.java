package com.user_use.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

import com.basic_tool.controller.Util;
import com.static_file.model.FinalStaticFile;
import com.user_use.model.UserBO;
import com.user_use.model.UserBOImpl;
import com.user_use.model.UserDao;
import com.user_use.model.UserDaoImpl;

public class UserServiceImpl implements UserService {
	private UserDao usd;
	private UserBO usbo;

	public UserServiceImpl() {
		usd = new UserDaoImpl();
		usbo = new UserBOImpl();
	}

	@Override
	public void deleteUser(Integer usPos) {
		Connection conn = Util.getConnection();
		PreparedStatement ps = null;
		usd.deleteUser(conn, ps, usPos);
	}

	@Override
	public void alterUser(Integer usPos, Object... paras) {
		Connection conn = Util.getConnection();
		PreparedStatement ps = null;
		usd.alterUser(conn, ps, usPos, paras);
	}

	@Override
	public JSONArray selectUser(String username) {
		Connection conn = Util.getConnection();
		PreparedStatement ps = null;
		JSONArray userJsonArr = usbo.getUsersToJSON(conn, ps, username);
		return userJsonArr;
	}

//	如果like數量增加或減少，就要執行到select count(*) from liketable更新讚數
//	回傳SELECT * FROM DiaryLike;
	@Override
	public Integer addOrDelLikeService(Integer diaryID, Integer idCustomer) {
		// TODO Auto-generated method stub
		Integer likeNum = 0;
		likeNum = usbo.addOrDelClickBO(idCustomer, diaryID);
		return likeNum;
	}
	@Override
	public Integer serviceCommReport(Integer commentID,Integer diaryID, String custNickName, String reportReason) {
		Connection conn = Util.getConnection();
		PreparedStatement ps = null;
		int successRow = 0;
		usbo.userCommReport(conn, ps, commentID,diaryID, custNickName, reportReason);
		return successRow;
	}

	@Override
	public Integer serviceDiaryReport(String diaryID, String custID, String reportReason) {
		Connection conn = Util.getConnection();
		PreparedStatement ps = null;
		Integer successRow = 0;
		try {
			conn.setAutoCommit(false);
			successRow = usbo.foodDiaryStatus(conn, ps, diaryID, custID, true);
			successRow += usbo.userDiaryReport(conn, ps, diaryID, custID, reportReason);
			System.out.println(successRow > 0 ? "送出檢舉成功!" : "檢舉送出失敗!");
			conn.commit();
		} catch (SQLException e) {
			try {
				conn.rollback();
				conn.setAutoCommit(true);
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			Util.closeConnection(conn, ps);
		}
		return successRow;
	}

	@Override
	public Integer serviceAddFriend(String metChoice, Integer custID, Integer myFriendID) {
		Integer addFriendNum = 0;
		Integer agreeFriendNum = 0;
		Integer ans = 0;
		Connection conn = Util.getConnection();
		PreparedStatement ps = null;
		if (custID == null || myFriendID == null) {
			return 0;
		}
		if ("addFriend".equals(metChoice)) {
			System.out.println("UserServiceImpl  serviceAddFriend()");
			addFriendNum = usd.userAddFriend(conn, ps, custID, myFriendID);
		} else if ("agreeFriend".equals(metChoice)) {
			agreeFriendNum = usd.agreeAddFriend(conn, ps, custID, myFriendID);
		}

		if (addFriendNum > 0) {
			System.out.println("好友申請提交成功，待對方同意!");
		} else if (agreeFriendNum > 0) {
			System.out.println("已同意該好友申請!");
		}
		return ans = agreeFriendNum > 0 ? agreeFriendNum : addFriendNum;
	}

	@Override
	public JSONArray serviceAboutFriend(String metChoice, Integer custID) {
		Connection conn = Util.getConnection();
		PreparedStatement ps = null;
		JSONObject jsonArr = new JSONObject();
		JSONArray ansJson = new JSONArray();
		Map<String, String> userData = new HashMap<String, String>();
		if ("friendList".equals(metChoice)) {
//			取得已經成為好友的列表資料，搜尋狀態為1的好友的列表
			jsonArr.put(FinalStaticFile.FRIENDLIST,
					usd.selectFriend(conn, ps, custID, FinalStaticFile.FRIENDLISTONE_SELECT));
		} else if ("applyList".equals(metChoice)) {
//			自己在custID被搜尋，狀態為0的結果，0代表已申請代同意
			jsonArr.put(FinalStaticFile.APPLY, usd.selectFriend(conn, ps, custID, FinalStaticFile.FRIENDAPPLI_SELECT));
//			userData.put(FinalStaticFile.APPLY, jsonArr.toString());
//			自己在myfriendID搜尋，狀態為0的結果，0代表已申請待同意
			jsonArr.put(FinalStaticFile.APPLIED,
					usd.selectFriend(conn, ps, custID, FinalStaticFile.FRIENDAPPLIED_SELECT));
//			userData.put(FinalStaticFile.APPLIED, jsonArr.toString());
		}
		ansJson.put(jsonArr);
		if (ansJson.toString() == null || ansJson.toString().length() == 0) {
			System.out.println("ansJson為空!");
		}
		return ansJson;
	}

	public JSONArray serviceGetDiaryComms() {
		Connection conn = Util.getConnection();
		PreparedStatement ps = null;

		return usd.getDiaryComms(conn, ps);
	}

	public JSONObject serviceGetDiaryReported() {
		Connection conn = Util.getConnection();
		PreparedStatement ps = null;

		return usd.getDiaryReported(conn, ps);
	}

	public Integer serviceDiaryRpCheck(Integer diaryID,Integer diaryRpID, Boolean drCheck) {
		Connection conn = Util.getConnection();
		PreparedStatement ps = null;
		return usd.updateDiaryResponse(conn, ps,diaryID, diaryRpID, drCheck);
	}

	public Integer serviceComment(String diaryID,String custNickName,String comments) {
		Integer commentID=0;
		Connection conn = Util.getConnection();
		PreparedStatement ps = null;
		commentID=usbo.insertComments(conn, ps, diaryID, custNickName, comments);
		Util.closeConnection(conn, ps);
		return commentID;
	}
	
//	public JSONArray serviceAppliedFriend(String metChoice, Integer custID) {
////	被加好友
//		Connection conn=Util.getConnection();
//		PreparedStatement ps=null;
//		JSONArray jsonArr=new JSONArray();
//		jsonArr=usd.selectFriend(conn, ps, custID, FinalStaticFile.FRIENDAPPLIED_SELECT);
//		return null;
//	}

}

package com.user_use.model;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

import com.basic_tool.controller.BaseDao;
import com.basic_tool.controller.BaseDaoImpl;
import com.basic_tool.controller.FileWorkDaoImpl;
import com.finalorder.model.FinalOrderDaoImpl;
import com.mysql.cj.util.Base64Decoder;
import com.pojo.model.AdminVO;
import com.pojo.model.CustomerVO;
import com.static_file.model.FinalStaticFile;

public class UserDaoImpl implements UserDao {
	private BaseDao bdi;
	private AdminVO admin;
	private Calendar cl;
	private CustomerVO custVO;
	Map<String, String> datas;

	public UserDaoImpl() {
		cl = Calendar.getInstance();
		admin = new AdminVO();
		bdi = new BaseDaoImpl();
		datas = new HashMap<>();
	}

	@Override
	public void deleteUser(Connection conn, PreparedStatement ps, Integer usPos) {
		int tempN = bdi.deleteData(conn, ps, FinalStaticFile.SGUSER_DELETE, usPos.toString());
	}

	@Override
	public void alterUser(Connection conn, PreparedStatement ps, Integer usPos, Object... paras) {
		int tempN = bdi.updateData(conn, ps, FinalStaticFile.USER_UPDATE, usPos.toString());
	}

	@Override
	public ResultSet selectUsers(Connection conn, PreparedStatement ps, String username) {

		datas.put("user", username);
//		datas.put("friend", username);
		System.out.println("username=" + username);
		ResultSet rs = bdi.selectData(conn, ps, FinalStaticFile.USER_SELECT, datas);
		System.out.println("UserDaoImpl	selectUsers()\t" + FinalStaticFile.USER_SELECT);
		if (rs == null) {
			System.out.println("沒有搜尋到任何資料!");
		}
		return rs;
	}

	@Override
	public AdminVO adminLogin(Connection conn, PreparedStatement ps, String username, String password) {
		datas.put("admin", username);
		ResultSet rs = bdi.selectData(conn, ps, FinalStaticFile.ADMIN_SELECT, datas);
		if (rs == null) {
			System.out.println(username + "\t尚未未註冊!");
			return null;
		}
		try {
			while (rs.next()) {
				admin.setAdminID(rs.getInt("idAdmin"));
				admin.setAdminAcco(rs.getString("account"));
				admin.setAdminPass(rs.getString("password"));
				admin.setCreatedTime(rs.getTimestamp("createdTime"));
				admin.setAdminAuthority(rs.getBoolean("authority"));
			}
			if (admin.getAdminPass() != null) {
				if ((admin.getAdminPass()).equals(password)) {
					System.out.println("使用者帳號及密碼相符，驗證成功!");
					return admin;
				}
			} else {
				System.out.println("登入密碼錯誤!");
				return null;
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

//增加like數量
	@Override
	public void insDiaryLike(Connection conn, PreparedStatement ps, Integer idCustomer, Integer diaryID) {
		// TODO Auto-generated method stub
		try {
			ps = conn.prepareStatement(FinalStaticFile.DIARYLIKESG_INSERT);
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

//刪除like數量
	@Override
	public void delDiaryLike(Connection conn, PreparedStatement ps, Integer diaryLikeID, Integer diaryID) {
		// TODO Auto-generated method stub
		try {
			ps = conn.prepareStatement(FinalStaticFile.DIARYLIKESG_DELETE);
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

//更新like數量
	@Override
	public Integer selDiaryLike(Connection conn, PreparedStatement ps, Integer diaryID) {
		// TODO Auto-generated method stub
		try {
			ps = conn.prepareStatement(FinalStaticFile.DIARYLIKESG_SELECT);
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

//	加好友前送出指令判斷資料庫中有沒有存入一樣的資訊了，若無則增加，有責不執行增加
	public ResultSet selectFriend(Connection conn, PreparedStatement ps, Integer custID, Integer friendID) {
		String sql = FinalStaticFile.FRIENDCOMP_SELECT;
		Boolean flag = false;
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, custID);
			ps.setInt(2, friendID);
			ps.setInt(3, friendID);
			ps.setInt(4, custID);
			rs = ps.executeQuery();
//	為空，就可以增加增加，反之不增加
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rs;
	}

//加好友功能
	@Override
	public Integer userAddFriend(Connection conn, PreparedStatement ps, Integer custID, Integer friendID) {
//		"INSERT INTO Friend(friendCharID,custID,myfriendID,friendStatusNum,statusUpdate)VALUES(?,?,?,?,?);";

		String sql = FinalStaticFile.FRIEND_INSERT;
		Integer successNum = 0;
		try {
			if (selectFriend(conn, ps, custID, friendID).next() != false) {
				System.out.println("待對方同意申請!");
				return 0;
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, 0);// 存入整個Friend表的PK，但是預設就是0，在資料庫會自動往後新增
			ps.setInt(2, custID);// 存入申請人ID
			ps.setInt(3, friendID);// 存入被申請人的ID
			ps.setInt(4, 0);// 好友狀態，剛申請待同意的時候，狀態都呈現為0
			ps.setTimestamp(5, new Timestamp(cl.getTimeInMillis()));// 申請好友開始時間
			successNum = ps.executeUpdate();
			if (successNum > 0) {
				System.out.println("新增一條增加好友訊息" + successNum);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return successNum;
	}

//同意申請，改好友狀態
//	sql="UPDATE Group4_db.Friend SET friendStatusNum = 1 ,statusUpdate = ? where custID=? and myFriendID=?;"
	@Override
	public Integer agreeAddFriend(Connection conn, PreparedStatement ps, Integer custID, Integer myFriendID) {
		int successNum = 0;
		try {

			ps = conn.prepareStatement(FinalStaticFile.FRIEND_AGREE);
			ps.setTimestamp(1, new Timestamp(Calendar.getInstance().getTimeInMillis()));
			ps.setInt(2, myFriendID);
			ps.setInt(3, custID);
			ps.executeUpdate();
			if (successNum > 0) {
				System.out.println("修改" + successNum + "好友申請成功!");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return successNum;
	}

//	會員轉址到日誌後執行搜索好友列表並回傳
//	"SELECT * FROM Group4_db.Friend where custID=? or myFriendID=?;"
	@Override
	public JSONArray selectFriend(Connection conn, PreparedStatement ps, Integer custID, String sql) {
		JSONArray jsonFriArr = new JSONArray();
		JSONObject friendList = new JSONObject();
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement(sql);
			if (FinalStaticFile.FRIENDLISTONE_SELECT.equals(sql)) {
//FRIENDLISTONE_SELECT="SELECT DISTINCT(cu.idCustomer),cu.`name`,cu.profic,cu.nickName,cu.`account`,cu.email,cu.phone FROM Friend fr join Customer cu on fr.custID=cu.idCustomer or fr.myFriendID=cu.idCustomer WHERE (custID=? or myFriendID=?) and fr.friendStatusNum = 1 and cu.idCustomer != ?;";
				ps.setInt(1, custID);
				ps.setInt(2, custID);
				ps.setInt(3, custID);
			} else if (FinalStaticFile.FRIENDAPPLI_SELECT.equals(sql)) {
				ps.setInt(1, custID);
			} else if (FinalStaticFile.FRIENDAPPLIED_SELECT.equals(sql)) {
				ps.setInt(1, custID);
			}
			rs = ps.executeQuery();
			while (rs.next()) {
//				StringBuffer strBuf=new StringBuffer();
				friendList.clear();
				friendList.put("idCustomer", rs.getInt("cu.idCustomer"));
				friendList.put("name", rs.getString("cu.name"));
//				friendList.put("profic",rs.getBytes("profic"));
				Base64.Encoder base64Encoder = Base64.getEncoder();
				friendList.put("profic", base64Encoder.encodeToString(rs.getBytes("profic")));
//				System.out.println(base64Encoder.encodeToString(rs.getBytes("profic")));
				friendList.put("nickName", rs.getString("nickName"));
				friendList.put("account", rs.getString("cu.account"));
				friendList.put("email", rs.getString("cu.email"));
				friendList.put("phone", rs.getString("cu.phone"));

				if (FinalStaticFile.FRIENDAPPLI_SELECT.equals(sql)) {
					friendList.put("apply", "applyFriend");
				} else if (FinalStaticFile.FRIENDAPPLIED_SELECT.equals(sql)) {
					friendList.put("apply", "appliedFriend");
				}
//				friList.add(custVO);
				jsonFriArr.put(friendList.toMap());
			}
			System.out.println(jsonFriArr);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jsonFriArr;
	}

	@Override
	public Integer rejectFriend(Connection conn, PreparedStatement ps, Integer custID, Integer friendID) {

		return null;
	}

	@Override
	public JSONArray getDiaryComms(Connection conn, PreparedStatement ps) {
		JSONArray jsonArr = new JSONArray();
		try {
			ps = conn.prepareStatement(FinalStaticFile.DIARYREPORT_SELECT);
			JSONObject jsonObj = new JSONObject();
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				jsonObj.put("diaryReportID", rs.getInt("diaryReportID"));
				jsonObj.put("diaryID", rs.getInt("diaryID"));
				jsonObj.put("custID", rs.getInt("custID"));
				jsonObj.put("createdTime", rs.getTimestamp("createdTime"));
				jsonObj.put("reportReason", rs.getString("reportReason"));
				jsonObj.put("reportResult", rs.getBoolean("reportResult"));
				jsonArr.put(jsonObj.toMap());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return jsonArr;
	}

	public JSONObject getDiaryReported(Connection conn, PreparedStatement ps) {
		JSONObject jsonOutObj = new JSONObject();
		try {
			ps = conn.prepareStatement(FinalStaticFile.FOODDIARY_SELECT);
			ResultSet rs = ps.executeQuery();
			JSONObject jsonObj = new JSONObject();

			while (rs.next()) {
				jsonObj.clear();
				jsonObj.put("diaryID", rs.getInt("diaryID"));
				jsonObj.put("custID", rs.getInt("custID"));
				jsonObj.put("subject", rs.getString("subject"));
				jsonObj.put("text", rs.getString("text"));
				jsonObj.put("fd_createdTime", rs.getTimestamp("fd.createdTime"));
				jsonObj.put("ct_createdTime", rs.getTimestamp("ct.createdTime"));
				String photoStr = FileWorkDaoImpl.photoToBase64Str(rs.getBytes("photo_video_1"));
				jsonObj.put("photo", photoStr);
				jsonObj.put("diaryStatus", rs.getBoolean("diaryStatus"));
				jsonObj.put("nickName", rs.getString("nickName"));
				jsonObj.put("email", rs.getString("email"));
				jsonObj.put("diaryReportedNum", rs.getString("diaryReportedNum"));
				jsonOutObj.put(String.valueOf(rs.getInt("diaryID")), jsonObj.toMap());
			}
			System.out.println(jsonOutObj);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return jsonOutObj;
	}

	@Override
	public Integer updateDiaryResponse(Connection conn, PreparedStatement ps,Integer diaryID, Integer diaryReportID,
			Boolean checkResult) {
		Integer executeNum = 0;
		try {
			ps=conn.prepareStatement(FinalStaticFile.FOODDIARY_UPDATE);
			ps.setBoolean(1,checkResult);
			ps.setInt(2,diaryID);
			ps.executeUpdate();
			ps = conn.prepareStatement(FinalStaticFile.DIARYREPORTCHECK_UPDATE);
			ps.setBoolean(1, checkResult);
			ps.setInt(2, diaryReportID);
			
			executeNum = ps.executeUpdate();
			System.out.println(executeNum > 0 ? "審核結果寫入成功!" : "審核結果寫入不成功!");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return executeNum;
	}
	
	
	
//	@Override
//	public JSONArray selectApplied(Connection conn, PreparedStatement ps, Integer custID) {
////		申請的 找 被申請的 資料
////	FRIENDAPPLI_SELECT="SELECT * FROM Friend fr join Customer cu on fr.myFriendID = cu.idCustomer where fr.custID = ? and friendStatusNum = 0;";
//		
//		
////		被申請的 找 申請 的資料
////	FRIENDAPPLIED_SELECT="SELECT * FROM Friend fr join Customer cu on fr.custID = cu.idCustomer where fr.myFriendID = ? and friendStatusNum = 0;";
//		
//		
//		
//		return null;
//	}

	

}

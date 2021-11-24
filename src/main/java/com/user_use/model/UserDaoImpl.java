package com.user_use.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;

import com.basic_tool.controller.BaseDao;
import com.basic_tool.controller.BaseDaoImpl;
import com.pojo.model.AdminVO;
import com.pojo.model.CustomerVO;
import com.static_file.model.FinalStaticFile;



public class UserDaoImpl implements UserDao{
	private BaseDao bdi;
	private AdminVO admin;
	private Calendar cl;
	private CustomerVO custVO;
	Map<String,String> datas;
	public UserDaoImpl()
	{
		cl=Calendar.getInstance();
		admin=new AdminVO();
		bdi=new BaseDaoImpl();
		datas=new HashMap<>();
	}

	@Override
	public void deleteUser(Connection conn, PreparedStatement ps, Integer usPos) {
		int tempN=bdi.deleteData(conn, ps,FinalStaticFile.SGUSER_DELETE,usPos.toString());
	}

	@Override
	public void alterUser(Connection conn, PreparedStatement ps, Integer usPos, Object... paras) {
		int tempN=bdi.updateData(conn, ps, FinalStaticFile.USER_UPDATE,usPos.toString());
	}

	@Override
	public ResultSet selectUsers(Connection conn, PreparedStatement ps, String username) {

		datas.put("user",username);
//		datas.put("friend", username);
		System.out.println("username="+username);
		ResultSet rs=bdi.selectData(conn, ps, FinalStaticFile.USER_SELECT,datas);
		System.out.println("UserDaoImpl	selectUsers()\t"+FinalStaticFile.USER_SELECT);
		if(rs==null)
		{
			System.out.println("沒有搜尋到任何資料!");
		}
		return rs;
	}

	@Override
	public AdminVO adminLogin(Connection conn,PreparedStatement ps,String username,String password)
	{
		datas.put("admin", username);
		ResultSet rs=bdi.selectData(conn,ps,FinalStaticFile.ADMIN_SELECT,datas);
		if(rs==null)
		{
			System.out.println(username+"\t尚未未註冊!");
			return null;
		}
		try {
			while(rs.next())
			{
				admin.setAdminID(rs.getInt("idAdmin"));
				admin.setAdminAcco(rs.getString("account"));
				admin.setAdminPass(rs.getString("password"));
				admin.setCreatedTime(rs.getTimestamp("createdTime"));
				admin.setAdminAuthority(rs.getBoolean("authority"));
			}
			if(admin.getAdminPass()!=null) {
				if((admin.getAdminPass()).equals(password)) {
					System.out.println("使用者帳號及密碼相符，驗證成功!");
					return admin;
				}
			}
			else
			{
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
			ps=conn.prepareStatement(FinalStaticFile.DIARYLIKESG_INSERT);
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
//刪除like數量
	@Override
	public void delDiaryLike(Connection conn, PreparedStatement ps, Integer diaryLikeID,Integer diaryID) {
		// TODO Auto-generated method stub
		try {
			ps=conn.prepareStatement(FinalStaticFile.DIARYLIKESG_DELETE);
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
			ps=conn.prepareStatement(FinalStaticFile.DIARYLIKESG_SELECT);
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
//加好友功能
	@Override
	public Integer userAddFriend(Connection conn, PreparedStatement ps, Integer custID, Integer friendID) {
//		"INSERT INTO Friend(friendCharID,custID,myfriendID,friendStatusNum,statusUpdate)VALUES(?,?,?,?,?);";

		String sql=FinalStaticFile.FRIEND_INSERT;
		Integer successNum=0;
		try {
			ps=conn.prepareStatement(sql);
			ps.setInt(1, 0);//存入整個Friend表的PK，但是預設就是0，在資料庫會自動往後新增
			ps.setInt(2, custID);//存入申請人ID
			ps.setInt(3, friendID);//存入被申請人的ID
			ps.setInt(4, 0);//好友狀態，剛申請待同意的時候，狀態都呈現為0
			ps.setTimestamp(5,new Timestamp(cl.getTimeInMillis()));//申請好友開始時間
			successNum=ps.executeUpdate();
			if(successNum>0)
			{
				System.out.println("新增一條增加好友訊息"+successNum);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return successNum;
	}
//同意申請，改好友狀態
//	sql="UPDATE Group4_db.Friend SET friendStatusNum = 1 ,statusUpdate = ? where (custID=? and myFriendID=?) or (custID=? and myFriendID=?)"
	@Override
	public Integer agreeAddFriend(Connection conn, PreparedStatement ps, Integer custID, Integer myFriendID) {
		int successNum=0;
		try {

			ps=conn.prepareStatement(FinalStaticFile.FRIEND_AGREE);
			ps.setTimestamp(1, new Timestamp(Calendar.getInstance().getTimeInMillis()));
			ps.setInt(2, custID);
			ps.setInt(3, myFriendID);
			ps.setInt(4,myFriendID);
			ps.setInt(5, custID);
			ps.executeUpdate();
			if(successNum>0)
			{
				System.out.println("確認對方申請好友訊息"+successNum);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return successNum;
	}
//	會員登入後執行搜索好友列表並回傳
//	"SELECT * FROM Group4_db.Friend where custID=? or myFriendID=?;"
	@Override
	public JSONArray selectOwnFriend(Connection conn, PreparedStatement ps, Integer custID) {
		JSONArray jsonFriArr=new JSONArray();
		ResultSet rs=null;
		List<CustomerVO> friList=new ArrayList<>();
		try {
//			public final static String FRIENDLIST_SELECT="SELECT cu.idCustomer,cu.`name`,cu.profic,cu.nickName,cu.`account`,cu.email,cu.phone FROM Friend fr join Customer cu on fr.custID=cu.idCustomer or fr.myFriendID=cu.idCustomer WHERE custID=? or myFriendID=?";
			ps=conn.prepareStatement(FinalStaticFile.FRIENDLIST_SELECT);
			ps.setInt(1, custID);
			ps.setInt(2, custID);

			rs=ps.executeQuery();
			while(rs.next())
			{
				custVO=new CustomerVO();
				custVO.setIdCustomer(rs.getInt("cu.idCustomer"));
				custVO.setName(rs.getString("cu.name"));
				custVO.setProfic(rs.getBytes("cu.profic"));
				custVO.setNickName(rs.getString("nickName"));
				custVO.setAccount(rs.getString("cu.account"));
				custVO.setEmail(rs.getString("cu.email"));
				custVO.setPhone(rs.getString("cu.phone"));
				friList.add(custVO);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}




}

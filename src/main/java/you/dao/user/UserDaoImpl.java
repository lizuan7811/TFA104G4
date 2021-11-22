package you.dao.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import you.basicdao.BaseDao;
import you.basicdao.BaseDaoImpl;
import you.contents.FinalStaticFile;
import you.pojo.AdminVO;


public class UserDaoImpl implements UserDao{
	private BaseDao bdi;
	private AdminVO admin;
	Map<String,String> datas;
	public UserDaoImpl()
	{
		admin=new AdminVO();
		bdi=new BaseDaoImpl();
		datas=new HashMap<String,String>();
	}

	public void deleteUser(Connection conn, PreparedStatement ps, Integer usPos) {
		int tempN=bdi.deleteData(conn, ps,FinalStaticFile.SGUSER_DELETE,usPos.toString());
	}

	public void alterUser(Connection conn, PreparedStatement ps, Integer usPos, Object... paras) {
		int tempN=bdi.updateData(conn, ps, FinalStaticFile.USER_UPDATE,usPos.toString());
	}

	public ResultSet selectUsers(Connection conn, PreparedStatement ps, String username) {
		
		datas.put("user",username);
		System.out.println("username="+username);
		ResultSet rs=bdi.selectData(conn, ps, FinalStaticFile.USER_SELECT,datas);
		System.out.println("FinalStaticFile.USER_SELECT="+FinalStaticFile.USER_SELECT);
		if(rs==null)
		{
			System.out.println("沒有搜尋到任何資料!");
		}
		return rs;
	}

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
//				System.out.println("賦值給ADMIN");
				admin.setAdminID(rs.getInt("adminID"));
				admin.setAdminAcco(rs.getString("adminAcco"));
				admin.setAdminPass(rs.getString("adminPass"));
				admin.setCreatedTime(rs.getTimestamp("createdTime"));
				admin.setAdminAuthority(rs.getBoolean("adminAuthority"));
			}
			System.out.println(admin.getAdminPass());
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

	@Override
	public void userAddFriend(Connection conn, PreparedStatement ps, String authorName, Integer custID) {
		
		
		
		
	}
	
	
	
}

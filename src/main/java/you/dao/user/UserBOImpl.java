package you.dao.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONArray;

import com.mysql.cj.xdevapi.JsonArray;

<<<<<<< HEAD
import you.conn.BaseConn;
import you.conn.JRedis;
=======
import basicutil.JRedis;
import basicutil.Util;
>>>>>>> LizBranch
import you.filedao.FileWorkDaoImpl;


public class UserBOImpl implements UserBO{
	private UserDaoImpl udl;
	public UserBOImpl()
	{
		udl=new UserDaoImpl();
	}
	
	public JSONArray getUsersToJSON(Connection conn,PreparedStatement ps,String userName)
	{
		JSONArray usJsonArr=new JSONArray();
		System.out.println(usJsonArr);
		ResultSet rs=udl.selectUsers(conn,ps,userName);
		
		if(rs!=null)
		{
			usJsonArr=FileWorkDaoImpl.getUssToJsArr(rs);
		}else {
			Map<String,String> mp=new HashMap<String,String>();
			mp.put("empty","This Customer List is Empty!");
			usJsonArr.put(mp);
		}
<<<<<<< HEAD
		BaseConn.closeConnection(conn, ps);
=======
		Util.closeConnection(conn, ps);
>>>>>>> LizBranch
		return usJsonArr;
	}

	@Override
	public Integer addClickBO(Connection conn, PreparedStatement ps, Integer idCustomer, Integer diaryID) {
		// TODO Auto-generated method stub
		Integer likeNum=0;
		udl.insDiaryLike(conn, ps, idCustomer, diaryID);
		likeNum=udl.selDiaryLike(conn, ps, diaryID);
<<<<<<< HEAD
		BaseConn.closeConnection(conn, ps);
=======
		Util.closeConnection(conn, ps);
>>>>>>> LizBranch
		return likeNum;
	}

	@Override
	public Integer delClickBO(Connection conn, PreparedStatement ps, Integer diaryLike,Integer diaryID) {
		// TODO Auto-generated method stub
		Integer likeNum=0;
		udl.delDiaryLike(conn, ps, diaryLike, diaryID);
		likeNum=udl.selDiaryLike(conn, ps, diaryID);
<<<<<<< HEAD
		BaseConn.closeConnection(conn, ps);
=======
		Util.closeConnection(conn, ps);
>>>>>>> LizBranch
		return likeNum;
	}

	@Override
	public Integer addOrDelClickBO(Integer idCustomer, Integer diaryID) {
		Integer likeNum=JRedis.clickLike(diaryID, idCustomer);
		
		return likeNum;
	}
}

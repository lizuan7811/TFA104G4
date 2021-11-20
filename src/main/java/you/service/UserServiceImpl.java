package you.service;

import java.sql.Connection;
import java.sql.PreparedStatement;

import org.json.JSONArray;

import basicutil.Util;
import you.dao.user.UserBO;
import you.dao.user.UserBOImpl;
import you.dao.user.UserDao;
import you.dao.user.UserDaoImpl;


public class UserServiceImpl implements UserService{
	private UserDao usd;
	private UserBO usbo;
	public UserServiceImpl()
	{
		usd=new UserDaoImpl();
		usbo=new UserBOImpl();
	}
	
	public void deleteUser(Integer usPos) {
		Connection conn=Util.getConnection();
		PreparedStatement ps=null;
		usd.deleteUser(conn, ps, usPos);
	}

	public void alterUser(Integer usPos,Object...paras) {
		Connection conn=Util.getConnection();
		PreparedStatement ps=null;
		usd.alterUser(conn, ps, usPos,paras);
	}

	public JSONArray selectUser(String username) {
		Connection conn=Util.getConnection();
		PreparedStatement ps=null;
		JSONArray userJsonArr=usbo.getUsersToJSON(conn, ps, username);
		return userJsonArr;
	}
//	如果like數量增加或減少，就要執行到select count(*) from liketable更新讚數
//	回傳SELECT * FROM DiaryLike;
	@Override
	public Integer addOrDelLikeService(Integer diaryID,Integer idCustomer) {
		// TODO Auto-generated method stub
		Integer likeNum=0;
		likeNum=usbo.addOrDelClickBO(idCustomer,diaryID);
		return likeNum;
	}
//	@Override
//	public Integer delClickService(Integer diaryLikeID,Integer diaryID) {
//		// TODO Auto-generated method stub
//		Connection conn=BaseConn.getConnection();
//		PreparedStatement ps=null;
//		Integer likeNum=0;
//		likeNum=usbo.delClickBO(conn,ps,diaryLikeID,diaryID);
//		return likeNum;
//	}
//	public int selClickService()
//	{
//		
//		return 0;
//	}
	

}

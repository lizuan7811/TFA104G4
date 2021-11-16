package you.service;

import java.sql.Connection;
import java.sql.PreparedStatement;

import org.json.JSONArray;

import you.conn.BaseConn;
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
		Connection conn=BaseConn.getConnection();
		PreparedStatement ps=null;
		usd.deleteUser(conn, ps, usPos);
	}

	public void alterUser(Integer usPos,Object...paras) {
		Connection conn=BaseConn.getConnection();
		PreparedStatement ps=null;
		usd.alterUser(conn, ps, usPos,paras);
	}

	public JSONArray selectUser(String username) {
		Connection conn=BaseConn.getConnection();
		PreparedStatement ps=null;
		JSONArray userJsonArr=usbo.getUsersToJSON(conn, ps, username);
		return userJsonArr;
	}

}

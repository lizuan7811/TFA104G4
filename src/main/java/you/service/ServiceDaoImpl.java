package you.service;

import java.sql.Connection;
import java.sql.PreparedStatement;

import basicutil.Util;
import you.dao.user.UserDao;
import you.dao.user.UserDaoImpl;
import you.pojo.AdminVO;
import you.pojo.UserVO;

public class ServiceDaoImpl implements ServiceDao{
	private UserDao userDao;
	public ServiceDaoImpl()
	{
		userDao=new UserDaoImpl();
	}
	
	public AdminVO login(String username,String password)
	{
		Connection conn=Util.getConnection();
		PreparedStatement ps=null;
		AdminVO admin=userDao.adminLogin(conn, ps,username, password);
		if(admin!=null)
		{
			return admin;
		}
		else
		{
			return null;
		}
	}
	

}

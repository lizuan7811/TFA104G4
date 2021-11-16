package you.dao.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.text.AbstractDocument.Content;

import you.basicdao.BaseDao;
import you.basicdao.BaseDaoImpl;
import you.contents.FinalStaticFile;


public class UserDaoImpl implements UserDao{
	private BaseDao bdi;
	public UserDaoImpl()
	{
		bdi=new BaseDaoImpl();		
	}

	public void deleteUser(Connection conn, PreparedStatement ps, Integer usPos) {
		int tempN=bdi.deleteData(conn, ps,FinalStaticFile.SGUSER_DELETE,usPos.toString());
	}

	public void alterUser(Connection conn, PreparedStatement ps, Integer usPos, Object... paras) {
		int tempN=bdi.updateData(conn, ps, FinalStaticFile.USER_UPDATE,usPos.toString());
	}

	public ResultSet selectUsers(Connection conn, PreparedStatement ps, Integer usPos) {
		ResultSet rs=bdi.selectData(conn, ps, FinalStaticFile.USER_SELECT,usPos.toString());
		return rs;
	}

}

package you.test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.PreparedStatement;

<<<<<<< HEAD
import you.basicdao.BaseDaoImpl;
import you.conn.BaseConn;
=======
import basicutil.Util;
import you.basicdao.BaseDaoImpl;
>>>>>>> LizBranch
import you.contents.FinalStaticFile;


public class Test {
	public static void main(String[] args)
	{
<<<<<<< HEAD
		Connection conn=BaseConn.getConnection();
=======
		Connection conn=Util.getConnection();
>>>>>>> LizBranch
		PreparedStatement ps=null;
		BaseDaoImpl bdi=new BaseDaoImpl();
		bdi.updateData(conn, ps, FinalStaticFile.ADMIN_UPDATE);
//		bdi.deleteData(conn, ps, FinalStaticFile.ADMIN_DELETE,0);
//		bdi.selectData(conn, ps, FinalStaticFile.ADMIN_SELECT,"");
//		bdi.insertData(conn, ps, FinalStaticFile.ADMIN_INSERT);
		
		
	}
}
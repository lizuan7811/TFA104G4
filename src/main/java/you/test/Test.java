package you.test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.PreparedStatement;

import basicutil.Util;
import you.basicdao.BaseDaoImpl;
import you.contents.FinalStaticFile;


public class Test {
	public static void main(String[] args)
	{
		Connection conn=Util.getConnection();
		PreparedStatement ps=null;
		BaseDaoImpl bdi=new BaseDaoImpl();
		bdi.updateData(conn, ps, FinalStaticFile.ADMIN_UPDATE);
//		bdi.deleteData(conn, ps, FinalStaticFile.ADMIN_DELETE,0);
//		bdi.selectData(conn, ps, FinalStaticFile.ADMIN_SELECT,"");
//		bdi.insertData(conn, ps, FinalStaticFile.ADMIN_INSERT);
		
		
	}
}
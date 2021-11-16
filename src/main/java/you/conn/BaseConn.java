package you.conn;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

import org.apache.tomcat.util.file.ConfigurationSource.Resource;

import you.filedao.FileInOutDao;



public class BaseConn {
	private static String driver;
	private static String username;
	private static String password;
	private static String url;
	static {
		InputStream is=null;
//		String path=BaseConn.class.getClassLoader().getResource("resource/jdbc.properties").getPath();
//		System.out.println(path);
//		resource資料夾中的檔案，如果沒有放在build資料夾中，讀取的時候會產生錯誤。
		try {
//			System.out.println(BaseConn.class.getResource("../../../").getPath()+"lib/jdbc.properties");
			is=FileInOutDao.getInputStr(new File(BaseConn.class.getResource("../../../").getPath()+"lib/jdbc.properties"));
			System.out.println("JDBC註冊檔讀取正確!\t"+is);
			Properties pros=new Properties();
			pros.load(is);
			driver=pros.getProperty("driver");
			username=pros.getProperty("username");
			password=pros.getProperty("password");
			url=pros.getProperty("url");
			Class.forName(driver);
		} catch (IOException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			if(is!=null)
			{
				try {
					is.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	public static Connection getConnection() 
	{
		Connection conn=null;
		try {
			conn=DriverManager.getConnection(url,username,password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conn;
	}

	public static void closeConnection(Connection conn,PreparedStatement ps)
	{
		if(ps!=null)
		{
			try {
				ps.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		if(conn!=null)
		{
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
	
}

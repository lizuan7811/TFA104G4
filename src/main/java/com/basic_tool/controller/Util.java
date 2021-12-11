package com.basic_tool.controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

public class Util {
	private static String driver;
	private static String username;
	private static String password;
	private static String url;
	private Util()
	{

	}
	static {
		InputStream is=null;
//		String path=BaseConn.class.getClassLoader().getResource("resource/jdbc.properties").getPath();
//		System.out.println(path);
//		resource資料夾中的檔案，如果沒有放在build資料夾中，讀取的時候會產生錯誤。
		try {
			System.out.println(new File("src/main/webapp/WEB-INF/lib/jdbc.properties").exists());
//			System.out.println(Util.class.getResource("../../../../")+"lib/jdbc.properties");
//			is=FileInOutDao.getInputStr(new File("src/main/webapp/WEB-INF/lib/jdbc.properties"));
			is=FileInOutDao.getInputStr(new File(Util.class.getResource("../../../../").getPath()+"lib/jdbc.properties"));

			System.out.println(is==null);
			System.out.println("JDBC註冊檔讀取正確!\t"+is);
			Properties pros=new Properties();
			pros.load(is);
			driver=pros.getProperty("driver");
			System.out.println(driver);
			username=pros.getProperty("username");
			System.out.println(username);
			password=pros.getProperty("password");
			System.out.println(username);
			url=pros.getProperty("url");
			System.out.println(url);

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
	public static void main(String[] args)
	{

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

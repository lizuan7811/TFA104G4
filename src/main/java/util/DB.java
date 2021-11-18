package util;

public class DB {
//	JDBC Driver
	public static final String DRIVER = "com.mysql.cj.jdbc.Driver";
//	資料庫連線至MySQL中的schema >> group4_db
	public static final String URL = "jdbc:mysql://localhost:3306/group4_db?serverTimezone=Asia/Taipei"; 
//	資料庫使用者名稱:
	public static final String USER = "root";
//	資料庫使用者密碼:
	public static final String PASSWORD = "password";
}

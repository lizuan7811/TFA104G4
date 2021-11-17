package ga.cf_eat_healthy;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Cf_TestDAOimpl implements Cf_TestDAO{
	public static final String DRIVER = "com.mysql.cj.jdbc.Driver";
	public static final String theURL = "jdbc:mysql://localhost:3306/group4_db?serverTimezone=Asia/Taipei"; 
	
	private static final String INSERT_TEST = "INSERT INTO CF_TEST (TESTNO, TNAME, PHONE) VALUES (?, ?, ?)";

	
	static {
		try {
			Class.forName(DRIVER);
		} catch (ClassNotFoundException ce) {
			ce.printStackTrace();
		}
	}
	
		

	@Override
	public void insert(Cf_TestVO cftest_VO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = DriverManager.getConnection(theURL, "root", "password");
			pstmt = con.prepareStatement(INSERT_TEST);
			
			pstmt.setInt(1, cftest_VO.getTestno());
			pstmt.setString(2, cftest_VO.getTname());
			pstmt.setString(3, cftest_VO.getPhone());
					
			pstmt.executeUpdate();
			
		}catch(SQLException se) {
			se.printStackTrace();
			
		}finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace();
				}
			}
			
			if (con != null) {
				try {
					con.close();
				} catch (SQLException se) {
					se.printStackTrace();
				}
			}
		}
		
	}
}

package po;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;



public class FriendChatDAOImpl implements FriendChatDAO{
	public static final String INSERT_STMT = "INSERT INTO FRIENDCHAT(FRIENDCHATID, CUSTID, MYFRIENDID, CHATTEXT, CREATEDTIME) VALUES (?, ?, ?, ?, ?)";
	public static final String UPDATE_STMT = "UPDATE FRIENDCHAT SET CUSTID = ?, MYFRIENDID = ?, CHATTEXT = ?, CREATEDTIME = ? WHERE FRIENDCHATID = ?";
	public static final String DELETE_STMT = "DELETE FROM FRIENDCHAT WHERE FRIENDCHATID = ?";
	public static final String FIND_BY_PK = "SELECT * FROM FRIENDCHAT WHERE FRIENDCHATID = ?";
	public static final String GET_ALL = "SELECT * FROM FRIENDCHAT";
	
	static {
		try {
			Class.forName(Util.DRIVER);
		} catch (ClassNotFoundException ce) {
			ce.printStackTrace();
		}
	}
	@Override
	public void insert(FriendChatVO friendChatVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, friendChatVO.getFriendChatID());
			pstmt.setInt(2, friendChatVO.getCustID());
			pstmt.setInt(3, friendChatVO.getMyFriendID());
			pstmt.setString(4, friendChatVO.getChatText());
			pstmt.setDate(5, friendChatVO.getCreatedTime());


			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (SQLException se) {
			se.printStackTrace();
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
	}


	@Override
	public void update(FriendChatVO friendChatVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(UPDATE_STMT);

			
			pstmt.setInt(1, friendChatVO.getCustID());
			pstmt.setInt(2, friendChatVO.getMyFriendID());
			pstmt.setString(3, friendChatVO.getChatText());
			pstmt.setDate(4, friendChatVO.getCreatedTime());
			pstmt.setInt(5, friendChatVO.getFriendChatID());
			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (SQLException se) {
			se.printStackTrace();
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		
		
	}

	@Override
	public void delete(Integer friendChatID) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(DELETE_STMT);

			pstmt.setInt(1, friendChatID);
			
			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (SQLException se) {
			se.printStackTrace();
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		
	}

	@Override
	public FriendChatVO findByPK(Integer friendChatID) {
		FriendChatVO fri = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(FIND_BY_PK);
			pstmt.setInt(1, friendChatID);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				fri = new FriendChatVO();
				fri.setFriendChatID(rs.getInt("FRIENDCHATID"));
				fri.setCustID(rs.getInt("CUSTID"));
				fri.setMyFriendID(rs.getInt("MYFRIENDID"));
				fri.setChatText(rs.getString("CHATTEXT"));
				fri.setCreatedTime(rs.getDate("CREATEDTIME"));
			}

		} catch (SQLException se) {
			se.printStackTrace();
			// Clean up JDBC resources
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}

		return fri;
	}
	@Override
	public List<FriendChatVO> getAll() {
		List<FriendChatVO> friList = new ArrayList<>();
		FriendChatVO fri = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(GET_ALL);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				fri = new FriendChatVO();
				fri.setFriendChatID(rs.getInt("FRIENDCHATID"));
				fri.setCustID(rs.getInt("CUSTID"));
				fri.setMyFriendID(rs.getInt("MYFRIENDID"));
				fri.setChatText(rs.getString("CHATTEXT"));
				fri.setCreatedTime(rs.getDate("CREATEDTIME"));
				friList.add(fri);
			}

		} catch (SQLException se) {
			se.printStackTrace();
			// Clean up JDBC resources
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return friList;
	}

}

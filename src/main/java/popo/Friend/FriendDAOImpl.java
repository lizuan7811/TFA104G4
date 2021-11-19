package po;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FriendDAOImpl implements FriendDAO {
	public static final String INSERT_STMT = "INSERT INTO FRIEND(FRIENDCHATID, CUSTID, MYFRIENDID, FRIENDSTATUSNUM, STATUSUPDATE) VALUES (?, ?, ?, ?, ?)";
	public static final String UPDATE_STMT = "UPDATE FRIEND SET CUSTID = ?, MYFRIENDID = ?, FRIENDSTATUSNUM = ?, STATUSUPDATE = ? WHERE FRIENDCHATID = ?";
	public static final String DELETE_STMT = "DELETE FROM FRIEND WHERE FRIENDCHATID = ?";
	public static final String FIND_BY_PK = "SELECT * FROM FRIEND WHERE FRIENDCHATID = ?";
	public static final String GET_ALL = "SELECT * FROM FRIEND";
	
	static {
		try {
			Class.forName(Util.DRIVER);
		} catch (ClassNotFoundException ce) {
			ce.printStackTrace();
		}
	}
	@Override
	public void insert(FriendVO friendVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, friendVO.getFriendChatID());
			pstmt.setInt(2, friendVO.getCustID());
			pstmt.setInt(3, friendVO.getMyFriendID());
			pstmt.setInt(4, friendVO.getFriendStatusNum());
			pstmt.setDate(5, friendVO.getStatusUpdate());


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
	public void update(FriendVO friendVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(UPDATE_STMT);

			pstmt.setInt(1, friendVO.getCustID());
			pstmt.setInt(2, friendVO.getMyFriendID());
			pstmt.setInt(3, friendVO.getFriendStatusNum());
			pstmt.setDate(4, friendVO.getStatusUpdate());
			pstmt.setInt(5, friendVO.getFriendChatID());

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
	public FriendVO findByPK(Integer friendChatID) {
		FriendVO frie = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(FIND_BY_PK);
			pstmt.setInt(1, friendChatID);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				frie = new FriendVO();
				frie.setFriendChatID(rs.getInt("FRIENDCHATID"));
				frie.setCustID(rs.getInt("CUSTID"));
				frie.setMyFriendID(rs.getInt("MYFRIENDID"));
				frie.setFriendStatusNum(rs.getInt("FRIENDSTATUSNUM"));
				frie.setStatusUpdate(rs.getDate("STATUSUPDATE"));
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

		return frie;
	}

	@Override
	public List<FriendVO> getAll() {
		List<FriendVO> frieList = new ArrayList<>();
		FriendVO frie = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(GET_ALL);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				frie = new FriendVO();
				frie.setFriendChatID(rs.getInt("FRIENDCHATID"));
				frie.setCustID(rs.getInt("CUSTID"));
				frie.setMyFriendID(rs.getInt("MYFRIENDID"));
				frie.setFriendStatusNum(rs.getInt("FRIENDSTATUSNUM"));
				frie.setStatusUpdate(rs.getDate("STATUSUPDATE"));
				frieList.add(frie);
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
		return frieList;
	}

}

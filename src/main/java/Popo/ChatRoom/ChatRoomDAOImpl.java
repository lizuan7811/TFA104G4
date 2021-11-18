package po;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class ChatRoomDAOImpl implements ChatRoomDAO{
	private static final String INSERT_STMT = "INSERT INTO CHATROOM(MESGID, CUSTID, CUSTMESG, CREATTIME, MESSAGE) VALUES (?, ?, ?, ?, ?)";
	private static final String UPDATE_STMT = "UPDATE CHATROOM SET  CUSTID = ?, CUSTMESG = ?, CREATTIME = ?, MESSAGE = ? WHERE MESGID = ?";
	private static final String DELETE_STMT = "DELETE FROM CHATROOM WHERE MESGID = ?";
	private static final String FIND_BY_PK = "SELECT * FROM CHATROOM WHERE MESGID = ?";
	private static final String GET_ALL = "SELECT * FROM CHATROOM";
	
	static {
		try {
			Class.forName(Util.DRIVER);
		} catch (ClassNotFoundException ce) {
			ce.printStackTrace();
		}
	}
	@Override
	public void add(ChatRoomVO chatRoomVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, chatRoomVO.getmesgID());
			pstmt.setInt(2, chatRoomVO.getcustID());
			pstmt.setBoolean(3, chatRoomVO.getCustMesg());
			pstmt.setDate(4, chatRoomVO.getCreatTime());
			pstmt.setString(5, chatRoomVO.getmessage());

			pstmt.executeUpdate();
			
			
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
	public void update(ChatRoomVO chatRoomVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(UPDATE_STMT);
			pstmt.setInt(1, chatRoomVO.getcustID());
			pstmt.setBoolean(2, chatRoomVO.getCustMesg());
			pstmt.setDate(3, chatRoomVO.getCreatTime());
			pstmt.setString(4, chatRoomVO.getmessage());
			pstmt.setInt(5, chatRoomVO.getmesgID());
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
	public void delete(int mesgID) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(DELETE_STMT);

			pstmt.setInt(1, mesgID);
			
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
	public ChatRoomVO findByPK(int mesgID) {
		ChatRoomVO chat = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(FIND_BY_PK);
			pstmt.setInt(1, mesgID);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				chat = new ChatRoomVO();
				chat.setmesgID(rs.getInt("MESGID"));
				chat.setcustID(rs.getInt("CUSTID"));
				chat.setBoolean(rs.getBoolean("CUSTMESG"));
				chat.setcreatTime(rs.getDate("CREATTIME"));
				chat.setmessage(rs.getString("Message"));
			
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

		return chat;
	}

	@Override
	public List<ChatRoomVO> getAll() {
		List<ChatRoomVO> chatList = new ArrayList<>();
		ChatRoomVO chat = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(GET_ALL);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				chat = new ChatRoomVO();
				chat.setmesgID(rs.getInt("MESGID"));
				chat.setcustID(rs.getInt("CUSTID"));
				chat.setBoolean(rs.getBoolean("CUSTMESG"));
				chat.setcreatTime(rs.getDate("CREATTIME"));
				chat.setmessage(rs.getString("Message"));
				chatList.add(chat);
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
		return chatList;
	}

}

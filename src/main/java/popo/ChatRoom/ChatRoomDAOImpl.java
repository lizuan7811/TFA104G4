package popo.ChatRoom;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import basicutil.Util;


public class ChatRoomDAOImpl implements ChatRoomDAO{
	public static final String INSERT_STMT = "INSERT INTO CHATROOM(MESGID, CUSTID, CUSTMESG, CREATEDTIME, MESSAGE) VALUES (?, ?, ?, ?, ?)";
	public static final String UPDATE_STMT = "UPDATE CHATROOM SET  CUSTID = ?, CUSTMESG = ?, CREATEDTIME = ?, MESSAGE = ? WHERE MESGID = ?";
	public static final String DELETE_STMT = "DELETE FROM CHATROOM WHERE MESGID = ?";
	public static final String FIND_BY_PK = "SELECT * FROM CHATROOM WHERE MESGID = ?";
	public static final String GET_ALL = "SELECT * FROM CHATROOM";
	
	@Override
	public void insert(ChatRoomVO chatRoomVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			
			con = Util.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, chatRoomVO.getMesgID());
			pstmt.setInt(2, chatRoomVO.getCustID());
			pstmt.setBoolean(3, chatRoomVO.getCustMesg());
			pstmt.setDate(4, chatRoomVO.getCreatedTime());
			pstmt.setString(5, chatRoomVO.getMessage());

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

			con = Util.getConnection();
			pstmt = con.prepareStatement(UPDATE_STMT);
			pstmt.setInt(1, chatRoomVO.getCustID());
			pstmt.setBoolean(2, chatRoomVO.getCustMesg());
			pstmt.setDate(3, chatRoomVO.getCreatedTime());
			pstmt.setString(4, chatRoomVO.getMessage());
			pstmt.setInt(5, chatRoomVO.getMesgID());
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
	public void delete(Integer mesgID) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = Util.getConnection();
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

			con = Util.getConnection();
			pstmt = con.prepareStatement(FIND_BY_PK);
			pstmt.setInt(1, mesgID);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				chat = new ChatRoomVO();
				chat.setMesgID(rs.getInt("MESGID"));
				chat.setCustID(rs.getInt("CUSTID"));
				chat.setBoolean(rs.getBoolean("CUSTMESG"));
				chat.setCreatedTime(rs.getDate("CREATEDTIME"));
				chat.setMessage(rs.getString("Message"));
			
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

			con = Util.getConnection();
			pstmt = con.prepareStatement(GET_ALL);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				chat = new ChatRoomVO();
				chat.setMesgID(rs.getInt("MESGID"));
				chat.setCustID(rs.getInt("CUSTID"));
				chat.setBoolean(rs.getBoolean("CUSTMESG"));
				chat.setCreatedTime(rs.getDate("CREATEDTIME"));
				chat.setMessage(rs.getString("Message"));
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

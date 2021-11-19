package cf.comment;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import util.DB;


public class CommentDAOimpl implements CommentDAO {

	private static final String INSERT_COMMENT = "INSERT INTO Comment (CommentID, DiaryID, CustID, "
			+ "CreatedTime, CommentText, CommentStatus) VALUES (?, ?, ?, ?, ?, ?)";
	private static final String UPDATE_COMMENT = "UPDATE Comment SET DiaryID = ?, CustID = ?, "
			+ "CreatedTime = ?, CommentText = ?, CommentStatus = ? where CommentID = ?";
	private static final String DELETE_COMMENT = "DELETE FROM Comment where CommentID = ?";
	private static final String FIND_BY_PK = "SELECT * FROM Comment WHERE CommentID = ?";
	private static final String GET_ALL = "SELECT * FROM Comment";

	static {
		try {
			Class.forName(DB.DRIVER);
		} catch (ClassNotFoundException ce) {
			ce.printStackTrace();
		}
	}

	@Override
	public void insert(CommentVO commentVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = DriverManager.getConnection(DB.URL, DB.USER, DB.PASSWORD);
			pstmt = con.prepareStatement(INSERT_COMMENT);
			
			pstmt.setInt(1, commentVO.getCommentID());
			pstmt.setInt(2, commentVO.getDiaryID());
			pstmt.setInt(3, commentVO.getCustID());
			pstmt.setTimestamp(4, commentVO.getCreatedTime());
			pstmt.setString(5, commentVO.getCommentText());
			pstmt.setBoolean(6, commentVO.getCommentStatus());
			
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

	@Override
	public void update(CommentVO commentVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = DriverManager.getConnection(DB.URL, DB.USER, DB.PASSWORD);
			pstmt = con.prepareStatement(UPDATE_COMMENT);

			pstmt.setInt(1, commentVO.getDiaryID());
			pstmt.setInt(2, commentVO.getCustID());
			pstmt.setTimestamp(3, commentVO.getCreatedTime());
			pstmt.setString(4, commentVO.getCommentText());
			pstmt.setBoolean(5, commentVO.getCommentStatus());
			pstmt.setInt(6, commentVO.getCommentID());			
			
			pstmt.executeUpdate();

		} catch (SQLException se) {
			se.printStackTrace();
			
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
	public void delete(Integer commentID) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = DriverManager.getConnection(DB.URL, DB.USER, DB.PASSWORD);
			pstmt = con.prepareStatement(DELETE_COMMENT);

			pstmt.setInt(1, commentID);
			
			pstmt.executeUpdate();

		} catch (SQLException se) {
			se.printStackTrace();

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
	public CommentVO findByPrimaryKey(Integer commentID) {
		CommentVO comment = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = DriverManager.getConnection(DB.URL, DB.USER, DB.PASSWORD);
			pstmt = con.prepareStatement(FIND_BY_PK);
			pstmt.setInt(1, commentID);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				comment = new CommentVO();
				comment.setCommentID(rs.getInt("CommentID"));
				comment.setDiaryID(rs.getInt("DiaryID"));
				comment.setCustID(rs.getInt("CustID"));
				comment.setCreatedTime(rs.getTimestamp("CreatedTime"));
				comment.setCommentText(rs.getString("CommentText"));
				comment.setCommentStatus(rs.getBoolean("CommentStatus"));
			}

		} catch (SQLException se) {
			se.printStackTrace();
			
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

		return comment;
	}

	@Override
	public List<CommentVO> getAll() {
		List<CommentVO> commentList = new ArrayList<>();
		CommentVO comment = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = DriverManager.getConnection(DB.URL, DB.USER, DB.PASSWORD);
			pstmt = con.prepareStatement(GET_ALL);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				comment = new CommentVO();
				comment.setCommentID(rs.getInt("CommentID"));
				comment.setDiaryID(rs.getInt("DiaryID"));
				comment.setCustID(rs.getInt("CustID"));
				comment.setCreatedTime(rs.getTimestamp("CreatedTime"));
				comment.setCommentText(rs.getString("CommentText"));
				comment.setCommentStatus(rs.getBoolean("CommentStatus"));
				commentList.add(comment);
			}

		} catch (SQLException se) {
			se.printStackTrace();
			
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
		return commentList;
	}

}

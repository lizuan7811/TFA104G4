package com.comment.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import com.basic_tool.controller.Util;



public class CommentDAOimpl implements CommentDAO {

	private static final String INSERT_COMMENT = "INSERT INTO Comment (DiaryID, CustNickName, "
			+ "CommentText) VALUES (?, ?, ?)";
	private static final String UPDATE_COMMENT = "UPDATE Comment SET DiaryID = ?, CustID = ?, "
			+ "CreatedTime = ?, CommentText = ?, CommentStatus = ? where CommentID = ?";
	private static final String DELETE_COMMENT = "DELETE FROM Comment where CommentID = ?";
	private static final String FIND_BY_PK = "SELECT * FROM Comment WHERE CommentID = ?";
	private static final String GET_ALL = "SELECT * FROM Comment";
	private static final String SEARCH_COMMENT="SELECT * FROM group4_db.comment where diaryid = ?"; 
	
	private static final String ONE_DIARY_COMMENT ="select * from comment where diaryID = ? ;";


	@Override
	public void insert(CommentVO commentVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = Util.getConnection();
			pstmt = con.prepareStatement(INSERT_COMMENT);
			
//			pstmt.setInt(1, commentVO.getCommentID());
			pstmt.setInt(1, commentVO.getDiaryID());
			pstmt.setString(2, commentVO.getNickName());
//			pstmt.setTimestamp(3, commentVO.getCreatedTime());
			pstmt.setString(3, commentVO.getCommentText());
//			pstmt.setBoolean(5, commentVO.getCommentStatus());
			
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

			con = Util.getConnection();
			pstmt = con.prepareStatement(UPDATE_COMMENT);

			pstmt.setInt(1, commentVO.getDiaryID());
			pstmt.setString(2, commentVO.getNickName());
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

			con = Util.getConnection();
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

			con = Util.getConnection();
			pstmt = con.prepareStatement(FIND_BY_PK);
			pstmt.setInt(1, commentID);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				comment = new CommentVO();
				comment.setCommentID(rs.getInt("CommentID"));
				comment.setDiaryID(rs.getInt("DiaryID"));
				comment.setNickName(rs.getString("NickName"));
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
	public JSONArray searchComment(Integer diaryID) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		JSONObject obj=new JSONObject();
		JSONArray array = new JSONArray();
		
		try {

			con = Util.getConnection();
			pstmt = con.prepareStatement(SEARCH_COMMENT);
			pstmt.setInt(1, diaryID);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				obj.clear();
				obj.put("DiaryID", rs.getInt("DiaryID"));
				obj.put("CommentID", rs.getInt("CommentID"));
				obj.put("CustNickName", rs.getString("CustNickName"));	
				SimpleDateFormat sd = new SimpleDateFormat("yyyy.MM.dd hh:mm");
				String ss=sd.format(rs.getTimestamp("CreatedTime"));
				obj.put("CreatedTime", ss);
				obj.put("CommentText", rs.getString("CommentText"));

				array.put(obj.toMap());
			}
			System.out.println(array);
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
		return array;
	}


	@Override
	public List<CommentVO> getAll() {
		List<CommentVO> commentList = new ArrayList<>();
		CommentVO comment = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = Util.getConnection();
			pstmt = con.prepareStatement(GET_ALL);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				comment = new CommentVO();
				comment.setCommentID(rs.getInt("CommentID"));
				comment.setDiaryID(rs.getInt("DiaryID"));
				comment.setNickName(rs.getString("NickName"));
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

	public List<CommentVO> getOneDiaryComment(Integer diaryID) {
		List<CommentVO> commentList = new ArrayList<>();
		CommentVO comment = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {

			con = Util.getConnection();
			pstmt = con.prepareStatement(ONE_DIARY_COMMENT);
			pstmt.setInt(1, diaryID);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				comment = new CommentVO();
				comment.setCommentID(rs.getInt("CommentID"));
				comment.setDiaryID(rs.getInt("DiaryID"));
				comment.setNickName(rs.getString("NickName"));
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

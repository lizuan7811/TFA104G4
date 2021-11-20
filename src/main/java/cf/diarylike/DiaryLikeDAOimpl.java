package cf.diarylike;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import basicutil.Util;
import util.DB;


public class DiaryLikeDAOimpl implements DiaryLikeDAO{
	
	private static final String INSERT_LIKE = "INSERT INTO DiaryLike (DiaryID, CustID, CreatedTime) VALUES (?, ?, ?)";
	private static final String UPDATE_LIKE = "UPDATE DiaryLike SET DiaryID = ?, CustID = ?, CreatedTime = ? WHERE DiaryLikeID = ?";
	private static final String DELETE_LIKE = "DELETE FROM DiaryLike WHERE DiaryLikeID = ?";
	private static final String FIND_BY_PK = "SELECT * FROM DiaryLike WHERE DiaryLikeID = ?";
	private static final String GET_ALL = "SELECT * FROM DiaryLike";

	@Override
	public void insert(DiaryLikeVO diarylikeVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = Util.getConnection();
			pstmt = con.prepareStatement(INSERT_LIKE);
			
			pstmt.setInt(1, diarylikeVO.getDiaryid());
			pstmt.setInt(2, diarylikeVO.getCustid());
			pstmt.setTimestamp(3, diarylikeVO.getCreatedtime());
			
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
	public void update(DiaryLikeVO diarylikeVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = Util.getConnection();
			pstmt = con.prepareStatement(UPDATE_LIKE);

			pstmt.setInt(1, diarylikeVO.getDiaryid());
			pstmt.setInt(2, diarylikeVO.getCustid());
			pstmt.setTimestamp(3, diarylikeVO.getCreatedtime());
			pstmt.setInt(4, diarylikeVO.getDiaryLikeid());
			
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
	public void delete(Integer diarylikeVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = Util.getConnection();
			pstmt = con.prepareStatement(DELETE_LIKE);

			pstmt.setInt(1, diarylikeVO);
			
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
	public DiaryLikeVO findByPrimaryKey(Integer diarylikeVO) {
		DiaryLikeVO like = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = Util.getConnection();
			pstmt = con.prepareStatement(FIND_BY_PK);
			pstmt.setInt(1, diarylikeVO);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				like = new DiaryLikeVO();
				like.setDiaryLikeid(rs.getInt("DiaryLikeID"));
				like.setDiaryid(rs.getInt("DiaryID"));
				like.setCustid(rs.getInt("CustID"));
				like.setCreatedtime(rs.getTimestamp("CreatedTime"));
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

		return like;
	}

	@Override
	public List<DiaryLikeVO> getAll() {
		List<DiaryLikeVO> likeList = new ArrayList<>();
		DiaryLikeVO like = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = Util.getConnection();
			pstmt = con.prepareStatement(GET_ALL);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				like = new DiaryLikeVO();
				like.setDiaryLikeid(rs.getInt("DiaryLikeID"));
				like.setDiaryid(rs.getInt("DiaryID"));
				like.setCustid(rs.getInt("CustID"));
				like.setCreatedtime(rs.getTimestamp("CreatedTime"));
				likeList.add(like);
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
		return likeList;
	}

}


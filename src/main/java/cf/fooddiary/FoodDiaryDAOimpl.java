package cf.fooddiary;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import basicutil.Util;
import util.DB;



public class FoodDiaryDAOimpl implements FoodDiaryDAO {
	
	private static final String INSERT_FOODDIARY = 
			"INSERT INTO FoodDiary (DiaryID, CustID, Subject, Text, CreatedTime, Photo_Video_1, Photo_Video_2, "
			+ "Photo_Video_3, Status, ForumLikeNum, DiaryStatus, DiaryType) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String UPDATE_FOODDIARY = 
			"UPDATE FoodDiary SET CustID = ?, Subject = ?, Text = ?, CreatedTime = ?, Photo_Video_1 = ?, Photo_Video_2 = ?, Photo_Video_3 = ?, Status = ?, ForumLikeNum = ?, DiaryStatus = ?, DiaryType = ? where DiaryID = ?";
	private static final String DELETE_FOODDIARY = "DELETE FROM FoodDiary where DiaryID = ?";
	private static final String FIND_BY_PK = "SELECT * FROM FoodDiary where DiaryID = ?";
	private static final String GET_ALL = "SELECT * FROM FoodDiary";
	
	@Override
	public void insert(FoodDiaryVO foodDiaryVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = Util.getConnection();
			pstmt = con.prepareStatement(INSERT_FOODDIARY);
			
			pstmt.setInt(1, foodDiaryVO.getDiaryID());
			pstmt.setInt(2, foodDiaryVO.getCustID());
			pstmt.setString(3, foodDiaryVO.getSubject());
			pstmt.setString(4, foodDiaryVO.getText());
			pstmt.setTimestamp(5, foodDiaryVO.getCreatedTime());
			pstmt.setBytes(6, foodDiaryVO.getPhoto_video_1());
			pstmt.setBytes(7, foodDiaryVO.getPhoto_video_2());
			pstmt.setBytes(8, foodDiaryVO.getPhoto_video_3());
			pstmt.setBoolean(9, foodDiaryVO.getStatus());
			pstmt.setInt(10, foodDiaryVO.getForumLikeNum());
			pstmt.setBoolean(11, foodDiaryVO.getDiaryStatus());
			pstmt.setInt(12, foodDiaryVO.getDiaryType());
			
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
	public void update(FoodDiaryVO foodDiaryVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = Util.getConnection();
			pstmt = con.prepareStatement(UPDATE_FOODDIARY);

			pstmt.setInt(1, foodDiaryVO.getCustID());
			pstmt.setString(2, foodDiaryVO.getSubject());
			pstmt.setString(3, foodDiaryVO.getText());
			pstmt.setTimestamp(4, foodDiaryVO.getCreatedTime());
			pstmt.setBytes(5, foodDiaryVO.getPhoto_video_1());
			pstmt.setBytes(6, foodDiaryVO.getPhoto_video_2());
			pstmt.setBytes(7, foodDiaryVO.getPhoto_video_3());
			pstmt.setBoolean(8, foodDiaryVO.getStatus());
			pstmt.setInt(9, foodDiaryVO.getForumLikeNum());
			pstmt.setBoolean(10, foodDiaryVO.getDiaryStatus());
			pstmt.setInt(11, foodDiaryVO.getDiaryType());
			pstmt.setInt(12, foodDiaryVO.getDiaryID());
			
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
	public void delete(Integer diaryID) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = Util.getConnection();
			pstmt = con.prepareStatement(DELETE_FOODDIARY);

			pstmt.setInt(1, diaryID);
			
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
	public FoodDiaryVO findByPrimaryKey(Integer diaryID) {
		FoodDiaryVO diary = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = Util.getConnection();
			pstmt = con.prepareStatement(FIND_BY_PK);
			pstmt.setInt(1, diaryID);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				diary = new FoodDiaryVO();
				diary.setDiaryID(rs.getInt("DiaryID"));
				diary.setCustID(rs.getInt("CustID"));
				diary.setSubject(rs.getString("Subject"));
				diary.setText(rs.getString("Text"));
				diary.setCreatedTime(rs.getTimestamp("CreatedTime"));
				diary.setPhoto_video_1(rs.getBytes("Photo_video_1"));
				diary.setPhoto_video_2(rs.getBytes("Photo_video_2"));
				diary.setPhoto_video_3(rs.getBytes("Photo_video_3"));
				diary.setStatus(rs.getBoolean("Status"));
				diary.setForumLikeNum(rs.getInt("ForumLikeNum"));
				diary.setDiaryStatus(rs.getBoolean("DiaryStatus"));
				diary.setDiaryType(rs.getInt("DiaryType"));
				
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

		return diary;
	}
	@Override
	public List<FoodDiaryVO> getAll() {
		List<FoodDiaryVO> diaryList = new ArrayList<>();
		FoodDiaryVO diary = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = Util.getConnection();
			pstmt = con.prepareStatement(GET_ALL);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				diary = new FoodDiaryVO();
				diary.setDiaryID(rs.getInt("DiaryID"));
				diary.setCustID(rs.getInt("CustID"));
				diary.setSubject(rs.getString("Subject"));
				diary.setText(rs.getString("Text"));
				diary.setCreatedTime(rs.getTimestamp("CreatedTime"));
				diary.setPhoto_video_1(rs.getBytes("Photo_video_1"));
				diary.setPhoto_video_2(rs.getBytes("Photo_video_2"));
				diary.setPhoto_video_3(rs.getBytes("Photo_video_3"));
				diary.setStatus(rs.getBoolean("Status"));
				diary.setForumLikeNum(rs.getInt("ForumLikeNum"));
				diary.setDiaryStatus(rs.getBoolean("DiaryStatus"));
				diary.setDiaryType(rs.getInt("DiaryType"));
				diaryList.add(diary);
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
		return diaryList;
	}

}

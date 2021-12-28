package com.fooddiary.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import com.basic_tool.controller.Util;


public class FoodDiaryDAOimpl implements FoodDiaryDAO {
	
	private static final String INSERT_FOODDIARY = 
			"INSERT INTO FoodDiary (CustID, Subject, Text, Photo_Video_1, Photo_Video_2, "
			+ "Photo_Video_3, Status, DiaryType, CreatedTime) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String UPDATE_FOODDIARY = 
			"UPDATE FoodDiary SET CustID = ?, Subject = ?, Text = ?, Photo_Video_1 = ?, Photo_Video_2 = ?, Photo_Video_3 = ?, Status = ?, DiaryType = ? , CreatedTime = ? where DiaryID = ?";
	private static final String DELETE_FOODDIARY = "DELETE FROM FoodDiary where DiaryID = ?";
	private static final String FIND_BY_PK = "SELECT * FROM FoodDiary where DiaryID = ?";
	private static final String GET_ALL = "SELECT diaryid , custid, subject, createdTime, diaryType, forumLikeNum, diaryStatus FROM FoodDiary";
	private static final String GET_LIST_ONE = "SELECT diaryid, subject, createdTime, status, diaryType  FROM FoodDiary where custid = ? ORDER BY createdTime DESC;";
	
	private static final String SEARCH_ALL = "SELECT diaryid , Photo_Video_1, subject FROM FoodDiary";
	private static final String SEARCH_TYPE = "SELECT diaryid , Photo_Video_1, subject FROM FoodDiary where diaryType = ? ";
	@Override
	public void insert(FoodDiaryVO foodDiaryVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = Util.getConnection();
			pstmt = con.prepareStatement(INSERT_FOODDIARY);
			
//			pstmt.setInt(1, foodDiaryVO.getDiaryID());
			pstmt.setInt(1, foodDiaryVO.getCustID());
			pstmt.setString(2, foodDiaryVO.getSubject());
			pstmt.setString(3, foodDiaryVO.getText());
			pstmt.setBytes(4, foodDiaryVO.getPhoto_video_1());
			pstmt.setBytes(5, foodDiaryVO.getPhoto_video_2());
			pstmt.setBytes(6, foodDiaryVO.getPhoto_video_3());
			pstmt.setBoolean(7, foodDiaryVO.getStatus());
//			pstmt.setInt(8,1);
//			pstmt.setBoolean(9,false);
			pstmt.setInt(8,foodDiaryVO.getDiaryType());
			pstmt.setTimestamp(9, foodDiaryVO.getCreatedTime());
			
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

//			pstmt.setInt(1, foodDiaryVO.getDiaryID());
			pstmt.setInt(1, foodDiaryVO.getCustID());
			pstmt.setString(2, foodDiaryVO.getSubject());
			pstmt.setString(3, foodDiaryVO.getText());
			pstmt.setBytes(4, foodDiaryVO.getPhoto_video_1());
			pstmt.setBytes(5, foodDiaryVO.getPhoto_video_2());
			pstmt.setBytes(6, foodDiaryVO.getPhoto_video_3());
			pstmt.setBoolean(7, foodDiaryVO.getStatus());
//			pstmt.setInt(8,1);
//			pstmt.setBoolean(9,false);
			pstmt.setInt(8,foodDiaryVO.getDiaryType());
			pstmt.setTimestamp(9, foodDiaryVO.getCreatedTime());
			
			pstmt.setInt(10, foodDiaryVO.getDiaryID());
			
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
				diary.setCreatedTime(rs.getTimestamp("CreatedTime"));
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
	
	@Override
	public List<FoodDiaryVO> get_LIST_ONE(Integer custID) {
		List<FoodDiaryVO> list = new ArrayList<>();
		FoodDiaryVO diary = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = Util.getConnection();
			pstmt = con.prepareStatement(GET_LIST_ONE);
			pstmt.setInt(1, custID);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				diary = new FoodDiaryVO();
				diary.setDiaryID(rs.getInt("DiaryID"));
//				diary.setCustID(rs.getInt("CustID"));
				diary.setSubject(rs.getString("Subject"));
//				diary.setText(rs.getString("Text"));
				diary.setCreatedTime(rs.getTimestamp("CreatedTime"));
//				diary.setPhoto_video_1(rs.getBytes("Photo_video_1"));
//				diary.setPhoto_video_2(rs.getBytes("Photo_video_2"));
//				diary.setPhoto_video_3(rs.getBytes("Photo_video_3"));
				diary.setStatus(rs.getBoolean("Status"));
//				diary.setForumLikeNum(rs.getInt("ForumLikeNum"));
//				diary.setDiaryStatus(rs.getBoolean("DiaryStatus"));
				diary.setDiaryType(rs.getInt("DiaryType"));
				list.add(diary);
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
		return list;
	}
	
	@Override
	public JSONArray searchAll() {
		List<FoodDiaryVO> diaryList = new ArrayList<>();
		FoodDiaryVO diary = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		JSONObject jObj=new JSONObject();
		JSONArray array = new JSONArray();
		try {

			con = Util.getConnection();
			pstmt = con.prepareStatement(SEARCH_ALL);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				jObj.clear();
				Base64.Encoder beCoder=Base64.getEncoder();
				String beStr=beCoder.encodeToString(rs.getBytes("Photo_Video_1"));
				jObj.put("DiaryID",rs.getInt("DiaryID"));
				jObj.put("Photo_Video_1", beStr);
				jObj.put("Subject", rs.getString("Subject"));
//				System.out.println(jObj);
				array.put(jObj.toMap());
			}
//			System.out.println(array);
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
	public JSONArray searchType(Integer diaryType) {
		List<FoodDiaryVO> diaryList = new ArrayList<>();
		FoodDiaryVO diary = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		JSONObject jObj=new JSONObject();
		JSONArray array = new JSONArray();
		try {

			con = Util.getConnection();
			pstmt = con.prepareStatement(SEARCH_TYPE);
//			System.out.println(diaryType);
			pstmt.setInt(1, diaryType);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				jObj.clear();
				Base64.Encoder beCoder=Base64.getEncoder();
				String beStr=beCoder.encodeToString(rs.getBytes("Photo_Video_1"));
				jObj.put("DiaryID",rs.getInt("DiaryID"));
				jObj.put("Photo_Video_1", beStr);
				jObj.put("Subject", rs.getString("Subject"));

				array.put(jObj.toMap());
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
		return array;
	}


}

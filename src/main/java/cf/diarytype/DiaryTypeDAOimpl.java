package cf.diarytype;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import basicutil.Util;
import util.DB;

public class DiaryTypeDAOimpl implements DiaryTypeDAO {
	
	private static final String INSERT_DIARYTYPE = "INSERT INTO DiaryType (DiaryTypeID, DiaryTypeName) VALUES (?, ?)";
	private static final String UPDATE_DIARYTYPE = "UPDATE DiaryType SET DiaryTypeName = ? WHERE DiaryTypeID = ?";
	private static final String DELETE_DIARYTYPE = "DELETE FROM DiaryType WHERE DiaryTypeID = ?";
	private static final String FIND_BY_PK = "SELECT * FROM DiaryType WHERE DiaryTypeID = ?";
	private static final String GET_ALL = "SELECT * FROM DiaryType";

	@Override
	public void insert(DiaryTypeVO diaryTypeVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = Util.getConnection();
			pstmt = con.prepareStatement(INSERT_DIARYTYPE);
			
			pstmt.setInt(1, diaryTypeVO.getDiaryTypeID());
			pstmt.setString(2, diaryTypeVO.getDiaryTypeName());
			
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
	public void update(DiaryTypeVO diaryTypeVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = Util.getConnection();
			pstmt = con.prepareStatement(UPDATE_DIARYTYPE);

			pstmt.setString(1, diaryTypeVO.getDiaryTypeName());
			pstmt.setInt(2, diaryTypeVO.getDiaryTypeID());
			
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
	public void delete(Integer diaryTypeID) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = Util.getConnection();
			pstmt = con.prepareStatement(DELETE_DIARYTYPE);

			pstmt.setInt(1, diaryTypeID);
			
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
	public DiaryTypeVO findByPrimaryKey(Integer diaryTypeID) {
		DiaryTypeVO type = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = Util.getConnection();
			pstmt = con.prepareStatement(FIND_BY_PK);
			pstmt.setInt(1, diaryTypeID);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				type = new DiaryTypeVO();
				type.setDiaryTypeID(rs.getInt("diaryTypeID"));
				type.setDiaryTypeName(rs.getString("diaryTypeName"));
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

		return type;
	}

	@Override
	public List<DiaryTypeVO> getAll() {
		List<DiaryTypeVO> typeList = new ArrayList<>();
		DiaryTypeVO type = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = Util.getConnection();
			pstmt = con.prepareStatement(GET_ALL);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				type = new DiaryTypeVO();
				type.setDiaryTypeID(rs.getInt("diaryTypeID"));
				type.setDiaryTypeName(rs.getString("diaryTypeName"));
				typeList.add(type);
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
		return typeList;
	}

}

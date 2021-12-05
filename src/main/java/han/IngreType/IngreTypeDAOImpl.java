package han.IngreType;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.basic_tool.controller.*;


public class IngreTypeDAOImpl implements IngreTypeDAO {
	private static final String INSERT_STMT = "INSERT INTO INGRETYPE(IDINGRETYPE, TYPENAME) VALUES (?, ?)";
	private static final String UPDATE_STMT = "UPDATE INGRETYPE SET TYPENAME = ? WHERE IDINGRETYPE = ?";
	private static final String DELETE_STMT = "DELETE FROM INGRETYPE WHERE IDINGRETYPE = ?";
	private static final String FIND_BY_PK = "SELECT * FROM INGRETYPE WHERE IDINGRETYPE = ?";
	private static final String GET_ALL = "SELECT * FROM INGRETYPE";
	
	@Override
	public void insert(IngreTypeVO ingreTypeVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = Util.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setInt(1,ingreTypeVO.getIdIngreType());
			pstmt.setString(2,ingreTypeVO.getTypeName());
			
			
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
	public void update(IngreTypeVO ingreTypeVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = Util.getConnection();
			pstmt = con.prepareStatement(UPDATE_STMT);

			pstmt.setString(1,ingreTypeVO.getTypeName());
			pstmt.setInt(2,ingreTypeVO.getIdIngreType());
			

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
	public void delete(Integer idIngreType) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = Util.getConnection();
			pstmt = con.prepareStatement(DELETE_STMT);

			pstmt.setInt(1, idIngreType);
			
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
	public IngreTypeVO findByPK(Integer idIngreType) {
		IngreTypeVO type = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = Util.getConnection();
			pstmt = con.prepareStatement(FIND_BY_PK);
			pstmt.setInt(1, idIngreType);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				type = new IngreTypeVO();
				type.setIdIngreType(rs.getInt("IDINGRETYPE"));
				type.setTypeName(rs.getString("TYPENAME"));
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

		return type;
	}
	@Override
	public List<IngreTypeVO> getAll() {
		List<IngreTypeVO> typeList = new ArrayList<>();
		IngreTypeVO type = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = Util.getConnection();
			pstmt = con.prepareStatement(GET_ALL);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				type = new IngreTypeVO();
				type.setIdIngreType(rs.getInt("IDINGRETYPE"));
				type.setTypeName(rs.getString("TYPENAME"));
				typeList.add(type);
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
		return typeList;
	}

}

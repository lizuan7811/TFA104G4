package po;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import basicutil.Util;


public class TempOrderDAOImpl implements TempOrderDAO {
	public static final String INSERT_STMT = "INSERT INTO TEMPORDER(CUSTID, INGRElID, ORDERQUAN, PRICE) VALUES (?, ?, ?, ?)";
	public static final String UPDATE_STMT = "UPDATE TEMPORDER SET INGRElID = ?, ORDERQUAN = ?, PRICE = ? WHERE CUSTID = ?";
	public static final String DELETE_STMT = "DELETE FROM TEMPORDER WHERE CUSTID = ?";
	public static final String FIND_BY_PK = "SELECT * FROM TEMPORDER WHERE CUSTID = ?";
	public static final String GET_ALL = "SELECT * FROM TEMPORDER";
	
	@Override
	public void insert(TempOrderVO tempOrderVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = Util.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, tempOrderVO.getCustID());
			pstmt.setInt(2, tempOrderVO.getIngrelID());
			pstmt.setInt(3, tempOrderVO.getOrderQuan());
			pstmt.setInt(4, tempOrderVO.getPrice());


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
	public void update(TempOrderVO tempOrderVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = Util.getConnection();
			pstmt = con.prepareStatement(UPDATE_STMT);

			pstmt.setInt(1, tempOrderVO.getIngrelID());
			pstmt.setInt(2, tempOrderVO.getOrderQuan());
			pstmt.setInt(3, tempOrderVO.getPrice());
			pstmt.setInt(4, tempOrderVO.getCustID());
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
	public void delete(Integer custID) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = Util.getConnection();
			pstmt = con.prepareStatement(DELETE_STMT);

			pstmt.setInt(1, custID);
			
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
	public TempOrderVO findByPK(Integer custID, Integer IngrelID) {
		TempOrderVO tem = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = Util.getConnection();
			pstmt = con.prepareStatement(FIND_BY_PK);
			pstmt.setInt(1, custID);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				tem = new TempOrderVO();
				tem.setCustID(rs.getInt("CUSTID"));
				tem.setIngrelID(rs.getInt("INGRElID"));
				tem.setOrderQuan(rs.getInt("ORDERQUAN"));
				tem.setPrice(rs.getInt("PRICE"));

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

		return tem;
	}

	@Override
	public List<TempOrderVO> getAll() {
		List<TempOrderVO> temList = new ArrayList<>();
		TempOrderVO tem = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = Util.getConnection();
			pstmt = con.prepareStatement(GET_ALL);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				tem = new TempOrderVO();
				tem.setCustID(rs.getInt("CUSTID"));
				tem.setIngrelID(rs.getInt("INGRElID"));
				tem.setOrderQuan(rs.getInt("ORDERQUAN"));
				tem.setPrice(rs.getInt("PRICE"));
				temList.add(tem);
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
		return temList;
	}

}

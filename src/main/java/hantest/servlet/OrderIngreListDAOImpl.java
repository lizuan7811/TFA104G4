package hantest.servlet;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderIngreListDAOImpl implements OrderIngreListDAO {
	private static final String INSERT_STMT = "INSERT INTO ORDERINGRELIST(IDORDER, IDINGRE, ORDERQUAN, PRICE) VALUES (?, ?, ?, ?)";
	private static final String UPDATE_STMT = "UPDATE ORDERINGRELIST SET ORDERQUAN = ?, PRICE = ? WHERE IDORDER = ? and IDINGRE = ?";
	private static final String DELETE_STMT = "DELETE FROM ORDERINGRELIST WHERE IDORDER = ? and IDINGRE = ?";
	private static final String FIND_BY_PK = "SELECT * FROM ORDERINGRELIST WHERE IDORDER = ? and IDINGRE = ?";
	private static final String GET_ALL = "SELECT * FROM ORDERINGRELIST";
	
	static {
		try {
			Class.forName(Util.DRIVER);
		} catch (ClassNotFoundException ce) {
			ce.printStackTrace();
		}
	}

	
	
	
	@Override
	public void insert(OrderIngreListVO orderingrelistVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setInt(1,orderingrelistVO.getIdOrder());
			pstmt.setInt(2,orderingrelistVO.getIdIngre());
			pstmt.setInt(3,orderingrelistVO.getOrderQuan());
			pstmt.setInt(4,orderingrelistVO.getPrice());
			
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
	public void update(OrderIngreListVO orderingrelistVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(UPDATE_STMT);

		
			pstmt.setInt(1,orderingrelistVO.getOrderQuan());
			pstmt.setInt(2,orderingrelistVO.getPrice());
			pstmt.setInt(3,orderingrelistVO.getIdOrder());
			pstmt.setInt(4,orderingrelistVO.getIdIngre());

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
	public void delete(int idOrder, int idIngre) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(DELETE_STMT);

			pstmt.setInt(1, idOrder);
			pstmt.setInt(2, idIngre);
			
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
	public OrderIngreListVO findByPK(int idOrder, int idIngre) {
		OrderIngreListVO orderil = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(FIND_BY_PK);
			pstmt.setInt(1, idOrder);
			pstmt.setInt(2, idIngre);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				orderil = new OrderIngreListVO();
				orderil.setIdOrder(rs.getInt("IDORDER"));
				orderil.setIdIngre(rs.getInt("IDINGRE"));
				orderil.setOrderQuan(rs.getInt("ORDERQUAN"));
				orderil.setPrice(rs.getInt("PRICE"));
			
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

		return orderil;
	}

	@Override
	public List<OrderIngreListVO> getAll() {
		List<OrderIngreListVO> orderilList = new ArrayList<>();
		OrderIngreListVO orderil = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(GET_ALL);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				orderil = new OrderIngreListVO();
				orderil.setIdOrder(rs.getInt("IDORDER"));
				orderil.setIdIngre(rs.getInt("IDINGRE"));
				orderil.setOrderQuan(rs.getInt("ORDERQUAN"));
				orderil.setPrice(rs.getInt("PRICE"));
				orderilList.add(orderil);
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
		return orderilList;
	}

}

package madi;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class OrderDAOImpl implements OrderDAO {

	private static final String INSERT_STMT = "INSERT INTO FinalOrder (idOrder, idCustomer, recipient, "
			+ "recipientAddress, orderAmount, createdTime, shipTime, arrivalTime, footnote)"
			+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String UPDATE_STMT = "UPDATE FinalOrder SET idCustomer = ?, recipient = ?, recipientAddress = ?, "
			+ "orderAmount = ?, createdTime = ?, shipTime = ?, arrivalTime = ?, footnote = ? WHERE idOrder = ?";
	private static final String DELETE_STMT = "DELETE FROM `Order` WHERE idOrder = ?";
	private static final String FIND_BY_PK = "SELECT * FROM `Order` WHERE idOrder = ?";
	private static final String GET_ALL = "SELECT * FROM `Order`";

	static {
		try {
			Class.forName(Util.DRIVER);
		} catch (ClassNotFoundException ce) {
			ce.printStackTrace();
		}
	}
	
	@Override
	public void add(OrderVO orderVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, orderVO.getIdOrder());
			pstmt.setInt(2, orderVO.getIdCustomer());
			pstmt.setString(3, orderVO.getRecipient());
			pstmt.setString(4, orderVO.getRecipientAddress());
			pstmt.setDouble(5, orderVO.getOrderAmount());
			pstmt.setTimestamp(6, orderVO.getCreatedTime());
			pstmt.setTimestamp(7, orderVO.getShipTime());
			pstmt.setTimestamp(8, orderVO.getArrivalTime());
			pstmt.setString(9, orderVO.getFootnote());
				
			pstmt.executeUpdate();
			
		} catch (SQLException se){
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
	public void update(OrderVO orderVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(UPDATE_STMT);
			
			pstmt.setInt(1, orderVO.getIdCustomer());
			pstmt.setString(2, orderVO.getRecipient());
			pstmt.setString(3, orderVO.getRecipientAddress());
			pstmt.setDouble(4, orderVO.getOrderAmount());
			pstmt.setTimestamp(5, orderVO.getCreatedTime());
			pstmt.setTimestamp(6, orderVO.getShipTime());
			pstmt.setTimestamp(7, orderVO.getArrivalTime());
			pstmt.setString(8, orderVO.getFootnote());
			pstmt.setInt(9, orderVO.getIdOrder());
				
			pstmt.executeUpdate();
			
		} catch (SQLException se){
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
	public void delete(Integer idOrder) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(DELETE_STMT);
			
			pstmt.setInt(1, idOrder);
			
			pstmt.executeUpdate();
			
		} catch (SQLException se){
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
	public OrderVO findByPK(Integer idOrder) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		OrderVO orderVO = null;
		
		try {
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(FIND_BY_PK);
			pstmt.setInt(1, idOrder);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {

				orderVO = new OrderVO();
				orderVO.setIdOrder(rs.getInt("idOrder"));
				orderVO.setIdCustomer(rs.getInt("idCustomer"));
				orderVO.setRecipient(rs.getString("recipient"));
				orderVO.setRecipientAddress(rs.getString("recipientAddress"));
				orderVO.setOrderAmount(rs.getDouble("orderAmount"));
				orderVO.setCreatedTime(rs.getTimestamp("createdTime"));
				orderVO.setShipTime(rs.getTimestamp("shipTime"));
				orderVO.setArrivalTime(rs.getTimestamp("arrivalTime"));
				orderVO.setFootnote(rs.getString("footnote"));			
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
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
		}
		
		return orderVO;
	}

	@Override
	public List<OrderVO> getAll() {
		List<OrderVO> orderList = new ArrayList<>();
		OrderVO orderVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(GET_ALL);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				
				orderVO = new OrderVO();
				orderVO.setIdOrder(rs.getInt("idOrder"));
				orderVO.setIdCustomer(rs.getInt("idCustomer"));
				orderVO.setRecipient(rs.getString("recipient"));
				orderVO.setRecipientAddress(rs.getString("recipientAddress"));
				orderVO.setOrderAmount(rs.getDouble("orderAmount"));
				orderVO.setCreatedTime(rs.getTimestamp("createdTime"));
				orderVO.setShipTime(rs.getTimestamp("shipTime"));
				orderVO.setArrivalTime(rs.getTimestamp("arrivalTime"));
				orderVO.setFootnote(rs.getString("footnote"));			
				orderList.add(orderVO);
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
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
		}
		
		return orderList;
	}


}

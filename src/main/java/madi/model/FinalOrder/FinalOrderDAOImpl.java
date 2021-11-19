package madi.model.FinalOrder;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import model.Util;

public class FinalOrderDAOImpl implements FinalOrderDAO {

	private static final String INSERT_STMT = "INSERT INTO FinalOrder (idFinalOrder, idCustomer, recipient, "
			+ "recipientAddress, orderAmount, createdTime, shipTime, arrivalTime, footnote)"
			+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String UPDATE_STMT = "UPDATE FinalOrder SET idCustomer = ?, recipient = ?, recipientAddress = ?, "
			+ "orderAmount = ?, createdTime = ?, shipTime = ?, arrivalTime = ?, footnote = ? WHERE idFinalOrder = ?";
	private static final String DELETE_STMT = "DELETE FROM FinalOrder WHERE idFinalOrder = ?";
	private static final String FIND_BY_PK = "SELECT * FROM FinalOrder WHERE idFinalOrder = ?";
	private static final String GET_ALL = "SELECT * FROM FinalOrder";

	static {
		try {
			Class.forName(Util.DRIVER);
		} catch (ClassNotFoundException ce) {
			ce.printStackTrace();
		}
	}
	
	@Override
	public void insert(FinalOrderVO finalOrderVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, finalOrderVO.getIdFinalOrder());
			pstmt.setInt(2, finalOrderVO.getIdCustomer());
			pstmt.setString(3, finalOrderVO.getRecipient());
			pstmt.setString(4, finalOrderVO.getRecipientAddress());
			pstmt.setDouble(5, finalOrderVO.getOrderAmount());
			pstmt.setTimestamp(6, finalOrderVO.getCreatedTime());
			pstmt.setTimestamp(7, finalOrderVO.getShipTime());
			pstmt.setTimestamp(8, finalOrderVO.getArrivalTime());
			pstmt.setString(9, finalOrderVO.getFootnote());
				
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
	public void update(FinalOrderVO finalOrderVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(UPDATE_STMT);
			
			pstmt.setInt(1, finalOrderVO.getIdCustomer());
			pstmt.setString(2, finalOrderVO.getRecipient());
			pstmt.setString(3, finalOrderVO.getRecipientAddress());
			pstmt.setDouble(4, finalOrderVO.getOrderAmount());
			pstmt.setTimestamp(5, finalOrderVO.getCreatedTime());
			pstmt.setTimestamp(6, finalOrderVO.getShipTime());
			pstmt.setTimestamp(7, finalOrderVO.getArrivalTime());
			pstmt.setString(8, finalOrderVO.getFootnote());
			pstmt.setInt(9, finalOrderVO.getIdFinalOrder());
				
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
	public void delete(Integer idFinalOrder) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(DELETE_STMT);
			
			pstmt.setInt(1, idFinalOrder);
			
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
	public FinalOrderVO findByPK(Integer idFinalOrder) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		FinalOrderVO finalOrderVO = null;
		
		try {
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(FIND_BY_PK);
			pstmt.setInt(1, idFinalOrder);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {

				finalOrderVO = new FinalOrderVO();
				finalOrderVO.setIdFinalOrder(rs.getInt("idFinalOrder"));
				finalOrderVO.setIdCustomer(rs.getInt("idCustomer"));
				finalOrderVO.setRecipient(rs.getString("recipient"));
				finalOrderVO.setRecipientAddress(rs.getString("recipientAddress"));
				finalOrderVO.setOrderAmount(rs.getDouble("orderAmount"));
				finalOrderVO.setCreatedTime(rs.getTimestamp("createdTime"));
				finalOrderVO.setShipTime(rs.getTimestamp("shipTime"));
				finalOrderVO.setArrivalTime(rs.getTimestamp("arrivalTime"));
				finalOrderVO.setFootnote(rs.getString("footnote"));			
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
		
		return finalOrderVO;
	}

	@Override
	public List<FinalOrderVO> getAll() {
		List<FinalOrderVO> finalOrderList = new ArrayList<>();
		FinalOrderVO finalOrderVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(GET_ALL);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				
				finalOrderVO = new FinalOrderVO();
				finalOrderVO.setIdFinalOrder(rs.getInt("idFinalOrder"));
				finalOrderVO.setIdCustomer(rs.getInt("idCustomer"));
				finalOrderVO.setRecipient(rs.getString("recipient"));
				finalOrderVO.setRecipientAddress(rs.getString("recipientAddress"));
				finalOrderVO.setOrderAmount(rs.getDouble("orderAmount"));
				finalOrderVO.setCreatedTime(rs.getTimestamp("createdTime"));
				finalOrderVO.setShipTime(rs.getTimestamp("shipTime"));
				finalOrderVO.setArrivalTime(rs.getTimestamp("arrivalTime"));
				finalOrderVO.setFootnote(rs.getString("footnote"));			
				finalOrderList.add(finalOrderVO);
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
		
		return finalOrderList;
	}


}

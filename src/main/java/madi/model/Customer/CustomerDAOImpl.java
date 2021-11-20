package madi.model.Customer;
import java.sql.*;
import java.util.*;

import basicutil.Util;

public class CustomerDAOImpl implements CustomerDAO {
	
	private static final String INSERT_STMT = "INSERT INTO Customer (idCustomer, name, profic, nickName, account, "
			+ "password, email, phone, createdTime, suspended, externalAcc, commentReportedNum, diaryReportedNum) "
			+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String UPDATE_STMT = "UPDATE Customer SET name = ?, profic = ?, nickName = ?, account = ?, "
			+ "password = ?, email = ?, phone = ?, createdTime = ?, suspended = ?, externalAcc = ?, commentReportedNum = ?, "
			+ "diaryReportedNum = ? WHERE idCustomer = ?";
	private static final String DELETE_STMT = "DELETE FROM Customer WHERE idCustomer = ?";
	private static final String FIND_BY_PK = "SELECT * FROM Customer WHERE idCustomer = ?";
	private static final String GET_ALL = "SELECT * FROM Customer";
	
	
	@Override
	public void insert(CustomerVO customerVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			
			con = Util.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setInt(1, customerVO.getIdCustomer());
			pstmt.setString(2, customerVO.getName());
			pstmt.setBytes(3, customerVO.getProfic());
			pstmt.setString(4, customerVO.getNickName());
			pstmt.setString(5, customerVO.getAccount());
			pstmt.setString(6, customerVO.getPassword());
			pstmt.setString(7, customerVO.getEmail());
			pstmt.setString(8, customerVO.getPhone());
			pstmt.setTimestamp(9, customerVO.getCreatedTime());
			pstmt.setBoolean(10, customerVO.getSuspended());
			pstmt.setInt(11, customerVO.getExternalAcc());
			pstmt.setInt(12, customerVO.getCommentReportedNum());
			pstmt.setInt(13, customerVO.getDiaryReportedNum());
			
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
	public void update(CustomerVO customerVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			
			con = Util.getConnection();
			pstmt = con.prepareStatement(UPDATE_STMT);
			
			pstmt.setString(1, customerVO.getName());
			pstmt.setBytes(2, customerVO.getProfic());
			pstmt.setString(3, customerVO.getNickName());
			pstmt.setString(4, customerVO.getAccount());
			pstmt.setString(5, customerVO.getPassword());
			pstmt.setString(6, customerVO.getEmail());
			pstmt.setString(7, customerVO.getPhone());
			pstmt.setTimestamp(8, customerVO.getCreatedTime());
			pstmt.setBoolean(9, customerVO.getSuspended());
			pstmt.setInt(10, customerVO.getExternalAcc());
			pstmt.setInt(11, customerVO.getCommentReportedNum());
			pstmt.setInt(12, customerVO.getDiaryReportedNum());
			pstmt.setInt(13, customerVO.getIdCustomer());
			
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
	public void delete(Integer idCustomer) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			
			con = Util.getConnection();
			pstmt = con.prepareStatement(DELETE_STMT);
			
			pstmt.setInt(1, idCustomer);
			
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
	public CustomerVO findByPK(Integer idCustomer) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		CustomerVO customerVO = null;
		
		try {
			con = Util.getConnection();
			pstmt = con.prepareStatement(FIND_BY_PK);
			pstmt.setInt(1, idCustomer);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				
				customerVO = new CustomerVO();
				customerVO.setIdCustomer(rs.getInt("idCustomer"));
				customerVO.setName(rs.getString("name"));
				customerVO.setProfic(rs.getBytes("profic"));
				customerVO.setNickName(rs.getString("nickname"));
				customerVO.setAccount(rs.getString("account"));
				customerVO.setPassword(rs.getString("password"));
				customerVO.setEmail(rs.getString("email"));
				customerVO.setPhone(rs.getString("phone"));
				customerVO.setCreatedTime(rs.getTimestamp("createdTime"));
				customerVO.setSuspended(rs.getBoolean("suspended"));
				customerVO.setExternalAcc(rs.getInt("externalAcc"));
				customerVO.setCommentReportedNum(rs.getInt("commentReportedNum"));
				customerVO.setDiaryReportedNum(rs.getInt("diaryReportedNum"));
				
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
		
		return customerVO;
	}

	@Override
	public List<CustomerVO> getAll() {
		List<CustomerVO> customerList = new ArrayList<>();
		CustomerVO customerVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = Util.getConnection();
			pstmt = con.prepareStatement(GET_ALL);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				
				customerVO = new CustomerVO();
				customerVO.setIdCustomer(rs.getInt("idCustomer"));
				customerVO.setName(rs.getString("name"));
				customerVO.setProfic(rs.getBytes("profic"));
				customerVO.setNickName(rs.getString("nickname"));
				customerVO.setAccount(rs.getString("account"));
				customerVO.setPassword(rs.getString("password"));
				customerVO.setEmail(rs.getString("email"));
				customerVO.setPhone(rs.getString("phone"));
				customerVO.setCreatedTime(rs.getTimestamp("createdTime"));
				customerVO.setSuspended(rs.getBoolean("suspended"));
				customerVO.setExternalAcc(rs.getInt("externalAcc"));
				customerVO.setCommentReportedNum(rs.getInt("commentReportedNum"));
				customerVO.setDiaryReportedNum(rs.getInt("diaryReportedNum"));
				customerList.add(customerVO);
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
		
		return customerList;
	}
	
}

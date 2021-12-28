package com.customer.model;
import java.sql.*;
import java.util.*;

import com.basic_tool.controller.Util;


public class CustomerDAOImpl implements CustomerDAO {
	
	private static final String INSERT_CUST_BY_ADMIN = "INSERT INTO Customer (idCustomer, name, nickname, account, password, email, phone, "
			+ "notification, profic, createdTime, activated, suspended, externalAcc, externalIdToken) "
			+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String INSERT_CUST_BY_CUST = "INSERT INTO Customer (name, nickname, account, password, email, phone, "
			+ "notification, profic, createdTime, activated, suspended, externalAcc, externalIdToken) "
			+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)"; // customerPreRegsiter.jsp & customerRegister.jsp
	private static final String UPDATE_CUST_BY_ADMIN = "UPDATE Customer SET name = ?, nickname = ?, account = ?, password = ?, "
			+ "email = ?, phone = ?, notification = ?, profic = ?, createdTime = ?, activated = ? suspended = ?, externalAcc = ?, externalIdToken = ?, "
			+ "commentReportedNum = ?, diaryReportedNum = ? WHERE idCustomer = ?";
	private static final String UPDATE_CUST_BY_CUST = "UPDATE Customer SET name = ?, nickname = ?, password = ?, email = ?, phone = ?, notification = ?, "
			+ "profic = ?, createdTime = ? WHERE account = ?"; // customerUpdate.jsp
	private static final String UPDATE_ACTI_BY_ADMIN = "UPDATE Customer SET activated = ? WHERE idCustomer = ?";
	private static final String UPDATE_PWD_BY_CUST = "UPDATE Customer SET password = ? WHERE account = ?"; // customerRest.jsp
	private static final String DELETE_CUST_BY_ADMIN = "DELETE FROM Customer WHERE idCustomer = ?";
	private static final String FIND_CUST_BY_ID = "SELECT * FROM Customer WHERE idCustomer = ?";
	private static final String FIND_CUST_BY_ACCOUNT = "SELECT * FROM Customer WHERE account = ?"; // customerLogin.jsp
	private static final String FIND_CUST_BY_EMAIL = "SELECT * FROM Customer WHERE email = ?"; // customerForget.jsp
//	private static final String GET_PROFIC_BY_ACCOUNT = "SELECT profic FROM Customer WHERE account = ?";
	private static final String GET_ALL = "SELECT * FROM Customer";
	
	@Override
	public void insertCustByAdmin(CustomerVO customerVO) {
		
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			
			con = Util.getConnection();
			pstmt = con.prepareStatement(INSERT_CUST_BY_ADMIN);
			
			pstmt.setInt(1, customerVO.getIdCustomer());
			pstmt.setString(2, customerVO.getName());
			pstmt.setString(3, customerVO.getNickname());
			pstmt.setString(4, customerVO.getAccount());
			pstmt.setString(5, customerVO.getPassword());
			pstmt.setString(6, customerVO.getEmail());
			pstmt.setString(7, customerVO.getPhone());
			pstmt.setBoolean(8, customerVO.getNotification());
			pstmt.setBytes(9, customerVO.getProfic());
			pstmt.setTimestamp(10, customerVO.getCreatedTime());
			pstmt.setBoolean(11, customerVO.getActivated());
			pstmt.setBoolean(12, customerVO.getSuspended());
			pstmt.setInt(13, customerVO.getExternalAcc());
			pstmt.setString(14, customerVO.getExternalIdToken());
//			pstmt.setInt(15, customerVO.getCommentReportedNum());
//			pstmt.setInt(16, customerVO.getDiaryReportedNum());
			
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
	public Integer insertCustByCust(CustomerVO customerVO) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		Integer idCustomer = null;
		try {
			
						con = Util.getConnection();
			pstmt = con.prepareStatement(INSERT_CUST_BY_CUST, Statement.RETURN_GENERATED_KEYS);
			
			pstmt.setString(1, customerVO.getName());
			pstmt.setString(2, customerVO.getNickname());
			pstmt.setString(3, customerVO.getAccount());
			pstmt.setString(4, customerVO.getPassword());
			pstmt.setString(5, customerVO.getEmail());
			pstmt.setString(6, customerVO.getPhone());
			pstmt.setBoolean(7, customerVO.getNotification());
			pstmt.setBytes(8, customerVO.getProfic());
			pstmt.setTimestamp(9, customerVO.getCreatedTime());
			pstmt.setBoolean(10, customerVO.getActivated());
			pstmt.setBoolean(11, customerVO.getSuspended());
			pstmt.setInt(12, customerVO.getExternalAcc());
			pstmt.setString(13, customerVO.getExternalIdToken());
//			pstmt.setInt(14, customerVO.getCommentReportedNum());
//			pstmt.setInt(15, customerVO.getDiaryReportedNum());
			
			pstmt.executeUpdate();
			
			ResultSet rs = pstmt.getGeneratedKeys();
			if (rs.next()) {
				idCustomer = rs.getInt(1);
			} else {
				System.out.println("No generated keys...");
			}
			
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
		return idCustomer;
	}

	@Override
	public void updateCustByAdmin(CustomerVO customerVO) {
		
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			
						con = Util.getConnection();
			pstmt = con.prepareStatement(UPDATE_CUST_BY_ADMIN);
			
			pstmt.setString(1, customerVO.getName());
			pstmt.setString(2, customerVO.getNickname());
			pstmt.setString(3, customerVO.getAccount());
			pstmt.setString(4, customerVO.getPassword());
			pstmt.setString(5, customerVO.getEmail());
			pstmt.setString(6, customerVO.getPhone());
			pstmt.setBoolean(7, customerVO.getNotification());
			pstmt.setBytes(8, customerVO.getProfic());
			pstmt.setTimestamp(9, customerVO.getCreatedTime());
			pstmt.setBoolean(10, customerVO.getActivated());
			pstmt.setBoolean(11, customerVO.getSuspended());
			pstmt.setInt(12, customerVO.getExternalAcc());
			pstmt.setString(13, customerVO.getExternalIdToken());
//			pstmt.setInt(14, customerVO.getCommentReportedNum());
//			pstmt.setInt(15, customerVO.getDiaryReportedNum());
			pstmt.setInt(16, customerVO.getIdCustomer());
			
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
	public void updateCustByCust(CustomerVO customerVO) {
		
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

						con = Util.getConnection();
			pstmt = con.prepareStatement(UPDATE_CUST_BY_CUST);
			
			pstmt.setString(1, customerVO.getName());
			pstmt.setString(2, customerVO.getNickname());
			pstmt.setString(3, customerVO.getPassword());
			pstmt.setString(4, customerVO.getEmail());
			pstmt.setString(5, customerVO.getPhone());
			pstmt.setBoolean(6, customerVO.getNotification());
			pstmt.setBytes(7, customerVO.getProfic());
			pstmt.setTimestamp(8, customerVO.getCreatedTime());
			pstmt.setString(9, customerVO.getAccount());
			
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
	public void updateActiByAdmin(Integer idCustomer) {
	
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			
						con = Util.getConnection();
			pstmt = con.prepareStatement(UPDATE_ACTI_BY_ADMIN);
			
			pstmt.setBoolean(1, true);
			pstmt.setInt(2, idCustomer);
			
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
	public void updatePwdByCust(String account, String password) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
						con = Util.getConnection();
			pstmt = con.prepareStatement(UPDATE_PWD_BY_CUST);
			
			pstmt.setString(1, password);
			pstmt.setString(2, account);
			
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
	public void deleteCustByAdmin(Integer idCustomer) {
		
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			
						con = Util.getConnection();
			pstmt = con.prepareStatement(DELETE_CUST_BY_ADMIN);
			
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
	public CustomerVO findCustById(Integer idCustomer) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		CustomerVO customerVO = null;
		
		try {
						con = Util.getConnection();
			pstmt = con.prepareStatement(FIND_CUST_BY_ID);
			pstmt.setInt(1, idCustomer);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				
				customerVO = new CustomerVO();
				customerVO.setIdCustomer(rs.getInt("idCustomer"));
				customerVO.setName(rs.getString("name"));
				customerVO.setNickname(rs.getString("nickname"));
				customerVO.setAccount(rs.getString("account"));
				customerVO.setPassword(rs.getString("password"));
				customerVO.setEmail(rs.getString("email"));
				customerVO.setPhone(rs.getString("phone"));
				customerVO.setNotification(rs.getBoolean("notification"));
				customerVO.setProfic(rs.getBytes("profic"));
				customerVO.setCreatedTime(rs.getTimestamp("createdTime"));
				customerVO.setActivated(rs.getBoolean("activated"));
				customerVO.setSuspended(rs.getBoolean("suspended"));
				customerVO.setExternalAcc(rs.getInt("externalAcc"));
				customerVO.setExternalIdToken(rs.getString("externalIdToken"));
//				customerVO.setCommentReportedNum(rs.getInt("commentReportedNum"));
//				customerVO.setDiaryReportedNum(rs.getInt("diaryReportedNum"));
				
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
	public CustomerVO findCustByAccount(String account) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		CustomerVO customerVO = null;
		
		try {
						con = Util.getConnection();
			pstmt = con.prepareStatement(FIND_CUST_BY_ACCOUNT);
			pstmt.setString(1, account);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				
				customerVO = new CustomerVO();
				customerVO.setIdCustomer(rs.getInt("idCustomer"));
				customerVO.setName(rs.getString("name"));
				customerVO.setNickname(rs.getString("nickname"));
				customerVO.setAccount(rs.getString("account"));
				customerVO.setPassword(rs.getString("password"));
				customerVO.setEmail(rs.getString("email"));
				customerVO.setPhone(rs.getString("phone"));
				customerVO.setNotification(rs.getBoolean("notification"));
				customerVO.setProfic(rs.getBytes("profic"));
				customerVO.setCreatedTime(rs.getTimestamp("createdTime"));
				customerVO.setActivated(rs.getBoolean("activated"));
				customerVO.setSuspended(rs.getBoolean("suspended"));
				customerVO.setExternalAcc(rs.getInt("externalAcc"));
				customerVO.setExternalIdToken(rs.getString("externalIdToken"));
//				customerVO.setCommentReportedNum(rs.getInt("commentReportedNum"));
//				customerVO.setDiaryReportedNum(rs.getInt("diaryReportedNum"));
				
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
	public CustomerVO findCustByEmail(String email) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		CustomerVO customerVO = null;
		
		try {
						con = Util.getConnection();
			pstmt = con.prepareStatement(FIND_CUST_BY_EMAIL);
			pstmt.setString(1, email);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				
				customerVO = new CustomerVO();
				customerVO.setIdCustomer(rs.getInt("idCustomer"));
				customerVO.setName(rs.getString("name"));
				customerVO.setNickname(rs.getString("nickname"));
				customerVO.setAccount(rs.getString("account"));
				customerVO.setPassword(rs.getString("password"));
				customerVO.setEmail(rs.getString("email"));
				customerVO.setPhone(rs.getString("phone"));
				customerVO.setNotification(rs.getBoolean("notification"));
				customerVO.setProfic(rs.getBytes("profic"));
				customerVO.setCreatedTime(rs.getTimestamp("createdTime"));
				customerVO.setActivated(rs.getBoolean("activated"));
				customerVO.setSuspended(rs.getBoolean("suspended"));
				customerVO.setExternalAcc(rs.getInt("externalAcc"));
				customerVO.setExternalIdToken(rs.getString("externalIdToken"));
//				customerVO.setCommentReportedNum(rs.getInt("commentReportedNum"));
//				customerVO.setDiaryReportedNum(rs.getInt("diaryReportedNum"));
				
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
				customerVO.setNickname(rs.getString("nickname"));
				customerVO.setAccount(rs.getString("account"));
				customerVO.setPassword(rs.getString("password"));
				customerVO.setEmail(rs.getString("email"));
				customerVO.setPhone(rs.getString("phone"));
				customerVO.setNotification(rs.getBoolean("notification"));
				customerVO.setProfic(rs.getBytes("profic"));
				customerVO.setCreatedTime(rs.getTimestamp("createdTime"));
				customerVO.setActivated(rs.getBoolean("activated"));
				customerVO.setSuspended(rs.getBoolean("suspended"));
				customerVO.setExternalAcc(rs.getInt("externalAcc"));
				customerVO.setExternalIdToken(rs.getString("externalIdToken"));
//				customerVO.setCommentReportedNum(rs.getInt("commentReportedNum"));
//				customerVO.setDiaryReportedNum(rs.getInt("diaryReportedNum"));
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

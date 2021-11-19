package model.Address;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Util;

public class AddressDAOImpl implements AddressDAO {
	
	private static final String INSERT_STMT = "INSERT INTO Address (idAddress, idCustomer, "
			+ "address, tag, longitude, latitude, createdTime, defaultOption)"
			+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String UPDATE_STMT = "UPDATE Address SET idCustomer = ?, address = ?, "
			+ "tag = ?, longitude = ?, latitude = ?, createdTime = ?, defaultOption = ? WHERE idAddress = ?";
	private static final String DELETE_STMT = "DELETE FROM Address WHERE idAddress = ?";
	private static final String FIND_BY_PK = "SELECT * FROM Address WHERE idAddress = ?";
	private static final String GET_ALL = "SELECT * FROM Address";

	static {
		try {
			Class.forName(Util.DRIVER);
		} catch (ClassNotFoundException ce) {
			ce.printStackTrace();
		}
	}
	
	@Override
	public void insert(AddressVO addressVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, addressVO.getIdAddress());
			pstmt.setInt(2, addressVO.getIdCustomer());
			pstmt.setString(3, addressVO.getAddress());
			pstmt.setString(4, addressVO.getTag());
			pstmt.setDouble(5, addressVO.getLongitude());
			pstmt.setDouble(6, addressVO.getLatitude());
			pstmt.setTimestamp(7, addressVO.getCreatedTime());
			pstmt.setBoolean(8, addressVO.getDefaultOption());
				
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
	public void update(AddressVO addressVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(UPDATE_STMT);
			
			pstmt.setInt(1, addressVO.getIdCustomer());
			pstmt.setString(2, addressVO.getAddress());
			pstmt.setString(3, addressVO.getTag());
			pstmt.setDouble(4, addressVO.getLongitude());
			pstmt.setDouble(5, addressVO.getLatitude());
			pstmt.setTimestamp(6, addressVO.getCreatedTime());
			pstmt.setBoolean(7, addressVO.getDefaultOption());
			pstmt.setInt(8, addressVO.getIdAddress());
				
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
	public void delete(Integer idAddress) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(DELETE_STMT);
			
			pstmt.setInt(1, idAddress);
			
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
	public AddressVO findByPK(Integer idAddress) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		AddressVO addressVO = null;
		
		try {
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(FIND_BY_PK);
			pstmt.setInt(1, idAddress);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				addressVO = new AddressVO();
				addressVO.setIdAddress(rs.getInt("idAddress"));
				addressVO.setIdCustomer(rs.getInt("idCustomer"));
				addressVO.setAddress(rs.getString("address"));
				addressVO.setTag(rs.getString("tag"));
				addressVO.setLongitude(rs.getDouble("longitude"));
				addressVO.setLatitude(rs.getDouble("latitude"));
				addressVO.setCreatedTime(rs.getTimestamp("createdTime"));
				addressVO.setDefaultOption(rs.getBoolean("defaultOption"));			
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
		
		return addressVO;
	}

	@Override
	public List<AddressVO> getAll() {
		List<AddressVO> addressList = new ArrayList<>();
		AddressVO addressVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(GET_ALL);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				
				addressVO = new AddressVO();
				addressVO.setIdAddress(rs.getInt("idAddress"));
				addressVO.setIdCustomer(rs.getInt("idCustomer"));
				addressVO.setAddress(rs.getString("address"));
				addressVO.setTag(rs.getString("tag"));
				addressVO.setLongitude(rs.getDouble("longitude"));
				addressVO.setLatitude(rs.getDouble("latitude"));
				addressVO.setCreatedTime(rs.getTimestamp("createdTime"));
				addressVO.setDefaultOption(rs.getBoolean("defaultOption"));	
				addressList.add(addressVO);
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
		
		return addressList;
	}

}

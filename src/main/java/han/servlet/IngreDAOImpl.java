package han.servlet;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

<<<<<<< HEAD
=======
import basicutil.Util;

>>>>>>> LizBranch
public class IngreDAOImpl implements IngreDAO {
	private static final String INSERT_STMT = "INSERT INTO INGRE(IDINGRE, IDINGRETYPE, NAME, PURPRICE, PRICE, UNIT, GRAN, SELL, DESCRIP, PHOTO, LAUNCH) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String UPDATE_STMT = "UPDATE INGRE SET IDINGRETYPE = ?, NAME = ?, PURPRICE = ?, PRICE = ?, UNIT = ?, GRAN = ?, SELL = ?, DESCRIP = ?, PHOTO = ?, LAUNCH = ? WHERE IDINGRE = ?";
	private static final String DELETE_STMT = "DELETE FROM INGRE WHERE IDINGRE = ?";
	private static final String FIND_BY_PK = "SELECT * FROM INGRE WHERE IDINGRE = ?";
	private static final String GET_ALL = "SELECT * FROM INGRE";
<<<<<<< HEAD
	
	static {
		try {
			Class.forName(Util.DRIVER);
		} catch (ClassNotFoundException ce) {
			ce.printStackTrace();
		}
	}
=======
>>>>>>> LizBranch

	
	@Override
	public void insert(IngreVO ingreVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

<<<<<<< HEAD
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
=======
			con = Util.getConnection();
>>>>>>> LizBranch
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setInt(1,ingreVO.getIdIngre());
			pstmt.setInt(2,ingreVO.getIdIngreType());
			pstmt.setString(3,ingreVO.getName());
			pstmt.setDouble(4,ingreVO.getPurPrice());
			pstmt.setDouble(5,ingreVO.getPrice());
			pstmt.setString(6,ingreVO.getUnit());
			pstmt.setInt(7,ingreVO.getGran());
			pstmt.setInt(8,ingreVO.getSell());
			pstmt.setString(9,ingreVO.getDescrip());
			pstmt.setBytes(10,ingreVO.getPhoto());
			pstmt.setBoolean(11,ingreVO.getLaunch());
			
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
	public void update(IngreVO ingreVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

<<<<<<< HEAD
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
=======
			con = Util.getConnection();
>>>>>>> LizBranch
			pstmt = con.prepareStatement(UPDATE_STMT);

	
			pstmt.setInt(1,ingreVO.getIdIngreType());
			pstmt.setString(2,ingreVO.getName());
			pstmt.setDouble(3,ingreVO.getPurPrice());
			pstmt.setDouble(4,ingreVO.getPrice());
			pstmt.setString(5,ingreVO.getUnit());
			pstmt.setInt(6,ingreVO.getGran());
			pstmt.setInt(7,ingreVO.getSell());
			pstmt.setString(8,ingreVO.getDescrip());
			pstmt.setBytes(9,ingreVO.getPhoto());
			pstmt.setBoolean(10,ingreVO.getLaunch());
			pstmt.setInt(11,ingreVO.getIdIngre());

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
	public void delete(int idIngre) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

<<<<<<< HEAD
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
=======
			con = Util.getConnection();
>>>>>>> LizBranch
			pstmt = con.prepareStatement(DELETE_STMT);

			pstmt.setInt(1, idIngre);
			
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
	public IngreVO findByPK(int idIngre) {
		IngreVO ingre = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

<<<<<<< HEAD
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
=======
			con = Util.getConnection();
>>>>>>> LizBranch
			pstmt = con.prepareStatement(FIND_BY_PK);
			pstmt.setInt(1, idIngre);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				ingre = new IngreVO();
				ingre.setIdIngre(rs.getInt("IDINGRE"));
				ingre.setIdIngreType(rs.getInt("IDINGRETYPE"));
				ingre.setName(rs.getString("NAME"));
				ingre.setPurPrice(rs.getDouble("PURPRICE"));
				ingre.setPrice(rs.getDouble("PRICE"));
				ingre.setUnit(rs.getString("UNIT"));
				ingre.setGran(rs.getInt("GRAN"));
				ingre.setSell(rs.getInt("SELL"));
				ingre.setDescrip(rs.getString("DESCRIP"));
				ingre.setPhoto(rs.getBytes("PHOTO"));
				ingre.setLaunch(rs.getBoolean("LAUNCH"));
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

		return ingre;
	}

	@Override
	public List<IngreVO> getAll() {
		List<IngreVO> ingreList = new ArrayList<>();
		IngreVO ingre = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

<<<<<<< HEAD
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
=======
			con = Util.getConnection();
>>>>>>> LizBranch
			pstmt = con.prepareStatement(GET_ALL);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				ingre = new IngreVO();
				ingre.setIdIngre(rs.getInt("IDINGRE"));
				ingre.setIdIngreType(rs.getInt("IDINGRETYPE"));
				ingre.setName(rs.getString("NAME"));
				ingre.setPurPrice(rs.getDouble("PURPRICE"));
				ingre.setPrice(rs.getDouble("PRICE"));
				ingre.setUnit(rs.getString("UNIT"));
				ingre.setGran(rs.getInt("GRAN"));
				ingre.setSell(rs.getInt("SELL"));
				ingre.setDescrip(rs.getString("DESCRIP"));
				ingre.setPhoto(rs.getBytes("PHOTO"));
				ingre.setLaunch(rs.getBoolean("LAUNCH"));
				ingreList.add(ingre);
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
		return ingreList;
	}

}

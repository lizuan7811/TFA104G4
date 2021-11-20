package madi.model.Card;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import basicutil.Util;

public class CardDAOImpl implements CardDAO{
	
	private static final String INSERT_STMT = "INSERT INTO Card (idCard, idCustomer, type, "
			+ "number, expiryDate, createdTime, defaultOption) VALUES (?, ?, ?, ?, ?, ?, ?)";
	private static final String UPDATE_STMT = "UPDATE Card SET idCustomer = ?, type = ?, number = ?,"
			+ "expiryDate = ?, createdTime = ?, defaultOption = ? WHERE idCard = ?";
	private static final String DELETE_STMT = "DELETE FROM Card WHERE idCard = ?";
	private static final String FIND_BY_PK = "SELECT * FROM Card WHERE idCard = ?";
	private static final String GET_ALL = "SELECT * FROM Card";

	
	@Override
	public void insert(CardVO cardVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			
			con = Util.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setInt(1, cardVO.getIdCard());
			pstmt.setInt(2, cardVO.getIdCustomer());
			pstmt.setBoolean(3, cardVO.getType());
			pstmt.setString(4, cardVO.getNumber());
			pstmt.setDate(5, cardVO.getExpiryDate());
			pstmt.setTimestamp(6, cardVO.getCreatedTime());
			pstmt.setBoolean(7, cardVO.getDefaultOption());
			
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
	public void update(CardVO cardVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			
			con = Util.getConnection();
			pstmt = con.prepareStatement(UPDATE_STMT);
			
			pstmt.setInt(1, cardVO.getIdCustomer());
			pstmt.setBoolean(2, cardVO.getType());
			pstmt.setString(3, cardVO.getNumber());
			pstmt.setDate(4, cardVO.getExpiryDate());
			pstmt.setTimestamp(5, cardVO.getCreatedTime());
			pstmt.setBoolean(6, cardVO.getDefaultOption());
			pstmt.setInt(7, cardVO.getIdCard());
			
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
	public void delete(Integer idCard) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			
			con = Util.getConnection();
			pstmt = con.prepareStatement(DELETE_STMT);
			
			pstmt.setInt(1, idCard);
			
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
	public CardVO findByPK(Integer idCard) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		CardVO cardVO = null;
		
		try {
			con = Util.getConnection();
			pstmt = con.prepareStatement(FIND_BY_PK);
			pstmt.setInt(1, idCard);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				cardVO = new CardVO();
				cardVO.setIdCard(rs.getInt("idCard"));
				cardVO.setIdCustomer(rs.getInt("idCustomer"));
				cardVO.setType(rs.getBoolean("type"));
				cardVO.setNumber(rs.getString("number"));
				cardVO.setExpiryDate(rs.getDate("expiryDate"));
				cardVO.setCreatedTime(rs.getTimestamp("createdTime"));
				cardVO.setDefaultOption(rs.getBoolean("defaultOption"));
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
		
		return cardVO;
	}

	@Override
	public List<CardVO> getAll() {
		List<CardVO> cardList = new ArrayList<>();
		CardVO cardVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = Util.getConnection();
			pstmt = con.prepareStatement(GET_ALL);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				
				cardVO = new CardVO();
				cardVO = new CardVO();
				cardVO.setIdCard(rs.getInt("idCard"));
				cardVO.setIdCustomer(rs.getInt("idCustomer"));
				cardVO.setType(rs.getBoolean("type"));
				cardVO.setNumber(rs.getString("number"));
				cardVO.setExpiryDate(rs.getDate("expiryDate"));
				cardVO.setCreatedTime(rs.getTimestamp("createdTime"));
				cardVO.setDefaultOption(rs.getBoolean("defaultOption"));
				cardList.add(cardVO);
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
		
		return cardList;
	}

}

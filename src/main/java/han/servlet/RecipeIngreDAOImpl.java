package han.servlet;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import basicutil.Util;

public class RecipeIngreDAOImpl implements RecipeIngreDAO{
	private static final String INSERT_STMT = "INSERT INTO RECIPEINGRE(IDRECIPEINGRE, IDRECIPE, IDINGRE, INGREQUAN) VALUES (?, ?, ?, ?)";
	private static final String UPDATE_STMT = "UPDATE RECIPEINGRE SET IDRECIPE = ?, IDINGRE = ?, INGREQUAN = ? WHERE IDRECIPEINGRE = ?";
	private static final String DELETE_STMT = "DELETE FROM RECIPEINGRE WHERE IDRECIPEINGRE = ?";
	private static final String FIND_BY_PK = "SELECT * FROM RECIPEINGRE WHERE IDRECIPEINGRE = ?";
	private static final String GET_ALL = "SELECT * FROM RECIPEINGRE";
	

	@Override
	public void insert(RecipeIngreVO recipeIngreVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = Util.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setInt(1,recipeIngreVO.getIdRecipeIngre());
			pstmt.setInt(2,recipeIngreVO.getIdRecipe());
			pstmt.setInt(3,recipeIngreVO.getIdIngre());
			pstmt.setInt(4,recipeIngreVO.getIngreQuan());
			
			
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
	public void update(RecipeIngreVO recipeIngreVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = Util.getConnection();
			pstmt = con.prepareStatement(UPDATE_STMT);

			
			pstmt.setInt(1,recipeIngreVO.getIdRecipe());
			pstmt.setInt(2,recipeIngreVO.getIdIngre());
			pstmt.setInt(3,recipeIngreVO.getIngreQuan());
			pstmt.setInt(4,recipeIngreVO.getIdRecipeIngre());
			
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
	public void delete(int idRecipeIngre) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = Util.getConnection();
			pstmt = con.prepareStatement(DELETE_STMT);

			pstmt.setInt(1, idRecipeIngre);
			
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
	public RecipeIngreVO findByPK(int idRecipeIngre) {
		RecipeIngreVO rcpingre = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = Util.getConnection();
			pstmt = con.prepareStatement(FIND_BY_PK);
			pstmt.setInt(1, idRecipeIngre);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				rcpingre = new RecipeIngreVO();
				rcpingre.setIdRecipeIngre(rs.getInt("IDRECIPEINGRE"));
				rcpingre.setIdRecipe(rs.getInt("IDRECIPE"));
				rcpingre.setIdIngre(rs.getInt("IDINGRE"));
				rcpingre.setIngreQuan(rs.getInt("INGREQUAN"));
				
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

		return rcpingre;
	}

	@Override
	public List<RecipeIngreVO> getAll() {
		List<RecipeIngreVO> rcpingreList = new ArrayList<>();
		RecipeIngreVO rcpingre = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = Util.getConnection();
			pstmt = con.prepareStatement(GET_ALL);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				rcpingre = new RecipeIngreVO();
				rcpingre.setIdRecipeIngre(rs.getInt("IDRECIPEINGRE"));
				rcpingre.setIdRecipe(rs.getInt("IDRECIPE"));
				rcpingre.setIdIngre(rs.getInt("IDINGRE"));
				rcpingre.setIngreQuan(rs.getInt("INGREQUAN"));
				rcpingreList.add(rcpingre);
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
		return rcpingreList;
	}

}

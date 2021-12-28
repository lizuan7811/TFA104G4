package han.RecipeIngre;

import java.util.List;

public class ReceipIngreJDBCTest {
	public static void main(String[] args) {
		RecipeIngreDAO dao = new RecipeIngreDAOImpl();
		
		// 新增
//		RecipeIngreVO rcpingre1 = new RecipeIngreVO();
//		rcpingre1.setIdRecipeIngre(04);
//		rcpingre1.setIdRecipe(03);
//		rcpingre1.setIdIngre(03);
//		rcpingre1.setIngreQuan(01);	
//		dao.insert(rcpingre1);
		
		// 修改
//		RecipeIngreVO rcpingre2 = new RecipeIngreVO();
//		rcpingre2.setIdRecipeIngre(01);
//		rcpingre2.setIdRecipe(55);
//		rcpingre2.setIdIngre(55);
//		rcpingre2.setIngreQuan(55);	
//		dao.update(rcpingre2);
		
		// 刪除
//		dao.delete(4);
		
		//查詢
//		RecipeIngreVO rcpingre3 = dao.findByPK(3);
//		System.out.print(rcpingre3.getIdRecipeIngre() + ",");
//		System.out.print(rcpingre3.getIdRecipe() + ",");
//		System.out.print(rcpingre3.getIdIngre() + ",");
//		System.out.print(rcpingre3.getIngreQuan() + ",");
//		System.out.println("---------------------");
		
		//查詢全部
		List<RecipeIngreVO> list = dao.getAll();
		for (RecipeIngreVO rcpingre : list) {
			System.out.print(rcpingre.getIdRecipeIngre() + ",");
			System.out.print(rcpingre.getIdRecipe() + ",");
			System.out.print(rcpingre.getIdIngre() + ",");
			System.out.print(rcpingre.getIngreQuan() + ",");
			System.out.println();
		}
		
		
	}
}

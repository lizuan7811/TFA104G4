package han.Recipe;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class RecipeJDBCTest {
	public static void main(String[] args) throws IOException {
		RecipeDAO dao = new RecipeDAOImpl();
		
		// 新增
//		RecipeVO rcp1 = new RecipeVO();
//		rcp1.setIdRecipe(5);
//		rcp1.setRecipeName("香煎雞胸肉");
//		rcp1.setDescrip("444444444444444");
//		rcp1.setText("444444444444444444");
//		byte[] pic1 = writePicture("C:\\Users\\Tibame_T14\\Desktop\\img\\香煎雞胸肉.png");
//		rcp1.setPhoto(pic1);
//		dao.insert(rcp1);
		
		// 修改
//		RecipeVO rcp2 = new RecipeVO();
//		rcp2.setIdRecipe(5);
//		rcp2.setRecipeName("123");
//		rcp2.setDescrip("111111");
//		rcp2.setText("2222222");
//		byte[] pic1 = writePicture("C:\\Users\\Tibame_T14\\Desktop\\img\\香煎雞胸肉.png");
//		rcp2.setPhoto(pic1);
//		dao.update(rcp2);
		
		// 刪除
//		dao.delete(4);
		
		//查詢
//		RecipeVO rcp3 = dao.findByPK(5);
//		System.out.print(rcp3.getIdRecipe() + ",");
//		System.out.print(rcp3.getRecipeName() + ",");
//		System.out.print(rcp3.getDescrip() + ",");
//		System.out.print(rcp3.getText() + ",");
//		System.out.print(rcp3.getPhoto() + ",");
//		System.out.println();
//		System.out.println("---------------------");
		
		//查詢全部
		List<RecipeVO> list = dao.getAll();
		for (RecipeVO rcp : list) {
			System.out.print(rcp.getIdRecipe() + ",");
			System.out.print(rcp.getRecipeName() + ",");
			System.out.print(rcp.getDescrip() + ",");
			System.out.print(rcp.getText() + ",");
			System.out.print(rcp.getPhoto() + ",");
			System.out.println();
		}
		
	
	}
	
	
	public static byte[] writePicture(String path) throws IOException {
		FileInputStream fis = new FileInputStream(path);
		byte[] buffer = new byte[fis.available()];
		fis.read(buffer);
		fis.close();
		return buffer;
	}
	
	public static void readPicture(byte[] bytes) throws IOException {
//		File dire = new File("")
		FileOutputStream fos = new FileOutputStream("Output/1.png");
		fos.write(bytes);
		fos.flush();
		fos.close();
	}
}


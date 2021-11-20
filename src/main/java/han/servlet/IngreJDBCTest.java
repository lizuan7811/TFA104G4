package han.servlet;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

//import java.util.List;

public class IngreJDBCTest {
	public static void main(String[] args) throws IOException {
		IngreDAO dao = new IngreDAOImpl();

		
		// 新增
//		IngreVO ingre1 = new IngreVO();
//		ingre1.setIdIngre(107);
//		ingre1.setIdIngreType(1);
//		ingre1.setName("鮭魚切片");
//		ingre1.setPurPrice(90);
//		ingre1.setPrice(179);
//		ingre1.setUnit("片");
//		ingre1.setGran(250);
//		ingre1.setSell(2);
//		ingre1.setDescrip("鮭魚含有豐富的Omega-3脂肪酸，能降低三酸甘油酯，其中的EPA能抗發炎、抗憂鬱、保護心血管、防止血栓形成，DHA則對大腦與眼睛有益處");
//		byte[] pic1 = writePicture("C:\\Users\\Tibame_T14\\Desktop\\img\\香煎雞胸肉.png");
//		ingre1.setPhoto(pic1);
//		ingre1.setLaunch(false);
//		dao.insert(ingre1);
		
		// 修改
//		IngreVO ingre2 = new IngreVO();
//		ingre2.setIdIngre(103);
//		ingre2.setIdIngreType(33333);
//		ingre2.setName("鮭魚切片");
//		ingre2.setPurPrice(999999);
//		ingre2.setPrice(999999);
//		ingre2.setUnit("片");
//		ingre2.setGran(250);
//		ingre2.setSell(2);
//		ingre2.setDescrip("鮭魚含有豐富的Omega-3脂肪酸，能降低三酸甘油酯，其中的EPA能抗發炎、抗憂鬱、保護心血管、防止血栓形成，DHA則對大腦與眼睛有益處");
//		byte[] pic1 = writePicture("C:\\Users\\Tibame_T14\\Desktop\\img\\香煎雞胸肉.png");
//		ingre2.setPhoto(pic1);
//		ingre2.setLaunch(1);
//		dao.update(ingre2);
		
		// 刪除
//		dao.delete(104);
		
		//查詢
//		IngreVO ingre3 = dao.findByPK(101);
//		System.out.print(ingre3.getIdIngre() + ",");
//		System.out.print(ingre3.getIdIngreType() + ",");
//		System.out.print(ingre3.getName() + ",");
//		System.out.print(ingre3.getPurPrice() + ",");
//		System.out.print(ingre3.getPrice() + ",");
//		System.out.print(ingre3.getUnit() + ",");
//		System.out.print(ingre3.getGran() + ",");
//		System.out.print(ingre3.getSell() + ",");
//		System.out.print(ingre3.getDescrip() + ",");
//		System.out.print(ingre3.getPhoto() + ",");
//		System.out.print(ingre3.getLaunch() + ",");
//		System.out.println();
//		System.out.println("---------------------");
		
		//查詢全部
		List<IngreVO> list = dao.getAll();
		for (IngreVO ingre : list) {
			System.out.print(ingre.getIdIngre() + ",");
			System.out.print(ingre.getIdIngreType() + ",");
			System.out.print(ingre.getName() + ",");
			System.out.print(ingre.getPurPrice() + ",");
			System.out.print(ingre.getPrice() + ",");
			System.out.print(ingre.getUnit() + ",");
			System.out.print(ingre.getGran() + ",");
			System.out.print(ingre.getSell() + ",");
			System.out.print(ingre.getDescrip() + ",");
			System.out.print(ingre.getPhoto() + ",");
			System.out.print(ingre.getLaunch() + ",");
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


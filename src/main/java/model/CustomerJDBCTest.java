package model;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

public class CustomerJDBCTest {
	
	public static void main(String[] args) throws IOException {
		CustomerDAO dao = new CustomerDAOImpl();
		
		// add
		CustomerVO cust1 = new CustomerVO();
		cust1.setIdCustomer(1);
		cust1.setName("David");
		byte[] pic1 = writePicture("Input/FC_Barcelona.png");
		cust1.setProfic(pic1);
		cust1.setNickName("大吳");
		cust1.setAccount("daivdWu123");
		cust1.setPassword("password456");
		cust1.setEmail("davidwu@gmail.com");
		cust1.setPhone("0912345678");
		cust1.setCreatedTime(new Timestamp(new java.util.Date().getTime()));
		cust1.setSuspended(false);
		cust1.setExternalAcc(0);
		cust1.setCommentReportedNum(3);
		cust1.setDiaryReportedNum(10);
		dao.insert(cust1);
		System.out.println("新增成功");
		
		// update
		CustomerVO cust2 = new CustomerVO();
		cust2.setIdCustomer(2);
		cust2.setName("William");
		byte[] pic2 = writePicture("Input/FC_Bayern.png");
		cust2.setProfic(pic2);
		cust2.setNickName("小明");
		cust2.setAccount("williamlee999");
		cust2.setPassword("123456789");
		cust2.setEmail("williamlee@gmail.com");
		cust2.setPhone("0987654321");
		cust2.setCreatedTime(new Timestamp(new java.util.Date().getTime() + 1));
		cust2.setSuspended(true);
		cust2.setExternalAcc(1);
		cust2.setCommentReportedNum(2);
		cust2.setDiaryReportedNum(3);
		dao.update(cust2);
		System.out.println("修改成功");
		
		// delete
//		dao.delete(2);
//		System.out.println("刪除成功");
		
		// findByPK
		CustomerVO cust3 = dao.findByPK(1);
		System.out.print(cust3.getIdCustomer() + ",");
		System.out.print(cust3.getName() + ",");
		System.out.print(cust3.getProfic() + ",");
		readPicture(pic1);
		System.out.print(cust3.getNickName() + ",");
		System.out.print(cust3.getAccount() + ",");
		System.out.print(cust3.getPassword() + ",");
		System.out.print(cust3.getEmail() + ",");
		System.out.print(cust3.getPhone() + ",");
		System.out.print(cust3.getCreatedTime() + ",");
		System.out.print(cust3.getSuspended() + ",");
		System.out.print(cust3.getExternalAcc() + ",");
		System.out.print(cust3.getCommentReportedNum() + ",");
		System.out.println(cust3.getDiaryReportedNum());
		System.out.println("---------------------");
		
		// getAll
		List<CustomerVO> list = dao.getAll();
		for (CustomerVO cust : list) {
			System.out.print(cust.getIdCustomer() + ",");
			System.out.print(cust.getName() + ",");
			System.out.print(cust.getProfic() + ",");
			readPicture(pic2);
			System.out.print(cust.getNickName() + ",");
			System.out.print(cust.getAccount() + ",");
			System.out.print(cust.getPassword() + ",");
			System.out.print(cust.getEmail() + ",");
			System.out.print(cust.getPhone() + ",");
			System.out.print(cust.getCreatedTime() + ",");
			System.out.print(cust.getSuspended() + ",");
			System.out.print(cust.getExternalAcc() + ",");
			System.out.print(cust.getCommentReportedNum() + ",");
			System.out.print(cust.getDiaryReportedNum());
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
		FileOutputStream fos = new FileOutputStream("Output/1.png");
		fos.write(bytes);
		fos.flush();
		fos.close();
	}

}

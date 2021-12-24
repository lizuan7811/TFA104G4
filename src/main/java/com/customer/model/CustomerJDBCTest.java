package com.customer.model;


import java.io.FileInputStream;

import java.io.IOException;
import java.sql.Timestamp;

public class CustomerJDBCTest {

	public static void main(String[] args) throws IOException {
		Timestamp now = new Timestamp(new java.util.Date().getTime());
		writeCustVO(0, "Default", "", "default", "Default123!@#", "default@gmail.com", "0900000000", false, "default_profic.png", now, true, false, 0, "", 0, 0);
		writeCustVO(1, "David", "", "test", "Test123!", "davidwu@gmail.com", "0912345678", true, "profic_1.png", now, false, true, 1, "sdwewe", 3, 0);
		writeCustVO(2, "William", "小明", "williamlee999", "Ab4@4k12", "williamlee@gmail.com", "0987654321", true, "default_profic.png", now, false, true, 2, "DWE2E12", 5, 0);
		writeCustVO (3, "Peter", "大吳", "peterBigWU", "!@#Bj223", "peterbigwu@gmail.com", "0934725912", true, "profic_3.png", now, false, false, 0, null, 9, 10);
		writeCustVO(4, "何嘉方", "", "DVW48095", "Wk*&2mw", "DVW48095@yahoo.com.tw", "0965248546", true, "default_profic.png", now, false, false, 0, null, 12, 2);
		writeCustVO(5, "李祐仁", "加法真是太神奇了", "Uhw12#ew", "Ccc60793", "Ugg41027@yahoo.comw", "0915329500", true, "profic_5.png", now, false, true, 1, "DSDSDW", 7, 10);
		writeCustVO(6, "劉家湄", "", "Dxx29942%", "22863-8@3", "Dxx29942@yahoo.com", "0968295193", false, "profic_6.png", now, false, true, 3, "skow", 0, 0);
		writeCustVO(7, "張雅雯", "說愛你", "DcW48095*", "W4058|4~6", "DVW48095@yahoo.com.tw", "0965248546", false, "profic_7.png", now, false, false, 0, null, 12, 2);
		writeCustVO(8, "林承瀚", "", "MAJhhb18592^%", "hhe58882", "MAJhhb18592@yahoo.com", "0913531450", false, "profic_8.png", now, false, true, 1, "fju", 0, 0);
		writeCustVO(9, "施博禹", "離開地球表面", "uu30556Q$", "T81^5~9$4", "uu30556@hotmail.co", "0924288395", false, "profic_9.png", now, false, false, 2, "72384hu", 5, 0);
		writeCustVO(10, "張芯玟", "小鹿斑比", "UYiiyyu31361&", "R52162%8", "UYiiyyu31361@hotmail.com", "0924816281", false, "profic_10.png", now, false, false, 0, null, 2, 2);

	}
		// add
	
	public static void writeCustVO (Integer idCustomer, String name, String nickname, String account, 
			String password, String email, String phone, Boolean notification, String proficName, Timestamp createdTime, Boolean activated, Boolean suspended, 
			Integer externalAcc, String externalIdToken, Integer commentReportedNum, Integer diaryReportedNum) throws IOException {
		
		CustomerVO cust = new CustomerVO();
		
		cust.setIdCustomer(idCustomer);
		cust.setName(name);
		cust.setNickname(nickname);
		cust.setAccount(account);
		cust.setPassword(password);
		cust.setEmail(email);
		cust.setPhone(phone);
		cust.setNotification(notification);
		byte[] pic = writePicture("./WebContent/resource/profic/" + proficName);
		cust.setProfic(pic);
		cust.setCreatedTime(createdTime);
		cust.setActivated(activated);
		cust.setSuspended(suspended);
		cust.setExternalAcc(externalAcc);
		cust.setExternalIdToken(externalIdToken);
//		cust.setCommentReportedNum(commentReportedNum);
//		cust.setDiaryReportedNum(diaryReportedNum);
		new CustomerDAOImpl().insertCustByAdmin(cust);
		System.out.println("cust" + idCustomer + "新增成功");
	}	
//		CustomerVO cust2 = new CustomerVO();
//		cust2.setIdCustomer(2);
//		cust2.setName("Willim");
//		byte[] pic2 = writePicture("WebContent/input/profic/profic_2.png");
//		cust2.setProfic(pic2);
//		cust2.setNickName("小明");
//		cust2.setAccount("williamlee999");
//		cust2.setPassword("123456789");
//		cust2.setEmail("williamlee@gmail.com");
//		cust2.setPhone("09876543218");
//		cust2.setNotification(true);
//		cust2.setCreatedTime(new Timestamp(new java.util.Date().getTime()));
//		cust2.setSuspended(false);
//		cust2.setExternalAcc(2);
//		cust2.setExternalIdToken("ewew ew");
//		cust2.setCommentReportedNum(3);
//		cust2.setDiaryReportedNum(10);
//		dao.insert(cust2);
//		System.out.println("新增cust2成功");
//		
//		CustomerVO cust3 = new CustomerVO();
//		cust3.setIdCustomer(3);
//		cust3.setName("Peter");
//		byte[] pic3 = writePicture("WebContent/input/profic/profic_3.png");
//		cust3.setProfic(pic3);
//		cust3.setNickName("大吳");
//		cust3.setAccount("peterBigWu");
//		cust3.setPassword("!@#$QWER");
//		cust3.setEmail("peterbigwu@gmail.com");
//		cust3.setPhone("0934725912");
//		cust3.setNotification(false);
//		cust3.setCreatedTime(new Timestamp(new java.util.Date().getTime()));
//		cust3.setSuspended(false);
//		cust3.setExternalAcc(0);
//		cust3.setExternalIdToken("");
//		cust3.setCommentReportedNum(0);
//		cust3.setDiaryReportedNum(4);
//		dao.insert(cust3);
//		System.out.println("新增cust3成功");
//		
//		
//		CustomerVO cust4 = new CustomerVO();
//		cust4.setIdCustomer(4);
//		cust4.setName("何嘉方");
//		byte[] pic4 = writePicture("WebContent/input/profic/profic_4.png");
//		cust4.setProfic(pic4);
//		cust4.setNickName("玻璃星");
//		cust4.setAccount("DVW48095");
//		cust4.setPassword("W4058|4~6");
//		cust4.setEmail("DVW48095@yahoo.com.tw");
//		cust4.setPhone("0965248546");
//		cust4.setNotification(false);
//		cust4.setCreatedTime(new Timestamp(new java.util.Date().getTime()));
//		cust4.setSuspended(true);
//		cust4.setExternalAcc(0);
//		cust4.setExternalIdToken("");
//		cust4.setCommentReportedNum(0);
//		cust4.setDiaryReportedNum(4);
//		dao.insert(cust4);
//		System.out.println("新增cust4成功");
//		
//		CustomerVO cust5 = new CustomerVO();
//		cust5.setIdCustomer(5);
//		cust5.setName("劉家湄");
//		byte[] pic5 = writePicture("WebContent/input/profic/profic_5.png");
//		cust5.setProfic(pic5);
//		cust5.setNickName("美食小當家");
//		cust5.setAccount("Dxx29942");
//		cust5.setPassword("22863-8@3");
//		cust5.setEmail("pDxx29942@yahoo.com");
//		cust5.setPhone("0968295193");
//		cust5.setNotification(true);
//		cust5.setCreatedTime(new Timestamp(new java.util.Date().getTime()));
//		cust5.setSuspended(false);
//		cust5.setExternalAcc(2);
//		cust5.setExternalIdToken("e2e2");
//		cust5.setCommentReportedNum(0);
//		cust5.setDiaryReportedNum(4);
//		dao.insert(cust5);
//		System.out.println("新增cust5成功");
//		
//		// update
//		CustomerVO cust2 = new CustomerVO();
//		cust2.setIdCustomer(2);
//		cust2.setName("William");
//		byte[] pic2 = writePicture("Input/profic_05.png");
//		System.out.println(new File("Input/profic_05.png").getParent());
//		
//		cust2.setProfic(pic2);
//		cust2.setNickName("小明");
//		cust2.setAccount("williamlee999");
//		cust2.setPassword("123456789");
//		cust2.setEmail("williamlee@gmail.com");
//		cust2.setPhone("0987654321");
//		cust2.setCreatedTime(new Timestamp(new java.util.Date().getTime() + 1));
//		cust2.setSuspended(true);
//		cust2.setExternalAcc(1);
//		cust2.setExternalIdToken("e2e1e22");
//		cust2.setCommentReportedNum(2);
//		cust2.setDiaryReportedNum(3);
//		dao.update(cust2);
//		System.out.println("修改成功");
//		
//		// delete
////		dao.delete(2);
////		System.out.println("刪除成功");
//		
		// findByPK
//		CustomerVO cust6 = dao.findByPK(1);
//		System.out.print(cust3.getIdCustomer() + ",");
//		System.out.print(cust3.getName() + ",");
//		System.out.print(cust3.getProfic() + ",");
//		readPicture(pic1);
//		System.out.print(cust3.getNickName() + ",");
//		System.out.print(cust3.getAccount() + ",");
//		System.out.print(cust3.getPassword() + ",");
//		System.out.print(cust3.getEmail() + ",");
//		System.out.print(cust3.getPhone() + ",");
//		System.out.print(cust3.getCreatedTime() + ",");
//		System.out.print(cust3.getSuspended() + ",");
//		System.out.print(cust3.getExternalAcc() + ",");
//		System.out.print(cust3.getExternalIdToken() + ", ");
//		System.out.print(cust3.getCommentReportedNum() + ",");
//		System.out.println(cust3.getDiaryReportedNum());
//		System.out.println("---------------------");
		
//		// getAll
//		List<CustomerVO> list = dao.getAll();
//		for (CustomerVO cust : list) {
//			System.out.print(cust.getIdCustomer() + ",");
//			System.out.print(cust.getName() + ",");
//			System.out.print(cust.getProfic() + ",");
//			readPicture(pic2);
//			System.out.print(cust.getNickName() + ",");
//			System.out.print(cust.getAccount() + ",");
//			System.out.print(cust.getPassword() + ",");
//			System.out.print(cust.getEmail() + ",");
//			System.out.print(cust.getPhone() + ",");
//			System.out.print(cust.getCreatedTime() + ",");
//			System.out.print(cust.getSuspended() + ",");
//			System.out.print(cust.getExternalAcc() + ",");
//			System.out.print(cust3.getExternalAcc() + ",");
//			System.out.print(cust.getCommentReportedNum() + ",");
//			System.out.print(cust.getDiaryReportedNum());
//			System.out.println();
//		}
//	}

//	
	public static byte[] writePicture(String path) throws IOException {
//		System.out.println(dir.getAbsolutePath());
		FileInputStream fis = new FileInputStream(path);
		byte[] pic = new byte[fis.available()];
		fis.read(pic);
		fis.close();
		return pic;
	}
	
//	public static void readPicture(byte[] bytes) throws IOException {
//		FileOutputStream fos = new FileOutputStream("Output/1.png");
//		fos.write(bytes);
//		fos.flush();
//		fos.close();
//	}

//	File dir = new File("Input");
//	String[] names = dir.list();
//	for (String str : names) {
//		File file = new File(dir, str);
//		FileInputStream fis = new FileInputStream(file);
//		byte[] pic = new byte[fis.available()];
//		fis.read(pic);	
//	}
}

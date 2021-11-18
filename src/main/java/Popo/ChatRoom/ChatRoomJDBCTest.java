package po;

import java.util.List;

public class ChatRoomJDBCTest {
	public static void main(String[] args) {
		ChatRoomDAO dao = new ChatRoomDAOImpl();

//		新增
		ChatRoomVO chat1 = new ChatRoomVO();
		chat1.setmesgID(7017);
		chat1.setcustID(1001);
		chat1.setBoolean(true);
		chat1.setcreatTime(java.sql.Date.valueOf("2016-01-01"));
		chat1.setmessage("ppppp");
		dao.add(chat1);

//		// 修改
//		ChatRoomVO chat2 = new ChatRoomVO();
//		chat2.setmesgID(7016);
//		chat2.setcustID(1002);
//		chat2.setBoolean(false);
//		chat2.setcreatTime(java.sql.Date.valueOf("2016-08-07"));
//		chat2.setmessage("ccccc");
//		dao.update(chat2);
//
////		// 刪除
//		dao.delete(7087);
////
////		// 查詢
//		ChatRoomVO chat3 = dao.findByPK(7016);
//		System.out.print(chat3.getmesgID() + ",");
//		System.out.print(chat3.getcustID() + ",");
//		System.out.print(chat3.getCustMesg() + ",");
//		System.out.print(chat3.getCreatTime() + ",");
//		System.out.print(chat3.getmessage());
//		System.out.println("---------------------");
//
////		// 查詢
//		List<ChatRoomVO> list = dao.getAll();
//		for (ChatRoomVO chat : list) {
//			System.out.print(chat.getmesgID() + ",");
//			System.out.print(chat.getcustID() + ",");
//			System.out.print(chat.getCustMesg() + ",");
//			System.out.print(chat.getCreatTime() + ",");
//			System.out.print(chat.getmessage());
//			System.out.println();
//		}
	}
}



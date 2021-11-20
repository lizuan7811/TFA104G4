package popo.ChatRoom;

import java.util.List;

public class ChatRoomJDBCTest {
	public static void main(String[] args) {
		ChatRoomDAO dao = new ChatRoomDAOImpl();

//		新增
		ChatRoomVO chat1 = new ChatRoomVO();
		chat1.setMesgID(7017);
		chat1.setCustID(1001);
		chat1.setBoolean(true);
		chat1.setCreatedTime(java.sql.Date.valueOf("2016-01-01"));
		chat1.setMessage("ppppp");
		dao.insert(chat1);

//		// 修改
		ChatRoomVO chat2 = new ChatRoomVO();
		chat2.setMesgID(7016);
		chat2.setCustID(1002);
		chat2.setBoolean(false);
		chat2.setCreatedTime(java.sql.Date.valueOf("2016-08-07"));
		chat2.setMessage("ccccc");
		dao.update(chat2);
//
////		// 刪除
		dao.delete(7087);
////
////		// 查詢
		ChatRoomVO chat3 = dao.findByPK(7016);
		System.out.print(chat3.getMesgID() + ",");
		System.out.print(chat3.getCustID() + ",");
		System.out.print(chat3.getCustMesg() + ",");
		System.out.print(chat3.getCreatedTime() + ",");
		System.out.print(chat3.getMessage());
		System.out.println("---------------------");

////		// 查詢
		List<ChatRoomVO> list = dao.getAll();
		for (ChatRoomVO chat : list) {
			System.out.print(chat.getMesgID() + ",");
			System.out.print(chat.getCustID() + ",");
			System.out.print(chat.getCustMesg() + ",");
			System.out.print(chat.getCreatedTime() + ",");
			System.out.print(chat.getMessage());
			System.out.println();
		}
	}
}



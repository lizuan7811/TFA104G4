package popo.FriendChat;

import java.util.List;

public class FriendChatJDBCTest {
	public static void main(String[] args) {
		FriendChatDAO dao = new FriendChatDAOImpl();

//		新增
		FriendChatVO fric = new FriendChatVO();
		fric.setFriendChatID(7016);
		fric.setCustID(1001);
		fric.setMyFriendID(1111);
		fric.setChatText("popo");
		fric.setCreatedTime(java.sql.Date.valueOf("2016-01-01"));
		dao.insert(fric);

////		// 修改
		FriendChatVO fric2 = new FriendChatVO();
		fric2.setFriendChatID(7016);
		fric2.setCustID(1002);
		fric2.setMyFriendID(1111);
		fric2.setChatText("popo");
		fric2.setCreatedTime(java.sql.Date.valueOf("2016-01-01"));
		dao.update(fric2);

////		// 刪除
//		dao.delete(7016);

////		// 查詢
		FriendChatVO fric3 = dao.findByPK(7016);
		System.out.print(fric3.getFriendChatID() + ",");
		System.out.print(fric3.getCustID() + ",");
		System.out.print(fric3.getMyFriendID() + ",");
		System.out.print(fric3.getChatText() + ",");
		System.out.print(fric3.getCreatedTime());
		System.out.println("---------------------");

//		// 查詢
		List<FriendChatVO> list = dao.getAll();
		for (FriendChatVO fri4 : list) {
			System.out.print(fri4.getFriendChatID() + ",");
			System.out.print(fri4.getCustID() + ",");
			System.out.print(fri4.getMyFriendID() + ",");
			System.out.print(fri4.getChatText() + ",");
			System.out.print(fri4.getCreatedTime());
			System.out.println();
		}
	}
}

package popo.Friend;

import java.util.List;

public class FriendJDBCTest {
	public static void main(String[] args) {
		FriendDAO dao = new FriendDAOImpl();

//		新增
		FriendVO fri = new FriendVO();
		fri.setFriendChatID(7016);
		fri.setCustID(1001);
		fri.setMyFriendID(1111);
		fri.setFriendStatusNum(1);
		fri.setStatusUpdate(java.sql.Date.valueOf("2016-01-01"));
		dao.insert(fri);

////		// 修改
		FriendVO fri2 = new FriendVO();
		fri2.setFriendChatID(7016);
		fri2.setCustID(1002);
		fri2.setMyFriendID(1111);
		fri2.setFriendStatusNum(2);
		fri2.setStatusUpdate(java.sql.Date.valueOf("2016-01-01"));
		dao.update(fri2);

////		// 刪除
		dao.delete(7016);
////
////		// 查詢
		FriendVO fri3 = dao.findByPK(7016);
		System.out.print(fri3.getFriendChatID() + ",");
		System.out.print(fri3.getCustID() + ",");
		System.out.print(fri3.getMyFriendID() + ",");
		System.out.print(fri3.getFriendStatusNum() + ",");
		System.out.print(fri3.getStatusUpdate());
		System.out.println("---------------------");

//		// 查詢
		List<FriendVO> list = dao.getAll();
		for (FriendVO fri4 : list) {
			System.out.print(fri4.getFriendChatID() + ",");
			System.out.print(fri4.getCustID() + ",");
			System.out.print(fri4.getMyFriendID() + ",");
			System.out.print(fri4.getFriendStatusNum() + ",");
			System.out.print(fri4.getStatusUpdate());
			System.out.println();
		}
	}
}

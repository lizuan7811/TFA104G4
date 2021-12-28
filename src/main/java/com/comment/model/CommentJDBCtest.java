package com.comment.model;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

public class CommentJDBCtest {

	public static void main(String[] args) {
		CommentDAO dao = new CommentDAOimpl();
		
		//新增
		CommentVO com_1 = new CommentVO();
		com_1.setCommentID(7);
		com_1.setDiaryID(2);
		com_1.setNickName("多多");
		com_1.setCreatedTime(Timestamp.valueOf(LocalDateTime.now()));
		com_1.setCommentText("寫得不錯喔~");
		com_1.setCommentStatus(false);
		
		dao.insert(com_1);
		System.out.println("新增成功");
		
		// 修改
		CommentVO com_2 = new CommentVO();
		com_2.setCommentID(7);
		com_2.setDiaryID(2);
		com_2.setNickName("蚊子");
		com_2.setCreatedTime(Timestamp.valueOf(LocalDateTime.now()));
		com_2.setCommentText("寫得不錯喔~可以交個朋友嗎?");
		com_2.setCommentStatus(false);
		
		dao.update(com_2);
		System.out.println("修改成功");

		// 刪除
//		dao.delete(7);
//		System.out.println("刪除成功");
		
		// 查詢
		CommentVO com_3 = dao.findByPrimaryKey(1);
		System.out.println("留言ID = " + com_3.getCommentID());
		System.out.println("日誌ID = " + com_3.getDiaryID());
		System.out.println("會員ID = " + com_3.getNickName());
		System.out.println("創建時間 = " + com_3.getCreatedTime());
		System.out.println("留言內容 = " + com_3.getCommentText());
		System.out.println("留言檢舉狀態 = " + com_3.getCommentStatus());
		System.out.println("---------------------");
		
		// 查詢全部
		List<CommentVO> list = dao.getAll();
		for (CommentVO all_comment : list) {
			System.out.println("留言ID = " + all_comment.getCommentID());
			System.out.println("日誌ID = " + all_comment.getDiaryID());
			System.out.println("會員ID = " + all_comment.getNickName());
			System.out.println("創建時間 = " + all_comment.getCreatedTime());
			System.out.println("留言內容 = " + all_comment.getCommentText());
			System.out.println("留言檢舉狀態 = " + all_comment.getCommentStatus());
			System.out.println();
		}
		
		//查詢單一日誌中的所有留言
		List<CommentVO> commentlist = dao.getOneDiaryComment(1) ;
		for (CommentVO all_comment : commentlist) {
			System.out.println("留言ID = " + all_comment.getCommentID());
			System.out.println("日誌ID = " + all_comment.getDiaryID());
			System.out.println("會員ID = " + all_comment.getNickName());
			System.out.println("創建時間 = " + all_comment.getCreatedTime());
			System.out.println("留言內容 = " + all_comment.getCommentText());
			System.out.println("留言檢舉狀態 = " + all_comment.getCommentStatus());
			System.out.println();
		}

	}

}

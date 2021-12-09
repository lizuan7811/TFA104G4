package com.fooddiary.model;

import java.io.*;
import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;


public class FoodDiaryJDBCtest {

	public static void main(String[] args) throws IOException {
		
		FoodDiaryDAO dao = new FoodDiaryDAOimpl();
		
		//新增
//		FoodDiaryVO fd_1 = new FoodDiaryVO();
//		fd_1.setDiaryID(4);
//		fd_1.setCustID(6);
//		fd_1.setSubject("標題-這是我的新文章");
//		fd_1.setText("文章內容");
//		fd_1.setCreatedTime(Timestamp.valueOf(LocalDateTime.now()));
//		byte[] pic = getPhoto("WebContent/resources/items/logo.png"); //新增的圖片
//		fd_1.setPhoto_video_1(pic);
//		fd_1.setPhoto_video_2(pic);
//		fd_1.setPhoto_video_3(pic);
		
//		fd_1.setStatus(false);
//		fd_1.setForumLikeNum(10);
//		fd_1.setDiaryStatus(false);
//		fd_1.setDiaryType(7);
//		dao.insert(fd_1);
//		System.out.println("文章新增成功");
		
		//修改
//		FoodDiaryVO fd_2 = new FoodDiaryVO();
//		fd_2.setDiaryID(4);
//		fd_2.setCustID(6);
//		fd_2.setSubject("標題-修改後的文章");
//		fd_2.setText("修改後的文章內容");
//		fd_2.setCreatedTime(Timestamp.valueOf(LocalDateTime.now()));
//		
//		byte[] pic_2 = getPhoto("items/toby.jpg"); //新增的圖片
//		fd_2.setPhoto_video_1(pic_2);
//		fd_2.setPhoto_video_2(pic_2);
//		fd_2.setPhoto_video_3(pic_2);	
//		fd_2.setStatus(false);
//		fd_2.setForumLikeNum(3);
//		fd_2.setDiaryStatus(false);
//		fd_2.setDiaryType(1);
//		
//		dao.update(fd_2);
//		System.out.println("文章修改成功");
		
		// 刪除
//		dao.delete(4);
//		System.out.println("文章刪除成功");
		
		// 查詢
//		FoodDiaryVO fd_3 = dao.findByPrimaryKey(1);
//		System.out.println("日誌編號 = " + fd_3.getDiaryID());
//		System.out.println("會員ID = " + fd_3.getCustID());
//		System.out.println("日誌標題 = " + fd_3.getSubject());
//		System.out.println("日誌文章 = " + fd_3.getText());
//		System.out.println("創建時間 = " + fd_3.getCreatedTime());
//		System.out.println("圖片/影片檔案1 = " + fd_3.getPhoto_video_1());
//		System.out.println("圖片/影片檔案2 = " + fd_3.getPhoto_video_2());
//		System.out.println("圖片/影片檔案3 = " + fd_3.getPhoto_video_3());
//		System.out.println("公開狀態 = " + fd_3.getStatus());
//		System.out.println("按讚數 = " + fd_3.getForumLikeNum());
//		System.out.println("日誌檢舉狀態 = " + fd_3.getDiaryStatus());
//		System.out.println("日誌分類 = " + fd_3.getDiaryType());	
//		System.out.println("---------------------");
		
		// 查詢全部
//		List<FoodDiaryVO> list = dao.getAll();
//		for (FoodDiaryVO all_diary : list) {
//			System.out.println("日誌編號 = " + all_diary.getDiaryID());
//			System.out.println("會員ID = " + all_diary.getCustID());
//			System.out.println("日誌標題 = " + all_diary.getSubject());
//			System.out.println("日誌文章 = " + all_diary.getText());
//			System.out.println("創建時間 = " + all_diary.getCreatedTime());
//			System.out.println("圖片/影片檔案1 = " + all_diary.getPhoto_video_1());
//			System.out.println("圖片/影片檔案2 = " + all_diary.getPhoto_video_2());
//			System.out.println("圖片/影片檔案3 = " + all_diary.getPhoto_video_3());
//			System.out.println("公開狀態 = " + all_diary.getStatus());
//			System.out.println("按讚數 = " + all_diary.getForumLikeNum());
//			System.out.println("日誌檢舉狀態 = " + all_diary.getDiaryStatus());
//			System.out.println("日誌分類 = " + all_diary.getDiaryType());	
//			System.out.println();
//		}
		
		// 個人日誌查詢
				List<FoodDiaryVO> fd_4 = dao.get_LIST_ONE(1);
				for (FoodDiaryVO my_diary : fd_4) {
				System.out.println("日誌編號 = " + my_diary.getDiaryID());
				System.out.println("會員ID = " + my_diary.getCustID());
				System.out.println("日誌標題 = " + my_diary.getSubject());
				System.out.println("日誌文章 = " + my_diary.getText());
				System.out.println("創建時間 = " + my_diary.getCreatedTime());
				System.out.println("圖片/影片檔案1 = " + my_diary.getPhoto_video_1());
				System.out.println("圖片/影片檔案2 = " + my_diary.getPhoto_video_2());
				System.out.println("圖片/影片檔案3 = " + my_diary.getPhoto_video_3());
				System.out.println("公開狀態 = " + my_diary.getStatus());
				System.out.println("按讚數 = " + my_diary.getForumLikeNum());
				System.out.println("日誌檢舉狀態 = " + my_diary.getDiaryStatus());
				System.out.println("日誌分類 = " + my_diary.getDiaryType());	
				System.out.println("---------------------");
				}
		
	}
	
	// 使用byte[] input items檔案夾中的圖片
	public static byte[] getPhoto(String items_path) throws IOException {
		FileInputStream pic = new FileInputStream(items_path);
		byte[] buffer = new byte[pic.available()];
		pic.read(buffer);
		pic.close();
		return buffer;
	}
	
}	

	
	



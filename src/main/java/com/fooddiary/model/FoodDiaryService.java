package com.fooddiary.model;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import org.json.JSONArray;


public class FoodDiaryService {
	private FoodDiaryDAO dao;
	
	public FoodDiaryService() {
		dao = new FoodDiaryDAOimpl();
	}
	
	//新增
	public FoodDiaryVO add_diary(Integer custID, String subject, String text, byte[] photo_video_1, byte[] photo_video_2, byte[] photo_video_3, Boolean status,Integer diaryType, Timestamp createdTime) 
	{
		
		FoodDiaryVO diaryVO = new FoodDiaryVO();
		
		diaryVO.setCustID(custID);
		diaryVO.setSubject(subject);
		diaryVO.setText(text);
		diaryVO.setPhoto_video_1(photo_video_1);
		diaryVO.setPhoto_video_2(photo_video_2);
		diaryVO.setPhoto_video_3(photo_video_3);
		diaryVO.setStatus(status);
		diaryVO.setDiaryType(diaryType);
		diaryVO.setCreatedTime(createdTime);
		
		dao.insert(diaryVO);

		return diaryVO;
		
	}
	
	//修改
	public FoodDiaryVO update_diary(Integer diaryID, Integer custID, String subject, String text, byte[] photo_video_1, byte[] photo_video_2, byte[] photo_video_3, Boolean status,Integer diaryType, Timestamp createdTime) 
	{
		
		FoodDiaryVO diaryVO = new FoodDiaryVO();
		
		diaryVO.setDiaryID(diaryID);
		diaryVO.setCustID(custID);
		diaryVO.setSubject(subject);
		diaryVO.setText(text);
		diaryVO.setPhoto_video_1(photo_video_1);
		diaryVO.setPhoto_video_2(photo_video_2);
		diaryVO.setPhoto_video_3(photo_video_3);
		diaryVO.setStatus(status);
		diaryVO.setDiaryType(diaryType);
		diaryVO.setCreatedTime(createdTime);
		
		dao.update(diaryVO);

		return diaryVO;
		
	}

	//刪除
	public void deleteDiary(Integer diaryID) {
		dao.delete(diaryID);
	}

	//取得單一日誌 diary_page.jsp
	public FoodDiaryVO getOneDiary(Integer diaryID) {
		return dao.findByPrimaryKey(diaryID);
	}

	//取得全部日誌back_end_diary_manage.jsp
	public List<FoodDiaryVO> getAll() {
		return dao.getAll();
	}
	
	//取得單一會員的所有日誌
	public List<FoodDiaryVO> get_LIST_ONE(Integer custID){
		return dao.get_LIST_ONE(custID);
	
	}
	
	//食健日誌搜尋"全部"的文章 group4_diary.html
	public JSONArray searchAll() {
		return dao.searchAll();
	}
	
	//食健日誌搜尋不同"分類"的文章 group4_diary.html
	public JSONArray searchType(Integer diaryType) {
		return dao.searchType(diaryType);
	}
}

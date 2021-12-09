package com.fooddiary.model;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;


public class FoodDiaryService {
	private FoodDiaryDAO dao;
	
	public FoodDiaryService() {
		dao = new FoodDiaryDAOimpl();
	}
	
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

	public void deleteDiary(Integer diaryID) {
		dao.delete(diaryID);
	}

	public FoodDiaryVO getOneDiary(Integer diaryID) {
		return dao.findByPrimaryKey(diaryID);
	}

	public List<FoodDiaryVO> getAll() {
		return dao.getAll();
	}
	
	public List<FoodDiaryVO> get_LIST_ONE(Integer custID){
		return dao.get_LIST_ONE(custID);
	
	}
}

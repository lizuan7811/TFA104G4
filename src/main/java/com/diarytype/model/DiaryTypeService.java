package com.diarytype.model;

import java.util.List;
import java.util.Set;

import com.fooddiary.model.FoodDiaryDAOimpl;

public class DiaryTypeService {
	
	private DiaryTypeDAO dao;
	
	public DiaryTypeService() {
		dao = new DiaryTypeDAOimpl();
	}
	
	public List<DiaryTypeVO> getAll() {
		return dao.getAll();
	}

	public DiaryTypeVO getOneType(Integer diarytypeID) {
		return dao.findByPrimaryKey(diarytypeID);
	}

//	public Set<DiaryTypeVO> getDiaryByTypeID(Integer diarytypeID) {
//		return dao.getDiaryByTypeID(diarytypeID);
//	}

	public void deleteDiaryType(Integer diarytypeID) {
		dao.delete(diarytypeID);
	}
	

}

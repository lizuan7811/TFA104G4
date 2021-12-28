package com.diarytype.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

	//取得 typeID和TypeName
	public Map<Integer, String> getMap() {
		Map<Integer, String> map = new HashMap<>();
//		Map<Integer,Map<Integer,String>>hh=new HashMap<>(); 
		for (DiaryTypeVO vo : getAll()) {
			map.put(vo.getDiaryTypeID(), vo.getDiaryTypeName());
		}
		return map;
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

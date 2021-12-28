package com.fooddiary.model;

import java.util.*;

import org.json.JSONArray;

public interface FoodDiaryDAO {
	//定義對資料庫的相關存取抽象方法 新增 | 更改 | 刪除 | PK查詢 | 查詢
	public void insert(FoodDiaryVO foodDiaryVO);
	public void update(FoodDiaryVO foodDiaryVO);
	public void delete(Integer diaryID);
	public FoodDiaryVO findByPrimaryKey(Integer diaryID);
	public List<FoodDiaryVO> getAll();
	public List<FoodDiaryVO> get_LIST_ONE(Integer custID);
	public JSONArray searchAll(); //食健日誌搜尋"全部"的文章 group4_diary.html
	public JSONArray searchType(Integer diaryType); //食健日誌搜尋不同"分類"的文章 group4_diary.html
}

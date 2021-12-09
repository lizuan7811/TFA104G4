package com.diarytype.model;

import java.util.*;

public interface DiaryTypeDAO {
	//定義對資料庫的相關存取抽象方法 新增 | 更改 | 刪除 | PK查詢 | 查詢
	public void insert(DiaryTypeVO diaryTypeVO);
    public void update(DiaryTypeVO diaryTypeVO);
    public void delete(Integer diaryTypeID);
    public DiaryTypeVO findByPrimaryKey(Integer diaryTypeID);
    public List<DiaryTypeVO> getAll();
}

package com.diarytype.model;

import java.io.Serializable;

public class DiaryTypeVO implements Serializable {
	
	private Integer diaryTypeID; //日誌分類ID
	private String diaryTypeName; //日誌類別名稱

	public DiaryTypeVO() {
		
	}

	public DiaryTypeVO(Integer diaryTypeID, String diaryTypeName) {
		super();
		this.diaryTypeID = diaryTypeID;
		this.diaryTypeName = diaryTypeName;
	}

	public Integer getDiaryTypeID() {
		return diaryTypeID;
	}

	public void setDiaryTypeID(Integer diaryTypeID) {
		this.diaryTypeID = diaryTypeID;
	}

	public String getDiaryTypeName() {
		return diaryTypeName;
	}

	public void setDiaryTypeName(String diaryTypeName) {
		this.diaryTypeName = diaryTypeName;
	}

}

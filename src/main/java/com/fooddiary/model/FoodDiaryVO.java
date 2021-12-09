package com.fooddiary.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class FoodDiaryVO implements Serializable{

	private Integer diaryID; //日誌ID
	private Integer custID; //會員ID
	private String subject; //日誌標題
	private String text; //日誌文章
	private Timestamp createdTime; //創建時間
	private byte[] photo_video_1; //圖片_影片_檔案1
	private byte[] photo_video_2; //圖片_影片_檔案2
	private byte[] photo_video_3; //圖片_影片_檔案3
	private Boolean status; //公開狀態
	private Integer forumLikeNum; //按讚數
	private Boolean diaryStatus; //日誌檢舉狀態
	private Integer diaryType; //日誌分類
	
	public FoodDiaryVO() {
		super();
	}
	
	public FoodDiaryVO(Integer diaryID, Integer custID, String subject, String text, Timestamp createdTime,
			byte[] photo_video_1, byte[] photo_video_2, byte[] photo_video_3, Boolean status, Integer forumLikeNum,
			Boolean diaryStatus, Integer diaryType) {
		super();
		this.diaryID = diaryID;
		this.custID = custID;
		this.subject = subject;
		this.text = text;
		this.createdTime = createdTime;
		this.photo_video_1 = photo_video_1;
		this.photo_video_2 = photo_video_2;
		this.photo_video_3 = photo_video_3;
		this.status = status;
		this.forumLikeNum = forumLikeNum;
		this.diaryStatus = diaryStatus;
		this.diaryType = diaryType;
	}
	
	public Integer getDiaryID() {
		return diaryID;
	}

	public void setDiaryID(Integer diaryID) {
		this.diaryID = diaryID;
	}

	public Integer getCustID() {
		return custID;
	}

	public void setCustID(Integer custID) {
		this.custID = custID;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Timestamp getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Timestamp createdTime) {
		this.createdTime = createdTime;
	}

	public byte[] getPhoto_video_1() {
		return photo_video_1;
	}

	public void setPhoto_video_1(byte[] setPhoto_video_1) {
		this.photo_video_1 = setPhoto_video_1;
	}

	public byte[] getPhoto_video_2() {
		return photo_video_2;
	}

	public void setPhoto_video_2(byte[] setPhoto_video_2) {
		this.photo_video_2 = setPhoto_video_2;
	}

	public byte[] getPhoto_video_3() {
		return photo_video_3;
	}

	public void setPhoto_video_3(byte[] photo_video_3) {
		this.photo_video_3 = photo_video_3;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public Integer getForumLikeNum() {
		return forumLikeNum;
	}

	public void setForumLikeNum(Integer forumLikeNum) {
		this.forumLikeNum = forumLikeNum;
	}

	public Boolean getDiaryStatus() {
		return diaryStatus;
	}

	public void setDiaryStatus(Boolean diaryStatus) {
		this.diaryStatus = diaryStatus;
	}

	public Integer getDiaryType() {
		return diaryType;
	}

	public void setDiaryType(Integer diaryType) {
		this.diaryType = diaryType;
	}


}

package com.comment.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class CommentVO implements Serializable {
	
	private Integer commentID; //留言ID
	private Integer diaryID; //日誌ID
	private String nickName; //暱稱
	private Timestamp createdTime; //創建時間
	private String commentText; //留言內容
	private Boolean commentStatus; //留言檢舉狀態
	
	public CommentVO() {
		super();
	}

	public CommentVO(Integer commentID, Integer diaryID, String nickName, Timestamp createdTime, String commentText,
			Boolean commentStatus) {
		super();
		this.commentID = commentID;
		this.diaryID = diaryID;
		this.nickName = nickName;
		this.createdTime = createdTime;
		this.commentText = commentText;
		this.commentStatus = commentStatus;
	}

	public Integer getCommentID() {
		return commentID;
	}

	public void setCommentID(Integer commentID) {
		this.commentID = commentID;
	}

	public Integer getDiaryID() {
		return diaryID;
	}

	public void setDiaryID(Integer diaryID) {
		this.diaryID = diaryID;
	}


	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public Timestamp getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Timestamp createdTime) {
		this.createdTime = createdTime;
	}

	public String getCommentText() {
		return commentText;
	}

	public void setCommentText(String commentText) {
		this.commentText = commentText;
	}

	public Boolean getCommentStatus() {
		return commentStatus;
	}

	public void setCommentStatus(Boolean commentStatus) {
		this.commentStatus = commentStatus;
	}
	
}

package com.pojo.model;

import java.sql.Timestamp;

public class CommentReportVO {
	private int commentID;
	private int diaryID;
	private int custID;
	private Timestamp createdTime;
	private String commentText;
	private Boolean commentStatus;


	public int getCommentID() {
		return commentID;
	}
	public void setCommentID(int commentID) {
		this.commentID = commentID;
	}
	public int getDiaryID() {
		return diaryID;
	}
	public void setDiaryID(int diaryID) {
		this.diaryID = diaryID;
	}
	public int getCustID() {
		return custID;
	}
	public void setCustID(int custID) {
		this.custID = custID;
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

package com.pojo.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class DiaryReportVO implements Serializable{
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private int commentReportID;
	private int custID;
	private Timestamp createTime;
	private String reportReason;
	private Boolean reportResult;

	public int getCommentReportID() {
		return commentReportID;
	}
	public void setCommentReportID(int commentReportID) {
		this.commentReportID = commentReportID;
	}
	public int getCustID() {
		return custID;
	}
	public void setCustID(int custID) {
		this.custID = custID;
	}
	public Timestamp getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}
	public String getReportReason() {
		return reportReason;
	}
	public void setReportReason(String reportReason) {
		this.reportReason = reportReason;
	}
	public Boolean getReportResult() {
		return reportResult;
	}
	public void setReportResult(Boolean reportResult) {
		this.reportResult = reportResult;
	}
}

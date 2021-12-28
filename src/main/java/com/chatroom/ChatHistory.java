package com.chatroom;

import java.sql.Date;

public class ChatHistory {
	private String type;
	private String message;
	private String selfAccount;
	private String friAccount;
	private String createdTime;
	public ChatHistory(String type,String selfAccount,String friAccount,String message,String createdTime)
	{
		this.type=type;
		this.message=message;
		this.selfAccount=selfAccount;
		this.friAccount=friAccount;
		this.createdTime=createdTime;
	}
	
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getSelfAccount() {
		return selfAccount;
	}
	public void setSelfAccount(String selfAccount) {
		this.selfAccount = selfAccount;
	}
	public String getFriAccount() {
		return friAccount;
	}
	public void setFriAccount(String friAccount) {
		this.friAccount = friAccount;
	}


	public String getCreatedTime() {
		return createdTime;
	}


	public void setCreatedTime(String createdTime) {
		this.createdTime = createdTime;
	}
	
}

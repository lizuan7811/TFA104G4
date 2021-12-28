package com.chatroom;

public class FriendChatPOJO {
	private String type;
	private String message;
	private String selfAccount;
	private String friAccount;
	public FriendChatPOJO(String type,String account,String friAccount,String message) {
		this.type=type;
		this.selfAccount=account;
		this.friAccount=friAccount;
		this.message=message;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getMessage() {
		return this.message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getSelfAccount() {
		return this.selfAccount;
	}
	public void setSelfAccount(String selfAccount) {
		this.selfAccount = selfAccount;
	}
	public String getFriAccount() {
		return this.friAccount;
	}
	public void setFriAccount(String friAccount) {
		this.friAccount = friAccount;
	}
	
}

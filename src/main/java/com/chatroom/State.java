package com.chatroom;

import java.util.Set;

public class State {
	private String type;
	private String userAccount;
	private Set<String>userAccounts;
	
	public State(String type,String userAccount,Set<String>userAccounts) {
		this.type=type;
		this.userAccount=userAccount;
		this.userAccounts=userAccounts;
	}
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getUserAccount() {
		return userAccount;
	}
	public void setUserAccount(String userAccount) {
		this.userAccount = userAccount;
	}
	public Set<String> getUserAccounts() {
		return userAccounts;
	}
	public void setUserAccounts(Set<String> userAccounts) {
		this.userAccounts = userAccounts;
	}
	
	

}

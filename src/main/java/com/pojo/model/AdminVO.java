package com.pojo.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class AdminVO implements Serializable{
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private int adminID;
	private String adminAcco;
	private String adminPass;
	private Timestamp createdTime;
	private Boolean adminAuthority;
	public AdminVO()
	{
//		adminID=0;
//		adminAcco="";
//		adminPass="";
//		createdTime=null;
//		adminAuthority=null;
	}

	public int getAdminID() {
		return adminID;
	}
	public void setAdminID(int adminID)
	{
		this.adminID=adminID;
	}
	public String getAdminAcco() {
		return adminAcco;
	}
	public void setAdminAcco(String adminAcco) {
		this.adminAcco = adminAcco;
	}
	public String getAdminPass() {
		return adminPass;
	}
	public void setAdminPass(String adminPass) {
		this.adminPass = adminPass;
	}
	public Timestamp getCreatedTime() {
		return createdTime;
	}
	public void setCreatedTime(Timestamp createdTime) {
		this.createdTime = createdTime;
	}
	public Boolean getAdminAuthority() {
		return adminAuthority;
	}
	public void setAdminAuthority(Boolean adminAuthority) {
		this.adminAuthority = adminAuthority;
	}


}

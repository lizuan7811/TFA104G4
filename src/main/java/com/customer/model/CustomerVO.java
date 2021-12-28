package com.customer.model;
import java.io.Serializable;
import java.sql.Timestamp;

public class CustomerVO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer idCustomer;
	private String name;
	private String nickname;
	private String account;
	private String password;
	private String email;
	private String phone;
	private Boolean notification;
	private byte[] profic;
	private Timestamp createdTime;
	private Boolean activated;
	private Boolean suspended; // 0為沒有被suspend, 1為被suspend
	private Integer externalAcc; // 1用自己email, 2是google, 3是facebook, 4是apple
	private String externalIdToken;
//	private Integer commentReportedNum;
//	private Integer diaryReportedNum;
	
	public CustomerVO() {
		
	}

	public CustomerVO(Integer idCustomer, String name, String nickname, String account, String password, String email, 
			String phone, Boolean notification, byte[] profic, Timestamp createdTime, Boolean acvtivated, Boolean suspended, 
			Integer externalAcc, String externalIdToken, Boolean activated) {
		this.idCustomer = idCustomer;
		this.name = name;
		this.nickname = nickname;
		this.account = account;
		this.password = password;
		this.email = email;
		this.phone = phone;
		this.notification = notification;
		this.profic = profic;
		this.createdTime = createdTime;
		this.activated = activated;
		this.suspended = suspended;
		this.externalAcc = externalAcc;
		this.externalIdToken = externalIdToken;
//		this.commentReportedNum = commentReportedNum;
//		this.diaryReportedNum = diaryReportedNum;
	}
	
	public Integer getIdCustomer() {
		return idCustomer;
	}

	public void setIdCustomer(Integer idCustomer) {
		this.idCustomer = idCustomer;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Boolean getNotification() {
		return notification;
	}
	
	public void setNotification(Boolean notification) {
		this.notification = notification;
	}
	
	public byte[] getProfic() {
		return profic;
	}

	public void setProfic(byte[] profic) {
		this.profic = profic;
	}
	
	public Timestamp getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Timestamp createdTime) {
		this.createdTime = createdTime;
	}
	
	public Boolean getActivated() {
		return activated;
	}

	public void setActivated(Boolean activated) {
		this.activated = activated;
	}	

	public Boolean getSuspended() {
		return suspended;
	}

	public void setSuspended(Boolean suspended) {
		this.suspended = suspended;
	}

	public Integer getExternalAcc() {
		return externalAcc;
	}

	public void setExternalAcc(Integer externalAcc) {
		this.externalAcc = externalAcc;
	}
	
	public String getExternalIdToken() {
		return externalIdToken;
	}
	
	public void setExternalIdToken(String externalIdToken) {
		this.externalIdToken = externalIdToken;
	}
	
//	public Integer getCommentReportedNum() {
//		return commentReportedNum;
//	}
//
//	public void setCommentReportedNum(Integer commentReportedNum) {
//		this.commentReportedNum = commentReportedNum;
//	}
//
//	public Integer getDiaryReportedNum() {
//		return diaryReportedNum;
//	}
//
//	public void setDiaryReportedNum(Integer diaryReportedNum) {
//		this.diaryReportedNum = diaryReportedNum;
//	}
//	
}

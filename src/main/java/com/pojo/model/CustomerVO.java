package com.pojo.model;
import java.io.Serializable;
import java.sql.Timestamp;

public class CustomerVO implements Serializable {
	private Integer idCustomer;
	private String name;
	private byte[] profic;
	private String nickName;
	private String account;
	private String password;
	private String email;
	private String phone;
	private Timestamp createdTime;
	private Boolean suspended;
	private Integer externalAcc;
	private Integer commentReportedNum;
	private Integer diaryReportedNum;

	public CustomerVO() {

	}

	public CustomerVO(Integer idCustomer, String name, byte[] profic, String nickName, String account,
			String password, String email, String phone, Timestamp createdTime, Boolean suspended,
			Integer externalAcc, Integer commentReportedNum, Integer diaryReportedNum) {
		this.idCustomer = idCustomer;
		this.name = name;
		this.profic = profic;
		this.nickName = nickName;
		this.account = account;
		this.password = password;
		this.email = email;
		this.phone = phone;
		this.createdTime = createdTime;
		this.suspended = suspended;
		this.externalAcc = externalAcc;
		this.commentReportedNum = commentReportedNum;
		this.diaryReportedNum = diaryReportedNum;
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

	public byte[] getProfic() {
		return profic;
	}

	public void setProfic(byte[] profic) {
		this.profic = profic;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
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

	public Timestamp getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Timestamp createdTime) {
		this.createdTime = createdTime;
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

	public Integer getCommentReportedNum() {
		return commentReportedNum;
	}

	public void setCommentReportedNum(Integer commentReportedNum) {
		this.commentReportedNum = commentReportedNum;
	}

	public Integer getDiaryReportedNum() {
		return diaryReportedNum;
	}

	public void setDiaryReportedNum(Integer diaryReportedNum) {
		this.diaryReportedNum = diaryReportedNum;
	}

}

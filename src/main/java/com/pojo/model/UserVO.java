package com.pojo.model;

import java.io.Serializable;
import java.sql.Blob;
import java.sql.Timestamp;


public class UserVO implements Serializable{
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private Integer idCustomer=11;//會員ID
	private String name="lizaun";//姓名
	private String nickName="lizaun";//暱稱
	private String account="lizaun";//會員帳號
	private String passWord="lizaun";//會員密碼
	private String email="lizaun";//信箱
	private String phone="lizaun";//電話
	private Timestamp createdTime;//帳號創建時間
	private Boolean suspended=false;//是否被停權
	private Integer externalAcc=null;//連接第三方帳號的狀態(0:無，1:GOOGLE，2:FB)
	private String externalIdToken=null;//第三方帳號IDToken
	private Integer commentReportedNum=10;//留言被檢舉成功次數
	private Integer diaryreportedNum=10;//日誌檢舉成功次數
	private Blob profic;//大頭照

	public UserVO()
	{

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
	public String getPassWord() {
		return passWord;
	}
	public void setPassWord(String passWord) {
		this.passWord = passWord;
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
	public String getExternalIdToken() {
		return externalIdToken;
	}
	public void setExternalIdToken(String externalIdToken) {
		this.externalIdToken = externalIdToken;
	}
	public Integer getCommentReportedNum() {
		return commentReportedNum;
	}
	public void setCommentReportedNum(Integer commentReportedNum) {
		this.commentReportedNum = commentReportedNum;
	}
	public Integer getDiaryreportedNum() {
		return diaryreportedNum;
	}
	public void setDiaryreportedNum(Integer diaryreportedNum) {
		this.diaryreportedNum = diaryreportedNum;
	}
	public Blob getProfic() {
		return profic;
	}
	public void setProfic(Blob profic) {
		this.profic = profic;
	}



}

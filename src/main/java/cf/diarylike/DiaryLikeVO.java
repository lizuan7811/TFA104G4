package cf.diarylike;

import java.io.Serializable;
import java.sql.Timestamp;

public class DiaryLikeVO implements Serializable{
	
	private Integer diarylikeID; //按讚ID
	private Integer diaryID; //日誌ID
	private Integer custID; //會員ID
	private Timestamp createdTime; //創建時間
		
	public DiaryLikeVO() {
		super();
	}
	
	public DiaryLikeVO(Integer diarylikeID, Integer diaryID, Integer custID, Timestamp createdTime) {
		super();
		this.diarylikeID=diarylikeID;
		this.diaryID=diaryID;
		this.custID=custID;
		this.createdTime=createdTime;
	}
	
	public Integer getDiaryLikeid() {
		return diarylikeID;
	}
	public void setDiaryLikeid(Integer diarylikeID) {
		this.diarylikeID = diarylikeID;
	}
	public Integer getDiaryid() {
		return diaryID;
	}
	public void setDiaryid(Integer diaryid) {
		this.diaryID = diaryid;
	}
	public Integer getCustid() {
		return custID;
	}
	public void setCustid(Integer custid) {
		this.custID = custid;
	}
	public Timestamp getCreatedtime() {
		return createdTime;
	}
	public void setCreatedtime(Timestamp createdtime) {
		this.createdTime = createdtime;
	}
	
	
}
package po;

import java.io.Serializable;
import java.sql.Date;


public class ChatRoomVO implements Serializable {

	private Integer mesgID;
	private Integer custID;
	private Boolean custMesg;
	private Date createdTime;
	private String message;
	
	public ChatRoomVO() {
		
	}
	
	public ChatRoomVO(Integer mesgID, Integer custID, Boolean custMesg, Date createdTime, String message) {
		super();
		this.mesgID = mesgID;
		this.custID = custID;
		this.custMesg = custMesg;
		this.createdTime = createdTime;
		this.message = message;
	}
	public Integer getMesgID() {
		return mesgID;
	}
	public void setMesgID(Integer mesgID) {
		this.mesgID = mesgID;
	}
	public Integer getCustID() {
		return custID;
	}
	public void setCustID(Integer custID) {
		this.custID = custID;
	}
	public Boolean getCustMesg() {
		return custMesg;
	}
	public void setBoolean(Boolean custMesg) {
		this.custMesg = custMesg;
	}
	public Date getCreatedTime() {
		return createdTime;
	}
	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	
}

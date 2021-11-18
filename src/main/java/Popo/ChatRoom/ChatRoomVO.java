package po;

import java.io.Serializable;
import java.sql.Date;


public class ChatRoomVO implements Serializable {

	private int mesgID;
	private int custID;
	private boolean custMesg;
	private Date creatTime;
	private String message;
	
	public ChatRoomVO() {
		
	}
	
	public ChatRoomVO(int mesgID, int custID, boolean custMesg, Date creatTime, String message) {
		super();
		this.mesgID = mesgID;
		this.custID = custID;
		this.custMesg = custMesg;
		this.creatTime = creatTime;
		this.message = message;
	}
	public int getmesgID() {
		return mesgID;
	}
	public void setmesgID(int mesgID) {
		this.mesgID = mesgID;
	}
	public int getcustID() {
		return custID;
	}
	public void setcustID(int custID) {
		this.custID = custID;
	}
	public boolean getCustMesg() {
		return custMesg;
	}
	public void setBoolean(boolean custMesg) {
		this.custMesg = custMesg;
	}
	public Date getCreatTime() {
		return creatTime;
	}
	public void setcreatTime(Date creatTime) {
		this.creatTime = creatTime;
	}
	public String getmessage() {
		return message;
	}
	public void setmessage(String message) {
		this.message = message;
	}
	
	
}

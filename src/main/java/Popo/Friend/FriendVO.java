package po;

import java.io.Serializable;
import java.sql.Date;

public class FriendVO implements Serializable {
	private int friendChatID;
	private int custID;
	private int myFriendID;
	private int friendStatusNum;
	private Date statusUpdate;


	public FriendVO() {
		
	}


	public FriendVO(int friendChatID, int custID, int myFriendID, int friendStatusNum, Date statusUpdate) {
		super();
		this.friendChatID = friendChatID;
		this.custID = custID;
		this.myFriendID = myFriendID;
		this.friendStatusNum = friendStatusNum;
		this.statusUpdate = statusUpdate;
	}


	public int getFriendChatID() {
		return friendChatID;
	}


	public void setFriendChatID(int friendChatID) {
		this.friendChatID = friendChatID;
	}


	public int getCustID() {
		return custID;
	}


	public void setCustID(int custID) {
		this.custID = custID;
	}


	public int getMyFriendID() {
		return myFriendID;
	}


	public void setMyFriendID(int myFriendID) {
		this.myFriendID = myFriendID;
	}


	public int getFriendStatusNum() {
		return friendStatusNum;
	}


	public void setFriendStatusNum(int friendStatusNum) {
		this.friendStatusNum = friendStatusNum;
	}


	public Date getStatusUpdate() {
		return statusUpdate;
	}


	public void setStatusUpdate(Date statusUpdate) {
		this.statusUpdate = statusUpdate;
	}
	
	
	
	
}
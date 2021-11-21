package popo.Friend;

import java.io.Serializable;
import java.sql.Date;

public class FriendVO implements Serializable {
	private Integer friendChatID;
	private Integer custID;
	private Integer myFriendID;
	private Integer friendStatusNum;
	private Date statusUpdate;


	public FriendVO() {
		
	}


	public FriendVO(Integer friendChatID, Integer custID, Integer myFriendID, Integer friendStatusNum, Date statusUpdate) {
		super();
		this.friendChatID = friendChatID;
		this.custID = custID;
		this.myFriendID = myFriendID;
		this.friendStatusNum = friendStatusNum;
		this.statusUpdate = statusUpdate;
	}


	public Integer getFriendChatID() {
		return friendChatID;
	}


	public void setFriendChatID(Integer friendChatID) {
		this.friendChatID = friendChatID;
	}


	public Integer getCustID() {
		return custID;
	}


	public void setCustID(Integer custID) {
		this.custID = custID;
	}


	public Integer getMyFriendID() {
		return myFriendID;
	}


	public void setMyFriendID(Integer myFriendID) {
		this.myFriendID = myFriendID;
	}


	public Integer getFriendStatusNum() {
		return friendStatusNum;
	}


	public void setFriendStatusNum(Integer friendStatusNum) {
		this.friendStatusNum = friendStatusNum;
	}


	public Date getStatusUpdate() {
		return statusUpdate;
	}


	public void setStatusUpdate(Date statusUpdate) {
		this.statusUpdate = statusUpdate;
	}
	
	
	
	
}
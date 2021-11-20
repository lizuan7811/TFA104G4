package popo.FriendChat;

import java.io.Serializable;
import java.sql.Date;

public class FriendChatVO implements Serializable {

	private Integer friendChatID;
	private Integer custID;
	private Integer myFriendID;
	private String chatText;
	private Date createdTime;
	
	public FriendChatVO() {
		
	}
	
	public FriendChatVO(Integer friendChatID, Integer custID, Integer myFriendID, String chatText, Date createdTime) {
		super();
		this.friendChatID = friendChatID;
		this.custID = custID;
		this.myFriendID = myFriendID;
		this.chatText = chatText;
		this.createdTime = createdTime;
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

	public String getChatText() {
		return chatText;
	}

	public void setChatText(String chatText) {
		this.chatText = chatText;
	}

	public Date getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}
	
	
	
	
	
}

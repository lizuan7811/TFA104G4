package po;

import java.io.Serializable;
import java.sql.Date;

public class FriendChatVO implements Serializable {

	private int friendChatID;
	private int custID;
	private int myFriendID;
	private String chatText;
	private Date creatTime;
	
	public FriendChatVO() {
		
	}
	
	public FriendChatVO(int friendChatID, int custID, int myFriendID, String chatText, Date creatTime) {
		super();
		this.friendChatID = friendChatID;
		this.custID = custID;
		this.myFriendID = myFriendID;
		this.chatText = chatText;
		this.creatTime = creatTime;
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

	public String getChatText() {
		return chatText;
	}

	public void setChatText(String chatText) {
		this.chatText = chatText;
	}

	public Date getCreatTime() {
		return creatTime;
	}

	public void setCreatTime(Date creatTime) {
		this.creatTime = creatTime;
	}
	
	
	
	
	
}

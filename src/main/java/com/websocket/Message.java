package com.websocket;

public class Message {

	private String toName;
	private String message;
	
	public String getToName()
	{
		return this.toName;
	}
	public void setToName(String toName)
	{
		this.toName=toName;
	}
	
	public void setMessage(String message)
	{
		this.message=message;
	}
	public String getMessage()
	{
		return this.message;
	}
	
	
}

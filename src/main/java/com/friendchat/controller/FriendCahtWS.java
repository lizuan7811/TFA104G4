package com.friendchat.controller;

import javax.websocket.CloseReason;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint("/chatroom.html")
public class FriendCahtWS {
	
	@OnOpen
	public void onOpen(Session session,@PathParam("userName")String userName)
	{
		System.out.println("OnOpen()");
	}
	
	@OnMessage
	public void onMessage(Session session ,String message)
	{
		System.out.println("OnMessage()");

	}
	@OnClose
	public void onClose(CloseReason reason)
	{
		System.out.println("OnClose()");

	}
	@OnError
	public void onError(Throwable e)
	{
		System.out.println("OnError()");

	}
	

}

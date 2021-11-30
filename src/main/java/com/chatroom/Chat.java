package com.chatroom;

import javax.websocket.CloseReason;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint("/chatroom")
public class Chat {

	@OnOpen
	public void onOpen(@PathParam("userName")String userName,Session session)
	{
		System.out.println("WebSocket Open!");
	}
	@OnMessage
	public void onMessage(Session session ,String message)
	{
		System.out.println("WebSocket Send Message!");
	}
	@OnClose
	public void onClose(Session session ,CloseReason reason)
	{
		System.out.println("WebSocket Close!");

	}
	@OnError
	public void onError(Throwable throwerror)
	{
		System.out.println("WebSocket Error!");

	}
	
}

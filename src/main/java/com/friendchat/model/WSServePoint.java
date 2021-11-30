package com.friendchat.model;
import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.websocket.CloseReason;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint("/chatroom")
public class WSServePoint {
	private static Set<Session>set=new HashSet<Session>();
	@OnOpen
	public void onOpen(Session session,@PathParam("userName")String userName)
	{
		System.out.println("建立!");
		set.add(session);
	}
	
	@OnClose
	public void onClose(Session session,CloseReason reason)
	{
		System.out.println("OnClose!");
	}
	
	@OnMessage
//	public void onMessage(Session session , String message)
	public void onMessage(String message)
	{
		System.out.println(message);
		
		for(Session ses:set)
		{
			ses.getAsyncRemote().sendText(message);			
		}

		System.out.println("OnMessage!");
	}
	
	@OnError
	public void onError(Throwable e)
	{
		System.out.println("Error!");
	}
	
	
}
package com.chatroom;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.websocket.CloseReason;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import javax.websocket.server.ServerEndpointConfig;

import com.google.gson.Gson;

@ServerEndpoint("/chatroom")
public class Chat {

	private static Map<String,Session> onlineSessions=new ConcurrentHashMap<>();
	Gson gson=new Gson();
	@OnOpen
	public void onOpen(@PathParam("userName")String userName,Session session,@PathParam("friendMap")String friendMap)
	{
		System.out.println("WebSocket Open!");
		onlineSessions.put(userName, session);
		System.out.println(friendMap);
//		if(onlineSessions.containsKey(userName))
//		for(String sess:onlineSessions.keySet())
//		{
//			
//		}
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

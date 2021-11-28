package com.talkc;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.OnMessage;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import com.google.gson.Gson;

@ServerEndpoint("/FriendWs/{userName}")
public class FriendWS {
	private static Map<String,Session>sessionsMap=new ConcurrentHashMap<>();
	Gson gson=new Gson();
	
	@OnOpen
	public void onOpen(@PathParam("userName") String userName,Session userSession)
	{
		sessionsMap.put(userName,userSession);
		
		Set<String> userNames=sessionsMap.keySet();
		State stateMessage=new State("open",userName,userNames);
		String stateMessageJson=gson.toJson(stateMessage);
		Collection<Session>sessions=sessionsMap.values();
		for(Session session:sessions)
		{
			if(session.isOpen())
			{
				session.getAsyncRemote().sendText(stateMessageJson);
			}
		}
		String text=String.format("Session ID= %s , connected; userName = %s%nusers: %s",userSession.getId(),userName,userNames);
		System.out.println(text);
	}
	
	@OnMessage
	public void OnMessage(Session userSession,String message)
	{
		ChatMessage chatMessage=gson.fromJson(message,ChatMessage.class);
		String sender=chatMessage.getSender();
		String receiver=chatMessage.getReceiver();
		
		if("history".equals(chatMessage.getType())) {
			List<String>historyData=JedisHandleMessage.getHistoryMsg(sender,receiver);
			String historyMsg=gson.toJson(historyData);
			ChatMessage cmHistory=new ChatMessage("history",sender,receiver,historyMsg);
			if(userSession!=null && userSession.isOpen())
			{
				userSession.getAsyncRemote().sendText(gson.toJson(cmHistory));
			}
		}
	}
}

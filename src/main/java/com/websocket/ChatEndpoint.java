package com.websocket;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.http.HttpSession;
import javax.websocket.EndpointConfig;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint(value="/ChatEndpoint",configurator=GetHttpSessionConfigurator.class)
public class ChatEndpoint {
	
	private static Map<String,ChatEndpoint>onlineSessions=new ConcurrentHashMap<String,ChatEndpoint>();
	
	private Session session;
	
	private HttpSession httpSession;

	@OnOpen
	public void onOpen(Session session,EndpointConfig config)
	{
		this.session=session;
		
		HttpSession httpSession=(HttpSession)config.getUserProperties().get(HttpSession.class.getName());

		this.httpSession=httpSession;
		
		String userName=(String) httpSession.getAttribute("userName");
		
		onlineSessions.put(userName,this);
		
//		MessageUtils.getMessage(true,null,getNames());
	
	}
	
	private void broadcastAllUsers(String message)
	{
		try {
			Set<String>sess=onlineSessions.keySet();
			for(String ses:sess)
			{
				ChatEndpoint chatEndpoint=onlineSessions.get(ses);
				chatEndpoint.session.getBasicRemote().sendText(message);
			}
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
	
	}
	
	private Set<String> getNames()
	{
		return onlineSessions.keySet();
	}
	
	
	@OnMessage
	public void onMessage(String message,Session session)
	{
		try {
			ObjectMapper mapper=new ObjectMapper();
			Message mess=mapper.readValue(message,Message.class);
			String toName=mess.getToName();
			
			String data=mess.getMessage();
			
			String username=(String) httpSession.getAttribute("user");
			
			String resultMessage=MessageUtils.getMessage(false,username,data);
			
			onlineSessions.get(toName).session.getBasicRemote().sendText(resultMessage);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}
	
	@OnClose
	public void onClose(Session session)
	{
		
	}
}

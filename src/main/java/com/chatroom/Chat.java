package com.chatroom;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import javax.websocket.CloseReason;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

//import javax.websocket.server.ServerEndpointConfig;
import com.google.gson.Gson;

@ServerEndpoint("/chatroom/{selfAccount}")
public class Chat {

	private static Map<String, Session> onlineSessions = new ConcurrentHashMap<>();
	Gson gson = new Gson();

	@OnOpen
	public void onOpen(Session session, @PathParam("selfAccount") String selfAccount) throws IOException {

		System.out.println("WebSocket Open!");
		System.out.println("Open get Account!\t" + selfAccount);
		try {
			if (session != null)
				if (selfAccount != null && session != null) {
					onlineSessions.put(selfAccount, session);
				}

			Set<String> userAccounts = onlineSessions.keySet();
			Session selfSession = onlineSessions.get(selfAccount);
			State stateMsg = new State("open", selfAccount, userAccounts);
			String frinedChatPJ = gson.toJson(stateMsg);

			Collection<Session> sessions = onlineSessions.values();
////		判斷目前使用者session是否與搜尋到的相同，且好友名稱及好友session相符，且在線上，就會通知對方當事人上線
			for (Session sess : sessions) {
				if (sess.isOpen()) {
					sess.getAsyncRemote().sendText(frinedChatPJ);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@OnMessage
	public void onMessage(Session session, String message) {
		try {
			FriendChatPOJO chatMessage = gson.fromJson(message, FriendChatPOJO.class);
			String selfAcc = chatMessage.getSelfAccount();
			String friAcc = chatMessage.getFriAccount();
//			String mess=chatMessage.getMessage();
//			System.out.println("selfAcc"+selfAcc);
//			System.out.println("friAcc"+friAcc);
//			System.out.println(chatMessage.getType());
			if ("history".equals(chatMessage.getType())) {
				System.out.println("印歷史聊天紀錄!");
				List<String> historyData = JedisHandleMessage.getHistoryMsg(selfAcc, friAcc);
				String historyMsg = gson.toJson(historyData);
				FriendChatPOJO cmHistory = new FriendChatPOJO("history", selfAcc, friAcc, historyMsg);
				if (session != null && session.isOpen()) {
					session.getAsyncRemote().sendText(gson.toJson(cmHistory));
					return;
				}
			}
			Session friSession = onlineSessions.get(friAcc);

//if(friSession==null)
//{
//	System.out.println("System.out.println(friSession == null);"+friSession == null);
//	return ;
//}
			System.out.printf("System.out.println(friSession == null);\t %s \r\n", friSession == null);

			Calendar cl = Calendar.getInstance();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

			ChatHistory chatHisto = new ChatHistory(chatMessage.getType(), selfAcc, friAcc, chatMessage.getMessage(),
					sdf.format(cl.getTimeInMillis()));
			message = gson.toJson(chatHisto);
			if (friSession != null && friSession.isOpen()) {
				friSession.getAsyncRemote().sendText(message);
//				JedisHandleMessage.saveChatMessage(selfAcc, friAcc, message);
			}
			JedisHandleMessage.saveChatMessage(selfAcc, friAcc, message);
			System.out.println("Message received: " + message);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@OnClose
	public void onClose(Session session, CloseReason reason) {
		System.out.println("WebSocket Close!");
		try {
			String userNameClose = null;
			Set<String> userNames = onlineSessions.keySet();
			for (String userName : userNames) {
				if (onlineSessions.get(userName).equals(session)) {
					userNameClose = userName;
					onlineSessions.remove(userName);
					break;
				}
			}
			if (userNameClose != null) {
				State stateMessage = new State("close", userNameClose, userNames);
				String stateMessageJson = gson.toJson(stateMessage);
				Collection<Session> sessions = onlineSessions.values();
				for (Session sess : sessions) {
					session.getAsyncRemote().sendText(stateMessageJson);
				}
			}

			String text = String.format("session ID = %s, disconnected; close code = %d%nusers: %s", session.getId(),
					reason.getCloseCode().getCode(), userNames);
			System.out.println(text);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@OnError
	public void onError(Session session, Throwable throwerror) {
		System.out.println("WebSocket Error!");
//		System.out.println("Error: " + throwerror.getCause()+"\t"+throwerror.getMessage());

	}

}

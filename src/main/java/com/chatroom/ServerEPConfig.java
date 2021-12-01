package com.chatroom;

import javax.servlet.http.HttpSession;
import javax.websocket.HandshakeResponse;
import javax.websocket.server.HandshakeRequest;
import javax.websocket.server.ServerEndpointConfig;
import javax.websocket.server.ServerEndpointConfig.Configurator;

public class ServerEPConfig extends Configurator {
	
	@Override
//	取得朋友列表名單
	public void modifyHandshake(ServerEndpointConfig config,HandshakeRequest request,HandshakeResponse response) {
		HttpSession httpSession=(HttpSession)request.getHttpSession();
		config.getUserProperties().put(HttpSession.class.getName(),httpSession);
	}
}

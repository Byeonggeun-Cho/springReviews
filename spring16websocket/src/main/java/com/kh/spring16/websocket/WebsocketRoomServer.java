package com.kh.spring16.websocket;

import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import lombok.extern.slf4j.Slf4j;

/*
 * 사용자들을 '방'으로 분리해서 별도 처리할 수 있는 서버
 * 
 */

@Slf4j
public class WebsocketRoomServer extends TextWebSocketHandler{@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {

	
	super.afterConnectionEstablished(session);
	}

	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {

		
		super.afterConnectionClosed(session, status);
	}
	
	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		
		
		super.handleTextMessage(session, message);
	}

	
	
}

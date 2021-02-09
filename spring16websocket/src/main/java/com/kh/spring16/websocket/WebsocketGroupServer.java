package com.kh.spring16.websocket;

import java.util.HashSet;
import java.util.Set;

import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class WebsocketGroupServer extends TextWebSocketHandler {
	
	// 목표: 접속자를 모아두고 관리하고 싶다.
	//	- 사용자 인원수는 유동적
	//	- set<WebSocketSession> 형태로 관리한다
	private Set<WebSocketSession> users = new HashSet<>();
	
	// 접속
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		// 입장 메세지: 내가 저장소에 추가되지 전에 입장 메세지를 전송
		users.add(session);
		
		super.afterConnectionEstablished(session);
	}

	// 종료
	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		// 퇴장 메세지: 내가 저장소에서 제거된 뒤에 퇴장 메세지를 전송
		users.remove(session);
		
		super.afterConnectionClosed(session, status);
	}

	// 수신
	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		
		for(WebSocketSession user : users) {
			user.sendMessage(message);
		}
		
		super.handleTextMessage(session, message);
	}
}

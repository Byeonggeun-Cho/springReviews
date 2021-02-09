package com.kh.spring16.vo;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

/*
 * 한 개의 채팅방에 필요한 기능들을 구현
 * - 사용자: Set<WebSocketSession>
 * 
 */
public class Room {
	private Set<WebSocketSession> users = new HashSet<>();
	
	// 입장기능
	public void enter(WebSocketSession session) {
		users.add(session);
	}
	
	// 퇴장기능
	public void leave(WebSocketSession session) {
		users.remove(session);
	}
	
	
	// 방 전체에 메세지 전송 기능(Broadcast)
	// 예외처리는 별도로 구현한다
	public void broadcast(String message) throws IOException {
		TextMessage tm = new TextMessage(message);
		
		for(WebSocketSession user: users) {
			user.sendMessage(tm);
		}
	}
	
	// 방이 비어있는지 확인
	public boolean isEmpty() {
//		return users.size() == 0;
		return users.isEmpty();
	}
}

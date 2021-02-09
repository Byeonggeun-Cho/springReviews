package com.kh.spring16.vo;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.socket.WebSocketSession;

/*
 * 채널 클래스
 * - 방(Room)을 번호로 관리하는 클래스
 */
public class Channel {
	// 방 저장소
	private Map<Integer, Room> storage = new HashMap<>();
	
	// 채널 접속: 방 번호 + 사용자 정보
	public void enter(int roomNumber, WebSocketSession session) {
		
	}
	
	// 방 나가기: 방 번호 + 사용자 정보
	public void leave(int roomNumber, WebSocketSession session) {
		
	}
	
	// 방 메세지 전송: 방 번호 + 사용자 + 메세지(String)
	public void sendRoom(int roomNumber, WebSocketSession session, String payload) {
		
	}
	
	// 전체 메세지 전송: 사용자 + 메세지(String)
	public void sendAll(WebSocketSession session, String payload) {
	}
	
}

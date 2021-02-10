package com.kh.spring16.vo;

import java.io.IOException;
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
		if(!storage.containsKey(roomNumber)) {	// 방 번호가 없다면
			storage.put(roomNumber, new Room());	// 방 생성
			
		}

		// 사용자를 방에 추가 
		storage.get(roomNumber).enter(session);
	}
	
	// 방 나가기: 방 번호 + 사용자 정보
	public void leave(int roomNumber, WebSocketSession session) {
		// 사용자를 방에서 제거
		storage.get(roomNumber).leave(session);
		
//		if(storage.get(roomNumber).size() == 0) {
		if(storage.get(roomNumber).isEmpty()) {	// 방에 사용자가 하나도 없다면
			storage.remove(roomNumber);	// 방 삭제
		
		}
	}
	
	// 방 메세지 전송: 방 번호 + 메세지(String)
	public void sendRoom(int roomNumber, String json) throws IOException {
		Room room = storage.get(roomNumber);		// 방 정보를 꺼낸다.
		
		if(room != null) {
			room.broadcast(json);
		}
	}
	
	// 전체 메세지 전송: 메세지(String)
	// - sendRoom을 전체 방 개수만큼 반복
	public void sendAll(String json) throws IOException {
		for(int roomNumber: storage.keySet()) {
			sendRoom(roomNumber, json);
		}
	}
}

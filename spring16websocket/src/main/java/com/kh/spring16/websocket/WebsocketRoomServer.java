package com.kh.spring16.websocket;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kh.spring16.vo.Channel;
import com.kh.spring16.vo.Message;

import lombok.extern.slf4j.Slf4j;

/*
 * 사용자들을 '방'으로 분리해서 별도 처리할 수 있는 서버
 * 
 */

@Slf4j
public class WebsocketRoomServer extends TextWebSocketHandler{

	// 캡슐화 구조를 만들어 Collection을 외부에 노출시키지 않고 기능 단위로 사용
	// 채널로 통합관리
	private Channel channel = new Channel();
	
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		
	
	super.afterConnectionEstablished(session);
	}

	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {

		
		super.afterConnectionClosed(session, status);
	}
	
	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		// [1] 사용자가 보낸 메세지의 payload를 읽는다.
		//	- ex) {"room":1, "type":"message", "content":"안녕하세요"}
		//	- 따라서, 수신한 JSON을 객체로 변환해줘야 한다(jackson-databind)
		
		// jackson을 직접 사용하는 명령어
		ObjectMapper mapper = new ObjectMapper();
		Message m = mapper.readValue(message.getPayload(), Message.class);
		
		// [2] 읽은 데이터의 종류(type)에 따라 다르게 처리하도록 구성
		// [3] 각 상황에 맞는 처리 구현
		
		log.info("payload={}", message.getPayload());
		log.info("m={}", m);
		
		if(m.getType().equals("enter")) {	// type == enter
			
			log.info("사용자가 {}번 방에 접속했습니다", m.getRoom());

			channel.enter(m.getRoom(), session);
			
		} else if(m.getType().equals("leave")) {	// type == leave
			
			log.info("사용자가 {}번 방에서 접속 종료했습니다", m.getRoom());
			
			channel.leave(m.getRoom(), session);
			
		} else if(m.getType().equals("message")) {	// type == message
			// 보낼 메세지에 시간 추가
			Date date = new Date();
			Format f = new SimpleDateFormat("a h:mm");
			m.setTime(f.format(date));
			
			String json = mapper.writeValueAsString(m);		// 객체 -> JSON

			if(m.getRoom() == 0) {	// 전체에게 메세지를 전송
				channel.sendAll(json);
			} else {	// 해당 방에만 메세지를 전송
				channel.sendRoom(m.getRoom(), json);
			}
		}
		
		super.handleTextMessage(session, message);
	}

	
	
}

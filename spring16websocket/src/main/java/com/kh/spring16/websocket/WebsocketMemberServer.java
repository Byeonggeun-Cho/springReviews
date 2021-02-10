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
import com.kh.spring16.vo.Member;
import com.kh.spring16.vo.Message;

import lombok.extern.slf4j.Slf4j;

/*
 * 로그인된 사용자들이 이용할 수 있는 분산 서버
 * 
 */

@Slf4j
public class WebsocketMemberServer extends TextWebSocketHandler{

	// 캡슐화 구조를 만들어 Collection을 외부에 노출시키지 않고 기능 단위로 사용
	// 채널로 통합관리
	private Channel channel = new Channel();
	
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
	
		// 실제로 웹소켓에 들어있는 HttpSession의 정보를 확인
		// - 서로 다른 통신이기 때문에 원칙적으로는 공유 불가
		// - Spring에서 제공해주는 Interceptor가 이것을 가능하도록 해준다.
		// - 단, 웹소켓 연결 전에 반드시 세션에 데이터가 존재해야 한다.
		
		// Member member = HttpSession에 들어있는 user 데이터
		Member member = (Member) session.getAttributes().get("user");
		log.info("member={}", member);
		
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

			// 회원정보(member)를 꺼내서 존재하는 경우에만 설정 후 전송(회원전용)
			Member member = (Member) session.getAttributes().get("user");

			// 회원인 경우에만 서버로 전달된 사용자의 메세지를 다른 사용자에게 전달
			if(member != null) {	// 로그인 된 상태라면
				// 회원 구분을 위한 회원정보 전달
				// 발신인(sender) 정보를 회원 id로 설정
				m.setSender(member.getId());
				
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
			
		}
		
		super.handleTextMessage(session, message);
	}

	
	
}

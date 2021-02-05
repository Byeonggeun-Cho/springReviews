package com.kh.spring16.websocket;

import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import lombok.extern.slf4j.Slf4j;

/*
 * Spring에서 Websocket 관련 처리를 수행하는 서버
 * - Controller와 별개로 작동
 * - 사용자 요청처리이므로 servlet-context.xml에 등록
 * - WebSocketSession을 사용( != HttpSession)
 * 
 * 구현방법
 * 1. 상속
 * 	- interface WebsocketHandler, class TextWebSocketHandler, ...)
 * 
 * 2. 메소드 재정의
 * 	- afterConnectionEstablished: 연결이 수립된 직후 실행되는 메소드
 * 		- WebSocketSession: 웹소켓 접속자 정보
 * 	
 *  - handleTextMessage: 
 * 		- WebSocketSession: 웹소켓 접속자 정보
 * 		- TextMessage: 수신 메세지 정보
 * 			- payload: 실제 전송된 텍스트
 * 			- byteCount: 실제 전송된 데이터 크기
 * 			- last: 마지막 데이터임을 표시(분할 전송 시). like EOF 
 * 	
 *  - afterConnectionClosed: 연결이 종료된 직후 실행되는 메소드
 * 		- WebSocketSession: 웹소켓 접속자 정보
 * 		- CloseStatus: 사용자 종료 상태 정보
 * 
 * 3. 서버를 servlet-context.xml에 등록
 * 
 */

@Slf4j
// public class WebsocketBasicServer implements WebSocketHandler {
// public class WebsocketBasicServer extends BinaryWebSocketHandler {
public class WebsocketBasicServer extends TextWebSocketHandler {

	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		log.info("afterConnectionEstablished 실행!");
		
		// WebSocketSession: 웹소켓 접속자 정보
		log.info("session = {}", session);
		super.afterConnectionEstablished(session);
	}

	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		log.info("handleTextMessage 실행!");
		
		log.info("session = {}", session);
		log.info("message = {}", message);
		
		// 메세지를 보낸 사용자에게만 회신(에코, echo)
		session.sendMessage(message);
		
		super.handleTextMessage(session, message);
	}

	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		log.info("afterConnectionClosed 실행!");

		log.info("session = {}", session);
		log.info("status = {}", status);
		super.afterConnectionClosed(session, status);
	}

}

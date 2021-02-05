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
 * 
 * 구현방법
 * 1. 상속
 * 	- interface WebsocketHandler, class TextWebSocketHandler, ...)
 * 
 * 2. 메소드 재정의
 * 	- afterConnectionEstablished: 
 * 	- handleTextMessage: 
 * 	- afterConnectionClosed: 
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
		super.afterConnectionEstablished(session);
	}

	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		log.info("handleTextMessage 실행!");
		super.handleTextMessage(session, message);
	}

	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		log.info("afterConnectionClosed 실행!");
		super.afterConnectionClosed(session, status);
	}

}

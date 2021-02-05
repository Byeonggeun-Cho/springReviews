<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" %>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

<h1>기본 웹소켓 예제</h1>

<button id="connect">접속버튼</button>
<hr>
<button id="disconnect">접속종료</button>


<script>
	// 웹소켓 관련 처리
	// - javascript에도 websocket API가 존재
	
	$(function(){
		// 연결 이벤트
		$("#connect").click(function(){
			var uri = "ws://localhost:8089/spring16/basic";
			
			// 웹소켓 연결(실제로는 "소켓을 생성했다"라고 표현)
			window.socket = new WebSocket(uri);
		});

		// 종료 이벤트
		$("#disconnect").click(function(){
			
			// 웹소켓 연결 종료(실제로는 "소켓을 소멸(종료)했다"라고 표현)
			// 1. 소켓 변수를 (공통 접근 가능한)외부에서 선언
			// 2. window 영역에 소켓 변수 선언
			window.socket.close();
		});
	});


</script>
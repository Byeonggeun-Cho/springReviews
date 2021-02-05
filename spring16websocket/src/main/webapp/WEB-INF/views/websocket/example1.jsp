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
			
			var socket = new WebSocket(uri);
		});

		// 종료 이벤트
		$("#disconnect").click(function(){
			
		});
	});


</script>
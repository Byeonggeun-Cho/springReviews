<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" %>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

<h1>그룹 웹소켓 예제</h1>

<button id="connect">접속버튼</button>
<button id="disconnect">접속종료</button>
<hr>

<input type="text" id="user-input" placeholder="메세지 입력">
<button id="send">전송</button>
<hr>
<div class="text-wrapper"></div>
<hr>


<script>
	// 웹소켓 관련 처리
	// - javascript에도 websocket API가 존재
	
	$(function(){
		// 연결 이벤트
		$("#connect").click(function(){
			var uri = "ws://localhost:8089/spring16/basic";
			
			// 웹소켓 연결(실제로는 "소켓을 생성했다"라고 표현)
			window.socket = new WebSocket(uri);
		
			// 연결 이후에 수행할 작업들을 예약(콜백, callback) 설정
			console.log(socket);		
			
			// 연결 후 수행
			socket.onopen = function(){
				console.log("연결 완료");

				$("<p>").text("서버에 접속했습니다.").appendTo(".text-wrapper");			
			};

			// 연결 후 수행
			socket.onclose = function(){
				console.log("연결 종료");

				$("<p>").text("서버와의 연결이 종료되었습니다.").appendTo(".text-wrapper");			
			};
			
			// 연결 오류 발생 시 수행
			socket.onerror = function(){
				console.log("에러 발생");
			};
			
			
			// 메세지 도착 시 수행
			socket.onmessage = function(message){
				console.log("메세지 수신");
				
			//	arguments: 함수(메소드)에서 사용 가능한 매개변수 정보 객체
			//	console.log(arguments[0]);
			//	console.log(message.data);

				$("<p>").text("-> " + message.data).appendTo(".text-wrapper");
			};
			
			
			// 전송 이벤트
			$("#send").click(function(){
				// 입력값을 불러온다
				var input = $("#user-input").val();
				
				// 입력값이 없는 경우 function 종료
				if(!input) return;
				
				// 입력값이 있는 경우 나머지 작업 수행
				// 전송
				socket.send(input);
				// Status Code 101로 Message 전송 됨
				// 개발자 도구에서 메세지 내용 확인 가능
				
				// 입력창 초기화
				$("#user-input").val("");
			});
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
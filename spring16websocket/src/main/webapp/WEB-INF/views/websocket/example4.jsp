<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" %>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

<h1>회원 채팅방 웹소켓 예제(${room}번방)</h1>

<h2>ID: ${user.id }</h2>
<h2>로그인: ${not empty user}</h2>

<!-- pathvariable 사용 시 가급적 절대경로를 쓰는 것이 용이 -->
<a href="${pageContext.request.contextPath}/login?room=${room}">로그인</a>
<a href="${pageContext.request.contextPath}/login">로그아웃</a>

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
			var uri = "ws://localhost:8089/spring16/member";
			
			// 웹소켓 연결(실제로는 "소켓을 생성했다"라고 표현)
			window.socket = new WebSocket(uri);
		
			// 연결 이후에 수행할 작업들을 예약(콜백, callback) 설정
			// console.log(socket);		
			
			// 연결 후 수행
			// -onopen에서 반드시 방 접속 메세지를 보내야 방 번호 정보를 서버에 전달 가능
			socket.onopen = function(){
				console.log("서버의 방에 접속하기 위한 메세지 발송")
				
				var msg = {
					"room":${room},
					type:"enter"
				};
				
				// JSON.stringify() -> JSON 형태를 String으로 변환
				socket.send(JSON.stringify(msg));
				
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

			//	console.log(JSON.parse(message.data).sender);
			// String 타입으로 전달받은 값(data)을 JSON 타입으로 변환
				var json = JSON.parse(message.data);
			
			//	$("<p>").text("-> " + message.data.sender).appendTo(".text-wrapper");
				$("<p>").text(json.sender + ":").appendTo(".text-wrapper");
				$("<p>").text(json.content + " ["
								+ json.time + "]").appendTo(".text-wrapper");
			
			};
			
			
			// 전송 이벤트
			$("#send").click(function(){
				// 입력값을 불러온다
				var input = $("#user-input").val();

				// 입력값을 json으로 변환
				var message = {
						room:${room},
						type:"message",
						content:input
				};
				
				// 입력값이 없는 경우 function 종료
				if(!input) return;
				
				// 입력값이 있는 경우 나머지 작업 수행
				// 전송
				socket.send(JSON.stringify(message));
				// Status Code 101로 Message 전송 됨
				// 개발자 도구에서 메세지 내용 확인 가능
				
				// JSON 형식을 통해 그룹 번호, 텍스트 내용 등 지정 가능
				
				// 입력창 초기화
				$("#user-input").val("");
			});
		});

		// 종료 이벤트
		// - 종료를 직접 수행하기 전 반드시 server에 종료 메세지를 전송
		// - 메세지를 통해 사용자 정보 및 방 정보 삭제
		$("#disconnect").click(function(){
			var msg ={
					room:${room},
					type: "leave"
			};
			
			socket.send(JSON.stringify(msg));

			// 웹소켓 연결 종료(실제로는 "소켓을 소멸(종료)했다"라고 표현)
			// 1. 소켓 변수를 (공통 접근 가능한)외부에서 선언
			// 2. window 영역에 소켓 변수 선언
			window.socket.close();
		});
	});


</script>
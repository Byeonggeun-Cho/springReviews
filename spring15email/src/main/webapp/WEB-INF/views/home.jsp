<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

<h1>로그인 정보: ${user}</h1>

<h1><a href="login">로그인</a></h1>
<h1><a href="logout">로그아웃</a></h1>

<hr>

<!-- 로그인 상태일 때 인증번호 보내기 버튼과 인증번호 입력창을 표시 -->
<c:if test="${not empty user }">
	<!-- 인증번호 컨트롤러 -> RestController로 처리 -->
	<button id="send">인증번호 보내기</button>
	<br>
	<input id="user-input" type="text"/>
	<button id="confirm">확인</button>
	<p id="result"/>
</c:if>

<!--
	프로젝트에 존재하는
	이미지나 css, js 등과 같은 정적자원(Static Resources)은
	어떤 경로를 통해 불러와야 하는가?
	
	- wepapp/resources	
-->

	<img src="http://localhost:8089/spring15/resources/iceberg.jpg">
	<img src="//localhost:8089/spring15/resources/iceberg.jpg">
	<img src="/spring15/resources/iceberg.jpg">
	
	<img src="<%=request.getContextPath()%>/resources/iceberg.jpg">
	<img src="${pageContext.request.contextPath }/resources/iceberg.jpg">
	<img src="./resources/iceberg.jpg">


<script>
	// [1] send버튼을 누르면 이메일을 보내달라고 비동기 요청을 수행
	// 		- 버튼을 누르면 끝나기 전에 비활성화(disabled)
	// [2] confirm 버튼을 누르면 입력된 인증번호를 검사하여 결과 전달
	//		- 입력이 안되어 있는 경우 검사 수행 X
	$(function(){
		$("#send").click(function(){
			// 한 번 버튼이 눌리면 비활성화
			// $(this).attr("disabled", true);

			// [1]
			$.ajax({
				url: "${pageContext.request.contextPath}/cert/send",
				type: "get",
				beforeSend: function(){
					// 비활성화
					$("#send").prop("disabled", true);
				},
				success: function(resp){
					alert("이메일로 인증번호가 발송되었습니다.");
				}, error: function(err){
					alert("서버에서 오류가 발생했습니다.");
				}, complete: function(){
					// 작업이 완료된 후 활성화
					$("#send").prop("disabled", false);
				}
			});
		});
		
		// [2]
		$("#confirm").click(function(){
			var text = $("#user-input").val();
			var regex = /\d{6}/g;
			
			if(!regex.test(text)) return;
			
			$.ajax({
				url: "${pageContext.request.contextPath}/cert/check",
				type: "get",	// 전송방식
				data: {			// 보내는 데이터
					number: text
				},
				dataType: "text",		// 기대하는 결과값의 형태
				success: function(resp){	// resp == Y or N
					// console.log(resp);
					if(resp == "Y"){
						alert("인증 성공");
						// 이후에 처리할 내용들
						$("#send").prop("disabled", true);
						$("#confirm").prop("disabled", true);
						$("#result").text("인증되었습니다.");
					} else{
						alert("인증번호가 일치하지 않습니다.");
					}
				}, error: function(err){
					// console.log(err);
				}
			});
			
		});
	});
</script>

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
	<input type="text"/>
	<button>확인</button>
</c:if>

<script>
	// [1] send버튼을 누르면 이메일을 보내달라고 비동기 요청을 수행
	$(function(){
		$("#send").click(function(){
			// 한 번 버튼이 눌리면 비활성화
			// $(this).attr("disabled", true);

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
	});
</script>

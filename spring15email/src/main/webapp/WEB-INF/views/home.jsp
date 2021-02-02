<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<h1>로그인 정보: ${user}</h1>

<h1><a href="login">로그인</a></h1>
<h1><a href="logout">로그아웃</a></h1>

<hr>

<!-- 로그인 상태일 때 인증번호 보내기 버튼과 인증번호 입력창을 표시 -->
<c:if test="${not empty user }">
	<!-- 인증번호 컨트롤러 -> RestController로 처리 -->
	<button>인증번호 보내기</button>
	<br>
	<input type="text"/>
	<button>확인</button>
</c:if>

<%@ page contentType="text/html; charset=UTF-8" %>

<body>
	<h1>테스트 페이지</h1>
	
	<h2>로그인 여부: ${user != null}, ${!empty user}, ${not empty user} </h2>
	<h2>로그인 정보: ${sessionScope.user}, ${user}</h2>
	
	<h2><a href="login">로그인</a></h2>
	<h2><a href="logout">로그아웃</a></h2>
	
	<h2><a href="member">회원 전용 페이지</a></h2>
	<h2><a href="normal">일반 페이지</a></h2>
</body>

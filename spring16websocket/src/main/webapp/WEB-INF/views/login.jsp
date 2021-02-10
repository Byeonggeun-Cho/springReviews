<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<h1>로그인</h1>

<form action="login" method="post">
	<input type="hidden" value="${param.room }" name="room">
	아이디: <input type="text" name="id">
	<br><br>
	비밀번호: <input type="password" name="pw">
	<br><br>
	<input type="submit" value="등록">
</form>
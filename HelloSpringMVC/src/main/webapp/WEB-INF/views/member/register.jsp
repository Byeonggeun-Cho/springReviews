<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>멤버 등록하기</title>
</head>
<body>
	<h1>멤버 등록하기</h1>

	<form method="post">
		이름: <input type="text" name="name"><br>
		나이: <input type="text" name="age"><br>
		성별: <input type="text" name="gender"><br>
		주소: <input type="text" name="address"><br>
		
		<input type="submit" value="저장하기">		
	
	</form>
</body>
</html>
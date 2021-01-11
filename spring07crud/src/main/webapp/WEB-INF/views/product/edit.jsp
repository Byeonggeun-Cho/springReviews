<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>edit</title>
</head>
<body>
	<h1>edit.jsp</h1>
	
	<form action="edit" method="post">
		<input name="no" type="hidden" value="${product.no }">
		<br><br>
		제품명: <input name="name" type="text" value="${product.name }">
		<br><br>
		가격: <input name="price" type="text" value="${product.price }">
		<br><br>
		<input type="submit" value="수정">
	</form>
</body>
</html>
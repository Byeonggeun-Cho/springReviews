<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>insert.jsp</title>
</head>
<body>
	<h1>insert.jsp</h1>

<%--
	<form action="${pageContext.request.contextPath }/insert" method="POST">
--%>
	<form action="insert" method="POST">
		이름: <input type="text" name="name" required>
		<br><br>
		가격: <input type="number" name="price" min="0" required>
		<br><br>
		<input type="submit" value="등록">
	</form>

</body>
</html>
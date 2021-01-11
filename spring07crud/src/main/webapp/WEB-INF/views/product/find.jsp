<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>find</title>
</head>
<body>
	<h1>find.jsp</h1>

	<h2>${param.no }번 상품</h2>
	
	${requestScope.product }
	<hr>
	${product}
	
</body>
</html>
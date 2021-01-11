<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>list</title>
</head>
<body>
	<h1>list.jsp</h1>
	
	<c:forEach var="product" items="${list }">
		<h2>
			${product.no}
			/
			${product.name }
			/
			${product.price}
			/
			${product.reg }
		</h2>
	</c:forEach>

</body>
</html>
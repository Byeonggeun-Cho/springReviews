<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>메뉴 목록</title>
</head>
<body>
	<h1>list.jsp</h1>
	
	<c:forEach var="menu" items="${list }">
		<h3>
			${menu }
		
			<img width="100" height="100" src="download2?no=${menu.no }">
			
			<a href="download2?no=${menu.no }">다운로드</a>
			
			${menu.no } /
			${menu.name } /
			${menu.price }원
		</h3>
	
	</c:forEach>

</body>
</html>
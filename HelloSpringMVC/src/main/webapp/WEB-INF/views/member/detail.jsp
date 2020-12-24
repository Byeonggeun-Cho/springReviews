<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>멤버 상세 조회</title>
</head>
<body>
	<h1>멤버 상세 조회</h1>

	<hr>
	
	<span><c:out value="${member.id}"/></span>
	<span><c:out value="${member.name}"/></span>
	<span><c:out value="${member.age}"/></span>
	<span><c:out value="${member.gender}"/></span>
	<span><c:out value="${member.address}"/></span>
</body>
</html>
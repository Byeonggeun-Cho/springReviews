<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>멤버 목록 조회</title>
</head>
<body>
	<h1>멤버 목록 조회</h1>

	<c:forEach var="member" items="${members }">
		<div>
			<span><c:out value="${member.id }"/></span>
				<span>
					<a href="<c:url value='/member/${member.id }'/>">
						<c:out value="${member.name}"/>
					</a>
				</span>
		</div>
	</c:forEach>
</body>
</html>
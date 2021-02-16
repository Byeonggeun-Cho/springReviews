<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<h1>전체 결제 내역</h1>

<c:forEach var="payment" items="${list }">
	<p>
		${payment}
	</p>
	<a href="search?no=${payment.no }">상세보기</a>
	
	<c:if test="${payment.status == '결제완료'}">
		<a href="undo?no=${payment.no}">결제취소</a>
	</c:if>
</c:forEach>


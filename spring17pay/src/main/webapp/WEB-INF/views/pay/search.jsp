<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<h1>결제 정보 조회</h1>
<hr>
<h4>${result }</h4>
<hr>
<ul>
	<li>상품명: ${result.item_name }</li>
	<li>금액: ${result.amount.total}원</li>
	<li>수량: ${result.quantity}개</li>
	<li>주문번호: ${result.partner_order_id}</li>
	<li>승인시각1: ${result.approved_at }</li>
	<li>승인시각2: <fmt:formatDate value="${result.approved_at }"
					pattern="yyyy-mm-dd E a h:mm:ss"/></li>
</ul>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
					pattern="yyyy-MM-dd E a h:mm:ss"/></li>
	<li>현재상태1: ${result.status }</li>
	<li>현재상태2: <c:choose>
					<c:when test="${result.status == 'READY'}">
						<c:out value="결제 대기"/>
					</c:when>
	
					<c:when test="${result.status == 'SUCCESS_PAYMENT'}">
						<c:out value="성공"/>
					</c:when>

					<c:when test="${result.status == 'CANCEL_PAYMENT'}">
						<c:out value="취소"/>
					</c:when>
					
					<c:otherwise>
						<c:out value="파악 불가"/>
					</c:otherwise>
				</c:choose>
	</li>
	<li>상세내역
		<ul>
			<c:forEach var="action" items="${result.payment_action_details }">
				<li>
					${action.payment_action_type } /
					${action.amount } /
					${action.approved_at }
				</li>
			</c:forEach>
		</ul>
	</li>
</ul>
<hr>
<!-- 
	만약 상태가 결제완료라면 취소가 가능하도록 취소 링크를 생성
-->
<c:if test="${result.status == 'SUCCESS_PAYMENT'}">
	<a href="undo?tid=${result.tid}&cancel_amount=${result.amount.total}">취소하기</a>
</c:if>








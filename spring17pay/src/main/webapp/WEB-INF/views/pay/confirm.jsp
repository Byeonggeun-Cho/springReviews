<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" %>

<form action="confirm" method="post">
	<!--
		입력창 추가
		- item_name: 상품명(수정불가)
		- quantity: 수량(수정불가)
		- total: 결제액(수정불가)
		
		상품이 여러 개일 수도 있다는 사실을 숙지
	-->

	상품명: <input type="text" name="item_name" required>
	<br><br>
	수량: <input type="number" name="quantity" value="1" required>
	<br><br>
	결제액: <input type="text" name="total" required>
	<br><br>

	<input type="submit" value="결제하기">
</form>
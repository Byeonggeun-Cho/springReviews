<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>메뉴등록</title>
</head>
<body>
	<h1>메뉴등록</h1>

	<!-- 파일 업로드를 하고싶다면 반드시 POST방식에 enctype="multipart/form-data" -->
	<form action="add" method="POST" enctype="multipart/form-data">
		이름: <input type="text" name="name">
		<br><br>
		종류: 
		<select name="type">
			<option value="">선택하세요</option>
			<option>식사</option>
			<option>음료</option>
			<option>안주</option>
		</select>
		<br><br>
		가격: <input type="text" name="price">
		<br><br>
		
		<!-- 파일 업로드를 위해 사진 첨부란을 추가 -->
		사진: <input type="file" name="im">
		<br><br>
		<input type="submit" value="등록">
	</form>

</body>
</html>
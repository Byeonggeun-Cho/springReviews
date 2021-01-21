<%@ page contentType="text/html; charset=UTF-8" %>

<!-- 
	아이디 중복검사 화면 구성
	- form과는 별개로 창 하나만 가지고 구현
	- ajax를 이용해서 서버에 검사를 요청
	- 결과에 따라 메세지를 출력하거나 기타 처리를 수행
	- 검사 시점 : 사용자가 입력하는 순간(oninput) vs 사용자 입력이 종료된 순간(onblur)
-->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

<script>
//	$(document).on("ready", function(){});
//	$(document).ready(function(){});
//	$().ready(function(){});
	$(function(){
		// 아이디 입력창에 blur 이벤트를 설정
		// -> input으로 인해 발생할 수 있는 서버 과부하를 방지

//		$("input[name=id]").blur(function(){});
		// 아이디 입력창에 input 이벤트를 설정
		$("input[name=id]").on("blur", function(){
			// console.log("입력완료");
			// 비동기 통신은 vanillaJS, jquery, axios 등으로 보낼 수 있다 (선택)
			
			// $.get(주소, {옵션});
			// $.post(주소, 데이터, {옵션});
			$.ajax({
				url: "주소",
				type: "방식",
				dataType: "결과형태",
				data: {전송데이터},
				success: function(){},
				error: function(){},
				complete: function(){}
			});
			
			
		});
	});
</script>
 
 <input type="text" name="id" placeholder="아이디">
<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="Refresh" content="10;url=https://www.naver.com"> <!-- content="1;" : 1초뒤에 화면 전환한다. -->
<title>/03/autoRequest.jsp</title>
</head>
<body>
<h4> 자동 요청을 발생시키는 방법 </h4>
<span id="secArea">10</span>초뒤에 네이버로 이동 함 .! 
<pre>
	1. 서버사이트 방식 : response header 중 Refresh 사용 
		<%
			//response.setIntHeader("Refresh", 1); //매 1초마다 새로운 요청 발생함.		
			Date now = new Date();
		%>
		<%=now %>
	2. 클라이언트 사이드 방식 
		1) HTML : meta 태그 이용.
		2) Javascript : setInterval / setTitmeout , location.reload(): 새로고침 ! ^_^
	
	
</pre>
<script type="text/javascript">
	var span = document.querySelector("#secArea");
	var seconds = 10; //초기값
	var jobId = setInterval(()=> { //콜백함수... ㅎㅎㅎ 
		//얼마만에 한번씩 무엇을 할것인가
		span.innerHTML = --seconds;
		if(seconds ==0) clearInterval(jobId);
	}, 1000); // 중단시키고 화면 넘어야가함.

	setTimeout(function (){
		location.reloads();
	},1000); //일정시간 기다렷다가 한번 작업 하고 끝나 명시적으로 취소 안시켜도됨 ! 
	
	//자바스크립트 디버깅하기..
// 	var test = 23;
// 	setTimeout(function (){
// 		let test2 = 89; //완변벽한 지역변수 ! 
// 		location.reload();
// 		페이지 앞뒤로 가보기
// 		window.history.back();
// 		window.history.go(1) //몇번째 앞페이지로 가꺼냐 / -1 : back과 같은 ;
// 	},1000);
	//작업, 지연시간 정보 필요해

</script>
</body>
</html>
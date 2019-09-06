<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isErrorPage="true"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h4>에러를 처리할 목적의 페이지 ^^ </h4>
<pre>
	<%
		ErrorData ed = pageContext.getErrorData(); //에러데이터 
	%>	
	<%=exception.getMessage() %>
	에러 발생 위치 : <%=ed.getRequestURI() %>
	에러 상태 코드 : <%=ed.getStatusCode() %>
	에러 : <%=ed.getThrowable() %>  <!-- 처리할수도없는 비정상 정보들받는거 -->
	에러는 500바이트 이상 으로 ,,, 
	오늘 203호에서 최종 프로젝트 발표를 햇는데
	한시간 내내 서서 보다 허리 부러지는줄 ...
	가만히 보니여럽긴하더라 ^^^^^^^^^^^^^^^^^^^
	잘할수있겟지? 화이팅하자 !!!!!!!!!!!!!!!!!!!!
	집에가고싶어요 이제 몇분 안남앗다
	머리아파요 ㅠㅠㅠㅠㅠㅠ 숙제가 많네 흑흑흑
	
</pre>
</body>
</html>
<%@page import="java.io.IOException"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<!--     errorPage="/error/eachError.jsp -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>07/errorProcess.jsp</title>
</head>
<body>
<h4> 에러 처리방법</h4>
<%
	if(1==1){
// 		throw new NullPointerException ("강제 발생 예외"); //500에러 나서 위에 errorPage갈려햇는데 못가닌까 404
		throw new IOException("강제발생에외"); //지역,타입별 없어 --> 500코드 
	}
%>
<pre>
	1.지역적 : jsp 페이지 하나를 대상. page 지시자 -> errorPage 속성. (우선순위 제일 높ㅇ아)
	2.전역적 : 대상은 웹어플리케이션 (web.xml -> error-page).  
		1) 발생 예외 타입별 처리 (exception-type)
		2)상태 코드별 처리 (error-code) 우선순위 제일 낮아 
</pre>

</body>
</html>
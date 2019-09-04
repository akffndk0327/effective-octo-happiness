<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>06/sessionDesc.jsp</title>
</head>
<body>
<h4>HttpSession</h4>
<pre>
생성시점 : <%=new Date(session.getCreationTime()) %><!-- 새로거고침해도 안돼 : 한번만든 세션이 계속 돌아가는중 -->
세션아이디 : <%=session.getId() %> <!-- =아이디 같어 Cookie: JSESSIONID=35BCC3FA31EAE958C8D008A8FCD61208 -->
<!-- 세션아이디 : 35BCC3FA31EAE958C8D008A8FCD61208 클라이언트 서버가 같은 데이터 가지고 있다  -->
<!-- 세션이 죽을 경우 알아보기 
1. 30분 후 
2. 클라이언트 브라우저 종료해 -> 어떻게 알고 만료시킴?
=>jssoncookieid 있나없나로 확인해서 서버에 잇는걸 알면 2번째 요청으로 알어. ㅋ  -->
세션의 라이프사이클에 대한 수업 . 
</pre>
</body>
</html>
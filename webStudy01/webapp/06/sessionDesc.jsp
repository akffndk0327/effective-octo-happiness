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
0904~05일 
세션 : - 시간 : 유저가 어플리케이션을 의미있게 사용하고 있는 시간(사용시작~종로, WEB)  
	 - 통로 : 한 세션내에서 두 피어 사이에 설정된 유일한 통로(데이터베이스) 
세션의 생명주기 (lifecycle)
시작 : 최초의 요청이 서버에 전달 될때.
	세션의 프로퍼티들 ! : 생성시점, 식별자(세션아이디) 마지막 요청 시점, 
	이것들을 캡슐화한게 HTTPSession임 ! 
	생성시점 : <%=new Date(session.getCreationTime()) %><!-- 새로거고침해도 안돼 : 한번만든 세션이 계속 돌아가는중 -->
	세션아이디 : <%=session.getId() %> 
	마지막요청 시점 : <%=new Date(session.getLastAccessedTime()) %><!-- 여기는 계속 바껴  -->
	만료 시간 <%=session.getMaxInactiveInterval() %>s
	<%
		session.setMaxInactiveInterval(2*60); //만료시간 재설정 
	%>
	재설정후 :<%=session.getMaxInactiveInterval()	%>초
	<a href="sessionDesc.jsp;jsessionid=<%=session.getId()%>"> 세션 유지를 위한 링크</a> <!-- session.getId() : 현재세션id잇어 -->
	-한 세션내에서 발생하는 요청을 식별하는 방법 
	&lt;session tracking mode&gt;
	1)cookie : JSESSIONID 같은 세션을 식별자를 포함하는 쿠키를 전송하고 저장(응답데이터 받고 브라우저가 저장)하고 재전송하는 방법으로 식별
	2) URL : jsessionid 세션 파라미터를 전송하여 세션을 식별(보안취약,URL rewriting 문제 잇어서 많이 안써 ) 
	3) SSL : Secure Socket Layer) : 두 피어 사이이의 데이터가 암호화되어 전달되며, 암호문내에 세션 식별자 포함.되잇지만 뭔지 몰라
								  : 인증서가 필요함 (개발 위한 openSSL). 제일 안전해 
								  :쿠키와 ssl 같이 사용함 
	<!-- 세션트래킹이 ㅇ됨. 세션아이디 누가 가져가면 위조 할수있어  -->
	<!-- =아이디 같어 Cookie: JSESSIONID=35BCC3FA31EAE958C8D008A8FCD61208 -->
	<!-- 세션아이디 : 35BCC3FA31EAE958C8D008A8FCD61208 클라이언트 서버가 같은 데이터 가지고 있다  -->

종료 : 1) 세션의 만료 시간(ex 30분) 이내에 새로운 요청이 발생하지 않을때 
	 2) 브라우저 종료 : 만료 시간이 지난 후 만료 
	 3) 특정 쿠키 삭제시 (jssoncookieid 원래 브라우저종료하면 쿠키도 삭제됨...)사라진 쿠키는 다시 사용x.근데 모두 같은 jsession쿠기id라고 쓰진않음 
	 4)명시적인 로그아웃(invalidate) : 애는 세션 바로 만료 => 서버사이드 코드여서 
	 <%
// 	 	session.invalidate(); // : 세션에 저장된걸 지운다 => 쿠키허용햇는데도 id가 바뀜 : 서버에서 만료시켜서 !! 
	 %>

</pre>
</body>
</html>
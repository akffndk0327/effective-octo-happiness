<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>07/scopeDesc</title>
</head>
<body>
<h4>scope(영역)</h4>
<pre>
	: 웹앱내에서 데이터(attribute)를 공유하기 위한 저장 공간 
	각 기본 객체가 가진 Map&lt;String, Object&gt;.
	모든 scope는 해당영역에 대한 제어를 담당하는 기본객체와 동일한 생명주기르 가지고 있다 .
	언제까지 살아남는가. 최소한의 저장 scope를 선택함. 
	1.page Scope : PageContext (제일 작아)
	2 request Scope : HttpServletRequest
	3 session Scope : HttpSession
	4 application Scope : ServletContext
	<%
		pageContext.setAttribute("pageAttr", "페이지속성");
		request.setAttribute("requestAttr", "요청속성");
		session.setAttribute("sessionAttr", "세션속성");
		application.setAttribute("applicationAttr", "애플리케이션속성");
	
		pageContext.forward("/02/standard.jsp"); //서버가 사용
// 		request.getRequestDispatcher("/02/standard.jsp")
// 				.forward(request,response); //3개 나와 
// 				.include(request,response); //3개 나와 
		response.sendRedirect(request.getContextPath()+"/02/standard.jsp"); //클라이언트가 사ㅛㅇ ! 외워!! 
	%>
</pre>

</body>
</html>
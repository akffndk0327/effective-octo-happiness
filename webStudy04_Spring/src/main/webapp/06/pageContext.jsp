<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>06/pageContext.jsp</title>
</head>
<body>
<h4>pageContext</h4>
<pre>
	:하나의 jsp페이지에 대한 모든 정보를 가진 객체 
	
	1. 나머지 8개의 기본객체를 확보. 천하무적
	<%=request==pageContext.getRequest() %> <!--  true : 같은 녀석이다 -->
	<%=response ==pageContext.getResponse() %><!--  true : 같은 녀석이다 -->
	<%=request.getContextPath() %> ${pageContext.request.contextPath}  
	<!-- ${pageContext } :el 8개가 전역변수인것처럼 나옴. 자바빈 규약 -->
	2. Scope를 접근할때 
	<%
// 		request.setAttribute("attr", "value"); 
		pageContext.setAttribute("attr", "value", PageContext.REQUEST_SCOPE); //PageContext의 2번째 기능 
	
	%>
		
	<%=request.getAttribute("attr") %>
	<%=pageContext.getAttribute("attr", PageContext.REQUEST_SCOPE) %> <!-- 꺼낼때 여기서 value나오나???? -->
	3. 흐름제어(flow control) : 페이지 이동 : request dispatch 방식(forward, include)
	<%
		String path = "/02/standard.jsp";
// 		RequestDispatcher rd = request.getRequestDispatcher(path);
// 		rd.forward(request, response);
// 		pageContext.forward(path) ; // 이코드가 위에 두줄 대신하고 있음. 위에 두출이 정석인ㅁ. 500에러 낼때
// 		rd.include(request, response); //응답데이터가 a+b -=> 중간에 버퍼 방출 x 			500에러 낼때
// 		pageContext.include(path);
	%>
	4. 에러데이터 확보
	<!-- 지금 못봐... 에러 안보여 ,,,,,, -->
	
	
</pre>
</body>
</html>













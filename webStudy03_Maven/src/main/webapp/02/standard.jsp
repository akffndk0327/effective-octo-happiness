<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>02/standard.jsp</title>
</head>
<body>
<h4> jsp 표준 구성 요소  </h4>
<div style="background-color:yellow;">
	<%=pageContext.getAttribute("pageAttr") %><br/>
	<%=pageContext.getAttribute("requestAttr",PageContext.REQUEST_SCOPE) %><br/>
	<%=session.getAttribute("sessionAttr") %><br/>
	<%=application.getAttribute("applicationAttr") %>

</div>
<pre>
	1. 정적 텍스트 분량 많어: 텍스트, HTML, css, javascript 공통점은 1개 : 클라이언트 사이드에서 동작 함 ! 
	
	2. JSP스크립트 요소 :  <% %>
		1) directive (지시자) : &lt;%@ 지시자명 속성들... @%&gt;
			- page(required) : JSP페이지에 대한 설정 정보(전처리)를 명시. 교재37p
			- include(optional) : 정적 내포. 아래 두개는 필요하면 쓰고 
			- taglib(optional) : 태그 라이브러리를 사용할 때 
		2) scriptlet(스크립틀릿요소) : <% //java code %>
		<%
			System.out.println("어딜에 출력되죠?? => 서버에출력됨"); 
			String today = new Date().toString();  
// 			if(1==1)
// 			return ; 
		%>
		3) expression(표현식) : <%=today %>
							 : 응답 데이터로 "출력"할  요소에 사용. 브라우저는 값만 남고 변수는 서버에서 처리됨. 클라이언트에서는 사용 x  
		4) declaration(선언부) :  전역변수나 메소드 선언에 사용 
		<%!
			public Date globalToday= new Date();
			public void test(){
		}
		
		%>
		
		5) comment(주석) : <%-- --%>
			- client side: HTML, Javascript, java, jsp
			- server side : java, jsp (***). 서버에 노출되면안되닌까 이거 쓰기 ! 
<!-- 			test -->
				<script>
				// 	test
				</script>
				<%
// 					test
				%>
<%-- 				<% test %> --%>

	3. 기본객체(9)
	4. jsp 액션태그
	5. EL(표현언어)
	6. JSTL 스크립트릿 안열고도 쓸수이음 
	<%-- 
	if(1==1) // 의미없는 컴파일 에러 피해갈수있어 
 		throw new NullPointerException("강제발생 예외");
	--%>
	

</pre>
</body>
</html>



















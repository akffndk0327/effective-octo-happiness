<%@page import="kr.or.ddit.vo.MemberVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<form name="logoutForm" method ="post" action="${pageContext.request.contextPath}/logout">

</form>
<%
	MemberVO authMember = (MemberVO)session.getAttribute("authMember");
	if(authMember ==null){
		%>
		<a href = "<%=request.getContextPath()%>/login"> 로그인</a>
		<%
	}else{ //로그인 된 상태
		%>
<%-- 		누가 로그인 햇는디  --%>
		<a href="<%=request.getContextPath() %>/mypage"> <%=authMember.getMem_name() %>님</a> <a href ="#" onclick="document.logoutForm.submit();">로그아웃</a>  <!-- a태그이지만 post방식 임  -->
		<%
	}
%>

</body>
</html>
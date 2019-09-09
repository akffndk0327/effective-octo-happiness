<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<form name="logoutForm" method ="post" action="${pageConText.request.contextPath}/logout">

</form>
<%
	String authMember = (String)session.getAttribute("authMember");
	if(authMember ==null){
		%>
		<a href = "<%=request.getContextPath()%>/login"> 로그인</a>
		<%
	}else{ //로그인 된 상태
		%>
<!-- 		누가 로그인 햇는디  -->
		<%=authMember %>님 <a href ="#" onclick="document.logoutForm.submit">로그아웃</a>  <!-- a태그이지만 post방식 임  -->
		<%
	}
%>

</body>
</html>
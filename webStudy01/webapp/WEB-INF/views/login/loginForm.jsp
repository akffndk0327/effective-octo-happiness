<%@page import="org.apache.commons.lang3.StringUtils"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
	String message = (String)session.getAttribute("message");
// 	케이스는 2개
//	flash attribute
	if(StringUtils.isNoneBlank(message)){
		//세션에서 보고나서 attr 지우기
		session.removeAttribute("message");
		%>
		<script type="text/javascript">
			alert("<%=message%>");
		</script>
		<%		
	}
%>
<form method ="post">
	<ul>
		<li> 
		아이디 :	<input type="text" name ="mem_id" />  
		</li>
		<li> 
		비밀번호 :<input type="text" name ="mem_pass" />
		<input type ="submit" value="로그인"> 
		</li>
	</ul>

</form>

</body>
</html>
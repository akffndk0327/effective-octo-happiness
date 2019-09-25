<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>JSP화면입니다 </title>
</head>
<body>
<h1> jsp화면입니ㅏㄷ.</h1>
<%
	String valueId = request.getParameter("id");

%>
<%=valueId %> 님 환영합니다 
</body>
</html>
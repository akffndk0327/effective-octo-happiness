<%@page import="java.net.URLDecoder"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h4>동일경로에서 쿠키화인.</h4>
<%
	Cookie[] cookies = request.getCookies(); //재전송된 쿠키 확보
	//나한테 필요한 쿠ㅋ ㅣ찾기
	Cookie searched = null;
	if (cookies != null) { //먼저 확인해야해
		for (Cookie tmp : cookies) {
// 				if ("testCookie".equals(tmp.getName())) {
// 					searched = tmp;
// 					break;
// 				}
			%>
URLDecoder : <%=URLDecoder.decode(tmp.getValue(),"UTF-8")%> <br>
			
			<%
			
		}
	}
%>
</body>
</html>
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
<h4>다른경로에서 쿠키확인.</h4>
<%
	Cookie[] cookies = request.getCookies();
	//나한테 필요한 쿠ㅋ ㅣ찾기
	Cookie searched = null;
	if (cookies != null) {
		for (Cookie tmp : cookies) {
			// 	if ("testCookie".equals(tmp.getName())) {
			// 		searched = tmp;
			// 		break;
			// 	}
			%>
URLDecoder : <%=URLDecoder.decode(tmp.getValue(),"UTF-8")%> <br>
			
			<%
			
			
		}
	}
%>

</body>
</html>
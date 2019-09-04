<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>01/resourceIdentify.jsp</title>
</head>
<body>
<pre>
URI(Uniform Resource Identifier) : 밑에 3개를 포괄한거 . 밑에는 통칭하는 말 
URL(Uniform Resource Locatet) : 위치자. 자원식별시 위치로 식별. 단점 : 다른자원이 올수도있어 
URN(Uniform Resource Name) : 자원에 이름 붙여놔 . 명부 필요 .모든 자원 등록하면 많은시간 메모리 낭비. 동명의 자원 구별 어려워
URC(Uniform Resource Content) : ex)책을 식별할때 출판사로 식별 ? - 그럼 출판사 책 다 검색해야해 별로임..
<%
	String uri = request.getRequestURI();
	String url = request.getRequestURL().toString();
	
%>
uri : <%=uri %>
url : <%=url %>

경로 표기방식
1. 상대경로 표기 : 현재 위치를 기준으로 자원을 식별하는 방법 .
ex) ../images/Desert.jpg
</pre>
<img src="../images/Desert.jpg">
<pre>
경로 표기방식
2. 절대경로 표기 : 경로의 루트부터 전체를 표기하여 식별하는 방법 
ex) http ://localhost/webStudy01/images/Desert.jpg
현재 위치(주소의 사용위치)-클라이언트사이드 에서 기억된 정보를 생략 가능
**클라이언트 사이드 절대경로 표시 : 도메인이후의 모든 경로가 표기.
**서버 사이드 절대경로 표기 : context name 이후의 모든 경로가 표기됨.
</pre>

<!-- <img src="http://localhost/webStudy01/images/Desert.jpg"> -->
<!-- <img src="//localhost/webStudy01/images/Desert.jpg"> -->
<!-- <img src="/webStudy01/images/Desert.jpg"> -->
<pre>
경로 표기방식
2. 절대경로 표기 : 너무 많이 지워서 잘못된 경로로 보여줌
현재 위치(주소의 사용위치)에서 기억된 정보를 생략 가능
</pre>
<img src="webStudy01/images/Desert.jpg">

</body>
</html>
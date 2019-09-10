<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>01/requestDesc.jsp</title>
<script type="text/javascript">
	console.log("test"); //콘솔은 클라이언트에있는거 
</script>
</head>
<body>
<h4> HttpServletRequest의 메소드</h4>
<pre>
	characterEncoding : <%=request.getCharacterEncoding() %> :" &lt;% %&gt;" 표현식 
	contentLength : <%=request.getContentLength() %>
	contextType : <%=request.getContentType() %> : body message mime(ex) text/xml, audio/mpeg)
	contextPath : <%=request.getContextPath()%>	 ** 가장많이 쓰고 client  side 절대 경로에 사용  "/webStudy01"
	requestURI : <%=request.getRequestURI() %>
	
	&lt;Server Info&gt; local =server
	localAddr : <%=request.getLocalAddr() %> 
	loacalName : <%=request.getLocalName() %>
	localPort: <%=request.getLocalPort() %> <!-- 80 -->
	
	&lt;Client Info&gt; remote= client
	remoteAddr : <%=request.getRemoteAddr() %> : 클라이언트의 ip 주소 정보
	remoteHost : <%=request.getRemoteHost() %> 
	remotePort : <%=request.getRemotePort() %>  : 클라이언트 ip 소스 
	queryString : <%=request.getQueryString() %> : 폼태그없이 바디없이 데이터 보내는 방법
	serverName : <%=request.getServerName() %> :서버의 도메인 이름
	serverPort : <%=request.getServerPort() %>
	servletContext : <%=request.getServletContext().hashCode() %> <!-- org.apache.catalina.core.ApplicationContextFacade@354d391f-->, hashcode :894253343 
	<%=request.getServletContext().getContextPath() %>
	
	
</pre>
<!-- <img src="http://localhost/webStudy01/images/Jellyfish.jpg"> 현재위치는 현재브라우저가 가갖고있는 위치 => 01ㅇ있는 젤리피시 찾을꺼야 근데 젤리피시는 다른데잇어상위로 올라가기 -->
<!-- <img src="/localhost/webStudy01/images/Jellyfish.jpg"> -->
<!-- <img src="/webStudy01/images/Jellyfish.jpg"> --> 
<img src="<%=request.getContextPath()%>/images/Jellyfish.jpg"> ** 가장많이 쓰고 client  side 절대 경로에 사용 
</body>
</html>
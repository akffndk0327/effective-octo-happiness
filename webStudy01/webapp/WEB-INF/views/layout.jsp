<%@page import="org.apache.commons.lang3.StringUtils"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" buffer="20kb" %>
<!DOCTYPE html>
<html>
   <head>
<meta charset="UTF-8">
<title>Insert title here</title>
<!--href태그 클라이언트 사이드 방식-->
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/main.css">
<script type="text/javascript" src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<script type="text/javascript">
	$.getContextPath = function() {
		return "<%=request.getContextPath()%>";
	}
</script>


   </head>
   <body>
   <!-- jsp 서버  iframe 클라이언트-->
      
      <div id="header">
         <jsp:include page="/includee/header.jsp" ></jsp:include>
<%--          <iframe src="<%=request.getContextPath() %>/includee/header.jsp"></iframe> --%>
      </div>
      
      <div id="leftMenu">
         <jsp:include page="/includee/left.jsp"></jsp:include>
<%--          <iframe src="<%=request.getContextPath() %>/includee/left.jsp"></iframe> --%>
      </div>
      <div id = "contents">
 <%
   String url = (String) request.getAttribute("page");
   if(StringUtils.isBlank(url)){
%>
스코프에 값이 없으면 이 화면 띄우고
1. server side 모듈화 : action 태그
2. client side 모듈화 : iframe
<%
   }else{
      pageContext.include(url);
   }
%>

      </div>
      
      <div id="footer">
         <jsp:include page="/includee/footer.jsp"></jsp:include>
<%--          <iframe src="<%=request.getContextPath() %>/includee/footer.jsp"></iframe> --%>
      </div>
   </body>
</html>
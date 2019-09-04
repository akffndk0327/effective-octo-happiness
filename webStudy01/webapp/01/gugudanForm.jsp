<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
필요한 구구단의 최소단과 최대단 입력 
<form action="<%=request.getContextPath() %>/02/gugudan.jsp" method="post"> <!-- post -> get수정, ->post->get --> 
<input type ="number" name="minDan"/>
<input type ="number" name="maxDan"/> <!--  요청받을수있게 만듬 -->
<input type ="submit" value="전송" />
</form>
</body>
</html>
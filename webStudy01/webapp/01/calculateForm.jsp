<%@page import="kr.or.ddit.servlet01.CalculateServlet.OperatorType"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%=request.getServletContext().hashCode() %>

<!-- 과제명 : 사칙연산기 내일 오전 10시.... -->
<!-- 1. homework 컨텍스트로 프로젝트 생성. -->
<!-- 2. webStudy01 대신 homework에서 작업... -->
<!-- 3. 연산의 결과는 plain으로 .... MIME설정 하기.. ^_^ 아이고 포맷팅도 해야함.... -->
<form action="<%=request.getContextPath()%>/calculator" method="post">
	연산을 수행하는 클라이언트의 이름 : <input type="text" name="name" />
	<br />
	<input type="number" name="leftOp"/>
	<select name="operator">
	<option value=""> 연산자선택 </option> 
<!-- 	정상적인 연산자 넘어갓는지 확인  -->
		<%
		for(OperatorType tmp : OperatorType.values()){
			%>
			<option value = "<%=tmp.name() %>"><%=tmp.getSign() %> </option>
			<%
		}
		%>
<!-- 		<option value="PLUS"> + </option> -->
<!-- 		<option value="MINUS"> - </option> -->
<!-- 		<option value="MULTIPLY"> * </option> -->
<!-- 		<option value ="DIVIDE"> / </option> -->
	</select>
	<input type="number" name="rightOp"/>
	<button type="submit"> = </button>

</form>
</body>
</html>
<%@page import="kr.or.ddit.vo.ProdVO"%>
<%@page import="java.util.List"%>
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
	List<ProdVO> prodList = (List)request.getAttribute("prodList");
%>
<table>
	<thead>
		<tr>
			<th>상품코드</th>
			<th>상품명</th>
			<th>분류명</th>
			<th>거래처명</th>
			<th>구매가</th>
			<th>판매가</th>
			<th>마일리지</th>
		</tr>
	</thead>
	<tbody>
	<%
		for(ProdVO prod : prodList){
	%>
		<tr>
			<td><%=prod.getProd_id() %></td>
			<td><%=prod.getProd_name() %></td>
			<td><%=prod.getLprod_nm() %></td>
			<td><%=prod.getBuyer_name() %></td>
			<td><%=prod.getProd_cost() %></td>
			<td><%=prod.getProd_price() %></td>
			<td><%=prod.getProd_mileage() %></td>
		</tr>
	<%
		}
	%>
	</tbody>
</table>
</body>
</html>
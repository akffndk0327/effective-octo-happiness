<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

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
	<%--
		
// 		for(ProdVO prod : prodList){
// 		pageContext.setAttribute("prod", prod);
 	--%>
		<c:forEach var="prod" items="${prodList }">

			<tr>
				<td>${prod.prod_id}</td>
				<td>${prod.Prod_name}</td>
				<td>${prod.Lprod_nm}</td>
				<td>${prod.Buyer_name}</td>
				<td>${prod.Prod_cost}</td>
				<td>${prod.Prod_price}</td>
				<td>${prod.Prod_mileage}</td>
			</tr>
		</c:forEach>

	</tbody>
</table>
</body>
</html>
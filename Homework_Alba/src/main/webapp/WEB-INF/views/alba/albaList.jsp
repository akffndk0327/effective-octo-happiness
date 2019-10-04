<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet"   href="${pageContext.request.contextPath }/bootstrap-4.3.1-dist/css/bootstrap.min.css">
<script type="text/javascript" src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<script type="text/javascript"   src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
<script type="text/javascript"   src="${pageContext.request.contextPath }/bootstrap-4.3.1-dist/js/bootstrap.min.js"></script>
</head>
<body>
<table>
	<thead>
		<tr>
			<th> 아이디</th>
			<th>이름 </th>
			<th>나이</th>
			<th>주소</th>
			<th>번호</th>
			<th>스펙</th>
			<th>경력</th>
			<th>성별</th>
		</tr>
	</thead>
	<tbody id ="listBody">
		<tr>	
			<td>아이디</td>
			<td>이름</td>
			<td>3</td>
			<td>4 </td>
			<td> 5</td>
			<td>6 </td>
		</tr>
	</tbody>
</table>
<%-- <form id="listForm" action="${pageContext.request.contextPath }/alba/albaView.do"> --%>
<!--  <input type="hidden" name="who"/>  -->
<!-- </form> -->
<script type="text/javascript">
var listBody = $("#listBody");
$.ajax({
	url : "alba/albaList.do",
	method : "get",
	dataType : "json",
	success : function(resp) {
		code="";
		$(resp).each(function(index, alba) {
				let trTag = $("<tr>").append(
					$('<td>').text(alba.al_id),
					$('<td>').text(alba.al_name),
					$('<td>').text(alba.al_AGE),
					$('<td>').text(alba.al_ADDRESS),
					$('<td>').text(alba.al_HP),
					$('<td>').text(alba.al_SPEC),
					$('<td>').text(alba.al_CAREER),
					$('<td>').text(alba.al_GEN)
				).prop("id", alba.al_id);
			code.push(trTag);
			})
		listBody.html(code);
	},
	error : function(errorResp) {
		console.log(errorResp.status);
	}

})





</script>
</body>
</html>




















<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
</head>
<body>
<table>
	<thead>
		<tr>
			<th>회원아이디</th>
			<th>회원명</th>
			<th>휴대폰</th>
			<th>이메일</th>
			<th>주소</th>
			<th>마일리지</th>
		</tr>
	</thead>
	<tbody id ="listBody">
	
	</tbody>
</table>
<%-- ui는 동기방식, 실제 데이터는 비동기 방식으로  --%>
<script type="text/javascript">
	 var listBody = $("#listBody");
	$.ajax({
// 		data : "",
		dataType : "json",
		success : function(resp) {
			let trTags = [];
			$(resp).each(function(index, member) {
				let trTag=$("<tr>").append(
					$('<td>').text(member.mem_id),	
					$('<td>').text(member.mem_name),	
					$('<td>').text(member.mem_hp),	
					$('<td>').text(member.mem_mail),	
					$('<td>').text(member.mem_mileage)
				).prop("id", member.mem_id);
				trTags.push(trTag);
			})
			listBody.html(trTags);
		},
		error : function(errorResp) {
			console.log(errorResp.status);
		}

	})
// 	listBody.on("click","tr",function(){
// 		console.log($(this).prod("id"));
// 	})
</script>
</body>
</html>









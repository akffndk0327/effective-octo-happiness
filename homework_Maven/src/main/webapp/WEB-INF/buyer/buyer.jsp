<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>

<style>
td {
	width: 20%;
	height: auto;
}

p:hover {
	background: crimson;
}

#detail{
	width : 80%;
	padding : 50px;
}
</style>
</head>
<body>
<div id="mainForm">
<div>
	<table class="table table-bordered">
		<tr>
			<td>거래처 이름</td>
			<td>거래처 상세 정보</td>
		</tr>
		<tr>
			<td id="name"></td>
			<td id="detail"></td>
		</tr>
	</table>
</div>
</div>
<!-- Modal -->
<div id="insertModal" class="modal fade" role="dialog">
  <div class="modal-dialog">
    <!-- Modal content-->
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal">&times;</button>
        <h4 class="modal-title">거래처 추가</h4>
      </div>
      <form id="insertForm" name="insertForm" action="post">
      <div class="modal-body">
	      <table border=1 class='table talbe-striped'>
			<tr>
				<td>BUYER_ID</td>
				<td><input type="text" id="id" name="buyer_id"></td>
			</tr>
			<tr>
				<td>거래처명</td>
				<td><input type="text" id="buyer_name" name="buyer_name"></td>
			</tr>
			<tr>
				<td>대분류</td>
				<td><input type="text" id="lgu" name="buyer_lgu"></td>
			</tr>
			<tr>
				<td>은행 분류번호</td>
				<td><input type="text" id="bankNo" name="buyer_bankno"></td>
			</tr>
			<tr>
				<td>은행명</td>
				<td><input type="text" id="bankName" name="buyer_bankname"></td>
			</tr>
			<tr>
				<td>우편번호</td>
				<td><input type="text" id="zip" name="buyer_bankname"></td>
			</tr>
			<tr>
				<td>주소</td>
				<td><input type="text" id="add1" name="buyer_bankname"></td>
			</tr>
			<tr>
				<td>상세주소</td>
				<td><input type="text" id="add2" name="buyer_add1"></td>
			</tr>
			<tr>
				<td>이메일</td>
				<td><input type="text" id="email" name="buyer_mail"></td>
			</tr>
		</table>
      </div>
      </form>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal" id="insert">추가하기</button>
        <button type="button" class="btn btn-default" data-dismiss="modal">취소</button>
      </div>
    </div>
  </div>
</div>
<!-- Modal -->
<script type="text/javascript">
$(function() {
	$.ajax({
		dataType : 'get',
		success : function(result) {
			code = "";
			$.each(result, function(i, v) {
				code += "<p id='" + result[i].buyer_id + "'>" + result[i].buyer_name + "</p>";
			})
			
			$('#name').html(code);
		},
		error : function(xhr) {
			alert("상태 : " + xhr.status);
		}
	})
	
// 	$('#name').on('click', 'p', function() {
// 		idvalue = $(this).attr('id');
		
// 		$.ajax({
<%-- 			url : '<%=request.getContextPath()%>/buyer/BuyerListServlet', --%>
// 			type : 'post',
// 			dataType : 'json',
// 			data : {"buyer_id" : idvalue},
// 			success : function(result) {
// 				code = "<table border=1 class='detailTable talbe-striped'>";
// 				code += "<tr><td>BUYER_ID</td>";
// 				code += "<td>" + result.buyer_id + "</td></tr>";
// 				code += "<tr><td>거래처명</td>";
// 				code += "<td>" + result.buyer_name + "</td></tr>";
// 				code += "<tr><td>대분류</td>";
// 				code += "<td>" + result.buyer_lgu + "</td></tr>";
// 				code += "<tr><td>은행</td>";
// 				code += "<td>" + result.buyer_bank + "</td></tr>";
// 				code += "<tr><td>은행</td>";
// 				code += "<td>" + result.buyer_bankname + "</td></tr>";
// 				code += "<tr><td>이메일</td>";
// 				code += "<td>" + result.buyer_mail + "</td></tr>";
// 				code += "<tr><td>주소</td>";
// 				code += "<td>" + result.buyer_add1 + "</td></tr>";
				
// 				code += "</table>";
				
// 				$('#detail').html(code);
// 			},
// 			error : function(xhr) {
// 				alert("상태 : " + xhr.status);
// 			}
			
// 		})
// 	})
	
	
	
	})
</script>
</html>
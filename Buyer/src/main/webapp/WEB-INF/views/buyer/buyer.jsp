<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
	background: lightblue;
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
<button id="btnInsertBuyer" type="button" data-toggle="modal" data-target="#insertModal">거래처 추가</button>
<button id="btnUpdateBuyer" type="button" data-toggle="modal" data-target="#updateModal">거래처 수정</button>
<button id="btnDeleteBuyer" type="button">거래처 삭제</button>
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
				<td>이메일</td>
				<td><input type="text" id="email" name="buyer_mail"></td>
			</tr>
			<tr>
				<td>주소</td>
				<td><input type="text" id="addr" name="buyer_add1"></td>
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
<div id="updateModal" class="modal fade" role="dialog">
  <div class="modal-dialog">
    <!-- Modal content-->
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal">&times;</button>
        <h4 class="modal-title">거래처 수정</h4>
      </div>
      <form id="updateForm" name="updateForm" action="post">
      <div class="modal-body">
	      <table border=1 class='table talbe-striped'>
			<tr>
				<td>BUYER_ID</td>
				<td><input type="text" id="upid" name="buyer_id" readonly="readonly"></td>
			</tr>
			<tr>
				<td>거래처명</td>
				<td><input type="text" id="upname" name="buyer_name"></td>
			</tr>
			<tr>
				<td>대분류</td>
				<td><input type="text" id="uplgu" name="buyer_lgu"></td>
			</tr>
			<tr>
				<td>은행 분류번호</td>
				<td><input type="text" id="upbankNo" name="buyer_bankno"></td>
			</tr>
			<tr>
				<td>은행명</td>
				<td><input type="text" id="upbankName" name="buyer_bankname"></td>
			</tr>
			<tr>
				<td>이메일</td>
				<td><input type="text" id="upemail" name="buyer_mail"></td>
			</tr>
			<tr>
				<td>주소</td>
				<td><input type="text" id="upaddr" name="buyer_add1"></td>
			</tr>
		</table>
      </div>
      </form>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal" id="update">수정하기</button>
        <button type="button" class="btn btn-default" data-dismiss="modal">취소</button>
      </div>
    </div>
  </div>
</div>
<script type="text/javascript">
$(function() {
	$.ajax({
		dataType : 'json',
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
	
	$('#name').on('click', 'p', function() {
		idvalue = $(this).attr('id');
		
		$.ajax({
			url : '${pageContext.request.contextPath}/buyer/DetailBuyer',
			type : 'post',
			data : {"buyer_id" : idvalue},
			success : function(result) {
				code = "<table border=1 class='detailTable talbe-striped'>";
				code += "<tr><td>BUYER_ID</td>";
				code += "<td>" + result.buyer_id + "</td></tr>";
				code += "<tr><td>거래처명</td>";
				code += "<td>" + result.buyer_name + "</td></tr>";
				code += "<tr><td>대분류</td>";
				code += "<td>" + result.buyer_lgu + "</td></tr>";
				code += "<tr><td>은행</td>";
				code += "<td>" + result.buyer_bank + "</td></tr>";
				code += "<tr><td>은행</td>";
				code += "<td>" + result.buyer_bankname + "</td></tr>";
				code += "<tr><td>이메일</td>";
				code += "<td>" + result.buyer_mail + "</td></tr>";
				code += "<tr><td>주소</td>";
				code += "<td>" + result.buyer_add1 + "</td></tr>";
				
				code += "</table>";
				
				$('#detail').html(code);
			},
			error : function(xhr) {
				alert("상태 : " + xhr.status);
			},
			dataType : 'json'
		})
	})
	
	$('#btnUpdateBuyer').on('click', function() {
		idvalue = $(this).parents('#mainForm').find('.detailTable tr:nth-child(1) td:nth-child(2)').text();
		nmvalue = $(this).parents('#mainForm').find('.detailTable tr:nth-child(2) td:nth-child(2)').text();
		lguvalue = $(this).parents('#mainForm').find('.detailTable tr:nth-child(3) td:nth-child(2)').text();
		bNovalue = $(this).parents('#mainForm').find('.detailTable tr:nth-child(4) td:nth-child(2)').text();
		bNMvalue = $(this).parents('#mainForm').find('.detailTable tr:nth-child(5) td:nth-child(2)').text();
		bMail = $(this).parents('#mainForm').find('.detailTable tr:nth-child(6) td:nth-child(2)').text();
		bAdd = $(this).parents('#mainForm').find('.detailTable tr:nth-child(7) td:nth-child(2)').text();
		$('#upid').val(idvalue);
		$('#upname').val(nmvalue);
		$('#uplgu').val(lguvalue);
		$('#upbankNo').val(bNovalue);
		$('#upbankName').val(bNMvalue);
		$('#upemail').val(bMail);
		$('#upaddr').val(bAdd);
		
		
		$('#update').on('click', function() {
			upid = $('#upid').val();
			upname = $('#upname').val();
			uplgu = $('#uplgu').val();
			upbankNo = $('#upbankNo').val();
			upbankName = $('#upbankName').val();
			upemail = $('#upemail').val();
			upaddr = $('#upaddr').val();
			
			$.ajax({
				url : '${pageContext.request.contextPath}/buyer/UpdateBuyer',
				method : 'post',
				data : {
							"buyer_id" : upid,
							"buyer_name" : upname,
							"buyer_lgu" : uplgu,
							"buyer_bankNo" : upbankNo,
							"buyer_bankname" : upbankName,
							"buyer_mail" : upemail,
							"buyer_add1" : upaddr
						},
				success : function(result) {
					alert("수정 성공");
				},
				error : function(xhr) {
					alert("상태 : " + xhr.status);
				},
				dataType : 'json'
			})
		})
	})
	
	$('#btnDeleteBuyer').on('click', function() {
		idvalue = $(this).parents('#mainForm').find('.detailTable tr:nth-child(1) td:nth-child(2)').text();
		alert(idvalue);
		$.ajax({
			url : '${pageContext.request.contextPath}/buyer/DeleteBuyer',
			type : 'post',
			data : {"buyer_id" : idvalue},
			success : function(result) {
				alert("삭제 성공");
			},
			error : function(xhr) {
				alert("상태 : " + xhr.status);
			},
			dataType : 'json'
		})
	})
	
	
	
	$('#insert').on('click', function() {
			insertDatas = $('#insertForm').serializeArray();
			console.log(insertDatas);

			$.ajax({
				url : '${pageContext.request.contextPath}/buyer/InsertBuyer',
				type : 'post',
				data : insertDatas,
				dataType : 'json',
				success : function(res) {
					alert("성공");
				},
				error : function(xhr) {
					alert("상태 : " + xhr.status);
				}

			})
		})
	})
</script>
</html>
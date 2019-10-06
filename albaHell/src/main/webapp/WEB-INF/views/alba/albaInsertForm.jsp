<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<title>알바 추가</title>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
</head>
<body>
	<div class="container">
		<h2>알바생 추가</h2>
		<div style="text-align: right">
			<input type="button" value="추가" id="btn-save" />
		</div>

		<form id="albaForm"
			action="${pageContext.request.contextPath}/alba/albaInsert.do"
			enctype="multipart/form-data" method="POST">
			<table class="table">
				<tr>
					<th>알바생코드</th>
					<td><input id="al_id" name="al_id" type="text" /></td>
				</tr>
				<tr>
					<th>이름</th>
					<td><input id="al_name" name="al_name" type="text" /></td>
				</tr>
				<tr>
					<th>나이</th>
					<td><input id="al_age" name="al_age" type="text" /></td>
				</tr>
				<tr>
					<th>주소</th>
					<td><input id="al_address" name="al_address" type="text" /></td>
				</tr>
				<tr>
					<th>휴대폰</th>
					<td><input id="al_hp" name="al_hp" type="text" /></td>
				</tr>
				<tr>
					<th>특기사항</th>
					<td><input id="al_spec" name="al_spec" type="text" /></td>
				</tr>
				<tr>
					<th>비고</th>
					<td><input id="al_desc" name="al_desc" type="text" /></td>
				</tr>
				<tr>
					<th>학력</th>
					<td><select id="gr_code" name="gr_code">
							<option value="G001">고졸</option>
							<option value="G002">초대졸</option>
							<option value="G003">대졸</option>
							<option value="G004">석사</option>
							<option value="G005">박사</option>
					</select></td>
				</tr>
				<tr>
					<th>경력사항</th>
					<td><input id="al_career" name="al_career" type="text" /></td>
				</tr>
				<tr>
					<th>성별</th>
					<td><select id="al_gen" name="al_gen">
							<option value="F">여</option>
							<option value="M">남</option>
					</select></td>
				</tr>
				<tr>
					<th>혈액형</th>
					<td><select id="al_btype" name="al_btype">
							<option value="A">A형</option>
							<option value="B">B형</option>
							<option value="O">O형</option>
							<option value="AB">AB형</option>
					</select></td>
				</tr>
				<tr>
					<th>이메일</th>
					<td><input id="al_mail" name="al_mail" type="text"
						value="${alba.al_mail}" /></td>
				</tr>
				<tr>
					<th>자격증</th>
					<td>
						<div id="license">
							<div id="appendDiv"></div>
							<input type="button" value="자격증 추가" id="btn-add-licAlba"
								class="btn btn-primary .btn-sm" data-toggle="modal"
								data-target="#myModal" style="margin-top: 20px;" />
						</div>
					</td>
				</tr>
			</table>
		</form>
	</div>

	<!-- 모달 -->
	<div class="modal" id="myModal">
		<div class="modal-dialog">
			<div class="modal-content">

				<!-- Modal Header -->
				<div class="modal-header">
					<h4 class="modal-title">자격증 추가</h4>
					<button type="button" class="close" data-dismiss="modal">&times;</button>
				</div>

				<!-- Modal body -->
				<div class="modal-body">
					<select id="License" name="License_type">
						<c:forEach items="${license}" var="license">
							<option value="${license.lic_code }">${license.lic_name}</option>
						</c:forEach>
					</select>
				</div>

				<!-- Modal footer -->
				<div class="modal-footer">
					<button type="button" class="btn btn-success" data-dismiss="modal"
						id="LicBtn">추가</button>
					<button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>
				</div>
			</div>
		</div>
	</div>

	<script type="text/javascript"> 
        //추가버튼 눌렀을때의 이벤트
        $('#btn-save').on('click',function(){
            $("#albaForm").submit();
        })

        //자격증추가
        $('#LicBtn').on('click',function(){
            var text  = $('#License option:selected').text();
            var value = $('#License option:selected').val();
            
            var html = "<span style='margin-right:20px;'>"+text+"</span>"
                     + "<input type='hidden' name='lic_code' value='"+value+"' />"
                     + "<input type='file'   name='lic_image' />"
                     + "<br>"
                     ;
                     
            $('#appendDiv').append(html);
        })
    </script>
</body>
</html>
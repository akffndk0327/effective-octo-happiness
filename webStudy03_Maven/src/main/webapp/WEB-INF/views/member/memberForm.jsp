<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet"	href="${pageContext.request.contextPath }/bootstrap-4.3.1-dist/css/bootstrap.min.css">
<style type="text/css">
.error {
	color: red;
}
</style>
<script type="text/javascript"	src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<script type="text/javascript"	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
<script type="text/javascript"	src="${pageContext.request.contextPath }/bootstrap-4.3.1-dist/js/bootstrap.min.js"></script>
<meta charset="UTF-8">
<title>신규 가입</title>

<c:if test="${not empty sessionScope.message }">
	<script type="text/javascript">
		alert("${sessionScope.message}");
	</script>
</c:if>
</head>
<body>
	<%-- 	<jsp:useBean id="member" class="kr.or.ddit.vo.MemberVO" scope="request"></jsp:useBean> --%>
	<%-- 	<jsp:useBean id="errors" class="java.util.HashMap" scope="request"></jsp:useBean> --%>
	<form method="post" enctype="multipart/form-data">
		<table>
			<tr>
				<th>회원아이디</th>
				<td><input type="text" required class="form-control"
					name="mem_id" id="mem_id" readonly value="${member.mem_id}" />
					<button type="button" id="idCheck">중복확인</button> <span
					class="errors">${errors["mem_id"]}</span></td>
			</tr>
			<tr>
				<th>비밀번호</th>
				<td><input type="text" required class="form-control"
					name="mem_pass" value="${member.mem_pass}" /> 
					<span class="errors">${errors["mem_pass"]}</span></td>
			</tr>
			<tr>
				<th>이름</th>
				<td><input type="text" required class="form-control"
					name="mem_name" value="${member.mem_name }" /> <span
					class="errors">${errors["mem_name"]}</span></td>
			</tr>
			<tr>
				<th>이미지</th>
				<td>
					<input type="file" 	name="mem_image"/> 
					
					</td>
			</tr>
			<tr>
				<th>주민번호1</th>
				<td><input type="text" class="form-control" name="mem_regno1"
					value="${member.mem_regno1 }" /><span class="errors">${errors["mem_regno1"]}</span></td>
			</tr>
			<tr>
				<th>주민번호2</th>
				<td><input type="text" class="form-control" name="mem_regno2"
					value="${member.mem_regno2 }" /><span class="errors">${errors["mem_regno2"]}</span></td>
			</tr>
			<tr>
				<th>생일</th>
				<td><input type="date" class="form-control" name="mem_bir"
					value="${member.mem_bir }" /><span class="errors">${errors["mem_bir"]}</span></td>
			</tr>
			<tr>
				<th>우편번호</th>
				<td><input type="text" required class="form-control"
					name="mem_zip" value="${member.mem_zip}" /><span class="errors">${errors["mem_zip"]}</span></td>
			</tr>
			<tr>
				<th>주소1</th>
				<td><input type="text" required class="form-control"
					name="mem_add1" value="${member.mem_add1 }" /><span class="errors">${errors["mem_add1"]}</span></td>
			</tr>
			<tr>
				<th>주소2</th>
				<td><input type="text" required class="form-control"
					name="mem_add2" value="${member.mem_add2 }" /><span class="errors">${errors["mem_add2"]}</span></td>
			</tr>
			<tr>
				<th>집전화</th>
				<td><input type="text" class="form-control" name="mem_hometel"
					value="${member.mem_hometel }" /><span class="errors">${errors["mem_hometel"]}</span></td>
			</tr>
			<tr>
				<th>회사전화</th>
				<td><input type="text" class="form-control" name="mem_comtel"
					value="${member.mem_comtel }" /><span class="errors">${errors["mem_comtel"]}</span></td>
			</tr>
			<tr>
				<th>폰번호</th>
				<td><input type="text" class="form-control" name="mem_hp"
					value="${member.mem_hp }" /><span class="errors">${errors["mem_hp"]}</span></td>
			</tr>
			<tr>
				<th>이메일</th>
				<td><input type="text" required class="form-control"
					name="mem_mail" value="${member.mem_mail }" /><span class="errors">${errors["mem_mail"]}</span></td>
			</tr>
			<tr>
				<th>직업</th>
				<td><input type="text" class="form-control" name="mem_job"
					value="${member.mem_job}" /><span class="errors">${errors["mem_job"]}</span></td>
			</tr>
			<tr>
				<th>취미</th>
				<td><input type="text" class="form-control" name="mem_like"
					value="${member.mem_like}" /><span class="errors">${errors["mem_like"]}</span></td>
			</tr>
			<tr>
				<th>기념일</th>
				<td><input type="text" class="form-control" name="mem_memorial"
					value="${member.mem_memorial }" /><span class="errors">${errors["mem_memorial"]}</span></td>
			</tr>
			<tr>
				<th>기념일자</th>
				<td><input type="date" class="form-control"
					name="mem_memorialday" value="${member.mem_memorialday }" /><span
					class="errors">${errors["mem_memorialday"]}</span></td>
			</tr>
			<tr>
				<th>마일리지</th>
				<td><input type="number" class="form-control"
					name="mem_mileage" value="${member.mem_mileage }" /><span
					class="errors">${errors["mem_mileage"]}</span></td>
			</tr>
			<tr>
				<th>탈퇴여부</th>
				<td><input type="text" class="form-control" name="mem_delete"
					value="${member.mem_delete }" /><span class="errors">${errors["mem_delete"]}</span></td>
			</tr>
			<tr>
				<td colspan="2"><input type="submit" value="가입" />
					<button type="reset">취소</button></td>
			</tr>
		</table>
	</form>

	<!--아이디 중복확인 Modal -->
	<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog"
		aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="exampleModalLabel">아이디 확인</h5>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">
					<form id="idCheckForm" method="post" action="${pageContext.request.contextPath }/member/idCheck.do">
						<input type="text" name="mem_id" id="checkMemId" /> 
						<span class="singid"/>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-secondary"
						data-dismiss="modal">Close</button>
					<button type="button" id="useId" class="btn btn-primary">아이디사용하기</button>
				</div>
				</form>
			</div>
		</div>
	</div>
	<script>
		var exampleModal = $('#exampleModal');
		var idCheck = $("#idCheck");
		var checkMemId = $("#checkMemId")
		var idCheckForm = $("#idCheckForm");
		var mem_id = $("#mem_id");
		var useId = $("#useId").hide();
		exampleModal.on("hidden.bs.modal", function() {
			checkMemId.val("");
			checkMemId.next(".signid").html();
		})

		useId.on("click", function() {
			mem_id.val(checkMemId.val());
			exampleModal.modal("hide");
		})

		idCheckForm.on("submit", function(event) {
			event.preventDefault();
			let action = $(this).attr("action");
			let method = $(this).attr("method");
			let queryString = $(this).serialize();
			$.ajax({
				url : action,
				method : method ? method : "get",
				data : queryString,
				dataType : "json",
				success : function(resp) {
					if (resp.valid) { //중복안되서 쓸수있다,.
// 						checkMemId.next(".singid").html("");
						useId.show();
					} else {
						checkMemId.next(".singid").html("아이디중복");
						checkMemId.focus();
						useId.hide();
					}
				},
				error : function(errorResp) {
					console.log(errorResp.status);
				}

			})

			return false;
		})

		idCheck.on("click", function() {
			exampleModal.modal("show");
		})
		checkMemId.on('blur', function() {
			//영문소문자로 시작, 영숫자, 4~12
			let regex = /^([a-z]([a-zA-Z0-9]{3,11}))$/m;
			//    Look around(look ahead, look behind)
			//    (?!=regex1)(?=regex2)regex3
			//    boolean test : 정규식과 맞는가!, [] exec : , [] match : 정규식에 맞는 부분이 있는가!
			let target = $(this).val();
			let match = regex.exec(target);

			if (!match) {
				$(this).empty();
				$(this).next(".singid").html("아이디 형식에 맞지 않습니다.");
				$(this).focus();
			} else {
				exampleModal.find('form').submit();
			}
		})
	</script>
</body>
</html>
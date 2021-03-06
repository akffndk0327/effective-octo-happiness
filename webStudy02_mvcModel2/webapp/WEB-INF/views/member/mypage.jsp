<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@page import="org.apache.commons.lang3.StringUtils"%>
<%@page import="kr.or.ddit.vo.MemberVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>mypage.jsp</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath }/bootstrap-4.3.1-dist/css/bootstrap.min.css">
<style type="text/css">
	.error{
		color: "red";
	}
</style>
<script type="text/javascript"
	src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
<script type="text/javascript"
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/bootstrap-4.3.1-dist/js/bootstrap.min.js"></script>
<%-- <jsp:useBean id="errors" class="java.util.HashMap" scope="session" /> --%>
<%
	session.removeAttribute("errors");
	String message = (String) session.getAttribute("message");
// 	Map<String, String> errors = (Map) session.getAttribute("errors");
// 	if(errors==null) errors = new HashMap<>();
	if (StringUtils.isNotBlank(message)) {
		session.removeAttribute("message");
%>
	<script type="text/javascript">
		alert("${sessionScope.message }");
	</script>
<%
}
%>	
</head>
<body>
	<%
		MemberVO savedMember = (MemberVO) request.getAttribute("savedMember");
	%>
<form action="${pageContext.request.contextPath }/member/memberUpdate.do" method="post">
	<table class="table table-bordered">
		<tr>
			<th>회원아이디</th>
			<td><input type="text" class="form-control"
				name="mem_id" value="${savedMember.mem_id }" />
				<span class ="error">${errors["mem_id"] }</span>
			</td>
		</tr>
		<tr>
			<th>비밀번호</th>
			<td><input type="text" class="form-control"
				name="mem_pass" value="${savedMember.mem_pass }" />
				<span class = "error">${errors["mem_pass"] } </span>	
			</td>
		</tr>
		<tr>
			<th>이름</th>
			<td><input type="text" required class="form-control"
				name="mem_name" value="${savedMember.mem_name }" />
				
			</td>
		</tr>
		<tr>
			<th>주민번호1</th>
			<td><input type="text" class="form-control" name="mem_regno1"
				value="${savedMember.mem_regno1 }" /></td>
		</tr>
		<tr>
			<th>주민번호2</th>
			<td><input type="text" class="form-control" name="mem_regno2"
				value="${savedMember.mem_regno2 }" /></td>
		</tr>
		<tr>
			<th>생일</th>
			<td><input type="date" class="form-control" name="mem_bir"
				value="${savedMember.mem_bir }" /></td>
		</tr>
		<tr>
			<th>우편번호</th>
			<td><input type="text" required class="form-control"
				name="mem_zip" value="${savedMember.mem_zip }" /></td>
		</tr>
		<tr>
			<th>주소1</th>
			<td><input type="text" required class="form-control"
				name="mem_add1" value="${savedMember.mem_add1 }" /></td>
		</tr>
		<tr>
			<th>주소2</th>
			<td><input type="text" required class="form-control"
				name="mem_add2" value="${savedMember.mem_add2 }" /></td>
		</tr>
		<tr>
			<th>집전화</th>
			<td><input type="text" class="form-control" name="mem_hometel"
				value="${savedMember.mem_hometel }" /></td>
		</tr>
		<tr>
			<th>회사전화</th>
			<td><input type="text" class="form-control" name="mem_comtel"
				value="${savedMember.mem_comtel }" /></td>
		</tr>
		<tr>
			<th>폰번호</th>
			<td><input type="text" class="form-control" name="mem_hp"
				value="${savedMember.mem_hp }" /></td>
		</tr>
		<tr>
			<th>이메일</th>
			<td><input type="text" required class="form-control"
				name="mem_mail" value="${savedMember.mem_mail }" /></td>
		</tr>
		<tr>
			<th>직업</th>
			<td><input type="text" class="form-control" name="mem_job"
				value="${savedMember.mem_job }" /></td>
		</tr>
		<tr>
			<th>취미</th>
			<td><input type="text" class="form-control" name="mem_like"
				value="${savedMember.mem_like }" /></td>
		</tr>
		<tr>
			<th>기념일</th>
			<td><input type="text" class="form-control" name="mem_memorial"
				value="${savedMember.mem_memorial }" /></td>
		</tr>
		<tr>
			<th>기념일자</th>
			<td><input type="date" class="form-control"
				name="mem_memorialday" value="${savedMember.mem_memorialday }" /></td>
		</tr>
		<tr>
			<th>마일리지</th>
			<td><input type="number" class="form-control" name="mem_mileage"
				value="${savedMember.mem_mileage }" /></td>
		</tr>
		<tr>
			<th>탈퇴여부</th>
			<td><input type="text" class="form-control" name="mem_delete"
				value="${savedMember.mem_delete }" /></td>
		</tr>
		<tr>
			<td colspan="2">
			<input type="submit" class="btn btn-info" value="저장" />
            <input type="reset" class="btn btn-info" value="취소" />
            <input type="button" class="btn btn-info" value="탈퇴" 
            		id="deleteBtn"/> 
			</td>
			
		</tr>
	</table>
</form>


<!-- Modal -->
<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel">삭제비번입력</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
  	<form method ="post" action="<%=request.getContextPath()%>/member/memberDelete.do">
      <div class="modal-body">
   		<input type="password" name="password" />
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
        <button type="submit" class="btn btn-primary">Save changes</button>
      </div>
      	</form>
    </div>
  </div>
</div>
<script type="text/javascript">
	$('#exampleModal').on('hidden.bs.modal',function(){
		$(this).find("form")[0].reset();
		});
	$('#deleteBtn').on('click',function(){
		$('#exampleModal').modal('show');
		
	})

</script>

</body>
</html>
<%@page import="java.util.Objects"%>
<%@page import="org.apache.commons.lang3.StringUtils"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>신규 가입</title>
<%
	String message = (String) request.getAttribute("message");
	if (StringUtils.isNotBlank(message)) {
%>
<script type="text/javascript">
		alert("<%=message%>");
</script>
<%
	}
%>

</head>
<body>
	<jsp:useBean id="member" class="kr.or.ddit.vo.MemberVO" scope="request"></jsp:useBean>
	<jsp:useBean id="errors" class="java.util.HashMap" scope="request" />
	<!-- 입력 ui필요 form 안에 action! 여기까지 올때 브라우저의 주소는 memberinsert.do -->
	<!-- get: 양식을 받아가지위한 :보낼데이터 x bodyx -->
	<!-- post 양식을 서버쪽으로 보내기 위한 보낼데이터 있어 바디 필요  -->
	<!-- member :  -->
	<form method="post">
		<table>
			<tr>
				<th>회원아이디</th>
				<td><input type="text" required class="form-control"
					name="mem_id" value="<%=Objects.toString(member.getMem_id(), "")%>" /><span
					class="error"><%=Objects.toString(errors.get("mem_id"), "")%></span></td>
			</tr>
			<tr>
				<th>비밀번호</th>
				<td><input type="text" required class="form-control"
					name="mem_pass" value="<%=member.getMem_pass()%>" /><span
					class="error"><%=errors.get("mem_pass")%></span></td>
			</tr>
			<tr>
				<th>이름</th>
				<td><input type="text" required class="form-control"
					name="mem_name" value="<%=member.getMem_name()%>" /><span
					class="error"><%=errors.get("mem_name")%></span></td>
			</tr>
			<tr>
				<th>주민번호1</th>
				<td><input type="text" class="form-control" name="mem_regno1"
					value="<%=member.getMem_regno1()%>" /><span class="error"><%=errors.get("mem_regno1")%></span></td>
			</tr>
			<tr>
				<th>주민번호2</th>
				<td><input type="text" class="form-control" name="mem_regno2"
					value="<%=member.getMem_regno2()%>" /><span class="error"><%=errors.get("mem_regno2")%></span></td>
			</tr>
			<tr>
				<th>생일</th>
				<td><input type="date" class="form-control" name="mem_bir"
					value="<%=member.getMem_bir()%>" /><span class="error"><%=errors.get("mem_bir")%></span></td>
			</tr>
			<tr>
				<th>우편번호</th>
				<td><input type="text" required class="form-control"
					name="mem_zip" value="<%=member.getMem_zip()%>" /><span
					class="error"><%=errors.get("mem_zip")%></span></td>
			</tr>
			<tr>
				<th>주소1</th>
				<td><input type="text" required class="form-control"
					name="mem_add1" value="<%=member.getMem_add1()%>" /><span
					class="error"><%=errors.get("mem_add1")%></span></td>
			</tr>
			<tr>
				<th>주소2</th>
				<td><input type="text" required class="form-control"
					name="mem_add2" value="<%=member.getMem_add2()%>" /><span
					class="error"><%=errors.get("mem_add2")%></span></td>
			</tr>
			<tr>
				<th>집전화</th>
				<td><input type="text" class="form-control" name="mem_hometel"
					value="<%=member.getMem_hometel()%>" /><span class="error"><%=errors.get("mem_hometel")%></span></td>
			</tr>
			<tr>
				<th>회사전화</th>
				<td><input type="text" class="form-control" name="mem_comtel"
					value="<%=member.getMem_comtel()%>" /><span class="error"><%=errors.get("mem_comtel")%></span></td>
			</tr>
			<tr>
				<th>폰번호</th>
				<td><input type="text" class="form-control" name="mem_hp"
					value="<%=member.getMem_hp()%>" /><span class="error"><%=errors.get("mem_hp")%></span></td>
			</tr>
			<tr>
				<th>이메일</th>
				<td><input type="text" required class="form-control"
					name="mem_mail" value="<%=member.getMem_mail()%>" /><span
					class="error"><%=errors.get("mem_mail")%></span></td>
			</tr>
			<tr>
				<th>직업</th>
				<td><input type="text" class="form-control" name="mem_job"
					value="<%=member.getMem_job()%>" /><span class="error"><%=errors.get("mem_job")%></span></td>
			</tr>
			<tr>
				<th>취미</th>
				<td><input type="text" class="form-control" name="mem_like"
					value="<%=member.getMem_like()%>" /><span class="error"><%=errors.get("mem_like")%></span></td>
			</tr>
			<tr>
				<th>기념일</th>
				<td><input type="text" class="form-control" name="mem_memorial"
					value="<%=member.getMem_memorial()%>" /><span class="error"><%=errors.get("mem_memorial")%></span></td>
			</tr>
			<tr>
				<th>기념일자</th>
				<td><input type="date" class="form-control"
					name="mem_memorialday" value="<%=member.getMem_memorialday()%>" /><span
					class="error"><%=errors.get("mem_memorialday")%></span></td>
			</tr>
			<tr>
				<th>마일리지</th>
				<td><input type="number" class="form-control"
					name="mem_mileage" value="<%=member.getMem_mileage()%>" /><span
					class="error"><%=errors.get("mem_mileage")%></span></td>
			</tr>
			<tr>
				<th>탈퇴여부</th>
				<td><input type="text" class="form-control" name="mem_delete"
					value="<%=member.getMem_delete()%>" /><span class="error"><%=errors.get("mem_delete")%></span></td>
			</tr>
			<tr>
				<td colspan="2"><input type="submit" value="가입" />
					<button type="reset">취소</button></td>
			</tr>
		</table>

	</form>

</body>
</html>
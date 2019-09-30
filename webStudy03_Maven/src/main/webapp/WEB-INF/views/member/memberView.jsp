<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%-- <jsp:useBean id="member" class="kr.or.ddit.vo.MemberVO" scope="request"></jsp:useBean>  --%>
<!-- jsp:useBean : 스크립트 안써도 객체 생성해주겟다 -->
	<table>
		<tr>
			<th>회원아이디</th>
<%-- 			<td><%=member.getMem_id()%> --%>
				<td>${member.mem_id}</td>
		</tr>
		<tr>
			<th>비밀번호</th>
			<td>${member.mem_pass}</td>
		</tr>
		<tr>
			<th>이름</th>
			<td>${member.mem_name}</td>
		</tr>
		<tr>
			<th>주민번호1</th>
			<td>${member.mem_regno1}</td>
		</tr>
		<tr>
			<th>주민번호2</th>
			<td>${member.mem_regno2}</td>
		</tr>
		<tr>
			<th>생일</th>
			<td>${member.mem_bir}</td>
		</tr>
		<tr>
			<th>우편번호</th>
			<td>${member.mem_zip}</td>
		</tr>
		<tr>
			<th>주소1</th>
			<td>${member.mem_add1}</td>
		</tr>
		<tr>
			<th>주소2</th>
			<td>${member.mem_add2}</td>
		</tr>
		<tr>
			<th>집전화</th>
			<td>${member.mem_hometel}</td>
		</tr>
		<tr>
			<th>회사전화</th>
			<td>${member.mem_comtel}</td>
		</tr>
		<tr>
			<th>폰번호</th>
			<td>${member.mem_hp}</td>
		</tr>
		<tr>
			<th>이메일</th>
			<td>${member.mem_mail}</td>
		</tr>
		<tr>
			<th>직업</th>
			<td>${member.mem_job}</td>
		</tr>
		<tr>
			<th>취미</th>
			<td>${member.mem_like}</td>
		</tr>
		<tr>
			<th>기념일</th>
			<td>${member.mem_memorial}</td>
		</tr>
		<tr>
			<th>기념일자</th>
			<td>${member.mem_memorialday}</td>
		</tr>
		<tr>
			<th>마일리지</th>
			<td>${member.mem_mileage}</td>
		</tr>
		<tr>
			<th>탈퇴여부</th>
			<td>${member.mem_delete}</td>
		</tr>
	</table>
</script>
<script>
member = $('#member');
$.ajax({
   dataType : "json",
   success : function(member) {
            let trTag = $("<tr>").append(
                  $("<td>").text(member.mem_id),
                  $("<td>").text(member.mem_name),
                  $("<td>").text(member.mem_hp),
                  $("<td>").text(member.mem_mail),
                  $("<td>").text(member.mem_add1),
                  $("<td>").text(member.mem_mileage))
                  .prop("id", member.mem_id);
            trTags.push(trTag);
      listBody.html(trTags);
   },
   error : function(err) {
      console.log(err.status);
   }
});
</script>
</body>
</html>
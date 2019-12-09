<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%--
* [[개정이력(Modification Information)]]
* 수정일                수정자      수정내용
* ----------  ---------  -----------------
* 2019. 11. 23.      	 허민지      최초작성
* Copyright (c) 2019 by DDIT All right reserved
 --%>

<c:if test="${member.authorId eq 'ROLE_MEM' }">
	<table class="table">
		<tr>
			<th>회원아이디</th>
			<td id="${member.memId }">${member.memId }</td>
			<c:if test="${member.memDelete eq 'N' }">
				<c:url value="/adminMypage/memDelete.do" var="deleteURL">
					<c:param name="memId" value="${member.memId }"></c:param>
				</c:url>
					<td><input type="button" class="btn btnAi0 deleteBtn"
						value="회원탈퇴" onclick="location.href='${deleteURL}';" /></td>
			</c:if>
		</tr>
		<tr>
			<th>이름</th>
			<td>${member.memName }</td>
		</tr>
		<tr>
			<th>우편번호</th>
			<td>${member.memZip }</td>
		</tr>
		<tr>
			<th>주소1</th>
			<td>${member.memAddr1 }</td>
		</tr>
		<tr>
			<th>주소2</th>
			<td>${member.memAddr2 }</td>
		</tr>
		<tr>
			<th>휴대전화</th>
			<td>${member.memTel }</td>
		</tr>
		<tr>
			<th>이메일</th>
			<td>${member.memMail }</td>
		</tr>
		<tr>
			<th>성별</th>
			<td>${member.memGender }</td>
		</tr>
		<tr>
			<th>출생년도</th>
			<td>${member.memAge }</td>
		</tr>
		<tr>
			<th>알레르기정보</th>
			<td>
				<ul>
			<c:forEach var="memAllergy" items="${memAllList }">
					<li>${memAllergy.allergy.allName }</li>
			</c:forEach>
				</ul>
			</td>
		</tr>
		
		<tr>
			<th>탈퇴여부</th>
			<td>${member.memDelete }</td>
		</tr>
	</table>
</c:if>

<!-- 기업회원정보 -->
<c:if test="${member.authorId eq 'ROLE_EMP' }">
	<table class="table">
		<tr>
			<th>회원아이디</th>
			<td id="${member.memId }">${member.memId }</td>
			<c:if test="${member.memDelete eq 'N' }">
				<c:url value="/adminMypage/memDelete.do" var="deleteURL">
					<c:param name="memId" value="${member.memId }"></c:param>
				</c:url>
				<td><input type="button" class="btn btnAi01 deleteBtn"
					value="회원탈퇴" onclick="location.href='${deleteURL}';" /></td>
			</c:if>
		</tr>
		<tr>
			<th>이름</th>
			<td>${member.memName }</td>
		</tr>
		<tr>
			<th>휴대전화</th>
			<td>${member.memTel }</td>
		</tr>
		<tr>
			<th>이메일</th>
			<td>${member.memMail }</td>
		</tr>
		<tr>
			<th>사업자번호</th>
			<td>${member.company.comNum }</td>
		</tr>
		<tr>
			<th>기업명</th>
			<td>${member.company.comName }</td>
		</tr>
		<tr>
			<th>기업주소</th>
			<td>${member.company.comAddr }</td>
		</tr>
		<tr>
			<th>탈퇴여부</th>
			<td>${member.memDelete }</td>
		</tr>
	</table>
</c:if>

<script>

</script>

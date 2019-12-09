<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>
<%--
* [[개정이력(Modification Information)]]
* 수정일                 수정자      수정내용
*
* ----------  ---------  -----------------
* 2019. 11. 6.      이진희      최초작성
* Copyright (c) 2019 by DDIT All right reserved
 --%>
<div id="InnerContainer">
	<h2 class="titleTopBar">상세보기</h2>
	<input name="correctNo" type="hidden" value="${correct.correctNo }"/>
	<table class="table">
		<tr>
			<th>글제목</th>
			<td>${correct.correctTitle }</td>
		</tr>
		<tr>
			<th>대분류</th>
			<td>${correct.codeName }</td>
		</tr>
		<tr>
			<th>소분류</th>
			<td>${correct.resName }</td>
		</tr>
		<tr>
			<th>작성일</th>
			<td>${correct.correctIndate }</td>
		</tr>
		<tr>
			<th>작성자</th>
			<td>${correct.memId }</td>
		</tr>
		<tr>
			<th>첨부파일</th>
			<td><c:if test="${not empty correct.attatchList }">
					<c:forEach items="${correct.attatchList }" var="attatch"
						varStatus="vs">
						<img src="${cPath }/correct/download.do?coattId=${attatch.coattId }">
						<c:url value="/correct/download.do" var="downloadURL">
							<c:param name="coattId" value="${attatch.coattId }" />
						</c:url>
						<a href="${downloadURL }">${attatch.coattName}</a>
					${not vs.last?",":"" }
				</c:forEach>
				</c:if></td>
		</tr>
		<tr>
			<th>내용</th>
			<td>${correct.correctContent }</td>
		</tr>
		<tr>
		<td colspan="2" style="text-align: right;">
		<sec:authorize url="/correct/correctInsert.do" access="hasAnyRole('ROLE_MEM','ROLE_EMP')">
			<c:if test="${empty correct.correctParent  }">
				<button id="btn1" class="btn btnAi1" data-toggle="modal" data-target="#exampleModal">삭제</button>
			</c:if> 
		</sec:authorize>	
			<sec:authorize url="/correct/correctInsert.do" access="hasRole('ROLE_ADMIN')">
	            <c:url value="/correct/correctInsert.do" var="insertURL">
	               <c:param name="correctParent" value="${correct.correctNo }" />
	               <c:param name="correctPw" value="${correct.correctPw }" />
	               <c:param name="codeName" value="${correct.codeName }" />
	               <c:param name="resName" value="${correct.resName }" />
	            </c:url>
	            <input id="btn2" type="button" class="btn btnAi0" value="답글쓰기"
	               onclick="location.href='${insertURL }';" />
         	</sec:authorize>
				<button id="btn3" class="btn btnAi2" type="button"
					onclick="location.href='${cPath}/correct/correctList.do';">목록</button>
			</td>
		</tr>
	</table>
</div>
<br><br>
<!-- Modal -->
<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog"
	aria-labelledby="exampleModalLabel" aria-hidden="true">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title" id="exampleModalLabel">비밀번호확인</h5>
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
			<form action="${cPath }/correct/correctDelete.do" method="post">
				<input type="hidden" name="correctNo" value="${correct.correctNo }" />
				<input type="hidden" name="memId" value="${loginId }" />
				<div class="modal-body">
					<input style="width:300px; height:30px;" type="password" name="correctPw" id="correctPw" required
						palceholder="비밀번호" />
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btnAi1"
						data-dismiss="modal">닫기</button>
						<button type="submit" class="btn btnAi0">삭제</button>
				</div>
			</form>
		</div>
	</div>
</div>
<c:if test="${not empty fail }">
   <script>
   $(document).ready(function() {
      Swal.fire({
           icon: 'error',
           title: 'Oops...',
           text: '서버오류.'
         })
   });
   </script>
</c:if>

<c:if test="${not empty password }">
   <script>
   $(document).ready(function() {
      Swal.fire({
           icon: 'error',
           title: 'Oops...',
           text: '비번오류 혹은 작성자가 아닙니다.'
         })
   });
   </script>
</c:if>




<c:if test="${not empty success }">
   <script>
   $(document).ready(function(){
      
   Swal.fire({
        position: 'center',
        icon: 'success',
        title: '등록되셨습니다.',
        showConfirmButton: false,
        timer: 1500
      })
   });
   </script>
</c:if>




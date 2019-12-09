<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script src="${cPath }/ckeditor/ckeditor.js"></script>
<%--
* [[개정이력(Modification Information)]]
* 수정일                 수정자      수정내용
* 2019. 11. 15      이진희      소분류추가
* 2019. 11. 12      이진희      폼수정
* ----------  ---------  -----------------
* 2019. 11. 6.      이진희      최초작성
* Copyright (c) 2019 by DDIT All right reserved
 --%>
 <style>
 	input{
 		border: 1px solid #ddd;
  		border-radius: 5px;
  		width:450px;
 	}
 
 </style>
<div id="InnerContainer">
	<h2 class="titleTopBar">글작성</h2>
	<form id="boardForm" method="post" enctype="multipart/form-data">
		<input type="hidden" name="correctNo" /> 
		<input type="hidden" name="memId" value="${loginId }" /> 
		<input type="hidden" name="correctParent" value="${param.correctParent }" /> 
		<table class="table table-bordered">
			<tr>
				<th>게시판종류</th>
				<td>
				<c:choose>
					<c:when test="${not empty param.codeName }">
					<c:forEach items="${codeList }" var="code" >
						<c:if test="${code.codeName eq param.codeName }">
						<input type="hidden" value="${code.codeId }"name="correctType"/>
							<span>${param.codeName }</span>
						</c:if>
					</c:forEach>	
					<c:forEach items="${boardList }" var="board">
						<c:if test="${board.codeName eq param.resName }">
						<input type="hidden" value="${board.codeId }" name="resId"/>
							<span>${param.resName }</span>
						</c:if>
					</c:forEach>
					</c:when>
					<c:otherwise>
						<select style="border-radius: 5px; height:30px;" name="correctType">
							<option value>선택해주세요</option>
							<c:forEach items="${codeList }" var="code">
								<option value="${code.codeId }">${code.codeName }</option>
							</c:forEach>
						</select> 
						<select style="border-radius: 5px; height:30px;" name="resId">
							<option value>선택</option>
							<c:forEach items="${boardList }" var="board">
								<option value="${board.codeId }">${board.codeName }</option>
							</c:forEach>
						</select>
					</c:otherwise>
				</c:choose>
				
				
				</td>
			</tr>
			<tr>
				<th>글제목</th>
				<td><input type="text" required class="form" name="correctTitle" /> <spanc class="error">${errors.correctTitle }</span></td>
			</tr>
			<tr>
				<th>비밀번호</th>
				<td>
					<c:if test="${not empty param.correctPw }">
						<input type="password" readonly name="correctPw" value="${param.correctPw }" />
						<span class="error">${errors.correctPw }</span>
					</c:if> 
					<c:if test="${empty param.correctPw }">
						<input type="password" required name="correctPw" />
						<span class="error">${errors.correctPw }</span>
					</c:if>
				</td>
			</tr>
		
			<tr>
				<th>첨부파일</th>
				<td>
					<div class="form-inline">
						<input type="file" name="bo_file"  />
					</div>
				</td>
			</tr>
			<tr>
				<th>내용</th>
				<td><textarea cols="50" rows="5" name="correctContent">${correct.correctContent }</textarea>
					<span class="error">${errors.correctContent }</span></td>
			</tr>
			<tr>
				<td colspan="2"><input id="btn3" type="button" class="btn btnAi2" value="목록" onclick="location.href='<c:url value="/correct/correctList.do"/>';" />
					<input id="btn1" type="submit" class="btn btnAi0" value="저장" /> 
					<input id="btn2" type="reset" class="btn btnAi1" value="취소" /></td>
			</tr>
		</table>
	</form>
</div>
<script type="text/javascript">
	CKEDITOR
			.replace(
					'correctContent',
					{
						filebrowserImageUploadUrl : "${cPath}/correct/imageUpload.do?command=QuickUpload&type=Images"
					});
	let boardForm = $("#boardForm");
</script>
<c:if test="${not empty fail }">
   <script>
   $(document).ready(function() {
      Swal.fire({
           icon: 'error',
           title: 'Oops...',
           text: '서버오류'
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
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%--
* [[개정이력(Modification Information)]]
* 수정일                 수정자      수정내용
* ----------  ---------  -----------------
* 2019. 11. 6.      이진희      최초작성
* 2019. 11. 8.      이진희      삭제모달창 수정
* Copyright (c) 2019 by DDIT All right reserved
 --%>
<style>
 .url{
 	color : blue;
 	font-weight : bold;
 }
 
 th{
	width:200px;
	background:#2F4F4F;
	color:white;
	text-align:center;
 }
 .uneat{
 	text-align:center;
 }
 
</style>
<div id="InnerContainer">
<br>
	<h4 class="titleTopBar">제품명 : ${uneatAble.prdtnm }</h4>
	<table class="table table-bordered">
		<tr>
			<th>제조일자</th>
			<td>${uneatAble.mnfdt }</td>
		</tr>
		<tr>
			<th>유통기한</th>
			<td>${uneatAble.distbtmlmt }</td>
		</tr>
		<tr>
			<th>영업자주소</th>
			<td>${uneatAble.addr }</td>
		</tr>
		<tr>
			<th>검사기관</th>
			<td>${uneatAble.insttNm }</td>
		</tr>
		<tr>
			<th>등록일</th>
			<td>${uneatAble.cretDtm }</td>
		</tr>
		</tr>
		<tr> 
			<th>회수폐기일련번호</th>
			<td>${uneatAble.rtrvldsuseSeq }</td>
		</tr>
	</table>
	<br><br>
	<h4 class="titleTopBar">부적합판정 사유</h4>
	<table class="table table-bordered">
		<thead>
			<th>부적합한항목</th>
			<th>기준규격</th>
			<th>검사결과</th>
		</thead>
		<tbody>
			<td class="uneat"><a class="url" href="https://www.foodsafetykorea.go.kr/foodcode/01_02.jsp?s_text=${uneatAble.testItmnm }">${uneatAble.testItmnm }</a></td>
			<td class="uneat">${uneatAble.stdrStnd }</td>
			<td class="uneat">${uneatAble.testanalsRslt }</td>
		</tbody>

	</table>
	<div class="btns">
	<button  class="btn btnAi2" type="button"
		onclick="history.back()">목록</button>
		
	<c:if test="${loginId eq 'admin' }">
	
		<c:url value="/uneatable/uneatableUpdate.do" var="updateURL">
			<c:param name="uneatId" value="${uneatAble.uneatId }" />
		</c:url>
		<input type="button" class="btn btnAi1" value="수정"
			onclick="location.href='${updateURL}';" />
		
		<button class="btn btnAi0" type="button" id="delBtn">삭제</button>
	</div>
	</c:if>
</div>
<br><br>
<form id="delForm" action="${cPath}/uneatable/uneatableDelete.do" method="post">
		<input name="uneatId" type="hidden" value="${uneatAble.uneatId }" />
</form>

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
        title: '등록되셨습니다',
        showConfirmButton: false,
        timer: 1500
      })
   });
   </script>
</c:if>

<script>

const swalWithBootstrapButtons = Swal.mixin({
	  customClass: {
	    confirmButton: 'btn btn-success',
	    cancelButton: 'btn btn-danger'
	  },
	  buttonsStyling: false
	})


var delBtn =$("#delBtn");

delBtn.on("click",function() {
	swalWithBootstrapButtons.fire({
		  title: '삭제 확인',
		  text: "정말 삭제하시겠습니까?",
		  icon: 'warning',
		  showCancelButton: true,
		  cancelButtonText: '아니오',
		  confirmButtonText: '네',
		  reverseButtons: true
		}).then((result) => {
		  if (result.value) {
			  $("#delForm").submit();
		  } else if (
		    result.dismiss === Swal.DismissReason.cancel
		  ) {
		    swalWithBootstrapButtons.fire(
		      '취소',
		      '취소되었습니다.',
		      'error'
		    )
		  }
		})
	
	return false;
})


</script>


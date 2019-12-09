<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%--
* [[개정이력(Modification Information)]]
* 수정일                 수정자      수정내용
* 
* ----------  ---------  -----------------
* 2019. 11. 6.      이진희      최초작성
* Copyright (c) 2019 by DDIT All right reserved
 --%>
<style>
	.stop{
		width:800px;
		height:500px;
	
	}
	.table{
	    width:800px;
		height: 298px;
		vertical-align: middle;
		vertical-align: top;
	}
	
	th{
		width:200px;
		background:#2F4F4F;
		color:white;
		text-align:center;
	}
	
	th,td{
		height:40px;
	}
	
	td{
		padding-left : 10px
	}
	
	#Inner{
		margin-left:150px;
	}
</style>
<div id="InnerContainer">
<h2 class="titleTopBar">회수.판매정지 식품 상세보기</h2><br><br>
<div id="Inner">
  <img class="stop" src="${stopSell.imgFilePath }"><br><br>
   <table class="table table-bordered" >
	  <tbody>
	    <tr>
	      <th>회수판매중지일련번호</th>
	      <td>${stopSell.rtrvldsuseSeq }</td>
	    </tr>
	    <tr>
	      <th>식품분류</th>
	      <td>${stopSell.prdlstType }</td>
	    </tr>
	    <tr>
	    <tr>
	      <th>제품명</th>
	      <td>${stopSell.prdtnm }</td>
	    </tr>
	    <tr>
	      <th>제조업체명</th>
	      <td>${stopSell.bsshnm }</td>
	    </tr>
	    <tr>
	      <th>제조업체주소</th>
	      <td>${stopSell.addr }</td>
	    </tr>
	    <tr>
	      <th>제조일자</th>
	      <td>${stopSell.mnfdt}</td>
	    </tr>
	    <tr>
	      <th>회수사유</th>
	      <td>${stopSell.rtrvlprvns }</td>
	    </tr>
	    <tr>
	      <th>회수방법</th>
				<c:choose>
					<c:when test="${not empty stopSell.rtrvlplandocRtrvlmthd }">
						<td>${stopSell.rtrvlplandocRtrvlmthd }</td>					
					</c:when>
					<c:otherwise>
						<td>알 수 없음</td>	
					</c:otherwise>
				</c:choose>
	    </tr>
	  </tbody>
	</table>
 	</div>
 	<div id="btns">
		<c:if test="${loginId eq 'admin' }">
			<c:url value="/stopSellingFood/stopSellingUpdate.do" var="updateURL">
				<c:param name="stopsellId" value="${stopSell.stopsellId }" />
			</c:url>
			<input type="button" class="btn btnAi1" value="수정" onclick="location.href='${updateURL}';"/>
			<button class="btn btnAi0" type="button" id="delBtn">삭제</button>
	 	</c:if>
	 	<button class="btn btnAi2" type="button" onclick="history.back()">목록</button>
 	</div>
 </div>	<br><br><br>
<form id="delForm" action="${cPath}/stopSellingFood/stopSellingDelete.do" method="post">
      <input name="stopsellId" type="hidden" value="${stopSell.stopsellId }" /> 
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
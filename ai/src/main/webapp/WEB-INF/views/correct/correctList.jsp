<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>
<%--
* [[개정이력(Modification Information)]]
* 수정일                 수정자      수정내용
* 2019. 11. 15.     이진희      소분류추가
* 2019. 11. 13.     이진희      메서드추가
* 2019. 11 .12.     이진희      뷰수정
* ----------  ---------  -----------------
* 2019. 11. 6.      이진희      최초작성
* Copyright (c) 2019 by DDIT All right reserved
 --%>
 
<style>
.lock {
	width: 10px;
}

.searchType,.searchType2{
	height:30px;
}

#pagingArea{
	text-align:center;
}

 #InnerContainer{
 	width:1500px;
 }
</style>
<div id="InnerContainer">
<div id="demo">
<input type="hidden" name="memId" value="${loginId }"/>
	<h2 class="titleTopBar">정정게시판/문의게시판</h2>
	<table>
		<tr id="search">
			<td colspan="4">
				<form action="?" id="searchForm" class="form-inline justify-content-center mb-3">
					<input type="hidden" name="page" /> 
					<img src="${cPath }/images/filterbig.png">
					<select style="width:180px; height:50px;" name="searchType" class="searchType">
						<c:forEach items="${codeList }" var="code">
							<option  value="${code.codeId }">${code.codeName }</option>
						</c:forEach>
					</select> 
					<select style="width:100px; height:50px;" name="searchType2" class="searchType2">
						<option value>선택</option>
						<c:forEach items="${boardList }" var="board">
							<option  value="${board.codeId }">${board.codeName }</option>
						</c:forEach>
					</select> 
				</form>
			</td>
		</tr>
	</table>
	<hr color="#ddd">
	<div class="table-responsive-vertical shadow-z-1">
		<!-- Table starts here -->
		<table id="table" class="table table-hover table-mc-light-blue">
			<thead>
				<tr>
					<th>글번호</th>
					<th>소분류</th>
					<th>내용</th>
					<th>작성자</th>
					<th>작성일</th>
				</tr>
			</thead>
			<tbody id="listBody">
				<c:set var="correctList" value="${pagingVO.dataList }" />
				<c:choose>
					<c:when test="${not empty correctList }">
						<c:forEach items="${correctList }" var="correct">
							<tr id="${correct.correctNo }">
								<td>${correct.rnum }</td>
								<td>${correct.resName }</td>
								<c:if test="${correct.correctUse eq 'Y' }">
									<td>${correct.correctTitle }<img style="width:22px;" src="${pageContext.request.contextPath }/images/lock.png" class="lock"></td>
								</c:if>
								<td>${correct.memId }</td>
								<td>${correct.correctIndate }</td>
								<input type="hidden" class="correctPass" value="${correct.correctPw }"/>
							</tr>
						</c:forEach>					
					</c:when>
				</c:choose>
			</tbody>
			<tfoot>
				<tr>
					<td colspan="5">
						<div id="pagingArea">${pagingVO.pagingHTML }</div>
					</td>
				</tr>
			</tfoot>
		</table>
		<sec:authorize url="/correct/correctInsert.do" access="hasAnyRole('ROLE_MEM','ROLE_EMP')">
		 <input id="btn2" type="button" class="btn btnAi0" value="등록" 
	       onclick="location.href='<c:url value="/correct/correctInsert.do"/>';"
	      />
		</sec:authorize>
	</div><br><br>
</div>
<div class="modal" tabindex="-1" role="dialog" id="PWmodal">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title">비밀글입니다</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
        <p style="font-size:25px;">비밀번호를 입력해주세요</p>
        <input style="width:280px; height:30px;" type="password" name="pass"/>
        <span id="check"></span>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn btnAi1" data-dismiss="modal">닫기</button>
        <button id="pwCheck" type="button" class="btn btnAi0">확인</button>
      </div>
    </div>
  </div>
</div>

<script type="text/javascript">
	var listBody = $("#listBody");
	var select = $("[name='searchType']");
	var select2 = $("[name='searchType2']");
	var pwCheck = $("#pwCheck");
	var pagingArea = $("#pagingArea");
	var pageTag = $("[name='page']");
	var pass = $("[name='pass']");
	var check = $("#check");
	var admin = $("[name='memId']");
	var search = $("#search");
	
	listBody.on("click","tr",function(){
		correctPass = $(this).children(".correctPass").val();
		value = $(this).attr("id");
		let memID = admin.val();
		if(memID=="admin"){
			location.href="${cPath}/correct/correctView.do?correctNo="+value;
		}else{
			$("#PWmodal").modal('show');
		}
	});
	
	pwCheck.on("click",function(){
		let passValue = pass.val();
		if(correctPass == passValue){
			location.href="${cPath}/correct/correctView.do?correctNo="+value;	
		}else{
			check.html("<span style='color:red; font-size:20px;'>비밀번호가 일치하지않습니다.</span>");
		}
	});
	
	var UI= function(resp){
		let list = resp.dataList;
		let trTags = [];
		$(list).each(function(i,v){
			
			let title = v.correctTitle.replace("&nbsp","  ") 
			let trTag = $("<tr id='"+v.correctNo+"'>").append(
						$("<td>").text(v.rnum),		
						$("<td>").text(v.resName),			
						$("<td>").html(v.correctTitle.replace("&nbsp","&nbsp;")).append("<img style='width:22px;' src='${pageContext.request.contextPath }/images/lock.png' class='lock'>"),
						$("<td>").text(v.memId),
						$("<td>").text(v.correctIndate),
						$("<input type='hidden' class='correctPass' value='"+v.correctPw+"'>")
			);
			trTags.push(trTag);
		});
		listBody.html(trTags);
		pagingArea.html(resp.pagingHTML);
		pageTag.val("1");
	}
	

	
	var value;
	select.on("change",function(){
		value = $(this).val();
		$.ajax({
			url : "${cPath}/correct/correctList.do",
			method : "get",
			data : {
				"correctType" : value
			},
			dataType : "json",
			success : function(resp) {
				UI(resp);
			},
			error : function(xhr) {
				console.log(xhr.status);
			}
			
		});
		
		select2.on("change",function(){
			let select2 = $(this).val();
			$.ajax({
				url : "${cPath}/correct/correctList.do",
				method : "get",
				data : {
					"correctType" : value,
					"resId" : select2
					
				},
				dataType : "json",
				success : function(resp) {
					UI(resp);
				},
				error : function(xhr) {
					console.log(xhr.status);
				}
			});
			
		});
		
		return false;
	});
	
	select2.on("change",function(){
		let select2 = $(this).val();
		$.ajax({
			url : "${cPath}/correct/correctList.do",
			method : "get",
			data : {
				"resId" : select2
			},
			dataType : "json",
			success : function(resp) {
				UI(resp);
			},
			error : function(xhr) {
				console.log(xhr.status);
			}
		});
		
	});
	
	
	
	pagingArea.on("click","a",function(event){
		event.preventDefault();
		let page = $(this).data("page");
		if(page < 1) return false;
		pageTag.val(page);
		searchForm.submit();
		return false;
	})
</script>		 
<c:if test="${not empty fail }">
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
        title: '삭제되셨습니다.',
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

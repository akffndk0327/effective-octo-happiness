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
<!-- <script type="text/javascript" src="https://code.jquery.com/jquery-3.4.1.min.js"></script> -->
<!-- <link rel="stylesheet" -->
<!-- 	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.0/css/bootstrap.min.css""> -->
<%-- <link rel="stylesheet" href="${cPath }/css/uneatableList.css"> --%>
<style>
#pagingArea{
	text-align:center;
}
.selectForm,.searchWord{
	height: 30px;
}

 #InnerContainer{
 	width:1500px;
 }
</style>
<div id="InnerContainer">
<h2 class="titleTopBar">회수.판매중지식품 게시판</h2>
	<table>
		<tr id="search">
			<td colspan="4">
				<form action="?" id="searchForm" class="form">
					<input type="hidden" name="page" /> 
					<select name="searchType" class="selectForm">
						<option value>전체</option>
						<option value="name">제품명</option>
						<option value="check">제조업체명</option>
					</select> 
					<input type="text" name="searchWord" class="searchWord" placeholder="검색어를 입력하세요" />
					<input style="margin-top: -4px;" id="btn1" type="submit" class="btn btnAi0" value="검색" />
				</form>
			</td>
		</tr>
	</table>
	<div class="table-responsive-vertical shadow-z-1">
		<!-- Table starts here -->
		<table id="table" class="table table-hover table-mc-light-blue" style="width:100%;">
			<thead>
				<tr>
					<th>글번호</th>
					<th>제품명</th>
					<th>제조업체명</th>
					<th>시험항목</th>
				</tr>
			</thead>
			<tbody id="listBody">
				<c:set var="stopList" value="${pagingVO.dataList }" />
				<c:choose>
					<c:when test="${not empty stopList }">
						<c:forEach items="${stopList }" var="stop">
							<tr id="${stop.stopsellId }">
								<td>${stop.rnum }</td>
								<td>${stop.prdtnm }</td>
								<td>${stop.bsshnm }</td>
								<td>${stop.rtrvldsuseSeq }</td>
							</tr>
						</c:forEach>
					</c:when>
					<c:otherwise>
						<tr>
							<td colspan="4">조건에 맞는 글이 없음.</td>
						</tr>
					</c:otherwise>
				</c:choose>
			</tbody>
			<tfoot>
				<tr>
					<td colspan="4">
						<div id="pagingArea">${pagingVO.pagingHTML }</div>
						<c:if test="${loginId eq 'admin' }">
						<input id="btn2" type="button" class="btn btnAi0" value="등록" 
						 	onclick="location.href='<c:url value="/stopSellingFood/stopSellingInsert.do"/>';"
						/>
						</c:if>
					</td>
				</tr>
			</tfoot>
		</table>
</div>	
<script type="text/javascript">
var listBody = $("#listBody");
var pagingArea = $("#pagingArea");
var searchForm = $("#searchForm");
var pageTag = $("[name='page']");
	
	listBody.on("click","tr",function(){
		let value = $(this).attr("id");
		location.href="${cPath}/stopSellingFood/stopSellingView.do?stopsellId="+value;
	});

	
	var makeUI = function(resp) {
		let uneatList = resp.dataList;
		let trTags = [];
		$(uneatList).each(function(i, v) {
			let trTag = $("<tr id='"+v.stopsellId+"'>").append(
						$("<td data-title='ID'>").text(v.rnum),
						$("<td data-title='Name'>").text(v.prdtnm),
						$("<td data-title='Link'>").text(v.bsshnm),
						$("<td data-title='Status'>").text(v.rtrvldsuseSeq)
					);
				trTags.push(trTag);
			});
		listBody.html(trTags);
		pagingArea.html(resp.pagingHTML );
		pageTag.val("1");
	}
	
	
	searchForm.on("submit", function(event){
		event.preventDefault();
		var action = $(this).attr("action");
		var method = $(this).attr("method");
		var queryString = $(this).serialize();
		$.ajax({
			url : action,
			method :method,
			data : queryString,
			dataType : "json", // REST 방식
			success : function(resp) {
				makeUI(resp);
			},
			error : function(errorResp) {
				console.log(errorResp.status);
			}

		});
		return false;
	});
	
	pagingArea.on("click", "a", function(event) {
		event.preventDefault();
		let page = $(this).data("page");
		if (page < 1) return false;
		pageTag.val(page);
		searchForm.submit();
		return false;
	});
	
	
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
        title: '삭제되셨습니다.',
        showConfirmButton: false,
        timer: 1500
      })
   });
   </script>
</c:if>
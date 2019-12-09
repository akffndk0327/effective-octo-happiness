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

 <table class="table">
	<thead>
		<tr>
			<th>회원아이디</th>
			<th>회원명</th>
			<th>회원권한</th>
			<th>탈퇴여부</th>
		</tr>
	</thead>
	<tbody id="listBody">
	</tbody>
	<tfoot>
		<tr>
			<td colspan="6">
				<form id="searchForm">
					<input type="hidden" name="page" />
					<select name="searchType">
						<option value>전체</option>
						<option value="id">아이디</option>
						<option value="name">이름</option>
					</select>
					<input type="text" name="searchWord" />
					<input type="submit" value="검색" />
				</form>
				<div id="pagingArea">
				
				</div>
			</td>
		</tr>
	</tfoot>
</table>
<!-- Modal -->
<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel">상세 조회</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
      
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btnAi1" data-dismiss="modal">Close</button>
      </div>
    </div>
  </div>
</div>

<form id="listForm" action="${cPath }/adminMypage/memberView.do">
	<input type="hidden" name="who" />
</form>

<script>
	var listBody = $("#listBody");
	var pagingArea = $("#pagingArea");
	var listForm = $("#listForm");
	var exampleModal = $("#exampleModal");
	var searchForm = $("#searchForm");
	var pageTag = $("[name='page']");
	
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
				let memberList = resp.dataList;
				let trTags = [];
				$(memberList).each(function(index, member) {
					let role;
					if(member.authorId == "ROLE_EMP"){
						role = "기업회원";
					}
					if(member.authorId== "ROLE_MEM"){
						role = "일반회원"
					}
					let trTag = $("<tr>").append(
									$("<td>").text(member.memId),
									$("<td>").text(member.memName),
									$("<td>").text(role),
									$("<td>").text(member.memDelete)
								).prop("id", member.memId);
					trTags.push(trTag);
				});
				listBody.html(trTags);
				pagingArea.html( resp.pagingHTML );
				pageTag.val("1");
			},
			error : function(errorResp) {
				console.log(errorResp.status);
			}
	
		});
		return false;
	});
	
	pagingArea.on("click", "a", function(){
		let page = $(this).data("page");
		paging(page);
	});
	
	exampleModal.on("hidden.bs.modal", function(){
		$(this).find(".modal-body").remove("table");
	});
	
	listForm.on("submit", function(){
		let action = $(this).attr("action");
		let method = $(this).attr("method");
		let queryString = $(this).serialize();
		$.ajax({
			url : action,
			method : method?method:"get",
			data : queryString,
			dataType : "html",
			success : function(resp) {
				exampleModal.find(".modal-body").html(resp);
				exampleModal.modal("show");
			},
			error : function(errorResp) {
				console.log(errorResp.status);
			}

		});
		return false;
	});
	
	function paging(page){
		if(page<1) return false;
		pageTag.val(page);
		searchForm.submit();
	}
	
	listBody.on("click", "tr" ,function(){
		let who = $(this).prop("id");
		listForm.find("[name='who']").val(who);
		listForm.submit();
	});
	
	paging(1);
	
</script>
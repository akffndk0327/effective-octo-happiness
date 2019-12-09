<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%--
* [[개정이력(Modification Information)]]
* 수정일                	          수정자     		 수정내용
* --------------  ---------  -----------------
* 2019. 11. 5.      박주연    		  최초작성
* 2019. 11. 14.     박주연		리스트 띄움
* Copyright (c) 2019 by DDIT All right reserved
 --%>
 
<style>
	table,th{
		text-align: center;

	}
	.table-mc-light-blue{
		width : 100%;
		margin: 0 auto;
/* 		text-align: center; */
	}
	.first{
		width: 120px;
	}
	.second{
		width: 500px;
	}
	
	.title{
		text-align: left;
		color: black;
		width:500px;
	    padding:0 5px;
	    overflow:hidden;
	    text-overflow:ellipsis;
	    white-space:nowrap;

	}
	
</style>

	

<div id="InnerContainer" style="width: 1500px">
	<h2 style="margin-left:65px" class="titleTopBar">뉴스</h2>
	<form action="?" id="searchForm">
		<input type="hidden" name="page" />
	</form>
	
		<!-- Table starts here -->
		<table id="table" class="table table-hover table-mc-light-blue">
			<thead>
				<tr>
					<th class="first">번호</th>
					<th class="second">제목</th>
					<th>작성자</th>
					<th>작성일</th>
					<th>조회수</th>
				</tr>
			</thead>
			<tbody id="listBody">
				<c:set var="newsList" value="${pagingVO.dataList }" />
				<c:choose>
					<c:when test="${not empty newsList }">
						<c:forEach items="${newsList }" var="newsList">
							<tr id="${newsList.newsNo }" class="newsList">
								<td >${newsList.rnum }</td>
								<td><div class="title">${newsList.newsTitle }</div></td>
								<td>${newsList.memId }</td>
								<td>${newsList.newsIndate }</td>
								<td>${newsList.newsHit }</td>
							</tr>
						</c:forEach>
					</c:when>
					<c:otherwise>
						<tr>
							<td colspan="5">조건에 맞는 글이 없음.</td>
						</tr>
					</c:otherwise>
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
		<br>
		<div>
		<c:if test="${loginId eq 'admin' }">
			<input type="button" class="btn btnAi0" value="작성" onclick="location.href='${cPath}/news/newsInsert.do';" >
		</c:if>
		</div>
		<br><br>
</div>
<script>
	var listBody = $("#listBody");
	var pagingArea = $("#pagingArea");
	var pageTag = $("[name='page']");
	
// 	var makeUI = function(resp) {
// 		let newsList = resp.dataList;
// 		let trTags = [];
// 		$(newsList).each(function(i, v) {
// 			let trTag = $("<tr id='"+v.newsNo+"'>").append(
// 						$("<td>").text(v.rnum), 
// 						$("<td>").text(v.newsTitle),
// 						$("<td>").text(v.memId),
// 						$("<td>").text(v.newsIndate),
// 						$("<td>").text(v.newsHit),
// 				trTags.push(trTag));
// 			});
// 		listBody.html(trTags);
// 		pagingArea.html(resp.pagingHTML);
// 		pageTag.val("1");
// 	}
	
	pagingArea.on("click", "a", function(event) {
		event.preventDefault();
		let page = $(this).data("page");
		if (page < 1)
			return false;
		pageTag.val(page);
		searchForm.submit();
		return false;
	});
	
	$(".newsList").on("click",function(){
		var newsId = $(this).prop("id");
		location.href = "${cPath}/news/newsView.do?newsNo="+newsId;
	})
</script>
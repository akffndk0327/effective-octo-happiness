<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%--
* [[개정이력(Modification Information)]]
* 수정일                 수정자      수정내용
* ----------  ---------  -----------------
* 2019. 11. 18.      박주연      최초작성
* Copyright (c) 2019 by DDIT All right reserved
 --%>
<style>
#pagingArea{
  text-align:center;
 }
 .titleLeftBar{
	color : #322C5C;
	border-left: 8px solid #322C5C;
/* 	background-color: #322C5C; */
}
</style>
<div class="titleLeftBar">관리자 광고 관리</div>
	<form action="?" id="searchForm">
		<input type="hidden" name="page" />
		<div id="adTable" class="table-responsive-vertical shadow-z-1">
			<!-- Table starts here -->
			<table id="table" class="table table-hover table-mc-light-blue">
				<thead>
					<tr>
						<th>글번호</th>
						<th>제목</th>
						<th>작성자</th>
						<th>광고 게시 날짜</th>
						<th>광고 종료 날짜</th>
						<th>승인 상태</th>
						<th>사용 상태</th>
					</tr>
				</thead>
				<tbody id="listBody">
					<c:set var="advertiseList" value="${pagingVO.dataList }" />
					<c:choose>
						<c:when test="${not empty advertiseList }">
							<c:forEach items="${advertiseList }" var="adList">
								<c:url value="/advertise/adViewAdmin.do?adNo=${adList.adId }"
									var="viewURL" />
								<tr>
									<td>${adList.rnum }</td>
									<td><a href="${viewURL }">${adList.adTitle }</td>
									<td>${adList.memId }</td>
									<td>${adList.adIndate }</td>
									<td>${adList.adTerm }</td>
									<td>${adList.adApprove }</td>
									<td>${adList.adUse }</td>
								</tr>
							</c:forEach>
						</c:when>
						<c:otherwise>
							<tr>
								<td colspan="7">신청된 광고가 없습니다.</td>
							</tr>
						</c:otherwise>
					</c:choose>
				</tbody>
				<tfoot>
					<tr>
						<td colspan="7">
							<div id="pagingArea">${pagingVO.pagingHTML }</div>
							<input type="button" class="btn btnAi2" value="등록"
								onclick="location.href='<c:url value="/advertise/adInsert.do"/>';" />
						</td>
					</tr>
				</tfoot>
			</table>
		</div>
<script>
var listBody = $("#listBody");
var pagingArea = $("#pagingArea");
var searchForm = $("#searchForm");
var pageTag = $("[name='page']");

	var makeUI = function(resp) {
		let adList = resp.dataList;
		let trTags = [];
		$(adList).each(
				function(i, v) {
					let trTag = $("<tr>").append(
							$("<td>").text(v.rnum),
							$("<td>").text(v.adTitle),
							$("<td>").text(v.memId),
							$("<td>").text(v.adIndate),
							$("<td>").text(v.adTerm),
							$("<td>").text(v.adApprove));
					trTags.push(trTag);
				});
		listBody.html(trTags);
		pagingArea.html(resp.pagingHTML);
		pageTag.val("1");
	}

	pagingArea.on("click", "a", function(event) {
		event.preventDefault();
		let page = $(this).data("page");
		if (page < 1)
			return false;
		pageTag.val(page);
		searchForm.submit();
		return false;
	});
</script>
<%@page import="kr.or.ddit.vo.AdvertiseVO"%>
<%@page import="kr.or.ddit.advertise.dao.IAdvertiseDAO"%>
<%@page
	import="kr.or.ddit.advertise.controller.AdvertiseRetrieveController"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%--
* [[개정이력(Modification Information)]]
* 수정일                 수정자      수정내용
* ----------  ---------  -----------------
* 2019. 11. 10.      박주연      최초작성
* Copyright (c) 2019 by DDIT All right reserved
--%>
<style>
	 #pagingArea{
  text-align:center;
 }
</style>

<div id="InnerContainer">
	<div class="container">
		<div class="row">
			<h3 class="titleTopBar">광고 신청</h3>
			<form action="?" id="searchForm">
				<input type="hidden" name="page" /> 
<%-- 				<input value="${advertise.adId }" type="hidden"> --%>
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
						<c:set var="adList" value="${pagingVO.dataList }" />
						<c:choose>
							<c:when test="${not empty adList }">
								<c:forEach items="${adList }" var="ad">
									<c:url value="/advertise/adView.do?adNo=${ad.adId }" var="viewURL" />
									<tr>
										<td>${ad.rnum }</td>
										<td><a href="${viewURL }">${ad.adTitle }</td>
										<td>${ad.memId }</td>
										<td>${ad.adIndate }</td>
										<td>${ad.adTerm }</td>
										<td>${ad.adApprove }</td>
										<td>${ad.adUse }</td>
									</tr>
								</c:forEach>
							</c:when>
							<c:otherwise>
								<tr>
									<td colspan="7" style="text-align:center">광고를 신청하세요 !</td>
								</tr>
							</c:otherwise>
						</c:choose>
					</tbody>
					<tfoot>
						<tr>
							<td colspan="7">
								<div id="pagingArea">${pagingVO.pagingHTML }</div>
								<input type="button" class="btn btnAi2" value="광고 신청"
									onclick="location.href='<c:url value="/advertise/adInsert.do"/>';" />
							</td>
						</tr>
					</tfoot>
				</table>
			</div>
		</div>
	</div>
</div>
<script>
var listBody = $("#listBody");
var pagingArea = $("#pagingArea");
var searchForm = $("#searchForm");
var pageTag = $("[name='page']");

	var makeUI = function(resp) {
		let adList = resp.dataList;
		let trTags = [];
		$(adList).each(function(i, v) {
			let trTag = $("<tr>").append(
					$("<td>").text(v.rnum), 
					$("<td>").append(
					$("<a>").attr("href =${cPath}/advertise/adView.do?adNo=${ad.adId }")		
							.text(v.adTitle)
						),
					$("<td>").text(v.memId),
					$("<td>").text(v.adIndate),
					$("<td>").text(v.adTerm),
					$("<td>").text(v.adApprove)
					);
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
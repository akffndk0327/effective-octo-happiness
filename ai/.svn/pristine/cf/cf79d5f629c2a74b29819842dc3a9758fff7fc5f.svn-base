<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%--
* [[개정이력(Modification Information)]]
* 수정일                   수정자          수정내용
* -------------  ---------  -----------------
* 2019. 11. 4.     최서희          최초작성
* 2019. 11. 20.	    최서희           리스트 뷰 구성
* Copyright (c) 2019 by DDIT All right reserved
 --%>
<link rel="stylesheet" href="${cPath }/css/notice.css">

<div id="InnerContainer">
<form action="?" id="searchForm">
	<input type="hidden" name="page"/>
</form>
<form action="${cPath }/notice/noticeView.do" id="viewForm">
	<input type="hidden" name="noticeNo" id="noticeNo"/>
</form>
<h2 class="titleTopBar">NOTICE</h2>
<c:set value="${pagingVO.dataList }" var="noticeList"></c:set>
<table class="mainTable">
	<tr>
		<th class="th short">No</th>
		<th class="boardTitleTh">제 &nbsp;&nbsp;&nbsp;&nbsp; 목</th>
		<th class="th middle">작성자</th>
		<th class="th middle">작성일</th>
		<th class="th short">조회수</th>
	</tr>
	<c:forEach items="${noticeList }" var="notice">
	<c:choose>
	<c:when test="${notice.noticeLevel eq '1'}">
		<tr class="pointerTr topTd" id="${notice.noticeNo }">
			<td class="td short">${notice.rnum }</td>
			<td class="boardTitleTd">${notice.noticeTitle }</td>
			<td class="td middle">${notice.admin.adminName }</td>
			<td class="td middle">${notice.noticeIndate }</td>
			<td class="td short">${notice.noticeHit }</td>
		</tr>
	</c:when>
	<c:otherwise>
		<tr class="pointerTr" id="${notice.noticeNo }">
			<td class="td short">${notice.rnum }</td>
			<td class="boardTitleTd">${notice.noticeTitle }</td>
			<td class="td middle">${notice.admin.adminName }</td>
			<td class="td middle">${notice.noticeIndate }</td>
			<td class="td short">${notice.noticeHit }</td>
		</tr>
	
	</c:otherwise>
	</c:choose>
	</c:forEach>
</table>
<c:if test="${authorId eq 'ROLE_ADMIN' }">
<c:url value="/notice/noticeInsert.do" var="noticeInsertURL" />
<input id="btn2" type="button" class="btn btnAi2" 
	onclick="location.href='${noticeInsertURL}';" value="등록"/>
</c:if>	
<div id="pagingArea">
	${pagingVO.pagingHTML }
</div>

</div>

<script type="text/javascript">
	var pagingArea = $("#pagingArea");
	var pageTag = $("[name='page']");
	var searchForm = $("#searchForm");
	var mainTable = $(".mainTable");
	var noticeNo = $("#noticeNo"); 
	var viewForm = $("#viewForm");
	
	pageTag.val("1");

	pagingArea.on("click", "a", function(event){
		event.preventDefault();
		let page = $(this).data("page");
		if(page<1) return false;
		pageTag.val(page);
		searchForm.submit();
		return false;
	});
	
	//글 상세보기 선택
	$(mainTable).on("click", ".pointerTr", function(){
		let no = $(this).prop("id");
		noticeNo.val(no);
		viewForm.submit();
	})
	
</script>
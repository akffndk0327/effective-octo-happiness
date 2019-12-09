<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%--
* [[개정이력(Modification Information)]]
* 수정일                 수정자                    수정내용
*
* ----------  ---------  -----------------
* 2019. 11. 5.      박주연      최초작성  뉴스 관리자리스트
* Copyright (c) 2019 by DDIT All right reserved
 --%>

<table class="table table-bordered">
	<thead>
	<h1>뉴스</h1>
		<tr>
			<th>번호</th>
			<th>제목</th>
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
					<tr>
						<td>1</td>
						<td><a href="${cPath }/news/newsView.do?what="+newsNo>테스트</td>
						<td>admin</td>
						<td>2019-11-07</td>
						<td>100</td>
						<%-- 						<td>${newsList.rnum }</td> --%>
<%-- 						<td><a data-bono="${newsList.newsNo }" data-toggle="tooltip" --%>
<%-- 							data-placement="top" title="글번호:${newsList.newsNo }">${newsList.newsTitle }</a></td> --%>
<%-- 						<td>${newsList.memId }</td> --%>
<%-- 						<td>${newsList.bo_date }</td> --%>
<%-- 						<td>${newsList.bo_hit }</td> --%>
<%-- 						<td>${newsList.bo_like }</td> --%>
					</tr>
				</c:forEach>
			</c:when>
			<c:otherwise>
<!-- 				<tr> -->
<!-- 					<td colspan="6">조건에 맞는 글이 없음.</td> -->
<!-- 				</tr> -->
			</c:otherwise>
		</c:choose>
	</tbody>
	<button id="btnWrite"type="button" value="글작성"><a href="${cPath }/news/newsInsert.do">글작성</button>
</table>
	<tfoot>
		<div id="pagingArea">${pagingVO.pagingHTML }</div>
	</tfoot>
	<script>
	var listBody = $("#listBody");
	var pagingArea = $("#pagingArea");
	var searchForm = $("#searchForm");
	var pageTag = $("[name='page']");
	
	listBody.on("click", "a", function(){
		let bono = $(this).data("bono");
		location.href="${cPath}/news/newsView.do?what="+newsNo;
	});
	pagingArea.on("click", "a", function(event){
		event.preventDefault();
		let page = $(this).data("page");
		if(page<1) return false;
		pageTag.val(page);
		searchForm.submit();
		return false;
	});
	$("#btnWrite").on('click',function(){
		
	})
	</script>
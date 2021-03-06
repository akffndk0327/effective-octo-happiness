<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<%--
* [[개정이력(Modification Information)]]
* 수정일                   수정자    	  수정내용
* -------------  ---------  -----------------
* 2019. 11. 4.     최서희          최초작성
* 2019. 11. 20.	    최서희          상세보기 뷰 구성
* Copyright (c) 2019 by DDIT All right reserved
 --%>
 <link rel="stylesheet" href="${cPath }/css/notice.css">
 
<div id="InnerContainer">
<h2 class="titleTopBar">Notice Content</h2>
<table class="mainTable">
	<tr>
		<th colspan="2" class="boardTitleTh">${notice.noticeTitle }</th>
	</tr>
	<tr>
		<td class="middle">관리자</td>
		<td class="middle indate">${notice.noticeIndate } &nbsp;&nbsp;&nbsp; | &nbsp;&nbsp;&nbsp; 조회수 : ${notice.noticeHit }</td>
	</tr>
	<tr>
		<td class="content" colspan="2">${notice.noticeContent }</td>
	</tr>
</table>
<c:url value="/notice/noticeList.do" var="titleURL"></c:url>
<input type="button" class="btn btnAi2" value="목록" onclick="location.href='${titleURL}';" />
<c:if test="${authorId eq 'ROLE_ADMIN' }">
		<c:url value="/notice/noticeDelete.do" var="deleteURL">
			<c:param name="what" value="${notice.noticeNo }"></c:param>
		</c:url>
		<input type="button" class="btn btnAi1" value="삭제" onclick="location.href='${deleteURL}';"/>
		<c:url value="/notice/noticeUpdate.do" var="noticeURL">
			<c:param name="what" value="${notice.noticeNo }"></c:param>
		</c:url>
		<input type="button" class="btn btnAi0" value="수정" onclick="location.href='${noticeURL}';"/>
</c:if>
</div>
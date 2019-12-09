<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%--
* [[개정이력(Modification Information)]]
* 수정일                 수정자      수정내용
* ----------  ---------  -----------------
* 2019. 11. 7.      박주연      최초작성
* Copyright (c) 2019 by DDIT All right reserved
 --%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<!-- <link rel="stylesheet" -->
<!-- 	href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css"> -->
<!-- <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script> -->

<!-- Datepicker 한국어로 변환 -->
<script
	src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.4/i18n/datepicker-ko.js"></script>
<style>
th {
	width: 20%;
	background: #e6e6e6;
}

.adtable {
	width: 70%;
	margin-left: 15%;
	font-size: 21px;
}
</style>
<div id="InnerContainer">
	<h3 class="titleTopBar" style="font-size: 30px">내가 신청한 광고</h3>
	<br>
	<c:set var="adView" value="${AdvertiseVO.dataList }"></c:set>
	<c:set var="adattView" value="${AdvertiseVO.adattatchList }"></c:set>
	<c:set var="adreply" value="${AdvertiseVO.adreplyList }"></c:set>
	<table class="table adtable table-condensed">
		<tr style="font-size: 25px;">
			<td style="border: none">신청자 정보</td>
		</tr>
		<tr>
			<th>신청번호</th>
			<td>${advertise.adId }<span></td>
		</tr>
		<tr>
			<th>제목</th>
			<td>${advertise.adTitle }<span></td>
		</tr>
		<tr>
			<th>성 명</th>
			<td>${advertise.memId }</td>
		</tr>
		<tr>
			<th>승인상태</th>
			<td>${advertise.adApprove }</td>
		</tr>
		<tr>
			<th>사용상태</th>
			<td>${advertise.adUse }</td>
		</tr>
		<tr>
			<th>광고 시작 날짜</th>
			<td>${advertise.adIndate }</td>
		</tr>
		<tr>
			<th>광고 종료 날짜</th>
			<td>${advertise.adTerm}</td>
		</tr>
		<tr>
			<th>이동 URL</th>
			<td>${advertise.adLink }</td>
		</tr>
		<tr>
			<th>게시 위치</th>
			<td>${advertise.adhit.adposition.resource.resName }
				${advertise.adhit.adposition.adpoName }</td>
		</tr>
		<tr>
			<th>첨부파일</th>
			<td>
			<c:if test="${not empty advertise.adattatchList }">
					<c:forEach items="${advertise.adattatchList}" var="attatch">
						<img src="${cPath }/advertise/addownload.do?what=${attatch.adattId}" style="max-width:120px; max-height:400px">
					</c:forEach>
				</c:if></td>
		</tr>
		<tr>
			<th>반려사유</th>
			<td>${advertise.adRep}</td>
		</tr>
		<tfoot>
			<tr>
				<td colspan="2">
				<c:if test="${advertise.adApprove eq '대기'}">
				<input type="button" class="btn btnAi0"	value="수정" 
				 onclick="location.href='<c:url value="/advertise/admemUpdate.do?what=${advertise.adId }"/>';"/>
				 </c:if> 
				<input type="button" class="btn btnAi2" value="목록" onclick="history.back();"/>
				</td>
			</tr>
		</tfoot>
	</table>
</div>


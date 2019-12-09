<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%--
* [[개정이력(Modification Information)]]
* 수정일                 수정자      수정내용
* ----------  ---------  -----------------
* 2019. 11. 6.      최서희      최초작성
* Copyright (c) 2019 by DDIT All right reserved
 --%>
<link rel="stylesheet" href="${cPath }/css/faq.css">

<div id="InnerContainer">
<form action="?" id="searchForm">
	<input type="hidden" name="page"/>
</form>
<table class="titleTable">
	<tr>
		<td class="leftTd"><h2 class="titleTopBar">FAQ</h2></td>
		<td class="rightTd"></td>
	</tr>
</table>
<c:set value="${pagingVO.dataList }" var="faqList"/>
<div id="accordion">
	<c:forEach items="${faqList }" var="faq">
	<div id="${faq.faqNo }" class="titleDiv"><img src="${cPath }/images/questionG.png"> &nbsp;&nbsp;${faq.faqTitle }</div>
	<div>
		<table>
			<tr>
				<td class="long">${faq.admin.adminName }</td>
				<td class="middle">${faq.faqIndate }  &nbsp; | &nbsp; 조회수 : ${faq.faqHit }</td>
				<td class="short"></td>
			</tr>
			<tr><td colspan="3"><hr color="#353535"></td></tr>
			<tr>
				<td class="content" colspan="2"><p>${faq.faqContent }</p></td>
				<td class="short">
					<c:url value="/faq/faqUpdate.do" var="updateURL">
						<c:param name="faqNo" value="${faq.faqNo }"></c:param>
					</c:url>
					<c:if test="${authorId eq 'ROLE_ADMIN' }">
						<img src="${cPath }/images/btnWrite.png" class="btnUpdate"
							onclick="location.href='${updateURL}';" >
						<c:url value="/faq/faqDelete.do" var="deleteURL">
							<c:param name="faqNo" value="${faq.faqNo }"></c:param>
						</c:url>
						<img src="${cPath }/images/btnDelete.png" class="btnDelete"
							onclick="location.href='${deleteURL}';" >
					</c:if>
				</td>
			</tr>
		</table>
	</div>
	</c:forEach>
</div>
<c:if test="${authorId eq 'ROLE_ADMIN' }">
	<input id="btnInsert" type="button" class="btn btnAi2" value="등록" 
		 onclick="location.href='<c:url value="/faq/faqInsert.do"/>';"/>
</c:if>

<div id="pagingArea">
	${pagingVO.pagingHTML }
</div>

</div> <%--innerContainer 끝--%>

<link rel="stylesheet" type="text/css" href="${cPath }/js/jqueryui/jquery-ui.css" />
<%-- <script type="text/javascript" src="${cPath}/js/jqueryui/jquery.js"></script> --%>
<script type="text/javascript" src="${cPath }/js/jqueryui/jquery-ui.min.js"></script>
<script type="text/javascript" >
var pagingArea = $("#pagingArea");
var pageTag = $("[name='page']");
var searchForm = $("#searchForm");
var accordion = $("#accordion");
var myModal = $("#myModal");

pageTag.val("1");

pagingArea.on("click", "a", function(event){
	event.preventDefault();
	let page = $(this).data("page");
	if(page<1) return false;
	pageTag.val(page);
	searchForm.submit();
	return false;
});

$(accordion).accordion();

//조회수 증가
$("#accordion").on("click", ".titleDiv", function(){
	event.preventDefault();
	let faqNo = $(this).prop("id");
	$.ajax({
		url : "${cPath}/faq/faqUpdateHit.do",
		method :  "post",
		data : {"faqNo" : faqNo}
	});
});
</script>
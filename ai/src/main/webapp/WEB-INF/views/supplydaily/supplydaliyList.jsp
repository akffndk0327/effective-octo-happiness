<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%--
* [[개정이력(Modification Information)]]
*     수정일              		  수정자     		 수정내용
* --------------  ---------  -----------------
* 2019. 11. 11.      박주연      		최초작성 생활용품 리스트
* 2019. 11. 20.		   박주연	        유해성분 출력.
* Copyright (c) 2019 by DDIT All right reserved
 --%>
<style>
#btn1, #btn2 {
	background-color: #2F4F4F;
	color: white;
	width: 60px;
}

#btn2 {
	float: right;
}

#pagingArea {
	text-align: center;
}

.selectForm, .searchWord {
	height: 30px;
}

.adimg {
	    width: 200px;
    height: 565px;
    margin-left: -36px;
    cursor:pointer;
}
</style>
<div class="adleft" id="S070">
		<c:forEach items="${adimgappend.adattatchList}" var="attatch">
			<img src="${cPath }/advertise/addownload.do?what=${attatch.adattId}" 
				class="adimg" url="${advertiseVO.adLink }">
	</c:forEach>
	<%-- 				 	<img src="${cPath }/advertise/${adattatchVO.adattSavename }" class="adimg" url="${advertiseVO.adLink }" /> --%>
	<%-- 				 	<img src="${cPath }/advertise/addownload.do?what=${adattatch.adattId}" style="max-width:120px; max-height:400px" class="adimg" url="${advertiseVO.adLink }"> --%>
	<%-- 				 	<img src="${cPath }/prod/prodDownload.do?prodId=${prod.prodId }"> --%>
	<input type="hidden" id="adId" value="${advertiseVO.adId }" />
</div>
<div id="InnerContainer">
	<div class="container">
		<div class="row">
			<h3 class="titleTopBar">생활용품</h3>
			<table>
				<tr id="search">
					<td colspan="4">
						<form action="?" id="searchForm">
							<input type="hidden" name="page" /> <select name="searchType"
								class="selectForm">
								<option value>전체</option>
								<option value="proname">제품명</option>
								<option value="company">제조업체</option>
							</select> <input type="text" name="searchWord" class="searchWord"
								placeholder="검색어를 입력하세요" /> <input id="btn1" type="submit"
								class="btn" value="검색" />
						</form>
					</td>
				</tr>
			</table>
			<div class="table-responsive-vertical shadow-z-1">
				<!-- Table starts here -->
				<table id="table" class="table table-hover table-mc-light-blue">
					<thead>
						<tr>
							<th>글번호</th>
							<th>제품명</th>
							<th>제조업체</th>
						</tr>
					</thead>
					<tbody id="listBody">
						<c:set var="dsList" value="${pagingVO.dataList }" />
						<c:choose>
							<c:when test="${not empty dsList }">
								<c:forEach items="${dsList }" var="dsList">
									<tr id="${dsList.bioCheId }">
										<td>${dsList.rnum }</td>
										<td>${dsList.bioCheName }</td>
										<td>${dsList.bioCheCom }</td>
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
								<div id="pagingArea">${pagingVO.pagingHTML }</div> <c:if
									test="${loginId eq 'admin' }">
									<input id="btn2" type="button" class="btn btnAi2" value="등록"
										onclick="location.href='<c:url value="/dailysupply/dsInsert.do"/>';" />
								</c:if>
							</td>
						</tr>
					</tfoot>
				</table>
			</div>
		</div>
	</div>
</div>

<script type="text/javascript">
	var listBody = $("#listBody");
	var pagingArea = $("#pagingArea");
	var searchForm = $("#searchForm");
	var pageTag = $("[name='page']");
	

	listBody.on("click","tr",function(){
		let value = $(this).attr("id");
		location.href="${cPath}/dailysupply/dsView.do?dsNo="+value;
	});
	
	var makeUI = function(resp) {
		let dsList = resp.dataList;
		let trTags = [];
		$(dsList).each(function(i, v) {
				let trTag = $("<tr id='"+v.bioCheId+"'>").append(
							$("<td>").text(v.rnum),
							$("<td>").text(v.bioCheName),
							$("<td>").text(v.bioCheCom)
							);
				trTags.push(trTag);
			});
		listBody.html(trTags);
		pagingArea.html(resp.pagingHTML);
		pageTag.val("1");
	}


	
// 	검색
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
	
	$(".adimg").on("click",function(){
		var sendUrl = "${advertiseVO.adLink}";
		console
		var adId = $("#adId").val();
		$.ajax({
			url : "${cPath}/dailysupply/adUpdate.do",
			method : "post",
			data : {
				"adId":adId
			},
			dataType : "json",
			success : function(resp) {
				location.href=sendUrl
			},
			error : function(xhr) {
				console.log(xhr.status);
			}
		});
	})
	
	
</script>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%--
* [[개정이력(Modification Information)]]
* 수정일                 수정자      수정내용
*
* ----------  ---------  -----------------
* 2019. 11. 7.      박주연      최초작성
* Copyright (c) 2019 by DDIT All right reserved
 --%>
<style>
#btn1,#btn2{
 		background-color: #2F4F4F;
 		color:white;
 		width:60px;
}
 	
#btn2{
 		float:right;
}
 #pagingArea{
  text-align:center;
 }
 .selectForm,.searchWord{
	height: 30px;
}
/* 알레르기 테이블  */
 #table{
 	width:80%
 }
 
/*  실시간검색  */
 #allDiv{
 	width: 270px;
    margin-left: 86%;
    margin-top: -50%;
    border : 1px solid black;
	padding : 10px;
	padding-top:-20px;     
 }

 #allKeyWord td{
 	padding:3px;
 }
 
  .allSearch:hover{
  	color:#47C83E;
  }
 
</style> 
<div id="InnerContainer">
	<h3 class="titleTopBar">알레르기</h3>
	<table>
		<tr id="search">
			<td colspan="4">
				<form action="?" id="searchForm">
					<input type="hidden" name="page" /> 
					<select name="searchType" class="selectForm">
						<option value>전체</option>
						<option value="allname">알레르기명</option>
						<option value="code">코드번호</option>
					</select> 
					<input type="text" name="searchWord" class="searchWord"
						placeholder="검색어를 입력하세요" /> 
					<input id="btn1" type="submit" class="btn" value="검색" />
				</form>
			</td>
		</tr>
	</table>
	<div class="table-responsive-vertical shadow-z-1">
		<!-- Table starts here -->
		<table id="table" class="table table-hover table-mc-light-blue">
			<thead>
				<tr>
					<th>번호</th>
					<th>알레르기코드</th>
					<th>알레르기명</th>
				</tr>
			</thead>
			<tbody id="listBody">
				<c:set var="alList" value="${pagingVO.dataList }" />
				<c:choose>
					<c:when test="${not empty alList }">
						<c:forEach items="${alList }" var="alList">
							<tr id="${alList.allId }" >
								<td>${alList.rnum }</td>
								<td>${alList.allId }</td>
								<td>${alList.allName }</td>
							</tr>
						</c:forEach>
					</c:when>
					<c:otherwise>
						<tr>
							<td colspan="3"> 조건에 맞는 글이 없음.</td>
						</tr>
					</c:otherwise>
				</c:choose>
			</tbody>
			<tfoot>
				<tr>
					<td colspan="4">
						<div id="pagingArea">${pagingVO.pagingHTML }</div> 
						<c:if test="${loginId eq 'admin' }">
							<input type="button" class="btn btnAi2" value="등록" style="margin-top:-14%;"
								onclick="location.href='<c:url value="/allergy/allergyInsert.do"/>';" />
						</c:if>
					</td>
				</tr>
			</tfoot>
		</table>
<!-- 		알레르기 검색어순위 -->
		<div id="allDiv">
			<table id="allKeyWord" >
				<tr>
				<td style="font-size:25px;" colspan="4">알레르기 검색어 순위</td>
				</tr>
				<c:forEach varStatus="vs" items="${allList }" begin="0" end="9" var="r">
					<tr class="allSearch" id="${r.allId }" > 
						<td>${vs.count }</td>
						<td>&nbsp;&nbsp;&nbsp;</td>
						<td>${r.allName }</td>
						<td><img src="${cPath }/images/b${vs.count }.png" /></td>
					</tr>
				</c:forEach>
			</table>
		</div>	
	</div>
	<script type="text/javascript">
		var listBody = $("#listBody");
		var pagingArea = $("#pagingArea");
		var searchForm = $("#searchForm");
		var pageTag = $("[name='page']");
		var allSearch = $(".allSearch");
		
		listBody.on("click","tr",function(){
			let value = $(this).attr("id");
// 			alert(value);
			location.href="${cPath }/allergy/allergyView.do?what="+value;
		});

		var makeUI = function(resp) {
			let dsList = resp.dataList;
			let trTags = [];
			$(dsList).each(function(i, v) {
				let trTag = $("<tr id='"+v.allId+"'>").append(
							$("<td>").text(v.rnum),
							$("<td>").text(v.allId),
							$("<td>").text(v.allName)
							);
				trTags.push(trTag);
				});
			listBody.html(trTags);
			pagingArea.html(resp.pagingHTML);
			pageTag.val("1");
		}
		
//	 	검색
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
		
		allSearch.on("click",function(){
			let value = $(this).attr("id");
			location.href="${cPath}/allergy/allergyView.do?what="+value;
		})
		
		
	</script>
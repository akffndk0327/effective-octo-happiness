<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
<%--
* [[개정이력(Modification Information)]]
* 수정일                 수정자      수정내용
* 
* ----------  ---------  -----------------
* 2019. 11. 6.      이진희      최초작성
* Copyright (c) 2019 by DDIT All right reserved
 --%>
 <style>
  #btn2{
   float:right;
  }
  
  #btn1{
 		background-color: #2F4F4F;
 		color:white;
 		width:100px;
	}
  
  #img{
  	width:200px;
  	height:200px;
  }
  
  #prdlstnm,#seller{
  	 font-weight:bold;
  	 vertical-align:middle;
  }
  
  #rnum{
  	vertical-align:middle;
  	text-align:center;
  	font-weight:bold;
  }
  th,tr,td{
  	text-align:center;
  }
  .btnAi0{
  	margin-left:1115px;
  }
 	
 .selectForm,.searchWord{
	height: 30px;
  }	
 
 </style>
<div id="InnerContainer">
	<table>
		<tr id="search">
			<td colspan="4">
				<form action="?" id="searchForm" >
					<input type="hidden" name="page" /> 
					<input type="hidden" name="fcId" value="${param.fcId }" />
					<select name="searchType" class="selectForm">
						
						<option value="name">제품명</option>
						<option value="allergy">알레르기</option>
					</select> 
						<input type="text"  name="searchWord" class="searchWord" placeholder="검색어를 입력하세요" />
						<input id="btn1" type="submit" class="btn" value="검색"  />
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
					<th>제품사진</th>
					<th>제품명</th>
					<th>제조업체</th>
				</tr>
			</thead>
			<tbody id="listBody">
				<c:set var="foodList" value="${pagingVO.dataList }" />
				<c:choose>
					<c:when test="${not empty foodList }">
						<c:forEach items="${foodList }" var="food">
							<tr id="${food.prdlstreportno }">
								<td id="rnum" data-title="ID">${food.rnum }</td>
								<td data-title="Name"><img id="img" src="${food.imgurl1 }"></td>
								<td id="prdlstnm" data-title="Link">${food.prdlstnm }</td>
								<td id="seller" data-title="Status">${food.manufacture }</td>
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
						<div id="pagingArea">${pagingVO.pagingHTML }</div>
					</td>
				</tr>
			</tfoot>
		</table>
		<button type="button" class="btn btnAi0" onclick="history.back()">목록</button>
		<br><br>
</div>
<script type="text/javascript">
	var listBody=$("#listBody");
	var pagingArea = $("#pagingArea");
	var searchForm = $("#searchForm");
	var pageTag = $("[name='page']");
	
	listBody.on("click","tr",function(){
		let value = $(this).attr("id");
		location.href = "${cPath}/food/foodView.do?prdlstreportno="+value;
	});
	
	var makeUI = function(resp){
		let list = resp.dataList;
		let trTags = [];
		$(list).each(function(i,v){
			let trTag = $("<tr id='"+v.prdlstreportno+"'>").append(
				        $("<td id='rnum' data-title='ID'>").text(v.rnum),
				        $("<td data-title='Name'>").append(
				        	$("<img id='img' src='"+v.imgurl1+"'>")		
				        ),
				        $("<td id='prdlstnm' data-title='Link'>").text(v.prdlstnm),
				        $("<td id='seller' data-title='Status'>").text(v.manufacture)
			);
			trTags.push(trTag);
		});
		listBody.html(trTags);
		pagingArea.html(resp.pagingHTML);
		pageTag.val("1");
	}
	
	
	searchForm.on("submit",function(event){
		event.preventDefault();
		var action = $(this).attr("action");
		var method = $(this).attr("method");
		var queryString = $(this).serialize();
		$.ajax({
			url : action,
			method : method,
			data : queryString,
			dataType : "json",
			success : function(resp){
				makeUI(resp);
			},
			error : function(errorResp){
				console.log(errorResp.status);
			}
			
		});
		return false;
	});
	
	pagingArea.on("click","a",function(event){
		event.preventDefault();
		let page = $(this).data("page");
		if(page<1) return false;
		pageTag.val(page);
		searchForm.submit();
		return false;
	});
	
</script>

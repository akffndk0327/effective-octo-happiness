<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%--
* [[개정이력(Modification Information)]]
* 수정일                     수정자           수정내용
* -------------  ---------  -----------------
* 2019. 11. 5.      최서희            최초작성
* 2019. 11. 15.     최서희		css변경
* Copyright (c) 2019 by DDIT All right reserved
 --%>
<script type="text/javascript" src="${cPath}/js/lprod.js"></script>
<link rel="stylesheet" href="${cPath }/css/prodList.css">

<div class="body" id="InnerContainer">

<c:set var="prodList" value="${pagingVO.dataList }" />
<div id="firstDiv">
<table class="mainTable">
	<tr>
		<td colspan="3">
			<div class="products-heading">
				<h2>WACCP PRODUCTS</h2>
			</div>	<!-- End of /.Products-heading -->
		</td>
	</tr>
	<tr>
		<form action="?" id="searchForm">
			<input type="hidden" name="page"/>
			<input type="hidden" name="lprodId" class="lprodId" />
			<td>
			<input id="searchInput" name="prodName" type="text" placeholder="제품명을 입력하세요."/>
			<sup><img src="${cPath }/images/search.png" class="btnSearchSend"></sup>
			</td>
		</form>
		<td style="width : 377px"></td>
		<td>
			<sup><img src="${cPath }/images/filterbig.png"></sup>
			<select id="selectFirst">
				<option value>카테고리 선택</option>
				<c:forEach items="${lprodList }" var="lprod">
					<option id="${lprod.lprodId }">${lprod.lprodNm }</option>
				</c:forEach>
			</select>
			<select id="selectSecond">
				<option value>선택</option>
			</select>
		</td>
	</tr>
	<tr><td class="blank" colspan="3"><hr color="lightgrey"></td></tr>
	<c:if test="${not empty prodList}">
	<tr class="product-grid">
	<c:forEach items="${prodList }" var="prod" varStatus="vs">
	<td id="shop" class="tdBody" data-prod="${prod.prodId }">
	<form class="tdBodyForm" action="${cPath }/prod/prodView.do" method="post">
		<input type="hidden" name="what" value="${prod.prodId }"/>
		<input type="hidden" name="memId" value="${loginId }"/>
	</form>
         <div class="products box${vs.count }" >
			<a href="#">
				<img src="data:image/*;base64,${prod.prod_imageBase64 }" />
			</a>
			<a href="#">
				<div class="prodName">
					<h4>${prod.prodName }</h4>
				</div>
			</a>
			<p class="price">${prod.prodPrice }원</p>
			<div class="align">
				<a class="view-link shutter" href="#">
				<i class="fa fa-plus-circle"></i>View Detail</a>
			</div>
		</div>	<!-- End of /.products -->
	</td>
	<c:if test="${vs.count eq 3}"> 
		<tr class="tempTr"></tr>
	</c:if> 
	</c:forEach>
	<tr class="pagingTr">
		<td colspan="3">
			<div id="pagingArea">
				${pagingVO.pagingHTML }
			</div>
		</td>
	<tr>
	</c:if>
	<c:if test="${empty prodList}">
	<tr>
		<td class="noProd" colspan="3">검색 결과가 없습니다.</td>
	</tr>
	</c:if>
</table>
		<div id="secondDiv" style="position:relative;">
			<!-- 판매 순위 5 -->
			<table id="topChartTable">
				<tr>
					<td style="font-size: 25px;" colspan="3">판매 TOP 5</td>
				</tr>
				<c:forEach items="${prodranklist }" var="prodrank" varStatus="vs">
					<tr>
						<td>${vs.count }</td>
						<td><div class="imgdiv">
								<img src="data:image/*;base64,${prodrank.prod_imageBase64 }"
									class="prodimg" />
							</div></td>
						<%-- 				<td>${prodrank.search.searchCount }</td> --%>
						<td>${prodrank.prodName }</td>
					</tr>
				</c:forEach>
			</table>
		</div>
	</div>

<script type="text/javascript">
	var tdBody = $(".tdBody");
	var searchForm = $("#searchForm");
	var pagingArea = $("#pagingArea");
	var pageTag = $("[name='page']");
	var selectFirst = $("#selectFirst");
	var selectSecond = $("#selectSecond");
	var btnSearchSend = $(".btnSearchSend");
	var lprodId = $(".lprodId");
	
	pageTag.val("1");

	pagingArea.on("click", "a", function(event){
		event.preventDefault();
		let page = $(this).data("page");
		if(page<1) return false;
		pageTag.val(page);
		searchForm.submit();
		return false;
	});
	
	tdBody.on("click", function(){
		$(this).find(".tdBodyForm").submit();
// 		let prodId = $(this).data("prod");
// 		location.href="${cPath}/prod/prodView.do?what="+prodId;
	});
	
	selectFirst.on("change", function(){
		let lgu = $(this).val();
		$(selectSecond).selectLprodB({
			lgu:lgu,
			plgu:lgu,
			cPath : "${cPath }"
		});
	});
	$(selectFirst).trigger("change");
	
	$(btnSearchSend).on("click", function(){
		searchForm.submit();
	})

	selectSecond.on("change", function(){
		let id = $(this).val();
		$(lprodId).val(id);
		searchForm.submit();
	});
	
	/*스크롤*/
	
	$(window).scroll(function() { 
		$('#secondDiv').animate({top:$(window).scrollTop()+"px" },{queue: false, duration: 500});
	}); 
	

</script>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<%--
* [[개정이력(Modification Information)]]
* 수정일                        수정자          수정내용
* --------------    ---------  -----------------
* 2019. 11. 6.        최서희          최초작성
* 2019. 11. 15.		   최서희		 로그인 한 기업회원의 판매리스트
* Copyright (c) 2019 by DDIT All right reserved
 --%>
<link rel="stylesheet" href="${cPath }/css/prodEmpList.css">

<div id="InnerContainer">

<div class="titleLeftBar">&nbsp; ${memName }( ${loginId } )님 제품 관리</div>
<c:set var="prodList" value="${pagingVO.dataList }" />
<table class="SearchT">
	<tr>
		<form action="?" id="searchForm">
		<td>
			<input type="text" id="searchInput" name="prodName"  placeholder="제품명을 입력하세요."/>
			<sup><img src="${cPath }/images/search.png" id="search"></sup>
		</td>
		<td class="blank"></td>
			<input type="hidden" name="page"/>
			<input type="hidden" name="searchType" value="code"/>
			<input type="hidden" name="searchWord" id="searchWord" />
			<td><img src="${cPath }/images/filterbig.png"></td>
			<td><a id="p2">대기중</a></td>
			<td>&nbsp;&nbsp;/&nbsp;&nbsp;</td>
			<td><a id="p1">판매중</a></td>
			<td>&nbsp;&nbsp;/&nbsp;&nbsp;</td>
			<td><a id="p0">판매중지</a></td>
		</form>
	<tr>
</table>
<br>
<table id="mainTable" class="table">
		<th>No</th>
		<th>이미지</th>
		<th class="leftTd">제품명</th>
		<th>가격</th>
		<th>배송비</th>
		<th>등록날짜</th>
		<th>판매상태</th>
		<th>비고</th>
	</tr>
	<c:if test="${prodList == null or empty prodList }">
	<tr><td colspan="8">판매중인 상품이 없습니다.</td></tr>
	</c:if>
	<c:if test="${prodList != null }">
	<c:forEach items="${prodList }" var="prod">
	<tr>
		<td>${prod.rnum }</td>
		<td ${prod.prodId }><img class="active prodImg" src="data:image/*;base64,${prod.prod_imageBase64 }" /></td>
		<td class="leftTd">
		<c:choose>
			<c:when test="${prod.prodApproval ne 'a1' or prod.prodUse eq 'p0' }">
				<a class="notApprovalProd" >${prod.prodName }</a>
			</c:when>
			<c:otherwise>
				<a class="nameURL" onclick="location.href='<c:url value='/prod/prodView.do?what=${prod.prodId }'/>';">${prod.prodName }</a>
			</c:otherwise>		
		</c:choose>
		</td>
		<td>${prod.prodPrice }</td>
		<c:if test="${prod.prodDelivery eq 0 }">
			<td>무료</td>
		</c:if>
		<c:if test="${prod.prodDelivery ne 0 }">
			<td>${prod.prodDelivery }</td>
		</c:if>
		<td>${prod.prodIndate }</td>
		<c:if test="${prod.prodApproval eq 'a0' }">
			<td>신청반려</td>
		</c:if>
		<c:if test="${prod.prodApproval ne 'a0' }">
			<td>${prod.code.codeName }</td>
		</c:if>
		<c:if test="${prod.prodApproval eq 'a0' }">
			<td>
				<input id="${prod.prodId }" type="button" class="btn btnGreen btnLong btnReasonCheck" value="반려사유" />
			</td>
		</c:if>
		<c:if test="${prod.prodUse eq 'p1' and prod.prodApproval eq 'a1'}">
			<td>
				<c:url value="/prod/prodUpdateStop.do" var="updateStopURL">
					<c:param name="prodId" value="${prod.prodId }"></c:param>
					<c:param name="authorId" value="${authorId }"></c:param>
				</c:url>
				<input type="button" class="btn btnWhite btnLong" 
						onclick="location.href='${updateStopURL}';" value="판매중지" />
			</td>
		</c:if>
		<c:if test="${prod.prodUse eq 'p0' and prod.prodApproval eq 'a1'}">
			<td></td>
		</c:if>
	</tr>
	</c:forEach>
	</c:if>
</table>
<div>
</div>
<div id="pagingArea" class="pagingTr">
	${pagingVO.pagingHTML }
</div>

  <!-- Modal 반려사유 출력 -->
  <div class="modal fade" id="yourModal" role="dialog">
    <div class="modal-dialog">
    
      <!-- Modal content-->
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">&times;</button>
          <div class="modal-title">반려사유 입니다.</div>
          <div id="reasonDate"></div>
        </div>
        <div class="modal-body">
          <div id="reasonContent"></div>
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btnGreen" data-dismiss="modal">확인</button>
        </div>
      </div>
      
    </div>
  </div>
</div>

<script type="text/javascript">
	var pagingArea = $("#pagingArea");
	var pageTag = $("[name='page']");
	var search = $("#search");
	var mainTable = $("#mainTable");
	
	var yourModal = $("#yourModal");
	var reasonDate = $("#reasonDate");
	var reasonContent = $("#reasonContent");
	
	
	pageTag.val("1");

	pagingArea.on("click", "a", function(event){
		event.preventDefault();
		let page = $(this).data("page");
		if(page<1) return false;
		pageTag.val(page);
		searchForm.submit();
		return false;
	});
	
	search.on("click", function(){
		searchForm.submit();
	});
	
	
	//반려사유 확인
	mainTable.on("click", ".btnReasonCheck", function(){
		var prodId = $(this).prop("id");
		$.ajax({
			url : "${cPath}/prod/prodRejectView.do",
			method : "post",
			data : {"prodId" : prodId},
			dataType : "json",
			success : function(resp) {
				reasonDate.text(resp.rejectDate);
				reasonContent.text(resp.rejectReason);
				$(yourModal).modal();
			},
			error : function(xhr) {
				console.log(xhr.status);
			}
		});
	})
	
	$("#p2").on("click", function(){
		let a = $(this).prop("id");
		$(searchWord).val(a);
		searchForm.submit();
	})
	$("#p1").on("click", function(){
		let a = $(this).prop("id");
		$(searchWord).val(a);
		searchForm.submit();
	})
	$("#p0").on("click", function(){
		let a = $(this).prop("id");
		$(searchWord).val(a);
		searchForm.submit();
	})
	$(mainTable).on("click", ".notApprovalProd", function(){
		Swal.fire({
		  	icon: 'error',
		  	text: '판매중인 제품만 상세보기가 가능합니다.'
		})
	})

	
</script>
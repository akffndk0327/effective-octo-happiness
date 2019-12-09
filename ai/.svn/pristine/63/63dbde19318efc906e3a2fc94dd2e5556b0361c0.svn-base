<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%--
* [[개정이력(Modification Information)]]
* 수정일                         수정자              수정내용
* --------------  ---------  -----------------
* 2019. 11. 6.      최서희              최초작성
* 2019. 11. 16.	       최서희		폼 view 구성
* Copyright (c) 2019 by DDIT All right reserved
 --%>
<link rel="stylesheet" href="${cPath }/css/prodAdminForm.css">

<div id="InnerContainer">
<div class="titleLeftBar">제품등록 승인</div>
<p class="p">( 제품ID : ${prod.prodId} )</p>
<form action="${cPath }/prod/prodAdminInsert.do" method="post">
<input type="hidden" value="${prod.lprodId}" id="lprodId" name="lprodId"/>
<input type="hidden" value="${prod.prodId }" name="prodId"/>
<table class="mainTable">
	<thead>
	<tr>
		<td class="three" rowspan="5">
			<img class="active prodImg" src="data:image/*;base64,${prod.prod_imageBase64 }" />
<!-- 			<div> -->
<!-- 				<input type="button" class="btn logo" value="Logo1" /> -->
<!-- 				<input type="button" class="btn logo" value="Logo2" /> -->
<!-- 			</div> -->
		</td>
		<td class="one" colspan="2" class="pre">
			<img src="${cPath }/images/company.png"> ${prod.comName}( ${prod.memId } )
		</td>
	</tr>
	<tr>
		<td class="one">품목명</td>
		<td class="two">${prod.lprod.lprodParent} > ${prod.lprod.lprodNm}</td>
	</tr>
	<tr>
		<td class="one">제품명</td>
		<td class="two">${prod.prodName}</td>
	</tr>
	<tr>
		<td class="one">가격</td>
		<td class="two">${prod.prodPrice}원</td>
	</tr>
	<tr>
		<td class="one">배송비</td>
		<td class="two">${prod.prodDelivery}원</td>
	</tr>
	</thead>
	<tbody>
	<tr>
		<td class="titleTd" colspan="2">
			<div class="title"><sup><img src="${cPath }/images/chemical.png"></sup> 전 성분 : </div>
		</td>
		<td rowspan="2">
			<div class="detailImg">
				<c:if test="${not empty prod.attatchList }">
					<c:forEach items="${prod.attatchList }" var="attatch" varStatus="vs">
					<img src="${cPath }/prod/prodDownload.do?prodId=${prod.prodId }">
 						<c:url value="/prod/prodDownload.do" var="downloadURL">
 							<c:param name="prodId" value="${prod.prodId }" /> 
 						</c:url>
 						<div class="down"><a href="${downloadURL }">▼ Download</a></div>
 					${not vs.last?",":"" } 
 					</c:forEach> 
 				</c:if> 
			</div>
		</td>
	</tr>
	<tr>
		<td id="plusTd" colspan="2">
			<input type="text"/>
			<c:if test="${fn:substring(prod.lprodId,0,1) eq 'L'}">
				<input type="hidden" name="casId" class="casId"/>
			</c:if>
			<c:if test="${fn:substring(prod.lprodId,0,1) eq 'F'}">
				<input type="hidden" name="rawId" class="rawId"/>
			</c:if>
			<input type="button" class="btn btnSearch btnGreen" value="검색" />
			<img src="${cPath }/images/plusblue.png" id="btnPlus">
		</td>
	</tr>
	</tbody>
</table>
<div id="btnDiv">
<input type="button" class="btn btnAi1" value="취소" onclick="history.back();" />
<input type="submit" class="btn btnAi0" value="저장" />
</div>
</form>
</div>

 <!-- Modal -->
  <div class="modal fade" id="myModal" role="dialog">
    <div class="modal-dialog">
    
      <!-- Modal content-->
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">&times;</button>
          <h4 class="modal-title">화학성분 검색</h4>
        </div>
        <div class="modal-body">
          <input type="text" id="inputTag" />  
          <hr color="#ddd">
          <table id="modalTable"></table>        
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btnWhite" id="btnClose" data-dismiss="modal">Close</button>
        </div>
      </div>
      
    </div>
  </div>
  
</div>

<script type="text/javascript">
var plusTd = $("#plusTd");
var btnPlus = $("#btnPlus");
var btnClose = $("#btnClose");
var btnEnter = $("#btnEnter");
var inputTag = $("#inputTag");
var lprodId = $("#lprodId").val();

var modalTable = $("#modalTable");

//Lprod => L, F
var option = lprodId.substring(0,1);

//플러스 버튼
$(btnPlus).on("click", function(){
	let tdTag = $(this).closest("td");
	tdTags = "<div>";
	tdTags += "<input type='text'/>";
	if(option == 'L'){
		tdTags += "<input type='hidden' name='casId' class='casId' /> ";
	}else if(option == 'F'){
		tdTags += "<input type='hidden' name='rawId' class='rawId' /> ";
	}
	tdTags += "<input type='button' class='btn btnSearch btnGreen' value='검색' /> ";
	tdTags += "<img src='${cPath }/images/minusblue.png' class='btnMinus'>";
	tdTags += "</div>";
	
	tdTag.append(tdTags);
})

//마이너스 버튼
$(plusTd).on("click", ".btnMinus", function(){
	$(this).parent("div").remove();
});

//검색 버튼
var temp;
$(plusTd).on("click", ".btnSearch", function(){
	temp = $(this);
    $("#myModal").modal();
    modalTable.empty();
})

$(inputTag).on("blur", function(){
	var k = $(this).val();
// 	$(modalTable).children().remove();
	if(option == 'L'){
		$.ajax({
			url : "${cPath}/chemical/chemicalList.do",
			method :"get",
			data : {
					"name" : k
				},
			dataType : "json",
			success : function(resp) {
				let tags = [];
				$(resp).each(function(i, v){
					let tag = $("<tr>").append(
							$("<td>").text(v.cheNameKo),
							$("<td>").text(v.cheNameEn)
					).prop("id", v.casId)
				tags.push(tag);
				});	
				$(inputTag).val("");
				modalTable.html(tags);
			},
			error : function(xhr) {
				console.log(xhr.status);
			}
		});
	}else if(option == 'F'){
		$.ajax({
			url : "${cPath}/chemical/rawmaterialList.do",
			method :"get",
			data : {
					"name" : k
				},
			dataType : "json",
			success : function(resp) {
				let tags = [];
				$(resp).each(function(i, v){
					let tag = $("<tr>").append(
							$("<td>").text(v.rawName)
					).prop("id", v.rawId)
				tags.push(tag);
				});	
				$(inputTag).val("");
				modalTable.html(tags);
			},
			error : function(xhr) {
				console.log(xhr.status);
			}
		});
	}
})

//모달창 취소버튼 클릭시 내용 비우기
$(btnClose).on("click", function(){
	$(inputTag).val("");
})

//모달창 검색결과 선택
$(modalTable).on("click", "tr", function(){
		//아이디 저장
		let what = $(this).prop("id");
		temp.prev().val(what);
	if(option == 'L'){
		//이름 표시
		let ko = $(this).children().first().text();
		let en = $(this).children().last().text();
		temp.prev().prev().val(ko+" ("+en+")");
	}else if(option == 'F'){
		let name = $(this).children().first().text();
		temp.prev().prev().val(name);
	}
	$("#myModal").modal('hide');
})


</script>


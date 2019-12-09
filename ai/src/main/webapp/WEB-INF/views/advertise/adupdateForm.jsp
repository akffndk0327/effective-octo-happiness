<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%--
* [[개정이력(Modification Information)]]
* 수정일                 수정자      수정내용
* ----------  ---------  -----------------
* 2019. 11. 28.      박주연      최초작성 회원이 수정할때 
* Copyright (c) 2019 by DDIT All right reserved
 --%>
<script type="text/javascript"
	src="https://cdn.iamport.kr/js/iamport.payment-1.1.5.js"></script>
<link rel="stylesheet" href="${cPath }/css/orderForm.css">
<script src="${cPath }/js/orderForm.js"></script>
<link rel="stylesheet"
	href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>

<!-- Datepicker 한국어로 변환 -->
<script
	src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.11.4/i18n/datepicker-ko.js"></script>
<style>
td {
	border: 1px solid black;
}

th {
	border: 1px solid black;
	text-align: center;
}

.gig-detail-option-btn:nth-child(2), .gig-detail-decision-option-btn>a:nth-child(2),
	.gig-detail-decision-option-btn>span:nth-child(2) {
	width: 30px;
	heigth: 25px;
	line-height: 18.5px;
	background-color: #fff;
	border-radius: 0;
	line-height: 18.5px
}

.gig-detail-option-btn, .gig-detail-decision-option-btn>a,
	.gig-detail-decision-option-btn>span {
	width: 30px;
	heigth: 25px;
	line-height: 20px;
	background-color: #90c322;
	display: inline-block;
	text-align: center;
	color: #4d4d4d;
	font-size: 15px;
}

.col-xs-offset-7 {
	margin-left: 58.33333%;
}

.col-xs-5 {
	width: 41.66667%;
}

#options .list-group-item:first-child, #options .extra-option-list-group-item:first-child,
	#options .index-side-list-group-item:first-child, #options .gig-form-container .gig-form-list-group-item:first-child,
	.gig-form-container #options .gig-form-list-group-item:first-child {
	border-top: 1px solid #999 !important;
}

textarea:focus {
	outline: none;
}

.adInput {
	width: 50%
}

#fromDate, #toDate {
	width: 20%;
}
</style>
<div id="InnerContainer">
	<h3 class="titleTopBar">광고 수정</h3>
	<div class="container">
		<div id="adRead"
			style="font-size: 17px; border-top: 1px solid black; border-bottom: 1px solid black; padding: 15px">
			광고 신청 전 꼭 읽어주세요 !<br /> &emsp;&emsp;&emsp;&emsp;&emsp;&emsp;
			<광고 신청 방법> <br>
			1. 신청자 정보를 입력하세요. <br>
			2. 광고를 게시할 게시판을 선택하세요.<br>
			3. 광고를 게시할 위치를 선택하세요.<br>
			4. 광고 시작 날짜를 선택하세요 (당일 선택 불가. 관리자 승인까지 2일은 소요됨니다.)<br>
			5. 광고 기간은 기본 30일입니다. 그 이상의 게시기간은 관리자에게 연락하세요. <br>
			6. 이미지를 첨부해주세요. (첨부파일은 1개만 등록 가능하며 크기는 1MB이하입니다.)<br>
			7. 관리자에게 남길말을 써주세요.<br>
			8. 신청버튼을 누르면 결제 화면으로 이동합니다.<br>
			9. 결제 후 마이페이지 > 광고 관리 에서 작성글을 확인 할 수 있습니다.<br>
			<br>
			&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;** 주의 사항 **<br>
			1. 수정은 1회만 가능합니다.<br />
			2. 한 게시판에 광고 신청은 5건으로 제한 됩니다. <br />
			3. 광고는 랜덤으로 보여집니다. <br />
			4. 광고 신청 후 승인까지 최소 2일이 소요됩니다.<br />
			5. 광고 내용이 부적절하거나 홈페이지와 관련없는 내용의 광고는 관리자에 의해 반려 될 수 있습니다.<br />
			6. 회원들을 위한 레포트 서비스를 제공해드립니다. 
		</div>
		<br> <br>
		<form action="${cPath }/advertise/admemUpdate.do" id="adForm2" method="post" enctype="multipart/form-data">
			<input type="hidden" name="adId" value="${advertise.adId }">
			<table style="margin-left: 9%; width: 1000px">
				<tr>
					<td style="border: none">
						<h3 class="titleTopBar" style="font-size: 25px">신청자 정보</h3>
					</td>
				</tr>
				<tr>
					<th>* 제목</th>
					<td><input type="text" name="adTitle" class="adInput"
						value="${advertise.adTitle }" class="INP100"> <span
						class="error">${errors.adTitle }</span></td>
				</tr>
				<tr>
					<th scope="row">* 담당자명</th>
					<td>${advertise.memId}</td>
				</tr>
				<tr>
					<th>* 게시판 선택</th>
					<td>${advertise.adhit.adposition.resource.resName }
						<select id="boardCode" name="resource2.resId" value="${advertise.adhit.adposition.resource.resName }">
							<option value>선택해주세요</option>
							<option value="S011">정정게시판</option>
							<option value="S070">생활용품 게시판</option>
							<option value="S078">뉴스 게시판</option>
					</select>
					</td>
				</tr>
				<tr>
					<th>* 위치</th>
					<td>${advertise.adhit.adposition.adpoName }
						<select id="position" name="adposition.adpoId" value="${advertise.adhit.adposition.adpoName }"  >
							<option value>선택하세요</option>
							<option value="TOP">상단</option>
							<option value="BOTTOM">하단</option>
							<option value="LEFT">왼쪽</option>
							<option value="RIGHT">오른쪽</option>
					</select>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div id="location">
							<img class="adimg" src="${cPath }/images/adposition.png"
								style="margin: 0 13% 0 16%;" />
						</div>
					</td>
				</tr>

				<tr>
					<th>* 광고 시작 날짜</th>
					<td><input type="text" class="inputDate" name="adIndate"
						id="fromDate" size="8" class="adInput" readonly="readonly"
						value="${advertise.adIndate }"> <input type="button"
						value="확인" id="datecheck" /></td>
				</tr>
				<tr>
					<th>* 광고 종료일</th>
					<td><input type="text" class="inputDate" name="adTerm"
						id="toDate" size="8" class="adInput" readonly="readonly"
						value="${advertise.adTerm }"></td>
				</tr>
				<tr>
					<th>* 이동 URL</th>
					<td><input type="text" placeholder="https://" class="adInput"
						name="adLink" value="${advertise.adLink }"></td>
				</tr>
				<tr>
					<th> 기존 첨부파일 </th>
					<td>
						<c:forEach items="${advertise.adattatchList}" var="attatch">
						<span>
							<img src="${cPath }/advertise/addownload.do?what=${attatch.adattId}" style="max-width:120px; max-height:400px">
							<a class="attDelBtn" data-adattId="${attatch.adattId }">
							<i class="fas fa-minus-square" style="font-size: 30px;"></i></a>
						</span>
						</c:forEach>
					</td>
				</tr>
				<tr>
					<th>* 첨부파일</th>
					<td><c:forEach items="${advertise.adattatchList}"
							var="attatch">
							<img src="${cPath }/advertise/addownload.do?what=${attatch.adattId}" style="max-width:120px; max-height:400px">
						</c:forEach> <input type="file" name="adfile" data-width="120"
						data-height="400" id="attatch" />
					</td>
				</tr>
			</table>
			<br>

			<!-- 			취소 환불 규정 -->
			<div id="asrule" style="float: left; margin-left: 9%;">
				<p style="font-size: 30px; font-weight: bold;">취소 규정</p>
				<div
					style="font-size: 21px; word-break: break-all; white-space: pre-line">
					01. 작업 시작 전 100% 환불 가능합니다.(작업 시작 후에도 결과물이 완성되지 않았을 경우에는 100% 환불
					가능합니다.) 02. 판매자의 사정으로 작업이 당초 약속했던 일자보다 지연되거나 그에 상응하는 사안이 발생하였을 경우
					진행한 작업 건수 또는 작업 노출 일수를 계산하여 제외한 금액을 환불 가능합니다. (1) 작업 건수 환불금액 = 결제금액
					- (진행한 작업 건수 × 건단가) (2) 작업 노출 기간 환불금액 = 결제금액 ÷ 30일 × 작업 노출되지 않은 일수
					03. 협의기간, 작업 기간, 광고기간에 따른 환불 규정 - 전문가가 작업을 시작하기 전, 의뢰인과 작업 일정 및 가능
					여부를 논의하는 '협의기간'에는 100% 환불 가능합니다. - 사전 협의된 광고기간(노출 기간)에는 작업건수/노출기간에
					따라 환불 금액이 산출됩니다. (단, 의뢰인의 과실이나 단순 변심, 광고 매체 규제 등으로 인한 불이익은 환불이 불가)
					04. 컨설팅·노하우 강의 및 자료, 솔루션 등 서비스 상품을 받은 이후에는 환불이 불가합니다. 05. 이메일
					·메시지(솔루션 포함) 발송 이후에는 환불이 불가합니다.</div>
			</div>
			<input type="hidden" name="memId" value="${loginId}" /> 
			<input id="adapply" type="submit" class="btn btnAi0" value="수정 ">
			<input type="button" class="btn btnAi1" value="취소" onclick="history.back();">

		</form>
	</div>
	<!-- 	container 끝 -->
</div>
<!-- InnerContainer 끝  -->

<script>
	var attatch = $("#attatch");
	var memName = $("[name='memName']").val();
	// alert(totalPrice);
	var defaultCheck1 = $("#defaultCheck1");
	var checked = false;
	var adForm =$("#adForm");

	$(defaultCheck1).change(function() {
		checked = $(this).prop('checked'); // checked 상태 (true, false)
	});
	var fromDate = $("#fromDate");
	$(function() {
		$.datepicker.setDefaults($.datepicker.regional['ko']);
		//시작일.
		$('#fromDate').datepicker({
			dateFormat : "yy-mm-dd", // 날짜의 형식
			changeMonth : true, // 월을 이동하기 위한 선택상자 표시여부
			minDate : 0, // 선택할수있는 최소날짜, ( 0 : 오늘 이전 날짜 선택 불가)
			onClose : function(selectedDate) {
				// 시작일(fromDate) datepicker가 닫힐때
				// 종료일(toDate)의 선택할수있는 최소 날짜(minDate)를 선택한 시작일로 지정
				$("#toDate").datepicker("option", "minDate", selectedDate);
			}
		});

		// 		
		$("#datecheck").on('click', function() {
			var start = $("#fromDate").val();
			var d1 = new Date(Date.parse(start) + 30 * 1000 * 60 * 60 * 24);
			var year = d1.getFullYear();
			var month = d1.getMonth() + 1;
			var day = d1.getDate();
			$("#toDate").val(year + "-" + month + "-" + day);
		})
	});
	
	$(".attDelBtn").on("click", function(){
		let adattId = $(this).data("adattId");
		$(this).parent("span").remove();
	});
	
</script>
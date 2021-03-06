<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%--
* [[개정이력(Modification Information)]]
* 수정일                 수정자      수정내용
* ----------  ---------  -----------------
* 2019. 11. 7.      최서희      최초작성
* Copyright (c) 2019 by DDIT All right reserved
 --%>
<!-- <script src="https://code.jquery.com/jquery-1.12.4.js"></script> -->
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<link rel="stylesheet" href="${cPath }/css/orderList.css">

<div class="body" id="InnerContainer">

<c:set var="orderList" value="${pagingVO.dataList }" />
<table class="filterTable">
<tr>	
	<td colspan="7" class="titleTd"><div class="title">&nbsp; ${memName }( ${loginId } )님 주문 관리</div></td>
</tr>
<tr>
	<td class="blankCenter"></td>
	<form action="?" id="searchForm">
		<input type="hidden" name="page"/>
		<input type="hidden" name="searchType" value="code"/>
			<input type="hidden" name="searchWord" id="searchWord" />
		<td><img src="${cPath }/images/filterbig.png"></td>
		<td><a id="d2">배송준비중</a></td>
		<td>&nbsp;&nbsp;/&nbsp;&nbsp;</td>
		<td><a id="d1">배송완료</a></td>
		<td>&nbsp;&nbsp;/&nbsp;&nbsp;</td>
		<td><a id="d0">주문취소</a></td>
	</form>
</tr></table>
<c:set var="orderList" value="${pagingVO.dataList }" />
<table class="mainTable">
	<tr>
		<th class="center">주문번호</th>
		<th class="prodName">제품명</th>
		<th class="prodQty">수량</th>
		<th class="middle">결제금액 </th>
		<th class="center">구매날짜 </th>
		<th class="center">결제상태 </th>
		<th class="center">배송상태 </th>
		<th class="center">비고</th>
	</tr>
	<c:if test="${orderList==null or empty orderList}">
	<tr><td class="noList" colspan="8">관리 가능한 상품이 없습니다. </td></tr>
	</c:if>
	<c:if test="${orderList!=null}" >
	<c:forEach items="${orderList }" var="order">
	<tr>
		<td class="center">
			<c:url value="/order/orderView.do" var="orderViewURL">
				<c:param name="orderId" value="${order.orderId }" />
			</c:url>
			<a class="a" href="${orderViewURL }" class="orderId">${order.orderId }</a>
		</td>
		<td colspan="3">
		<c:forEach items="${order.orderDtList }"  var="dt">
			<table class="dtTable"><tr>
				<td class="prodName">${dt.prodVO.prodName }</td>
				<td class="prodQty">${dt.orderQty }</td>
				<td class="prodPrice">${(dt.prodVO.prodPrice * dt.orderQty) + dt.prodVO.prodDelivery }원</td>
			</tr></table>
		</c:forEach>
		</td>
		<td class="center">${order.orderDate }</td>
		<td class="center">${order.payVO.payStatusName }</td>
		<td class="center status">${order.orderStatusName}</td>
		<td class="center">
			<c:if test="${order.payVO.payStatus eq 'k1' and order.orderStatus eq 'd2'}">
				<c:url value="/order/orderUpdate.do" var="orderUpdateURL">
					<c:param name="orderId" value="${order.orderId }"></c:param>
				</c:url>
				<input type="button" class="btn btnRefund" onclick="location.href='${orderUpdateURL}';" value="배송완료" />
			</c:if>
			<c:if test="${order.orderStatus eq 'd1' and order.refund.refundApproval eq 'a2' }">
				<input type="hidden" value="${order.refund.refundDate}"/>
				<input type="hidden" value="${order.refund.refundReason}"/>
				<input type="button" class="btn btnCancel btnReasonCheck" value="반품신청 확인" />
				<input type="hidden" value="${order.orderId }" />
			</c:if>
			<c:if test="${order.orderStatus eq 'd1' and order.refund.refundApproval eq 'a0' }">
				<div>반품신청 거절</div>
			</c:if>
			<c:if test="${order.orderStatus eq 'd0' and order.refund.refundApproval eq 'a1' }">
				<div>반품처리 승인</div>
			</c:if>
		</td>
	</tr>
	</c:forEach>
	</c:if>
</table>
<div id="pagingArea">
	${pagingVO.pagingHTML }
</div>

</div>

  <!-- Modal 환불신청 출력 -->
  <div class="modal fade" id="yourModal" role="dialog">
    <div class="modal-dialog">
      <!-- Modal content-->
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">&times;</button>
          <div class="modal-title">환불신청 사유 입니다.</div>
          <div id="reasonDate"></div>
        </div>
        <div class="modal-body">
          <div id="reasonContent"></div>
        </div>
        <div class="modal-footer">
          <form action="${cPath }/refund/refundUpdateApproval.do" method="get">
          	<input type="hidden" name="orderId" />
          	<button id="btnYes" type="button" class="btn btnGreen" data-dismiss="modal" style="margin-right: 66px;">승인</button>
          </form>
          <form action="${cPath }/refund/refundUpdateApproval.do" method="post">
          	<input type="hidden" name="orderId" />
	        <button id="btnNo" type="button" class="btn btnWhite" data-dismiss="modal" style="margin-top: -57px;">반려</button>
          </form>
        </div>
      </div>
    </div>
  </div>

<script type="text/javascript">
var tdBody = $(".tdBody");
var pagingArea = $("#pagingArea");
var pageTag = $("[name='page']");
var selectFirst = $("#selectFirst");
var selectSecond = $("#selectSecond");

var mainTable = $(".mainTable");

var yourModal = $("#yourModal");
var reasonContent = $("#reasonContent");
var reasonDate = $("#reasonDate");

var btnYes = $("#btnYes");
var btnNo = $("#btnNo");

pageTag.val("1");

pagingArea.on("click", "a", function(event){
	event.preventDefault();
	let page = $(this).data("page");
	if(page<1) return false;
	pageTag.val(page);
	searchForm.submit();
	return false;
});

var clickThis;
//환불사유 확인
$(mainTable).on("click", ".btnReasonCheck", function(){
	clickThis = $(this);
	let date = $(this).prev().prev().val();
	let reason = $(this).prev().val();
	reasonDate.text(date);
	reasonContent.text(reason);
	$(yourModal).modal();
})

//환불 승인
btnYes.on("click", function(){
	let orderId = clickThis.next().val();
	$(this).prev().val(orderId);
	$(this).parent().submit();
});

//환불 반려
btnNo.on("click", function(){
	let orderId = clickThis.next().val();
	$(this).prev().val(orderId);
	$(this).parent().submit();
});

//input을 datepicker로 선언
$("#datepicker1").datepicker({
    dateFormat: 'yy-mm-dd' //Input Display Format 변경
    ,showOtherMonths: true //빈 공간에 현재월의 앞뒤월의 날짜를 표시
    ,showMonthAfterYear:true //년도 먼저 나오고, 뒤에 월 표시
    ,changeYear: true //콤보박스에서 년 선택 가능
    ,changeMonth: true //콤보박스에서 월 선택 가능                
    ,yearSuffix: "년" //달력의 년도 부분 뒤에 붙는 텍스트
    ,monthNamesShort: ['1','2','3','4','5','6','7','8','9','10','11','12'] //달력의 월 부분 텍스트
    ,monthNames: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'] //달력의 월 부분 Tooltip 텍스트
    ,dayNamesMin: ['일','월','화','수','목','금','토'] //달력의 요일 부분 텍스트
    ,dayNames: ['일요일','월요일','화요일','수요일','목요일','금요일','토요일'] //달력의 요일 부분 Tooltip 텍스트
    ,maxDate: "+0D" //최대 선택일자(+1D:하루후, -1M:한달후, -1Y:일년후)                
});                    
$("#datepicker2").datepicker({
    dateFormat: 'yy-mm-dd' //Input Display Format 변경
    ,showOtherMonths: true //빈 공간에 현재월의 앞뒤월의 날짜를 표시
    ,showMonthAfterYear:true //년도 먼저 나오고, 뒤에 월 표시
    ,changeYear: true //콤보박스에서 년 선택 가능
    ,changeMonth: true //콤보박스에서 월 선택 가능                
    ,yearSuffix: "년" //달력의 년도 부분 뒤에 붙는 텍스트
    ,monthNamesShort: ['1','2','3','4','5','6','7','8','9','10','11','12'] //달력의 월 부분 텍스트
    ,monthNames: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'] //달력의 월 부분 Tooltip 텍스트
    ,dayNamesMin: ['일','월','화','수','목','금','토'] //달력의 요일 부분 텍스트
    ,dayNames: ['일요일','월요일','화요일','수요일','목요일','금요일','토요일'] //달력의 요일 부분 Tooltip 텍스트
    ,maxDate: "+0D" //최대 선택일자(+1D:하루후, -1M:한달후, -1Y:일년후)                
});                    

//초기값을 오늘 날짜로 설정
$('#datepicker1').datepicker(); 
$('#datepicker2').datepicker();

$("#d2").on("click", function(){
	let a = $(this).prop("id");
	$(searchWord).val(a);
	searchForm.submit();
})
$("#d1").on("click", function(){
	let a = $(this).prop("id");
	$(searchWord).val(a);
	searchForm.submit();
})
$("#d0").on("click", function(){
	let a = $(this).prop("id");
	$(searchWord).val(a);
	searchForm.submit();
})
</script>

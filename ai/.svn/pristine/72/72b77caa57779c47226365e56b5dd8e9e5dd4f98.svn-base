<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<%--
* [[개정이력(Modification Information)]]
* 수정일                 수정자      	    수정내용
* -------------  ---------  -----------------
* 2019. 11. 6.   최서희  		    최초작성
* 2019. 11. 13.  최서희		   로그인 한 회원 주문리스트
* Copyright (c) 2019 by DDIT All right reserved
 --%>
<!-- <script src="https://code.jquery.com/jquery-1.12.4.js"></script> -->
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<link rel="stylesheet" href="${cPath }/css/orderList.css">
 
<div class="body" id="InnerContainer">
<form action="?" id="searchForm">
	<input type="hidden" name="page"/>
</form>
<table class="filterTable">
	<tr>
		<td class="oneTd" colspan="2"><div class="title">&nbsp; ${memName }( ${loginId } )님 주문 리스트</div></td>
	</tr>
	<tr>
		<td class="blank2"></td>
		<td>
			<input type="text" name="minDate" id="datepicker1" data-position="right top" />
			~ &nbsp;
			<input type="text" name="maxDate" id="datepicker2" data-position="right top" />
			<sup><img src="${cPath }/images/search.png"></sup>
		</td>
	</tr>
</table>
<c:set var="orderList" value="${pagingVO.dataList }" />
<table class="mainTable">
	<tr>
		<th class="center">주문번호</th>
		<th class="prodName">제품명</th>
		<th class="center">결제금액 </th>
		<th class="center">구매날짜 </th>
		<th class="center">결제상태 </th>
		<th class="center">배송상태 </th>
		<th class="center2">비고</th>
	</tr>
	<c:if test="${not empty orderList}">
	<c:forEach items="${orderList }" var="order">
	<tr>
		<td class="center">
			<c:url value="/order/orderView.do" var="orderViewURL">
				<c:param name="orderId" value="${order.orderId }" />
			</c:url>
			<a class="a" href="${orderViewURL }" class="orderId">${order.orderId }</a>
		</td>
		<c:if test="${order.plus eq 0}">
			<td class="prodName">${order.prod.prodName }</td>
		</c:if>
		<c:if test="${order.plus gt 0}">
			<td class="prodName">${order.prod.prodName } 외 ${order.plus} 건</td>
		</c:if>
		<td class="center">${order.payPrice }</td>
		<td class="center">${order.orderDate }</td>
		<td class="center">${order.payVO.payStatusName }</td>
		<td class="center">${order.orderStatusName}</td>
		<td class="center2">
			<c:if test="${order.orderStatus eq 'd2'}">
			<c:url value="/order/orderDelete.do" var="orderCancelURL">
				<c:param name="orderId" value="${order.orderId }" />
			</c:url>
				<input type="button" class="btn btnCancel" onclick="location.href='${orderCancelURL}';" value="주문취소" />
			</c:if>
			<c:if test="${order.orderStatus eq 'd1' and order.refund.refundReason eq null}">
				<form action="${cPath }/refund/refundInsert.do" class="refundForm">
					<input type="hidden" name="orderId" value="${order.orderId }" />
					<input type="hidden" name="refundReason" />
					<input type="button" class="btn btnRefund" value="환불신청" />
				</form>
			</c:if>
			<c:if test="${order.refund.refundApproval eq 'a2' }">
				<div>환불신청처리중 :)</div>
			</c:if>
			<c:if test="${order.refund.refundApproval eq 'a1' }">
				<div>환불처리완료</div>
			</c:if>
			<c:if test="${order.refund.refundApproval eq 'a0' }">
				<div>환불처리거절</div>
			</c:if>
		</td>
	</tr>
	</c:forEach>
	</c:if>
	<c:if test="${empty orderList}">
	<tr>
		<td class="noOrder" colspan="7">주문 내역이 없습니다.</td>
	</tr>
	</c:if>
</table>
<div id="pagingArea">
	${pagingVO.pagingHTML }
</div>

</div>

 <!-- Modal 반려사유 입력 -->
  <div class="modal fade" id="myModal" role="dialog">
    <div class="modal-dialog">
    
      <!-- Modal content-->
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">&times;</button>
          <div class="modal-title">환불 사유를 입력하세요.</div>
        </div>
        <div class="modal-body">
          <input type="text" id="inputReason"/>
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btnGreen" id="btnSendReason">확인</button>
          <button type="button" class="btn btnWhite" id="btnClose" data-dismiss="modal">취소</button>
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

var btnSendReason = $("#btnSendReason");
var inputReason = $("#inputReason");
var btnClose = $("#btnClose");

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

var thisTemp;
var temp;
//반품신청 버튼 클릭
$(mainTable).on("click", ".btnRefund", function(){
	thisTemp = $(this);
	temp = $(this).prev();
	$("#myModal").modal();
});

//모달창에서 반려사유 입력 후 확인 버튼 클릭
$(btnSendReason).on("click", function(){
	temp.val($(inputReason).val());
	
	var id="giup21";
	var orderId=$("input[name='orderId']").val();
	var refundReason=$("input[name='refundReason']").val();
	var login="${loginId}";
	var text = login+"님이 "+orderId+" 주문건에 대해 '"+refundReason+"'을 사유로 "+ "환불 요청 하셨습니다.";
	
	console.log(id);
	console.log(orderId);
	console.log(refundReason);
	console.log(login);
	console.log(text);
	
	$.ajax({
 url : "${cPath}/note/sendingNote.do",
 method : "post",
 data : {
     "id" : id,
     "text" : text,
     "selected" : "self",
     "login" : login
 },
 dataType : "json",
 success : function(resp) {
     resp = resp.toString();
     resp = resp.split(",");
     if (resp[0] == 'ok') {
         resp.splice(0,2);
         var reciver;
         for (var i = 0; i < resp.length; i++) {
             sock.send(resp[i]);
         }
	$(thisTemp).parent().submit();
     }
 }
});
})

//모달창 취소버튼 클릭시 내용 비우기
$(btnClose).on("click", function(){
	$(inputReason).val("");
})


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
$('#datepicker1').datepicker(); //(-1D:하루전, -1M:한달전, -1Y:일년전), (+1D:하루후, -1M:한달후, -1Y:일년후)
$('#datepicker2').datepicker(); //(-1D:하루전, -1M:한달전, -1Y:일년전), (+1D:하루후, -1M:한달후, -1Y:일년후)
</script>

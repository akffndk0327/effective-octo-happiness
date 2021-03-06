<%--
* [[개정이력(Modification Information)]]
* 수정일                       수정자        수정내용
* --------------  ---------   -----------------
* 2019. 11. 6.       최서희         최초작성
* 2019. 11. 12.      최서희         주문결제 기능 구현
* Copyright (c) 2019 by DDIT All right reserved
 --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<sec:authorize access="isAuthenticated()">
<sec:authentication property="principal" var="authMember"/>
</sec:authorize>
<c:set var="memTel" scope="request" value="${authMember.memTel}" />
<c:set var="memZip" scope="request" value="${authMember.memZip}" />
<c:set var="memAddr1" scope="request" value="${authMember.memAddr1}" />
<c:set var="memAddr2" scope="request" value="${authMember.memAddr2}" />
<!-- 다음 우편주소 api -->
<script src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<!-- i'mport -->
<script type="text/javascript" src="https://cdn.iamport.kr/js/iamport.payment-1.1.5.js"></script>
<link rel="stylesheet" href="${cPath }/css/orderForm.css">
<script src="${cPath }/js/orderForm.js"></script>

<div id="InnerContainer">
<form id="payForm" action="${cPath }/order/orderInsert.do" method="post">
		<div class="title">주문상품정보</div>
		<br>
		<table class="t">
			<tr>
				<th class="td1">이미지</th>
				<th class="td2">제품명/판매자</th>
				<th class="td6">가격</th>
				<th class="td3">수량</th>
				<th class="td3">배송비</th>
			<tr>
			
			<input type="hidden" name="memId" value="${loginId}"/>
			<input type="hidden" name="orderTotal" value="${list[0].orderTotal}"/>
			<input type="hidden" name="totalPrice" value=${list[0].totalPrice } />
			
			<c:forEach items="${list }" var="prod">
			<input type="hidden" name="prodIds" value="${prod.prodId}"/>
			<input type="hidden" name="prodQty" value="${prod.cartQty}"/>
			<input type="hidden" name="delCartId" value="${prod.cartId}"/>
			
			<tr>
			<table class="cartBox"><tr>
				<td class="td1">
					<img class="active prodImg" src="data:image/*;base64,${prod.prod.prod_imageBase64 }" />
				</td>
				<td class="td2">
					<div class="prodName">${prod.prod.prodName}</div>
					<p>${prod.company.comName }</p>
				</td>
				<td class="td6">${prod.orderPrice } 원</td>
				<td class="td3">${prod.cartQty}</td>
				<c:if test="${prod.prodDelivery eq 0 }">
					<td class="td3">무료</td>
				</c:if>
				<c:if test="${prod.prodDelivery ne 0 }">
					<td class="td3">${prod.prodDelivery }</td>
				</c:if>
				</tr></table>
			</tr>
		</c:forEach>
		</table>
		<br><br>
		<div class="title">
		<div>배송정보<input type="button" id="btnChange" class="btn" value="배송정보 변경"></div>
		</div>
		<br>
		<table class="t2">
			<tr>
				<th>주문자</th>
				<td><input type="text" name="memName" class="memInfo" value="${memName }" readonly/></td>
			</tr>
			<tr>
				<th>전화번호</th>
				<td><input type="text" name="memTel" class="memInfo" value="${memTel }" readonly/></td>
			</tr>
			<tr>
				<th>주소</th>
				<td>
					<input type="text" name="memZip" class="memInfo read" value="${memZip }" id="sample6_postcode" placeholder="우편번호" readonly>
					<input type="button" id="btnZip" class="btn" onclick="sample6_execDaumPostcode()" value="우편번호 찾기" disabled="true"><br>
					<input type="text" name="memAddr1" class="memInfo read" value="${memAddr1 }" id="sample6_address" placeholder="주소" readonly><br>
					<input type="text" name="memAddr2" class="memInfo" value="${memAddr2 }" id="sample6_extraAddress" placeholder="상세주소" readonly>
				</td>
			</tr>
		</table>
		<div class="title">결제수단</div>
		<br>
		<table class="t4">
			<tr>
				<th colspan="1">카카오페이 결제</th>
				<td colspan="4"><input type="radio" checked/><img src="${cPath }/images/kp.jpg"></td>
			</tr>
			<tr>
				<th colspan="5">구매자 주문 동의</th>
			</tr>
			<tr>
				<td colspan="5">
					<div class="form-check">
					  <input class="form-check-input" type="checkbox" value="Y" id="defaultCheck1" />
					  <label class="form-check-label" for="defaultCheck1">
					    구매 및 결제대행 서비스 이용약관 등에 모두 동의합니다.
					  </label>
					</div>
					<ul class="payul">
						<li>구매조건 확인 및 결제 진행 동의</li>
						<li>결제 대행 서비스 이용약관 동의</li>
					</ul>
				</td>
			</tr>
		</table>
		<br>
		<table class="t4">
		<tr>
			<th colspan="2" class="resultTh">
					<div>주문금액</div>					
					<div>배송비</div>
					<br>					
					<div class="resultPriceTitle">결제금액</div>
					<br><div></div>
				</th>
				<td class="resultPrice" colspan="1">
					<div>${list[0].orderTotal} 원</div>					
					<div>${list[0].totalPrice - list[0].orderTotal} 원</div>
					<br>
					<div class="resultPriceTitle">${list[0].totalPrice } 원</div>
					<br><div></div>
				</td>
				<td colspan="2"  class="resultTd">
					<div>
<!-- 						<input id="btnKakaoPay" type="submit" class="btn btnpay" value="결제"/> -->
						<input id="btnKakaoPay" type="button" class="btn btnpay" value="결제"/>
						<input type="button" class="btn btnpay btnreset" onclick="location.href='<c:url value="javascript:history.go(-1)"/>';" value="취소"/>
					</div>
				</td>
			</tr>
		</table>
		<br>
</form>
</div>

<script type="text/javascript">
	var payForm = $("#payForm");
	var totalPrice = $("[name='totalPrice']").val();
	var memName = $("[name='memName']").val();
	var memTel = $("[name='memTel']").val();
	var memAddr1 = $("[name='memAddr1']").val();
	var memAddr2 = $("[name='memAddr2']").val();
	var memZip = $("[name='memZip']").val();
	var btnKakaoPay = $("#btnKakaoPay");
	// alert(totalPrice);
	var defaultCheck1 = $("#defaultCheck1");
	var checked = false;
	
	$(defaultCheck1).change(function() {
	    checked = $(this).prop('checked');  // checked 상태 (true, false)
	});

	/*카카오페이*/
    var IMP = window.IMP; // 생략가능
    IMP.init('imp72791211'); // 'iamport' 대신 부여받은 "가맹점 식별코드"를 사용
    var message;
    
    btnKakaoPay.on("click", function(){
    	if(!checked){
    		Swal.fire({
  			  icon: 'error',
  			  title: '구매 및 결제대행 서비스 등 동의 후 결제를 진행해 주세요.'
  			})
    		return false;
    	}
	    IMP.request_pay({
	        pg : 'kakaopay',
	        pay_method : 'card',
	        merchant_uid : 'merchant_' + new Date().getTime(),
	        name : 'WACCP 인증 제품 결제',
	        amount : totalPrice,
	        buyer_email : '',
	        buyer_name : memName,
	        buyer_tel : memTel,
	        buyer_addr : memAddr1+" "+memAddr2,
	        buyer_postcode : memZip,
	    }, function(rsp) {
	        if ( rsp.success ) {
	            //[1] 서버단에서 결제정보 조회를 위해 jQuery ajax로 imp_uid 전달하기
	            jQuery.ajax({
	                url: "/payments/complete", //cross-domain error가 발생하지 않도록 주의해주세요
	                type: 'POST',
	                dataType: 'json',
	                data: {
	                    imp_uid : rsp.imp_uid
	                    //기타 필요한 데이터가 있으면 추가 전달
	                }
	            }).done(function(data) {
	                //[2] 서버에서 REST API로 결제정보확인 및 서비스루틴이 정상적인 경우
	                if ( everythings_fine ) {
	                    message = '결제가 완료되었습니다.';
	                    message += '\n고유ID : ' + rsp.imp_uid;
	                    message += '\n상점 거래ID : ' + rsp.merchant_uid;
	                    message += '\결제 금액 : ' + rsp.paid_amount;
	                    message += '카드 승인번호 : ' + rsp.apply_num;
	                    
	                    alert(message);
	                } else {
	                    //[3] 아직 제대로 결제가 되지 않았습니다.
	                    //[4] 결제된 금액이 요청한 금액과 달라 결제를 자동취소처리하였습니다.
	                }
	            });
	            //성공시 이동할 페이지
	            $(payForm).submit();
// 	            location.href='${cPath}/order/orderResultView.do';
	        } else {
	        	message = '결제에 실패하였습니다.';
	        	message += '에러내용 : ' + rsp.error_msg;
	            //실패시 이동할 페이지
	            location.href="${cPath}/cart/cartList.do?memId=${loginId}";
	            alert(message);
	        }
	    });	
    
    });
</script>
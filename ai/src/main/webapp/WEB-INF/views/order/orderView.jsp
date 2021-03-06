<%--
* [[개정이력(Modification Information)]]
* 수정일                   수정자    	   수정내용
* --------------  ---------  -----------------
* 2019. 11. 6.     최서희     	   최초작성
* 2019. 11. 13.    최서희		  상세보기 기능 구현 시작
* Copyright (c) 2019 by DDIT All right reserved
 --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<sec:authorize access="isAuthenticated()">
<sec:authentication property="principal" var="authMember"/>
</sec:authorize>
<c:set var="memTel" scope="request" value="${authMember.memTel}" />
<c:set var="memZip" scope="request" value="${authMember.memZip}" />
<c:set var="memAddr1" scope="request" value="${authMember.memAddr1}" />
<c:set var="memAddr2" scope="request" value="${authMember.memAddr2}" />
 <link rel="stylesheet" href="${cPath }/css/orderForm.css">
<script src="${cPath }/js/orderForm.js"></script>

<style>
.orderId{
	font-size : 20px;
	text-align : left;
	color : #5D5D5D;
}
.listT{
	border-top : 1px solid #5D5D5D;
	border-bottom : 1px solid #5D5D5D;
}
.listT td{
	padding : 23px;
}
.td3{
	text-align : center;
}
.t{
	font-size : 20px;
}
.btnAi2{
	margin : 50px;
	margin-left : 253px;
	background: #fff;
	color: #2F4F4F;
	border: 1px solid #2F4F4F;
}
.td2{
	text-align : left;
}
.nameURL{
/* 	color : #0000CD; */
	cursor : pointer;
}
.nameURL:hover{
	color : blue;
	text-decoration: underline;
	cursor : pointer;
}
sub{
	font-size : 18px;
}
</style>

<div id="InnerContaier">
	<table class="t"><tr><td>
		<div class="orderId"><img src="${cPath }/images/pin.png"> &nbsp;&nbsp; <sub>주문번호 : ${orderVO.orderId } &nbsp;&nbsp;&nbsp;&nbsp;주문날짜 : ${orderVO.orderDate }</sub></div>
	</td></tr></table>
<div class="title">구매목록</div><br>
	<table class="t listT">
			<tr>
				<th class="td3 listT">제품명</th>
				<th class="td3 listT">수량</th>
				<th class="td3 listT">가격</th>
				<th class="td3 listT">배송비</th>
				<th class="td5 listT">판매자</th>
			</tr>
	<c:forEach items="${orderVO.orderDtList}" var="dt">
			<tr>
				<td class="td2 listT"><a class="nameURL" onclick="location.href='<c:url value='/prod/prodView.do?what=${dt.prodId }'/>';">${dt.prodVO.prodName}</a></td>
				<td class="td3 listT">${dt.orderQty}</td>
				<td class="td3 listT">${dt.orderQty * dt.prodVO.prodPrice}</td>
				<c:if test="${dt.prodVO.prodDelivery eq 0 }">
					<td class="td3 listT">무료</td>
				</c:if>
				<c:if test="${dt.prodVO.prodDelivery ne 0 }">
					<td class="td3 listT">${dt.prodVO.prodDelivery}</td>
				</c:if>
				<td class="td5 listT">${dt.prodVO.company.comName}</td>
			</tr>
	</c:forEach>
	</table>
	<div class="title">
		<div>배송정보</div>
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
					<input type="text" name="memZip" class="memInfo read" value="${memZip }" id="sample6_postcode" placeholder="우편번호" readonly><br>
					<input type="text" name="memAddr1" class="memInfo read" value="${memAddr1 }" id="sample6_address" placeholder="주소" readonly><br>
					<input type="text" name="memAddr2" class="memInfo" value="${memAddr2 }" id="sample6_extraAddress" placeholder="상세주소" readonly>
				</td>
			</tr>
		</table>
		<div class="title">결제정보</div>
		<br>
		<table class="t t4">
		<tr>
			<th colspan="2" class="resultTh">
					<div>주문금액</div>					
					<div>배송비</div>
					<br>					
					<div class="resultPriceTitle">결제금액</div>
					<br><div></div>
				</th>
				<td class="resultPrice" colspan="1">
					<div>${orderVO.orderTotal} 원</div>					
					<div>${orderVO.totalPrice - orderVO.orderTotal} 원</div>
					<br>
					<div class="resultPriceTitle">${orderVO.totalPrice } 원</div>
					<br><div></div>
				</td>
				<td colspan="2"  class="resultTd">
					<div>
					</div>
				</td>
			</tr>
		</table>

<input id="btn2" type="button" class="btn btnAi2" " value="뒤로가기" 
		 onclick="location.href='<c:url value="/order/orderList.do"/>';"/>

</div>
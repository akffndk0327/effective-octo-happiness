<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<%--
* [[개정이력(Modification Information)]]
* 수정일                     수정자         수정내용
* --------------  ---------  -----------------
* 2019. 11. 6.      최서희         최초작성
* 2019. 11. 13.     최서희	   장바구니 삭제
* 2019. 11. 19.     최서희	   장바구니 수량 변경
* Copyright (c) 2019 by DDIT All right reserved
 --%>
<link rel="stylesheet" href="${cPath }/css/cartList.css">

<div id="InnerContainer">
<table class="t">
	<tr>
		<td colspan="6" class="oneTd"><div class="title">&nbsp; ${memName }( ${loginId } )님 장바구니</div></td>
	</tr>
	<tr>
		<th class="td1">이미지</th>
		<th class="td2">제품명/판매자</th>
		<th class="td3">가격</th>
		<th class="td3">수량</th>
		<th class="td3">배송비</th>
		<th class="td4"></th>
	</tr>
</table>
<div id="mainTable">
	<div id="cartMainTable">
	<table  class="t">
		<c:if test="${empty cartVO }">
		<tr><td>
			<div>장바구니가 비어있습니다.</div>
			</td></tr>	
		</c:if>
		<c:if test="${not empty cartVO }">
		<c:forEach items="${cartVO }" var="cart">
		<tr>
		<table class="cartBox"><tr>
			<td class="td1">
				<img class="active prodImg" src="data:image/*;base64,${cart.prod.prod_imageBase64 }" />
			</td>
			<td class="td2">
				<div class="prodName">${cart.prod.prodName }</div>
				<p>${cart.prod.company.comName }</p>
			</td>
			<td class="td3">${cart.orderPrice } 원</td>
			<td class="td3">
				<input type="hidden" value="${cart.cartId }" />
				<input class="cartQty" name="cartQty" type="number" value="${cart.cartQty}"/>
			</td>
			<c:if test="${cart.prodDelivery eq 0 }">
				<td class="td3">무료</td>
			</c:if>
			<c:if test="${cart.prodDelivery ne 0 }">
				<td class="td3">${cart.prodDelivery }</td>
			</c:if>
			<input type="hidden" value="${cart.payPrice }" />
			<td class="td4 trashTd">
				<input type="hidden" name="cartId" class="cartId" value="${cart.cartId}" />
				<img src="${cPath }/images/trashcan.jpg" class="btnDelCart" >
			</td>
		</tr></table>
		</tr>
		</c:forEach>
	</c:if>
	</table>
	</div>
</div>
<table class="t3">
	<tr>
		<td>
			<p>
			
			
			 <img src="${cPath }/images/alert.png"> AI(Allergy Infromation)는 결제정보의 중개서비스 또는 통신판매중개시스템의 제공자로서, 통신판매의 당사자가 아니며 상품의 주문, 배송 및 환불 등과 관련한 의무와 책임은 각 판매자에게 있습니다.
			
			</p>
		</td>
	</tr>
</table>
<table class="t4">
	<tr>
		<th colspan="2" class="resultTh">
			<div>주문금액</div>					
			<div>배송비</div>
			<br>					
			<div class="resultPriceTitle">결제금액</div>
			<div></div>
		</th>
		<td class="resultPrice" colspan="1">
			<div>${cartVO[0].orderTotal} 원</div>					
			<div>${cartVO[0].totalPrice - cartVO[0].orderTotal} 원</div>
			<br>
			<div class="resultPriceTitle">${cartVO[0].totalPrice } 원</div>
			<div></div>
		</td>
		<td colspan="2"  class="resultTd">
			<div>
				<c:url value="/order/orderInsert.do" var="orderURL">
					<c:param name="memId" value="${loginId }"></c:param>
				</c:url>
				<input type="button" class="btn btnpay" onclick="location.href='${orderURL}';" value="전체구매" />
			</div>
		</td>
	</tr>
</table>


</div>

<script type="text/javascript">
var cartQty = $("[name='cartQty']");
var btnDelCart = $(".btnDelCart");
var cartMainTable = $("#cartMainTable");
var mainTable = $("#mainTable");

const swalWithBootstrapButtons = Swal.mixin({
	  customClass: {
	    confirmButton: 'btn btn-success',
	    cancelButton: 'btn btn-danger'
	  },
	  buttonsStyling: false
	})

//장바구니 리스트 UI
var makeCartListUI = function(resp){
	let trTags = "<table  class='t'>";
	let payTags = "";
	if(resp.length > 0 ){
		$(resp).each(function(i, c){
		 	trTags+= "<tr><table class='cartBox'><tr><td class='td1'>";
			trTags+="<img class='active prodImg' src='data:image/*;base64,"+c.prod.prod_imageBase64+"' />";
			trTags+="</td><td class='td2'><div class='prodName'>"+c.prod.prodName+"</div>";
			trTags+="<p>"+c.prod.company.comName+"</p></td>";
			trTags+="<td class='td3'>"+c.orderPrice+" 원</td>";
			trTags+="<td class='td3'>";
			trTags+="<input type='hidden' value='"+c.cartId+"' />";
			trTags+="<input name='cartQty' class='cartQty' type='number' value='"+c.cartQty+"'/></td>";
			if(c.prodDelivery == 0){
			trTags+="<td class='td3'>무료</td>";
			}else{
			trTags+="<td class='td3'>"+c.prodDelivery+"</td>";
			}
			trTags+="<input type='hidden' value='"+c.payPrice+"' />";
			trTags+="<td class='td4 trashTd'>";
			trTags+="<input type='hidden' name='cartId' class='cartId' value='"+c.cartId+"' />";
			trTags+="<img src='/ai/images/trashcan.jpg' class='btnDelCart'>";
			trTags+="</td></tr></table></tr>";
		});
		payTags += "<div>"+resp[0].orderTotal+" 원</div>";
		payTags += "<div>"+(resp[0].totalPrice - resp[0].orderTotal)+" 원</div><br>";
		payTags += "<div class='resultPriceTitle'>"+resp[0].totalPrice+" 원</div><div></div>";
	}else{
		trTags += "<tr><td>";
		trTags += "<div>장바구니가 비어있습니다.</div>";
		trTags += "</td></tr>";		
		
		payTags += "<div> 원</div>";
		payTags += "<div> 원</div><br>";
		payTags += "<div class='resultPriceTitle'> 원</div><div></div>";
	}
	trTags+="</table>";
	
	$("#cartMainTable").html(trTags);
	$(".resultPrice").html(payTags);
}


//장바구니 수량 변경
$(mainTable).on("change", ".cartQty", function(){
	let cartId = $(this).prev().val();
	let cartQty = $(this).val();
	$.ajax({
		url : "${cPath}/cart/cartUpdate.do",
		method : "post",
		data : {
			"cartId" : cartId,
			"cartQty" : cartQty
		},
		dataType : "json",
		success : function(resp) {
			makeCartListUI(resp);
		},
		error : function(xhr) {
			console.log(xhr.status);
		}
	});
});


//수량을 음수로 적었을때 검증
$(mainTable).on("change", ".cartQty",function(){
  var num = $(this).val()-1;
  if(typeof num !== "number" || num < 0) {
  	$(this).val('1');
  	Swal.fire({
		  icon: 'error',
		  title: '1개 이상 선택하세요'
	})
    $(this).focus();
  }
});

//장바구니 삭제 버튼 클릭
$(mainTable).on("click", "img",function() {
	let cartVal = $(this).siblings('.cartId').val();
		Swal.fire({
             title: '상품 제외',
	     text: "해당 상품을 장바구니에서 삭제하시겠습니까?",
	     icon: 'warning',
             showCancelButton: true,
             confirmButtonColor: '#90c322',
             confirmButtonText: '네',
             cancelButtonText: '아니오',
			  reverseButtons: true
			}).then((result) => {
			  if (result.value) {
				
				  $.ajax({
					url : "${cPath}/cart/cartDelete.do",
					method : "post",
					data : {"cartId":cartVal},
					dataType : "json",
					success : function(resp) {
						swalWithBootstrapButtons.fire(
							      'Deleted!',
							      '삭제 성공',
							      'success'
							    )
						makeCartListUI(resp);
					},
					error : function(xhr) {
						Swal.fire({
							  icon: 'error',
							  title: '삭제실패'
						})
						console.log(xhr.status);
					}
				}); 
                    	
			  } else if (
			    /* Read more about handling dismissals below */
			    result.dismiss === Swal.DismissReason.cancel
			  ) {
			    swalWithBootstrapButtons.fire(
			      '취소',
			      '취소되었습니다.',
			      'error'
			    )
			  }
			})
    })


</script>
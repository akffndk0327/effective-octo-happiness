<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%--
* [[개정이력(Modification Information)]]
* 수정일                     수정자           수정내용
* --------------  ---------  -----------------
* 2019. 11. 5.      최서희           최초작성
* 2019. 11. 7.      최서희           장바구니 담기 기능구현
* 2019. 11. 7.      최서희           수량 input 추가
* 2019. 11. 18.     최서희	     새로운 템플릿 적용
* Copyright (c) 2019 by DDIT All right reserved
 --%>
<!-- <script src="http://code.jquery.com/jquery-latest.js"></script> -->
<!-- <script	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script> -->
<!-- <script type="text/javascript"	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script> -->
<!-- <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"> -->
<link rel="stylesheet" href="${cPath }/css/prodView.css">
<link rel="stylesheet" href="${cPath }/css/review.css">

<div id="InnerContainer">
<%-- <c:url value="/prod/prodUpdate.do" var="prodUpdateURL"> --%>
<%-- 	<c:param name="prodId" value="${prod.prodId }"></c:param> --%>
<%-- </c:url> --%>
<%-- <c:if test="${authorId eq 'ROLE_ADMIN' }"> --%>
<!-- <hr color="#ddd"> -->
<!-- <input type="button" class="btn btnUpdate"  -->
<%-- 	onclick="location.href='${prodUpdateURL}';" value="관리자 수정"/> --%>
<!-- <hr color="#ddd"> -->
<%-- </c:if> --%>
	<section id="single-product">
		<div class="container">
			<div class="row">
				<div class="col-md-5">
					<div class="single-product-img">
						<img class="active prodImg" src="data:image/*;base64,${prod.prod_imageBase64 }" />
					</div>

				</div> <!-- End of /.col-md-5 -->
				<div class="col-md-4">
					<div class="block">
						<div class="product-des">
							<p class="comName">${prod.comName }</p>
							<h4>${prod.prodName }</h4>
							<p class="price">${prod.prodPrice }원</p>
							<hr color="#ddd">
							<div class="dileveryInfo">
								<div>배송정보 : 택배배송</div>
								<c:if test="${prod.prodDelivery eq 0 }">
									<div>배송비   : 무료</div>
								</c:if>
								<c:if test="${prod.prodDelivery ne 0 }">
									<div>배송비   : ${prod.prodDelivery } 원</div>
								</c:if>
							</div>
							<hr color="#ddd">
							<div class="qty">수량&nbsp&nbsp&nbsp:&nbsp&nbsp&nbsp<input id="cartQty" name="cartQty" type="number" value="1"/></div>
							<input type="hidden" id="authId" value="${authorId }"/>
							<a id="btnCart" class="view-link" href="#"><i class="fa fa-plus-circle"></i>Add To Cart</a>
						</div>	<!-- End of /.product-des -->
					</div> <!-- End of /.block -->
				</div>	<!-- End of /.col-md-4 -->
			</div>	<!-- End of /.row -->
			<div class="row">
				<div class="col-md-9">
					<!-- Nav tabs -->
					<ul class="nav nav-tabs">
						<li class="active"><a href="#home" data-toggle="tab">상세보기</a></li>
						<li><a href="#profile" data-toggle="tab">상품후기</a></li>
					</ul>

					<!-- Tab panes -->
					<div class="tab-content">
						<div class="tab-pane active" id="home">
							<table class="subTable">
								<tr>
									<td class="leftThink"><img src="${cPath }/images/leftThink.png" ></td>
									<td class="pcont"><p class="cont">${prod.prodCont }</p></td>
									<td class="rightThink"><img src="${cPath }/images/rightThink.png" ></td>
								</tr>
								<tr><td colspan="3" class="balnkTr"></td></tr>
							</table>
							<table  class="subTable">
							<tr>
									<th class="idTd" style="">성분ID</th>
									<th class="nameTd">성분이름표시</th>
									<th></th>
								</tr>
								<c:choose>
									<c:when test="${not empty rawResult }">
										<c:forEach items="${rawResult }" var="raw">
										<tr>
											<td class="idTd">${raw.rawId }</td>
											<td class="nameTd">${raw.raw.rawName }</td>
											<td></td>
										</tr>
										</c:forEach>
									</c:when>
									<c:when test="${not empty cheResult }">
										<c:forEach items="${cheResult }" var="che">
											<tr>
												<td class="idTd">${che.casId }</td>
												<td class="nameTd">${che.chemical.cheNameKo } ( ${che.chemical.cheNameEn} )</td>
												<td></td>
											</tr>
										</c:forEach>
									</c:when>
									<c:otherwise>
										<tr>
										<td class="balnkTr2" colspan="3">등록된 성분이 없습니다.</td>
										</tr>
									</c:otherwise>
								</c:choose>
							
							</table>
						</div>
						<div class="tab-pane" id="profile">
							<%-- 여기서부터 리뷰 ----------------------------------------------%>
							<form action="${cPath }/review/reviewList.do" id="searchForm" method="post">
								<input type="hidden" name="page"/>
								<input type="hidden" id="prodId" name="what" value="${prod.prodId}"/>
								<input type="hidden" id="memId" name="memId" value="${loginId}"/>
							</form>
							<table class="reviewTable">
								<%-- 구매회원만 상품평 작성 가능 --%>
								<c:if test="${not empty order }">
								<tr>
									<td class="reviewTd">
										<!-- Review Form-->
										<form class="form" name="form" action="${cPath }/review/reviewInsert.do" method="post"
											ng-submit="form.$valid && cmntCtrl.addComment()" novalidate>
											<div class="form-row">
												<input type="hidden" name="prodId" value="${prod.prodId}"/>
												<input type="hidden" name="memId" value="${loginId}"/>	
												<div class="radio">
													<label class="radio-inline"> <input type="radio" name="reviewEval" value="r1" checked /> <img src="${cPath }/images/good.png"> 좋아요</label>   
													<label class="radio-inline"> <input type="radio" name="reviewEval" value="r2" /> <img src="${cPath }/images/soso.png"> 보통</label>             
													<label class="radio-inline"> <input type="radio" name="reviewEval" value="r3" /> <img src="${cPath }/images/bad.png"> 별로예요</label>         
												</div>
												<textarea name="reviewContent" class="input" ng-model="cmntCtrl.comment.text"
													placeholder="제품 구매시 상품평을 작성할 수 있습니다." required></textarea>
												<input id="btnReviewInsert" class="btn" type="submit" value="등록">
											</div>
										</form>
									</td>
								</tr>
								</c:if>
								<tr><td><hr color="#ddd"></td></tr>
								<tr>
									<td class="reviewTd1"  rowspan="2">
										<c:set var="reviewList" value="${pagingVO.dataList}" /> 
										<!-- Reivew List --> 
										<table class="listTable">
											<tr>
												<th class="ssxTh">No</th>
												<th class="sxTh">평가</th>
												<th class="longTd">내용</th>
												<th class="mTh">구매자</th>
												<th class="sTh">날짜</th>
												<th class="sxTh"></th>
											</tr>
										<c:if test="${reviewList == null or empty reviewList }">
											<tr><td colspan="6" class="blank">
												<div>등록된 상품평이 없습니다.</div>
											</td></tr>
										</c:if>
										<tbody id="reviewBody">
										<c:if test="${reviewList != null }">
											<c:forEach items="${reviewList }" var="review">
											<tr>
												<td class="ssxTh">${review.rnum }</td>
												<c:if test="${review.reviewEval eq 'r1'}">
													<td class="sxTh"><img src="${cPath }/images/good.png"></td>											
												</c:if>							
												<c:if test="${review.reviewEval eq 'r2'}">
													<td class="sxTh"><img src="${cPath }/images/soso.png"></td>											
												</c:if>							
												<c:if test="${review.reviewEval eq 'r3'}">
													<td class="sxTh"><img src="${cPath }/images/bad.png"></td>											
												</c:if>							
												<td class="longTd">${review.reviewContent }</td>
												<td class="mTh">${review.memId }</td>
												<td>${review.reviewIndate }</td>
												<td>
												<c:if test="${loginId eq review.memId }">
													<input type="hidden" value="${review.reviewNo }" id="reviewNo"/>
													<img class="btnDelReview" src="${cPath }/images/cancel.png" style="cursor:pointer;">
												</c:if>
												</td>
											</tr>
											</c:forEach> 
										</c:if>
										</table>
										</tbody>
										<tfoot>
										<tr><td>
										<div id="pagingArea">
											${pagingVO.pagingHTML }
										</div>
										</td></tr>
										</tfoot>
									</td>
								</tr>
							</table>
						</div> <!-- 리뷰 끝 -->
						</div>
						</div>
					</div>
				</div>	<!-- End of /.col-md-9 -->
			</div>	<!-- End of /.row -->
	</section> <!-- End of /.Single-product -->

<c:url value="/prod/prodList.do" var="listURL" />
<input type="button" class="btn btnAi2" value="목록" onclick="location.href='${listURL}'" />


<script type="text/javascript">
	var pagingArea = $("#pagingArea");
	var pageTag = $("[name='page']");
	var searchForm = $("#searchForm");
	var reviewTd1 = $(".reviewTd1");
	var btnReviewInsert =$("#btnReviewInsert");
	var prodId = $("#prodId").val();
	var articleTd2 = $(".articleTd2");
	var cartQty = $("#cartQty");
	var reviewBody = $("#reviewBody");
	var authId = $("#authId").val();
	
	const swalWithBootstrapButtons = Swal.mixin({
		  customClass: {
		    confirmButton: 'btn btn-success',
		    cancelButton: 'btn btn-danger'
		  },
		  buttonsStyling: false
		})
	
	pageTag.val("1");
	
	//상품평UI 만드는 함수
	var makeUI = function(resp){
		let reviewList = resp.dataList;
		let loginId = $('#memId').val();
		let trTags = " ";
		if(reviewList.length > 0 ){
			$(reviewList).each(function(i, review){
				trTags += "<tr><td class='ssxTh'>"+review.rnum+"</td>";
				if(review.reviewEval == 'r1'){
					trTags += "<td class='sxTh'><img src='${cPath }/images/good.png'></td>";
				}else if(review.reviewEval == 'r2'){
					trTags += "<td class='sxTh'><img src='${cPath }/images/soso.png'></td>";
				}else if(review.reviewEval == 'r3'){
					trTags += "<td class='sxTh'><img src='${cPath }/images/bad.png'></td>";
				}
				trTags += "<td calss='longTd'>"+review.reviewContent+"</td>";
				trTags += "<td class='mTh'>"+review.memId+"</td>";
				trTags += "<td>"+review.reviewIndate+"</td>";
				trTags += "<td>";
				if(loginId == review.memId){
					trTags += "<input type='hidden' value='"+review.reviewNo+"' id='reviewNo'/>";
					trTags += "<img class='btnDelReview' src='${cPath }/images/cancel.png' style='cursor:pointer;'>";
				}
				trTags += "</td></tr>";	
			});
		}else{
			trTags += "<tr><td>";
			trTags += "<div>등록된 상품평이 없습니다.</div>";
			trTags += "</td></tr>";																							
		}
		trTags += "</table>";
		$(reviewBody).html(trTags);
		pagingArea.html(resp.pagingHTML );
		pageTag.val("1");
	}
	
	//상품평 페이지 이동 함수
	searchForm.on("submit", function(event){
			event.preventDefault();
			var action = $(this).attr("action");
			var method = $(this).attr("method");
			var queryString = $(this).serialize();
			$.ajax({
				url : action,
				method :method,
				data : queryString,
				dataType : "json", // REST 방식
				success : function(resp) {
					makeUI(resp);
				},
				error : function(errorResp) {
					console.log(errorResp.status);
				}
	
			});
			return false;
		});
		
	
	$(pagingArea).on("click", "a", function(){
		event.preventDefault();
		let page = $(this).data("page");
		if (page < 1) return false;
		pageTag.val(page);
		searchForm.submit();
		return false;
	});
	
	
	//상품평 삭제 버튼 클릭
	$(reviewBody).on("click", ".btnDelReview",function() {
		let reviewNo = $(this).prev().val();
		Swal.fire({
			title: '상품 후기 삭제',
			  text: "해당 상품 후기를 삭제하시겠습니까?",
			  icon: 'warning',
             showCancelButton: true,
             confirmButtonColor: '#90c322',
             confirmButtonText: '확인',
             cancelButtonText: '취소',
			  reverseButtons: true
			}).then((result) => {
			  if (result.value) {
				//삭제할건지 묻고 확인을 누르면 여기
				  $.ajax({
						url : "${cPath}/review/reviewDelete.do",
						method : "post",
						data : {"reviewNo" : reviewNo,
								"prodId" : prodId	
						},
						success : function(resp) {
							swalWithBootstrapButtons.fire(
								      'Deleted!',
								      '삭제 성공',
								      'success'
								    )
							makeUI(resp);
						},
						error : function(xhr) {
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
	
	//수량을 음수로 적었을때 검증
	$(cartQty).change(function() {
	    var num = $(this).val() - 0;
	    if(typeof num !== "number" || num < 0) {
	    	$(this).val('');
	    	Swal.fire({
				  icon: 'error',
				  title: '1개 이상 선택하세요'
			})
	        $(this).focus();
	    }
	});
	
	$(function() {
		let btnCart = $("#btnCart");
		let prodIdVal = $("#prodId").val();
		
		//장바구니 담기 버튼 클릭
		btnCart.on("click", function(){
			let cartQtyVal = $("#cartQty").val();
			if(authId == 'ROLE_MEM'){
				$.ajax({
					url : "${cPath}/cart/cartInsert.do",
					method : "post",
					data : {"prodId" : prodIdVal,
							"cartQty" : cartQtyVal
					},
					success : function(resp) {
						Swal.fire({
							  icon: 'success',
							  title: '장바구니 담기 성공',
							  showConfirmButton: false,
							  timer: 1500
							})
					},
					error : function(xhr) {
						console.log(xhr.status);
					}
				});		
			}else{
				//만약 로그인이 되어있지 않으면 메시지 출력
				Swal.fire({
				  icon: 'error',
				  title: '일반회원만 가능합니다.'
				})
			}			
		});	
	});
</script>

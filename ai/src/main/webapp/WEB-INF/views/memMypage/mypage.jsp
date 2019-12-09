<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%--
* [[개정이력(Modification Information)]]
* 수정일                 		수정자      		수정내용
* --------------  ---------  -----------------
* 2019. 11. 6.      허민지      		최초작성
* 2019. 11. 7.  	허민지      마이페이지 메뉴들 Memleft로 이사
* 2019. 11. 11	      박주연	마이페이지 left 생성함. 
* 2019. 11. 15		허민지     회원 알러지 정보 출력
* Copyright (c) 2019 by DDIT All right reserved
 --%>
<style>
 #mothlyTable{
	width:580px;
    float: left;
 }
 #recipeTable{ 
 	width: 580px; 
 	float: right; 
 		 
  } 

 #orderTable{
/*  	margin-left: -18px; */
 	float: left; 
 	margin-top: 55px;
 }

 
 #replyTable{
/*  	margin-left: -18px; */
 	float: left; 
 }

</style>

<c:if test="${not empty msg }">
	<script>
	$(document).ready(function() {
		Swal.fire({
		  	icon: 'error',
		  	title: 'Oops...',
		  	text: '비밀번호 오류'
			})
	});
	</script>
</c:if>

<c:if test="${not empty fail }">
	<script>
	$(document).ready(function() {
		Swal.fire({
		  	icon: 'error',
		  	title: 'Oops...',
		  	text: '수정 실패'
			})
	});
	</script>
</c:if>
<div id="InnerContainer">
	<div class="container">
		<div class="row">
			<h1>
			<img src="${cPath }/images/user.png" style="width:60px"/>
			${savedMember.memName }(${savedMember.memId })님의 정보</h1>
			<div class="table-responsive-vertical shadow-z-1">
			<!-- Table starts here -->
				<table id="mothlyTable" class="table table-hover table-mc-light-blue">
					<thead>
						<tr>
							<th class="titleLeftBar">최근 작성한 식단 공유 글</th>
							<th style="text-align:right">
								<a href="${cPath }/memMypage/monthlyView.do"><li class="fa fa-plus-square"></li>더보기</a></th>
						</tr>
					</thead>
					<tbody>
						<c:choose>
							<c:when test="${not empty monthlyRecentList}">
								<tr>
									<td>글 제목</td>	
									<td>날짜</td>	
								</tr>
								<c:forEach var="savedMonthly" items="${monthlyRecentList}" >
									<tr>
										<td>
											${savedMonthly.monthlyTitle}
										</td>
										<td>
											${savedMonthly.monthlyIndate}
										</td>
									</tr>
								</c:forEach>
							</c:when>
							<c:otherwise>
								<td colspan="3">작성 한 글이 존재하지 않습니다.</td>
							</c:otherwise>
						</c:choose>
					</tbody>
<!-- 					<tfoot> -->
<!-- 						<tr> -->
<!-- 							<td>table foot</td> -->
<!-- 						</tr> -->
<!-- 					</tfoot> -->
				</table>
				<!-- Table starts here -->
				<div class="recipeTable">
				<table id="recipeTable" class="table table-hover table-mc-light-blue recipeTable">
					<thead>
						<tr>
							<th class="titleLeftBar">최근 작성 한 레시피</th>
							<th style="text-align:right">
								<a href="${cPath }/memMypage/recipeList.do"><li class="fa fa-plus-square"></li>더보기</a></th>
						</tr>
					</thead>
					<tbody>
						<c:choose>
							<c:when test="${not empty recipeRecentList}">
								<tr>
									<td>글 제목</td>	
									<td>날짜</td>	
								</tr>
								<c:forEach var="savedRecipe" items="${recipeRecentList}" >
									<tr>
										<td>
											${savedRecipe.recipeTitle}
										</td>
										<td>
											${savedRecipe.recipeIndate}
										</td>
									</tr>
								</c:forEach>
							</c:when>
							<c:otherwise>
								<td colspan="3">작성 한 글이 존재하지 않습니다.</td>
							</c:otherwise>
						</c:choose>
					</tbody>
<!-- 					<tfoot> -->
<!-- 						<tr> -->
<!-- 							<td>table foot</td> -->
<!-- 						</tr> -->
<!-- 					</tfoot> -->
				</table>
				</div>
				
				<!-- Table starts here 3-->
				<table id="orderTable" class="table table-hover table-mc-light-blue orderTable">
					<thead>
						<tr>
							<th style="width:200px;" class="titleLeftBar">최근 주문 내역</th>
							<th></th>
							<th></th>
							<th style="text-align:right">
								<a href="${cPath }/order/orderList.do"><li class="fa fa-plus-square"></li>더보기</a></th>
						</tr>
						<tr></tr>
					</thead>
					<tbody>
						<c:choose>
							<c:when test="${not empty orderRecentList}">
								<tr>
									<td>주문번호</td>	
									<td>상품명</td>
									<td>주문일자</td>	
									<td>주문상태</td>	
								</tr>
								<c:forEach var="savedOrder" items="${orderRecentList}" >
									<tr>
										<td>
											${savedOrder.orderId}
										</td>
										<c:if test="${savedOrder.plus eq 0}">
											<td>${savedOrder.prod.prodName }</td>
										</c:if>
										<c:if test="${savedOrder.plus gt 0}">
											<td>${savedOrder.prod.prodName } 외 ${savedOrder.plus} 건</td>
										</c:if>
										<td>
											${savedOrder.orderDate}
										</td>
										<td>
											${savedOrder.orderStatusName}
										</td>
									</tr>
								</c:forEach>
							</c:when>
							<c:otherwise>
								<td colspan="4">주문 내역이 존재하지 않습니다.</td>
							</c:otherwise>
						</c:choose>
					</tbody>
<!-- 					<tfoot> -->
<!-- 						<tr> -->
<!-- 							<td>table foot</td> -->
<!-- 						</tr> -->
<!-- 					</tfoot> -->
				</table>

			</div>
		</div>
	</div>


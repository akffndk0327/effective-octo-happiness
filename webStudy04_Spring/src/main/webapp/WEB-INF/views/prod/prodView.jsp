<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<link rel="stylesheet"   href="${pageContext.request.contextPath }/bootstrap-4.3.1-dist/css/bootstrap.min.css">
<!-- <script type="text/javascript"   src="https://code.jquery.com/jquery-3.3.1.min.js"></script> -->
<script type="text/javascript"   src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
<script type="text/javascript"   src="${pageContext.request.contextPath }/bootstrap-4.3.1-dist/js/bootstrap.min.js"></script>
</head>
<body>
	<table border="1">
		<thead>
			<h4>
				<p>${prod.prod_name }</p>
			</h4>
		</thead>
		<tbody>
			<tr>
				<th>상품코드</th>
				<td>${prod.prod_id }</td>
			</tr>
			<tr>
				<th>상품명</th>
				<td>${prod.prod_name }</td>
			</tr>
			<tr>
				<th>대분류</th>
				<td>${prod.prod_lgu }</td>
			</tr>
			<tr>
				<th>분류명</th>
				<td>${prod.lprod_nm }</td>
			</tr>
			<tr>
				<th>거래코드</th>
				<td>${prod.prod_buyer }</td>
			</tr>
			<tr>
         <th>거래처</th>
         <td>
            <table>
               <thead>
                  <tr>
                     <th>거래처코드</th>
                     <th>거래처명</th>
                     <th>담당자</th>
                     <th>연락처</th>
                     <th>소재지</th>
                  </tr>
               </thead>
               <tbody>
                  <tr>
                     <td>${prod.buyer.buyer_id }</td>
                     <td>${prod.buyer.buyer_name }</td>
                     <td>${prod.buyer.buyer_charger }</td>
                     <td>${prod.buyer.buyer_comtel }</td>
                     <td>${prod.buyer.buyer_add1 }</td>
                  </tr>
               </tbody>
            </table>
         </td>
      </tr>
			<tr>
				<th>구매가</th>
				<td>${prod.prod_cost }</td>
			</tr>
			<tr>
				<th>판매가</th>
				<td>${prod.prod_price }</td>
			</tr>
			<tr>
				<th>세일가</th>
				<td>${prod.prod_sale }</td>
			</tr>
			<tr>
				<th>outline</th>
				<td>${prod.prod_outline }</td>
			</tr>
			<tr>
				<th>상세정보</th>
				<td>${prod.prod_detail }</td>
			</tr>
			<tr>
				<th>이미지</th>
				<td><img src="${pageContext.request.contextPath }/prodImages/${prod.prod_img }"/></td>
			</tr>
			<tr>
				<th>상품재고</th>
				<td>${prod.prod_totalstock }</td>
			</tr>
			<tr>
				<th>입고날짜</th>
				<td>${prod.prod_insdate }</td>
			</tr>
			<tr>
				<th>재고</th>
				<td>${prod.prod_properstock }</td>
			</tr>
			<tr>
				<th>제품크기</th>
				<td>${prod.prod_size }</td>
			</tr>
			<tr>
				<th>제품색상</th>
				<td>${prod.prod_color }</td>
			</tr>
			<tr>
				<th>배달주소</th>
				<td>${prod.prod_delivery }</td>
			</tr>
			<tr>
				<th>?</th>
				<td>${prod.prod_unit }</td>
			</tr>
			<tr>
				<th>입고수량</th>
				<td>${prod.prod_qtyin }</td>
			</tr>
			<tr>
				<th>할인수량</th>
				<td>${prod.prod_qtysale }</td>
			</tr>
			<tr>
				<th>마일리지</th>
				<td>${prod.prod_mileage }</td>
			</tr>
			<tr>
				<td colspan="2"><c:url value="/prod/prodUpdate.do"
						var="updateURL">
						<c:param name="what" value="${prod.prod_id }">

						</c:param>
					</c:url>
					<button type="button" onclick="location.href='${updateURL}';">상품수정</button>
				</td>
			</tr>
		</tbody>
	</table>
	<h4>구매기록 </h4>
	<table>
		<thead>
			<tr>
				<th>회원아이디</th>
				<th>회원명</th>
				<th>휴대폰</th>
				<th>이메일</th>
				<th>소재지</th>
				
				
			</tr>
		</thead>
		<tbody>
			<c:choose>
				<c:when test="${not empty prod.memberList }">
					<c:forEach items="${prod.memberList }" var="member">
						<tr>
							<td>${member.mem_id}</td>
							<td>${member.mem_name}</td>
							<td>${member.mem_hp}</td>
							<td>${member.mem_mail}</td>
							<td>${member.mem_add1}</td>
						</tr>
					</c:forEach>
				</c:when>
				<c:otherwise>
					<tr>
						<td colspan="5">
							구매자가 없음 안팔려요...
						</td>
					</tr>
				</c:otherwise>
			</c:choose>
		
		</tbody>
	</table>


</body>
</html>
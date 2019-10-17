<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!-- <!DOCTYPE html> -->
<!-- <html> -->
<!-- <head> -->
<!-- <meta charset="UTF-8" /> -->
<!-- <title>Insert title here</title> -->
<%-- <link rel="stylesheet"	href="${pageContext.request.contextPath }/bootstrap-4.3.1-dist/css/bootstrap.min.css"> --%>
	
<!-- <script type="text/javascript"	src="https://code.jquery.com/jquery-3.3.1.min.js"></script> -->
<!-- <script type="text/javascript"	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script> -->
<%-- <script type="text/javascript"	src="${pageContext.request.contextPath }/bootstrap-4.3.1-dist/js/bootstrap.min.js"></script> --%>
<script type="text/javascript" 
	src ="${pageContext.request.contextPath }/js/generataLprodBuye.js"></script>
<!-- </head> -->
<!-- <style type="text/css"> -->
<!-- /* 	.error{ */ -->
<!-- /* 		color: red; */ -->
<!-- /* 	} */ -->
<!-- </style> -->
<!-- <body> -->
  <input style="width: 50px; height: 50px" type="image" src="<c:url value="/images/ko.png" />"
         onclick="location.href='?lang=ko';"
   />
   <input style="width: 50px; height: 50px" type="image" src="<c:url value="/images/usa.jpg" />"
         onclick="location.href='?lang=en';"
   />

<form id="searchForm">
	<input type="hidden" name="page"/>
	<input type="hidden" name="prod_lgu" value="${pagingVO.searchVO.prod_lgu }"/>
	<input type="hidden" name="prod_buyer" value="${pagingVO.searchVO.prod_buyer }"/>
	<input type="hidden" name="prod_name" value="${pagingVO.searchVO.prod_name }"/>
</form>

<div id = "searchUI" >
	<select id="prod_lgu">
		<option value>분류선택</option>
	</select>
	<select id="prod_buyer">
		<option value>거래처선택</option>
	</select>
	<input type="text" name="prod_name" id="prod_name" value="${pagingVO.searchVO.prod_name }">
	<input type="button" value="검색" id="searchBtn" />
</div>
<c:url value="/prod/prodInsert.do" var="insertURL"/>
<button class="btn btn-info" type="button" onclick="location.href='${insertURL}';">신규상품 등록</button>
<table class="table table-bordered table-striped">
	<thead>
		<tr>
			<th><spring:message code="prod.prod_id" /> </th>
			<th><spring:message code="prod.prod_name" /></th>
			<th><spring:message code="prod.prod_lgu" /></th>
			<th><spring:message code="prod.prod_buyer" /></th>
			<th><spring:message code="prod.prod_cost" /></th>
			<th><spring:message code="prod.prod_price" /></th>
			<th><spring:message code="prod.prod_mileage" /></th>
		</tr>
	</thead>
<!-- 	10.01 -->
	<tbody>
		<c:set var="prodList" value="${pagingVO.dataList }" />
		<c:forEach var="prod" items="${prodList }">
<%-- 			<c:url value="/prod/prodView.do" var="viewURL"> --%>
			<c:url value="/prod/${prod.prod_id}" var="viewURL"/>
<%-- 				<c:param name="what" value="${prod.prod_id }" /> --%>
						<tr>
				<td>${prod.prod_id }</td>
				<td><a href="${viewURL }">${prod.prod_name }</a></td>
				<td>${prod.lprod_nm }</td>
				<td>${prod.buyer_name }</td>
				<td>${prod.prod_cost }</td>
				<td>${prod.prod_price }</td>
				<td>${prod.prod_mileage }</td>
			</tr>
		</c:forEach>
	</tbody>
<!-- 	10.01 -->
	<tfoot>
		<tr>
			<td colspan="7">
				<div id="pagingArea">
					${pagingVO.pagingHTML } 
				</div>
			</td>
		</tr>
	</tfoot>
</table>
<script type="text/javascript">
	//1002
	var prod_lguTag = $("#prod_lgu");
	var prod_buyerTag=$("#prod_buyer");
	var searchUI =$("#searchUI");
	var searchBtn = $("#searchBtn"); //서치ui 입력데이터 ui만 갖고 있음 
	var searchForm =$("#searchForm");
// 	var searchTxt = $("#searchTxt");  //단어 검색 
	var pageTag = $("[name='page']");
	
	//1001
	$(prod_lguTag).generateLprod("${pageContext.request.contextPath}","${pagingVO.searchVO.prod_lgu}");
	
	//1002
	$(prod_lguTag).on("change",function(){
		let lgu = $(this).val();
		$(prod_buyerTag).generateBuyer({
			cPath:"${pageContext.request.contextPath}",
			lgu : lgu,
			selectedBuyer : "${pagingVO.searchVO.prod_buyer}"
			
		}); //동적 구성 간으 
	})
	
	$(prod_lguTag).trigger("change");
	
	$("#pagingArea").on("click", "a", function(){
		let page = $(this).data("page"); //this.dataset.page : 안에있는 데이터를 다가져온다 
		if(page<=0)return;
		pageTag.val(page);
		searchForm.submit();
	});
	
	$(searchBtn).on("click",function(){
// 		let child = searchUI.find("select");
		let child = searchUI.find(":input"); //select input 2개 찾을수 있어 
		$(child).each(function(index, element){ //<select> 돌린느 반복문 
			let id = $(this).prop("id"); //this : element => option
			let value = $(this).val();
			searchForm.find("[name='"+ id+"']").val(value); //id와 동일ㅇ한 name을 찾음
		});
		
// 		let id = searchTxt.prop("id"); //text검색
// 		let value = searchTxt.val();
// 		searchForm.find("[name='"+id+"']").val(value);
		searchForm.submit();
	})
	
</script>
<!-- </body> -->
<!-- </html> -->



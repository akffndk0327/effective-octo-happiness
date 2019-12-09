<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%--
* [[개정이력(Modification Information)]]
* 수정일                 수정자      수정내용
* ----------  ---------  -----------------
* 2019. 11. 19.      박주연      최초작성 생활용품 상세보기 
* Copyright (c) 2019 by DDIT All right reserved
 --%>
<style>
.single-product-img {
	width: 480px;
	height: 400px;
}
.dsimg{
	max-width: 480px;
	max-height: 400px;
}
</style>

<section id="single-product">
<div id="InnerContainer">
	<div class="container">
		<div class="row">
			<div class="col-md-5">
				<!-- 				이미지  -->
				<div class="single-product-img" style="text-align: center">
					<c:if test="${empty dsprod.bioCheImg }">
						<img class="dsimg" src="${cPath }/images/noImage.png" alt="">
					</c:if>
					<c:if test="${not empty dsprod.bioCheImg }">
						<img class="dsimg" name="bioCheImg"
							src="data:image/*;base64,${dsprod.bio_imgBase64 }" alt="">
					</c:if>
				</div>
				<br />
			</div>
			<!-- End of /.col-md-5 -->
			<div class="col-md-4" style="width: 500px;">
				<div class="block">
					<div class="product-des" style="height: 380px;">
						<h4>
							<p class="price">${dsprod.bioCheName }</p>
						</h4>
						<table class="table" style="font-size: 21px;">
							<tr>
								<th>제품분류/용도/중량</th>
								<td>${dsprod.bioCheCont}</td>
							</tr>
							<tr>
								<th>업체명</th>
								<td>${dsprod.bioCheCom}</td>
							</tr>
							<tr>
								<th>업체 주소</th>
								<td>${dsprod.bioCheComAddr}</td>
							</tr>
						</table>
					</div>
					<!-- End of /.product-des -->
				</div>
				<!-- End of /.block -->
			</div>
			<!-- End of /.col-md-4 -->
		</div>
		<!-- 		row 끝 -->

		<div class="row" style="margin-bottom: 5%; font-size: 19px">
			<div class="col-md-9" style="width: 985px;">
				<!-- Nav tabs -->
				<ul class="nav nav-tabs">
					<li class="active"><a href="#home" data-toggle="tab"> 주 의
							사 항 </a></li>
					<li><a href="#profile" data-toggle="tab">화학성분 정보</a></li>
				</ul>

				<!-- Tab panes -->
				<div class="tab-content" style="width: 963px;">
					<div class="tab-pane active" id="home">
						<p>${dsprod.bioChePreca }</p>
					</div>
					<div class="tab-pane" id="profile">
						<p>
						<table class="table">
							<th>성분명</th>
							<th>CAS-ID</th>
							<c:forEach items="${dsprod.biocheList }" var="dsList">
								<tr>
									<td>${dsList.chemicals.cheNameKo }</td>
									<td>${dsList.casId }</td>
								</tr>
							</c:forEach>
						</table>
						</p>
					</div>
				</div>
			</div>
			<!-- End of /.col-md-9 -->
		</div>
		<div>
			<input type="button" class="btn btnAi2" onclick="location.href='${cPath }/dailysupply/dsList.do'" value="목록"/>
			<c:if test="${loginId eq 'admin' }">
				<c:url value="/dailysupply/dsUpdate.do?dsNo=${dsprod.bioCheId }"
					var="updateURL" />
				<input id="btn2" type="button" class="btn btnAi0" value="수정하기"
					onclick="location.href='${updateURL}';" />
			</c:if>

		</div>
	</div>
	<!-- 	container 끝 -->
</div>
<!-- InnerContainer 끝 --> </section>


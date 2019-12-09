<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%--
* [[개정이력(Modification Information)]]
* 수정일                     수정자             수정내용
* ----------  	  ---------  -----------------
* 2019. 11. 6.      허민지             최초작성
* 2019. 11. 19      허민지         회원 보유 알러지 세팅
* Copyright (c) 2019 by DDIT All right reserved
 --%>


<style>
.form-group{
	font-size : 20px;
}
#add{
	    cursor: pointer;
    -webkit-appearance: button;
    margin-top: -47px;
    margin-right: 19%;
}
</style>
<div id="InnerContainer">
<div class=titleLeftBar style="width: 600px">${memName }(${loginId })님의 알레르기 정보</div>
	
<%-- 	<c:forEach var="memAllergy" items="${memAllergyList }" varStatus="vs"> --%>
<%-- 		<c:choose> --%>
<%-- 			<c:when test="${memAllergy.allId eq '0' }"> --%>
<!-- 				알레르기 정보 없음. -->
<%-- 			</c:when> --%>
<%-- 			<c:otherwise> --%>
<%-- 			<form method="post" action="${cPath}/memMypage/memAllergyDelete.do"> --%>
<!-- 				<div style="font-size: 19px; width: 200px; display: inline"> -->
<%-- 					<input type="hidden" name="allId" value="${memAllergy.allergy.allId }" /> --%>
<%-- 						<div class="allInfo" style="display: inline; float: left;">${memAllergy.allergy.allName } - &nbsp;</div>  --%>
<%-- 					<input type="hidden" name="symId" value="${memAllergy.allergy.allergySymtom.symName }" /> --%>
<%-- 						<div class="allInfo" style="display: inline; float: left;">${memAllergy.allergy.allergySymtom.symName }</div>  --%>
<%-- 					<input type="submit"  class="btn btnAi1" value="${vs.count }번 알레르기 삭제하기" /> --%>
<!-- 					<br> -->
<!-- 					<br> -->
<!-- 				</div> -->
<%-- 			</form> --%>
<%-- 			</c:otherwise> --%>
<%-- 		</c:choose>	 --%>
<%-- 	</c:forEach> --%>
	<form method="post" action="${cPath}/memMypage/memAllergyDelete.do">
		<div id="allergyPosition">
		
		</div>
	</form>
	
	<hr>
	<form:form action="${cPath }/memMypage/memAllergyInsert.do" method="post" modelAttribute="member">
	<div style="font-size:35px;">알러지 정보 추가하기</div>	<input type="submit" class="btn btnAi0"value="추가하기" id="add"/>
	<br><br>
		<div class="form-group">
		<div class='original'>
			<div class="input-group">
			<select name="memAllList[0].allId" style="height: 45px; font-size: 27px;">
				<option value="0">해당없음</option>
				<!-- contains로 왼10개 오른쪽 10개의 빵을 비교한다. 100개의 빵이 아님 -->
				<!-- contains로 allId를 서로 비교해야함 -->
				<c:forEach var="allergy" items="${allergyList }">
					<c:if test="${allergy.notContains(memAllergyList)}">
						<option value="${allergy.allId }">${allergy.allName }</option>
					</c:if>
				</c:forEach>
			</select>
			<select name="memAllList[0].symId" style="height: 45px; font-size: 27px;">
				<option value="S0">없음</option>
				<c:forEach var="symtomList" items="${symtomList }">
					<option value="${symtomList.symId }">${symtomList.symName }</option>
				</c:forEach>
			</select>
<!-- 			<input type="button" class="btn bntAi0 plusBtn" value="추가하기"/> -->
			<img src="${cPath }/images/plus.png" class="plusBtn"/>
			<br>
			<br>
			</div>
		</div>
		</div>
	
	</form:form>
</div>


<script>

	var cPath  = "${cPath}";
	$("body").on("click", ".plusBtn", function(){
// 		console.log($(".original").html());
		//복사할 한 세트 지정
		var parent = $(this).parents('.original');
		console.log(parent);
		var content = $(parent).get(0).outerHTML;
// 		오리지널 세트의 갯수를 알아야함
		var index = $(".original").length;
		//가져온 html을 넣기전에 그 html의 인덱스를 바꿔줘야함
		//바꿀 인덱스를 갯수만큼 바꿔줘야함
		var newContent = content.replace(/memAllList\[\d\]/gm,"memAllList["+index+"]");
		//바로 위 부모
		var allergy = $(parent).parents('div:first');
		allergy.append(newContent);
// 		console.log(index);
// 		console.log(newContent);
	})
	
	
// 	var minusBtn = $(".minusBtn");
// 	$("body").on("click",".minusBtn", function(){
		
// 	})
	
	//알러지들 눌렀을 때 삭제되게
// 	$("body").on("click", ".allInfo", function(){
// 		console.log("dd");
// 		let action = $(this).attr("action");
// 		let method = $(this).attr("method");
// 		let queryString = $(this).serialize();
// 		$.ajax({
// 			url : action ? action : "",
// 			method : method ? method : "get",
// 			data : queryString,
// 			dataType : "json",
// 			success : function(resp) {
// 				if(resp>0){
// 					$(this).remove();
// 				}
// 			},
// 			error : function(xhr) {
// 				console.log(xhr.status);
// 			}
// 		});
// 	})
	
	
	
</script>


<script src="https://d3js.org/d3.v5.min.js"></script>
<script src="${cPath}/js/allergyCloud2.js"></script>
<script src="https://rawgit.com/jasondavies/d3-cloud/master/build/d3.layout.cloud.js" type="text/JavaScript" />
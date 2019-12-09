<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
<%--
* [[개정이력(Modification Information)]]
* 수정일                     수정자          수정내용
* --------------  ---------  -----------------
* 2019. 11. 22.     이진희          최초작성
* 2019. 11. 26.     최서희	   연관검색어 추가
* Copyright (c) 2019 by DDIT All right reserved
 --%>
<style>
#result{
	min-height : 300px;
}
.url{
	color: green;
}

.name{
	font-weight:bold;
	font-size:25px;
	cursor : pointer;
	color : blue;
	
}
 .name:hover{ 
 	text-decoration: underline; 
} 
.font{
font-size:15px;
}
#InnerContainer{
	border : 1px solid #ddd;
	padding : 20px;
}
#sWords{
	font-size : 20px;
}
.sTokens{
	color : blue;
	cursor : pointer;
}
.sTokens:hover{
	color : blue;
	text-decoration: underline; 
}
#noResult{
	font-size : 17px;
	padding : 30px;
}
</style>
<div id="InnerContainer">
<h2 class="titleTopBar">검색하신 ' <em>${param.prdlstnm }</em>  &nbsp;&nbsp;' 의 검색결과 </h2>
<div id="sDiv">
<c:if test="${not empty similerWords }">
	<p id="sWords"> 혹시 이것을 찾으세요 ? 
		<c:forTokens items="${similerWords }" delims="," var="sw" varStatus="vs">
		<c:if test="${vs.count gt 1 }">
			<a class="sTokens" id="${vs.count }">${sw }</a>
		</c:if>
	</c:forTokens>
	</p>
</c:if>
</div>
<hr color="#ddd" >
<div id="result">
<c:choose>
	<c:when test="${not empty list }">
		<c:forEach items="${list }" var="list">
			<div id="${cPath}${list.resPattern}${list.prdlstreportno }" class="name">${list.prdlstnm }</div>
			<div class="font"><a  class="url" href="${cPath}${list.resPattern}${list.prdlstreportno }">${cPath}${list.resPattern}${list.prdlstreportno }</a></div>
			<div class="font cont" >${list.rawmtrl }</div><br><br>
		</c:forEach>
	</c:when>
	<c:otherwise>
		<div id="noResult"> <b>${param.prdlstnm }</b> 와(과) 일치하는 검색결과가 없습니다.<br/><br/>
		<ol id="listOl">
			<li>· 모든 단어의 철자가 정확한지 확인하세요.</li>
			<li>· 다른 검색어를 사용해 보세요.</li>
			<li>· 더 일반적인 검색어를 사용해 보세요.</li>
			<li>· 키워드 수를 줄여보세요.</li>
		</ol>
		</div>
	</c:otherwise>
</c:choose>
</div>
</div> 
<script >
var result = $("#result");
var sDiv = $("#sDiv");
var sTokens = $(".sTokens");
var resultInput = $(".form-control");

	result.on("click",".name",function(){
		let url = $(this).prop("id");
		location.href=url;
	})
	
	$(sDiv).on("click", ".sTokens", function(){
		let tokens = $(this).text();
		resultInput.val(tokens);
		$(submitBtn).click();
		
	})
</script>



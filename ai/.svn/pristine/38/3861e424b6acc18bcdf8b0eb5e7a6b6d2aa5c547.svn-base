<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<%--
* [[개정이력(Modification Information)]]
* 수정일                  수정자	       수정내용
* -------------  ---------  -----------------
* 2019. 11. 6.    최서희           최초작성
* Copyright (c) 2019 by DDIT All right reserved
 --%>
<style>
#waccpTitle{
	font-size : 33px;
	text-align : center;
}
#waccplogo{
	width : 90%;
	height : 90%;
	margin-left : 50px;
}

hr{
	margin-top : 10px;
	margin-bottom : 20px;
}
.btnAi0{
	height : 60px;
	margin-right : 80px;
    width: 150px;
}
.btnDiv{
	margin-bottom: 200px;
}
</style>
<div id="InnerContainer">

	<h2 class="titleTopBar">WACCP 제품 인증 절차</h2><br>
	<hr color="#ddd">
	<div><img id="waccplogo" src="${cPath }/images/waccpintro.png" ></div>
	<div id="waccpTitle"><b>W</b>ell-made <b>A</b>nalysis and <b>C</b>ritical <b>C</b>ontrol <b>P</b>oint</div>
	<hr color="#ddd">
	<div><img id="waccplogo" src="${cPath }/images/waccpflow.jpg" ></div>
	<br/>
	<div>
	
	<hr color="#ddd">
	<c:url value="/prod/prodList.do" var="listURL" />
	<div class="btnDiv">
		<input type="button" class="btn btnAi0" value="제품 사러가기" onclick="location.href='${listURL}'"/>
	</div>
	</div>
</div>
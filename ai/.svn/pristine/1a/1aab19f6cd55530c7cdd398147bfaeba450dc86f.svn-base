<%@page import="org.apache.commons.lang3.StringUtils"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" buffer="20kb"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<!DOCTYPE html>
<!-- [[개정이력(Modification Information)]] -->
<!-- 수정일 			수정자 	수정내용 -->
<!-- ========== ====== ============== -->
<!-- 2019. 11. 4 	최서희 	최초작성 -->
<!-- 2019. 11. 7 	허민지      레프트 주석 해제 -->
<!-- 2019. 12. 1    허민지      toast message 주석 sweetalert추가 -->
<!-- 2019. 12. 02   허민지      sweet alert 크기 조정 -->
<!-- Copyright (c) ${year} by DDIT All right reserved -->
<html>
<head>
<meta charset="UTF-8">
<jsp:include page="/includee/preScript.jsp" />
<title>Allergy Information</title>
<!-- <script type="text/javascript" -->
<!-- 	src="https://code.jquery.com/jquery-3.4.1.min.js"></script> -->
<style>
.swal2-title {
   font-size: 25px;
}

.swal2-html-container {
   font-size: 18px;
}

.swal2-styled.swal2-confirm {
   font-weight: bold;
   font-size: 15px;
   width: 106px;
   height: 42px;
   font-family: 'Eoe_Zno_L';
}
.swal2-styled.swal2-cancel {
   font-weight: bold;
   font-size: 15px;
   width: 106px;
   height: 42px;
   font-family: 'Eoe_Zno_L';
}

.swal2-popup {
   width: 400px;
   height: 250px;
}
</style>

<script type="text/javascript">
	$.getContextPath = function(){
		return "<%=request.getContextPath()%>";
	}
</script>

<div id="header">
	<tiles:insertAttribute name="topMenu" />
</div>
</head>
<body>
	<div id="comleft">
		<tiles:insertAttribute name="comleft" />
	</div>
	<div id="contents">
		<tiles:insertAttribute name="contents" />
	</div>
	
	<footer>
		<tiles:insertAttribute name="footer" />
	</footer>
	<script type="text/javascript">
		<c:if test="${not empty message }">
	
		//sweet alert
		$(document).ready(function(){
			Swal.fire({
		  	position: 'center',
		  	icon: 'success',
		  	title: '${message}',
		  	showConfirmButton: false,
		  	timer: 1500
			})
		});
		</c:if>
	</script>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%--
* [[개정이력(Modification Information)]]
* 수정일                 수정자      수정내용
* ----------  ---------  -----------------
* 2019. 11. 17.      박주연      최초작성
* Copyright (c) 2019 by DDIT All right reserved
 --%>
<link rel="stylesheet" href="${cPath }/css/adminleft.css">
<div class="titleTopBar">
<li id="adminTitle">관리자 페이지</li>
</div>
<br>
<ul>
	<li><a href="${cPath }/adminMypage/memberList.do"> 회원정보관리</a></li>
	<li><a href="${cPath }/prod/prodAdminList.do"> WACCP 제품 관리 </a></li>
	<li><a href="${cPath }/advertise/adAdminList.do"> 광고 관리 </a></li>
</ul>



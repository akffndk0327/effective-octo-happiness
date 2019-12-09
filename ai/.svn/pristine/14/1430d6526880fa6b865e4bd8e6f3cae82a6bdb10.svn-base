<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%--
* [[개정이력(Modification Information)]]
* 수정일                 수정자      수정내용
* ----------  ---------  -----------------
* 2019. 11. 6.      최서희      최초작성
* Copyright (c) 2019 by DDIT All right reserved
 --%>
<style>
.resultBtn{
	width : 300px;
	height : 50px;
	margin : 2px;
	font-size : 26px;
	background-color : #077B5F;
	border : 1px solid #077B5F;
	color : white;
}
.resultdiv{
	font-size : 30px;
}
table{
	text-align : center;
	width : 95%;
	height : 600px;
	margin-bottom : 100px;
}
</style>
<div id="InnerContainer">
<table>
	<tr><td>
		<div class="resultdiv">
		주문번호  <a href="#">${orderId }</a>  결제가 
		</div>
	<div class="resultdiv">완료되었습니다.</div>
	<br><br><br>
	<input type="button" class="btn resultBtn" value="주문내역확인" 
		 onclick="location.href='<c:url value="/order/orderList.do"/>';"/>
	</td></tr>
</table>

</div>
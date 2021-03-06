<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
<%--
* [[개정이력(Modification Information)]]
* 수정일                            수정자      수정내용
* 2019. 11. 17.     이진희     식품상세보기 메서드작성
* 2019. 11. 16.     이진희     식품카테고리 뷰 작성
* ----------  ---------  -----------------
* 2019. 11. 6.      이진희      최초작성
* Copyright (c) 2019 by DDIT All right reserved
 --%>
<style>
	.food{
		width:100px;
		height:100px;
		display:block;
		margin-left:20px;
		
	}
	
	.table1{
	  width:700px;
	  height:500px;
	  margin-left:300px;
	  border-spacing: 2px;
	  border-collapse: separate;
	  margin-top : 30px;
	}
	p{
	  font-size:25px;
	  text-align:center;
	}
	.td1{
	  width:150px;
	  border: solid 5px transparent;
	}
	
	.td1:hover {
	    border-color: #2F4F4F;
	    color:#2F4F4F;
	}

	 .searchWord{
		height: 30px;
	}
	
	#search{
		margin-left:500px;
	}
	
	#btn1{
 		background-color: #2F4F4F;
 		color:white;
 		width:60px;
	}
	
	.titleTopBar{
		margin-left:200px;
		margin-top:30px;
	}
	
</style>
<div id="InnerContainer">
	<h1 class="titleTopBar">CATEGORY</h1>
	<table class="table1">
		<tr>
			<c:url value="/food/foodList.do" var="ListURL">
				<c:param name="fcId" value="F01" />
			</c:url>
			<td id="F01" class="td1"><img class="food" src="${cPath }/images/ketchup.png" onmouseover="this.src='${cPath }/images/ketchup2.png';" onmouseout="this.src='${cPath }/images/ketchup.png';" onclick="location.href='${ListURL}';" ><p>Oil/Source</p></td>
			
			<c:url value="/food/foodList.do" var="ListURL">
				<c:param name="fcId" value="F02" />
			</c:url>
			<td id="F02" class="td1"><img class="food" src="${cPath }/images/Refrigerator.png" onmouseover="this.src='${cPath }/images/Refrigerator2.png';" onmouseout="this.src='${cPath }/images/Refrigerator.png';" onclick="location.href='${ListURL}';" ><p>Refrigeration</p></td>
			
			<c:url value="/food/foodList.do" var="ListURL">
				<c:param name="fcId" value="F03" />
			</c:url>
			<td id="F03" class="td1"><img class="food" src="${cPath }/images/tetra.png" onmouseover="this.src='${cPath }/images/tetra2.png';" onmouseout="this.src='${cPath }/images/tetra.png';" onclick="location.href='${ListURL}';"><p>Milk</p></td>
			
			<c:url value="/food/foodList.do" var="ListURL">
				<c:param name="fcId" value="F04" />
			</c:url>
			<td id="F04" class="td1"><img class="food" src="${cPath }/images/Tea.png" onmouseover="this.src='${cPath }/images/Tea2.png';" onmouseout="this.src='${cPath }/images/Tea.png';" onclick="location.href='${ListURL}';"><p>Tea/Coffee</p></td>
			
		</tr>
		<tr>
			<c:url value="/food/foodList.do" var="ListURL">
				<c:param name="fcId" value="F05" />
			</c:url>
			<td id="F05" class="td1"><img class="food" src="${cPath }/images/drinks.png" onmouseover="this.src='${cPath }/images/drinks2.png';" onmouseout="this.src='${cPath }/images/drinks.png';" onclick="location.href='${ListURL}';"><p>Liquor</p></td>
			
			<c:url value="/food/foodList.do" var="ListURL">
				<c:param name="fcId" value="F06" />
			</c:url>
			<td id="F06" class="td1"><img class="food" src="${cPath }/images/chocolate.png" onmouseover="this.src='${cPath }/images/chocolate2.png';" onmouseout="this.src='${cPath }/images/chocolate.png';" onclick="location.href='${ListURL}';"><p>Snacks</p></td>
			
			<c:url value="/food/foodList.do" var="ListURL">
				<c:param name="fcId" value="F07" />
			</c:url>
			<td id="F07" class="td1"><img class="food" src="${cPath }/images/can.png" onmouseover="this.src='${cPath }/images/can2.png';" onmouseout="this.src='${cPath }/images/can.png';" onclick="location.href='${ListURL}';"><p>Can</p></td>
			
			<c:url value="/food/foodList.do" var="ListURL">
				<c:param name="fcId" value="F08" />
			</c:url>
			<td id="F08" class="td1"><img class="food" src="${cPath }/images/baby.png" onmouseover="this.src='${cPath }/images/baby2.png';" onmouseout="this.src='${cPath }/images/baby.png';" onclick="location.href='${ListURL}';"><p>Babyfood</p></td>
		</tr>
	</table>
	<br><br><br>
</div>


<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%--
* [[개정이력(Modification Information)]]
* 수정일                 수정자      수정내용
*
* ----------  ---------  -----------------
* 2019. 11. 7.      박주연      최초작성
* Copyright (c) 2019 by DDIT All right reserved
 --%>
 
<style>
	.table-mc-light-blue{
		width : 1000px;
		margin: 0 auto;
	}
	thead{
		font-size: 17px;
	
	}
	tbody{
		font-size: 17px;
	}
	
	#InnerContainer{
		margin-bottom: 10px;
	}
	
	#title,th,
	#player{
		text-align: center;
	}
	
	img{
/* 		display: block;  */
 		margin: 0px auto; 
	}
	.small{
		font-size: 19px;
		color: #575555;
	} 
	.big{
		font-weight : bold;
		font-size: 24px;
		color: #575555;
	}
	#noimage{
		width: 300px;
	}

</style>
<div id="InnerContainer">
      <h1 class="titleTopBar" style="margin-left:148px" >News View</h1>
<div id="title">
	<h2 >${news.newsTitle}</h2>
</div>
	<table class="table table-hover table-mc-light-blue">
	<thead style="font-size: 24px">
		<tr>
			<th>
				작성자 : ${news.memId}
			</th>
			<th>
				날짜 : ${news.newsIndate}  
			</th>
			<th>
				조회수 : ${news.newsHit}  
			</th>
		</tr>
	</thead>
	<tbody>
		<tr>
			<td colspan="3" style="font-size: 20px">
					${news.newsContent}
			</td>
		</tr>
	</tbody>
	</table>
	<br><br>
	<c:if test="${loginId eq 'admin' }">
		<c:url value="/news/newsUpdate.do" var="updateURL">
			<c:param name="newsNo" value="${news.newsNo}" />
		</c:url>
		<input type="button" class="btn btnAi0" value="삭제하기" id="remove">
		<input type="button" class="btn btnAi0" onclick="location.href='${updateURL}';" value="수정하기" />
	</c:if>
		<input type="button" class="btn btnAi1" id="back" value="뒤로가기" style="float: left"/>
	<br><br>
</div>
<script>
	$(function(){
		$("#back").on("click",function(){
			history.back();
		})
		
		$("#remove").on("click",function(){
			$.ajax({
				url : "${cPath}/news/newsDelete.do",
				method :"post",
				data : {
					"newsNo" : "${news.newsNo}"
				},
				dataType : "json",
				success : function(resp) {
					Swal.fire({
		                 title: '삭제',
		                 text: "삭제되었습니다.",
		                 icon: 'success',
		                 showCancelButton: false,
		                 confirmButtonColor: '#90c322',
		                 confirmButtonText: '확인'
		               }).then((result) => {
		                   if (result.value) {
							location.href = "${cPath}/news/newsList.do";
		                  }
		              });
				},
				error : function(xhr) {
					console.log(xhr.status);
				}
			});
		})
	})
</script>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%--
* [[개정이력(Modification Information)]]
* 수정일                 수정자      수정내용
*
* ----------  ---------  -----------------
* 2019. 11. 6.      박슬기      최초작성
* Copyright (c) 2019 by DDIT All right reserved



 --%>
<style>
.lock {
	width: 12px;
}

#myModal {
	overflow: hidden;
	font-size: 25px;
	
	} 

span {
	font-size: 20px;
	color: red;
}
#text{
	color:black;
}

.btn-outline-success{
	float: inherit;
	background-color: #2F4F4F;
	color:white;
	text-align: center;
}


tfoot,th{
	text-align: center;
}


.table-mc-light-blue{
	width : 100%;
	margin: 0 auto;
	text-align: center;
}

#buttonlocation{
	text-align: right;
}

.title{
	text-align: left;
}


</style>
<div id="InnerContainer" style="width: 1500px">
	<div id="demo">
		<h2 style="margin-left:8px" class="titleTopBar">SMART식단</h2>
		<br>
		<form action="?" id="searchForm">
			<input type="hidden" name="page" />
		</form>

		<div class="table-responsive-vertical shadow-z-1">
			<table class="table table-hover table-mc-light-blue">
				<thead>
					<tr>
						<th>글번호</th>
						<th>제목</th>
						<th>작성자</th>
						<th>작성일</th>
					</tr>
				</thead>
				<tbody id="listBody">
					<c:set var="dietList" value="${pagingVO.dataList}" />
					<c:choose>
						<c:when test="${not empty dietList }">
							<c:forEach items="${dietList }" var="diet">
								<tr id="${diet.monthlyId }" class="list">
									<td>${diet.rnum }</td>
									<c:if test="${diet.monthlyUse eq 'N'}">
										<td class="title"><img
											src="${pageContext.request.contextPath }/images/lock.png"
											class="lock">&nbsp;${diet.monthlyTitle }</td>
									</c:if>
									<c:if test="${diet.monthlyUse ne 'N'}">
										<td class="title">${diet.monthlyTitle }</td>
									</c:if>
									<td class="writer">${diet.memId }</td>
									<td>${diet.monthlyIndate }</td>
									<input type="hidden" class="mon" value="${diet.monPass}" />
								</tr>
							</c:forEach>
						</c:when>
						<c:otherwise>
							<tr>
								<td colspan="4">조건에 맞는 글이 없음.</td>
							</tr>
						</c:otherwise>
					</c:choose>
				</tbody>
				<tfoot>
					<tr>
						<td colspan="4">
							<div id="pagingArea">${pagingVO.pagingHTML }</div>
						</td>
					</tr>
				</tfoot>
			</table>
		</div>
			<div id="buttonlocation">
					<input type="button" value="작성하기"
						class="btn btn-outline-success btn btnAi1 "
						onclick="location.href='<c:url value="/diet/dietInsert.do"/>';"/>
			</div>

	</div>
	<br>
	<br>
</div>

<div class="modal" tabindex="-1" role="dialog" id="myModal">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title">비밀글입니다</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
        <p>비밀번호를 입력해주세요</p>
        <input type="password" id="text"/>
        <span class="passcheck"></span>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-dismiss="modal">닫기</button>
        <button type="button" class="btn btn-primary">확인</button>
      </div>
    </div>
  </div>
</div>

<script type="text/javascript">
	var listBody = $("#listBody");
	var pagingArea = $("#pagingArea");
	var pageTag = $("[name='page']");
	var searchForm = $("#searchForm");
	pageTag.val("1");

	pagingArea.on("click", "a", function(event) {
		event.preventDefault();
		let page = $(this).data("page");
		if (page < 1)
			return false;
		pageTag.val(page);
		searchForm.submit();
		return false;	
	});
	
	var monPass;
	var value;
	var writer;
	$(function() {
		$('.list').on("click", function() {
			var check = $(this).children().children().attr('class');
			monPass = $(this).children(".mon").val();
			value = $(this).attr("id");
			writer = $(this).children(".writer").html();
			if (check == "lock") {
				$("#text").val("");
				$("#text").next(".passcheck").html("");
				$("#myModal").modal("show");
			} else {
				location.href = "${cPath}/diet/dietView.do?monthlyId="+value+"&memId="+writer;
			}
		})
		
		
		$(".btn-primary").on("click", function() {
			var pass = $("#text").val();
			if (pass == monPass) {
				location.href = "${cPath}/diet/dietView.do?monthlyId=" + value+"&memId="+writer;
			} else {
				$("#text").next(".passcheck").html(" 비밀번호가 일치하지 않습니다.");
				$("#text").val("");
			}
		})

	})
</script>
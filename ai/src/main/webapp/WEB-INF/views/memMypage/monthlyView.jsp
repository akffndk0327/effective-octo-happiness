<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%--
* [[개정이력(Modification Information)]]
* 수정일                수정자      수정내용
* ----------  ---------  -----------------
* 2019. 11. 21.      	 허민지      최초작성
* Copyright (c) 2019 by DDIT All right reserved
 --%>
 
<style>
#mothlyTable{
	width: 93%;
    margin-bottom: 21px;
    margin-left: 50px;
}
</style>
	<div id="InnerContainer">
<div class=titleLeftBar style="width: 470px">${memName}(${loginId })님의 식단 공유 게시글</div>
	<input type="hidden" value="${savedMember.memId }" id="memId"/>
		<form action="?" id="searchForm">
			<input type="hidden" name="page" />
		</form>
		<table id="mothlyTable" class="table table-hover table-mc-light-blue">
					<thead>
						<tr>
							<th>선택</th>
							<th>글 번호</th>	
							<th>글 제목</th>	
							<th>날짜</th>	
						</tr>
					</thead>
					<tbody>
					
						<c:choose>
							<c:when test="${not empty pagingVO.dataList}">
								<c:forEach var="monthly" items="${pagingVO.dataList}" >
									<tr>
										<td>
											<input type="checkbox" value="${monthly.monthlyId }" name="check"/>
										</td>
										<td class="diet">
											${monthly.monthlyId }
										</td>
										<td> 
											${monthly.monthlyTitle}
										</td>
										<td>
											${monthly.monthlyIndate}
										</td>
									</tr>
								</c:forEach>
							</c:when>
							<c:otherwise>
								<td colspan="4">작성 한 글이 존재하지 않습니다.</td>
							</c:otherwise>
						</c:choose>
					</tbody>
					<tfoot>
						<tr>
							<td colspan="4" style="text-align: center">
								<div id="pagingArea">${pagingVO.pagingHTML }</div>
							</td>
						</tr>
					</tfoot>
				</table>
				<div style="float: right;">
					<input type="button" class="btn btnAi2" value="삭제하기" id="delete"/>
				</div>
				<br>
	</div>
<div class="modal fade bs-example-modal-sm" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel" id="checkModal">
    <div class="modal-dialog modal-sm" role="document">
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
          <h4 class="modal-title" id="mySmallModalLabel">삭제 확인</h4>
        </div>
        <div class="modal-body" style="text-align: center">
          <h6>
             삭제된 데이터는 복구 불가능합니다.<br>
             삭제하시겠습니까?
          </h6>
         <button type="button" class="btn btn-primary">확인</button>
         <button type="button" class="btn btn-secondary" data-dismiss="modal">닫기</button>
        </div>

      </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->
 <script type="text/javascript">
	$(function(){
		var pagingArea = $("#pagingArea");
		var pageTag = $("[name='page']");
		pagingArea.on("click", "a", function(event) {
			event.preventDefault();
			let page = $(this).data("page");
			if (page < 1)
				return false;
			pageTag.val(page);
			searchForm.submit();
			return false;	
		});
		
		
		$(".diet").on("click",function(){
			var monId = $(this).text();
			var memId = $("#memId").val();
			location.href = "${cPath}/diet/dietView.do?monthlyId="+monId+"&memId="+memId;
		})
		
		var deleteList = [];
		$("#delete").on("click",function(){
			$("input[name=check]:checked").each(function(){
				deleteList.push($(this).val());
			});
			$("#checkModal").modal("show");
			$(".btn-primary").on("click",function(){
				$.ajax({
					url : "${cPath}/diet/dietDelete.do",
					method : "post",
					traditional : true,
					data : {
						"deleteList" : deleteList
					},
					dataType : "json",
					success : function(resp) {
						  window.location.reload();
					},
					error : function(xhr) {
						console.log(xhr.status);
					}
				});
			 })
		})
		
		
	})
</script>
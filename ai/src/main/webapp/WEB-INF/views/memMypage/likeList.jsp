<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%--
* [[개정이력(Modification Information)]]
* 수정일                 수정자      수정내용
*
* ----------  ---------  -----------------
* 2019. 11. 6.      허민지      최초작성
* 2019. 11. 26.      이유진      조회기능구현
* Copyright (c) 2019 by DDIT All right reserved
 --%>
<style>
#mothlyTable {
	width: 93%;
	margin-bottom: 21px;
	margin-left: 50px;
}
</style>
<div id="InnerContainer">
<div class="titleLeftBar">${savedMember.memName }(${savedMember.memId })님의
	찜한 레시피</div>
	<input type="hidden" value="${savedMember.memId }" id="memId" />
	<table id="mothlyTable" class="table table-hover table-mc-light-blue">
		<thead>
			<tr>
				<th style="width: 150px">좋아요 한 레시피</th>
				<th></th>
				<th></th>
				<th></th>
			</tr>
		</thead>
		<tbody>
			<c:if test="${not empty likeList}">
				<tr>
					<td>좋아요</td>
					<td>분류</td>
					<td>글 제목</td>
				</tr>
				<c:forEach var="like" items="${likeList}">
					<tr>
						<td><img class="heart" name="${like.recipeNo }"
							src="<c:url value="/images/heart.png" />" style="height: 20px;">
						</td>
						<td>${like.recipeType}</td>
						<td class="re" id="${like.recipeNo }">${like.recipeTitle}</td>
					</tr>
				</c:forEach>
			</c:if>
			<c:if test="${empty likeList}">
				<td colspan="3">좋아요 한 게시글이 존재하지 않습니다.</td>
			</c:if>
		</tbody>
	</table>
</div>
<div class="modal fade bs-example-modal-sm" tabindex="-1" role="dialog"
	aria-labelledby="mySmallModalLabel" id="checkModal">
	<div class="modal-dialog modal-sm" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<h4 class="modal-title" id="mySmallModalLabel">삭제 확인</h4>
			</div>
			<div class="modal-body" style="text-align: center">
				<h6>
					삭제된 데이터는 복구 불가능합니다.<br> 삭제하시겠습니까?
				</h6>
				<button type="button" class="btn btn-primary">확인</button>
				<button type="button" class="btn btn-secondary" data-dismiss="modal">닫기</button>
			</div>

		</div>
		<!-- /.modal-content -->
	</div>
	<!-- /.modal-dialog -->
</div>
<!-- /.modal -->
<script type="text/javascript">
    $(function(){
        
        $(".re").on("click",function(){
            var recipeId = $(this).attr("id");
            location.href = "${cPath}/recipe/recipeView.do?what="+recipeId;
        })
        
        
        
        $('.heart').on("click",function(){
        var type='heart';
        var loginId='${savedMember.memId}';
        var recipeNo=$(this).attr("name");
        $.ajax({
            url : "${cPath}/recipe/recipeLike.do",
            method :"post",
            data : {"type":type,
                "loginId":loginId,
                "recipeNo":recipeNo},
            dataType : "json",
            success : function(resp) {
               if(resp.status=='ok'){
                   if(resp.type=="heart"){
                          Swal.fire({
                               title: '취소',
                               text: "좋아요를 취소 했습니다",
                               icon: 'success',
                               showCancelButton: false,
                               confirmButtonColor: '#90c322',
                               confirmButtonText: '확인'
                             }).then((result) => {
                                 if (result.value) {
                                     window.location.reload();
                                   }
                            });
                   }
               }
            },
            error : function(xhr) {
                console.log(xhr.status);
            }
        });
        
    })
    })
</script>
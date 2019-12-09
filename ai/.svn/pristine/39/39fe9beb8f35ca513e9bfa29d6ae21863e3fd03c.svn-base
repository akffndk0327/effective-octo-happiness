<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
#mothlyTable{
    width: 93%;
    margin-bottom: 21px;
    margin-left: 50px;
}
</style>
    <div id="InnerContainer">
<div class="titleLeftBar">${savedMember.memName }(${savedMember.memId })님의 레시피 게시글</div>
    <input type="hidden" value="${savedMember.memId }" id="memId"/>
        <table id="mothlyTable" class="table table-hover table-mc-light-blue">
                    <thead>
                        <tr>
                            <th style="width:150px"></th>
                            <th style="width:150px"></th>
                            <th></th>
                            <th><input type="button" class="btn btnAi0" value="삭제하기" id="delete"/></th>
                        </tr>
                    </thead>
                    <tbody>
                            <c:if test="${not empty recipeList}">
                                <tr>
                                    <td>선택</td>
                                    <td>분류</td>   
                                    <td>글 제목</td>   
                                    <td>날짜</td> 
                                </tr>
                                <c:forEach  var="recipe" items="${recipeList}">
                                    <tr class="re" id="${recipe.recipeNo }">
                                        <td>
                                            <input type="checkbox" value="${recipe.recipeNo }" name="check"/>
                                        </td>
                                        <td>
                                            ${recipe.recipeType}
                                        </td>
                                        <td> 
                                            ${recipe.recipeTitle}
                                        </td>
                                        <td>
                                            ${recipe.recipeIndate}
                                        </td>
                                    </tr>
                                </c:forEach>
                            </c:if>
                            <c:if test="${empty recipeList}">
                                <td colspan="4">작성 한 글이 존재하지 않습니다.</td>
                            </c:if>
                    </tbody>
                </table>
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
        
        
        $(".re").on("click",function(){
            var recipeId = $(this).attr("id");
            location.href = "${cPath}/recipe/recipeView.do?what="+recipeId;
        })
        
        var deleteList = [];
        $("#delete").on("click",function(){
            $("input[name=check]:checked").each(function(){
                deleteList.push($(this).val());
            });
            $("#checkModal").modal("show");
            $(".btn-primary").on("click",function(){
                $.ajax({
                    url : "${cPath}/recipe/recipeDelete.do",
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
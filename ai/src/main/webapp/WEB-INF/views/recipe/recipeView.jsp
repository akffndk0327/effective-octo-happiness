<!-- * [[개정이력(Modification Information)]] -->
<!-- * 수정일                 수정자      수정내용 -->
<!-- * -->
<!-- * ----------  ---------  ----------------- -->
<!-- * 2019. 11. 4.      이유진      최초작성 -->
<!-- * Copyright (c) 2019 by DDIT All right reserved -->
<!--  --%> -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<link rel="stylesheet" href="${cPath }/css/review.css">
<!-- <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script> -->
<!-- <script src="https://cdn.jsdelivr.net/npm/sweetalert2@9"></script> -->

<style type="text/css">
.contentDiv {
	width: 100%;
	height: 100%;
	margin-top: 15px;
}

.subContentsDiv {
	width: 80%;
	margin: 0 auto;
}

.listDiv {
	width: 100%
}

.listDiv table th:nth-child(1) {
	width: 20%;
	vertical-align: super;
}

.listDiv table td:nth-child(1) {
	width: 20%;
	vertical-align: center;
	text-align: left;
}

.listDiv table td:nth-child(2) {
	width: 60%;
	vertical-align: center;
	text-align: left;
}

.listDiv table td:nth-child(3) {
	width: 20%;
	vertical-align: center;
	text-align: center;
}

.listDiv table td:nth-child(4) {
	width: 20%;
	vertical-align: center;
	text-align: center;
}

.buttonDiv {
	margin: 10px;
	width: 100%;
	text-align: right;
}

.commentName {
	width: 9%;
	height: 50px;
	display: inline-block;
	vertical-align: top;
}

.commentContents {
	width: 80%;
	height: 'auto';
	display: inline-block;
	vertical-align: top;
}

.commentDate {
	width: 9%;
	height: 50px;
	display: inline-block;
	vertical-align: top;
	text-align: center;
}

.commentSubContents {
	border: solid 1px #DEE2E6;
	padding: 10px;
	margin: 10px;
}

.commentDel {
	float: right;
	padding-right: 10px;
}

.commentAdd {
	float: right;
	padding-right: 10px;
}

span{
    font-size: 25px;
}
</style>

<c:set value="${pagingVO }" var="pagingVO" />

<div id="InnerContainer">
	<h2 class="titleTopBar">레시피 상세보기</h2>
	<div class="titleLeftBar">${recipe.recipeTitle}</div>
	<div class="contentDiv">
		<div class="subContentsDiv">
			<div class="listDiv">
				<table style="font-size: 20px;" class="table table-bordered">
					<th>작성자</th>
					<td>${recipe.memId}</td>
					</tr>
					</tr>
					<tr>
						<th>작성일</th>
						<td>${recipe.recipeIndate}</td>
					</tr>

					<tr>
						<th>분류</th>
						<td>${recipe.reType.recipeTypenm}</td>
					</tr>
					<tr>
						<th>찜</th>
						<td><c:choose>
								<c:when test="${recipe.count gt 0 }">
									<img class="heart" src="<c:url value="/images/heart.png" />"
										style="height: 20px;">
								</c:when>
								<c:otherwise>
									<img class="heart" src="<c:url value="/images/beanheart.png"/>"
										style="height: 20px;">
								</c:otherwise>
							</c:choose></td>
					</tr>
					<tr>
						<th>레시피</th>
						<td style="font-size: 15px">${recipe.recipeContent}</td>
					</tr>
				</table>
			</div>

			<div class="buttonDiv">
				<c:url value="/recipe/recipeUpdate.do" var="updateURL">
					<c:param name="what" value="${recipe.recipeNo }" />
				</c:url>


				<c:if test="${recipe.memId eq loginId}">
					<input type="button" class="btn btnAi0" value="수정"
						onclick="location.href='${updateURL}';" />
					<input type="button" class="btn btnAi0" value="삭제"
						id="deleteRecipe" />
				</c:if>
				<input type="button" class="btn btnAi0" value="목록으로"
					onclick="location.href='<c:url value="/recipe/recipeList.do"/>';" />
			</div>

			<div class="listDiv" style="font-size: 15px;">
				<table class="custom" style="margin-top: 20px; width: 100%;">
					<tr style="border: solid 1px #DEE2E6;">
						<th colspan="4" style="padding: 10px;">댓글</th>
					</tr>
					<c:forEach items="${pagingVO.dataList }" var="reply"
						varStatus="status">
						<tr>
						<c:if test="${empty reply.parRep }">
							<td style="padding: 10px;">${reply.memId}</td>
						</c:if>
						<c:if test="${not empty reply.parRep }">
							<td style="padding: 10px;">ㄴ ${reply.memId}</td>
						</c:if>
							<td style="padding-left:${reply.level * 2}0px;" class="cont">
                                     ${reply.repCont}
							</td>
							<td>
									<c:out value="${reply.repDate}" />
								</td>
							<td><c:if test="${loginId ne reply.memId }">
									<input type="button" class="replyadd btn btnAi0" value="답댓글" style="width: 80px;" />
									<input type="hidden" value="${reply.repId }" />
									<input type="hidden" value="${reply.memId}" />
								</c:if> <c:if test="${loginId eq reply.memId }">
									<c:if test="${empty reply.repDel }">
										<input type="button" class="replymodi btn btnAi1" value="수정" style="width: 80px; height: 40px;" />
										<input type="button" class="replydel btn btnAi1" value="삭제"
											style="width: 80px; height: 40px;" />
										<input type="hidden" value="${reply.repId }" />
										<input type="hidden" value="${reply.memId}" />
										<input type="hidden" value="${reply.repCont}" />
									</c:if>
								</c:if></td>
						</tr>
					</c:forEach>
					<tr>
						<th colspan="4" style="padding: 10px;">
							<div id="pagingArea" style="width: 100%; margin: 0 auto;">${pagingVO.pagingHTML }</div>
						</th>
					</tr>
				</table>
			</div>

			<div class="listDiv" style="border: solid 1px #DEE2E6;">
				<form class="form" name="form"
					ng-submit="form.$valid && cmntCtrl.addComment()" novalidate>
					<div class="form-row" style="text-align: center;">
						<textarea id="txt" class="input" ng-model="cmntCtrl.comment.text"
							placeholder="댓글을 입력해주세요." required
							style="width: 80%; padding: 10px;"></textarea>
						<div id="app"
							style="display: inline-block; position: relative; top: 50px;">
							<input type="button" id="submitt" class="btn btnAi0" value="등록">
						</div>
					</div>
					<div class="form-2"></div>
				</form>
			</div>
		</div>
	</div>

</div>
<form action="?" id="searchForm"
	class="form-inline justify-content-center mb-3">
	<input type="hidden" name="page" /> <input type="hidden" name="what"
		value="${recipe.recipeNo }" />

</form>
<script type="text/javascript">
    var pagingArea = $("#pagingArea");
    var pagingArea = $("#pagingArea");
    var pageTag = $("[name='page']");
    var replydel=$('.replydel');
    pageTag.val(1);
    
    //찜하기
    $('.heart').on("click",function(){
         var loginId ="${loginId}";
         var auth="${authorId}";
         
        if(loginId==""){
            Swal.fire({
                title: '로그인 오류',
                text: "로그인 후 사용할 수 있는 기능입니다",
                icon: 'warning',
                showCancelButton: false,
                confirmButtonColor: '#90c322',
                confirmButtonText: '확인'
              });
                 
            return;
        }
        
        if(auth=="ROLE_ADMIN"){
            Swal.fire({
                title: '경고',
                text: "회원만이 사용할 수 있는 기능입니다",
                icon: 'error',
                showCancelButton: false,
                confirmButtonColor: '#90c322',
                confirmButtonText: '확인'
              });
                 
            return;
        }
         
        var type;
        var img=$(this).attr("src");
        if(img=='${cPath}/images/heart.png'){
            type='heart';
        }else if(img=='${cPath}/images/beanheart.png'){
            type='bean'
        }
    
        $.ajax({
            url : "${cPath}/recipe/recipeLike.do",
            method :"post",
            data : {"type":type,
                "loginId":loginId,
                "recipeNo":"${recipe.recipeNo}"},
            dataType : "json",
            success : function(resp) {
               if(resp.status=='ok'){
                   if(resp.type=="heart"){
                       $(this).attr("src",'/images/beanheart.png')
                          Swal.fire({
                               title: '취소',
                               text: "좋아요를 취소 했습니다",
                               icon: 'info',
                               showCancelButton: false,
                               confirmButtonColor: '#90c322',
                               confirmButtonText: '확인'
                             }).then((result) => {
                                 if (result.value) {
                                     window.location.reload();
                                   }
                            });
                   }else{
                       $(this).attr("src",'/images/heart.png');
                       Swal.fire({
                           title: '좋아요!',
                           text: "${recipe.recipeTitle} 을 좋아요 하셨습니다",
                           icon: 'info',
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
    
    
    //글 삭제 에이젝스
    $('#deleteRecipe').on('click',function(){
    	Swal.fire({
            title: '정말 삭제하시겠습니까?',
            text: "${recipe.recipeTitle} 의 모든 댓글과 내용이 삭제 됩니다",
            icon: 'warning',
            showCancelButton: true,
            confirmButtonColor: '#90c322',
            confirmButtonText: '확인'
          }).then((result) => {
              if (result.value) {
            	  $.ajax({
                      url : "${cPath}/recipe/recipeDelete.do",
                      method :"get",
                      data : {"what":"${recipe.recipeNo }"},
                      dataType : "json",
                      success : function(resp) {
                          if(resp.status=='ok'){
                        	  Swal.fire({
                                  title: '삭제 성공',
                                  text: "게시글이 삭제 되었습니다",
                                  icon: 'success',
                                  showCancelButton: false,
                                  confirmButtonColor: '#90c322',
                                  confirmButtonText: '확인'
                                }).then((result) => {
                                  if (result.value) {
                              window.location.href = '${cPath}/recipe/recipeList.do';
                                  }
                                });
                          }
                      },
                      error : function(xhr) {
                          console.log(xhr.status);
                      }
                  });
                }
         });

    })
    
    //댓글 페이징 처리
    pagingArea.on("click", "a", function(event) {
        event.preventDefault();
        let page = $(this).data("page");
        if (page < 1)
            return false;
        pageTag.val(page);
        searchForm.submit();
        return false;
    });
    
    //댓글 삭제
    replydel.on('click', function() {
        var repNo = $(this).next().val();
        
        $.ajax({
            url : '${cPath}/recipe/replyDelete.do',
            method :'post',
            data : {
                "repNo" : repNo
            },
            dataType : 'json',
            success : function(resp) {
                if (resp.status=="OK") {
                	 Swal.fire({
                         title: '댓글 삭제 완료',
                         text: "댓글을 삭제 했습니다",
                         icon: 'success',
                         showCancelButton: false,
                         confirmButtonColor: '#90c322',
                         confirmButtonText: '확인'
                       }).then((result) => {
                           if (result.value) {
                               window.location.reload();
                             }
                      });
                } else {
                    alert("삭제 실패");
                }
            },
            error : function(xhr) {
                console.log(xhr.status);
            }
        });

    });
    
    //대댓글
    $('.replyadd').on('click', function() {
           var loginId ="${loginId}";
           var recipeNo="${recipe.recipeNo }";
           var repNo = $(this).next().val();
           var mem = $(this).next().next().val();
         $('#txt').attr("placeholder","@"+mem+"님에게 대댓글을 달아보세요!");
         $('#submitt').attr('repNo',repNo);
    });
    
    //댓글 등록
    $('#submitt').on('click', function() {
       var cont = $('#txt').val();
       var loginId ="${loginId}";
       var recipeNo="${recipe.recipeNo }";
       var parent=$(this).attr('repNo');
       
       if(loginId==""){
           Swal.fire({
               title: '로그인 오류',
               text: "로그인 후 사용할 수 있는 기능입니다",
               icon: 'warning',
               showCancelButton: false,
               confirmButtonColor: '#90c322',
               confirmButtonText: '확인'
             });
           return;
       }
       
       $.ajax({
           url : '${cPath}/recipe/replyInsert.do',
           method :'post',
           data : {
               "cont" : cont,
               "loginId" : loginId,
               "recipeNo":recipeNo,
               "parent":parent
           },
           dataType : 'json',
           success : function(resp1) {
               if (resp1.status=="OK") {
                   Swal.fire({
                       title: '댓글 등록 완료',
                       text: "댓글을 등록 했습니다",
                       icon: 'success',
                       showCancelButton: false,
                       confirmButtonColor: '#90c322',
                       confirmButtonText: '확인'
                     }).then((result) => {
                         if (result.value) {
                           $(this).attr("repNo","");
                             window.location.reload();
                           }
                    });
               } else {
                   alert("실패");
               }
           },
           error:function(request,status,error){
                console.log("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
        }
       });
       
      
    });
    
    //댓글-수정버튼 눌렀을때
    $('.replymodi').on('click', function() {
        $('#newadd1').remove();
        var cont = $(this).next().next().next().next().val();
        var mem = $(this).next().next().next().val();
        var repNo = $(this).next().next().val();
        
        var index=cont.indexOf(":");
        cont=cont.substring(index+1);
       $('#txt').val(cont+"");
       $('#app').append('<input type="button" id="newadd1" class="btn btnAi0" repNo='+repNo+' value="수정">');
    });
    
    //수정등록
    $(document).on('click','#newadd1',function(){
        var cont=$('#txt').val();
        var recipeNo=$(this).attr('repNo');
         $.ajax({
             url : '${cPath}/recipe/replyUpdate.do',
             method :'post',
             data : {
                 "cont" : cont,
                 "recipeNo":recipeNo
             },
             dataType : 'json',
             success : function(resp1) {
                 if (resp1.status=="OK") {
                     if (resp1.status=="OK") {
                         Swal.fire({
                             title: '댓글 수정 완료',
                             text: "댓글을 수정 했습니다",
                             icon: 'success',
                             showCancelButton: false,
                             confirmButtonColor: '#90c322',
                             confirmButtonText: '확인'
                           }).then((result) => {
                               if (result.value) {
                                   window.location.reload();
                                 }
                          });
                 } else {
                     alert("수정 실패");
                 }
             }
             },
             error:function(request,status,error){
                  console.log("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
          }
         });
    });
    
    

    
    

    

</script>
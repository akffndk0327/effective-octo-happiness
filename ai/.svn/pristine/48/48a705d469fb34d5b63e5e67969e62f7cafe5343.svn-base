<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"  %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<link href="${cPath }/css/recipeList.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" href="${cPath }/sweertalert/sweetalert2.min.css">
<script src="${cPath}/sweertalert/sweetalert2.min.js"></script>

<%--
* [[개정이력(Modification Information)]]
* 수정일                 수정자      수정내용
*
* ----------  ---------  -----------------
* 2019. 11. 4.      이유진      최초작성
* Copyright (c) 2019 by DDIT All right reserved
 --%>

<style>
    <c:set var="recipeList" value="${pagingVO.dataList }" />
    <c:choose>
        <c:when test="${not empty recipeList }">
            <c:forEach items="${recipeList }" var="recipe" varStatus="status">
                <c:if test="${fn:contains(recipe,'/ai/') }">
                    #img${status.index}, #imgs${status.index} {
                        background-image: url('http://localhost${recipe.recipeContent }');
                        color:red;
                    }
                </c:if>

                <c:if test="${fn:contains(recipe,'https:') }">
                    #img${status.index}, #imgs${status.index} {  
                        background-image: url('${recipe.recipeContent }');
                        color:red;
                    }
                </c:if>
                
                <c:if test="${fn:contains(recipe,'http:') }">
                    #img${status.index}, #imgs${status.index} {  
                        background-image: url('${recipe.recipeContent }');
                        color:red;
                    }
                </c:if>
                
                <c:if test="${fn:contains(recipe,'data:') }">
                    #img${status.index}, #imgs${status.index} {  
                        background-image: url('${recipe.recipeContent }');
                        color:red;
                    }
                </c:if>
            </c:forEach>
        </c:when>
    </c:choose>
    .card{width:200px;}
    span
    {
    font-size: 25px;
    }
</style> 

<div id="InnerContainer">
<h2 class="titleTopBar">레시피 게시판</h2>
<!-- 새글쓰기 위에 놓기 -->
<%-- <c:if test="${not empty loginId }"> --%>
<!--     <div style="width:100%;height:40px;"> -->
<%--         <input type="button" class="btn btnAi0" value="새글쓰기" onclick="location.href='<c:url value="/recipe/recipeInsert.do"/>';" /> --%>
<!--     </div> -->
<%-- </c:if> --%>

<div class="cards" id="center">
    <c:set var="recipeList" value="${pagingVO.dataList }" />
    <c:set var="reType" value="${reType }" />
        <c:choose>
            <c:when test="${not empty recipeList }">
                <c:forEach items="${recipeList }" var="recipe" varStatus="status">
                    <article class="card card--1" )>
                        <div class="card__info-hover">
                            <div class="card__like"  viewBox="0 0 24 24">
                                <c:choose>
                                    <c:when test="${recipe.like eq 'Y' }">
                                        <img src="<c:url value="/images/heart.png" />"style="height:20px;">
                                    </c:when>
                                    <c:otherwise>
                                        <img src="<c:url value="/images/beanheart.png"/>"style="height:20px;">
                                    </c:otherwise>
                                </c:choose>
                            </div>
                        </div>

                        <div class="card__img" id="img${status.index}"></div>
                        <div class="card__img--hover" id="imgs${status.index}"></div>

                        <div class="card__info">
                            <span class="card__category">${recipe.reType.recipeTypenm }</span>
                            <span class="card__by">by <a href="#" class="card__author" title="author"> ${recipe.memId }</a></span>
                            <h3 class="card__title"><a href="${cPath}/recipe/recipeView.do?what=${recipe.recipeNo }">${recipe.recipeTitle }</a></h3>
                        </div>
                    </article> 
                </c:forEach>
            </c:when>
            <c:otherwise>
                조건에 맞는 글이 없음.
            </c:otherwise>
        </c:choose>
    </div>

    <!-- 새글쓰기 아래에 놓기 -->
    <c:if test="${not empty loginId }">
        <div style="width:100%;height:40px;">
            <input type="button" class="btn btnAi0" value="새글쓰기" onclick="location.href='<c:url value="/recipe/recipeInsert.do"/>';" />
        </div>
    </c:if>
    
    <div style="text-align:center;">
        <div id="pagingArea">${pagingVO.pagingHTML }</div>
    </div>

    <div style="width:100%;text-align:center;">
        <form action="?" id="pageForm" class="form-inline justify-content-center mb-3">
            <input type="hidden" name="page" />
        </form>
        <form action="${cPath}/recipe/recipeSearch.do" id="searchForm" class="form-inline justify-content-center mb-3" style="margin-bottom:10px;">
            <input type="hidden" name="page" />
            
            <select name="searchType" class="form-control mr-2" style="width:10%;height:40px;">
                <option value="">전체</option>
                <c:forEach items="${reType }" var="recipe">
                    <option value="${recipe.recipeType }">${recipe.recipeTypenm }</option>
                </c:forEach>
            </select>

            <input type="text" class="form-control mr-2" name="searchWord" style="width:50%;height:26px;"/>

            <div style="display:inline-block;position:relative;top:15px;">
                <input type="submit" class="btn btnAi0" value="검색" />
            </div>
       </form>
    </div>
</div>

<script type="text/javascript">
        var listBody = $("#listBody");
        var pagingArea = $("#pagingArea");
        var pageTag = $("[name='page']");

        listBody.on("click", "a", function() {
            let bono = $(this).data("bono");
            location.href = "${cPath}/recipe/recipeView.do?what=" + bono;
        });

        pageTag.val("1");

        pagingArea.on("click", "a", function(event) {
            event.preventDefault();
            let page = $(this).data("page");
            if (page < 1)
                return false;
            pageTag.val(page);
            pageForm.submit();
            return false;
        });
        
//         $('#searchForm').on("submit", function(event){
//             event.preventDefault();
//             var action = $(this).attr("action");
//             var method = $(this).attr("method");
//             var queryString = $(this).serialize();
//             $.ajax({
//                 url : action,
//                 method :method,
//                 data : queryString,
//                 dataType : "json", // REST 방식
//                 success : function(resp) {
//                     alert(resp);
//                     //window.history.pushState(resp, "boradList", "?"+queryString);
//                     //makeUI(resp);
//                 },
//                 error : function(errorResp) {
//                     console.log(errorResp.status);
//                 }
        
//             });
//             return false;
//         });
    </script>
    

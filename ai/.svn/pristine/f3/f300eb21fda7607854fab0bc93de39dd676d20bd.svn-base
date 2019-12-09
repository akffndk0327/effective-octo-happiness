<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%--
* [[개정이력(Modification Information)]]
* 수정일                 수정자      수정내용
*
* ----------  ---------  -----------------
* 2019. 11. 6.      이유진      최초작성
* 2019. 11. 14      이유진     list띄우기
* Copyright (c) 2019 by DDIT All right reserved
 --%>


<style type="text/css">
    .table thead tr th:nth-child(1) {
        color: white;
        font-weight: 100;
        vertical-align: middle;
        background-color: #2f4f4f;
        width: 50%;
        font-size: 16px;
        text-align:center;
    }

    .table thead tr th:nth-child(2) {
        color: white;
        font-weight: 100;
        vertical-align: middle;
        background-color: #2f4f4f;
        width: 35%;
        font-size: 16px;
        text-align:center;
    }

    .table thead tr th:nth-child(3) {
        color: white;
        font-weight: 100;
        vertical-align: middle;
        background-color: #2f4f4f;
        width: 25%;
        font-size: 16px;
        text-align:center;
    }

    .table thead>tr>th {
        border-top:2px solid #ddd;
        border-bottom:2px solid #ddd;
    }

    .table tbody>tr>td{
        cursor:pointer;
    }

</style>

<jsp:useBean id="now" class="java.util.Date" />

<fmt:formatDate value="${now}" pattern="yyyy-MM-dd HH:mm:ss" var="today" />

<div id="InnerContainer">
    <div id="demo" style="height:500px; ">
        <h2 class="titleTopBar">설문 게시판</h2><br><br>

        <c:set value="${pagingVO.dataList }" var="surveyList"/>
        <div class="table-responsive-vertical shadow-z-1">
            <table class="table table-hover table-mc-light-blue" 
            style="width: 100%; margin: auto; border: 1px solid #ddd;">
                <thead class="listTHead">
                    <tr>
                        <th>설문지이름</th>
                        <th>설문지 진행일</th>
                        <th>설문지 진행여부</th>
                    </tr>
                </thead>
                <tbody id="listBody" class="listTBody">
                    <c:forEach items="${surveyList }" var="survey">
                        <tr id="${survey.surId}">
                            <td  style="text-align: center">
                                ${survey.subName}
                            </td>
                            <td style="text-align:center;">
                                ${survey.surIndate}&nbsp;&nbsp;~&nbsp;&nbsp;${survey.surEnddate}
                            </td>
                            <td class="today" style="text-align:center;">
                                <fmt:parseDate value="${survey.surIndate }" var="surIndate" pattern="yyyy-MM-dd"/>
                                <fmt:parseDate value="${survey.surEnddate }" var="surEnddate" pattern="yyyy-MM-dd"/>

                                 <c:if test="${today le survey.surEnddate}">진행중</c:if>
                                 <c:if test="${today gt survey.surEnddate}">종료</c:if>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
                <tfoot>
                    <tr>
                        <td colspan="4" style="border:solid 1px white;">
                            <div id="pagingArea" style="text-align:center;">${pagingVO.pagingHTML }</div>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="4" style="border:solid 1px white;">
                        <c:if test="${not empty authorId }">
                            <c:if test="${authorId eq 'ROLE_ADMIN' }">
                                <input type="button" class="btn btnAi0 jg" value="새 설문조사 만들기" onclick="location.href='${cPath}/survey/surveyInsert.do';" style="font-size:15px; width: 150px;">
                            </c:if>
                        </c:if>   
                        </td>
                    </tr>
                </tfoot>
            </table>

            <table>
                <tr id="search">
                    <td colspan="4">
                        <form action="?" id="searchForm" class="form-inline justify-content-center mb-3">
                            <input type="hidden" name="page" />
                        </form>
                    </td>
                </tr>
            </table>
        </div>
    </div>
</div>

<script type="text/javascript">
    var listBody   = $("#listBody");
    var pagingArea = $("#pagingArea");
    var searchForm = $("#searchForm");
    var pageTag    = $("[name='page']");

    listBody.on("click", "tr", function() {
        let value = $(this).attr("id");
        location.href = "${cPath}/survey/surveyView.do?surId="+ value;
    });

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
</script>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<%--
* [[개정이력(Modification Information)]]
* 수정일                 수정자      수정내용
*
* ----------  ---------  -----------------
* 2019. 11. 16.      이유진      최초작성
* Copyright (c) 2019 by DDIT All right reserved
 --%>
<link href="http://fonts.googleapis.com/earlyaccess/jejugothic.css" rel="stylesheet">
<link href="http://fonts.googleapis.com/earlyaccess/hanna.css" rel="stylesheet">

<script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.7.2/Chart.min.js"></script>
<script src="https://d3js.org/d3.v3.min.js"></script>
<script src="https://rawgit.com/jasondavies/d3-cloud/master/build/d3.layout.cloud.js" type="text/JavaScript"></script>

<style type="text/css">
    .table-bordered>thead>tr>th, .table-bordered>tbody>tr>th, .table-bordered>tfoot>tr>th, .table-bordered>thead>tr>td, .table-bordered>tbody>tr>td, .table-bordered>tfoot>tr>td {
        font-family: 'Jeju Gothic', sans-serif;
    }

    .jg{font-family: 'Jeju Gothic', sans-serif;}
    .hn{font-family: 'Hanna', sans-serif;}
</style>


<div id="InnerContainer">
<h2 class="titleTopBar">설문조사 통계</h2>
    <c:if test="${empty SubName }">
        <h5>설문조사 응답이 등록되지 않았습니다</h5>
        <input type="button" value="응답하기" onclick="history.back();" class="btn btnAi2">
        <input type="button" value="목록으로" onclick="location.href='${cPath}/survey/surveyList.do'" class="btn btnAi2">
    </c:if>
   <c:if test="${not empty SubName }">
    
<div class="titleLeftBar jg">${SubName }</div>
<div style="margin:50px;">
    <table class="table table-bordered" style="border:solid 1px white;">
        <c:forEach var="quest" items="${quest}" varStatus="i">
            <tr>
                <td style="border:solid 1px white;">
                    <!-- 질문내용 -->
                    <span style="font-size: 1.49em; font-weight: bold;">*&nbsp;&nbsp;${quest.questCont }</span>
                </td>
            </tr>
            <tr>
                <td  style="border:solid 1px white;">
                    <!--객관식 -->
                    <c:if test="${quest.questType eq 'example' }">
                        <div class="example" style="margin-left: 20px">
                            <div class="canvas">
                                <canvas id="${quest.questId }" height="200px" width="600px"></canvas>
                            </div>
                        </div>
                    </c:if>

                    <!-- 주관식 -->
                    <c:if test="${quest.questType eq 'token' }">
                        <div class="token">
                            <div style="float:left; width: 80%; margin-left: 300px;">
                            <c:forEach items="${quest.plainTokenResult}" var="plainToken" varStatus="s" >
                                <c:choose>
                                    <c:when test="${s.last and s.index eq 4}">
                                        <c:set var="createCount" value="1"/>
                                    </c:when>
                                    <c:otherwise>
                                        <c:set var="createCount" value="${(fn:length(quest.plainTokenResult) / 5)+1}"/>
                                    </c:otherwise>
                                </c:choose>
                            </c:forEach>

                            <c:choose>
                                <c:when test="${createCount eq 1}">
                                    <table class="table table-bordered" style="width:50%;">
                                        <c:forEach items="${quest.plainTokenResult}" var="plainToken" varStatus="status1" >
                                            <tr>
                                                <td>
                                                    ${plainToken}
                                                </td>
                                            </tr>
                                        </c:forEach>
                                    </table>
                                </c:when>
                                <c:otherwise>
                                    <table class="table table-bordered" style="width:50%;height:100px;">
                                        <input type="hidden" id="currentPage${i.index}" value="1"/>
                                        <input type="hidden" id="lastPage${i.index}" value="${createCount-(createCount%1)}"/>

                                        <c:forEach items="${quest.plainTokenResult}" var="plainToken" varStatus="status2" >
                                            <c:choose>
                                                <c:when test="${status2.index eq 0 or status2.index eq 1 or status2.index eq 2 or status2.index eq 3 or status2.index eq 4}">
                                                    <tr id="show_${i.index}_${status2.index + 1}">
                                                        <td>
                                                            ${plainToken}
                                                        </td>
                                                    </tr>
                                                </c:when>
                                                <c:otherwise>
                                                    <tr id="show_${i.index}_${status2.index + 1}" style="display:none;">
                                                        <td>
                                                            ${plainToken}
                                                        </td>
                                                    </tr>
                                                </c:otherwise>
                                            </c:choose>
                                        </c:forEach>
                                        <tr>
                                            <td>
                                                <input id="btnPrev${i.index}" type="button" class="btn btnAi0" value="◀" onclick="prevPage('${i.index}')" style="float:left;">
                                                <input id="btnNext${i.index}" type="button" class="btn btnAi0" value="▶" onclick="nextPage('${i.index}')">
                                            </td>
                                        </tr>
                                    </table>
                                </c:otherwise>
                            </c:choose>
                        </div>
                    </c:if>
                </td>
            </tr>
        </c:forEach>
    </table>
                            </div>
    </c:if>
    <input type="button" class="btn btnAi0" value="목록으로" onclick="location.href ='${cPath}/survey/surveyList.do'">
</div>
    <br><br><br>
</div>

<script>
    $(document).ready(function() {
        $("input[id^='btnPrev']").hide();

        //객관식 그래프
        <c:forEach items="${quest }" var="quest">
        <c:if test="${quest.questType eq 'example' }">
        //도넛그래프
        var data = {
            labels : [
                <c:forEach items="${quest.exampleList }" var="exam" varStatus="i">
                   <c:if test="${!i.last }">
                     "${exam.examCont}",
                  </c:if>

                   <c:if test="${i.last }">
                      "${exam.examCont}"
                   </c:if>
                </c:forEach>
                ],
            datasets : [ {
                data : [
                    <c:forEach items="${quest.exampleList }" var="exam" varStatus="i">
                       <c:if test="${!i.last }">
                         "${exam.checkCount}",
                      </c:if>

                       <c:if test="${i.last }">
                          "${exam.checkCount}"
                       </c:if>
                    </c:forEach>
                ],
                backgroundColor : [ "#BFD0E6", "#798F8C", "#E9CBD1" ]

            } ]
        };

        new Chart(document.getElementById("${quest.questId}"), {
            type : 'doughnut',
            data : data,
            options : {
                title : {
                    text : '${quest.questCont}',
//                  display : true,
                    fontSize : 30
                }
            }
        });
        </c:if>

        //주관식
        <c:if test="${quest.questType eq 'token' }">
//                $(".token").append("${quest.plainTokenResult}");
        </c:if>
        </c:forEach>


    })//끝

    function prevPage(idx) {
        $("#currentPage"+idx).val(parseInt($("#currentPage"+idx).val())-1);

        var currentPage = $("#currentPage"+idx).val();
        var lastPage    = parseInt($("#lastPage"+idx).val());

        if(currentPage == 1) {
            $("#btnPrev"+idx).hide();
            $("#btnNext"+idx).show();
         }

        for(var i=1; i<16; i++){
            $("#show_"+idx+"_"+i).hide();

            if(currentPage == 1) {
                if(i<6) $("#show_"+idx+"_"+i).show();
            } else if(currentPage == 2) {
                if(i>5 && i<11) $("#show_"+idx+"_"+i).show();
            } else if(currentPage == 3) {
                if(i>10 && i<16) $("#show_"+idx+"_"+i).show();
            }
        }
    }

    function nextPage(idx) {
        $("#currentPage"+idx).val(parseInt($("#currentPage"+idx).val())+1);

        var currentPage = $("#currentPage"+idx).val();
        var lastPage    = parseInt($("#lastPage"+idx).val());

        if(currentPage < lastPage) {
           $("#btnNext"+idx).show();
        }

        if(currentPage == lastPage) {
            $("#btnPrev"+idx).show();
            $("#btnNext"+idx).hide();
        }

        for(var i=1; i<16; i++){
            $("#show_"+idx+"_"+i).hide();

            if(currentPage == 1) {
                if(i<6) $("#show_"+idx+"_"+i).show();
            } else if(currentPage == 2) {
                if(i>5 && i<11) $("#show_"+idx+"_"+i).show();
            } else if(currentPage == 3) {
                if(i>10 && i<16) $("#show_"+idx+"_"+i).show();
            }
        }
    }
</script>

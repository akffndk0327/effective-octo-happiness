<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%--
* [[개정이력(Modification Information)]]
* 수정일                 수정자      수정내용
*
* ----------  ---------  -----------------
* 2019. 11. 6.      이유진      최초작성
* 2019. 11. 14.      이유진      상세보기 수정
* Copyright (c) 2019 by DDIT All right reserved
 --%>
<link href="http://fonts.googleapis.com/earlyaccess/jejugothic.css" rel="stylesheet">
<link href="http://fonts.googleapis.com/earlyaccess/hanna.css" rel="stylesheet">



<div id="InnerContainer">
    <c:url value="/survey/surveyResult.do" var="ResultURL">
        <c:param name="sur" value="${survey.surId}" />
    </c:url>

    <c:if test="${not empty authorId }">
        <c:if test="${authorId eq 'ROLE_ADMIN' }">
            <input type="button" class="btn btnAi1 jg" value="삭제" id="delete">
        </c:if>
    </c:if>

    <h2 class="titleTopBar">설문조사 참여</h2>

    <div class="titleLeftBar jg" style="font-size: 30px">${survey.subName}</div>

    <jsp:useBean id="now" class="java.util.Date" />
    <fmt:formatDate value="${now}" pattern="yyyy-MM-dd" var="today" />
    <fmt:parseDate value="${survey.surIndate}"   var="surIndate"   pattern="yyyy-MM-dd"/>
    <fmt:parseDate value="${survey.surEnddate}"  var="surEnddate" pattern="yyyy-MM-dd"/>


    <c:choose>
        <c:when test="${now gt surEnddate}">
            <div style="text-align: center;">
	        <h2>설문조사 기간 종료</h2>
	        <h6>종료된 설문조사 입니다. 참여해주셔서 감사합니다</h6>
	        <input type="button" class="btn btnAi0" value="목록으로" onclick="location.href ='${cPath}/survey/surveyList.do'">
            <input type="button" class="btn btnAi0" value="통계보기" onclick="location.href='${ResultURL}';" style="float: right;">
	    </div>
        </c:when>
        <c:otherwise>
            <table class="table" style="margin: 30px;">
                <tr class="Default">
                    <td><p style="margin: 50px; font-size: 20px;">${survey.surLead }</p><td>
                </tr>

                <form id="SurveyResultVO" name="SurveyResultVO" action="${cPath }/survey/surveyReponse.do" method="post">
                    <c:forEach items="${survey.questionList }" var="question" varStatus="v">

                        <tr class="success"> <!-- 질문 -->
                            <td><p style="margin: 20px; font-size: 25px;">*&nbsp;&nbsp;&nbsp; ${question.questCont   }</p>
                               <input type="hidden" name="list[${v.index}].questId" value="${question.questId }">
                               <input type="hidden" name="list[${v.index}].surId" value="${question.surveyId }">

                        <c:if test="${question.questType eq 'token'}"> <!-- 주관식일때 -->
                               <input type="text" name="list[${v.index}].tokenResult" required class="form-control" style="width:70%;margin: 20px; font-size: 30px;"></td>
                           </tr>
                        </c:if>

                        <c:if test="${question.questType eq 'example'}"> <!-- 객관식일때-->
                                <p class="radio-group" style="font-size: 20px;margin: 20px;">
                                    <c:forEach items="${question.exampleList }" var="example" varStatus="ee"> <!-- 답변 for문 -->
                                    <c:if test="${ee.index eq 0 }">
                                        <input type="radio" checked style="margin: 10px;width: 20px;height: 20px;" value="${example.examId }" name="${example.questionId }" required /> ${example.examCont}<br>
                                    </c:if>
                                    <c:if test="${ee.index ne 0 }">
                                        <input type="radio" style="margin: 10px;width: 20px;height: 20px;" value="${example.examId }" name="${example.questionId }" required /> ${example.examCont}<br>
                                    </c:if>
                                    </c:forEach>
                                        <input type="hidden" name="list[${v.index}].examId" class="example">
                                </p>
                             </td>
                           </tr>
                        </c:if>
                    </c:forEach>
                    <tr>
                        <td>
                            <input type="button" class="btn btnAi0" value="제출" id="gogo">
                            <input type="button" class="btn btnAi1" value="취소" onclick="history.back();">
                        </td>
                    </tr>
                </form>
            </table>
        </c:otherwise>
    </c:choose>
</div>

<script>
      $('#gogo').on("click",function(){

        var radio_name = [];//array
        var raido_name_val = {};//object
        var radio = $("input[type=radio]"); //라디오 정보를 가져옵니다.
        console.log(radio);


        $.each(radio, function (key, value) { // input radio의 name 값을 가져옵니다.
            if($(value).prop("checked")){
                $(this).siblings('.example').val($(this).val());
            }
        });

        $('#SurveyResultVO').submit();

      })

       $('#delete').on("click",function(){
           var surId = "${survey.surId}";
           Swal.fire({
               title: '정말 삭제하시겠습니까?',
               html: "설문조사의 질문과 답변, 통계가 <br> 모두 삭제 되고  복구 될 수 없습니다",
               icon: 'warning',
               showCancelButton: true,
               confirmButtonColor: '#90c322',
               confirmButtonText: '확인'
             }).then((result) => {
                 if (result.value) {
                    $.ajax({
                        url :"${cPath}/survey/surveyDelete.do",
                        method :"get",
                        data : {"surId":surId},
                        dataType : "json",
                        success : function(resp) {
                            console.log(resp);
                            if(resp.status=='OK'){

                             Swal.fire({
                                   title: '삭제 완료',
                                   text: "해당 설문조사가 삭제 되었습니다",
                                   icon: 'success',
                                   showCancelButton: false,
                                   confirmButtonColor: '#3085d6',
                                   confirmButtonText: '확인'
                                 }).then((result) => {
                                   if (result.value) {
                                      window.location.href = '${cPath}/survey/surveyList.do';
                                   }
                        });
                            }
                        },
                         error:function(request,status,error){
                                console.log("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
                        }
                    });
                   }
            });
       });

</script>

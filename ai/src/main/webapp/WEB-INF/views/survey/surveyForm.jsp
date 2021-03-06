<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
* [[개정이력(Modification Information)]]
* 수정일                 수정자      수정내용
*
* ----------  ---------  -----------------
* 2019. 11. 6.      이유진      최초작성
* Copyright (c) 2019 by DDIT All right reserved
 --%>

<link href="http://fonts.googleapis.com/earlyaccess/jejugothic.css" rel="stylesheet">
<link href="http://fonts.googleapis.com/earlyaccess/hanna.css" rel="stylesheet">

<style type="text/css">
    .table tr td:nth-child(1) {
        color: black;
        font-weight: 100;
        vertical-align: middle;
        background-color:#e6e6e6;
        padding-left:20px;
        width:20%;
        font-size:20px;
        font-family: 'Jeju Gothic', sans-serif;
    }

    .table-bordered>thead>tr>th, .table-bordered>tbody>tr>th, .table-bordered>tfoot>tr>th, .table-bordered>thead>tr>td, .table-bordered>tbody>tr>td, .table-bordered>tfoot>tr>td {
        border:solid 1px #bfb8b8;
        font-family: 'Jeju Gothic', sans-serif;
    }

    .form-control {
        height:25px;
    }

    button.close {
        position:relative;
        top:-40px;
    }

    .tdToken {
        background-color:white;
    }

input[type=text], input[type=date] { 
    font-size: 18px;
}
</style>


<div id="InnerContainer">
    <form id="surveyVO" name="surveyVO" action="${cPath }/survey/surveyInsert.do" method="post">
        <table class="table table-bordered">
            <tr>
                <td>제목</td>
                <td>
                    <input type="text"  name="subName" class="form-control" placeholder="설문조사 제목" required style="width: 95%;">
                </td>
            </tr>
            <tr>
                <td>설문조사 기간</td>
                <td>
                    <input type="date" min="2019-12-06" name="surIndate"  class="form-control" style="width:160px;display:inline-block;text-align:center;" required >
                    &nbsp;~&nbsp;
                     <input type="date" min="2019-12-06" name="surEnddate"  class="form-control" style="width:160px;display:inline-block;text-align:center;" required>
                </td>
            </tr>
            <tr>
                <td>목적</td>
                <td>
                     <input type="text"  name="surPurpose"  class="form-control" placeholder="설문조사 목적" required style="width: 95%;">
                </td>
            </tr>
            <tr>
                <td>머리글</td>
                <td>
                    <textarea class="form-control" name="surLead" style="width: 95%; height: 100px; font-size: 18px;" required name="surLead" placeholder="설문조사 머리글"></textarea>
                </td>
            </tr>
            <tr>
                <td>내용</td>
                <td>
                     <input type="text"  name="surContent"  class="form-control" placeholder="설문조사 내용" required style="width: 95%;">
                </td>
            </tr>
            <tr>
                <td>질문</td>
                <td>
                <div id="inputQuest"></div>
                     <input type="button"  data-toggle="modal" data-target="#questionModal" class="btn btnAi2" value="주관식" style="font-size:15px;">
                     <input type="button"   data-toggle="modal" data-target="#exampleModal" class="btn btnAi2" value="객관식" style="font-size:15px;">
                </td>
            </tr>
        </table>

        <div id="btn">
           <input type="button" value="취소" class="btn btnAi1">
           <input type="submit" value="저장" class="btn btnAi0">
        </div>
        <br><br><br>
    </form>
</div>

<!-- 주관식 모달  -->
<div class="modal fade" id="questionModal" tabindex="-1" role="dialog" aria-labelledby="ModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">주관식 질문 등록</h4>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <form>
                    <table>
                        <tr>
                            <th class="jg">질문 입력 :</th>
                            <td class="jg" style="width:80%;">
                                <input type="text" style="width: 400px; height: 30px; font-size: 18px;" class="form-control Equest" required placeholder="질문을 입력해주세요">
                            </td>
                        </tr>
                    </table>
                </form>
            </div>
            <div class="modal-footer" id="aa">
                <button type="button" class="btn btn-primary add jg"     style="font-size:15px;">추가하기</button>
                <button type="button" class="btn btn-primary TmodiTo jg" style="font-size:15px;">수정하기</button>
                <button type="button" id="Tclose" class="btn btn-primary jg" data-dismiss="modal" style="font-size:15px;">Close</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->

<!-- 객관식 모달 -->
<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">객관식 질문 등록</h4>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <form>
                    <table>
                        <tr>
                            <th class="jg">질문 입력 :</th>
                            <td class="jg">
                                <input type="text" class="form-control quest" required placeholder="질문을 입력해주세요" 
                                style="width:300px; height: 30px; font-size: 18px;" >
                            </td>
                            <td>
                                <button id="addQuestion" class="btn btn-primary jg" type="button" data-toggle="collapse" data-target="#collapseExample" aria-expanded="false" aria-controls="collapseExample"  style="font-size:15px;">
                                    문항 추가
                                </button>
                            </td>
                        </tr>
                        <tr>
                            <th class="jg">답변 문항 : </th>
                            <td>
                                <div id="eQuestion" class="jg"></div>
                            </td>
                        </tr>
                    </table>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary addTo jg"  style="font-size:15px;">추가하기</button>
                <button type="button" class="btn btn-primary modiTo jg" style="font-size:15px;">수정하기</button>
                <button type="button" class="btn btn-primary jg" id="close" data-dismiss="modal" style="font-size:15px;">Close</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->


<script type="text/javascript">
    //객관식
    var eQuestion=$('#eQuestion');
    var addQuestion=$('#addQuestion');

    //주관식
    var token=$('#token');
    var addToken=$('#addToken');

    var i=0;
    var j=0;

    $(document).ready(function(){
        $(".TmodiTo").hide();
        $(".modiTo").hide();
    });

<%-- 객관식 --%>
    <%-- 객관식 질문 추가 --%>
    $('.addTo').on("click",function(){
        var quest=$('.quest').val();

        const answer = [];
        var contentDiv = document.getElementsByClassName("answer");

        for(let i=0; i<contentDiv.length; i++){
            answer.push(contentDiv[i].children[0].innerText);
        }

        $('#inputQuest').append( '<table>'
                               + '<tr><td style="background-color:white;width:1%;">질문 : </td>'
                               + '<td class="tdQuest" style="background-color:white;width:890px;text-align:left;">'
                               + quest
                               + '</td></tr>'
                               + '<tr><td style="background-color:white;width:1%;">답변 : </td>'
                               + '<td class="tdAnswer" style="background-color:white;"> '
                               + answer
                               + '</td></tr>'
                               + '<tr><td colspan="2" style="background-color:white;">'
                               + '<input type="button" value="삭제" class="btn btnAi1 deleteQuest" style="float:right; padding: 5px 10px;margin:3px;width: 50px;font-size: 14px; height: 30px;">'
                               + '<input type="button" value="수정" class="btn btnAi1 modiQuest" style="float:right; padding: 5px 10px;margin:3px;width: 50px;font-size: 14px; height: 30px;">'
                               + '</td></tr>'
                               + '<input type="hidden" name="questionList['+i+'].questCont" value="'+quest+'">'                //질문
                               + '<input type="hidden" name="questionList['+i+'].questType" value="example">'                  //타입
                               + '<input type="hidden" name="questionList['+i+'].exampleList[0].examCont" value="'+answer+'">' //대답
                               + '</table><br>'
        );

        $('#exampleModal').find('form')[0].reset();
        $('#exampleModal').find('#eQuestion').empty();
        $('#exampleModal').modal('hide');
        i++;
    });

    <%-- 객관식 문항 추가 버튼 클릭 이벤트 --%>
    addQuestion.on("click",function(){
        eQuestion.append('<div class="collapse" id="collapseExample">'
                        + '<img src="${pageContext.request.contextPath }/images/checkbox-blue.png" width="25px;">'
                        + '<input type="text" id="eInput" class="form-control eInput" placeholder="문항을 입력해주세요" required>'
                        + '<input type="text"  class="btn btn-warning  eCreate" value="등록" style="width: 100px;">'
                        + '</div>'
                        );
    });

    <%-- 객관식 문항 등록 버튼 클릭 이벤트 --%>
    $(document).on("click",'.eCreate',function(){
        var eInput=$('.eInput').val();

        eQuestion.append( '<div class="answer">'
                        + '<span class="a" style="font-size: 20px;">'
                        + '&nbsp;&nbsp;'+eInput
                        + '</span>'
                        + '<button type="button" class="btn btn-danger btn-sm eDelete" style="margin: 15px">X</button>'
                        + '</div>'
        )
        var parent=$(this).closest("div");

        parent.remove();
    })

    <%-- 객관식 삭제 버튼 클릭 이벤트 --%>
    $(document).on("click",'.deleteQuest',function(){
        $(this).closest("table").remove();
    });

    <%-- 객관식 문항 삭제 버튼 클릭 이벤트 --%>
    $(document).on("click",'.eDelete',function(){
        $(this).closest("div").remove();
    });

    <%-- 객관식 수정 버튼 클릭 이벤트 --%>
    $(document).on("click",'.modiQuest',function(){
        var tdQuest  = $(this).closest('table').find('.tdQuest').text();
        var tdAnswer = $(this).closest('table').find('.tdAnswer').text();

        if(tdQuest != null && tdQuest != "") {
            $('#exampleModal').modal('show');

            const answer=tdAnswer.split(",");

            <%-- 질문 값 설정 --%>
            $('.quest').val(tdQuest);

            <%-- 보기 값 설정 --%>
            var divCount = $(eQuestion).find(".answer").length;
            if(divCount < 1) {
                for(var i=0; i<answer.length; i++){
                    eQuestion.append( '<div class="answer">'
                                    + '<span class="a" style="font-size: 20px;">'
                                    + answer[i]
                                    + '</span>'
                                    + '<button type="button" class="btn btn-danger btn-sm eDelete" style="margin: 15px">X</button>'
                                    + '</div>'
                               );
                }
            }

            $(this).attr("id","checkId");
            $('.addTo').hide();
            $(".modiTo").show();
        }
    });

    <%-- 객관식 질문/문항 수정 데이터 반영 --%>
    $(document).on("click",'.modiTo',function(){
        var quest=$('.quest').val();

        <%-- 객관식 질문 등록여부 확인 --%>
        if(quest == "" || quest == null) {
            alert("수정될 질문을 입력해주세요.");
            return false;
        }

        <%-- 객관식 문항 등록여부 확인(선택해야하기 때문에 2개 이상 작성해야 다음으로 넘어갈 수 있다.) --%>
        var contentDiv = document.getElementsByClassName("answer");
        if(contentDiv.length < 2) {
            alert("문항을 2개 이상 입력해주세요.");
            return false;
        }

        const answer = [];
        for(let i=0; i<contentDiv.length; i++){
            answer.push(contentDiv[i].children[0].innerText);
        }

        $('#checkId').closest('table').find('.tdQuest').text(quest);
        $('#checkId').closest('table').find('.tdAnswer').text(answer);

        $('#exampleModal').find('form')[0].reset();
        $('#exampleModal').find('#eQuestion').empty();

        $('#checkId').attr("id","")
        $('.modiTo').hide();
        $('.addTo').show();

        <%-- 수정 후 모달창 닫기 --%>
        $('#exampleModal').modal('hide');
    });





<%-- 주관식 --%>
    <%-- 주관식 등록 버튼 클릭 이벤트 --%>
    $('.add').on("click",function(){
        var quest=$('.Equest').val();

        $('#inputQuest').append(
        '<table><tr>'+
            '<td style="background-color:white;width:1%;">질문 : </td>'+
            '<td class="tdToken" style="background-color:white;width:890px;text-align:left;">'+quest+'</td></tr>'+
            '<input type="hidden" name="questionList['+i+'].questCont" value="'+quest+'">'+ //질문
            '<input type="hidden" name="questionList['+i+'].questType" value="token">'+ //타입

            '<tr><td colspan="2" style="background-color:white;">'+
            '<input type="button" value="삭제" class="btn btnAi1 btn-xs deleteToken" style="float:right; padding: 5px 10px;margin:3px;width: 50px;font-size: 14px; height: 30px;">'+
            '<input type="button" value="수정" class="btn btnAi1 btn-xs modiToken" style="float:right; padding: 5px 10px;margin:3px;width: 50px;font-size: 14px; height: 30px;">'+
            '</td></tr>'+
            '</table><br>'
        );

        $('#questionModal').find('form')[0].reset();
        $('#questionModal').modal('hide');
        i++;
    });

    <%-- 주관식 삭제 버튼 클릭 이벤트 --%>
    $(document).on("click",'.deleteToken',function(){
        $(this).closest("table").remove();
    });

    <%-- 주관식 수정 버튼 클릭 이벤트 --%>
    $(document).on("click",'.modiToken',function(){
        var tdToken=$(this).closest('table').find('.tdToken').text();

        if(tdToken != null && tdToken != "") {
            $('#questionModal').modal('show');

            $('.add').hide();
            $(".TmodiTo").show();
            $('.Equest').val(tdToken);
            $(this).attr("id","TcheckId");
        }
    });

    <%-- 주관식 질문 수정 데이터 반영 --%>
    $(document).on("click",'.TmodiTo',function(){
        var quest=$('.Equest').val();

        <%-- 주관식 질문 등록여부 확인 --%>
        if(quest == "" || quest == null){
            alert("수정될 질문을 입력해주세요.");
            return false;
        }

        $('#TcheckId').closest('table').find('.tdToken').text(quest);
        $('#questionModal').find('form')[0].reset();
        $('#questionModal').find('#eQuestion').empty();
        $('#TcheckId').attr("id","");
        $('.TmodiTo').hide();
        $('.add').show();

        <%-- 수정 후 모달창 닫기 --%>
        $('#questionModal').modal('hide');
    });

</script>
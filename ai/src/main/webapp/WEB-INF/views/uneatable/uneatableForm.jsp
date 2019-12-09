<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
<%--
* [[개정이력(Modification Information)]]
* 수정일                 수정자      수정내용
* 2019. 11. 8       이진희      폼작성
* ----------  ---------  -----------------
* 2019. 11. 6.      이진희      최초작성
* Copyright (c) 2019 by DDIT All right reserved
 --%>
<!-- 다음 우편주소 api -->
<script src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<style>
	#buttons{
		margin-top:50px;
	
	}
	
	.search {
	width:101px;
	height:44px;
	background: darkslategray;
	color: #fff;
	font-size: 19px;
	margin :5px;
	font-weight: bold;
	border: 1px solid darkslategray;
}
	
	
</style>
 
<div id="InnerContainer">
<form id="uneatForm" method="post" >
<h4>제품명 : <input type="text" name="prdtnm" value="${uneatable.prdtnm }"/></h4>
<table class="table table-bordered">
    <tr>
      <td>제조일자</td>
      <td>
	  <input type="text" name="mnfdt" value="${uneatable.mnfdt }" id="datepicker" data-position="right top" /></td>
    </tr>
    <tr>
      <td>유통기한</td>
      <td>
      <input type="text" name="distbtmlmt" value="${uneatable.distbtmlmt }" id="datepicker1" data-position="right top" /></td>
    </tr>
    <tr>
      <td>영업자주소</td>
      <td>
	    <input style="width:300px;" type="text" name="addr" value="${uneatable.addr }" id="address" placeholder="주소" readonly />
	    <input style="width:150px;" type="button" class="search" onclick="openZipSearch()" value="우편번호 찾기" />
      </td>
    </tr>
     <tr>
      <td>검사기관</td>
      <td><input readonly type="text" name="insttNm" value="${uneatable.insttNm }"/>
      <button id="btn1" type="button" class="search" data-toggle="modal" data-target="#exampleModal">검색</button>
      </td>
      <tr>
      <td>회수폐기일련번호</td>
      <td><input type="text" name="rtrvldsuseSeq" value="${uneatable.rtrvldsuseSeq }"/>
      </td>
      <tr>
    </tr>
    </tr>
</table>
<h4>부적합판정 사유</h4>
<table class="table table-bordered">
	<thead>
		<th>부적합한항목</th>
		<th>기준규격</th>
		<th>검사결과</th>
	</thead>
    <tbody>
    	<td><input readonly type="text" name="testItmnm" value="${uneatable.testItmnm }"/>
    	<button id="btn2" type="button" class="search" data-toggle="modal" data-target="#exampleModal2">검색</button>
    	</td>
    	<td><input type="text" name="stdrStnd" value="${uneatable.stdrStnd }"/></td>
    	<td><input type="text" name="testanalsRslt" value="${uneatable.testanalsRslt }"/></td>
    </tbody>
    <tr>
    	<td colspan="3">
    		<input class="btn btnAi1" type="reset" value="취소">
    		<input class="btn btnAi0" type="submit" value="등록">
    	</td>
    </tr>
  </table>
</form>
</div> 
<!-- 콘테이너 끝 -->

<!-- 검색기관찾는 모달창 -->
<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel">찾으시는 검사기관을 입력해주세요</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
     
      <div class="modal-body">
        <input style="width:250px; height:30px;" type="text" name="insttNm2"/>
        <input id="btn7" type="button" class="search" value="검색" />
      </div>
      <div id="listBody">
      	
      </div>
      <input type="hidden" name="tmp" />
      <div class="modal-footer">
        <button type="button" class="btn btn btnAi0" data-dismiss="modal">닫기</button>
        <button id="btn9" type="button" class="btn btn btnAi1">확인</button>
      </div>
    </div>
  </div>
</div>

<!-- 항목찾는 모달창 -->
<div class="modal fade" id="exampleModal2" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel">찾으시는 항목을 검색해주세요</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
        <input style="width:250px; height:30px;" type="text" name="test" />
        <button id="btn4" type="button" class="search" >검색</button>
      </div>
      <div id="listBody2">
      
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btnAi0" data-dismiss="modal">닫기</button>
        <button id="bnt10" type="button" class="btn btnAi1">확인</button>
      </div>
    </div>
  </div>
</div>
<script>
var instt = $("[name='insttNm2']");
var body = $("#listBody");
var insttNm =$("[name='insttNm']");
var hidden = $("[name='tmp']");
var exampleModal = $("#exampleModal");
var test =$("[name='test']");
var body2 = $("#listBody2");
var hidden2 = $("[name='testItmnm']");
$(function() {
    //input을 datepicker로 선언
    $("#datepicker").datepicker({
        dateFormat: 'yy-mm-dd' //Input Display Format 변경
        ,showOtherMonths: true //빈 공간에 현재월의 앞뒤월의 날짜를 표시
        ,showMonthAfterYear:true //년도 먼저 나오고, 뒤에 월 표시
        ,changeYear: true //콤보박스에서 년 선택 가능
        ,changeMonth: true //콤보박스에서 월 선택 가능                
        ,showOn: "both" //button:버튼을 표시하고,버튼을 눌러야만 달력 표시 ^ both:버튼을 표시하고,버튼을 누르거나 input을 클릭하면 달력 표시  
//         ,buttonImageOnly: true //기본 버튼의 회색 부분을 없애고, 이미지만 보이게 함
        ,buttonText: "선택" //버튼에 마우스 갖다 댔을 때 표시되는 텍스트                
        ,yearSuffix: "년" //달력의 년도 부분 뒤에 붙는 텍스트
        ,monthNamesShort: ['1','2','3','4','5','6','7','8','9','10','11','12'] //달력의 월 부분 텍스트
        ,monthNames: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'] //달력의 월 부분 Tooltip 텍스트
        ,dayNamesMin: ['일','월','화','수','목','금','토'] //달력의 요일 부분 텍스트
        ,dayNames: ['일요일','월요일','화요일','수요일','목요일','금요일','토요일'] //달력의 요일 부분 Tooltip 텍스트
        ,minDate: "-1M" //최소 선택일자(-1D:하루전, -1M:한달전, -1Y:일년전)
        ,maxDate: "+1M" //최대 선택일자(+1D:하루후, -1M:한달후, -1Y:일년후) 
       	,onSelect: function(selected) {
       		$("#datepicker1").datepicker("option","minDate", selected)
       	}
   
    
    
    });                    
  
    $("#datepicker1").datepicker({
        dateFormat: 'yy-mm-dd' //Input Display Format 변경
        ,showOtherMonths: true //빈 공간에 현재월의 앞뒤월의 날짜를 표시
        ,showMonthAfterYear:true //년도 먼저 나오고, 뒤에 월 표시
        ,changeYear: true //콤보박스에서 년 선택 가능
        ,changeMonth: true //콤보박스에서 월 선택 가능                
        ,showOn: "both" //button:버튼을 표시하고,버튼을 눌러야만 달력 표시 ^ both:버튼을 표시하고,버튼을 누르거나 input을 클릭하면 달력 표시  
//         ,buttonImageOnly: true //기본 버튼의 회색 부분을 없애고, 이미지만 보이게 함
        ,buttonText: "선택" //버튼에 마우스 갖다 댔을 때 표시되는 텍스트                
        ,yearSuffix: "년" //달력의 년도 부분 뒤에 붙는 텍스트
        ,monthNamesShort: ['1','2','3','4','5','6','7','8','9','10','11','12'] //달력의 월 부분 텍스트
        ,monthNames: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'] //달력의 월 부분 Tooltip 텍스트
        ,dayNamesMin: ['일','월','화','수','목','금','토'] //달력의 요일 부분 텍스트
        ,dayNames: ['일요일','월요일','화요일','수요일','목요일','금요일','토요일'] //달력의 요일 부분 Tooltip 텍스트
        ,minDate: "-1M" //최소 선택일자(-1D:하루전, -1M:한달전, -1Y:일년전)
        ,maxDate: "+1M" //최대 선택일자(+1D:하루후, -1M:한달후, -1Y:일년후)
    	,onSelect: function(selected) {
    		$("#datepicker").datepicker("option","maxDate", selected)
    	}
    });                    
    
    
  
    //초기값을 오늘 날짜로 설정
    $('#datepicker1').datepicker({
    	minDate :  date
    	
    }); //(-1D:하루전, -1M:한달전, -1Y:일년전), (+1D:하루후, -1M:한달후, -1Y:일년후)
   
});

	function openZipSearch() {
		new daum.Postcode({
			oncomplete: function(data) {
				$("#address").val(data.address);
			}
		}).open();
	}
	
	$("#btn7").on("click",function(){
		let value = instt.val();
		$.ajax({
			url : "${cPath }/uneatable/insttNmList.do",
			method : "post",
			data : {
				"insttNm2" : value
			},
			dataType : "json",
			success : function(resp) {
				let tmps = [];
				$(resp).each(function(i,v){
					let tmp = $("<p>").text(v);
					tmps.push(tmp);
				});
				
				body.html(tmps);
			},
			error : function(xhr) {
				console.log(xhr.status);
			}
		});
	});
	
	body.on("click","p",function(){
		let value = $(this).text();
		hidden.val(value);
	})
	
	$("#btn9").on("click",function(){
		let value = hidden.val();
		insttNm.val(value);
		$('#exampleModal').modal('hide');
	});
	
	
	$("#btn4").on("click",function(){
		let value = test.val();
		$.ajax({
			url : "${cPath }/uneatable/testNmList.do",
			method : "post",
			data : {
				"testNm" : value
			},
			dataType : "json",
			success : function(resp) {
				let tmps = [];
				$(resp).each(function(i,v){
					let tmp = $("<p>").text(v);
					tmps.push(tmp);
				});
				
				body2.html(tmps);
			},
			error : function(xhr) {
				console.log(xhr.status);
			}
		});
	});
	
	body2.on("click","p",function(){
		let value = $(this).text();
		hidden2.val(value);
	});
	
	$("#bnt10").on("click",function(){
		$('#exampleModal2').modal('hide');
	});
	

</script>

<c:if test="${not empty fail }">
   <script>
   $(document).ready(function() {
      Swal.fire({
           icon: 'error',
           title: 'Oops...',
           text: '서버오류'
         })
   });
   </script>
</c:if>

<c:if test="${not empty success }">
   <script>
   $(document).ready(function(){
      
   Swal.fire({
        position: 'center',
        icon: 'success',
        title: '등록되셨습니다.',
        showConfirmButton: false,
        timer: 1500
      })
   });
   </script>
</c:if>


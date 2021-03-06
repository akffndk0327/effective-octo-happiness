<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
<%--
* [[개정이력(Modification Information)]]
* 수정일                 수정자      수정내용
* 2019. 11. 9.      이진희      문서수정
* ----------  ---------  -----------------
* 2019. 11. 6.      이진희      최초작성
* Copyright (c) 2019 by DDIT All right reserved
 --%>
<script src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<div id="InnerContainer">
<form id="stopselling" method="post">
<table class="table table-bordered">
   <c:choose>
	<c:when test="${not empty stopsellVO.imgFilePath}">
	<tr>
	<th>기존첨부파일</th>
		<td>
			<div class="form-inline">
			<input type="file" name="imgFilePath" class="form-control mr-2"/><span name="imgFilePath">${stopsellVO.imgFilePath }</span>
			</div>
		</td>
	</tr>
	</c:when>
	<c:otherwise>
	<tr>
	<th>첨부파일</th>
		<td>
			<div class="form-inline">
			<input type="file" name="imgFilePath" class="form-control mr-2" />
			</div>
		</td>
	</tr>
	</c:otherwise>
	</c:choose>
	
	<c:choose>
	  <c:when test="${not empty stopsellVO.rtrvldsuseSeq }">
		<tr>
		<th>회수판매중지일련번호</th>
			<td>
				<input type="text" readonly name="rtrvldsuseSeq" value="${stopsellVO.rtrvldsuseSeq }"/>
			</td>
		</tr>
	  </c:when>
	  <c:otherwise>
	  	<tr>
		<th>회수판매중지일련번호</th>
			<td>
				<input type="text"  name="rtrvldsuseSeq" />
			</td>
		</tr>
	  </c:otherwise>
	</c:choose>
	<tr>
	<th>제품명</th>
		<td>
			<input type="text" name="prdtnm" value="${stopsellVO.prdtnm }"/>
		</td>
	</tr>
	<tr>
	<th>제조업체명</th>
		<td>
			<input type="text" name="bsshnm" value="${stopsellVO.bsshnm }"/>
		</td>
	</tr>
	<tr>
	<th>제조일자</th>
		<td>
			<input readonly type="text" value="${stopsellVO.mnfdt }" name="mnfdt" id="datepicker" data-position="right top" />
		</td>
	</tr>
	<tr>
	<th>회수사유</th>
		<td>
			<input type="text" name="rtrvlprvns" value="${stopsellVO.rtrvlprvns }" />
		</td>
	</tr>
	<th>회수방법</th>
		<td>
			<c:choose>
				<c:when test="${empty stopsellVO.rtrvlplandocRtrvlmthd}">
				<select name="rtrvlplandocRtrvlmthd">	
					<option>선택해주세요</option>
					<option>영업자 직접 회수</option>
					<option>영업자 자진회수</option>
					<option>영업자 강제회수</option>
					<option>업체 개별 재고 파악후 택배 회수</option>
					<option>택배(착불가능) 및 점포별 배송차량 방문하여 수거</option>
					<option>거래처 유선통보 및 소비자 공개를 통해 전량 회수</option>
				</select>
				</c:when>
				<c:otherwise>
				<select name="rtrvlplandocRtrvlmthd">	
					<option>${stopsellVO.rtrvlplandocRtrvlmthd }</option>
					<option>영업자 직접 회수</option>
					<option>영업자 자진회수</option>
					<option>영업자 강제회수</option>
					<option>업체 개별 재고 파악후 택배 회수</option>
					<option>택배(착불가능) 및 점포별 배송차량 방문하여 수거</option>
					<option>거래처 유선통보 및 소비자 공개를 통해 전량 회수</option>
				</select>
				</c:otherwise>
			</c:choose>
		</td>
	</tr>
	<tr>
		<td colspan="2" >
			<input class="btn btnAi0" type="submit" value="등록" />
		</td>
	</tr>
</table>
</form>
</div>
<script type="text/javascript">
	
	$(function() {
	    //input을 datepicker로 선언
	    $("#datepicker").datepicker({
	        dateFormat: 'yy-mm-dd' //Input Display Format 변경
	        ,showOtherMonths: true //빈 공간에 현재월의 앞뒤월의 날짜를 표시
	        ,showMonthAfterYear:true //년도 먼저 나오고, 뒤에 월 표시
	        ,changeYear: true //콤보박스에서 년 선택 가능
	        ,changeMonth: true //콤보박스에서 월 선택 가능                
	        ,showOn: "both" //button:버튼을 표시하고,버튼을 눌러야만 달력 표시 ^ both:버튼을 표시하고,버튼을 누르거나 input을 클릭하면 달력 표시  
// 	        ,buttonImage: "http://jqueryui.com/resources/demos/datepicker/images/calendar.gif" //버튼 이미지 경로
// 	        ,buttonImageOnly: true //기본 버튼의 회색 부분을 없애고, 이미지만 보이게 함
	        ,buttonText: "선택" //버튼에 마우스 갖다 댔을 때 표시되는 텍스트                
	        ,yearSuffix: "년" //달력의 년도 부분 뒤에 붙는 텍스트
	        ,monthNamesShort: ['1','2','3','4','5','6','7','8','9','10','11','12'] //달력의 월 부분 텍스트
	        ,monthNames: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'] //달력의 월 부분 Tooltip 텍스트
	        ,dayNamesMin: ['일','월','화','수','목','금','토'] //달력의 요일 부분 텍스트
	        ,dayNames: ['일요일','월요일','화요일','수요일','목요일','금요일','토요일'] //달력의 요일 부분 Tooltip 텍스트
	        ,minDate: "-1M" //최소 선택일자(-1D:하루전, -1M:한달전, -1Y:일년전)
	        ,maxDate: "+1M" //최대 선택일자(+1D:하루후, -1M:한달후, -1Y:일년후)                
	    });                    
	    
	    //초기값을 오늘 날짜로 설정
	    $('#datepicker').datepicker(); //(-1D:하루전, -1M:한달전, -1Y:일년전), (+1D:하루후, -1M:한달후, -1Y:일년후)
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
        title: '등록되셨습니다',
        showConfirmButton: false,
        timer: 1500
      })
   });
   </script>
</c:if>

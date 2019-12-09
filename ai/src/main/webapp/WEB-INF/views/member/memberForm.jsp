<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%--
* 회원 타입 선택 v
* 아이디 , 비밀번호, 비밀번호 확인, 이름, 이메일, 주소, 휴대전화(본인인증), 캡챠
* 회원 - 성별, 나이, 알러지유무
* 기업 - 사업자 번호, 기업명, 사업장형태?
* [[개정이력(Modification Information)]]
* 수정일                 수정자      수정내용
* ----------  ---------  -----------------
* 2019. 11. 6.    허민지      최초작성
* 2019. 11. 7.    허민지       다음 우편번호 api 추가
* 2019. 11. 12.   허민지     사업자 폼 추가
* Copyright (c) 2019 by DDIT All right reserved
 --%>

<!-- jQuery -->
<!-- <script type="text/javascript" src="https://code.jquery.com/jquery-3.4.1.min.js"></script> -->

<!-- bootstrap validator js -->
<script type="text/javascript" src="//cdnjs.cloudflare.com/ajax/libs/jquery.bootstrapvalidator/0.5.3/js/bootstrapValidator.js"></script>

<!-- 다음 우편주소 api -->
<script src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>

<!-- 정규식 체크 연결 -->
<script type="text/javascript" src="${cPath }/js/check.js"></script>

<!-- 구글 reCAPTCHA -->
<script src="https://www.google.com/recaptcha/api.js" async defer></script>

<!-- <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script> -->

<style>
#capDiv{
   width: 30%;
    margin-left: 40%;
}

 .checkBtn{
    margin-right: 205px;
    margin-top: 1px;
}
 .btnRight1{ 
    margin-right: -169px; 
    float : right;
 } 
  .back{  
     margin-left: -100px;  
     float : left;
 } 
 .font{
	font-size : 20px;
}
.minusBtn{
	width:32px;
	height:32px;
}
</style>


<div id="InnerContainer">

<div class="container">
<form class="well form-horizontal" method="post" id="contact_form">


<!--회원 분류 -->
	<!-- 회원 정보 수정 시 뜨면 안되는 폼 -->
    	<div class="font form-group">
    		<label class="col-md-4 control-label">회원 분류</label>
        	<div class="col-md-4">
            	<div class="radio">
                	<label>
                    	<input type="radio" class="authorId" name="authorId" value="ROLE_MEM" checked/> 일반회원 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                	</label>
                	<label>
                    	<input type="radio" class="authorId" name="authorId" value="ROLE_EMP" /> 기업회원
                	</label>
            	</div>
        	</div>
    	</div>
    	
   		<!-- id-->
   		<div class="font form-group">
     		<label class="col-md-4 control-label">아이디</label>  
     		<div class="col-md-4 inputGroupContainer">
     			<div class="input-group">
     				<span class="input-group-addon"><i class="fa fa-plus"></i></span>
	     			<input id="checkMemId" name="memId" placeholder="아이디" class="form-control"  type="text">
     			</div>
     		</div>
     		<input type="button" id="idCheckBtn" class="btn checkBtn btnAi1" value="중복확인"/>
   		</div>
   	
   	
   <!-- password -->
   <div class="font form-group">
     <label class="col-md-4 control-label">비밀번호</label>  
     <div class="col-md-4 inputGroupContainer">
     	<div class="input-group">
     		<span class="input-group-addon"><i class="fa fa-lock"></i></span>
     		<input  name="memPass" placeholder="비밀번호" 
     				class="form-control"  type="password" value="${savedMember.memPass }">
       </div>
     </div>
   </div>
   
   
   <!-- 이름-->
   <!-- 수정 시 보이면 안되는 폼 -->
   <div class="font form-group">
     <label class="col-md-4 control-label">이름</label>  
     <div class="col-md-4 inputGroupContainer">
     	<div class="input-group">
     		<span class="input-group-addon"><i class="fa fa-user"></i></span>
     		<input  name="memName" placeholder="이름" class="form-control"  type="text">
       </div>
     </div>
   </div>
   
   <!-- email-->
    <div class="font form-group">
     <label class="col-md-4 control-label">E-Mail</label>  
     <div class="col-md-4 inputGroupContainer">
       <div class="input-group">
       	<span class="input-group-addon"><i class="fa fa-mail-bulk"></i></span>
     	<input id="checkMail" name="memMail" placeholder="E-Mail Address" 
     			class="form-control"  type="text" value="${savedMember.memMail }">
       </div>
     </div>
       <input type="button" id="mailCheckBtn" class="btn checkBtn btnAi1" value="중복확인"/>
   </div>
   
   <!-- phone-->
   <div id="phoneArea">
   <div class="font form-group">
     <label class="col-md-4 control-label">Phone </label>  
       <div class="col-md-4 inputGroupContainer">
       <div class="input-group">
           <span class="input-group-addon"><i class="fa fa-phone"></i></span>
     		<input name="memTel" id="phone" placeholder="01012345678"
     				class="form-control memTel" type="text" >
     			<input placeholder="인증번호" class="form-control" 
     					type="text" name="verify">
       </div>
     </div>
     	<input type="button" id="sendBtn" class="btn checkBtn btnAi1" value="전송"
     			style="margin-right:205px;">
   </div>
   </div>

   <!-- 회원전용 - 주소, 성별, 나이, 알러지유무-->
   <!-- 수정시  주소만 나옴-->
   <!-- 성별-->
   <div id="memForm">
   <!-- zip code-->
   <div class="font form-group">
     <label class="col-md-4 control-label">Zip Code</label>  
       <div class="col-md-4 inputGroupContainer">
       <div class="input-group">
           <span class="input-group-addon"><i class="fa fa-home"></i></span>
      <input type="text" name="memZip" id="sample6_postcode" class="form-control"
      		 placeholder="우편번호" value="${savedMember.memZip }" readonly>
      <input type="text" name="memAddr1" id="sample6_address" 
      		 placeholder="주소" value="${savedMember.memAddr1 }" class="form-control" readonly><br>
      <input type="text" name="memAddr2" id="sample6_extraAddress" class="form-control"
      		placeholder="상세주소1" value="${savedMember.memAddr2 }">
      <input type="text" id="sample6_detailAddress" class="form-control" placeholder="상세주소">
       </div>
   </div>
      <input type="button" onclick="sample6_execDaumPostcode()" class="btn checkBtn btnAi1" value="우편번호 찾기"
      		 style="margin-right:175px; width:130px; ">
   </div>
   
    <div class="font form-group">
                           <label class="col-md-4 control-label">성별</label>
                           <div class="col-md-4">
                               <div class="radio">
                                   <label>
                                       <input type="radio" name="memGender" value="F" checked/> 여자 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                   </label>
                                   <label>
                                       <input type="radio" name="memGender" value="M" /> 남자
                                   </label>
                               </div>
                           </div>
                       </div>
   
   <!-- 나이  -->
   <div class="font form-group">
     <label class="col-md-4 control-label">생년</label>  
       <div class="col-md-4 inputGroupContainer">
       <div class="input-group">
        <select name="memAge" >
           <option>선택</option>
           <c:forEach var="i" begin="0" end="${2019-1920 }" varStatus="vs">
           		<c:set var="age" value="${2019-i }"/>
              <option value="${age }">${age }</option>
           </c:forEach>
        </select>
       </div>
     </div>
   </div>
   
   <!-- 알레르기 유무 -->
   <div class="font form-group">
                           <label class="col-md-4 control-label">알레르기</label>
                           <div class="col-md-4">
                               <div class="radio">
                                   <label>
                                       <input type="radio" name="memAll" class="memAll" /> 해당있음&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                   </label>
                                   <label>
                                       <input type="radio" name="memAll" class="memAll" checked value="해당없음" />해당없음
                                   </label>
                               </div>
                           </div>
      </div>
   
   <div id="allForm" style=display:none>
   <!-- 식품 -->         
   <div class="font form-group original">
     <label class="col-md-4 control-label"></label>  
       <div class="col-md-4 inputGroupContainer">
       <div class="input-group">
        <select name="memAllList[0].allId" style="height: 30px">
           	<option value="0" selected>종류</option>
            	 	<c:forEach var="allergy" items="${allergy }">
	           	  		<c:if test="${allergy.allType ne '해당없음' }">
                				<option value="${allergy.allId }">${allergy.allName }</option>
             			</c:if>
             		</c:forEach>
        </select>      
        <br>  <br>
        <select name="memAllList[0].symId" style="height: 30px">
           <option value="S0" selected >증상</option>
           <c:forEach var="symtom" items="${symtom }">
              <option value="${symtom.symId }">${symtom.symName }</option>
           </c:forEach>
        </select>
     		&nbsp;<img src="${cPath }/images/plus.png" class="plusBtn"/>
     		&nbsp;<img src="${cPath }/images/minus.png" class="minusBtn"/>
       </div>
     </div>
    </div><!--식품 div끝 -->
   </div><!-- allForm 끝 -->
   
   </div> <!-- memForm 닫는 div -->
   
   
   <div id="comForm" style=display:none>
   <!-- 기업전용 - 사업자 번호, 기업명, 기업 주소 -->
    <div class="font form-group">
     <label class="col-md-4 control-label">사업자번호</label>  
       <div class="col-md-4 inputGroupContainer">
       <div class="input-group">
           <span class="input-group-addon"><i class="fa fa-briefcase"></i></span>
              <input id="numBox" name="comNum" placeholder="사업자번호" class="form-control" type="text" />
       </div>
       
     </div>
       <input type="button" class="checkBtn btnAi1 searchBtn" value="검색"/>
   </div>
   
   
   <!-- 기업명 -->
    <div class="font form-group">
     <label class="col-md-4 control-label">기업명</label>  
       <div class="col-md-4 inputGroupContainer">
       <div id="setHere1" class="input-group">
     <input id="nameBox" name="comName" placeholder="기업명" class="form-control"  type="text" value="" readonly>
       </div>
     </div>
     </div>
    <div class="font form-group">
   <!-- 기업주소 -->
     <label class="col-md-4 control-label">기업주소</label>  
       <div class="col-md-4 inputGroupContainer">
       <div id="setHere2" class="input-group">
     <input id="addrBox" name="comAddr" placeholder="기업주소" class="form-control"  type="text" value="" readonly>
       </div>
   </div>
   </div>
                       
   </div><!-- comForm 끝 -->
   
   
    <!-- 캡챠 -->
	  <br>
      <div id="capDiv" class="g-recaptcha" data-sitekey="6LflpcMUAAAAAI-Mhsgl_XeD1qvvsv6ZkwL9qlzZ"></div>
      <br/>
      <input type="hidden" id="reCapchaBtn" value="Submit">
   
   <!-- Button -->
   <div class="font form-group">
     <label class="col-md-4 control-label"></label>
     <div class="col-md-4">
   		<input type="button" onclick="history.back();" class="btn back btnAi1" value="뒤로가기"/>
        <input type="submit" class="btn btnRight1 btnAi0" id="apply" value="등록" />
     </div>
   </div>
     
   </form>
   

   
   </div>
       </div><!-- /.container -->
</div>


<script type="text/javascript">
	var addAllergy = $("#addAllergy");
	var addedSite = $("#addedSite");
	var idCheckBtn = $("#idCheckBtn");
	var mailCheckBtn = $("#mailCheckBtn");

	$("#reCapchaBtn").on("click",function(){
		$("#contact_form").attr("action","${cPath}/member/VerifyRecaptcha");
	});
	
	$("#apply").on("click", function(){
		$("#contact_form").attr("action","${cPath}/member/memberInsert.do");
	});
	
	//사업자번호 검색
	$(".searchBtn").on("click", function(){
	    var numBox = $("#numBox").val();
	    $.ajax({
	       url : '${cPath}/company/companyInsert.do',
	       method : "get",
	       data : {"comNum" : numBox},
	       dataType : "json",
	       success : function(resp) {
	          let input1 = $("input[name='comName']").attr('value',resp.comName);
	          let input2 = $("input[name='comAddr']").attr('value',resp.comAddr);
	          $('#setHere1').html(input1);
	          $('#setHere2').html(input2);
	       },
	       error : function(xhr) {
	          console.log(xhr.status);
	       }
	    });
	 })
	 
	 //알러지추가
	 $("body").on("click", ".plusBtn", function(){
// 		console.log($(".original").html());
		//복사할 한 세트 지정
		var parent = $(this).parents('.original');
		//그녀석의 outer html 가져오기
		var content = $(parent).get(0).outerHTML;
		//오리지널 세트의 갯수를 알아야함
		var index = $(".original").length;
		//가져온 html을 넣기전에 그 html의 인덱스를 바꿔줘야함
		//바꿀 인덱스를 갯수만큼 바꿔줘야함
		var newContent = content.replace(/memAllList\[\d\]/gm,"memAllList["+index+"]");
		//바로 위 부모
		var allergy = $(parent).parents('div:first');
// 		var minus = "&nbsp;<img src='${cPath }/images/minus.png' class='minusBtn'/>";
		allergy.append(newContent);
		console.log(index);
		console.log(newContent);
	 })
	 
	 //알러지삭제
	 $("body").on("click", ".minusBtn", function(){
			//복사할 한 세트 지정
			var parent = $(this).parents('.original');
			//그녀석의 outer html 가져오기
			var content = $(parent).get(0).outerHTML;
			//오리지널 세트의 갯수를 알아야함
			var index = $(".original").length;
			//카운트 변수를 하나 넣어서 추가할때는 추가 해주고 마이너스때는 -1 해줘서 그 값이 1이면 삭제 할 수 없게.
		 	if(index==1){
			 	Swal.fire({
				  	icon: 'error',
				  	title: 'Oops...',
				  	text: '삭제 할 수 없습니다'
					})
		 	}else{
		 		$(this).parent("div").remove();
		 	}
		
	 })
	 
	 //id중복검사
	 idCheckBtn.on("click", function(){
		var checkMemId = $("#checkMemId");
		var idValue= checkMemId.val();
		$.ajax({
	     	  url : '${cPath}/member/idCheck.do',
	    	   method : "get",
	    	   data : {"memId" : idValue},
	    	   dataType : "json",
	   	    success : function(resp) {
	   	    	//아이디가 있으면 중복이고 
	   	    	if(resp.memId=='0'){
	   	    		Swal.fire({
		   	    		  position: 'center',
		   	    		  icon: 'success',
		   	    		  title: '사용 할 수 있는 아이디 입니다.',
		   	    		  showConfirmButton: false,
		   	    		  timer: 1500
	   	    		})
	   	    	//공백이면 검색 x
	   	    	}else if(resp.memId==""){
	   	    		Swal.fire({
		   	    		  icon: 'error',
		   	    		  title: 'Oops...',
		   	    		  text: '검색 할 수 없는 아이디 입니다.',
	   	    			})
 	   	    	//아이디가 없으면 사용 할 수 있음.
	   	    	}else {
	   	    		Swal.fire({
		   	    		  icon: 'error',
		   	    		  title: 'Oops...',
		   	    		  text: '이미 존재하는 아이디 입니다.',
	   	    			})
	   	    	}
	   	    },
	   	    error : function(xhr) {
	  	        console.log(xhr.status);
	  	     }
	 	   });
	 })
	 
	 //mail중복
	 mailCheckBtn.on("click", function(){
		var checkMail = $("#checkMail");
		var mailValue = checkMail.val();
		$.ajax({
	     	  url : '${cPath}/member/mailCheck.do',
	    	   method : "get",
	    	   data : {"memMail" : mailValue},
	    	   dataType : "json",
	   	    success : function(resp) {
	   	    	//아이디가 있으면 중복이고 
	   	    	if(resp.memMail=='0'){
	   	    		Swal.fire({
		   	    		  position: 'center',
		   	    		  icon: 'success',
		   	    		  title: '사용 할 수 있는 이메일 입니다.',
		   	    		  showConfirmButton: false,
		   	    		  timer: 1500
	   	    		})
	   	    	//공백이면 검색 x
	   	    	}else if(resp.memMail==""){
	   	    		Swal.fire({
		   	    		  icon: 'error',
		   	    		  title: 'Oops...',
		   	    		  text: '검색 할 수 없는 메일 주소 입니다.',
	   	    			})
 	   	    	//아이디가 없으면 사용 할 수 있음.
	   	    	}else {
	   	    		Swal.fire({
		   	    		  icon: 'error',
		   	    		  title: 'Oops...',
		   	    		  text: '이미 존재하는 이메일 입니다.',
	   	    			})
	   	    	}
	   	    },
	   	    error : function(xhr) {
	  	        console.log(xhr.status);
	  	     }
	 	   });
	 })
	
	 $("#sendBtn").on("click", function(){
		 let timerInterval
		 Swal.fire({
		   title: 'ARS 서비스 요청 중 입니다.',
		   html: '잠시만 기다려 주세요',
		   timer: 6000,
		   timerProgressBar: true,
		   onBeforeOpen: () => {
		     Swal.showLoading()
		     timerInterval = setInterval(() => {
		       Swal.getContent().querySelector('b')
		         .textContent = Swal.getTimerLeft()
		     }, 100)
		   },
		   onClose: () => {
		     clearInterval(timerInterval)
		   }
		 }).then((result) => {
		   if (
		     /* Read more about handling dismissals below */
		     result.dismiss === Swal.DismissReason.timer
		   ) {
		     console.log('I was closed by the timer') // eslint-disable-line
		   }
		 })
		 
	 })

</script>
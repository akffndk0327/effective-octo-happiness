<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<%--
* [[개정이력(Modification Information)]]
* 수정일                수정자      수정내용
* ----------  ---------  -----------------
* 2019. 11. 18.      	 허민지      최초작성
* Copyright (c) 2019 by DDIT All right reserved
 --%>

<!-- bootstrap validator js -->
<script type="text/javascript" src="//cdnjs.cloudflare.com/ajax/libs/jquery.bootstrapvalidator/0.5.3/js/bootstrapValidator.js"></script>

<!-- 다음 우편주소 api -->
<script src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>

<!-- 정규식 체크 연결 -->
<script type="text/javascript" src="${cPath }/js/check.js"></script>
<style>
.form-group{
	height: 20px;
}

.well{
	height: 670px;
}
 .font{
	font-size : 20px;
}
 .back{  
     margin-left: -500px;  
     float : left;
} 
.form-control{
	font-size: 20px;
}
</style>

<div id="InnerContainer">

	<div class="container">
		<form:form modelAttribute="member" class="well form-horizontal"  method="post" 
				action="${cPath }/member/memberUpdate.do" id="contact_form">
				
		<input type="hidden" name=authorId value="ROLE_MEM"/>
   		<!-- id-->
   		<div class="font form-group">
     		<label class="col-md-4 control-label">아이디</label>  
     		<div class="col-md-4 inputGroupContainer">
     			<div class="input-group">
     				<span class="input-group-addon"><i class="fa fa-plus"></i></span>
	     			<input id="checkMemId" name="memId" placeholder="아이디" class="form-control"  
	     					value="${savedMember.memId }" type="text" readonly>
     			</div>
     		</div>
   		</div>
   		<br><br>
   	    <!-- 이름-->
	   <div class="font form-group">
	     	<label class="col-md-4 control-label">이름</label>  
	     	<div class="col-md-4 inputGroupContainer">
	     		<div class="input-group">
	     			<span class="input-group-addon"><i class="fa fa-user"></i></span>
		     			<input placeholder="이름" class="form-control"
		    					value="${savedMember.memName }" type="text" readonly>
	       </div>
	     </div>
	   </div>
		<br><br>
		
	   <!-- password -->
	   <div class="font form-group">
	     <label class="col-md-4 control-label">비밀번호</label>  
	     <div class="col-md-4 inputGroupContainer">
	     	<div class="input-group">
	     		<span class="input-group-addon"><i class="fa fa-lock"></i></span>
	     		<input  name="memPass" placeholder="비밀번호" 
	     				class="form-control"  type="password">
	       </div>
	     </div>
	   </div>
   		<br><br>
   
	   <!-- email-->
	    <div class="font form-group">
	     <label class="col-md-4 control-label">E-Mail</label>  
	     <div class="col-md-4 inputGroupContainer">
	       <div class="input-group">
	       	<span class="input-group-addon"><i class="fa fa-mail-bulk"></i></span>
	     	<input name="memMail" placeholder="E-Mail Address" 
	     			class="form-control"  type="text" value="${savedMember.memMail }">
	       </div>
	     </div>
	   </div>
   		<br><br>
	   <!-- phone-->
	   <div class="font form-group">
	     <label class="col-md-4 control-label">Phone </label>  
	       <div class="col-md-4 inputGroupContainer">
	       <div class="input-group">
	           <span class="input-group-addon"><i class="fa fa-phone"></i></span>
	     		<input name="memTel" placeholder="01012345678" 
	     				class="form-control" type="text" value="${savedMember.memTel }">
	       </div>
	     </div>
	   </div>
   		<br><br>
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
   		<br>
   
   <!-- Button -->
   		
		  	<c:url value="/memMypage/memberDelete.do" var="deleteURL">
		   		<c:param name="memId" value="${savedMember.memId }"></c:param>
		   	</c:url>
		   
     		<div class="col-md-4" style="float: right; text-align: right">
     			<br>	<br>
	   			<sec:authorize url="/memMypage/memberDelete.do" access="hasRole('ROLE_MEM')">
		   			<input type="button" class="btn btnAi2" value="회원탈퇴" onclick="location.href='${deleteURL}';" />
	   			</sec:authorize>
			   	<input type="button" onclick="history.back();" class="btn back btnAi1" value="뒤로가기" />
       			<input type="submit" class="btn btn btnAi0" value="수정하기" />
     		</div>
   		</form:form>
   
   	</div>
 </div><!-- /.container -->
      
<c:if test="${not empty msg }">
	<script type="text/javascript">
	$(document).ready(function() {
		Swal.fire({
	  	icon: 'error',
	  	title: 'Oops...',
	  	text: '비밀번호 오류'
		})
	});
	</script>
</c:if>



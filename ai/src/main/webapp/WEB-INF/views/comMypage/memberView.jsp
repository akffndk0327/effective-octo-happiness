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
<!-- jQuery -->
<!-- <script type="text/javascript" src="https://code.jquery.com/jquery-3.4.1.min.js"></script> -->

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
  .back{   
      margin-left: 260px;   
      float : left; 
 }  
.font{
	font-size : 20px;
}
.well{
	height: 680px;
}
.form-control{
	font-size: 20px;
}
</style>

<div id="InnerContainer">

	<div class="container">
		<form:form modelAttribute="member" class="well form-horizontal"  method="post" 
				action="${cPath }/member/memberUpdate.do" id="contact_form">
    	
    	
    	<input type="hidden" name=authorId value="ROLE_EMP"/>
    	
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
	     	<label class="col-md-4 control-label">담당자 이름</label>  
	     	<div class="col-md-4 inputGroupContainer">
	     		<div class="input-group">
	     			<span class="input-group-addon"><i class="fa fa-user"></i></span>
	     				<input placeholder="이름" class="form-control" name="memName"
		     					value="${savedMember.memName }" type="text">
	     		</div>
	   		</div>
	   </div>

   	   <br><br>
   	   <!--사업자 번호-->
	   <div class="font form-group">
	     <label class="col-md-4 control-label">사업자 번호</label>  
	     <div class="col-md-4 inputGroupContainer">
	     	<div class="input-group">
	     		<span class="input-group-addon"><i class="fa fa-user"></i></span>
	     		<input name="comNum" placeholder="사업자번호" class="form-control"  
	     				value="${savedMember.company.comNum }" type="text" readonly>
	       </div>
	     </div>
	   </div>
	   <br><br>
   	    <!--기업명-->
	   <div class="font form-group">
	     <label class="col-md-4 control-label">기업명</label>  
	     <div class="col-md-4 inputGroupContainer">
	     	<div class="input-group">
	     		<span class="input-group-addon"><i class="fa fa-user"></i></span>
	     		<input name="comName" placeholder="기업명" class="form-control"  
	     				value="${savedMember.company.comName }" type="text" readonly>
	       </div>
	     </div>
	   </div>
	   <br><br>
	   
   	    <!--기업 주소-->
	   <div class="font form-group">
	     <label class="col-md-4 control-label">기업주소</label>  
	     <div class="col-md-4 inputGroupContainer">
	     	<div class="input-group">
	     		<span class="input-group-addon"><i class="fa fa-user"></i></span>
	     		<input name="comAddr" placeholder="기업주소" class="form-control"  
	     				value="${savedMember.company.comAddr }" type="text" readonly>
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
   
   		<br>
   <br><br>
   <!-- Button -->
       	<input type="submit" class="btn btn btnAi0" value="수정하기" />
		<input type="button" onclick="history.back();" class="btn back btnAi1" value="뒤로가기" />
  	 	<c:url value="/memMypage/memberDelete.do" var="deleteURL">
   			<c:param name="memId" value="${savedMember.memId }"></c:param>
   		</c:url>
   			<sec:authorize url="/memMypage/memberDelete.do" access="hasRole('ROLE_EMP')">
	   			<input type="button" class="btn btnAi0" value="회원탈퇴" onclick="location.href='${deleteURL}';" />
   			</sec:authorize>
     
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



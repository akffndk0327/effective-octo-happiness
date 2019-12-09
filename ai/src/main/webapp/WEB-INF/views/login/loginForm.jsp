<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<%--
* [[개정이력(Modification Information)]]
* 수정일                 수정자      수정내용
* ----------  ---------  -----------------
* 2019. 11. 16.      허민지      최초작성
* 2019. 11. 23       박주연      css 수정함.
* Copyright (c) 2019 by DDIT All right reserved
 --%>
<link rel="stylesheet" type="text/css" href="${cPath}/css/login.css">
<c:if test="${not empty sessionScope.SPRING_SECURITY_LAST_EXCEPTION }">
	<script type="text/javascript">
		Swal.fire({
			  icon: 'error',
			  title: 'Oops...',
			  text: "${sessionScope.SPRING_SECURITY_LAST_EXCEPTION.message}",
			})
	</script>
	<c:remove var="SPRING_SECURITY_LAST_EXCEPTION" scope="session"/>
</c:if>

<style>
.findId{
	width: 124px;	
}
.findPass{
	width: 144px;	
}
.input{
	width: 388px;
}
#idspan{
	font-size:20px;
}
.nameTag{
	font-size:20px;
}
#mBody{
	height:418px;
}
</style>

<div class='wrap'>
  <p id="letter">Login</p>
    <form action="${cPath }/loginProcess" method="post">
    	<c:set var="saveId" value="${cookie.idCookie.value }" />
        <input type="text" name="memId" id="username" placeholder="ID" value="${saveId }">
        <input type="password" name="memPass" id="password" placeholder="Password" style="margin-top: 7px;" />
  		<input type="checkbox" id="saveId" name="idSave" value="on" ${not empty saveId?"checked":"" }>
  		<span id ="idspan"> &nbsp;로그인 정보 기억하기 </span>
  		 <button type="submit" class='login loginBtn' id="loginBtn">LOGIN</button>
    	 <button type="button" class='forgot loginBtn' data-toggle="modal"
			data-target="#exampleModal" id="loginBtn">FORGOT YOUR ACCOUNT ?</button>
    </form>
</div>

<!-- Modal -->
<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel">아이디 비밀번호 찾기</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div id="mBody" class="modal-body">
      	<form:form modelAttribute="member" action="${cPath }/member/findId.do">
			<div class="nameTag">이&nbsp;름 : &nbsp;&nbsp;&nbsp;&nbsp;
				<input type="text" class="input" name="memName"/>
			</div>
			<br>
			<div class="nameTag">메일주소 :&nbsp;
				<input type="text" class="input" name="memMail"/><br><br>
				<input type="submit" class="btn findId btnAi1" value="아이디 찾기"/>
			</div>
      	</form:form>
      	<br><br><br>
      	<form:form modelAttribute="member" method="post" action="${cPath }/member/findPass.do">
      	<br><br>
      		<div class="nameTag">아이디 : &nbsp;&nbsp;&nbsp;
				<input type="text" class="input" name="memId"/>
      		</div>
      		<br>
			<div class="nameTag">메일주소 :&nbsp;
				<input type="text" class="input" name="memMail"/>
			</div>
			<br>
      		<input type="submit" class="btn findPass btnAi1" value="비밀번호 찾기"/>
      	</form:form>
      </div>
<!--       <div class="modal-footer"> -->
<!--         <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button> -->
<!--       </div> -->
    </div>
  </div>
</div>


<c:if test="${not empty noMem}">
<script>
	$(document).ready(function() {
		Swal.fire({
	  	icon: 'error',
	  	title: 'Oops...',
	  	text: '존재하지 않는 회원이거나 탈퇴한 회원입니다.'
		})
	});
</script>
</c:if>

<c:if test="${not empty findId }">
<script>
	$(document).ready(function(){
		Swal.fire('회원님의 아이디는' + '${findId.memId}' + '입니다.' )
	});
</script>
</c:if>


<c:if test="${not empty findPass }">
	<script>
	$(document).ready(function(){
		Swal.fire({
			  position: 'center',
			  icon: 'success',
			  title: '임시 비밀번호 전송 완료',
			  showConfirmButton: false,
			  timer: 1500
			})
	});
	</script>
</c:if>
<c:if test="${not empty noPass }">
	<script>
	$(document).ready(function(){
		Swal.fire({
			  icon: 'error',
			  title: 'Oops...',
			  text: '잘못된 회원 정보 입니다.'
			})
	});
	</script>
</c:if>



<script>
/*

// Found on this dribbble: http://dribbble.com/shots/1284939-2point0-CP-Login/attachments/177970

*/

$('#username, #password').on('input', function() {
    if ($('#username').val() && $('#password').val()) {
        $('.login').addClass('buttonafter');
    } else {
        $('.login').removeClass('buttonafter');
    }
});

</script>
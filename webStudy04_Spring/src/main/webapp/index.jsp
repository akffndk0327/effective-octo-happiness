<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript"	src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
</head>
<body>
<form name="logoutForm" method ="post" action="${pageContext.request.contextPath}/logout">

</form>
<c:choose>
	<c:when test="${not empty authMember }">
		<a href="${cPath }/mypage"> ${authMember.mem_name}님,${authMember.mem_role }</a> <a href ="#" onclick="document.logoutForm.submit();">로그아웃</a>  <!-- a태그이지만 post방식 임  -->
		<div>
			<h4> 접속자 리스트 </h4>
			<ul id ="userListArea">
				
			</ul>
		</div>
	</c:when>
	<c:otherwise>
		<a href = "${cPath}/login"> 로그인</a>
		<a href = "${cPath}/member/memberInsert.do">가입하기</a>
	</c:otherwise>
</c:choose>

<script type="text/javascript">
	var userListArea = $("#userListArea");
	setInterval(() => {
		$.ajax({
			url : "${cPath}/getUserList.do",
			dataType : "json",
			success : function(resp) {
				var liTags =[];
				$(resp).each(function() {
					let liTag = $("<li>").text(
						$(this).prop("mem_name")
					);
					liTags.push(liTag);
				});
				userListArea.html(liTags);
			},
			error : function(errorResp) {
				console.log(errorResp.status);
			}

		})
	}, 2000);
	
</script>
<%-- <% --%>
<!-- 	MemberVO authMember = (MemberVO)session.getAttribute("authMember"); -->
<!--  	if(authMember ==null){ -->
<%-- 		%> --%>
<%-- 		<% --%>
<!--  	}else{ //로그인 된 상태 -->
<%-- 		%> --%>
<%-- 		누가 로그인 햇는지  --%>
		
		
<%-- <% --%>
<!-- // 	} -->
<%-- %> --%>

</body>
</html>
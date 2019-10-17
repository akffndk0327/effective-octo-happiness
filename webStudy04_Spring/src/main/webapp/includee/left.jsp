<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%--상대경로에서 기준은 내가 아닌 브라우저..!  --%>
<form id ="leftForm" action="<%=request.getContextPath()%>/module/layout.do">
	<input type="hidden" name="command" />
</form>
<ul>
	<li><a id ="standard" href="#">스탄다드jsp</a></li>
	<li><a id ="gugudan" href="#">구구단</a></li>
	<li><a id ="calendar" href="#">달력</a></li>
	<li><a id ="time" href="#">시간확인</a></li>
	<li><a id ="img" href="#">이미지뷰어</a></li>
	<li><a id ="explorer" href="#">Server Explorer</a></li>
</ul>
<ul>
   <c:forEach var="menu" items="${menuList }">
      <li><a href="<c:url value="${menu.menuURL}"/>">${menu.menuText }</a></li>
   </c:forEach>
</ul>
<script type="text/javascript">
	var leftForm=$("#leftForm"); 
	$(function() {
		$("#leftMenu a").on('click',function(){
			//1.누구인지.
			command=$(this).prop("id"); //id값은 prop로 줌. attr도 맞김함.
			leftForm.find("[name='command']").val(command);
			//3.전송하기 
			leftForm.submit();
			
		})
	})
	


</script>
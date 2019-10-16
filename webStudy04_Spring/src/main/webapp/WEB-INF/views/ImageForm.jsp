<%@page import="java.util.Objects"%>
<%@page import="java.util.regex.Pattern"%>
<%@page import="java.net.URLEncoder"%>
<%@page import="java.io.File"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<style>
	img{
		width: 100px;
		height: 100px;
	}
</style>
</head>
<body>
<%	
	String[] images = (String[])request.getAttribute("images"); //스코프안에 목록 데이터 꺼내고있음
	String imageName = (String)request.getAttribute("imageName");
	Cookie[] cookie = request.getCookies(); //응답 받기 
%>
<form action="<%=request.getContextPath() %>/image.do">
<select name="image">
	<option value> 이미지선택 </option>
<%
	for(String name :images){
		%>
		<option><%=name %><<%=name %>/option>
		<%
	}
%>
</select>
</form>
<div id ="imageArea">

</div>
<script type="text/javascript">

	var imageArea = $("#imageArea");
	var pattern='<img src="<%=request.getContextPath() %>/image.do?image=%V"/>'; <%-- 1. 이미지주소먼저 오기 --> --%>
	
	$("[name='image']").on("change",function(){
		var imageName =$(this).val(); //var -> let쓰면 괄호안까지만 제한됨.
		
		//img태그를 divㅇ ㅔ넣기 
// 		imageArea.html(pattern.replace("%V",imageName));
		imageArea.append(pattern.replace("%V",imageName));
		
	});
	imageArea.on("click","img",function(){ //$("#img")이미지에 하면 아무것도 없어.+body태그 생성될때 1번만 실행됨.(동적이벤트)
		//-> imageArea로 바꾸꼬(정적이벤트에 추가하기) 
		//.on("click","img",function(){} : "img" 추가하기(디센던트) : 특정 엘리먼트 안에 들어있는 자손들 "img"안쓰면 다 삭제됨.쓰면 클릭한것만 삭제됨.
		$(this).remove(); //this : div
	
	});
// 	alert($("img").length);
	$("[name='image']").val("<%=Objects.toString(imageName,"")%>"); 
	//이옵션을 선택한다.
	$("[name='image']").trigger("change");

	



</script>
마지막으로 본 이미지가 다시 열엇을때 마지막 사진이 떠있어야함.
쿠키에 저장하기... 뭘 봣다라는 걸 쿠키에 저장하고 복원하기...
1. 이미지 파일명이 한글파일인 경우 처리하기.
2. 현재 사용하고있는 form.do와 이미지 경로 다를때도 cookie사용 
3. 2일간 살ㅇ아잇도록 

</body>
</html>
























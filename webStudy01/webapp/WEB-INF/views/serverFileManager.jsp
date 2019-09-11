
<%@page import="kr.or.ddit.enums.CommandType"%>
<%@page import="java.util.Objects"%>
<%@page import="kr.or.ddit.servlet03.FileWrapper"%>
<%@page import="java.util.List"%>
<%@page import="java.io.File"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
li.active {
	background: skyblue;
}
</style>
<script type="text/javascript"
	src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
</head>
<body>
	<h4>서버 파일 매니저</h4>
	<%
		// 	File[] files= (File[])request.getAttribute("files");
		List<FileWrapper> leftfiles = (List) request.getAttribute("leftfiles");
		List<FileWrapper> rightfiles = (List) request.getAttribute("rightfiles");
		String leftSrc = request.getParameter("leftSrc");
		String rightTarget = request.getParameter("rightTarget");
		String srcFile = request.getParameter("srcFile");
	%>
	<form id="serverFileForm">
<%-- 	<input type="text" readonly name ="leftSrc" id ="leftSrc" value=" <%=leftSrc %>" /> <!-- 여기에 데이터 있어야하고 코드에 의해 sumbmit잇어야해  --> --%>
		<input type="text" readonly name="leftSrc" id="leftSrc"
			value="<%=Objects.toString(leftSrc, "")%>" /> <!-- readonly : 변경불가  -->
		<!-- 여기에 데이터 있어야하고 코드에 의해 sumbmit잇어야해  -->
		<input type="text" readonly name="rightTarget" id="rightTarget"
			value="<%=Objects.toString(rightTarget, "")%>" />
		<!-- 여기에 데이터 있어야하고 코드에 의해 sumbmit잇어야해  -->

	</form>
	<%-- <%= application.getRealPath("") %> --%>

	<ul id="leftArea">
		<%
			for (FileWrapper tmp : leftfiles) {
				//폴더라는 의미  이경우 이면 더블클릭하게끔 만들고 id는 ..? 뭘 가지고 있어서 안돼
		%>
		<%-- 			<li class="<%=tmp.isDirectory()? "dir" : "file"%>"><%=tmp.getName() %></li> <!-- 파일명 받아와  --> --%>
		<%-- 			<li class="<%=tmp.isDirectory()?"dir":"file"%>"><%=tmp.getAbsolutePath() %></li> <!-- 파일명 받아와 tmp.getAbsolutePath()  --> --%>
		<li id="<%=tmp.getId()%>" class="<%=tmp.isDirectory() ? "dir" : "file"%>"><%=tmp.getDisplayName()%></li>
		<!-- 파일명 받아와 tmp.getAbsolutePath()  -->
		<%
			}
		%>
	</ul>
	<form action="?" method="post">
		<input type="text" readonly name="leftSrc" id="leftSrc"
			value="<%=Objects.toString(leftSrc, "")%>" /> <input type="text"
			readonly name="rightTarget" id="rightTarget"
			value="<%=Objects.toString(rightTarget, "")%>" /> <input type="text"
			readonly name="srcFile" id="srcFile" required
			value="<%=Objects.toString(srcFile, "")%>" />

		<%
			for (CommandType command : CommandType.values()) {
		%>
		<input type="radio" required name="commad"
			value="<%=command.name()%>" />
		<!-- value ="" 이게 서버로 가야함  -->
		<%=command.name()%>
		<%
			}
		%>
		<input type="submit" value="전송" />
	</form>


	<ul id="rightArea">
		<%
			for (FileWrapper tmp : rightfiles) {
				//폴더라는 의미  이경우 이면 더블클릭하게끔 만들고 id는 ..? 뭘 가지고 있어서 안돼
		%>
		<li id="<%=tmp.getId()%>" class="<%=tmp.isDirectory() ? "dir" : "file"%>"><%=tmp.getDisplayName()%></li>
		<!-- 파일명 받아와 tmp.getAbsolutePath()  -->
		<%
			}
		%>
	</ul>
	<script type="text/javascript">
		var leftSrc = $('#leftSrc');
		var rightTarget = $('#rightTarget');
		var serverFileForm = $("#serverFileForm");
		var srcFile = $("#srcFile");
		$("li").css({cursor:"pointer"});
		$(".dir").on("dblclick", function() { //더블클릭 
			if ($(this).parent().prop("id") == 'leftArea') {
				leftSrc.val($(this).prop("id"));
			} else {
				rightTarget.val($(this).prop("id"));
			}
			serverFileForm.submit();
			//여기에 ajax코드 넣기 
		});

		$("#leftArea>.file").on("click", function() {
			$(this).siblings("li").removeClass("active");
			$(this).toggleClass("active") //현재 엘리먼트값에 클래스추가할수있다 .
			if ($(this).hasClass("active")) {
				srcFile.val($(this).prop("id")); //액티브추가됐을때 실행되야함 
			}else{
				srcFile.val("");				
			}
		})
	</script>
</body>
</html>
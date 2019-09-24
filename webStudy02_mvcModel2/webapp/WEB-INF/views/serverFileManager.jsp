
<%@page import="kr.or.ddit.enums.CommandType"%>
<%@page import="java.util.Objects"%>
<%@page import="kr.or.ddit.servlet03.FileWrapper"%>
<%@page import="java.util.List"%>
<%@page import="java.io.File"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<style>
li.active {
	background: skyblue;
}
</style>
<script type="text/javascript">
	$.getContextPath = function() {
		return "<%=request.getContextPath()%>";
	}
</script>
	<h4>서버 파일 매니저</h4>
	<%
		// 	File[] files= (File[])request.getAttribute("files");
		List<FileWrapper> leftfiles = (List) request.getAttribute("leftfiles");
		List<FileWrapper> rightfiles = (List) request.getAttribute("rightfiles");
		String leftSrc = request.getParameter("leftSrc");
		String rightTarget = request.getParameter("rightTarget");
		String srcFile = request.getParameter("srcFile");
	%>
<form id="serverFileForm" class="managerForm">
<!-- 	<input type="hidden" name="command" value="explorer" /> -->
<%-- 	<input type="text" readonly name ="leftSrc" id ="leftSrc" value=" <%=leftSrc %>" /> <!-- 여기에 데이터 있어야하고 코드에 의해 sumbmit잇어야해  --> --%>
		<input type="text" readonly name="leftSrc" id="leftSrc" class="leftSrc"
			value="<%=Objects.toString(leftSrc, "")%>" /> <!-- readonly : 변경불가  -->
		<!-- 여기에 데이터 있어야하고 코드에 의해 sumbmit잇어야해  -->
		<input type="text" readonly name="rightTarget" class="rightTarget" value="<%=Objects.toString(rightTarget, "")%>" />

</form>
<%-- <%= application.getRealPath("") %> --%>

<ul id="leftArea" class="groupUL">
<%
	for (FileWrapper tmp : leftfiles) {
	//폴더라는 의미  이경우 이면 더블클릭하게끔 만들고 id는 ..? 뭘 가지고 있어서 안돼
%>
<%-- 			<li class="<%=tmp.isDirectory()? "dir" : "file"%>"><%=tmp.getName() %></li> <!-- 파일명 받아와  --> --%>
<%-- 			<li class="<%=tmp.isDirectory()?"dir":"file"%>"><%=tmp.getAbsolutePath() %></li> <!-- 파일명 받아와 tmp.getAbsolutePath()  --> --%>
	<li id="<%=tmp.getId()%>" class="<%=tmp.isDirectory()?"dir" : "file"%>"><%=tmp.getDisplayName()%></li>
		<!-- 파일명 받아와 tmp.getAbsolutePath()  -->
<%
	}
%>
	</ul>
<form id="commandForm" action="?" method="post" class="managerForm">
<!-- 		<input type="hidden" name="command" value="explorer" /> -->
		<input type="text" readonly name="leftSrc" id="leftSrc" class="leftSrc"	value="<%=Objects.toString(leftSrc, "")%>" /> 
		<input type="text" readonly name="rightTarget" class="rightTarget"	value="<%=Objects.toString(rightTarget, "")%>" /> 
		<input type="text" readonly name="srcFile" id="srcFile" required value="<%=Objects.toString(srcFile, "")%>" />

		<%
			for (CommandType command : CommandType.values()) {
		%>
		<input type="radio" required name="command"
			value="<%=command.name()%>" />
		<!-- value ="" 이게 서버로 가야함  -->
		<%=command.name()%>
		<%
			}
		%>
		<input type="submit" value="전송" />
</form>


<ul id="rightArea" class="groupUL">
		<%
			for (FileWrapper tmp : rightfiles) {
				//폴더라는 의미  이경우 이면 더블클릭하게끔 만들고 id는 ..? 뭘 가지고 있어서 안돼
		%>
		<li id="<%=tmp.getId()%>" class="<%=tmp.isDirectory()?"dir":"file"%>"><%=tmp.getDisplayName()%></li>
		<!-- 파일명 받아와 tmp.getAbsolutePath()  -->
		<%
			}
		%>
	</ul>
	<!-- 스티브사우더스의 웹사이트 최적화 원칙 -->
	<script type="text/javascript" src ="<%=request.getContextPath() %>/js/serverManager.js" >
	
	</script>


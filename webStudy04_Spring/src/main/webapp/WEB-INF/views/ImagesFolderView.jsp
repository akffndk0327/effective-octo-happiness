<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<form method="post">
<%=request.getContextPath() %>/imagesFolderProcess
<%-- <% --%>
<!-- // 	String[] images = (String[])request.getAttribute("images"); -->
<%-- %> --%>

<%
String[] imageFiles = (String[])request.getAttribute("imageFiles");
String[] targetFiles = (String[])request.getAttribute("targetFiles");
%>
<!-- 서블릿 최소 2개  -->
<!-- 1.여기오려는 애 1개 doget() : 페이지 이동까지 하기. 3가지 중에서 forward... 골라써  -->
<!-- 2. 하나는 dopost() 둘다 공통적으로 페이지 이동하는 코드가 있어야하고 둘이 코드는 달라야해  -->
<select name="filename" >
<!-- 	옵션들을 이미지파일로만들어  -->
<!-- 	이미지 목록 d://~  -->
<!-- 	파일시스템리소스가 웹리소스로 달라짐  -->
<!-- 	좀만 손 보면 되는데 이미지말고 다른거 있으면 걸러야해  -->
<!-- 	getMIME으로 거를수도 있어   -->
ㅇ;ㅣ미지하나 선택하고 copy버튼클릭하고 서밋하면 07번 폴더에 복사 :String copy 
어떤 이미지선택하고 move 하면 그 이지미를 07에 옮겨 원복지워
이미선택 후 사진지우기 

<option value> 이미지선택 </option>
<%
for(String file :imageFiles){
	%>
	<option><%=file %></option> <!-- text면서 value -->
	<%
}
%>
<!-- required : 아무것도 선택안햇을때 경고창 -->
<input type="radio" value="COPY" name ="command" required/>복사
<input type="radio" value="MOVE" name ="command" required/>이동
<input type="radio" value="DELETE" name ="command" required/>삭제
<input type="submit" value="명령을 처리해"/>
</select>
</form>
<ul>
	<%
		for( String file : targetFiles){
			%>
			<li><%=file %></li>
			<%
		}
	%>
</ul>

</body>
</html>













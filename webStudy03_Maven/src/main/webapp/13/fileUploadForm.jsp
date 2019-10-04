<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h4>파일 업로드 처리</h4>
<pre>
	1. Client side - UI구성되야해 
		1)form 구성 : 
			1. method ="post"로 body형성해야돼 
			get이면 쿼리스트링으로 보낼수잇지만 스트링으로 구성되어잇어. 근데 쿼리스트링 너무 길면 안돼 그래서 body에 데이터 넣어서 보내 
			2. enctype ="multipart/form-data" : 바디 형성을 input태그 개수만큼 여러개의 파트로쪼갠다
			 	enctype 기본은 "application"
			 	multipart:body를 파트로 나눈다 
				- 각 part를 통해 input태그 하나의 데이터가 전송됨.
				- part name = input 태그의 name 속성
												 
	2. server side(3.0ver)
		1) servlet 등록시 multipar-config설정 추가 (Part API 사용, Parameter 사용하기 위해서 )
			web.xml (multipart-config)
			@WebServlet(@MultipartConfig)
		2) 저장위치
			1.middle tier(WAS) - 지연시간 줄이기위햇
			  - Wdb resource, Classpath, FileSystem => 저장명 필요
			2.third tier(DB) : LOB타입 
		3) 저장 명 : 웹 취약성 제거(보안), 중복이름 --> 저장명 생성('UUID' API 사용 ! )
			- 원본파일명 그대로 사용
			- 새로 만든 파일명으로 사용  v 이렇게 사용하기 :
		4) 메타데이터 추출 & DB에 저장 
			1. 저장명
			2. 원본파일명
			3. 파일의 MIME, size 
		5) stream copy(upload) : 저장위치에 저장명으로 이미지 카피 뜨기 
	
</pre>

<form action="<c:url value='/file/upload.do'/>" method="post" enctype="multipart/form-data">
	<input type="text" name="uploader"/>
	<input type="file" name="uploadFile"/>
	<input type="file" name="uploadFile"/>
	<input type="file" name="uploadFile"/>
	<input type="submit" value="업로드" />
</form>
${sessionScope }
<c:if test="${not empty saveFiles }">
<div style="border: 1px solid black;">
	${uploader }
	<c:forEach items="${saveFiles}" var="saveFileURL">
		<img src="<c:url value='${saveFileURL }' />"/>
	</c:forEach>
	<c:remove var="uploader" scope="session"/>
<%-- 	<c:remove var="saveFileURL" scope="session"/> --%>
	<c:remove var="saveFiles" scope="session"/>
</div>
</c:if>
</body>
</html>
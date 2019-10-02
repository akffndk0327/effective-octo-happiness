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
	1. Client side
		1)form 구성 : 
			1. method ="post" body형성해야돼
			2. enctype ="multipart/form-data" : 바디 형성을 input태그 개수만큼 여러개의 파트로쪼갠다
				각 part를 통해 input 태그 하나의 데이터가 전송됨.
				part name = input 태그의 name 속성
												 
	2. server side
		1) servlet 등록시 multipar-config설정 추가 (Part API 사용, Parameter 사용하기 위해서 )
		2) 저장위치
		3) 저장 명 
		4) 메타데이터 추출
		5) stream copy(upload)
	
</pre>

<form action="<c:url value='/file/upload.do'/>" method="post" enctype="multipart/form-data">
	<input type="text" name="uploader"/>
	<input type="file" name="uploadFile"/>
	<input type="submit" value="업로드"/>
</form>
<c:if test="${not empty uploader }">
<div style="border: 1px solid black;">
	${uploader }
	<img src="<c:url value='${saveFileURL }' />"/>
	<c:remove var="uploader" scope="session"/>
	<c:remove var="saveFileURL" scope="session"/>
</div>
</c:if>
</body>
</html>
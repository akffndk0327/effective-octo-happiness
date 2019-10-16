<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags"  prefix="spring"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<!-- 파트는 몇개? input 태그 개수만큼 생성됨  -->
<!-- 이 두개 파트를 upload 컨드롤러 까지 전송이 되야함. -->
<!-- 파트데이터 스고싶으면 multipart 등록해야함 web.xml -->
<form method="post" enctype="multipart/form-data">
	uploader: <input type="text" name="uploader"/>
	file : <input type="file" name="uploadFile">
	<input type="submit" value="업로드"/>
</form>
<spring:eval expression="@appInfo.imagesClientUrl" var="images"></spring:eval>
<img src="${pageContext.request.contextPath }${images}${fileVO.savename }"/>
</body>
</html>
<%@page import="java.io.InputStream"%>
<%@page import="org.apache.commons.io.IOUtils"%>
<%@page import="java.io.FileOutputStream"%>
<%@page import="java.io.FileInputStream"%>
<%@page import="kr.or.ddit.servlet01.DescriptionServlet"%>
<%@page import="java.io.File"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>07/applicationDesc.jsp</title>
</head>
<body>
<h4> ServletContext application</h4>
서블릿은 싱글톤으로 관리됨. 
web.xml = application이 하고잇더?
<pre>
 : 서블릿의 어플리케이션과 서버에 대한 정보를 가진 객체
 컨텍스트 하나를 기준으로 싱글컨의 형태.
 ServletContext --> <%=application.hashCode() %>
 <a href="<%=request.getContextPath() %>/gugudan">구구단 서블릿</a>
 1. 서버의 정보 확보 : <%=application.getServerInfo() %> 
 		서블릿 스펙 버전 : <%=application.getMajorVersion()+"."+application.getMinorVersion() %>
 2. 로그 기록(logging) : 서버를위한거여서 콘솔에 찍힘 
 	1). 시스템 분석 : 프로그램 유지위해서  
 	<%
 		application.log("디버깅 목적으로 기록 ...");
 	%> 		
 3. 웹 리소스 확보
  파일시스템 절대경로 : getRealPath(uri)
  URL getResource(uri); url 에 실제 파일경로있엇어
  입력스트림(inputStream)getResourceAsStream(uri);
  MimeText getMimeType(파일명); <%=application.getMimeType("Desert.jpg") %>
  
  ** 리소스를 확보하는 방법
  1. File System Resource
  2. Classpath Resource
  3. Web Resource
  <%
 	File file1 = new File("d:/contents/Desert.jpg");
 	DescriptionServlet.class.getResource("Desert.jpg"); //서블릿이 있는 위치에서부터 뒤져 로컬 호스트 이후의 주소로 검색? 
 	///// 	String uri = "webStudy01/images/Desert.jpg";
 	String uri2 = "/images/Desert.jpg";
 	//  	String path = application.getRealPath(uri2);  //가상경로를 통해 진짜 통로로 간다 ;/uri : 서버가 사용하고있음 
 	//서버사이드는 컨텍스트패스이후부터나와야함 :/images/Desert.jpg" 부터
 	///webStudy01 :는 배포하는 파일명이기 때문에 안써 
 	//localhost 는 언제든 바뀔수있다.
 	//  	File srcFile = new File(path); //FileInputStream 이거때문에 필요함 
 	String path = application.getResource(uri2).getFile(); //파일시스템상의 절대경로가 String paht와 같음 
 	String targetUri = "/07/Desert.jpg";
 	String targetPath = application.getRealPath(targetUri);
 	File targetFile = new File(targetPath);

 	try (
//  		FileInputStream inStream = new FileInputStream(srcFile);
 			InputStream is = application.getResourceAsStream(uri2); //ResourceAsStream 알아서 가져옴 
 			FileOutputStream outStream = new FileOutputStream(targetFile);) {
 		IOUtils.copy(is, outStream);
 	}
 %>
  <%=path %>
	<img src="<%=request.getContextPath()+targetUri%>"/>
<!--   //D:\A_TeachingMaterial\7.JspSpring\workspace\.metadata\.plugins\org.eclipse.wst.server.core\tmp1 -->
<!--   \wtpwebapps\ :localhost => 여기까지는 언제든 바뀔수 있어  -->
<!--   webStudy01\webStudy01\ -->
<!--   images\Desert.jpg : contextPath() -->
 웹: HTTP, 도메인 URL로 접근 
 클래 : 이미 .getclass.get 왜 클래스?=> classs location으로 식별 
 파일리소스 : 절대 경로로 파일위치 찾아 
 		
</pre>
</body>
</html>
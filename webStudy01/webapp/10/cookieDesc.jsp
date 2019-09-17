<%@page import="java.net.URLDecoder"%>
<%@page import="java.net.URLEncoder"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>10/cookieDesc.jsp</title>
</head>
<body>
<h4> Cookie</h4>
<pre>
	: HTTP 의 stateless 단점을 보완하기 위한 최소한의 상태정보가 클라이언트 쪽에 저장되는 개념.
	
	쿠키 사용 단계
	서버사이드
	1. 서버사이드에서 쿠키객체 생성
	2. 응답에 실어 전송 (저장은 클라이언트에서 해야함.)response.addCookie
	
	클라이언트 사이드 
	3. 브라우저별로 쿠키 저장소에 저장 
	4. 저장된 쿠키가 다음번 요청에 실려서 서버로 재전송
	  1)Creates a cookie, 2)a small amount of information sent by a servlet to a Web browser, 
	  3)saved by the browser, 4)and later sent back to the server.
	  
   서버사이드
   5. 재전송된 쿠키확보
   
   ** 쿠키 속성의 종류
   1. name(required)  :  영문자와 숫자, _ 허용  => 자바의 식별자 규칙성 
   2. value(required) : 모든 문자 허용, 단 RFC2396 규약에 따라 특수문자는 %인코딩, URL인코딩 필요.
   3.(옵션) path : 기본값 : 쿠키를 생성할 때 경로 반영 (ex : /webStudy01)
   			path의 하위 경로로 발생하는 요청에 대해서만 재전송됨.. 그렇지 않으면 기본값으로 감.
  4. domain/host : 기본값은 쿠키 생성 도메인/ 호스트 반영 	
  도메인주소의 레벨필요		
  			.naver.com 같은 형태로 특정기관의 모든 호스트로 재전송 가능(path설정과 함께)
  5. maxAge : 기본값은 세션의 만료시간과 동일함.(=jsessionId)  이 쿠키를 언제까지 저장되잇어야하나 라는 말 .만료시간은 현재 request세션과 동일하다(4가지 방법 )
  		만료값 재설정할때 쓰ㅁ임.
  		0 : maxAge 를 제외한 "나머지 속성들이 모두 동일한" 쿠키의 경우, 삭제
  		-1 : 브라우저가 종료시 해당 쿠키도 삭제.
  		
   			
   <a href ="<%=request.getContextPath()%>/10/viewCookie.jsp">동일 경로에서 쿠키 확인하기</a> 
   <a href ="<%=request.getContextPath()%>/10/10_inner/viewCookie.jsp">동일 깊이에서 쿠키 확인하기</a> 잘나옴
   <a href ="<%=request.getContextPath()%>/09/viewCookie.jsp">다른경로에서 쿠키 확인하기</a> 
	<%	
		String value = URLEncoder.encode("한글값","UTF-8"); //한글은 특수문자로 인식해서 인코딩해야함.
		//1. 서버사이드 쿠키 객체 생성
		Cookie cookie = new Cookie("testCookie", value); //한글값은 8버전ㅇ ㅣ하는 오류남.
 	  	//2.응답데이터에 넣어서 보내기 
	  	response.addCookie(cookie); //한번의 응답데이터에 여러 쿠키 헤더에 싣을수 있어
	
	  	//=====================================================================================
		
	  	//경로 예시 쿠키
	  	Cookie targeting = new Cookie("pathCookie", "specificPathCookie");
// 	  	targeting.setPath(request.getContextPath()+ "/09"); //방법1
	  	targeting.setPath("/"); //방법2. 어디든 경로 접근 가능 함.
	 	response.addCookie(targeting);
	 	//이경로들이 어디로 사용된느지 확인 하기 ! 
	 	//쿠키 재전송위치 : ㅋ클라이언드 
	 	
	 	//=========================================================================================

	 	//도메인 예시 쿠키
	 	//1.쿠키 객체 생성
	 	Cookie allDomainCookie = new Cookie("allDomain","allDomainCookie");
	  	//도메인 설정 변경하기
// 	  	allDomainCookie.setDomain("mail.pjy.com"); //가까져여서 오류ㅜ나는데 mail.~으로 바꿔봐
	  	//1-1.경로 설정하기
	  	allDomainCookie.setPath("/");
	  	//2.응답데이터에 넣기 
	  	response.addCookie(allDomainCookie); 
	  	
	  	//====================================================================================================

	  	//쿠키 저장 기간 설정 *****
	  	Cookie allLiveCookie = new Cookie("allLive","all~~Live~~");
// 	  	allLiveCookie.setMaxAge(60*60*24*2); //방법1. 데이터 정보가 2일 살아있어
		allLiveCookie.setPath("/"); //maxage0에 "/"로 하면..? ==> 안지워지고 남아있음 ! path :  /webStudy01/10 로 잡혀잇어서 다른 쿠키라고 생가함.
	  	allLiveCookie.setMaxAge(0); //사라지고없음 .삭제
	  	response.addCookie(allLiveCookie);
	%>
</pre>
</body>
</html>



















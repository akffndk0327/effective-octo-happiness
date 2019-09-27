<%@ page language="java"
    pageEncoding="UTF-8"%>
<%
// 	response.setContentType("text/plain"); //최신버전. 서버에서 charset붙여주는거 
response.setHeader("Content-Type", "text/html"); //옛날 서블릿일때 이렇게 서야함 
%>
<!DOCTYPE html>
<html>
<head>
<!-- <meta charset="UTF-8"> -->
<title>03/responseDesc.jsp</title>
</head>
<body>
<h4>Http 프로토콜의 응답 데이터 패키징 방식 </h4>
<pre>
	1.Response Line : Status code(응답상태 코드) Protocol
		*status code : 명령에 처리 결과를 의미하는 숫자 체계
		100 (since 1.1) : ING... : 연결 안귾어 =>단점 보완 : websocket
			http:stateless(무상태, 비상태유지 ) 요청끝나면 연결 다끝어
		200 : ok(success)
		300 : 완전한 처리를위해 사용자의 추가 액션이 필요한 상황.
			304(Not Modified) +캐쉬알아야해, : 너가 갖고있는캐쉬데이터 알아봐라 
			캐시제어 해더 2-2) Pragma(http 1.0), Cache-Control(1.1), Expires(1.1)
			302/307(Moved) :너가 사용하련느 자원의 위치가 다른데로 옮겻다라는 의미 +location으로 위치 정보 알려줌 
			ex)선생님이 나를 를 찾을 때 샘피시기준 3번째줄 통로에서두번째 잇는애 라고 하면 내가 일어나ㅡㄴ데 
			자리옮기면 옮겻다고 이야해야하는것과 같은ㅁ
		400 : Client side fail 클라이언트 사이드의 문제로 실패함. : 자세하게 ㅇㄹ려줨
			400(Bad Request) : 데이터 검증할때 발생시킴, 404(Not found):사용한 주소가 잘못됨 의미
			405(Method Not Allowed) :현재 메서드는 지원하지 않는다, 
			403(Forbidden) :금지된. 일반사용자가 관리자화면들어갈려할때 보여주는 에러. 
			401(UnAuthorized) :보안 인증:신원 확인, 인가 : 학생증 내밀어  => 권한이 없음 
			403(아예금지)>401(권한가져와...)
			415(Media Not Supported) : 이미지만 지원하는데 동영상 달라하고할때 
		500 : Server side fail 서버사이드 문제로 실패함. , 500(internal Server Error) : 구체적인 에러내용 안알려줘. 해킹위험많아서  
		HttpServletResponse.setStatus/sendError*주로사용 정상적이지 않은 상태코드 사용함 
	2.Response Header : response body 의 데이터에 대한 메타데이터 .문자열로 넘어감 
		1) MIME 변경 : Content-Type
			- page 지시자의 contentType 속성
			- response.setContentType
			- response.setHeader
		2) 캐시 제어 : Pragma(http 1.0), Cache-Control(1.1), Expires(1.1)
			캐시ㅣ: 클라이언트와 서버의 속도차이 좁히기 위한것 
			간혹 캐시 안남기게 해야함 - 보안 ㄷx) 피시방에서 인터넷뱅킹해 ex)익스플로어 인터넷임시파일에 캐시저장함- 아는사람이 정보빼갈수도 있어 .
			그래서 캐시 남으면 안돼 
			개발자는 3개다 쓸수 있어햐해
		<%
			response.setHeader("Pragma", "no-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.addHeader("Cache-Control", "no-store"); //헤더에 값 추가,1개의 헤더에 여러개 값 세팅 가능함 
// 			response.setHeader("Expires", "0"); //70년1월 1일 0시에 남겨라는말인데결국은 남기지 말앙라... 
			response.setDateHeader("Expires", 0); //70년 1ㅝㄹ1ㅇ리0시에 남겨라는말인데결국은 남기지 말앙라... long : 밀리세컨드데이트로 참조함 .
			
		%>	
			
		3) auto request  : Refresh
		: <a href="<%=request.getContextPath() %>/03/autoRequest.jsp">/03/autoRequest.jsp</a>
		4) *** 페이지 이동 : Location, 302/307
			<a href="${pageContext.request.contextPath}/05/flowControl.jsp">/05/flowControl.jsp</a> <!-- 클릭이벤트 발생할때 href 발생함 - 클라이언트사이드 방식  -->
		<!-- 표현언어 기호${}  -->
	3.Response Body(message Body)
</pre>
<img src="<%=request.getContextPath() %>/images/Jellyfish.jpg"/>
</body>
</html>


















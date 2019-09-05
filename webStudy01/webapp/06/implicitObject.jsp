<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isErrorPage="true"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>06/implicitObject.jsp</title>
</head>
<body>
<h4>기본객체(내장객체)</h4>
<pre>
	: jsp 컨테이너에 의해 서블릿 소스가 파싱될때 기본 제공되는 객체(_JSPService 메소드의 지역변수 형태)
	***PageContext pageContext<a href="<%=request.getContextPath() %>">/06/pageContext.jsp 참고</a> jsp 갯구마다 
	HttpServletRequest request : http 프로토콜의 요청에 대한 정보를 캡슐화. 요청마다 만들어져 
	HttplServletResponse response : http 프로토콜의 응답에 대한 정보를 캡슐화
	JSPWriter out : 한 jsp페이지으 ㅣ출력 버퍼에 대한 정보를 캡슐화 버퍼에 기록을 함 응답데이터 출력함.응답과 생명주기같어 
	"HttpSession session" :한 유저가 한 브라우저를 이용한 상황에서 해당 유저에 대한 정보를 캡슐화.1유저 3브라우저= 3세션 세션 =사람+브라우저
		세션 : 유저가 어플리케이션을 사용하고 있는 기간 (시간)
			   해당 기간내에 두 피어 사이에 의미있게 형성된 통로.
			   Http stateless 단점 을 보완하기 위한 최소한의 상태정보를 서버상에 유지./ 클라이언트에 저장하면 쿠키!  
			   단점: ( 요청, 응답 들어오고 나가면서 서로 답한내용을 저장하지 않음.) 
			   <a href="${pageContext.request.contextPath}/06/sessionDesc.jsp"> 06/sessionDesc.jsp참고</a>
			   <!-- 파싱잘 됨 . -->
	"ServletContext application" :어플리케이션(컨텍스트)과 서버에 대한 정보를 캡슐화. 웹리소스(파일리소스.클래스패스리소스,) 확보할때 많이 써 !   
		<a href="<%=request.getContextPath() %>/07/applicationDesc.jsp">/07/applicationDesc.jsp</a>
	ServletConfig config : 서블릿에 대한 메타데이터 객체  잘안써
	Object page : this ; 현재페이지의 인스턴스. 잘안쓰지만 this로 쓰면됌
	Trowable exception : 발생한 예외나 에러에 대한 정보를 캡술화  처리할수잇고없는예외의 통칭을 Throwable이라 할수 있다 .
	ServletContext --> <%=application.hashCode() %>
</pre>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>05/flowControl.jsp</title>
</head>
<body>
<h4> 웹 어플리케이션에서의 흐름 제어 (Flow Control)</h4>
<pre>
	Http의 특성 : Stateless : 비상태 유지특성..?
	1.소켓이 열려잇고 그 통로를  커넥션이라고 함 .
	2. 요청이 날라감
	3. 응답이 옴 
	4. 응답 후 소켓은 어째? 요청을 다 끊어 => connectless : 가용공간다쓰면 끊어버릴느거 
		-끊는이유는 서로가 상대방의 정보 관리하기 싫어서
		끊기면서 서버의 정보 사라짐 양쪽 peer에서 서로 정보 사라짐 
	1.Request Dispath : 요청을 분리해 = 서버사이드위임방식 클라이언트는 b의 결과를 a에서 응답한거라고 생가해 
		:"서버사이드"내에서만 이동하는 위임처리 방식으로 원본요청을 가지고 분리하는 방식 
		1)forward : 분기한 이후 도착지에서만 응답전송 (완전한 위임) (B)
		2)include : 분기한 도착지의 결과 데이터르 가지고 복귀.A+B
	2.Redirect
		:최초의 요청에 대한 응답이 이동 전에 먼저 발생하고,
		해당 응답에는 body가 없는대신 line(302/207), Header(Location))가 응답을 전송.
		최종적으로 "클라이언트쪽"으로 Location 방향으로 새로운요청을 발생시켜서 이동 
		오고가는 응답데이터 2쌍
		<%
// 			1.Request dispath, forward
// 			분기제어관리자 requeat dispather
			String path ="/02/standard.jsp"; //서버사이트에서는 상대경로 나오면안되! 상대경로 의미없어
// 			String path =request.getContextPath()+"/02/standard.jsp"; //서버사이드에서 쓰면안됌 
			//=>/webStudy01/webStudy01/02/standard.jsp 중복되서 나옴 
			RequestDispatcher rd = request.getRequestDispatcher(path); 
			//서버사이드 따라가닌까 컨텍스트 안붙여도돼
// 			rd.forward(request, response);
// 			2.Request dispatch, include
// 			rd.include(request, response);
// 			3. Rediect : 응답데이터가 한번 나가
			String location = request.getContextPath()+path;
			response.sendRedirect(location); //오류남.. 404 => 컨텍스트 네임 안보ㅇㅕ... 왜>???	, 누구에게 쓴껀지 확인하기 


		%>
</pre>

</body>
</html>
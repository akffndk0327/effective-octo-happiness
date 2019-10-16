<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" buffer="10kb" autoFlush="true"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>05/bufferDesc.jsp</title>
</head>
<body>
<h4>버퍼(Buffer)</h4>
<pre>
	: 두 피어 사이에서 발생하는 속도 차이를 보완하기 위한 임시저장 영역 .
	버퍼의 크기는 8kb => 어떻게 정해졋나?? 맨위에 지시자  +autoflush(자동방출 지원)= true / false: 버퍼 꽉 차도 방출안함 : 500번 에러 출력함.
	jsp웹 모듈에서 기본 버퍼의 크기 : 8kb
	버퍼를 제어하고 상태를 확인할때 사용하는 JspWriter :의  "out". 객체 사용 
	버퍼의 크기 : <%=out.getBufferSize()%> bytes + 얼마나 버퍼가 사용되었나 => 페이지 소스보기에서 글자수 세기 !한글 한자 2바이트
	버퍼의 잔여 크기 : <%=out.getRemaining() %> bytes
	****주의점 ! 
	버퍼를방출하기 전까지는 상태코그나 메타데이터 변경 가능.. !   
	버퍼 방출 된 이후에는 forward 이동이나 예외에 대한 처리가 불가능 
	<%
		for(int i=1;i<=100;i ++){
			out.println(i+"번째 반복."); // for문 돌때만다 얼마나 줄어들까. 최소 14바이트 사용 함 . =>중간에 방출 될것 : 100번째
			//autoflush :false일때  500번 에러 출력함. 방출안되게 하려면?
// 			if(!out.isAutoFlush() && i ==20){
// 				out.flush(); //내가 수동으로 방출시켜 
// 			}
			if(!out.isAutoFlush() && out.getRemaining()<20){ 
				out.flush(); //그래도 출력 잘됨.
			}
			if(i==98){
// 				throw new NullPointerException("강제발생 예외"); //에러페이지 예상햇는데 아눔것도 안나오고 콘솔에는 찍혀  안된느유는 중간에 버퍼가 방출되어서
				//autof~ : true바꾸기 
				String path = "/02/standard.jsp";
				RequestDispatcher rd = request.getRequestDispatcher(path);
				rd.include(request, response);  // => 오류남. 10kb => 500상태코드 발생 ! 
				//base의 버퍼는 넉넉하게 잡쟈 ! 
			}
		}
	%>
</pre>
</body>
</html>
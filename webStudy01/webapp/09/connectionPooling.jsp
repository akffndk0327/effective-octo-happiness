<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>09/connectionPooling.jsp</title>
</head>
<body>
<h4>Connection Pooling을 이용한 성능 향상</h4>
<pre>
한어플리케이션을 불특정다수가 이용해.

	1.메모리(공간) 기본형 string 대신에 버퍼(객체),wrapper사용 =>gavige콜렉션  
	2.소요시간 어디를 바꿔야 소요시간 짧아지는지
		: latency time(지연시간) + processing time(처리시간)
	플링 : connection객체 미리 pool에 만들어놓기 
	플링 vs 논플링
	1) 한번의 커넥션 한번의 처리 :25ms / pooling 사용 후 : 0ms
	2) 백번의 커넥션 백번의 처리 :712ms/ pooling 사용 후  : 19ms
	3) 한번의 커넥션 백번의 처리 :15ms => processing 타임은 거의 영향x + 지연시간을 줄여야함.
</pre>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
//클라이언트에서 요청시 데이타를 전송 받아서 
//처리(CRUD)후 응답 할 데이터를 만든다.
//데이터으ㅢ 형태는 json형태의 Object이다. .  []로 시작함 
//배열은 Strng, number, Object, array
//object는 name/value 쌍들의 비순서화된 SET이다. object는 {좌 중괄호로 시작하고 }우 중괄호로 끝내어 표현한다. 
// 각 name 뒤에 :colon을 붙이고 ,comma로 name/value 쌍들 간을 구분한다.
%>
{
	"name" : "홍길동",
	"id"   : "a001",
	"tel"  : "010-123-4567",
	"addr" : "대전 중구 대흥동"
}
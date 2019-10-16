<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"  %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JSTL(Jsp Standard Tah Library)</title>
<style>
	.red{
		background-color: red;
	}
</style>
</head>
<body>
<pre>
	: 커스텀 태그의 집합 라이브러리 
	1. 빌드패스에 jar 추가
	2. taglib에 지시자를 이용해 커스텀 태그 로딩 &lt;%@ taglib url="", prefix="" %&gt;
	3. &lt;prefix : tagname &gt;
	
	JSTL 종류 
	1. Core 태그 (c 접두어 사용)
	1) EL 변수 지원 : set(생성, 할당 ), remove(삭제)  지원한다  :1만들고 2할당 3지워준다.
	** remove 는 반드시 scope를 설정하기 !! 
<!-- 		scope : 입력했을때 넣ㅇ르때 영역 지울때 영역다름 scope :reguest = 3, page =x -->
		1. 솏성이름, scope 	value=3
		<c:set var="data" scope="page" value="3" />
		\${data * 3} =>${data * 3}
		<c:remove var="data" scope="page"/>
		\${data} =>${data}             //화이트스페이스
		<c:set  var="test" value="테스트 "/>
	2) 조건문(단일, 다중), 반복문
		if ("연산식 or 값") : 단일 조건문 test 속성에 조건식 
<%-- 		"true" => 문자열 준거 리터럴로 처리되어야함. \"${true}"ㅇ렇게 써야해  --%>
		<c:if test="${empty test}">
			test업다
		</c:if>
		<c:if test="${not empty test}">
			test 있다
		</c:if>

		choose == Switch, case : c:when, default : c:otherwise
		<c:choose>
			<c:when test="${empty test}">
				test 업고
			</c:when>
			<c:otherwise>
				test 잇고 
			</c:otherwise>
		</c:choose>
		
		forEach
			for(선언절 ; 조건절; 증감절)
			<c:forEach var="i" begin="1" end="10" step="2" varStatus="vs">
				${i }
				<span> index : ${vs.index }, count:${vs.count }</span>
			</c:forEach>
			
<%-- 			 컬렉션 먼저 필요. scope 없으면 기본값인 page로 인식함 .value="<%= %>"인경우 값을 할당하겟다 --%>
			 <c:set var="array" value='<%=new String[]{"a","b","c"} %>'/>
<!-- 			 //파싱하려했는데 ""이 또있어서 ''로 묶어줌.   -->
			for(임시블럭변수  :반복대상컬렉션) 
			varStatus : 루프태그스테이터스에 접근하고싶을때.  반복에 대한 상태자료 갖고 있어/
				${array[0] }
				LoopTagStatus 객체
					1. int : begin, end, step, index, count 
					2. boolean : first(맨 처음 반복만 true), last(마지막 반복에만 true,나머지는 false) 
				<c:forEach items="${array}" var="element" varStatus="vs">
				 		<c:if test="${vs.count eq 2 }">
<!-- 							//속성으로 만ㄷ르어 서 아래에서 써먹을수잇게 하기 				 		 -->
							<c:set var="clzName" value="red"/>
				 		</c:if>
				 		<c:if test="${vs.count ne 2 }">
							<c:set var="clzName" value="normal"/>
				 		</c:if>
				 	<span class="${clzName }">	${element}  ${not vs.last ? "," : "" } </span>
				</c:forEach>	
						
		forTokens : 토큰 : 문자의 의미부여할수잇는 최소한 의 단어? 문장 ?
		ex) int i =4; inti=4; => 의미 부여할 공백들이 없어 구분자의 위치에 따라 의미 가 달라져
		items : 문장, delims : 토큰방법
			<c:forTokens items="아버지가 가방에 들어가신다"  delims=" " var="token">
				${token }
			</c:forTokens>
			
	3) URL 재처리 : url,redirect => 서버사이트를 클라ㅣ언트 사이드로 바꿔줌
		1.. 클라이언트 사이드 경로 완성해줌. 2쿠키지원 확인후 쿠키 줌. 3 쿼리스트링 생성 
		var="viewURL" : scope에 넣어서 el로 찾아야해 
		<c:url value="/member/memberView.do" var="viewURL">
			<c:param name="param1" value="value1"/>
			<c:param name="param2" value="value2"></c:param>
		</c:url>
		${viewURL }
<%-- 		<c:redirect url="/02/standard.jsp"></c:redirect> --%>
	4) 기타 : import, out
<!-- 	 escapeXml="false": 태그 내용 보일수있어   -->
		<c:out value="<span>태그바디</span>" escapeXml="false"/>
		<c:import url="https://www.naver.com" var="naver"></c:import>
<%-- 		<jsp:include page="https://www.naver.com"></jsp:include> --%>
<%-- 		<c:out value="${naver }" escapeXml="false"/> --%>
		

	<%
		int data =3;
	pageContext.setAttribute("data", 3);
	pageContext.removeAttribute("data");
	%>
</pre>

</body>
</html>
<%@page import="kr.or.ddit.vo.MemberVO"%>
<%@page import="java.lang.reflect.Member"%>
<%@page import="java.util.HashSet"%>
<%@page import="java.util.Set"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>12/elDesc.jsp</title>
</head>
<body>
<h4>표현 언어 (Expression Language)</h4>
<pre>
	: 속성 데이터를 표한하기 위한 목적의 스크립트 형태(컴파일 필요없어 하지만 기본언어는 자바임.. 내부적으로 톰ㅌ캣에 의해 컴파일 되고있음 ) 언어 
 	=> 표현방법 : \${속성명=el변수명}
	<%
		String attr = "속성데이터";
		pageContext.setAttribute("attr", attr);
		request.setAttribute("attr", "요청 속성");
		
	%>
	<%=attr %> ==> ${attr}, ${requestScope.attr }
<!-- 	스코프생략되있으면 범위 작은거부터 뒤짐 -->
	1. 속성데이터의 표현
	2. 연산자의종류
	  1) 산술연산자(+-*/) + :유일한 산술연산자 concat 기능 수행 x
	   ${1+2}, ${1+"2"} concat 기능x "2"를 산술이 가능한 현태로 바꿈 =>3
	   ${"1"+"2" }, \${"A"+"1" }
	   NumberFormatException For input string: "A" : 파싱안되서 예외발생함.
	   ${test }, <%=request.getAttribute("test") %>
	   <%--
	   		pageContext.setAttribute("test", "abc");
	   --%>
	   ${test + 3 } => +있어서 연산하고 싶은데 test잇으닌까 숫자로 바꿀려고 함. 근데 null로 되닌까 0으로 인식하고 0+3 으로 처리
	   ${test1 -test2 }
	  
	  2) 논리 연산자(&&,||, !) => and,or, not 이 더 많이 씀
	  	\${true && true } => ${true &&true } 
	  	\${true and true } => ${true and true }
	  	\${test and  true } => ${test and  true }
	  	\${test1 and  test2 } => ${test1 and test2 }
	  	\${not test } => ${not test }
	  	\${not test and true } => ${not test and true }
	  
	  3) 비교연산자 (>,<,>=,<=,==, !=) => gt,lt,ge,le,eq,ne
	  	\${3 gt 4 } => ${3 gt 4 }
	  	\${test eq 3 } =>${test eq 3 }
	  	\${test1 ne test2 } => ${test1 ne test2 } 비교불가능
	  
	  4) 단항연산자 (empty) 제일 많이 쓸꺼얌.....
	  	 1.잇냐 없냐, 2. null이냐 아니냐  3.legnth(배열, 문자열) or size(collections) 체크하기 => StringUtils오 ㅏ비슷함.
	  <%
	   		pageContext.setAttribute("test", "");
	  	 	List<String> list = new ArrayList<>();
	  	 	pageContext.setAttribute("list", list);
	  	 	list.add("testValue");
	   %>
	  	\${empty test } =>${empty test }
	  	\${empty list } =>${empty list }
	  5) 삼항연산자 (조건식?참:거짓 )
	  	\${not empty test?"있다":"없다" }	=> ${not empty test?"있다":"없다" }	
	  	${not empty test1 and not empty test2?test1+test2:"없다" }
	  	둘다 있으면 더하고 없으면ㅇ ㅓㅄ다
	3. (속성으로 공유된 ) 집합객체에 대한 접근 방법 
		<%
			String[] array = new String[]{"ele1","ele2"};
			pageContext.setAttribute("array", array);
			list.add("testValue2");
			Map<String, Object> map = new HashMap<>();
			map.put("key1","value1");
			map.put("key 1","value1");
			map.put("key-1","value1");
			map.put("key2","value2");
			Set<String> set = new HashSet<>();
			pageContext.setAttribute("set", set);
			set.add("value1");
			set.add("value1");
		%>
		\${array }=> ${array }
		\${array[1] }=> ${array[1] }
		\${array[6] }=> ${array[6] }, 
<%-- 		<%=array[6] %> --%>
		:접근할수 없는 거 접근해도 화이트스페이스로 만들어서 예외안만들려고 함.
		\${list } => ${list }
<%-- 		\${list.get(6) } => ${list.get(6) } --%>
		: 톰캣에서 메소드 쓸수있게 바뀌긴햇음
		\${list[1] } => ${list[1] } //testValue
		\${list[6] } => ${list[6] } : 화이트 스페이스 
		\${map.get("key1") } => ${map.get("key1") } : 화이트 스페이스 
<%-- 	\${map.key 1 } => ${map.key 1} =>표현식 완성안되서 오류남. --%>
		\${map.key-1 } => ${map.key-1}  //-1 : 연산자로 인식해서 map.key : 0으로 인ㅅ기
		\${map["key-1"] } => ${map["key-1"] }  : 연산배열구조
		${set } => 2번째꺼는추출못해 없으닌까 
		
	4.(속성으로 공유된) 객체에 대한 접근 방법 
		<%
			MemberVO member = new MemberVO();
			pageContext.setAttribute("member",member);
			member.setMem_name("테스드");
		%>
<%-- 	방법1	 ${member.mem_name } : 프로퍼티 접근이 아닌, 자바빈규약을 거꾸로한거 . 변수에 접근할거라고 착각 노노해 !  --%>
<%-- 	방법2	${member.getMem_test() }, ${member.mem_test } : getter 호출과 같음. 그러나 가능한 쓰지말자 --%>
<%-- 	방법3		${member["mem_id"]} } --%>
		
	5. EL의 기본객체 (11)
	  1) 영역(Scope) 객체 Map&lt;String, Object&gt;
	   	pageScope, requestScope, sessionScope, applicationScope =>이거 다 Map
	   	${pageScope.member }, ${pageScope["member"] }
	   	${pageScope.member.mem_name}, 	${pageScope["member"]["mem_name"] },	
	   	${member.mem_name}
	   	${applicationScope.test } , ${test }
	  2) 요청 파라미터 객체  : param(Map&lt;String, String&gt;), 
	  			 paramValues(Map&lt;String,String[]&gt;). //배열 타입도 문자열 .
	    <%=request.getParameter("param1") %>, 
	    \${param.param1 }=>${param.param1 },
	    \${paramValues.param1[0] } => ${paramValues.param1[0] }    //=>value1
		${paramValues.param1[1] } => value2 가져옴.
		http://localhost/webStudy02/12/elDesc.jsp?param1=value1&param1=value2 : 하나의 이름으로 2개의 파라미터 넘어감 
		
	  3)헤더 객체 : header(Map&lt;String,String&gt;), 
	   headerValues(Map&lt;String, String[]&gt;) Map이긴 한데 헤더의 정보와 값ㅇ르 가졍 ㅘ
	    \${header.accept }= >${header.accept }
	    ${header.user-agent } //0 user도 없고 agent 속성도 없음 그래서 0 
	    ${header["user-agent"]}
	 	${headerValues["user-agent"]}
	  
	  4)쿠키 객체 : cookie(Map&lt;String, Cookie&gt;) Cookie :key :쿠키 이름 
	  	랜덤으로 쿠키에 접근하기 위해 만들어짐.
	  	${cookie.JSESSIONID }, ${cookie.JSESSIONID.value },
	  	${cookie["JSESSIONID"]["value"] }
	  	${cookie.JSESSIONID.name }
	  	
	  5)컨텍스트 파라미터 객체  : initParam(Map&lt;String, String&gt;)
	  	<%=application.getInitParameter("service") %>	//web.xml contextParam으로 받아온거
	  	\${initParam.service } =>${initParam.service },  방법1
	  	\${initParam["service"] } =>${initParam["service"] } 방법2
	  
	  6) pageContext (나머지 모든객체 가져올수있어. getter가지고잇어서 ) 
	    <%=request.getContextPath() %>	  
	  	  \${pageContext.request.contextPath } =>${pageContext.request.contextPath }  <%-- /webStudy03_Maven  --%>
	  	  \${pageContext.request.method } =>${pageContext.request.method }	 //=> get
	  	
	  
	  
	  	
	  	
</pre>
</body>
</html>














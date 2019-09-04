<%@page import="org.eclipse.jdt.internal.compiler.apt.model.Factory"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%!
	long factorial(int leftOp){ //양수가 맞는지 확인 ... 졸려 ...
		if(leftOp <=0) throw new IllegalArgumentException("피연산자 확인 :"+leftOp); //발생키기고 싶은 예외는 illegalargument exception!! -enum안 ㄴ에있음 
			if(leftOp ==1){				//1이 되면 리턴 해야함. 
				return 1;
			}else{
				//예외 발생시키기: throw cf) 예외처리 ㅣ: try-catch
				return leftOp * factorial(leftOp-1); //재귀호출 => 종료조건 반드시 필요 !!!!!
		}
	
		
	}
%>
<%
	request.setCharacterEncoding("UTF-8");
	String leftParam = request.getParameter("leftOp");
	long result = -1;
	//검증하기
	if(leftParam !=null&& leftParam.matches("^1?[0-9]$")){
		int leftOp = Integer.parseInt(leftParam);
		//재귀호출구조 
		result = factorial(leftOp);
	}
	
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>03/factorial.jsp</title>
<script type="text/javascript" src="https://code.jquery.com/jquery-3.4.1.min.js"></script>

</head>
<body>
<!-- 먼저 숫자를 받을때 검즈을 해야지 숫자만오게금  -->
action, href 처러 ㅁ다음 요청으 ㅣ주소를 기술하는 속성에, 값이 생략되면 ,현재 주소가 그대로 반영 

<form id="facForm">
	피연산자 : <input type="number" name="leftOp" min="1" max="19"/> <!-- 양수만 입력가느으... 양수로 얼마 이하만 입력하게끔 클라이언트에서 검증해야함., 서버에서도 받을때 검증해야함  -->
	<input type="submit" value="submit">
	<input type="reset" value="reset">
	<input type="button" value="button">
</form>
<%
	if(result != -1){
		%>
			<div>
			<!-- 포맷팅해야함 표현식 필요해 -->
				<%=String.format("%s !=%d", leftParam,result ) %>
				<!-- 어디선가 10!을 받는데가잇어야하는데 그곳은 서버  -->
				<!--  for문, 재귀호출... -->
			</div>
		<%
		
	}
%>
<script type="text/javascript">
$(function() {
	$('#facForm').on('submit',(event)=>{
		var form = event.target;
		
		console.log(form);
		console.log(form.leftOp);
		console.log(form.leftOp.value);
		var leftOp = form.leftOp.value;
		console.log(typeof leftOp);
		//1~19검증해야해 
		var regex= /^1?[0-9]$/igm //정규 표현식 i:대소문자구분, g, m:체킹문자가 여러줄이면 그거 다 검사하겟다. 1? :1이 오거나 안오거나 
		var valid = regex.test(leftOp);
		if(!valid){ //boolean타입으로 바음
			 alert("피연산자 확인 ");
		 }
		return valid; //정상적인 데이터 입력받고 확인하는중 
	});
	
})

</script>
</body>
</html>
























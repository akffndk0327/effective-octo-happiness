<%@page import="org.apache.commons.lang3.StringUtils"%>
<%@page import="java.util.Map.Entry"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.LinkedHashMap"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<script type="text/javascript">
	$(function() {
		$("select[name='sesMember']").on('change',function(event){ //타켓은 :select[name='sesMember' , dom-tree구조
			//submit하기 유발하다 = trigger
			var form = $(this).parents("form") // 내 조상들을 다 가져온다.
			console.log(form);
			form.submit();
		})
	})
</script>
</head>

<body>
<%
	//파라미터 꺼내기 =>request필요해
	Map<String,String[]> memberMap = new LinkedHashMap<>();  //String[] : pk제외한 종속  LinkedHashMap : 순서있는걸로 접근한느 api이다.
	memberMap.put("a001", new String[]{"바다","/ses/bada.jsp"}); //entry
	memberMap.put("a002", new String[]{"유진","/ses/yujin.jsp"});
	memberMap.put("a003", new String[]{"슈","/ses/shoe.jsp"});
%>
	<form action="<%=request.getContextPath()%>/sesProcess">
		<select name="sesMember"> <!-- ui구성에서 사용 됨.  -->
			<%
				for (Entry<String, String[]> tmp : memberMap.entrySet()) {
					tmp.getValue();
					String pk=tmp.getKey();
					String name = tmp.getValue()[0];
			%>
			<option value="<%=pk%>"><%=name%></option> <!-- Map의 a001, 바다 , 주소 동적 생성 !  -->

			<%
				}
			%>
		</select>
		<!-- ui 없을경우 강제적으로 스크립트처리해 -->
	</form>
<!-- jsp = ui구성  -->
<!-- A. memberMap을 이용해서 동적 option구성 -->
<!-- B. submit 버튼을 대신하여 option선택시 코드로 submit이벤트를 trigger시킴 -->
<!-- C. 전송 후 서버사이드에서 sesMember파아미터에 따라 다음특성으로 개인페이지를 서비스함  -->
<!-- 1. 사용자는 멤버의 개인페이지에 대한 정보를 모름 => 서버에서 이동 dispatch -->
<!-- 2. 사용자는 모든 요청이 seeForm.jsp에 의해처리되는걸로 착가함  -->
<!-- 3. 각 맴버의 개인페이지에서는 sesForm으로 전달된 파라미터를 확인할 수 있도록 한다. -->

<!-- 4. 멤버의 개인페이지결과 화면에서 다른멤버를 선택할 수 있도록  -->
<!-- ** -->
<!-- 1.현재 요청에 포함된 모든데이터를 삭제하고 도착지로 이동  -->
<!-- 2. 클라이언트는최종적으로 멤버의 개인페이지의 위치를 인지할 수 있음  -->
</body>
</html>
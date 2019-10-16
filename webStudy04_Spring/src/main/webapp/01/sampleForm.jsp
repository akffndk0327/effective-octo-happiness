<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>01/sampleForm.jsp</title>
</head>
<body>
<%=request.getServletContext().hashCode() %>
<form action="/webStudy01/sampleProcess" method="get"> <!-- 기본은 1.get이야.. 그래서 get아님 service만 쓸수이음, 2. post  -->
	<pre>
		<input type = "number" name="numParam"/>
		<input type = "text" name="textParam"/>
		<input type = "radio" name="radioParam" value= "radioON"/> ON
		<input type = "radio" name="radioParam" value = "raioOFF"/> OFF
		<input type = "checkbox" name="checkParam" value="checkA" /> 
		<input type = "checkbox" name="checkParam" value="checkB"/> 
		<input type = "checkbox" name="checkParam" value="checkC"/> 
		<select name ="selectParam1">
			<option>combox1</option>
			<option>combox2</option>
			<option>combox3</option>
		</select>
		<select name ="selectParam2" multiple ="multiple">
			<option>listbox1</option>
			<option>listbox2</option>
			<option>listbox3</option>
		</select>
		<button type ="submit">전송 </button>
	</pre>
</form>
</body>
</html>
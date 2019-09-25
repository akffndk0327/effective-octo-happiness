<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String resId= (String)request.getAttribute("resId");
	if(resId !=null){
// 	성공
%>
	{
		"sw" : "<%=resId %>님 가입 성공"
	}

<%	}else{ //실패 %>

	{
		"sw" : "가입 실패"	
	}

<%	
	}
%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	int seq = (Integer)request.getAttribute("res");
	if(seq !=0 ){
%>		
	{
	"sw" : "삭제성공"  
	
	}

<%	}else{  %>
	
	{
		"sw" :"삭제실패"
	}


<%		
	}
%>
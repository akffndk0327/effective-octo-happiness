<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
 String getId = (String)request.getAttribute("getId");
 String inputId = (String)request.getAttribute("inputId");
	if(getId ==null){
		//가입 가능한 아이디 
		
%>
{
	"sw" : "<%=inputId %>는 사용가능합니다."
}

<%	}else{ %>
{
	"sw" : "<%=inputId %>는 사용불가능"
<%-- 	"sw" : "<%=inputId %>는 사용불가능"  이것도 사용 가능함.--%>
}
<%		
	}
%>
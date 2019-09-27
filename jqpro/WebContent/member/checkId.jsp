<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
 String getId = (String)request.getAttribute("getid");
 String putId = (String)request.getAttribute("inputid");
	if(getId ==null){
		//가입 가능한 아이디 
		
%>
{
	"sw" : "<%=putId %>는 사용가능합니다."
}

<%	}else{ %>
{
	"sw" : "<%=putId %>는 사용불가능"
<%-- 	"sw" : "<%=inputId %>는 사용불가능"  이것도 사용 가능함.--%>
}
<%		
	}
%>
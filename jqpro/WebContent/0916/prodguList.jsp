<%@page import="kr.or.ddit.prod.vo.ProdVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	List<ProdVO> list = (List<ProdVO>)request.getAttribute("list");
	if(list.size()>0){
	//배열 만ㄷ르꺼야
%>	
	{
	<%-- 이게 success로 가면 res.sw , res.data[i].id, res.data[i].name 으로 써야함. --%>
		"sw" : "OK",
		"data" : [
<%
		for(int i = 0 ; i<list.size(); i++){
			ProdVO vo = list.get(i);
			if(i>0) out.print(",");
%>
{
		"id" : "<%=vo.getProd_id()%>",
		"name" : "<%=vo.getProd_name() %>"
}
<%
	}
%>		
		]
	
	}	
		
<%	}else{%>
	{
		"sw" : "NO"	
	}

<%		
	}
%>
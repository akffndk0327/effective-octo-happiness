<%@page import="kr.or.ddit.board.vo.ReplyVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%--  list닌까  배열 먼저 만들어야해  --%>
[
<%
	List<ReplyVO> list = (List<ReplyVO>)request.getAttribute("list");
	for(int i = 0; i<list.size(); i ++){
		ReplyVO vo = list.get(i);
		if(i>0) out.print(",");
		
%>
	{
		"renum" : "<%= vo.getRenum() %>",
		"bonum" : "<%= vo.getBonum() %>",
		"name"  : "<%= vo.getName() %>",
		"cont" : "<%= vo.getCont().replaceAll("\r", "").replaceAll("\n", "<br>") %>",
		"rdate" : "<%=vo.getRedate() %>"
	}
<%		
	}
%>
	
]
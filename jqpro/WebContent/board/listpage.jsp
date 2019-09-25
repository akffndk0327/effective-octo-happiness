<%@page import="kr.or.ddit.board.vo.BoardVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
List<BoardVO> list = (List<BoardVO>)request.getAttribute("list");
int spage = (Integer)request.getAttribute("startpage");
int epage = (Integer)request.getAttribute("endpage");
int tpage = (Integer)request.getAttribute("totalPage");

%>

{
	"tpage" : <%=tpage %>,
	"spage" : <%=spage %>,
	"epage" : <%=epage %>,
	"data" :[  
	<%
		for(int i =0 ; i<list.size(); i++){
		BoardVO vo = list.get(i);
		if(i>0) out.print(",");
	%>		
		{
		"seq" : "<%=vo.getSeq() %>",
		"writer" : "<%=vo.getWriter() %>",
		"subject" : "<%=vo.getSubject() %>",
		"email" : "<%=vo.getMail() %>",
		"content" : "<%=vo.getContent().replaceAll("\r", "").replaceAll("\n","<br>") %>",
		"hit" : "<%=vo.getHit() %>",
		"date" : "<%=vo.getWdate() %>"
	}
	
	<%		
    	}
	%>
	]

}
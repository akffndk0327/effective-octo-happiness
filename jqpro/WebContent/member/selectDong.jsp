<%@page import="kr.or.ddit.member.vo.ZipVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
[
<%
	List<ZipVO> list = (List<ZipVO>)request.getAttribute("list");
%>
	
<%
// 서블릿에서 setattr~ 한 "list" -key값
for(int i = 0; i<list.size(); i ++){
	ZipVO vo = list.get(i);
	String bunji = vo.getBunji(); //미리 가져와 -> 번지가 null일 경우 없애ㅑ기 위해
	String gugun = vo.getGugun(); //구군이 null인 경우
	String dong = vo.getDong(); //동이 null인 경우 
	if(bunji ==null || gugun ==null || dong == null ) bunji =""; gugun =""; dong="";
	if(i>0) out.print(",");
%>
<%-- 여기서 json이 반복해서 만들어짐.  --%>
{
	"zip" : "<%=vo.getZipcode() %>",
	"sido": "<%=vo.getSido() %>",
	"gugun": "<%=vo.getGugun()%>",
	"dong": "<%=vo.getDong() %>",
	"addr" : "<%=vo.getSido() %> <%=vo.getGugun() %> <%=vo.getDong() %>",
	"bunji" : "<%=bunji %>"
}
<%-- 이걸 html success에 쓴다. --%>
<%
}
%>


]





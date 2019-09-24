<%@page import="kr.or.ddit.vo.MemberVO"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@page import="kr.or.ddit.db.ConnectionFactory"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>oneConnOneProcess</title>
</head>
<body>
<%
	long startTime = System.currentTimeMillis();
	// a001 유저의 기본 정보 조회 
	//1.db정보가져오려면 connection필요함 
	StringBuffer sql = new StringBuffer();
	sql.append("SELECT MEM_ID, MEM_NAME, MEM_HP ");
	sql.append("FROM MEMBER                      ");
	sql.append("WHERE MEM_ID=?                   ");
	MemberVO member = null;	
	try(
		Connection conn = ConnectionFactory.getConnection();
		PreparedStatement pstmt = conn.prepareStatement(sql.toString());
		//커넥션을 끊는게 아니라 반납함.=> 재활용 메모리공간 효율적 사용 가능 
	){
// 		조건문 필요, 리터럴 형태로 a001 가야해 
	//파라미터 정하기 
	pstmt.setString(1,"a001");
	ResultSet rs = pstmt.executeQuery();
	// 위에서 sql쿼리문 결정되서 첫번째꺼 사용함. 두번째꺼즌 동적쿼리 임. 
	if(rs.next()){
			member = new MemberVO();
			member.setMem_id(rs.getString("MEM_ID"));
			member.setMem_name(rs.getString("MEM_NAME"));
			member.setMem_hp(rs.getString("MEM_HP"));
		}
	}
%>
<%=member %>
<%=System.currentTimeMillis()-startTime %>ms, 첨에 오래걸리는 커넥션을 미리 5개 만들어놔야해서 
</body>
</html>




























<%@page import="java.util.Map"%>
<%@page import="java.util.LinkedHashMap"%>
<%@page import="java.sql.ResultSetMetaData"%>
<%@page import="java.util.ArrayList"%>
<%@page import="kr.or.ddit.vo.DataBasePropertyVO"%>
<%@page import="java.util.List"%>
<%@page import="java.sql.SQLException"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>

<%@page import="java.sql.Connection"%>
<%@page import="java.sql.DriverManager"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>08/jdbcDesc.jsp</title>
</head>
<body>
   <h4>JDBC(Java Database Connectivity)</h4>
   <pre>
   Refactory ...  =>2teer =>3teer
   Facada 패턴을 이용하여 데이터베이스와 자바 어플리케이션을 연결하는 방법 : Driver

   1. 벤더가 제공하는 driver를 classpath에 추가 / 드라이버 설치 : ojdbc6.jar
   2. driver class 로딩(ClassLoader 사용)
   3. 이미 로딩된 driver를 통해 connection 사용
   4. Query 객체 생성
      1) Statement : Sql injection 취약(동적쿼리가 가능하기 때문)
      2) PreparedStatement : 동적쿼리 불가능 -> 정적쿼리 지향(쿼리 파라미터 활용)
      3) CallableStatement : 함수나 프로시저를 호출
   5. 쿼리 실행 (sql)
      1) ResultSet executeQuery : select
      2) int executeUpdate : insert, update, delete (row count 반환)
   6. 질의 결과 사용(raw data)
      /db로부터 데이터 받기(관계형, 자바 어플리케이션-object로 표현함)
       result-set => 커서 형태로 가져온다.
   **7. closs
      db에서 데이터 가져올 필요없는 쿼리, 커넥션 마무리
   
</pre>
 
<%
   Map<String, Object> dataMap = (Map)request.getAttribute("dataMap");
   List<DataBasePropertyVO> list = (List)dataMap.get("list");
   String[] headers = (String[])dataMap.get("headers");
%>
   <table border="1">
      <thead>
         <tr>
            <%
            for(String header : headers){
               %>
               <th><%=header %></th>
               <%
            }
            %>
         </tr>
      </thead>
      <tbody>   
         <%
//     if(list.size()==0){
    if(list.isEmpty()){
         %>
      
      <tr>
         <td colspan="3">조회된 결과가 없음</td>
      </tr>
      <%
      } else {
         for(int i=0; i<list.size() ; i++){
            %>
         <tr>
            <td><%=list.get(i).getProperty_name() %></td>
            <td><%=list.get(i).getProperty_value() %></td>
            <td><%=list.get(i).getDescription() %></td>
         </tr>
         <%
         }
      }
   %>
   </table>
</body>
</html>
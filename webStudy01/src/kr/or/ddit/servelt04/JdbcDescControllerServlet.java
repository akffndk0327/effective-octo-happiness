package kr.or.ddit.servelt04;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.db.ConnectionFactory;
import kr.or.ddit.vo.DataBasePropertyVO;
@WebServlet("/jdbcDesc")
public class JdbcDescControllerServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		   //3. drvier 사용하기 -> 인터페이스 이용
		  
		   Connection conn = null;
		   Statement stmt = null;
		   ResultSet rs = null;
		   try{
		      Map<String, Object> dataMap = new LinkedHashMap<>();
//		      pageContext.setAttribute("dataMap", dataMap);
		      req.setAttribute("dataMap", dataMap);
		      List<DataBasePropertyVO> list = new ArrayList<>();
		      dataMap.put("list", list);
		      conn=ConnectionFactory.getConnection(); //이거 가져오면 편함. 
		      req.setAttribute("list", list);
		      //4.
		      stmt = conn.createStatement();
		      //5.
		      StringBuffer sql = new StringBuffer();
		      sql.append("SELECT PROPERTY_NAME, PROPERTY_VALUE, DESCRIPTION");
		      sql.append(" FROM DATABASE_PROPERTIES");
		      rs = stmt.executeQuery(sql.toString());
		      
		      ResultSetMetaData rsmd = rs.getMetaData();
		      int colCnt = rsmd.getColumnCount();
		      String[] headers = new String[colCnt];
		      dataMap.put("headers", headers);
		      for(int idx=1; idx<colCnt; idx++){
		         headers[idx-1] = rsmd.getColumnName(idx);
		      }
		      
		      //6.
		      while (rs.next()) {
//		          rs.getString(1);
//		          rs.getString("PROPERTY_VALUE");
//		          rs.getString("DESCRIPTION");
		         DataBasePropertyVO vo = new DataBasePropertyVO();
		         vo.setProperty_name(rs.getString(1));
		         vo.setProperty_value(rs.getString("PROPERTY_VALUE"));
		         vo.setDescription(rs.getString("DESCRIPTION"));
		         
		         list.add(vo);
		      }

		   } catch (SQLException e) {
		      throw new RuntimeException(e);
		   } finally {
		      //7.
		      try {
		         if (rs != null) {
		            rs.close();
		         }
		         if (stmt != null) {
		            stmt.close();
		         }
		         if (conn != null) {
		            conn.close();
		         }
		      } catch (SQLException e) {
		         throw new RuntimeException(e);
		      }

		      //session end      
		   }
		
		
		String view = "/WEB-INF/views/jdbcDesc.jsp"; //1번
		req.getRequestDispatcher(view).forward(req,resp);
	
	}
}

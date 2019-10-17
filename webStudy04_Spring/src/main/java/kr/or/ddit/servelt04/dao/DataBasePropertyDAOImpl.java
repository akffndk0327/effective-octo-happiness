package kr.or.ddit.servelt04.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.dbcp2.ConnectionFactory;

import kr.or.ddit.vo.DataBasePropertyVO;

public class DataBasePropertyDAOImpl implements IDatabasePropertyDAO {

	@Override
	public List<DataBasePropertyVO> selectDataBasePropertyList(Map<String, Object> dataMap) {
		   //3. drvier 사용하기 -> 인터페이스 이용		  
		   Connection conn = null;
		   Statement stmt = null;
		   ResultSet rs = null;
		   try{
			   List<DataBasePropertyVO> list = new ArrayList<>();
		      dataMap.put("list", list);
		      
		      conn=ConnectionFactory.getConnection(); //ConnectionFactory에서 받아와이거 가져오면 편함. 
		     
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
		         list.add(vo);
		         vo.setProperty_name(rs.getString(1));
		         vo.setProperty_value(rs.getString("PROPERTY_VALUE"));
		         vo.setDescription(rs.getString("DESCRIPTION"));
		         
		      }
		      	return list;
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
		
	}

}

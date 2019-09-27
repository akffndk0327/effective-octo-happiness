package kr.or.ddit.servelt04;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.db.ConnectionFactory;
import kr.or.ddit.servelt04.dao.DataBasePropertyDAOImpl;
import kr.or.ddit.servelt04.dao.IDatabasePropertyDAO;
import kr.or.ddit.servelt04.service.DataBasePropertyServiceImpl;
import kr.or.ddit.servelt04.service.IDataBasePropertyService;
import kr.or.ddit.vo.DataBasePropertyVO;
@WebServlet("/jdbcDesc")
public class JdbcDescControllerServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//dao의존성데이터 필요
//		IDatabasePropertyDAO dao =new DataBasePropertyDAOImpl();
		//로그남기면서로직가져오기 -> 서비스 필요 
		IDataBasePropertyService service = new DataBasePropertyServiceImpl();
		
		Map<String, Object> dataMap = new HashMap<String, Object>();
		
		service.readAndLoggingDataBaseProperty(dataMap);
		
//		List<DataBasePropertyVO> list = dao.selectDataBasePropertyList(dataMap);
		//헤더는 어떻게 받아오지,,,? => callbyereference?
		
		//서비스실행되면서 어디서 파일 가져왓는지 신경x 로직실행되는것만 ! 
		
		req.setAttribute("dataMap", dataMap);
		
		String view = "/WEB-INF/views/jdbcDesc.jsp"; //1번
		req.getRequestDispatcher(view).forward(req,resp);
	
	}
}

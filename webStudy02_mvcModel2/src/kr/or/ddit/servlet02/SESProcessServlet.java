package kr.or.ddit.servlet02;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
@WebServlet("/sesProcess") //서버사이트 방식 
public class SESProcessServlet extends HttpServlet {
	@Override //get : 기본 메서드
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//파라미터 꺼내기 =>request필요해
		String sesMember = req.getParameter("sesMember"); //jsp에서 "sesMember"을 받아 옴 
		Map<String,String[]> memberMap = new LinkedHashMap<>();  //String[] : pk제외한 종속  LinkedHashMap : 순서있는걸로 접근한느 api이다.
		memberMap.put("a001", new String[]{"바다","/ses/bada.jsp"}); //entry
		memberMap.put("a002", new String[]{"유진","/ses/yujin.jsp"});
		memberMap.put("a003", new String[]{"슈","/ses/shoe.jsp"});
		if(StringUtils.isNoneBlank(sesMember)){ //파라미터 넘어온걸 의미함 .
			//어느 엔트리에 포함? 아예 미포함? 확인하기 ! 
			if(!memberMap.containsKey(sesMember)){ //파라미터 넘어왓는데 어디에도 없다는 의미 => 멤버없다. 서비스 불가능 ! 
				//404(클라이언트 실수)에러 출력해야해.
				resp.sendError(HttpServletResponse.SC_NOT_FOUND,"해당 멤버는 존재하지 않음 .");
				return; 
			}
			String[] record = memberMap.get(sesMember);
			String path = "/WEB-INF"+record[1];
//	 		String path = "/05"+record[1];
			RequestDispatcher rd = req.getRequestDispatcher(path); //디스패치방식 path : 서버가 ㅅ용 
			rd.forward(req, resp);	//forward : 분기한 이후 도착지에서만 응답전송(완전한위임)클라이언트는 a가 보낸줄 알지만 b가 정보 보낸거
//	 		rd.include(req, resp); 	//include : 분기한 도착지의 결과 데이터를 가지로 복귀. b가 a게 응답 데이터를 줌.그리고 a가 클라이언트에게 응답함.  
//	 		resp.sendRedirect(req.getContextPath()+path); //로케이션이후의 파라미터여서 null
			//location L
			return;
		}else { //? 서블릿 동작하려면 필수 파라미터 와야하는데 안와서 에러메세지 ! 
			resp.sendError(400,"어떤 멤버가 필요한가");
			
			
		}

	}
}

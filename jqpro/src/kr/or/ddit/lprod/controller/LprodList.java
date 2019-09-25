package kr.or.ddit.lprod.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.lprod.service.ILprodService;
import kr.or.ddit.lprod.service.LprodServiceImpl;
import kr.or.ddit.lprod.vo.LprodVO;

@WebServlet("/LprodList")
public class LprodList extends HttpServlet {
	public LprodList(){
		super();
	}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		ILprodService service = LprodServiceImpl.getInstance(); //객체 얻어옴.
		//서비스메소드 호출 - 결과값 가져온다.
		List<LprodVO> list = service.selectAll();
		
		//list를 request에 저장
		//jsp로 forward시킨다.
		
		req.setAttribute("list", list); //"list"가 jsp로 감
		
		RequestDispatcher disp = 
				req.getRequestDispatcher("/0910/lprodList.jsp"); //view로 보여줄 파일명 쓰기 
		disp.forward(req, resp);
		
	}
}

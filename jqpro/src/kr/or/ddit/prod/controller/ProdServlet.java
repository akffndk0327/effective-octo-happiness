package kr.or.ddit.prod.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import oracle.net.ano.Service;
import kr.or.ddit.prod.service.IprodService;
import kr.or.ddit.prod.service.ProdServiceImpl;
import kr.or.ddit.prod.vo.ProdVO;

/**
 * Servlet implementation class ProdServlet2
 */
@WebServlet("/ProdServlet")
public class ProdServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//0. 클라이언트의 요청시(ajax) prod_id 전달받고
		String lprodGu = request.getParameter("id");
		//1. service 객체 얻어오기
		IprodService service = ProdServiceImpl.getInstance();
		//2.service 메소드호출(getBuyLguList) - 리턴결과값 
		List<ProdVO> list = service.getBuyLguList(lprodGu);
		//3. 결과 list를 request객체에 저장
		request.setAttribute("list", list);
		
		//4. jsp로 이동 - prodNameList.jsp
//		RequestDispatcher disp = request.getRequestDispatcher("0916/prodNameList.jsp");//view로 보여줄 파일명 쓰기
		RequestDispatcher disp = request.getRequestDispatcher("0916/prodguList.jsp");//데이터 있음 없음 체크 할 거
		disp.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//0. 클라이언트의 요청시(ajax) prod_id 전달받고
		String prodId = request.getParameter("id");
		//1. service 객체 얻어오기
		IprodService service = ProdServiceImpl.getInstance();
		//2.service 메소드호출(getDetail)
		ProdVO vo = service.getDetail(prodId);
		//3. 결과 Vo를 request객체에 저장
		request.setAttribute("vo",vo);
		//4. jsp로 이동 - prodDetail.jsp
		RequestDispatcher disp = request.getRequestDispatcher("0916/prodDetail.jsp");
		disp.forward(request, response);
	}

}

package kr.or.ddit.member.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.member.service.IMemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.member.vo.ZipVO;

/**
 * Servlet implementation class SelectDong
 */
@WebServlet("/SelectDong")
public class SelectDong extends HttpServlet {
	private static final long serialVersionUID = 1L;


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//0.
		
		String inputDong = request.getParameter("dong");
		
		//1. 서비스 객체얻기
		IMemberService service = MemberServiceImpl.getInstance();
		
		//2. 메소드호추
		List<ZipVO> list = service.selectByDong(inputDong);
		
		//3. 결과값 거장
		request.setAttribute("list", list); //"list" 는 jsp로 보낼 파라미터 값.
		
		//4.jsp로 이동. "list"이름 가져가
		RequestDispatcher disp = request.getRequestDispatcher("member/selectDong.jsp");
		disp.forward(request, response);
		
	}

}

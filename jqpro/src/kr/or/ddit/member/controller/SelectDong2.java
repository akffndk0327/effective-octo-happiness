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
 * Servlet implementation class SelectDong2
 */
@WebServlet("/SelectDong2")
public class SelectDong2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String sido = request.getParameter("sido");
		String gugun = request.getParameter("gugun");
		
		IMemberService service = MemberServiceImpl.getInstance();
		//2메서드 호출하기
		ZipVO vo = new ZipVO();
		vo.setSido(sido);
		vo.setGugun(gugun);
		
		List<ZipVO> list= service.selectDong(vo);
		request.setAttribute("list", list);
		
		RequestDispatcher disp = request.getRequestDispatcher("member/selectDong.jsp");
		disp.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}

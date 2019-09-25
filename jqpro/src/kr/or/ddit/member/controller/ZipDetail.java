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

@WebServlet("/detail")
public class ZipDetail extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String sido = request.getParameter("sido");
		String gugun = request.getParameter("gugun");
		String dong = request.getParameter("dong");
		
		IMemberService service = MemberServiceImpl.getInstance();
		//2메서드 호출하기
		ZipVO vo = new ZipVO();
		vo.setSido(sido);
		vo.setGugun(gugun);
		vo.setDong(dong);
		
		List<ZipVO> list= service.selectAll(vo);
		request.setAttribute("list", list);
		
		RequestDispatcher disp = request.getRequestDispatcher("member/selectDong.jsp");
		disp.forward(request, response);
	}

}

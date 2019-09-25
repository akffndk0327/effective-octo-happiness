package kr.or.ddit.member.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.member.service.IMemberService;
import kr.or.ddit.member.service.MemberServiceImpl;

/**
 * Servlet implementation class CheckId
 */
@WebServlet("/CheckId")
public class CheckId extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//0.  ajax에서 입력한 id를 받는다 
		String inputId = request.getParameter("id");
		
		//1. service객체 얻기 
		IMemberService  service = MemberServiceImpl.getInstance();
		
		//2. 메소드 호출 - 결과값 a001 , null 
		String getId = service.selectByid(inputId);
		
		//3. 결과값을 request에 저장
		request.setAttribute("getId", getId);
		request.setAttribute("inputId", inputId);
		
		//4. jsp로 이동 
		RequestDispatcher  disp =
				request.getRequestDispatcher("member/checkId.jsp");
		
		disp.forward(request, response);
	}

}

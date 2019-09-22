package kr.or.ddit.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.or.ddit.member.service.IMemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.vo.MemberVO;

@WebServlet("/mypage")
public class MypageServlet extends HttpServlet {
	//1. 싱글톤...
	IMemberService service = new MemberServiceImpl();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//2. 세션받기
		HttpSession session = req.getSession();
		if(session.isNew()) {
			//비정상요청 발생 한거 상태코드로 전달 
			resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
			return;
		}
		MemberVO authMember = (MemberVO)session.getAttribute("authMember");
		String viewName = null;
		boolean redirect = false; //..왜? 
		if(authMember==null) {
			String message ="마이페이지는 로그인이 필요함.";
			session.setAttribute("message", message); //로그인 폼 jsp에서 보여줘야헤 
			viewName ="/login"; //경로 신경 ㅆ쓸피요없요 req.getContextPath() => 밑으로 빼서
 			redirect = true;
		}else {
			MemberVO savedMember = service.retrieveMember(authMember);
			req.setAttribute("savedMember", savedMember);
			viewName ="member/mypage";
		}
		
		if(redirect) {//redirect 상태에 따라 달라져
			resp.sendRedirect(req.getContextPath()+viewName); //나갓다가 다시들어옴. 
		}else {
			String prefix = "/WEB-INF/views/";
			String suffix = ".jsp";
			viewName = prefix + viewName + suffix;
			req.getRequestDispatcher(viewName).forward(req,resp); 
			
		}
		//인즈에 성공햇을때엉디ㅔ 뭘 넣는가 
		//세션없어 -리쿼스트에서 ㅓ꺼내 - 
		//1. 이요청이 클라이언트의 최초의 요청은 아님.
		//현제 세션이 이제 막 만ㄷ르어졋다면 정상적
		//2. 로그인 확인해
	}
}

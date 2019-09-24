package kr.or.ddit.common.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;

import kr.or.ddit.member.exception.NotAuthenticatedException;
import kr.or.ddit.member.exception.UserNotFoundException;
import kr.or.ddit.member.service.AuthenticateServiceImpl;
import kr.or.ddit.member.service.IAuthenticateService;
import kr.or.ddit.utils.CookieUtil;
import kr.or.ddit.vo.MemberVO;

@WebServlet("/login")
public class LoginControllerServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//쿠기 0917
		//전략의 주입자?
		String saveId = new CookieUtil(req).getCookieValue("idCookie");
		req.setAttribute("saveId", saveId); //scope로 forward할때 req로 같이 넘어가 ㅁ
		//1 요청 분석 2. 가공 3. UI제공  4 UI선택5. ui 전달 :
		String viewName = "/WEB-INF/views/login/loginForm.jsp";
		req.getRequestDispatcher(viewName).forward(req,resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//1. 파라메터잡기 2. 검증 3.인증 
		String mem_id = req.getParameter("mem_id");
		String mem_pass = req.getParameter("mem_pass");
		
		//검증
		if(StringUtils.isBlank(mem_id) || StringUtils.isBlank(mem_pass)) { //필수파라미터 누락 됏을때 
			resp.sendError(400, "아이디나 비번 누락 ");
			return;
		}
		//인증이 뒤섞여 => 분리하기 
		HttpSession session = req.getSession(); //세션은 최초의 요청 이루어질때. false : 요청들어오면 만들고 빈칸은 true  
		//아이디와 비번이 동일하면 성공 !
		if(session ==null || session.isNew()) { //여기에 걸리면 정상적 절차 아님 .
			resp.sendError(400,"로그인 철자 이상한뎁...");
			return;
		}
		//인증성공실패
		IAuthenticateService service = new AuthenticateServiceImpl(); 
		try {
			MemberVO savedMember = service.authenticate(new MemberVO(mem_id, mem_pass));
			//쿠키 0917
			String checkbox = req.getParameter("idSave");
			Cookie idCookie = CookieUtil.createCookie("idCookie", mem_id);
			int maxAge= 0;
			if("idSave".equals(checkbox)) {
				//1.어디에 저장할지
//				Cookie idCookie = CookieUtil.createCookie("idCookie", mem_id);
				//2. 언제까지 기억할지
				//3.응답으로 보내기 resp.addCookie(idCookie);
				maxAge = 60*60*24*2;
			}
			idCookie.setMaxAge(maxAge);
			resp.addCookie(idCookie);
			
			session.setAttribute("authMember", savedMember); // mem_id : scope. 세션스코프에 authMember없으면 로그인 안한거
			//이동 방식 
			resp.sendRedirect(req.getContextPath() + "/");
		} catch (UserNotFoundException | NotAuthenticatedException e) {
			session.setAttribute("message", e.getMessage());
			resp.sendRedirect(req.getContextPath() + "/login");

//		}
//		//아이디비번 동일하면 서공
//		if(mem_id.equals(mem_pass)) { //내 세션 유지 하면서 로그인정보 남아잇어야해 
////			session.setAttribute("authMember", mem_id ); //mem_id : scope. 세션스코프에 authMember없으면 로그인 안한거
////			//00이 적용되면 dispath 나 redirect가 적용됨 .
////			//이동방식 : request  남길 필요없어.
////			resp.sendRedirect(req.getContextPath()+"/"); //인덱스가 웰컴페이지인 경우 "/"씀
//		}else {
//			//1,로그인 페이지 이동시 어떤 방식을 ㅗ이동 ? ==> 무조건 인증인가에 실패하면 정상적인 사람아니라고 생각하고 redirect
////			resp.sendRedirect(req.getContextPath()+"/login");
////			//2. 아이디 비번틀렷을떄 메세지 어떻게 전달?
////			String message ="아이디나 비번 오류";
////			//세션 =>최소한의 이동 
////			session.setAttribute("message", message); //메세지는 jsp에서 꺼내야햄 
		}
		
	}
}
















package kr.or.ddit.common.controller;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.ddit.common.events.AuthenticateSuccessEvent;
import kr.or.ddit.member.exception.NotAuthenticatedException;
import kr.or.ddit.member.exception.UserNotFoundException;
import kr.or.ddit.member.service.IAuthenticateService;
import kr.or.ddit.utils.CookieUtil;
import kr.or.ddit.vo.MemberVO;

//@WebServlet("/login")
@Controller
public class LoginController {
	@Inject //1022 주입까지 받음 
	ApplicationEventPublisher publisher;
	
	
	@RequestMapping("/login")
	public String loginForm(){
//		String saveId = new CookieUtil(req).getCookieValue("idCookie");
//		req.setAttribute("saveId", saveId); //scope로 forward할때 req로 같이 넘어가 ㅁ
		//1 요청 분석 2. 가공 3. UI제공  4 UI선택5. ui 전달 :
		String viewName = "login/loginForm";
		return viewName;
	}
	
	@Inject
	IAuthenticateService service;
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String doPost(@RequestParam(required=true) String mem_id, 
						 @RequestParam(required=true) String mem_pass,
						 @RequestParam(name="idSave", required=false ) String checkbox,
						 HttpSession session, HttpServletResponse resp) throws IOException  {
		//인증이 뒤섞여 => 분리하기 
		//아이디와 비번이 동일하면 성공 !
		if(session ==null || session.isNew()) { //여기에 걸리면 정상적 절차 아님 .
			resp.sendError(400,"로그인 철자 이상한뎁...");
			return null;
		}
		//인증성공실패
		
		String viewName=null;
	
		try {
			MemberVO savedMember = service.authenticate(new MemberVO(mem_id, mem_pass));
			//쿠키 0917
			Cookie idCookie = CookieUtil.createCookie("idCookie", mem_id);
			int maxAge= 0;
			if("idSave".equals(checkbox)) {
				maxAge = 60*60*24*2;
			}
			idCookie.setMaxAge(maxAge);
			resp.addCookie(idCookie);
			publisher.publishEvent(
					new AuthenticateSuccessEvent(this, savedMember)); //우리만의 이벤트를 하나 만들자 ! ^_^ target이 클래스
			session.setAttribute("authMember", savedMember); // mem_id : scope. 세션스코프에 authMember없으면 로그인 안한거
			//이동 방식 
			viewName = "redirect:/";
		} catch (UserNotFoundException | NotAuthenticatedException e) {
			session.setAttribute("message", e.getMessage());
			viewName = "redirect:/login";

		}
		return viewName;
		
	}
}
















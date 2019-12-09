package kr.or.ddit.common.controller;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.or.ddit.common.events.LogoutSuccessEvent;
import kr.or.ddit.vo.MemberVO;
//@WebServlet("/logout")
@Controller
public class LogOutController{
	//1022
	@Inject
	ApplicationEventPublisher publisher;
	
	
	@RequestMapping(value = "/logout", method =RequestMethod.POST)
	public String doPost(HttpSession session,HttpServletResponse resp) throws IOException {
		//세션만료 4가지 이벤트
		/*1. 타임아웃 30분이루	2. 브라우저 종료3.. 쿠키 삭젷4. 명시적 세션 자체를 invalidate*/
//		HttpSession session = req.getSession();
		if(session.isNew()) {
			resp.sendError(400);
			return null;
		}
//		1022 이벤트 발행하기
		MemberVO authMember = (MemberVO) session.getAttribute("authMember");
		publisher.publishEvent(new LogoutSuccessEvent(this, authMember));
		
		
		session.invalidate(); //세션만료위해 강제 종료. request도 종료  로그인 상태 풀림 
		//페이지 이동 : 로그인 화면 or 
//		resp.sendRedirect(req.getContextPath()+"/");
		return "redirect:/";
		
	}
}

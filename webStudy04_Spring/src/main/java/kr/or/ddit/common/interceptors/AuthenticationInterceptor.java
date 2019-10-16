package kr.or.ddit.common.interceptors;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import kr.or.ddit.member.service.IMemberService;

public class AuthenticationInterceptor extends HandlerInterceptorAdapter {
	//필터는 컨테이너 밖에있어 그래서 bean으 ㄹ인젝션 못해
//	인터럽트는 컨테이너에 잇어서 멤버서비스 이용가능함.
	@Inject
	IMemberService service;
//	마이페이지요청한 사람이 로그인햇는지 확인 하는 작업 
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
//		인증여부확인하고 통과 
		HttpSession session =  request.getSession();
		boolean pass = true;
		pass = !session.isNew(); // 방금 만든애가 비번 틀렷다? return false; -> !session.isNew() 
		//2. authmember잇나 보기
		if(pass) {
			Object authMember = session.getAttribute("authMember");
			pass = authMember!=null;
		}

		if(!pass) { //비번이 맞지 않으면
//			로그인페이지로 보내기
			response.sendRedirect(request.getContextPath()+"/login");
		}
		
		return pass ; //핸들러로 가면안됨
	}

}

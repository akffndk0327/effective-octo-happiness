package kr.or.ddit.common.security;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

import kr.or.ddit.common.events.AuthenticateSuccessEvent;
import kr.or.ddit.vo.MemberVO;

public class CustomAuthenticateSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {
	@Inject
	ApplicationEventPublisher publisher;
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws ServletException, IOException {
		MemberVO authMember = (MemberVO) authentication.getPrincipal();
		publisher.publishEvent(new AuthenticateSuccessEvent(this, authMember));
		super.onAuthenticationSuccess(request, response, authentication);
	}
}


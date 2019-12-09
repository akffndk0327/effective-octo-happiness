package kr.or.ddit.common.events;

import org.springframework.context.ApplicationEvent;

import kr.or.ddit.vo.MemberVO;
/**
 * @author 허민지
 * @since 2019. 11. 5.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                          수정자               수정내용
 * --------     --------    ----------------------
 * 2019. 11. 5.      허민지       최초작성
 * Copyright (c) 2019 by DDIT All right reserved
 * </pre>
 */
public class AuthenticateSuccessEvent extends ApplicationEvent {
	private MemberVO authMember;

	public AuthenticateSuccessEvent(Object target, MemberVO authMember) {
		super(target);
		this.authMember = authMember;
	}
	
	public MemberVO getAuthMember() {
		return authMember;
	}
}

package kr.or.ddit.member.service;

import kr.or.ddit.vo.AccountVO;
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
 * 2019. 11. 20.     허민지       encodePassword추가
 * Copyright (c) 2019 by DDIT All right reserved
 * </pre>
 */
public interface IAuthenticateService {
	public void encodePassword(MemberVO member);
	public void encodePassword(AccountVO account);
	public MemberVO authenticate(MemberVO member);
}

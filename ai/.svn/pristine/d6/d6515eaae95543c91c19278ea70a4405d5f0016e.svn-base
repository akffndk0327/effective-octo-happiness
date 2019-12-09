package kr.or.ddit.common.security.auth;

import javax.inject.Inject;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import kr.or.ddit.common.security.dao.ISecuredServiceDAO;
import kr.or.ddit.vo.MemberVO;

@Component
public class AccessControlUtils {
	
	@Inject
	ISecuredServiceDAO serviceDAO;
	
	public boolean checkBoardTypeAuth(Authentication authentication, String uri) {
//		#board_type == 'B02' ? hasRole('ROLE_ADMIN') : permitAll
		boolean pass = false;
		if(uri.contains("B02")) {
			if(authentication.getPrincipal() instanceof MemberVO) {
				MemberVO user = (MemberVO) authentication.getPrincipal();
				pass = user.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN"));
			}
		}else {
			pass = true;
		}
		return pass;
	}
}

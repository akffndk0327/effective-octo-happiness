package kr.or.ddit.member.service;

import javax.inject.Inject;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;

import kr.or.ddit.member.dao.IMemberDAO;
import kr.or.ddit.vo.MemberVO;

public class CustomUserDetailService implements UserDetailsService {
	@Inject
	IMemberDAO memberDAO;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		MemberVO member = memberDAO.selectAccount(username);
		if (member == null) {
			throw new UsernameNotFoundException(username + "는 존재하지 않는 아이디 입니다.");
		}
		MemberVO savedMember = memberDAO.selectMember(member);
		if("Y".equals(savedMember.getMemDelete())) {
			throw new UsernameNotFoundException(savedMember.getMemId() + "는 탈퇴한 회원입니다.");
		}
		return savedMember;
	}
	
}
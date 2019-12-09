package kr.or.ddit.member.service;

import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.stereotype.Service;

import kr.or.ddit.member.dao.IMemberDAO;
import kr.or.ddit.member.exception.NotAuthenticatedException;
import kr.or.ddit.member.exception.UserNotFoundException;
import kr.or.ddit.vo.AccountVO;
import kr.or.ddit.vo.MemberVO;

@Service
public class AuthenticateServiceImpl implements IAuthenticateService{
	@Inject
	IMemberDAO dao;
	
	@Inject
	DelegatingPasswordEncoder passwordEncoder;
	
	@Override
	public MemberVO authenticate(MemberVO member) {
		MemberVO savedMember = dao.selectMember(member);
		if(savedMember==null) {
			throw new UserNotFoundException("존재하지 않는 아이디 입니다.");
		}
		if(!passwordEncoder.matches(member.getMemPass(), savedMember.getMemPass())) {
			throw new NotAuthenticatedException("잘못된 비밀번호 입니다.");
		}
		return savedMember;
	}

	@Override
	public void encodePassword(MemberVO member) {
		if(StringUtils.isBlank(member.getMemPass())) return;
		member.setMemPass(passwordEncoder.encode(member.getMemPass()));
	}

	@Override
	public void encodePassword(AccountVO account) {
		if(StringUtils.isBlank(account.getMemPass())) return;
		account.setMemPass(passwordEncoder.encode(account.getMemPass()));
	}
}

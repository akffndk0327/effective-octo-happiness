package kr.or.ddit.member.service;

import kr.or.ddit.vo.MemberVO;

public interface IAuthenticateService {
	//인증하기위한 로직 
	public MemberVO authenticate(MemberVO member); //앞에vo 로그인성공해서 그사람의 정보 조회됐을때  
}

package kr.or.ddit.member.service;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import kr.or.ddit.member.dao.IMemberDAO;
import kr.or.ddit.member.dao.MemberDAOImpl_JDBC;
import kr.or.ddit.member.dao.MemberDaoImpl;
import kr.or.ddit.member.exception.NotAuthenticatedException;
import kr.or.ddit.member.exception.UserNotFoundException;
import kr.or.ddit.vo.MemberVO;

@Service
public class AuthenticateServiceImpl implements IAuthenticateService {
	//서비스와 다오 의존상태만들기  
//	private IMemberDAO dao = new MemberDAOImpl(); //daoImpl에서 구현하닌까 dao는 구현될걸 연결 
//	IMemberDAO dao = new MemberDaoImpl(); //daoImpl에서 구현하닌까 dao는 구현될걸 연결 
	
	@Inject //방법3 1015
	IMemberDAO dao; //daoImpl에서 구현하닌까 dao는 구현될걸 연결 
	
	//인증로직 구현 => 아이디가 틀렷는지 비번이 틀렷는지 확인 
	@Override
	public MemberVO authenticate(MemberVO member) {
		MemberVO savedMember = dao.selectMember(member);
		//경우에 따라 존재하지 않을때 => 인증실패시
		if(savedMember ==null) {
			throw new UserNotFoundException("아이디 잘못됐음");
		}else {
			
		}
		if(!savedMember.getMem_pass().equals(member.getMem_pass())) {
			//다른종류의 예외필요
			throw new NotAuthenticatedException( "비번틀림");
		}
		return savedMember;
	}

}

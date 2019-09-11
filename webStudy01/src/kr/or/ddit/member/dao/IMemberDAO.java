package kr.or.ddit.member.dao;

import kr.or.ddit.vo.MemberVO;

public interface IMemberDAO {
	public MemberVO selectMember(MemberVO member); //앞에 Vo는 db에서 조회한거  뒤에 vo는 클라이언트가 입력한거 
}

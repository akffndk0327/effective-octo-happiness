package kr.or.ddit.member.dao;

import org.junit.Test;
import static org.junit.Assert.*;
import kr.or.ddit.vo.MemberVO;

public class MemberDAOImplTest {
	IMemberDAO dao = MemberDAOImpl_JDBC.getInstance();
	@Test
	public void testSelectMember() {
		MemberVO member = dao.selectMember(new MemberVO("c001",null)); //가정
		//가정이 맞는지 확인해야해 
//		assertNotNull(member);
		assertNull(member);
		
	}

	@Test
	public void testInsertMember() {
	}

}

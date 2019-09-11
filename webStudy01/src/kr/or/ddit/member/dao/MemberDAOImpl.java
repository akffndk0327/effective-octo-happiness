package kr.or.ddit.member.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import kr.or.ddit.db.ConnectionFactory;
import kr.or.ddit.vo.MemberVO;

public class MemberDAOImpl implements IMemberDAO {

	@Override
	public MemberVO selectMember(MemberVO member) {
		MemberVO savedMember = null;
		// 아이디 비번 이름 휴대폰 이메일
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT MEM_ID, MEM_PASS, MEM_NAME, MEM_HP, MEM_MAIL, MEM_ADD1");
		sql.append(" FROM MEMBER");
		sql.append(" WHERE MEM_ID= ? "); // #mem_id# : 인라인파라미터
//		sql.append(" AND MEM_PASS= ? "); //인증로직으로가서 아이디가 맞는지 비번이 맞는지 확인하기위해 주석 처리  
		try (Connection conn = ConnectionFactory.getConnection(); // ConnectionFactory에서 받아와이거 가져오면 편함.
				PreparedStatement pstmt = conn.prepareStatement(sql.toString());

		) {
			pstmt.setString(1, member.getMem_id());
//			pstmt.setString(2, member.getMem_pass());
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				savedMember = new MemberVO();
				savedMember.setMem_id(rs.getString("MEM_ID"));
				savedMember.setMem_name(rs.getString("MEM_NAME"));
				savedMember.setMem_pass(rs.getString("MEM_PASS"));
				savedMember.setMem_hp(rs.getString("MEM_HP"));
				savedMember.setMem_mail(rs.getString("MEM_MAIL"));
			}

			return savedMember;
		} catch (SQLException e) {
			throw new RuntimeException(e);

		}
	}
}

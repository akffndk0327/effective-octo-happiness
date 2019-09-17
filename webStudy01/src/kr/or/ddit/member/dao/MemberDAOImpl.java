package kr.or.ddit.member.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLClientInfoException;
import java.sql.SQLException;
import java.util.List;

import kr.or.ddit.db.ConnectionFactory;
import kr.or.ddit.enums.ServiceResult;
import kr.or.ddit.vo.MemberVO;

public class MemberDAOImpl implements IMemberDAO {
	private static MemberDAOImpl instance;
	private MemberDAOImpl(){}
	
	public static MemberDAOImpl getInstance() {// 다오임플 사용할 수있게 기본 생성자 메모리 공간 효율적으로 쓸려고...
		if(instance ==null) instance = new MemberDAOImpl();
		return instance; 
	}
	@Override
	public MemberVO selectMember(MemberVO member) {
		MemberVO savedMember = null;
		// 아이디 비번 이름 휴대폰 이메일 주소                                                                 "                                   
		StringBuffer sql = new StringBuffer();                                                            
		sql.append(" SELECT                                                                                     ");
		sql.append("    mem_id,    mem_pass,    mem_name,                                                      ");
		sql.append("    mem_regno1,    mem_regno2,    to_char(mem_bir,'yyyy-MM-dd') mem_bir,                   ");
		sql.append("    mem_zip,    mem_add1,    mem_add2,                                                     ");
		sql.append("    mem_hometel,    mem_comtel,    mem_hp,                                                 ");
		sql.append("    mem_mail,    mem_job,    mem_like,                                                     ");
		sql.append("    mem_memorial,                                                                          ");
		sql.append("    to_char(mem_memorialday,'yyyy-MM-dd')mem_memorialday,    mem_mileage,                  ");
		sql.append("    mem_delete                                                                             ");
		sql.append(" FROM    MEMBER                                                                             ");
		sql.append(" WHERE MEM_ID = ?                                                                           ");                             
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
				//reflection 이후 작성.
			}

			return savedMember;
		} catch (SQLException e) {
			throw new RuntimeException(e);

		}
	}

	@Override
	public int insertMember(MemberVO member) {
		int insertMember = 0;
		if(insertMember>0) {
			
		}
		return insertMember;
	}

	@Override
	public List<MemberVO> selectMemeberList() {
		List<MemberVO> list =null;
		list= instance.selectMemeberList();
		return list;
	}

	@Override
	public int updateMember(MemberVO member) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteMember(MemberVO member) {
		// TODO Auto-generated method stub
		return 0;
	}
}

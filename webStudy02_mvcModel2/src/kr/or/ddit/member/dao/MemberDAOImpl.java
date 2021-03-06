package kr.or.ddit.member.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLClientInfoException;
import java.sql.SQLException;
import java.util.ArrayList;
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
		sql.append(" WHERE MEM_ID = ?  and MEM_DELETE IS NULL                                                                        ");                             
//		sql.append(" AND MEM_PASS= ? "); //인증로직으로가서 아이디가 맞는지 비번이 맞는지 확인하기위해 주석 처리  
		try (
			Connection conn = ConnectionFactory.getConnection(); // ConnectionFactory에서 받아와이거 가져오면 편함.
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
		) {
			pstmt.setString(1, member.getMem_id());
//			pstmt.setString(2, member.getMem_pass());
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				savedMember = new MemberVO();
				savedMember.setMem_id(rs.getString("MEM_ID"));
				savedMember.setMem_pass(rs.getString("MEM_PASS"));
				savedMember.setMem_name(rs.getString("MEM_NAME"));
				savedMember.setMem_regno1(rs.getString("MEM_REGNO1"));
				savedMember.setMem_regno2(rs.getString("MEM_REGNO2"));
				savedMember.setMem_bir(rs.getString("MEM_BIR"));
				savedMember.setMem_zip(rs.getString("MEM_ZIP"));
				savedMember.setMem_add1(rs.getString("MEM_ADD1"));
				savedMember.setMem_add2(rs.getString("MEM_ADD2"));
				savedMember.setMem_hometel(rs.getString("MEM_HOMETEL"));
				savedMember.setMem_comtel(rs.getString("MEM_COMTEL"));
				savedMember.setMem_hp(rs.getString("MEM_HP"));
				savedMember.setMem_mail(rs.getString("MEM_MAIL"));
				savedMember.setMem_job(rs.getString("MEM_JOB"));
				savedMember.setMem_like(rs.getString("MEM_LIKE"));
				savedMember.setMem_memorial(rs.getString("MEM_MEMORIAL"));
				savedMember.setMem_memorialday(rs.getString("MEM_MEMORIALDAY"));
				savedMember.setMem_mileage(rs.getInt("MEM_MILEAGE"));
				savedMember.setMem_delete(rs.getString("MEM_DELETE"));
				//reflection 이후 작성.
				
				//부트스트랩 =제이쿼리플러그인= css플랫폼 =>css코드 로딩해야함.
			}

			return savedMember;
		} catch (SQLException e) {
			throw new RuntimeException(e);

		}
	}

	@Override
	public int insertMember(MemberVO member) {
		StringBuffer sql = new StringBuffer();
		sql.append(" INSERT INTO MEMBER (  ");
		sql.append("     MEM_ID,    MEM_PASS,    MEM_NAME,    MEM_REGNO1,      ");
		sql.append("     MEM_REGNO2,    MEM_BIR,    MEM_ZIP,    MEM_ADD1,      ");
		sql.append("     MEM_ADD2,    MEM_HOMETEL,    MEM_COMTEL,    MEM_HP,   ");
		sql.append("     MEM_MAIL,    MEM_JOB,    MEM_LIKE,    MEM_MEMORIAL,   ");
		sql.append("     MEM_MEMORIALDAY,    MEM_MILEAGE       ");
		sql.append(" ) VALUES (                                                ");
		sql.append(" 	?,    ?,    ?,    ?,                                   ");
		sql.append("     ?,    ?,    ?,    ?,                                  ");
		sql.append("     ?,    ?,    ?,    ?,                                  ");
		sql.append("     ?,    ?,    ?,    ?,                                  ");
		sql.append("     ?,    2000		                                   ");
		sql.append(" )                                                         ");
		try (Connection conn = ConnectionFactory.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql.toString());) {
			// 쿼리 파라미터 설정
			int idx = 1;
			pstmt.setString(idx++, member.getMem_id());
			pstmt.setString(idx++, member.getMem_pass());
			pstmt.setString(idx++, member.getMem_name());
			pstmt.setString(idx++, member.getMem_regno1());
			pstmt.setString(idx++, member.getMem_regno2());
			pstmt.setString(idx++, member.getMem_bir());
			pstmt.setString(idx++, member.getMem_zip());
			pstmt.setString(idx++, member.getMem_add1());
			pstmt.setString(idx++, member.getMem_add2());
			pstmt.setString(idx++, member.getMem_hometel());
			pstmt.setString(idx++, member.getMem_comtel());
			pstmt.setString(idx++, member.getMem_hp());
			pstmt.setString(idx++, member.getMem_mail());
			pstmt.setString(idx++, member.getMem_job());
			pstmt.setString(idx++, member.getMem_like());
			pstmt.setString(idx++, member.getMem_memorial());
			pstmt.setString(idx++, member.getMem_memorialday());
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	@Override
	public List<MemberVO> selectMemeberList() {
		List<MemberVO> list = new ArrayList<MemberVO>();
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT MEM_ID, MEM_NAME, MEM_HP, MEM_MAIL, MEM_ADD1, MEM_MILEAGE ");
		sql.append(" FROM MEMBER ");

		try (
				Connection conn = ConnectionFactory.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql.toString());
		) {
			ResultSet rs = pstmt.executeQuery(); // 쿼리문을 실행...

			while (rs.next()) {
				MemberVO member = new MemberVO();
				list.add(member);
				member.setMem_id(rs.getString("MEM_ID"));
				member.setMem_name(rs.getString("MEM_NAME"));
				member.setMem_hp(rs.getString("MEM_HP"));
				member.setMem_mail(rs.getString("MEM_MAIL"));
				member.setMem_add1(rs.getString("MEM_ADD1"));
				member.setMem_mileage(rs.getInt("MEM_MILEAGE"));
			}
			return list;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public int updateMember(MemberVO member) {
		StringBuffer sql = new StringBuffer();
	      sql.append(" UPDATE MEMBER SET   ");
	      sql.append(" MEM_NAME= ?,          ");
	      sql.append(" MEM_ZIP= ?,           ");
	      sql.append(" MEM_ADD1= ?,          ");
	      sql.append(" MEM_ADD2= ?,          ");
	      sql.append(" MEM_HOMETEL= ?,       ");
	      sql.append(" MEM_COMTEL= ?,        ");
	      sql.append(" MEM_HP= ?,            ");
	      sql.append(" MEM_MAIL= ?,          ");
	      sql.append(" MEM_JOB= ?,           ");
	      sql.append(" MEM_LIKE= ?,          ");
	      sql.append(" MEM_MEMORIAL= ?,      ");
	      sql.append(" MEM_MEMORIALDAY= ? ");
	      sql.append(" WHERE MEM_ID=? ");
	      
	      try (
	    	Connection conn = ConnectionFactory.getConnection();
	         PreparedStatement pstmt = conn.prepareStatement(sql.toString());
	        ){
	         //쿼리파라미터 세팅
	         
	         int idx=1;
	         
	         pstmt.setString(idx++, member.getMem_name());
	         pstmt.setString(idx++, member.getMem_zip());
	         pstmt.setString (idx++, member.getMem_add1());
	         pstmt.setString (idx++, member.getMem_add2());
	         pstmt.setString (idx++, member.getMem_hometel());
	         pstmt.setString (idx++, member.getMem_comtel());
	         pstmt.setString (idx++, member.getMem_hp());
	         pstmt.setString (idx++, member.getMem_mail());
	         pstmt.setString (idx++, member.getMem_job());
	         pstmt.setString (idx++, member.getMem_like());
	         pstmt.setString (idx++, member.getMem_memorial());
	         pstmt.setString (idx++, member.getMem_memorialday());
	         pstmt.setString (idx++, member.getMem_id());
	         
	         return pstmt.executeUpdate();
//	         return 0;
	      } catch (SQLException e) {
	         throw new RuntimeException(e);

	      }
	}

	@Override
	public int deleteMember(MemberVO member) {
		String pass="";
		StringBuffer sql = new StringBuffer();
		sql.append(" UPDATE MEMBER ");
		sql.append(" SET ");
		sql.append(" MEM_DELETE = 'Y' ");
		sql.append(" WHERE MEM_ID=?");
		//try(조건이 맞으면){ 여기 안을 이렇게 실행해라.}
		try (
		Connection conn = ConnectionFactory.getConnection();
		PreparedStatement pstmt = conn.prepareStatement(sql.toString());
		){
			//쿼리파라미터세팅 
			pstmt.setString(1 ,member.getMem_id());
//			pstmt.setString(1 ,member.getMem_pass());

			return pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e); 
		}
	}
}

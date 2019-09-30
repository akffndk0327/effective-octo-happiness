package kr.or.ddit.member.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import kr.or.ddit.db.mybatis.CustomSqlSessionFactoryBuilder;
import kr.or.ddit.vo.MemberVO;

public class MemberDaoImpl implements IMemberDAO {
	
	private SqlSessionFactory SqlSessionFactory 
				= CustomSqlSessionFactoryBuilder.getSqlSessionFactory(); //여기가 싱글톤 ! 

	@Override
	public int insertMember(MemberVO member) {
		//insert/ update/deele의 경우 autoCommit 설정이 없을떄,
		//commit 필요 ! 
		try(
				SqlSession sqlSession = SqlSessionFactory.openSession();
		){
			IMemberDAO mapper = sqlSession.getMapper(IMemberDAO.class);
//			return mapper.insertMember(member);
			int cnt = mapper.insertMember(member);
			sqlSession.commit();
			return cnt;
		}
	}

	@Override
	public List<MemberVO> selectMemeberList() {
		try(
				SqlSession sqlSession = SqlSessionFactory.openSession(); //close시켜야해서 try 안에 넣엇음.
			){
			IMemberDAO mapper = sqlSession.getMapper(IMemberDAO.class);
				return mapper.selectMemeberList();
			}
	}

	@Override
	public MemberVO selectMember(MemberVO member) {
		try(
			SqlSession sqlSession = SqlSessionFactory.openSession();
		){
//			sqlSession.selectOne("kr.or.ddit.member.dao.IMemberDAO.selectmember",member);
			IMemberDAO mapper = sqlSession.getMapper(IMemberDAO.class);
			return mapper.selectMember(member); //매개변수를 return => 타입안정성 높아. 벗 속도가 느려 
		}
	}

	@Override
	public int updateMember(MemberVO member) {
		try(
				SqlSession sqlSession = SqlSessionFactory.openSession();
				){
			
			IMemberDAO mapper = sqlSession.getMapper(IMemberDAO.class);
			int cnt = mapper.updateMember(member);
			sqlSession.commit();
			return cnt;
		}
	}

	@Override
	public int deleteMember(MemberVO member) {
		try(
				SqlSession sqlSession = SqlSessionFactory.openSession();
				){
			
			IMemberDAO mapper = sqlSession.getMapper(IMemberDAO.class);
			int cnt = mapper.deleteMember(member);
			sqlSession.commit();
			return cnt;
		}
	}

}

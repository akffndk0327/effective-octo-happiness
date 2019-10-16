package kr.or.ddit.alba.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.stereotype.Repository;

import kr.or.ddit.db.mybatis.CustomSqlSessionFactoryBuilder;
@Repository
public class CodeDaoImpl implements ICodeDao {
	SqlSessionFactory sessionFactory = 
			CustomSqlSessionFactoryBuilder.getSqlSessionFactory();

	@Override
	public List<Map<String, String>> selectLicense() {
		try(
			SqlSession sqlSession = sessionFactory.openSession();
		){
			return sqlSession.getMapper(ICodeDao.class).selectLicense();
		}
	}

	@Override
	public List<Map<String, String>> selectGrades() {
		try(
			SqlSession sqlSession = sessionFactory.openSession();
		){
			return sqlSession.getMapper(ICodeDao.class).selectGrades();
		}
	}

}
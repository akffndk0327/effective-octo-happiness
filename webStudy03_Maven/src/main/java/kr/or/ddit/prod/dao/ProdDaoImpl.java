package kr.or.ddit.prod.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import kr.or.ddit.db.mybatis.CustomSqlSessionFactoryBuilder;
import kr.or.ddit.vo.ProdVO;

public class ProdDaoImpl implements IProdDAO {
	private SqlSessionFactory SqlSessionFactory 
	= CustomSqlSessionFactoryBuilder.getSqlSessionFactory();
	
	@Override
	public int insertProd(ProdVO prod) {
		try(
			SqlSession sqlSession = SqlSessionFactory.openSession();
		){
			IProdDAO mapper = sqlSession.getMapper(IProdDAO.class);
			int cnt = mapper.insertProd(prod);
			sqlSession.commit();
			return cnt;
		}
		
	}

	@Override
	public List<ProdVO> selectProdList() {
		try (
			SqlSession sqlSession = SqlSessionFactory.openSession(); // close시켜야해서 try 안에 넣엇음.
		) {
			IProdDAO mapper = sqlSession.getMapper(IProdDAO.class);
			return mapper.selectProdList();
		}
	}

	@Override
	public ProdVO selectProd(String prod_id) {
		try(
			SqlSession sqlSession = SqlSessionFactory.openSession();	
		){
			IProdDAO mapper = sqlSession.getMapper(IProdDAO.class);
			return mapper.selectProd(prod_id);
		}
	}

	@Override //트랜젝션 종료가 필요해 
	public int updateProd(ProdVO prod) {
		try(
				SqlSession sqlSession = SqlSessionFactory.openSession();	
			){
				IProdDAO mapper = sqlSession.getMapper(IProdDAO.class);
				int cnt = mapper.updateProd(prod);
				sqlSession.commit();
				return cnt;
			}
	}
}

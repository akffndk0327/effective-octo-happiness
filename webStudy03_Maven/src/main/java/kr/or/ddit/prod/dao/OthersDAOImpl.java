package kr.or.ddit.prod.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import kr.or.ddit.db.mybatis.CustomSqlSessionFactoryBuilder;
import kr.or.ddit.vo.BuyerVO;

public class OthersDAOImpl implements IOthersDAO {
	private SqlSessionFactory sqlSessionFactory = CustomSqlSessionFactoryBuilder.getSqlSessionFactory();
	//원래 다오임플의 crud기능 외에 부가적인 기능을 하는 메서드 모아둔거 
	@Override
	public List<Map<String, Object>> selectLprodList() {
		try (SqlSession sqlSession = sqlSessionFactory.openSession();) {
			IOthersDAO mapper = sqlSession.getMapper(IOthersDAO.class);
			return mapper.selectLprodList();
		}
	}

	@Override
	public List<BuyerVO> selectBuyerList(String prod_lgu) {
		try (
				SqlSession sqlSession = sqlSessionFactory.openSession();
			) {
			IOthersDAO mapper = sqlSession.getMapper(IOthersDAO.class);
			return mapper.selectBuyerList(prod_lgu);

		}
	}

}

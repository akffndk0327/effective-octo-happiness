package kr.or.ddit.buyer.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import kr.or.ddit.buyer.vo.BuyerVO;
import kr.or.ddit.db.mybatis.CustomSqlSessionFactoryBuilder;

public class BuyerDaoImpl implements IBuyerDao {
	private SqlSessionFactory SqlSessionFactory = CustomSqlSessionFactoryBuilder.getSqlSessionFactory();// 싱글톤
	@Override
	public List<BuyerVO> selectNameList() {
		try(
			SqlSession sqlSession = SqlSessionFactory.openSession();	
		){
			IBuyerDao mapper = sqlSession.getMapper(IBuyerDao.class);
			return mapper.selectNameList();
		}
	}

	@Override
	public BuyerVO buyerDetail(String buyer_id) {
		try(
				SqlSession sqlSession = SqlSessionFactory.openSession();	
			){
				IBuyerDao mapper = sqlSession.getMapper(IBuyerDao.class);
				return mapper.buyerDetail(buyer_id);
			}
	}

	@Override
	public int buyerInsert(BuyerVO vo) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int buyerDelete(String buyer_id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int buyerUpdate(BuyerVO vo) {
		// TODO Auto-generated method stub
		return 0;
	}

}

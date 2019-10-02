package kr.or.ddit.buyer.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import kr.or.ddit.buyer.vo.BuyerVO;
import kr.or.ddit.buyer.vo.PagingInfoVO;
import kr.or.ddit.db.mybatis.CustomSqlSessionFactoryBuilder;

public class BuyerDaoImpl implements IBuyerDao {
	private SqlSessionFactory SqlSessionFactory = CustomSqlSessionFactoryBuilder.getSqlSessionFactory();// 싱글톤
	
	
	@Override
	public int selectBuyerCount(PagingInfoVO<BuyerVO> pagingVO) {
	try(
			SqlSession sqlSession = SqlSessionFactory.openSession();	
		){
			IBuyerDao mapper = sqlSession.getMapper(IBuyerDao.class);
			return mapper.selectBuyerCount(pagingVO);
		}
	}
	
	@Override
	public List<BuyerVO> selectNameList(PagingInfoVO pagingVO) {

		try(
			SqlSession sqlSession = SqlSessionFactory.openSession();	
		){
			IBuyerDao mapper = sqlSession.getMapper(IBuyerDao.class);
			return mapper.selectNameList(pagingVO);
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
		try (
				SqlSession sqlSession = SqlSessionFactory.openSession();
		) {
			IBuyerDao mapper = sqlSession.getMapper(IBuyerDao.class);
			int cnt = mapper.buyerInsert(vo);
			sqlSession.commit();
			return cnt;
		}
	}

	@Override
	public int buyerDelete(String buyer_id) {
		try (
				SqlSession sqlSession = SqlSessionFactory.openSession();
		) {
			IBuyerDao mapper = sqlSession.getMapper(IBuyerDao.class);
			int cnt = mapper.buyerDelete(buyer_id);
			sqlSession.commit();
			return cnt;
		}
	}

	@Override
	public int buyerUpdate(BuyerVO vo) {
		try (
				SqlSession sqlSession = SqlSessionFactory.openSession();
		) {
			IBuyerDao mapper = sqlSession.getMapper(IBuyerDao.class);
			int cnt = mapper.buyerUpdate(vo);
			sqlSession.commit();
			return cnt;
		}
	}

	

}

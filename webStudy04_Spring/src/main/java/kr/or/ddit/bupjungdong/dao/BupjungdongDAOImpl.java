package kr.or.ddit.bupjungdong.dao;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import kr.or.ddit.vo.BupjungdongVO;
import kr.or.ddit.vo.PagingInfoVO;

@Repository
public class BupjungdongDAOImpl implements IBupjungdongDAO {
	@Inject
	SqlSessionTemplate sqlSession;
	
	@Override
	public List<BupjungdongVO> selectBjd(PagingInfoVO<BupjungdongVO> pagingVO) {
//		try(SqlSession sqlSession = sqlSessionFactory.openSession();){
			IBupjungdongDAO mapper = sqlSession.getMapper(IBupjungdongDAO.class);
			return mapper.selectBjd(pagingVO);
//		}
	}

	@Override
	public int selectBjdCount(PagingInfoVO<BupjungdongVO> pagingVO) {
//		try(SqlSession sqlSession = sqlSessionFactory.openSession();){
			IBupjungdongDAO mapper = sqlSession.getMapper(IBupjungdongDAO.class);
			return mapper.selectBjdCount(pagingVO);
//		}
	}

	
}

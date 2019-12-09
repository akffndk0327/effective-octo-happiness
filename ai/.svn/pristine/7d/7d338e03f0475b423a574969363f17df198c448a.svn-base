package kr.or.ddit.newsboard.dao;

import java.util.List;

import javax.inject.Inject;

import org.mybatis.spring.SqlSessionTemplate;

import kr.or.ddit.diet.dao.IDietDAO;
import kr.or.ddit.vo.NewsVO;
import kr.or.ddit.vo.PagingInfoVO;

public class NewsboardDAOImpl implements INewsboardDAO {
	@Inject
	SqlSessionTemplate sqlSession;
	
	
	
	@Override
	public int selectBoardCount(PagingInfoVO<NewsVO> pagingVO) {
		INewsboardDAO mapper = sqlSession.getMapper(INewsboardDAO.class);
		return mapper.selectBoardCount(pagingVO);
	}

	@Override
	public List<NewsVO> selectBoardList(PagingInfoVO<NewsVO> pagingVO) {
		INewsboardDAO mapper = sqlSession.getMapper(INewsboardDAO.class);
		return mapper.selectBoardList(pagingVO);
	}

	@Override
	public NewsVO selectNews(int newsNo) {
		INewsboardDAO mapper = sqlSession.getMapper(INewsboardDAO.class);
		return mapper.selectNews(newsNo);
	}

	@Override
	public int updateNewsHit(int newsNo) {
		INewsboardDAO mapper = sqlSession.getMapper(INewsboardDAO.class);
		return mapper.updateNewsHit(newsNo);
	}

	@Override
	public int updateNews(NewsVO newsVO) {
		INewsboardDAO mapper = sqlSession.getMapper(INewsboardDAO.class);
		int cnt = mapper.updateNews(newsVO);
		if (cnt>0) {
			sqlSession.commit();
		}
		return cnt;
	}

	@Override
	public int insertNews(NewsVO newsVO) {
		INewsboardDAO mapper = sqlSession.getMapper(INewsboardDAO.class);
		int cnt = mapper.insertNews(newsVO);
		if (cnt>0) {
			sqlSession.commit();
		}
		return cnt;
	}

	@Override
	public int deleteNews(int newsNo) {
		INewsboardDAO mapper = sqlSession.getMapper(INewsboardDAO.class);
		int cnt = mapper.deleteNews(newsNo);
		if (cnt>0) {
			sqlSession.commit();
		}
		return cnt;
	}

}

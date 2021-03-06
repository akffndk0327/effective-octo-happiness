package kr.or.ddit.newsboard.service;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.inject.Inject;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import kr.or.ddit.enums.ServiceResult;
import kr.or.ddit.exception.CommonException;
import kr.or.ddit.newsboard.dao.INewsboardDAO;
import kr.or.ddit.vo.AdvertiseVO;
import kr.or.ddit.vo.NewsAttatchVO;
import kr.or.ddit.vo.NewsVO;
import kr.or.ddit.vo.PagingInfoVO;
/**
 * 
 * @author 박주연
 * @since 2019. 11. 6.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                          수정자               수정내용
 * --------     --------    ----------------------
 * 2019. 11. 6.        박주연		 최초작성
 * Copyright (c) 2019 by DDIT All right reserved
 * </pre>
 */

@Service
public class NewsServiceImpl implements INewsService {
	@Inject
	INewsboardDAO newsDAO;

	@Override
	public int retrievBoardCount(PagingInfoVO<NewsVO> pagingVO) {
		return newsDAO.selectBoardCount(pagingVO);
	}

	@Override
	public List<NewsVO> retrieveBoardList(PagingInfoVO<NewsVO> pagingVO) {
		return newsDAO.selectBoardList(pagingVO);
	}

	@Override
	public NewsVO retrieveNews(int news) {
		return newsDAO.selectNews(news);
	}

	@Override
	public int modifyNewsHit(int newsNo) {
		return newsDAO.updateNewsHit(newsNo);
	}

	@Override
	public int modifyNews(NewsVO newsVO) {
		return newsDAO.updateNews(newsVO);
	}

	@Override
	public int insertNews(NewsVO newsVO) {
		return newsDAO.insertNews(newsVO);
	}
	


	@Override
	public int removeNews(int newsNo) {
		return newsDAO.deleteNews(newsNo);
	}
}

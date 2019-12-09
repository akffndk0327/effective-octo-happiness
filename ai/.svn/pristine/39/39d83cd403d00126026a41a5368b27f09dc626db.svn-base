package kr.or.ddit.newsboard.service;

import java.util.List;

import kr.or.ddit.enums.ServiceResult;
import kr.or.ddit.vo.NewsAttatchVO;
import kr.or.ddit.vo.NewsVO;
import kr.or.ddit.vo.PagingInfoVO;

public interface INewsService {
	/**
	 * 페이징
	 * @param pagingVO
	 * @return
	 */
	public int retrievBoardCount(PagingInfoVO<NewsVO> pagingVO);
	
	
	/**
	 * 목록조회
	 * @param pagingVO
	 * @return
	 */
	public List<NewsVO> retrieveBoardList(PagingInfoVO<NewsVO> pagingVO);
	
	
	/**
	 * 뉴스상세보기 
	 * @param news
	 * @return
	 */
	public NewsVO retrieveNews(int news); //뉴스 상세보기
	
	
	//조회수 카운팅
	public int modifyNewsHit(int newsNo);
	
	/**
	 * 뉴스 수정
	 * @param news
	 * @return
	 */
	public int modifyNews(NewsVO newsVO);
	
	/**
	 * 뉴스 등록
	 * @param news
	 * @return
	 */
	public int insertNews(NewsVO newsVO);
	
	/**
	 * 뉴스 삭제
	 * @param news
	 * @return
	 */
	public int removeNews(int newsNo);
	
}

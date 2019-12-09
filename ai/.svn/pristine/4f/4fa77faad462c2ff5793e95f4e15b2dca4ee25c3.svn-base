package kr.or.ddit.newsboard.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import kr.or.ddit.vo.NewsVO;
import kr.or.ddit.vo.PagingInfoVO;

@Repository
public interface INewsboardDAO {
	/**
	 * 페이징
	 * @param pagingVO
	 * @return
	 */
	public int selectBoardCount(PagingInfoVO<NewsVO> pagingVO);
	
	
	/**
	 * 목록조회
	 * @param pagingVO
	 * @return
	 */
	public List<NewsVO> selectBoardList(PagingInfoVO<NewsVO> pagingVO);
	
	
	/**
	 * 뉴스상세보기 
	 * @param news
	 * @return
	 */
	public NewsVO selectNews(int newsNo); //뉴스 상세보기
	
	/**
	 * 뉴스 조회수
	 * @param news
	 * @return
	 */
	public int updateNewsHit(int newsNo);
	
	
	/**
	 * 뉴스 수정
	 * @param news
	 * @return
	 */
	public int updateNews(NewsVO newsVO);
	
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
	public int deleteNews(int newsNo);
	
	
}

package kr.or.ddit.search.service;

import java.util.List;

import kr.or.ddit.vo.AllVO;
import kr.or.ddit.vo.Resource2VO;
import kr.or.ddit.vo.SearchVO;

/**
 * @author 이진희
 * @since 2019. 11. 22.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                          수정자               수정내용
 *
 * --------     --------    ----------------------
 * 2019. 11. 22.      이진희       최초작성
 * 2019. 12. 03.      이진희       메서드추가
 * Copyright (c) 2019 by DDIT All right reserved
 * </pre>
 */
public interface ISearchService {
	
	//검색한 제품의 목록을 가지고 오는 메서드 
	public List<AllVO> selectAllList(String vo);
	
	//게시판 URL 주소 가지고 오는 메서드
	public List<Resource2VO> selectURL();
	
	//검색어 insert 
	public int insertKeyWord(SearchVO vo);
	
	//검색어 가지고 오기 
	public List<SearchVO> selectKeyWord();

	//몇번 검색했는지 가지고 오는 메서드 
	public List<SearchVO> selectcnt(String name);
	
}

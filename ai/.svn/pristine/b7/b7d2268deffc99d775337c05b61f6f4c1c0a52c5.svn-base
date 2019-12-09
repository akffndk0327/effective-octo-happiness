package kr.or.ddit.search.service;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import kr.or.ddit.search.dao.ISearchDAO;
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
 * 수정일                     수정자               수정내용
 * --------------   --------    ----------------------
 * 2019. 11. 22.      이진희          최초작성
 * Copyright (c) 2019 by DDIT All right reserved
 * </pre>
 */
@Service
public class SearchServiceImp implements ISearchService{
	
	@Inject
	ISearchDAO dao;

	@Override
	public List<AllVO> selectAllList(String vo) {
		return dao.selectAllList(vo);
	}

	@Override
	public List<Resource2VO> selectURL() {
		return dao.selectURL();
	}

	@Override
	public int insertKeyWord(SearchVO vo) {
		return dao.insertKeyWord(vo);
	}

	
	@Override
	public List<SearchVO> selectKeyWord() {
		return dao.selectKeyWord();
	}

	@Override
	public List<SearchVO> selectcnt(String name) {
		
		 List<SearchVO> list = dao.selectKeyWord();
		 List<SearchVO> result = new ArrayList();

		 for(SearchVO temp : list){
			 SearchVO a = new SearchVO();
		     a = dao.selectcnt(temp.getSearchName());
		    result.add(a);
		  }
		
		return result;
	}


}

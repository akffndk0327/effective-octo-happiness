package kr.or.ddit.uneatable.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import kr.or.ddit.enums.ServiceResult;
import kr.or.ddit.vo.PagingInfoVO;
import kr.or.ddit.vo.UneatableVO;

/**
 * 
 * @author 이진희
 * @since 2019. 11. 5.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                            수정자       수정내용
 * 2019. 11. 10.     이진희       메서드추가
 * 2019. 11. 6.      이진희       메서드추가
 * --------     --------    ----------------------
 * 2019. 11. 5.      이진희       최초작성
 * Copyright (c) 2019 by DDIT All right reserved
 * </pre>
 */
@Repository
public interface IUneatableDAO {
	//총 제품의 갯수
	public int selectUneatableCount(PagingInfoVO<UneatableVO> pagingVO);
	
	//제품의 리스트 출력하기 
	public List<UneatableVO> selectUneatableList(PagingInfoVO<UneatableVO> pagingVO);
	
	//제품하나에 대한 상세정보 
	public UneatableVO selectUneatable(UneatableVO uneatVO);
	
	//제품삭제하기 
	public int deleteUneatable(int id);
	
	//검사기관 리스트 출력하기 
	public List<String> selectInsttNmList(String insttNm);
	
	//부적합한항목 리스트 출력하기 
	public List<String> selectTestNmList(String testNm);
	
	//제품추가하기 
	public int creatUneatable(UneatableVO uneatVO);
	
	//제품수정하기 
	public int updateUneatable(UneatableVO uneatVO);
	

}

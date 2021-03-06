package kr.or.ddit.food.service;

import java.util.List;

import kr.or.ddit.vo.HaccpVO;
import kr.or.ddit.vo.NutrientVO;
import kr.or.ddit.vo.PagingInfoVO;

/**
 * @author 이진희
 * @since 2019. 11. 15.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                   수정자       수정내용
 * 2019. 11. 19.      이진희       영양소정보메서드추가 
 * --------     --------    ----------------------
 * 2019. 11. 15.      이진희       최초작성
 * Copyright (c) 2019 by DDIT All right reserved
 * </pre>
 */
public interface IFoodService {
	
	//카테고리 별로 식품 리스트 가지고 오기 
	public List<HaccpVO> selectFoodList(PagingInfoVO<HaccpVO> pagingVO);
	
	//카테고리 별로 식품 페이징 처리 하기 
	public int selectFoodCount(PagingInfoVO<HaccpVO> pagingVO);
	
	//식품 하나에 대한 상세정보 
	public HaccpVO selectFood(HaccpVO vo);

	//식품 하나에 대한 영양정보 
	public NutrientVO selectNurient(HaccpVO vo);
}

package kr.or.ddit.food.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import kr.or.ddit.food.dao.IFoodDAO;
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
 * 수정일                          수정자               수정내용
 *
 * --------     --------    ----------------------
 * 2019. 11. 15.      이진희       최초작성
 * Copyright (c) 2019 by DDIT All right reserved
 * </pre>
 */
@Service
public class FoodServiceImp implements IFoodService{
	
	@Inject
	IFoodDAO dao;
	
	
	@Override
	public List<HaccpVO> selectFoodList(PagingInfoVO<HaccpVO> pagingVO) {
		return dao.selectFoodList(pagingVO);
	}

	@Override
	public int selectFoodCount(PagingInfoVO<HaccpVO> pagingVO) {
		return dao.selectFoodCount(pagingVO);
	}

	@Override
	public HaccpVO selectFood(HaccpVO vo) {
		return dao.selectFood(vo);
	}

	@Override
	public NutrientVO selectNurient(HaccpVO vo) {
		return dao.selectNurient(vo);
	}

}

package kr.or.ddit.stopsellingfood.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import kr.or.ddit.enums.ServiceResult;
import kr.or.ddit.stopsellingfood.dao.IStopSellingDAO;
import kr.or.ddit.vo.PagingInfoVO;
import kr.or.ddit.vo.StopSellingFoodVO;

/**
 * @author 이진희
 * @since 2019. 11. 7.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                          수정자               수정내용
 *
 * --------     --------    ----------------------
 * 2019. 11. 7.      이진희       최초작성
 * Copyright (c) 2019 by DDIT All right reserved
 * </pre>
 */
@Service
public class StopSellingServiceImp implements IStopSellingService{

	@Inject
	IStopSellingDAO dao;
	
	@Override
	public int selectStopSellingCount(PagingInfoVO<StopSellingFoodVO> pagingVO) {
		return dao.selectStopSellingCount(pagingVO);
	}

	@Override
	public List<StopSellingFoodVO> selectStopSellingList(PagingInfoVO<StopSellingFoodVO> pagingVO) {
		return dao.selectStopSellingList(pagingVO);
	}

	@Override
	public StopSellingFoodVO selectStopSelling(StopSellingFoodVO vo) {
		return dao.selectStopSelling(vo);
	}

	@Override
	public ServiceResult deleteStopSelling(int id) {
		ServiceResult result = null;
		int cnt = dao.deleteStopSelling(id);
		
		if(cnt>0) {
			result = ServiceResult.OK;
		}else {
			result = ServiceResult.FAILED;
		}
		return result;
	}

	@Override
	public ServiceResult createStopSelling(StopSellingFoodVO vo) {
		ServiceResult result = null;
		int cnt = dao.createStopSelling(vo);
		if(cnt>0) {
			result = ServiceResult.OK;
		}else {
			result = ServiceResult.FAILED;
		}
		return result;
	}

	@Override
	public ServiceResult updateStopSelling(StopSellingFoodVO vo) {
		ServiceResult result = null;
		int cnt = dao.updateStopSelling(vo);
		if(cnt>0) {
			result = ServiceResult.OK;
		}else {
			result = ServiceResult.FAILED;
		}
		return result;
	}

}

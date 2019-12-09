package kr.or.ddit.stopsellingfood.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import kr.or.ddit.enums.ServiceResult;
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
 * 2019. 11. 11.      이진희       메서드추가 
 * --------     --------    ----------------------
 * 2019. 11. 7.      이진희       최초작성
 * Copyright (c) 2019 by DDIT All right reserved
 * </pre>
 */
@Repository
public interface IStopSellingDAO {
	
	//총 리스트갯수
	public int selectStopSellingCount(PagingInfoVO<StopSellingFoodVO> pagingVO);
	
	//회수판매중지식품 리스트 출력하기 
	public List<StopSellingFoodVO> selectStopSellingList(PagingInfoVO<StopSellingFoodVO> pagingVO);
	
	//회수판매중지식품 상세보기 
	public StopSellingFoodVO selectStopSelling(StopSellingFoodVO vo);
	
	//회수판매중지식품 삭제하기 
	public int deleteStopSelling(int id);
	
	//회수판매중지식품 추가하기
	public int createStopSelling(StopSellingFoodVO vo);
	
	//회수판매중지식품 수정하기
	public int updateStopSelling(StopSellingFoodVO vo);
}

package kr.or.ddit.buyer.service;

import java.util.List;

import kr.or.ddit.buyer.enums.ServiceResult;
import kr.or.ddit.buyer.vo.BuyerVO;

public interface IBuyerService {
//crud
	
	/**
	 * 신규등록
	 * @param buyer
	 * @return
	 */
	public ServiceResult createBuyer(BuyerVO buyer);  
	
	//조회
	public List<BuyerVO> selectBuyerList();
	//수정
	public int updateBuyer(String buyer_id);
	//삭제
	public int deleteBuyer(String buyer_id);
	
}

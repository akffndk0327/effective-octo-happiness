package kr.or.ddit.buyer.dao;

import java.util.List;

import kr.or.ddit.buyer.enums.ServiceResult;
import kr.or.ddit.buyer.vo.BuyerVO;

public interface IbuyerDao{
	
	/**
	 * 신규등록
	 * @param buyer
	 * @return
	 */
	public ServiceResult createBuyer(BuyerVO buyer);  
	
	//조회
	public List<BuyerVO> selectBuyerList();
	
	//상세보기 
	public BuyerVO detailBuyer(String buyer_id);
	
	//수정
	public int updateBuyer(BuyerVO vo);
	//삭제
	public int deleteBuyer(String buyer_id);
	
}

package kr.or.ddit.buyer.dao;

import java.util.List;

import kr.or.ddit.buyer.vo.BuyerVO;

public interface IbuyerDao {
	
	/**
	 * 신규등록
	 * @param buyer
	 * @return 등록성공(>0), 실패(<=0)
	 */
	public int insertBuyer(BuyerVO buyer);
	
	/**
	 * 거래처 목록 조회
	 * @return 조건 맞는 거래처 없는 경우 size ==0
	 */
	public List<BuyerVO> selectBuyerList();
	
	
}

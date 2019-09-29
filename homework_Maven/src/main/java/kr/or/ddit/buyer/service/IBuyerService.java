package kr.or.ddit.buyer.service;

import java.util.List;

import kr.or.ddit.buyer.enums.ServiceResult;
import kr.or.ddit.buyer.vo.BuyerVO;

public interface IBuyerService {
//crud

	public List<BuyerVO> selectBuyerList();
	
}

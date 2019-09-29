package kr.or.ddit.buyer.dao;

import java.sql.SQLException;
import java.util.List;

import kr.or.ddit.buyer.enums.ServiceResult;
import kr.or.ddit.buyer.vo.BuyerVO;

public interface IbuyerDao{
	
	//조회
	public List<BuyerVO> selectBuyerList() throws SQLException;
	
	
}

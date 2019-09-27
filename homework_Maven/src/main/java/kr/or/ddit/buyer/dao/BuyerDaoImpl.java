package kr.or.ddit.buyer.dao;

import java.util.List;

import kr.or.ddit.buyer.enums.ServiceResult;
import kr.or.ddit.buyer.vo.BuyerVO;

public class BuyerDaoImpl implements IbuyerDao {
	private static BuyerDaoImpl dao;
	private BuyerDaoImpl() {};
	
	public static BuyerDaoImpl getInstance() {
		if(dao == null) {
			dao =new BuyerDaoImpl();
		}
		return dao;
	}
	
	
	@Override
	public ServiceResult createBuyer(BuyerVO buyer) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<BuyerVO> selectBuyerList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BuyerVO detailBuyer(String buyer_id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int updateBuyer(BuyerVO vo) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteBuyer(String buyer_id) {
		// TODO Auto-generated method stub
		return 0;
	}

}

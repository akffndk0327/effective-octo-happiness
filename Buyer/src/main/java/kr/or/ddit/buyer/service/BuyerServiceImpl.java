package kr.or.ddit.buyer.service;

import java.util.List;

import kr.or.ddit.buyer.dao.BuyerDaoImpl;
import kr.or.ddit.buyer.dao.BuyerDaoImpl_JDBC;
import kr.or.ddit.buyer.dao.IBuyerDao;
import kr.or.ddit.buyer.vo.BuyerVO;
import kr.or.ddit.enums.ServiceResult;

public class BuyerServiceImpl implements IBuyerService{
//	private IBuyerDao dao = BuyerDaoImpl_JDBC.getInstance();
	private IBuyerDao dao = new BuyerDaoImpl();
	
	private static BuyerServiceImpl instance;
	
	public BuyerServiceImpl() {	}
	
	public static BuyerServiceImpl getInstance() {
		if(instance == null) {
			instance = new BuyerServiceImpl();
		}
		return instance;
	}
	
	@Override
	public List<BuyerVO> selectNameList() {
		return dao.selectNameList();
	}

	@Override
	public BuyerVO buyerDetail(String buyer_id) {
		return dao.buyerDetail(buyer_id);
	}

	@Override
	public int buyerInsert(BuyerVO vo) {
		return dao.buyerInsert(vo);
	}

	@Override
	public ServiceResult buyerDelete(String buyer_id) {
		int tmp =  dao.buyerDelete(buyer_id);
		
		ServiceResult result = null;
		if(tmp > 0) {
			result = ServiceResult.OK;
		}else {
			result = ServiceResult.FAILED;
		}
		
		return result;
	}

	@Override
	public int buyerUpdate(BuyerVO vo) {
		return dao.buyerUpdate(vo);
	}

}

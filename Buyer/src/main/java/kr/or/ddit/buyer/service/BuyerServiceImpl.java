package kr.or.ddit.buyer.service;

import java.util.List;

import kr.or.ddit.buyer.dao.BuyerDaoImpl;
import kr.or.ddit.buyer.dao.BuyerDaoImpl_JDBC;
import kr.or.ddit.buyer.dao.IBuyerDao;
import kr.or.ddit.buyer.vo.BuyerVO;
import kr.or.ddit.buyer.vo.PagingInfoVO;
import kr.or.ddit.enums.ServiceResult;
import kr.or.ddit.exception.CommonException;

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
	public int selectBuyerCount(PagingInfoVO<BuyerVO> pagingVO) {
		return dao.selectBuyerCount(pagingVO);
	}
	
	@Override
	public List<BuyerVO> selectNameList(PagingInfoVO pagingVO) {
		return dao.selectNameList(pagingVO);
	}

	@Override
	public BuyerVO buyerDetail(String buyer_id) {
		BuyerVO buyer = dao.buyerDetail(buyer_id);
		if (buyer == null)
			throw new CommonException(buyer_id + "해당 거래처 없음");
		return buyer;

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
	public ServiceResult buyerUpdate(BuyerVO vo) {
		buyerDetail(vo.getBuyer_id());
		ServiceResult result = null;
		int cnt = dao.buyerUpdate(vo);
		if (cnt > 0)
			result = ServiceResult.OK;
		else
			result = ServiceResult.FAILED;
		return result;

	}

	

	

}

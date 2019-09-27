package kr.or.ddit.buyer.service;

import java.util.List;

import kr.or.ddit.buyer.dao.BuyerDaoImpl;
import kr.or.ddit.buyer.dao.IbuyerDao;
import kr.or.ddit.buyer.enums.ServiceResult;
import kr.or.ddit.buyer.vo.BuyerVO;

public class BuyerServiceImpl implements IBuyerService {
	private static IBuyerService service;
	private IbuyerDao dao ;

	public static IBuyerService getInstance() {
		if(service ==null) {
			service = new BuyerServiceImpl();
		}
		return service;
	}
	
	private BuyerServiceImpl() {
		dao = BuyerDaoImpl.getInstance();
	}
	
	@Override
	public ServiceResult createBuyer(BuyerVO buyer) {
		
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

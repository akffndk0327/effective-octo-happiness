package kr.or.ddit.buyer.service;

import java.sql.SQLException;
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
	public List<BuyerVO> selectBuyerList() {
		List<BuyerVO> list = null;
		try {
			list = dao.selectBuyerList();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;
	}
	
	

}

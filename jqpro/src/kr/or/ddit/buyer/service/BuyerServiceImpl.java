package kr.or.ddit.buyer.service;

import java.sql.SQLException;
import java.util.List;

import kr.or.ddit.buyer.dao.BuyerDaoImpl;
import kr.or.ddit.buyer.dao.IBuyerDao;
import kr.or.ddit.buyer.vo.BuyerVO;

public class BuyerServiceImpl implements IBuyerService {
	private IBuyerDao dao;
	private static IBuyerService service;
	
	private BuyerServiceImpl (){
		dao = BuyerDaoImpl.getInstance();
	}
	
	public static IBuyerService getInstance(){
		if(service == null){
			service = new BuyerServiceImpl();
		}
		return service;
	}
	
	@Override
	public List<BuyerVO> selectNameList() {
		List<BuyerVO> list =null;
		try {
			list = dao.selectNameList();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public BuyerVO buyerDetail(String buyer_id) {
		BuyerVO vo =null;
		try {
			vo = dao.buyerDetail(buyer_id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return vo;
	}

}

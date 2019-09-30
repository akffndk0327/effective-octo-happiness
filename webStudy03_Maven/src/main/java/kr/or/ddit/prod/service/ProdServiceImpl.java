package kr.or.ddit.prod.service;

import java.util.List;

import kr.or.ddit.enums.ServiceResult;
import kr.or.ddit.exception.CommonException;
import kr.or.ddit.member.service.AuthenticateServiceImpl;
import kr.or.ddit.member.service.IAuthenticateService;
import kr.or.ddit.prod.dao.IProdDAO;
import kr.or.ddit.prod.dao.ProdDaoImpl;
import kr.or.ddit.vo.ProdVO;

public class ProdServiceImpl implements IProdService {
	public IProdDAO dao = new ProdDaoImpl();
	
	private IAuthenticateService service = new AuthenticateServiceImpl();
	private static ProdServiceImpl instance ;
	
	public static IProdService getInstance() {
		if(instance ==null) {
			instance =new ProdServiceImpl();
		}
		return instance;
	}
	
	@Override
	public ServiceResult createProd(ProdVO prod) {
		ServiceResult result = null;
		int cnt = dao.insertProd(prod);
		if(cnt > 0) result = ServiceResult.OK;
		else result = ServiceResult.FAILED;
		return result;
	}

	@Override
	public List<ProdVO> retrieveProdList() {
		
		return dao.selectProdList();
	}

	@Override
	public ProdVO retrieveProd(String prod_id) {
		ProdVO prod = dao.selectProd(prod_id);
		if(prod==null) 
			throw new CommonException(prod_id+" 상품이 없음.");
		return prod;
	}
	
	//0930
	@Override
	public ServiceResult modifyProd(ProdVO prod) {
		retrieveProd(prod.getProd_id());
		ServiceResult result = null;
		int cnt = dao.updateProd(prod);
		if(cnt > 0) result = ServiceResult.OK;
		else result = ServiceResult.FAILED;
		return result;
		
	}
}

package kr.or.ddit.prod.service;

import java.util.List;

import kr.or.ddit.enums.ServiceResult;
import kr.or.ddit.member.service.AuthenticateServiceImpl;
import kr.or.ddit.member.service.IAuthenticateService;
import kr.or.ddit.prod.dao.IProdDAO;
import kr.or.ddit.prod.dao.ProdDaoImpl;
import kr.or.ddit.vo.ProdVO;

public class ProdServiceImpl implements IProdService {
	public IProdDAO dao = ProdDaoImpl.getInstance();
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
		return null;
	}

	@Override
	public List<ProdVO> retrieveProdList() {
		
		return dao.selectProdList();
	}

	@Override
	public ProdVO retrieveProd(String prod_id) {
		return null;
	}

	@Override
	public ServiceResult modifyProd(ProdVO prod) {
		return null;
	}

}

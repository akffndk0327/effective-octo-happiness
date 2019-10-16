package kr.or.ddit.prod.service;

import java.util.List;

import kr.or.ddit.enums.ServiceResult;
import kr.or.ddit.vo.PagingInfoVO;
import kr.or.ddit.vo.ProdVO;

/**
 * 상품 관리 business logic layer
 *
 */
public interface IProdService {
	/**
	 * @param prod
	 * @return ok,fail
	 */
	public ServiceResult createProd(ProdVO prod);
	public int retreieveProdCount(PagingInfoVO pagingVO);
	public List<ProdVO>retrieveProdList(PagingInfoVO PagingVo);
	public ProdVO retrieveProd(String prod_id);
	/**
	 * @param prod
	 * @return CommonException, ok,failed
	 */
	public ServiceResult modifyProd(ProdVO prod);
	
	
}

package kr.or.ddit.prod.dao;

import java.util.List;

import kr.or.ddit.vo.PagingInfoVO;
import kr.or.ddit.vo.ProdVO;

/**
 * 상품관리 Persistence Layer (alt shift j)
 *
 */
public interface IProdDAO {
	//아이바티스
	//마이바티스....?
	//call by reference...?
	public int insertProd(ProdVO prod);
	public int selectProdCount(PagingInfoVO pagingVO); //목록 & TOTALRECORD
	public List<ProdVO> selectProdList(PagingInfoVO pagingVO);
	public ProdVO selectProd(String prod_id);
	public int updateProd(ProdVO prod);

}

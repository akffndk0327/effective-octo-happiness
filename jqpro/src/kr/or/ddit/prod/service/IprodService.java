package kr.or.ddit.prod.service;

import java.util.List;

import kr.or.ddit.prod.vo.ProdVO;

public interface IprodService {
	public List<ProdVO> getBuyLguList(String lprod_gu);

	public ProdVO getDetail(String prod_id);
}

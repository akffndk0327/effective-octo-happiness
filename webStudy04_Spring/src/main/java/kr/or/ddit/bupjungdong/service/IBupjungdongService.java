package kr.or.ddit.bupjungdong.service;


import java.util.List;

import kr.or.ddit.vo.BupjungdongVO;
import kr.or.ddit.vo.PagingInfoVO;

public interface IBupjungdongService {
	public List<BupjungdongVO> selectBjd(PagingInfoVO<BupjungdongVO> pagingVO);
	public int selectBjdCount(PagingInfoVO<BupjungdongVO> pagingVO);
}

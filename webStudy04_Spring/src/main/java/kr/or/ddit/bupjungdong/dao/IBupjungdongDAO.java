package kr.or.ddit.bupjungdong.dao;

import java.util.List;

import kr.or.ddit.vo.BupjungdongVO;
import kr.or.ddit.vo.PagingInfoVO;

public interface IBupjungdongDAO {
	public List<BupjungdongVO> selectBjd(PagingInfoVO<BupjungdongVO> pagingVO);
	public int selectBjdCount(PagingInfoVO<BupjungdongVO> pagingVO);
}

package kr.or.ddit.bupjungdong.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import kr.or.ddit.bupjungdong.dao.IBupjungdongDAO;
import kr.or.ddit.vo.BupjungdongVO;
import kr.or.ddit.vo.PagingInfoVO;

@Service
public class BupjungdongServiceImpl implements IBupjungdongService {
	@Inject
	private IBupjungdongDAO dao;
	@Override
	public List<BupjungdongVO> selectBjd(PagingInfoVO<BupjungdongVO> pagingVO) {
		return dao.selectBjd(pagingVO);
	}
	@Override
	public int selectBjdCount(PagingInfoVO<BupjungdongVO> pagingVO) {
		return dao.selectBjdCount(pagingVO);
	}

}

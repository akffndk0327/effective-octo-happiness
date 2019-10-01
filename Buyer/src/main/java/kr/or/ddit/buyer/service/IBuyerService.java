package kr.or.ddit.buyer.service;

import java.util.List;

import kr.or.ddit.buyer.vo.BuyerVO;
import kr.or.ddit.buyer.vo.PagingInfoVO;
import kr.or.ddit.enums.ServiceResult;

public interface IBuyerService {
	
	/**
	 * 페이징 처리위한 메서드 
	 * @param pagingVO
	 * @return
	 */
	public int selectBuyerCount(PagingInfoVO<BuyerVO> pagingVO);
	
	/**
	 * buyer list 호출 메서드
	 * @param pagingVO TODO
	 * @return db의 table 'buyer'의 모든 목록
	 */
	public List<BuyerVO> selectNameList(PagingInfoVO pagingVO);
	
	
	/**
	 * buyer_id를 갖는 데이터 로드
	 * @param buyer_id
	 * @return db의 table 'buyer'에서 id = buyer_id인 데이터(buyerVO객체)
	 */
	public BuyerVO buyerDetail(String buyer_id);
	
	/**
	 * buyer 추가 메서드
	 * @param vo
	 * @return
	 */
	public int buyerInsert(BuyerVO vo);
	
	/**
	 * buyer 삭제 메서드
	 * @param buyer
	 * @return
	 */
	public ServiceResult buyerDelete(String buyer_id);
	
	/**
	 * buyer 업데이트 메서드
	 * @param vo
	 * @return
	 */
	public ServiceResult buyerUpdate(BuyerVO vo);

}

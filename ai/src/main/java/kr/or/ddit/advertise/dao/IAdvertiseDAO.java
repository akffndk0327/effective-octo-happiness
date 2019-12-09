package kr.or.ddit.advertise.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import kr.or.ddit.enums.ServiceResult;
import kr.or.ddit.vo.AdattatchVO;
import kr.or.ddit.vo.AdhitVO;
import kr.or.ddit.vo.AdpositionVO;
import kr.or.ddit.vo.AdvertiseVO;
import kr.or.ddit.vo.AdvertiseVO;
import kr.or.ddit.vo.PagingInfoVO;

@Repository
public interface IAdvertiseDAO {
	/**
	 * 페이징
	 * @param pagingVO
	 * @return
	 */
	public int selectAdCount(PagingInfoVO<AdvertiseVO> pagingVO);
	/**
	 * 목록조회
	 * @param pagingVO
	 * @return
	 */
	public List<AdvertiseVO> selectadAdList(PagingInfoVO<AdvertiseVO> pagingVO);
	/**
	 * 광고상세보기 
	 * @param Advertise
	 * @return
	 */
	public AdvertiseVO selectAd(AdvertiseVO advertise); //광고 상세보기
	/**
	 * 광고 등록
	 * @param Advertise
	 * @return
	 */
	public int insertAd(AdvertiseVO advertise);
	/**
	 * 광고 수정
	 * 
	 * @param Advertise
	 * @return
	 */
	public int updateAd(AdvertiseVO advertise);
	/**
	 * 광고 조회수(클릭수)
	 * @param Advertise
	 * @return
	 */
	public int updateAdHit(int adId);
	
	/**
	 * 광고 위치 넣기 
	 * @param position
	 * @return
	 */
	public int insertposition(AdhitVO adhit);
	
	/**
	 * 광고 위치 수정하기 
	 * @param adhit
	 * @return
	 */
	public int updateposition(AdvertiseVO adhit);
	
	/**
	 * 광고 승인여부 사용여부 변경
	 * @param advertise
	 * @return
	 */
	public int updateApprove(AdvertiseVO advertise);
	
//	//이미지 메타데이터 조회
	public AdattatchVO selectAttatch(AdattatchVO adattid);
	
	//관리자 
	/**
	 * 관리자용 페이징
	 * @param pagingVO
	 * @return
	 */
	public int selectAdminCount(PagingInfoVO<AdvertiseVO> pagingVO);
	/**
	 * 관리자용 광고 목록조회
	 * @param pagingVO
	 * @return
	 */
	public List<AdvertiseVO> selectadAdminList(PagingInfoVO<AdvertiseVO> pagingVO);
	
	/**
	 * 광고 이미지 append
	 * @param advertise
	 * @return
	 */
	public int adimgAppend(AdvertiseVO advertise);
	
	//광고이미지 승인후 띄우기
	public AdvertiseVO selectAdImage();
	
}

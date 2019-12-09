package kr.or.ddit.advertise.service;

import java.util.List;

import kr.or.ddit.enums.ServiceResult;
import kr.or.ddit.vo.AdattatchVO;
import kr.or.ddit.vo.AdhitVO;
import kr.or.ddit.vo.AdvertiseVO;
import kr.or.ddit.vo.PagingInfoVO;

public interface IAdvertiseService {
	/**
	 * 페이징
	 * @param pagingVO
	 * @return
	 */
	public int retrieveAdCount(PagingInfoVO<AdvertiseVO> pagingVO);
	/**
	 * 목록조회
	 * @param pagingVO
	 * @return
	 */
	public List<AdvertiseVO> retrieveAdList(PagingInfoVO<AdvertiseVO> pagingVO);
	/**
	 * 광고상세보기 
	 * @param Advertise
	 * @return
	 */
	public AdvertiseVO retrieveAd(AdvertiseVO advertise); //광고 상세보기
	/**
	 * 광고 등록
	 * @param Advertise
	 * @return
	 */
	public ServiceResult insertAd(AdvertiseVO advertise);
	/**
	 * 광고 수정
	 * @param Advertise
	 * @return
	 */
	public ServiceResult modifyAd(AdvertiseVO advertise);
	/**
	 * 광고 삭제
	 * @param Advertise
	 * @return
	 */
	public ServiceResult removeAd(AdvertiseVO advertise);
	
	/**
	 * 광고 위치 수정하기 
	 * @param adhit
	 * @return
	 */
	public ServiceResult updateposition(AdvertiseVO adhit);
	
	
	/**
	 * 광고 조회수
	 * @param Advertise
	 * @return
	 */
	public ServiceResult updateAdHit(int adId);
	
	/**
	 * 광고 승인여부 사용여부 변경
	 * @param advertise
	 * @return
	 */
	public ServiceResult updateApprove(AdvertiseVO advertise);
	
	/**
	 * 첨부파일
	 * @param what
	 * @return
	 */
	public AdattatchVO downloadAttatch(String what);
	
	//관리자 
	/**
	 * 페이징
	 * @param pagingVO
	 * @return
	 */
	public int retrieveAdminCount(PagingInfoVO<AdvertiseVO> pagingVO);
	/**
	 * 목록조회
	 * @param pagingVO
	 * @return
	 */
	public List<AdvertiseVO> retrieveadAdminList(PagingInfoVO<AdvertiseVO> pagingVO);
	/**
	 * 광고 이미지 append
	 * @param advertise
	 * @return
	 */
	public ServiceResult adimgAppend(AdvertiseVO advertise);
	
	//첨부파일 등록 
	public String processAttatch(AdvertiseVO advertise);
	
	//광고이미지 조회
	public AdvertiseVO retrieveAdImage();
}

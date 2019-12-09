package kr.or.ddit.allergy.service;

import java.util.List;

import kr.or.ddit.enums.ServiceResult;
import kr.or.ddit.vo.AllergyVO;
import kr.or.ddit.vo.PagingInfoVO;

public interface IAllergyService {
	/**
	 * 페이징
	 * @param pagingVO
	 * @return
	 */
	public int retrieveAllergyCount(PagingInfoVO<AllergyVO> pagingVO);
	/**
	 * 목록조회
	 * @param pagingVO
	 * @return
	 */
	public List<AllergyVO> retrieveAllergyList(PagingInfoVO<AllergyVO> pagingVO);
	/**
	 * 알레르기상세보기 
	 * @param Allergy
	 * @return
	 */
	public AllergyVO retrieveAllergy(AllergyVO allergy); //알레르기 상세보기
	/**
	 * 알레르기 등록
	 * @param Allergy
	 * @return
	 */
	public ServiceResult insertAllergy(AllergyVO allergy);
	/**
	 * 알레르기 수정
	 * @param Allergy
	 * @return
	 */
	public ServiceResult modifyAllergy(AllergyVO allergy);
	/**
	 * 알레르기 삭제 
	 * @param allergy
	 * @return
	 */
	public ServiceResult removeAllergy(AllergyVO allergy);
	
	/**
	 * 알레르기 조회수
	 * @param Allergy
	 * @return
	 */
	public ServiceResult updateAllergyHit(String allId);
	/**
	 * 첨부파일
	 * @param what
	 * @return
	 */
//	public AllergyAttatchVO downloadAttatch(int what);
	
	/**
	 * 모든알레르기리스트
	 * @author 이진희
	 */
	public List<AllergyVO> selectList();
	
	
}

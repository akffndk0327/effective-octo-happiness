package kr.or.ddit.alba.service;

import java.util.List;

import kr.or.ddit.alba.vo.AlbaVO;
import kr.or.ddit.alba.vo.Lic_albaVO;
import kr.or.ddit.alba.vo.LicenseVO;
import kr.or.ddit.enums.ServiceResult;

public interface IAlbaService {
	/**
	 * 알바생 목록 조회
	 * 
	 * @return
	 */
	public List<AlbaVO> selectAlbaList();

	/**
	 * 알바생 상세정보
	 * 
	 * @param alba
	 * @return
	 */
	public AlbaVO selctAlba(AlbaVO alba);

	/**
	 * 알바생 정보 수정
	 * 
	 * @param alba
	 * @return
	 */
	public ServiceResult updateAlba(AlbaVO alba);

	/**
	 * 알바 추가
	 * 
	 * @param alba
	 * @return
	 */
	public ServiceResult insertAlba(AlbaVO alba);

	/**
	 * 알바 정보 삭제
	 * 
	 * @param al_id
	 * @return
	 */
	public String deleteAlba(String alba);

	
	/**
	 * 자격 사진 조회
	 * 
	 * @param licAlba
	 * @return
	 */
	public Lic_albaVO selectLicAlba(Lic_albaVO licAlba);

	/**
	 * 자격 사진 정보 수정
	 * 
	 * @param licAlba
	 * @return
	 */
	public String updateLicAlba(Lic_albaVO licAlba);

	/**
	 * 알바 자격증 정보 모두 삭제
	 * 
	 * @param al_id
	 * @return
	 */
	public void deleteLicAlba(String al_id);

	/**
	 * 선택된 알바 자격증 정보 삭제
	 * 
	 * @param licAlba
	 * @return
	 */
	public String deleteLic(Lic_albaVO licAlba);

	/**
	 * 자격증 목록 조회
	 * 
	 * @return
	 */
	public List<LicenseVO> selectLic();

	/**
	 * 자격증 추가
	 * 
	 * @return
	 */
	public String insertLicAlba(Lic_albaVO licAlba);
}

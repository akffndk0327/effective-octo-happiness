package kr.or.ddit.alba.dao;

import java.util.List;

import kr.or.ddit.alba.vo.AlbaVO;
import kr.or.ddit.alba.vo.Lic_albaVO;
import kr.or.ddit.alba.vo.LicenseVO;

public interface IAlbaDao {

	// 목록 조회
	public List<AlbaVO> selectAlbaList();

	// 상세조회
	public AlbaVO selctAlba(AlbaVO alba);

	// 정보 수정
	public int updateAlba(AlbaVO alba);

	// 신규 등록
	public int intsertAlba(AlbaVO alba);

	// 정보 삭제
	public int deleteAlba(String alba);

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
	public int updateLicAlba(Lic_albaVO licAlba);

	/**
	 * 알바 자격증 정보 모두 삭제
	 * 
	 * @param al_id
	 * @return
	 */
	public int deleteLicAlba(String al_id);

	/**
	 * 선택된 알바 자격증 정보 삭제
	 * 
	 * @param licAlba
	 * @return
	 */
	public int deleteLic(Lic_albaVO licAlba);

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
	public int insertLicAlba(Lic_albaVO licAlba);
}

package kr.or.ddit.advertise.dao;

import org.springframework.stereotype.Repository;

import kr.or.ddit.vo.AdattatchVO;
import kr.or.ddit.vo.AdvertiseVO;

@Repository
public interface IAdAttatchDAO {
	/**
	 * 첨부파일 등록
	 * @param ad
	 * @return
	 */
	public int insertAttatches(AdvertiseVO ad);
	/**
	 * 첨부파일 보기 
	 * @param adattId
	 * @return
	 */
	public AdattatchVO selectAttatch(String adattId);

	/**
	 * 첨부파일 삭제
	 * @param advertise
	 * @return
	 */
	public int deleteAttatches(AdvertiseVO advertise);
}

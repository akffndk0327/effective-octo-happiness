package kr.or.ddit.alba.service;

import java.util.List;

import kr.or.ddit.alba.vo.AlbaVO;
import kr.or.ddit.alba.vo.Lic_albaVO;
import kr.or.ddit.alba.vo.PagingInfoVO;
import kr.or.ddit.enums.ServiceResult;

public interface IAlbaService {
	
	public ServiceResult createAlba(AlbaVO alba); // insert
	
	public AlbaVO retrieveAlba(String al_id); //상세조회
	
	//페이징
	public int retrieveAlbaCount(PagingInfoVO<AlbaVO> pagingVO);
	
	//페이징 리스트 
	public List<AlbaVO> retrieveAlbaList(PagingInfoVO<AlbaVO> pagingVO);
	
	//알바 수정 
	public ServiceResult modifyAlba(AlbaVO alba);//수정
	
	//알바 삭제
	public ServiceResult removeAlba(String al_id); //제거
	
	public Lic_albaVO retrieveLicense(Lic_albaVO licAlba);
	
	
	
}

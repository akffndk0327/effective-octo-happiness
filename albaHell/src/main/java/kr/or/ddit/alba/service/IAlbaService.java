package kr.or.ddit.alba.service;

import java.util.List;

import kr.or.ddit.alba.vo.AlbaVO;
import kr.or.ddit.enums.ServiceResult;



public interface IAlbaService {
	//목록 조회
	public List<AlbaVO> selectAlbaList();
	
	//상세조회
	public AlbaVO selctAlba(AlbaVO alba);
	
	//정보 수정 
	public ServiceResult updateAlba(AlbaVO alba);
	//신규 등록
	public ServiceResult insertAlba(AlbaVO alba);
	//정보 삭제
	public ServiceResult deleteAlba(AlbaVO alba);
}

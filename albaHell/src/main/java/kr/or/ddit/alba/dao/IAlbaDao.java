package kr.or.ddit.alba.dao;

import java.util.List;

import kr.or.ddit.alba.vo.AlbaVO;

public interface IAlbaDao  {
	
	//목록 조회
	public List<AlbaVO> selectAlbaList();

	//상세조회
	public AlbaVO selctAlba(AlbaVO alba);
	
	//정보 수정 
	public int updateAlba(AlbaVO alba);
	
	//신규 등록
	public int intsertAlba(AlbaVO alba);
	
	//정보 삭제
	public int deleteAlba(AlbaVO alba);


}

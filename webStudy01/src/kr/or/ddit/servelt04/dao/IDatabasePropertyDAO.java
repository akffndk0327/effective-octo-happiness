package kr.or.ddit.servelt04.dao;

import java.util.List;
import java.util.Map;

import kr.or.ddit.vo.DataBasePropertyVO;

public interface IDatabasePropertyDAO {
	//시그니처 필요해 
	//조회할것 : db에있는 모든 vo
	//vo의 컬랙션인 list로 만들어져야해
	public List<DataBasePropertyVO> selectDataBasePropertyList(Map<String, Object> dataMap);
}

package kr.or.ddit.servelt04.service;

import java.util.List;
import java.util.Map;

import kr.or.ddit.servelt04.dao.DataBasePropertyDAOImpl;
import kr.or.ddit.servelt04.dao.IDatabasePropertyDAO;
import kr.or.ddit.vo.DataBasePropertyVO;

public class DataBasePropertyServiceImpl implements IDataBasePropertyService {
	//dao가져와햐해 
	IDatabasePropertyDAO dao = new  DataBasePropertyDAOImpl();

	@Override
	public void readAndLoggingDataBaseProperty(Map<String, Object> dataMap) {
		List<DataBasePropertyVO> list = dao.selectDataBasePropertyList(dataMap); //datamap : 프로퍼티 정보갖고있어
//		dataMap.get("list");
		for(DataBasePropertyVO vo :list) {
			System.out.printf("%s : %s [%s\n]", vo.getProperty_name(), vo.getProperty_value(),vo.getDescription()); //명, 값, 디스크립션
		}
		
		
	}

}

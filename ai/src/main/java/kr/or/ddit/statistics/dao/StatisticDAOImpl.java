package kr.or.ddit.statistics.dao;

import javax.inject.Inject;

import org.mybatis.spring.SqlSessionTemplate;

import kr.or.ddit.vo.BrowserVO;

/**
 * 
 * @author 박슬기
 * @since 2019. 11. 21.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                          수정자               수정내용
 * 
 * --------     --------    ----------------------
 * 2019. 11. 21.      박슬기       최초작성
 * Copyright (c) 2019 by DDIT All right reserved
 * </pre>
 */
public class StatisticDAOImpl implements IStatisticDAO {
	@Inject
	SqlSessionTemplate sqlSession;
	
	@Override
	public int insertLogin(BrowserVO browserVO) {
		IStatisticDAO mapper = sqlSession.getMapper(IStatisticDAO.class);
		int cnt =  mapper.insertLogin(browserVO);
		sqlSession.commit();
		return cnt;
	}

}

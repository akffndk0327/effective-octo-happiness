package kr.or.ddit.mypage.dao;

import org.springframework.stereotype.Repository;

/**
 * @author 최서희
 * @since 2019. 11. 11.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                          수정자               수정내용
 * --------     --------    ----------------------
 * 2019. 11. 11.      최서희       최초작성
 * Copyright (c) 2019 by DDIT All right reserved
 * </pre>
 */
@Repository
public interface IComReportDAO {
	
	public int selectJan(String loginId);
	public int selectFeb(String loginId);
	public int selectMar(String loginId);
	public int selectApr(String loginId);
	public int selectMay(String loginId);
	public int selectJun(String loginId);
	public int selectJul(String loginId);
	public int selectAug(String loginId);
	public int selectSep(String loginId);
	public int selectOct(String loginId);
	public int selectNov(String loginId);
	public int selectDec(String loginId);
	
}

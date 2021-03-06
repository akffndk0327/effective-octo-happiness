package kr.or.ddit.addrChart.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import kr.or.ddit.vo.AllergyVO;
import kr.or.ddit.vo.MemberVO;

/**
 * @author 이진희
 * @since 2019. 11. 20.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                          수정자               수정내용
 *
 * --------     --------    ----------------------
 * 2019. 11. 20.      이진희       최초작성
 * Copyright (c) 2019 by DDIT All right reserved
 * </pre>
 */
@Repository
public interface IMemChartDAO {
	
	//성별 발생률이 높은 비율의 알레르기 가지고 오기 
	public AllergyVO selectGender(MemberVO vo);
	
	//지역 발생률이 높은 비율의 알레르기 가지고 오기 
	public AllergyVO selectAddr(MemberVO vo);
	
	//생년월일 발생률이 높은 비율의 알레르기 가지고 오기 
	public AllergyVO selectAge(MemberVO vo);
}

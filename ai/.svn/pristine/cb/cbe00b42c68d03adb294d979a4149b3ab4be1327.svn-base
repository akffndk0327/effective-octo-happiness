package kr.or.ddit.mypage.service;

import java.util.List;

import kr.or.ddit.enums.ServiceResult;
import kr.or.ddit.vo.MemAllergyVO;
import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.MonthlyVO;
import kr.or.ddit.vo.Order2VO;
import kr.or.ddit.vo.PagingInfoVO;

/**
 * @author 허민지
 * @since 2019. 11. 19.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                          수정자         수정내용
 * 2019.11.27			박슬기		멤버별 조회 수정
 * --------     	  --------    ----------------------
 * 2019. 11. 19.      	허민지          최초작성
 * 2019. 11. 19			허민지	   createMemAllergy, removeMemAllergy 추가
 * Copyright (c) 2019 by DDIT All right reserved
 * </pre>
 */

public interface IMemMypageService {

	public ServiceResult createMemAllergy(MemberVO member);
	
	public int removeMemAllergy(MemAllergyVO member);
	
	public ServiceResult removeMember(String memId);
	
	public List<Order2VO> orderByOrderList(String memId);
	
	public List<MonthlyVO> retrieveMemberDietList(PagingInfoVO<MonthlyVO> pagingVO);
	
	public int retrieveMemberDietCount(PagingInfoVO<MonthlyVO> pagingVO);
}

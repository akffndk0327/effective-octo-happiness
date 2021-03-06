package kr.or.ddit.mypage.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

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
 * 수정일                          수정자               수정내용
  * 2019.11.27			박슬기		멤버별 조회 수정
 * --------     		--------    ----------------------
 * 2019. 11. 19.      	허민지      	 최초작성
 * 2019. 11. 19.   		허민지		updateMemAllergy, deleteMemAllergy추가
 * Copyright (c) 2019 by DDIT All right reserved
 * </pre>
 */
@Repository
public interface IMemMypageDAO {
	
	public int insertMemAllergy(MemberVO member);
	
	public int deleteMemAllergy(MemAllergyVO member);
	
	/**
	 * 회원 자진 탈퇴
	 * @param memId
	 * @return
	 */
	public int deleteMember(String memId);
	
	/**
	 * 멤버별 식단 목록 조회
	 * @param memId
	 * @return
	 */
	public List<MonthlyVO> selectMemberDietList(PagingInfoVO<MonthlyVO> pagingVO);
	
	/**
	 * 멤버별 식단 목록 페이징 처리를 위한 카운팅
	 * @param memId
	 * @return
	 */
	public int selectMemberDietCount(PagingInfoVO<MonthlyVO> pagingVO);
	
	
	/**
	 * 최근 주문내역 3건만
	 * @param member
	 * @return
	 */
	public List<Order2VO> orderByOrderList(String memId);
}

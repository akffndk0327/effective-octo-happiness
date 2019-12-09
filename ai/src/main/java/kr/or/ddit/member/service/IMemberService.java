package kr.or.ddit.member.service;

import java.util.List;

import kr.or.ddit.enums.ServiceResult;
import kr.or.ddit.vo.AccountVO;
import kr.or.ddit.vo.AllergySymtomVO;
import kr.or.ddit.vo.AllergyVO;
import kr.or.ddit.vo.ComInfoVO;
import kr.or.ddit.vo.CompanyVO;
import kr.or.ddit.vo.MemAllergyVO;
import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.MonthlyVO;
import kr.or.ddit.vo.RecipeBoardVO;

/**
 * @author 허민지
 * @since 2019. 11. 5.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                    수정자               수정내용
 * --------     --------    ----------------------
 * 2019. 11. 5.     허민지       최초작성
 * 2019. 11. 9.     허민지	 create추가
 * 2019. 11. 11 	허민지	 retrievcAllergy 추가
 * 2019. 11. 12     허민지       retrieveCompanyForSignUp, retrieveCompany, createCompany 추가
 * 2019. 11. 13     허민지       createCompany 삭제
 * 2019. 11. 15      허민지		 orderByMonthly 추가
 * 2019. 11. 18     이진희     회원한명의 알레르기정보 
 * Copyright (c) 2019 by DDIT All right reserved
 * </pre>
 */

public interface IMemberService {

	/**
	 * 회원 정보 상세 조회
	 * @param member
	 * @return 조건에 맞는 사용자가 없으면, UserNotFoundException 발생
	 */
	public MemberVO retrieveMember(MemberVO member);
	
	
	/**
	 * 신규등록
	 * @param member
	 * @return PKDUPLICATED, OK, FAILED
	 */
	public ServiceResult createMember(MemberVO member);
	
	public List<String> retrieveListAllergy();
	
	public List<String> retrieveSytomsList();
	
	public List<MonthlyVO> orderByMonthly(String memId);
	
	public List<RecipeBoardVO> orderByRecipe(String memId);
	
	public ServiceResult modifyMember(MemberVO member);
	
	public List<MemAllergyVO> selectMemAllergy(String memId);	
	
	public MemberVO idCheck(String memId);

	public MemberVO mailCheck(String memMail);
	
	public MemberVO findId(MemberVO member);
	
	public MemberVO findPass(MemberVO member);
	
	public ServiceResult updatePass(AccountVO account);
	
	//-----------company--------------
	public ComInfoVO retrieveCompanyForSignUp(String comNum);
	
	public CompanyVO retrieveCompany(CompanyVO company);
	
	//이진희---------------
	public MemberVO selectMemberAll(MemberVO mem);
}

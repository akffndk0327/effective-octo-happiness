package kr.or.ddit.member.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import kr.or.ddit.vo.AccountVO;
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
 * 수정일                     수정자               수정내용
 * --------     --------    ----------------------
 * 2019. 11. 5.      허민지       최초작성
 * 2019. 11. 9.      허민지	insertMember, insertAccount, insertAuthorities 추가
 * 
 * 2019. 11. 12      허민지    selectCompany,selectCompanyForSignUp,insertCompany 추가
 * 2019. 11. 13      허민지    insertCompany 삭제
 * 2019. 11. 15      허민지    orderByMonthly 추가
 * 2019. 11. 18      이진희    memAllergy 가지고 오는 메서드 
 * Copyright (c) 2019 by DDIT All right reserved
 * </pre>
 */

@Repository
public interface IMemberDAO {
	
	/**
	 * 계정부터 조회
	 * @param username
	 * @return
	 */
	public MemberVO selectAccount(String memId);
	/**
	 * 회원 권한 조회
	 * @param member
	 * @return
	 */
	
	
	/**
	 * 회원 상세 조회
	 * @param member 조회할 회원에 대한 조건을 가진 VO
	 * @return 조건에 맞는 회원이 없는 경우, null 반환
	 */
	public MemberVO selectMember(MemberVO member);
	
	
	/**
	 * @param member
	 * @return 등록성공(>0), 실패(<=0)
	 */
	public int insertAccount(MemberVO member);
	
	/**
	 * 신규등록
	 * member table
	 * @param member
	 * @return 등록성공 (>0), 실패(<=0)
	 */
	public int insertMember(MemberVO member);
	
	/**
	 * 회원가입시 받을 알러지 정보
	 * @return
	 */
	public List<String> selectAllergyList();
	
	/**
	 * 회원가입시 입력받을 알러지 리스트
	 * @return
	 */
	public List<String> selectSytomsList();
	
	
	/**
	 * 마이페이지에서 띄울 최근 식단 게시글 3개
	 * @param memId
	 * @return
	 */
	public List<MonthlyVO> orderByMonthly(String memId);
	
	
	/**
	 * 마이페이지 띄울 최근 식단 게시글 3개
	 * @param memId
	 * @return
	 */
	public List<RecipeBoardVO> orderByRecipe(String memId);
	
	
	/**
	 * 회원 수정
	 * @param member
	 * @return
	 */
	public int updateMember(MemberVO member);
	
	/**
	 * 회원의 알레르기 정보
	 * @param memId
	 * @return
	 */
	public List<MemAllergyVO> selectMemAllergy(String memId);	
	
	
	
	/**
	 * 아이디 중복검사
	 * @param member
	 * @return
	 */
	public MemberVO idCheck(String memId);
	
	
	/**
	 * 메일 중복 검사
	 * @param memMail
	 * @return
	 */
	public MemberVO mailCheck(String memMail);
	
	
	/**
	 * ID찾기
	 * @param member
	 * @return
	 */
	public MemberVO findId(MemberVO member);
	
	
	/**
	 * 비밀번호찾기
	 * @param member
	 * @return
	 */
	public MemberVO findPass(MemberVO member);
	
	
	/**
	 * 비밀번호 변경
	 * @param memPass
	 * @return
	 */
	public int updatePass(AccountVO account);
	
	//-------------company--------------
	/**
	 * @param company
	 * @return
	 */
	public int insertAccount(CompanyVO company);
	
	
	/**
	 * 회원가입시 사업자 번호를 넘기면 정보가 해당 정보가 출력
	 * @param comNum
	 * @return
	 */
	public ComInfoVO selectCompanyForSignUp(String comNum);
	
	
	/**
	 * 기업회원 상세 조회
	 * @param company
	 * @return
	 */
	public CompanyVO selectCompany(CompanyVO company);
	
	/**
	 * 회원한명의 알레르기 정보 조회
	 * @author 이진희
	 * @param mem
	 * @return
	 */
	public MemberVO selectMemberAll(MemberVO mem);
	
}

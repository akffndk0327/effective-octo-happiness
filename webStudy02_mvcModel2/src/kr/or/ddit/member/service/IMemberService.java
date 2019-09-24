package kr.or.ddit.member.service;

import java.util.List;

import kr.or.ddit.enums.ServiceResult;
import kr.or.ddit.vo.MemberVO;

/**
 * 회원 관리를 위한 Business logic layer  
 *crud
 */
public interface IMemberService {
	/**
	 * 신규등록
	 * @param member
	 * @return PKDUPLICATED, OK, FAILED
	 */
	public ServiceResult createMember(MemberVO member);
	/**
	 * 회원 정보 상세 조회 
	 * @param member
	 * @return 조건에 맞는 회원이 없다면, 유저낫파운드exception 
	 */
	public MemberVO retrieveMember(MemberVO member);
	/**
	 * 목록조회
	 * @param member
	 * @return 없으면 , size()== 0;
	 */
	public List<MemberVO> retrieveMemberList();
	/**
	 * 정보 수정 
	 * @param member
	 * @return UserNotfoundexception, invalidpassword, ok, failed
	 */
	public ServiceResult modifyMember(MemberVO member);
	/**
	 * 정보 삭제 
	 * @param member
	 * @return usernotfoundexception, invalidpassword, ok, failed
	 */
	public ServiceResult removeMember(MemberVO member);
	
}

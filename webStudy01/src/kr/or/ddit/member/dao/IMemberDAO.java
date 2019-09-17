package kr.or.ddit.member.dao;

import java.util.List;

import kr.or.ddit.vo.MemberVO;
/**
 *회원관리를 위한 persistence Layer
 *CRUD 
 *
 */
public interface IMemberDAO {
	/**
	 * 신규등록
	 * @param member
	 * @return 등록성공(>0), 실패(<=0)
	 */
	public int insertMember(MemberVO member);
	/**
	 * 회원목록 조회
	 * @return 조건에 맞는 회원ㅇ ㅣ없는 경우, size() ==0
	 */
	public List<MemberVO>selectMemeberList();
	/**
	 * 회원 상세 조회
	 * @param member 조회할 회원에 대한 조건을 가진 vo
	 * @return 조건에 맞는 회원이 없는 경우, null반환 
	 */
	public MemberVO selectMember(MemberVO member); //앞에 Vo는 db에서 조회한거  뒤에 vo는 클라이언트가 입력한거 
	/**
	 * 회원정보 수정
	 * @param member 수정할 정보를 가진 VO
	 * @return 수정 성공실패 여부를 확인할 row count
	 */
	public int updateMember(MemberVO member);
	/**
	 * 회원 정보 삭제
	 * @param member
	 * @return 삭제 성공실패여부를 확일 할 row count 
	 */
	public int deleteMember(MemberVO member);
}
